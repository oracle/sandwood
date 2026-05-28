package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DistributionTest6$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DistributionTest6$CoreInterface {
	
	// Declare the variables for the model.
	private boolean constrainedFlag$sample11 = true;
	private boolean[] constrainedFlag$sample27;
	private boolean constrainedFlag$sample5 = true;
	private double[] cv$var11$stateProbabilityGlobal;
	private double[][] cv$var27$stateProbabilityGlobal;
	private double[] cv$var5$stateProbabilityGlobal;
	private double[] distribution$sample11;
	private double[][] distribution$sample27;
	private double[] distribution$sample5;
	private boolean fixedFlag$sample11 = false;
	private boolean fixedFlag$sample27 = false;
	private boolean fixedFlag$sample5 = false;
	private boolean fixedProbFlag$sample11 = false;
	private boolean fixedProbFlag$sample27 = false;
	private boolean fixedProbFlag$sample49 = false;
	private boolean fixedProbFlag$sample5 = false;
	private boolean[] guard$sample11bernoulli48$global;
	private boolean[][] guard$sample27bernoulli48$global;
	private int length$value;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$sample27;
	private double[] logProbability$sample49;
	private double logProbability$v;
	private double logProbability$v1;
	private double logProbability$v2;
	private double logProbability$var11;
	private int size;
	private boolean system$gibbsForward = true;
	private boolean[] v;
	private int v1;
	private int[] v2;
	private boolean[] value;
	private double[] weightings;

	public DistributionTest6$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for distribution$sample11.
	@Override
	public final double[] get$distribution$sample11() {
		return distribution$sample11;
	}

	// Setter for distribution$sample11.
	@Override
	public final void set$distribution$sample11(double[] cv$value, boolean allocated$) {
		distribution$sample11 = cv$value;
	}

	// Getter for distribution$sample27.
	@Override
	public final double[][] get$distribution$sample27() {
		return distribution$sample27;
	}

	// Setter for distribution$sample27.
	@Override
	public final void set$distribution$sample27(double[][] cv$value, boolean allocated$) {
		distribution$sample27 = cv$value;
	}

	// Getter for distribution$sample5.
	@Override
	public final double[] get$distribution$sample5() {
		return distribution$sample5;
	}

	// Setter for distribution$sample5.
	@Override
	public final void set$distribution$sample5(double[] cv$value, boolean allocated$) {
		distribution$sample5 = cv$value;
	}

	// Getter for fixedFlag$sample11.
	@Override
	public final boolean get$fixedFlag$sample11() {
		return fixedFlag$sample11;
	}

	// Setter for fixedFlag$sample11.
	@Override
	public final void set$fixedFlag$sample11(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample11 including if probabilities
		// need to be updated.
		fixedFlag$sample11 = cv$value;
		
		// Substituted "fixedFlag$sample11" with its value "cv$value".
		constrainedFlag$sample11 = (cv$value || constrainedFlag$sample11);
		
		// Should the probability of sample 11 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample11" with its value "cv$value".
		fixedProbFlag$sample11 = (cv$value && fixedProbFlag$sample11);
		
		// Should the probability of sample 49 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample11" with its value "cv$value".
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
	}

	// Getter for fixedFlag$sample27.
	@Override
	public final boolean get$fixedFlag$sample27() {
		return fixedFlag$sample27;
	}

	// Setter for fixedFlag$sample27.
	@Override
	public final void set$fixedFlag$sample27(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample27 including if probabilities
		// need to be updated.
		fixedFlag$sample27 = cv$value;
		
		// If the model has been allocated update the constraints flags
		if(allocated$) {
			// Set all the values in the array
			for(int index$constrainedFlag$sample27$1 = 0; index$constrainedFlag$sample27$1 < constrainedFlag$sample27.length; index$constrainedFlag$sample27$1 += 1)
				// Substituted "fixedFlag$sample27" with its value "cv$value".
				constrainedFlag$sample27[index$constrainedFlag$sample27$1] = cv$value;
		}
		
		// Should the probability of sample 27 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample27" with its value "cv$value".
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
		
		// Should the probability of sample 49 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample27" with its value "cv$value".
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
	}

	// Getter for fixedFlag$sample5.
	@Override
	public final boolean get$fixedFlag$sample5() {
		return fixedFlag$sample5;
	}

	// Setter for fixedFlag$sample5.
	@Override
	public final void set$fixedFlag$sample5(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample5 including if probabilities
		// need to be updated.
		fixedFlag$sample5 = cv$value;
		
		// Substituted "fixedFlag$sample5" with its value "cv$value".
		constrainedFlag$sample5 = (cv$value || constrainedFlag$sample5);
		
		// Should the probability of sample 5 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample5" with its value "cv$value".
		fixedProbFlag$sample5 = (cv$value && fixedProbFlag$sample5);
		
		// Should the probability of sample 49 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample5" with its value "cv$value".
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
	}

	// Getter for length$value.
	@Override
	public final int get$length$value() {
		return length$value;
	}

	// Setter for length$value.
	@Override
	public final void set$length$value(int cv$value, boolean allocated$) {
		length$value = cv$value;
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

	// Getter for logProbability$v.
	@Override
	public final double get$logProbability$v() {
		return logProbability$v;
	}

	// Getter for logProbability$v1.
	@Override
	public final double get$logProbability$v1() {
		return logProbability$v1;
	}

	// Getter for logProbability$v2.
	@Override
	public final double get$logProbability$v2() {
		return logProbability$v2;
	}

	// Getter for size.
	@Override
	public final int get$size() {
		return size;
	}

	// Getter for v.
	@Override
	public final boolean[] get$v() {
		return v;
	}

	// Getter for v1.
	@Override
	public final int get$v1() {
		return v1;
	}

	// Setter for v1.
	@Override
	public final void set$v1(int cv$value, boolean allocated$) {
		// Set flags for all the side effects of v1 including if probabilities need to be
		// updated.
		v1 = cv$value;
		
		// Unset the fixed probability flag for sample 5 as it depends on v1.
		fixedProbFlag$sample5 = false;
		
		// Unset the fixed probability flag for sample 49 as it depends on v1.
		fixedProbFlag$sample49 = false;
	}

	// Getter for v2.
	@Override
	public final int[] get$v2() {
		return v2;
	}

	// Setter for v2.
	@Override
	public final void set$v2(int[] cv$value, boolean allocated$) {
		// Set flags for all the side effects of v2 including if probabilities need to be
		// updated.
		v2 = cv$value;
		
		// Unset the fixed probability flag for sample 11 as it depends on v2.
		fixedProbFlag$sample11 = false;
		
		// Unset the fixed probability flag for sample 27 as it depends on v2.
		fixedProbFlag$sample27 = false;
		
		// Unset the fixed probability flag for sample 49 as it depends on v2.
		fixedProbFlag$sample49 = false;
	}

	// Getter for value.
	@Override
	public final boolean[] get$value() {
		return value;
	}

	// Setter for value.
	@Override
	public final void set$value(boolean[] cv$value, boolean allocated$) {
		value = cv$value;
	}

	// Getter for weightings.
	@Override
	public final double[] get$weightings() {
		return weightings;
	}

	// Setter for weightings.
	@Override
	public final void set$weightings(double[] cv$value, boolean allocated$) {
		weightings = cv$value;
	}

	// Pick a value from the distribution for the unconditioned variable from sample11
	private final void drawValueSample11() {
		v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
	}

	// Pick a value from the distribution for the unconditioned variable from sample27
	private final void drawValueSample27(int i, int threadID$cv$i, Rng RNG$) {
		v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
	}

	// Pick a value from the distribution for the unconditioned variable from sample5
	private final void drawValueSample5() {
		v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 11 drawn from Categorical 10. Inference was performed using variable
	// marginalization.
	private final void inferSample11() {
		constrainedFlag$sample11 = false;
		
		// Variable declaration of cv$numStates moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		int cv$numStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Constructing a random variable input for use later.
			int $var2938 = weightings.length;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var2938) && (0 < $var2938)) && (0.0 <= weightings[cv$valuePos])) && (weightings[cv$valuePos] <= 1.0))?Math.log(weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < size)) {
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample11bernoulli48$global[0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample11bernoulli48$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample11bernoulli48$global[0] = true;
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample11 = true;
					
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Bernoulli 48 which is consuming
					// the output of Sample task 11.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 48 which is consuming
					// the output of Sample task 11.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 48 which is consuming
					// the output of Sample task 11.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 48 which is consuming
					// the output of Sample task 11.
					if(fixedFlag$sample5) {
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var47 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
						
						// Substituted "j" with its value "0".
						cv$accumulatedConsumerProbabilities = (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY);
						
						// Recorded the probability of reaching sample task 49 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						// Enumerating the possible outputs of Categorical 4.
						for(int index$sample5$11 = 0; index$sample5$11 < weightings.length; index$sample5$11 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample5Value12 = distribution$sample5[index$sample5$11];
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var47 = ((double)((index$sample5$11 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 49 generating output with current configuration.
							// 
							// Substituted "j" with its value "0".
							if(((Math.log(cv$probabilitySample5Value12) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								// Substituted "j" with its value "0".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value12) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value12) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								else
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value12) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value12)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
							}
							
							// Recorded the probability of reaching sample task 49 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value12);
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
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample11bernoulli48$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample11bernoulli48$global[0] = true;
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample11 = true;
					
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Bernoulli 48 which is consuming
					// the output of Sample task 11.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 48 which is consuming
					// the output of Sample task 11.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 48 which is consuming
					// the output of Sample task 11.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 48 which is consuming
					// the output of Sample task 11.
					if(fixedFlag$sample5) {
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var47 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
						
						// Substituted "j" with its value "0".
						cv$accumulatedConsumerProbabilities = (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY);
						
						// Recorded the probability of reaching sample task 49 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						// Enumerating the possible outputs of Categorical 4.
						for(int index$sample5$155 = 0; index$sample5$155 < weightings.length; index$sample5$155 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample5Value156 = distribution$sample5[index$sample5$155];
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var47 = ((double)((index$sample5$155 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 49 generating output with current configuration.
							// 
							// Substituted "j" with its value "0".
							if(((Math.log(cv$probabilitySample5Value156) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								// Substituted "j" with its value "0".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value156) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value156) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								else
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value156) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value156)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
							}
							
							// Recorded the probability of reaching sample task 49 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value156);
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
				
				// Substituted "j" with its value "0".
				if(!guard$sample11bernoulli48$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample11bernoulli48$global[0] = true;
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample11 = true;
					
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Bernoulli 48 which is consuming
					// the output of Sample task 11.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 48 which is consuming
					// the output of Sample task 11.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 48 which is consuming
					// the output of Sample task 11.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 48 which is consuming
					// the output of Sample task 11.
					if(fixedFlag$sample5) {
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var47 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
						
						// Substituted "j" with its value "0".
						cv$accumulatedConsumerProbabilities = (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY);
						
						// Recorded the probability of reaching sample task 49 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						// Enumerating the possible outputs of Categorical 4.
						for(int index$sample5$299 = 0; index$sample5$299 < weightings.length; index$sample5$299 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample5Value300 = distribution$sample5[index$sample5$299];
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var47 = ((double)((index$sample5$299 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 49 generating output with current configuration.
							// 
							// Substituted "j" with its value "0".
							if(((Math.log(cv$probabilitySample5Value300) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								// Substituted "j" with its value "0".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value300) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value300) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								else
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value300) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value300)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
							}
							
							// Recorded the probability of reaching sample task 49 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value300);
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
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var11$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample11) {
			// Set the calculated probabilities to be the distribution values, and normalize
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var11$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = cv$var11$stateProbabilityGlobal[cv$lseIndex];
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
					cv$lseSum = (cv$lseSum + Math.exp((cv$var11$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Local copy of the probability array
					distribution$sample11[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Local copy of the probability array
					distribution$sample11[cv$indexName] = Math.exp((cv$var11$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var11$stateProbabilityGlobal.length; cv$indexName += 1)
				// Local copy of the probability array
				distribution$sample11[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 27 drawn from Categorical 26. Inference was performed using variable
	// marginalization.
	private final void inferSample27(int i, int threadID$cv$i, Rng RNG$) {
		constrainedFlag$sample27[i] = false;
		
		// Variable declaration of cv$numStates moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		int cv$numStates = weightings.length;
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var27$stateProbabilityGlobal[threadID$cv$i];
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Constructing a random variable input for use later.
			int $var3595 = weightings.length;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var3595) && (0 < $var3595)) && (0.0 <= weightings[cv$valuePos])) && (weightings[cv$valuePos] <= 1.0))?Math.log(weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Processing random variable 48.
			// 
			// Looking for a path between Sample 27 and consumer Bernoulli 48.
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			boolean[] guard$sample27bernoulli48 = guard$sample27bernoulli48$global[threadID$cv$i];
			{
				int j = (i + 1);
				if((j < size))
					// Set the flags to false
					guard$sample27bernoulli48[j] = false;
			}
			{
				int j = (i + 1);
				if((j < size))
					// Set the flags to false
					guard$sample27bernoulli48[j] = false;
			}
			{
				int j = (i + 1);
				if((j < size))
					// Set the flags to false
					guard$sample27bernoulli48[j] = false;
			}
			{
				int j = (i + 1);
				if(((j < size) && !guard$sample27bernoulli48[j])) {
					// The body will execute, so should not be executed again
					guard$sample27bernoulli48[j] = true;
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample27[i] = true;
					
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Bernoulli 48 which is consuming
					// the output of Sample task 27.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 48 which is consuming
					// the output of Sample task 27.
					if(fixedFlag$sample5) {
						{
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var47 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							cv$accumulatedConsumerProbabilities = (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 49 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						int index$i$132 = (j - 1);
						
						// index$i$1's comment
						// Copy of index so that its values can be safely substituted
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						if(!(index$i$132 == i)) {
							// Enumerating the possible outputs of Categorical 26.
							for(int index$sample27$133 = 0; index$sample27$133 < weightings.length; index$sample27$133 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample27Value134 = distribution$sample27[index$i$132][index$sample27$133];
								
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var47 = ((double)((v1 + cv$valuePos) + cv$valuePos) / index$sample27$133);
								
								// Record the probability of sample task 49 generating output with current configuration.
								if(((Math.log(cv$probabilitySample27Value134) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value134) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value134) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value134) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value134)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								}
								
								// Recorded the probability of reaching sample task 49 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value134);
							}
						}
						int index$i$120 = (j - 1);
						
						// index$i$1's comment
						// Copy of index so that its values can be safely substituted
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						if(!(index$i$120 == i)) {
							// Enumerating the possible outputs of Categorical 26.
							for(int index$sample27$121 = 0; index$sample27$121 < weightings.length; index$sample27$121 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample27Value122 = distribution$sample27[index$i$120][index$sample27$121];
								{
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var47 = ((double)((v1 + cv$valuePos) + index$sample27$121) / cv$valuePos);
									
									// Record the probability of sample task 49 generating output with current configuration.
									if(((Math.log(cv$probabilitySample27Value122) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value122) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value122) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value122) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value122)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									}
									
									// Recorded the probability of reaching sample task 49 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value122);
								}
								
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var47 = ((double)((v1 + cv$valuePos) + index$sample27$121) / index$sample27$121);
								
								// Record the probability of sample task 49 generating output with current configuration.
								if(((Math.log(cv$probabilitySample27Value122) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value122) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value122) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value122) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value122)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								}
								
								// Recorded the probability of reaching sample task 49 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value122);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 4.
						for(int index$sample5$115 = 0; index$sample5$115 < weightings.length; index$sample5$115 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample5Value116 = distribution$sample5[index$sample5$115];
							{
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var47 = ((double)((index$sample5$115 + cv$valuePos) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 49 generating output with current configuration.
								if(((Math.log(cv$probabilitySample5Value116) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value116) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value116) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value116) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value116)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								}
								
								// Recorded the probability of reaching sample task 49 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value116);
							}
							int index$i$145 = (j - 1);
							
							// index$i$1's comment
							// Copy of index so that its values can be safely substituted
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							if(!(index$i$145 == i)) {
								// Enumerating the possible outputs of Categorical 26.
								for(int index$sample27$146 = 0; index$sample27$146 < weightings.length; index$sample27$146 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample27Value147 = (cv$probabilitySample5Value116 * distribution$sample27[index$i$145][index$sample27$146]);
									
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var47 = ((double)((index$sample5$115 + cv$valuePos) + cv$valuePos) / index$sample27$146);
									
									// Record the probability of sample task 49 generating output with current configuration.
									if(((Math.log(cv$probabilitySample27Value147) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value147) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value147) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value147) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value147)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									}
									
									// Recorded the probability of reaching sample task 49 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value147);
								}
							}
							int index$i$126 = (j - 1);
							
							// index$i$1's comment
							// Copy of index so that its values can be safely substituted
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							if(!(index$i$126 == i)) {
								// Enumerating the possible outputs of Categorical 26.
								for(int index$sample27$127 = 0; index$sample27$127 < weightings.length; index$sample27$127 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample27Value128 = (cv$probabilitySample5Value116 * distribution$sample27[index$i$126][index$sample27$127]);
									{
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double var47 = ((double)((index$sample5$115 + cv$valuePos) + index$sample27$127) / cv$valuePos);
										
										// Record the probability of sample task 49 generating output with current configuration.
										if(((Math.log(cv$probabilitySample27Value128) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value128) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											// If the second value is -infinity.
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value128) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
											else
												cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value128) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value128)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
										}
										
										// Recorded the probability of reaching sample task 49 with the current configuration.
										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value128);
									}
									
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var47 = ((double)((index$sample5$115 + cv$valuePos) + index$sample27$127) / index$sample27$127);
									
									// Record the probability of sample task 49 generating output with current configuration.
									if(((Math.log(cv$probabilitySample27Value128) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value128) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value128) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value128) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value128)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									}
									
									// Recorded the probability of reaching sample task 49 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value128);
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
			{
				int j = (i + 1);
				if(((j < size) && !guard$sample27bernoulli48[j])) {
					// The body will execute, so should not be executed again
					guard$sample27bernoulli48[j] = true;
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample27[i] = true;
					
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Bernoulli 48 which is consuming
					// the output of Sample task 27.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 48 which is consuming
					// the output of Sample task 27.
					if(fixedFlag$sample5) {
						{
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var47 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							cv$accumulatedConsumerProbabilities = (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 49 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						int index$i$278 = (j - 1);
						
						// index$i$1's comment
						// Copy of index so that its values can be safely substituted
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						if(!(index$i$278 == i)) {
							// Enumerating the possible outputs of Categorical 26.
							for(int index$sample27$279 = 0; index$sample27$279 < weightings.length; index$sample27$279 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample27Value280 = distribution$sample27[index$i$278][index$sample27$279];
								
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var47 = ((double)((v1 + cv$valuePos) + cv$valuePos) / index$sample27$279);
								
								// Record the probability of sample task 49 generating output with current configuration.
								if(((Math.log(cv$probabilitySample27Value280) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value280) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value280) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value280) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value280)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								}
								
								// Recorded the probability of reaching sample task 49 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value280);
							}
						}
						int index$i$266 = (j - 1);
						
						// index$i$1's comment
						// Copy of index so that its values can be safely substituted
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						if(!(index$i$266 == i)) {
							// Enumerating the possible outputs of Categorical 26.
							for(int index$sample27$267 = 0; index$sample27$267 < weightings.length; index$sample27$267 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample27Value268 = distribution$sample27[index$i$266][index$sample27$267];
								{
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var47 = ((double)((v1 + index$sample27$267) + cv$valuePos) / cv$valuePos);
									
									// Record the probability of sample task 49 generating output with current configuration.
									if(((Math.log(cv$probabilitySample27Value268) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value268) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value268) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value268) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value268)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									}
									
									// Recorded the probability of reaching sample task 49 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value268);
								}
								
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var47 = ((double)((v1 + index$sample27$267) + cv$valuePos) / index$sample27$267);
								
								// Record the probability of sample task 49 generating output with current configuration.
								if(((Math.log(cv$probabilitySample27Value268) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value268) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value268) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value268) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value268)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								}
								
								// Recorded the probability of reaching sample task 49 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value268);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 4.
						for(int index$sample5$261 = 0; index$sample5$261 < weightings.length; index$sample5$261 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample5Value262 = distribution$sample5[index$sample5$261];
							{
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var47 = ((double)((index$sample5$261 + cv$valuePos) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 49 generating output with current configuration.
								if(((Math.log(cv$probabilitySample5Value262) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value262) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value262) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value262) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value262)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								}
								
								// Recorded the probability of reaching sample task 49 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value262);
							}
							int index$i$291 = (j - 1);
							
							// index$i$1's comment
							// Copy of index so that its values can be safely substituted
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							if(!(index$i$291 == i)) {
								// Enumerating the possible outputs of Categorical 26.
								for(int index$sample27$292 = 0; index$sample27$292 < weightings.length; index$sample27$292 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample27Value293 = (cv$probabilitySample5Value262 * distribution$sample27[index$i$291][index$sample27$292]);
									
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var47 = ((double)((index$sample5$261 + cv$valuePos) + cv$valuePos) / index$sample27$292);
									
									// Record the probability of sample task 49 generating output with current configuration.
									if(((Math.log(cv$probabilitySample27Value293) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value293) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value293) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value293) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value293)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									}
									
									// Recorded the probability of reaching sample task 49 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value293);
								}
							}
							int index$i$272 = (j - 1);
							
							// index$i$1's comment
							// Copy of index so that its values can be safely substituted
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							if(!(index$i$272 == i)) {
								// Enumerating the possible outputs of Categorical 26.
								for(int index$sample27$273 = 0; index$sample27$273 < weightings.length; index$sample27$273 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample27Value274 = (cv$probabilitySample5Value262 * distribution$sample27[index$i$272][index$sample27$273]);
									{
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double var47 = ((double)((index$sample5$261 + index$sample27$273) + cv$valuePos) / cv$valuePos);
										
										// Record the probability of sample task 49 generating output with current configuration.
										if(((Math.log(cv$probabilitySample27Value274) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value274) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											// If the second value is -infinity.
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value274) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
											else
												cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value274) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value274)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
										}
										
										// Recorded the probability of reaching sample task 49 with the current configuration.
										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value274);
									}
									
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var47 = ((double)((index$sample5$261 + index$sample27$273) + cv$valuePos) / index$sample27$273);
									
									// Record the probability of sample task 49 generating output with current configuration.
									if(((Math.log(cv$probabilitySample27Value274) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value274) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value274) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value274) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value274)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									}
									
									// Recorded the probability of reaching sample task 49 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value274);
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
			int j = (i + 1);
			if(((j < size) && !guard$sample27bernoulli48[j])) {
				// The body will execute, so should not be executed again
				guard$sample27bernoulli48[j] = true;
				
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample27[i] = true;
				
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Bernoulli 48 which is consuming
				// the output of Sample task 27.
				// 
				// Enumerating the possible arguments for the variable Bernoulli 48 which is consuming
				// the output of Sample task 27.
				if(fixedFlag$sample5) {
					{
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var47 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
						cv$accumulatedConsumerProbabilities = (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY);
						
						// Recorded the probability of reaching sample task 49 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					int index$i$424 = (j - 1);
					
					// index$i$1's comment
					// Copy of index so that its values can be safely substituted
					// 
					// Substituted "j" with its value "(i + 1)".
					// 
					// Substituted "j" with its value "(i + 1)".
					// 
					// Substituted "j" with its value "(i + 1)".
					if(!(index$i$424 == i)) {
						// Enumerating the possible outputs of Categorical 26.
						for(int index$sample27$425 = 0; index$sample27$425 < weightings.length; index$sample27$425 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample27Value426 = distribution$sample27[index$i$424][index$sample27$425];
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var47 = ((double)((v1 + cv$valuePos) + index$sample27$425) / cv$valuePos);
							
							// Record the probability of sample task 49 generating output with current configuration.
							if(((Math.log(cv$probabilitySample27Value426) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value426) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value426) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value426) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value426)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
							}
							
							// Recorded the probability of reaching sample task 49 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value426);
						}
					}
					int index$i$412 = (j - 1);
					
					// index$i$1's comment
					// Copy of index so that its values can be safely substituted
					// 
					// Substituted "j" with its value "(i + 1)".
					// 
					// Substituted "j" with its value "(i + 1)".
					// 
					// Substituted "j" with its value "(i + 1)".
					if(!(index$i$412 == i)) {
						// Enumerating the possible outputs of Categorical 26.
						for(int index$sample27$413 = 0; index$sample27$413 < weightings.length; index$sample27$413 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample27Value414 = distribution$sample27[index$i$412][index$sample27$413];
							{
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var47 = ((double)((v1 + index$sample27$413) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 49 generating output with current configuration.
								if(((Math.log(cv$probabilitySample27Value414) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value414) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value414) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value414) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value414)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								}
								
								// Recorded the probability of reaching sample task 49 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value414);
							}
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var47 = ((double)((v1 + index$sample27$413) + index$sample27$413) / cv$valuePos);
							
							// Record the probability of sample task 49 generating output with current configuration.
							if(((Math.log(cv$probabilitySample27Value414) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value414) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value414) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value414) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value414)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
							}
							
							// Recorded the probability of reaching sample task 49 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value414);
						}
					}
				} else {
					// Enumerating the possible outputs of Categorical 4.
					for(int index$sample5$407 = 0; index$sample5$407 < weightings.length; index$sample5$407 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample5Value408 = distribution$sample5[index$sample5$407];
						{
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var47 = ((double)((index$sample5$407 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 49 generating output with current configuration.
							if(((Math.log(cv$probabilitySample5Value408) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value408) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value408) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value408) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value408)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
							}
							
							// Recorded the probability of reaching sample task 49 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value408);
						}
						int index$i$437 = (j - 1);
						
						// index$i$1's comment
						// Copy of index so that its values can be safely substituted
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						if(!(index$i$437 == i)) {
							// Enumerating the possible outputs of Categorical 26.
							for(int index$sample27$438 = 0; index$sample27$438 < weightings.length; index$sample27$438 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample27Value439 = (cv$probabilitySample5Value408 * distribution$sample27[index$i$437][index$sample27$438]);
								
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var47 = ((double)((index$sample5$407 + cv$valuePos) + index$sample27$438) / cv$valuePos);
								
								// Record the probability of sample task 49 generating output with current configuration.
								if(((Math.log(cv$probabilitySample27Value439) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value439) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value439) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value439) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value439)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								}
								
								// Recorded the probability of reaching sample task 49 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value439);
							}
						}
						int index$i$418 = (j - 1);
						
						// index$i$1's comment
						// Copy of index so that its values can be safely substituted
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						if(!(index$i$418 == i)) {
							// Enumerating the possible outputs of Categorical 26.
							for(int index$sample27$419 = 0; index$sample27$419 < weightings.length; index$sample27$419 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample27Value420 = (cv$probabilitySample5Value408 * distribution$sample27[index$i$418][index$sample27$419]);
								{
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var47 = ((double)((index$sample5$407 + index$sample27$419) + cv$valuePos) / cv$valuePos);
									
									// Record the probability of sample task 49 generating output with current configuration.
									if(((Math.log(cv$probabilitySample27Value420) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value420) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value420) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value420) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value420)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									}
									
									// Recorded the probability of reaching sample task 49 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value420);
								}
								
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var47 = ((double)((index$sample5$407 + index$sample27$419) + index$sample27$419) / cv$valuePos);
								
								// Record the probability of sample task 49 generating output with current configuration.
								if(((Math.log(cv$probabilitySample27Value420) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value420) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value420) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value420) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value420)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								}
								
								// Recorded the probability of reaching sample task 49 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value420);
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
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$stateProbabilityLocal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample27[i]) {
			// Set the calculated probabilities to be the distribution values, and normalize
			// Local copy of the probability array
			double[] cv$localProbability = distribution$sample27[i];
			
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
	// by sample task 5 drawn from Categorical 4. Inference was performed using variable
	// marginalization.
	private final void inferSample5() {
		constrainedFlag$sample5 = false;
		
		// Variable declaration of cv$numStates moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		int cv$numStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Constructing a random variable input for use later.
			int $var2608 = weightings.length;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var2608) && (0 < $var2608)) && (0.0 <= weightings[cv$valuePos])) && (weightings[cv$valuePos] <= 1.0))?Math.log(weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			for(int j = 0; j < size; j += 1) {
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample5 = true;
				
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Bernoulli 48 which is consuming
				// the output of Sample task 5.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == j)) {
					if(fixedFlag$sample11) {
						// Constructing a random variable input for use later.
						// 
						// Substituted "j" with its value "0".
						// 
						// Value of the variable at this index
						double var47 = ((double)((cv$valuePos + v2[0]) + v2[0]) / v2[0]);
						
						// Substituted "j" with its value "0".
						cv$accumulatedConsumerProbabilities = (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY);
						
						// Recorded the probability of reaching sample task 49 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						// Enumerating the possible outputs of Categorical 10.
						for(int index$sample11$4 = 0; index$sample11$4 < weightings.length; index$sample11$4 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample11Value5 = distribution$sample11[index$sample11$4];
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var47 = ((double)((cv$valuePos + index$sample11$4) + index$sample11$4) / index$sample11$4);
							
							// Record the probability of sample task 49 generating output with current configuration.
							// 
							// Substituted "j" with its value "0".
							if(((Math.log(cv$probabilitySample11Value5) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								// Substituted "j" with its value "0".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value5) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value5) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								else
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value5) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample11Value5)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
							}
							
							// Recorded the probability of reaching sample task 49 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value5);
						}
					}
				}
				
				// Enumerating the possible arguments for the variable Bernoulli 48 which is consuming
				// the output of Sample task 5.
				if(fixedFlag$sample27) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 <= j)) {
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double var47 = ((double)((cv$valuePos + v2[j]) + v2[j]) / v2[j]);
						
						// Record the probability of sample task 49 generating output with current configuration.
						if(((((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY);
							else
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY))) + 1)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
						}
						
						// Recorded the probability of reaching sample task 49 with the current configuration.
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
					}
				} else {
					int i = (j - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 <= i)) {
						// Enumerating the possible outputs of Categorical 26.
						for(int index$sample27$203 = 0; index$sample27$203 < weightings.length; index$sample27$203 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample27Value204 = distribution$sample27[i][index$sample27$203];
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var47 = ((double)((cv$valuePos + index$sample27$203) + index$sample27$203) / index$sample27$203);
							
							// Record the probability of sample task 49 generating output with current configuration.
							if(((Math.log(cv$probabilitySample27Value204) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value204) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value204) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value204) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value204)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
							}
							
							// Recorded the probability of reaching sample task 49 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value204);
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
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var5$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample5) {
			// Set the calculated probabilities to be the distribution values, and normalize
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var5$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = cv$var5$stateProbabilityGlobal[cv$lseIndex];
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
					cv$lseSum = (cv$lseSum + Math.exp((cv$var5$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Local copy of the probability array
					distribution$sample5[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Local copy of the probability array
					distribution$sample5[cv$indexName] = Math.exp((cv$var5$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var5$stateProbabilityGlobal.length; cv$indexName += 1)
				// Local copy of the probability array
				distribution$sample5[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Calculate the probability of the samples represented by sample11 using probability
	// distributions.
	private final void logProbabilityDistribution$sample11() {
		// Determine if we need to calculate the values for sample task 11 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample11) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample11) {
				// Generating probabilities for sample task
				// The sample value to calculate the probability of generating
				int cv$sampleValue = v2[0];
				
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
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[cv$sampleValue])) && (weightings[cv$sampleValue] <= 1.0))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Store the sample task probability
				logProbability$var11 = cv$distributionAccumulator;
				
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
				logProbability$v2 = (logProbability$v2 + cv$distributionAccumulator);
				
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
				// Substituted "fixedFlag$sample11" with its value "true".
				fixedProbFlag$sample11 = true;
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample11)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$v2 = (logProbability$v2 + logProbability$var11);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var11);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample11)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var11);
		}
	}

	// Calculate the probability of the samples represented by sample27 using probability
	// distributions.
	private final void logProbabilityDistribution$sample27() {
		// Determine if we need to calculate the values for sample task 27 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample27) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample27) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int i = 0; i < size; i += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = v2[(i + 1)];
					
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
					double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[cv$sampleValue])) && (weightings[cv$sampleValue] <= 1.0))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					// 
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					
					// Store the sample task probability
					logProbability$sample27[i] = cv$distributionAccumulator;
				}
				
				// Update the variable probability
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample27" with its value "true".
				fixedProbFlag$sample27 = true;
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample27[i]);
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample27)
				// Update the variable probability
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample49 using probability
	// distributions.
	private final void logProbabilityDistribution$sample49() {
		// Determine if we need to calculate the values for sample task 49 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample49) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 49 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				boolean cv$sampleValue = v[j];
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == j)) {
					// Enumerating the possible arguments for Bernoulli 48.
					// 
					// Enumerating the possible arguments for Bernoulli 48.
					// 
					// Enumerating the possible arguments for Bernoulli 48.
					// 
					// Enumerating the possible arguments for Bernoulli 48.
					// 
					// Enumerating the possible arguments for Bernoulli 48.
					if(fixedFlag$sample5) {
						if(fixedFlag$sample11) {
							// Substituted "j" with its value "0".
							double var47 = ((double)((v1 + v2[0]) + v2[0]) / v2[0]);
							
							// Store the value of the function call, so the function call is only made once.
							cv$distributionAccumulator = (((0.0 <= var47) && (var47 <= 1.0))?Math.log((cv$sampleValue?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY);
							
							// Add the probability of this distribution configuration to the accumulator.
							// 
							// An accumulator for the distributed probability space covered.
							cv$probabilityReached = 1.0;
						} else {
							// Enumerating the possible outputs of Categorical 10.
							for(int index$sample11$8 = 0; index$sample11$8 < weightings.length; index$sample11$8 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample11Value9 = distribution$sample11[index$sample11$8];
								double var47 = ((double)((v1 + index$sample11$8) + index$sample11$8) / index$sample11$8);
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample11Value9) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((cv$sampleValue?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value9);
							}
						}
					}
					// Enumerating the possible arguments for Bernoulli 48.
					// 
					// Enumerating the possible arguments for Bernoulli 48.
					else {
						// Enumerating the possible outputs of Categorical 4.
						for(int index$sample5$3 = 0; index$sample5$3 < weightings.length; index$sample5$3 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample5Value4 = distribution$sample5[index$sample5$3];
							if(fixedFlag$sample11) {
								// Substituted "j" with its value "0".
								double var47 = ((double)((index$sample5$3 + v2[0]) + v2[0]) / v2[0]);
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample5Value4) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((cv$sampleValue?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value4);
							} else {
								// Enumerating the possible outputs of Categorical 10.
								for(int index$sample11$13 = 0; index$sample11$13 < weightings.length; index$sample11$13 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample11Value14 = (cv$probabilitySample5Value4 * distribution$sample11[index$sample11$13]);
									double var47 = ((double)((index$sample5$3 + index$sample11$13) + index$sample11$13) / index$sample11$13);
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample11Value14) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((cv$sampleValue?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value14);
								}
							}
						}
					}
				}
				
				// Enumerating the possible arguments for Bernoulli 48.
				if(fixedFlag$sample5) {
					if(fixedFlag$sample27) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 <= j)) {
							double var47 = ((double)((v1 + v2[j]) + v2[j]) / v2[j]);
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (((0.0 <= var47) && (var47 <= 1.0))?Math.log((cv$sampleValue?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY);
							
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
						int i = (j - 1);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= i)) {
							// Enumerating the possible outputs of Categorical 26.
							for(int index$sample27$440 = 0; index$sample27$440 < weightings.length; index$sample27$440 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample27Value441 = distribution$sample27[i][index$sample27$440];
								double var47 = ((double)((v1 + index$sample27$440) + index$sample27$440) / index$sample27$440);
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample27Value441) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((cv$sampleValue?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value441);
							}
						}
					}
				} else {
					// Enumerating the possible outputs of Categorical 4.
					for(int index$sample5$434 = 0; index$sample5$434 < weightings.length; index$sample5$434 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample5Value435 = distribution$sample5[index$sample5$434];
						if(fixedFlag$sample27) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((1 <= j)) {
								double var47 = ((double)((index$sample5$434 + v2[j]) + v2[j]) / v2[j]);
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample5Value435) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((cv$sampleValue?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value435);
							}
						} else {
							int i = (j - 1);
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((0 <= i)) {
								// Enumerating the possible outputs of Categorical 26.
								for(int index$sample27$446 = 0; index$sample27$446 < weightings.length; index$sample27$446 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample27Value447 = (cv$probabilitySample5Value435 * distribution$sample27[i][index$sample27$446]);
									double var47 = ((double)((index$sample5$434 + index$sample27$446) + index$sample27$446) / index$sample27$446);
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample27Value447) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((cv$sampleValue?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value447);
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
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample49[j] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$v = (logProbability$v + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample49 = ((fixedFlag$sample5 && fixedFlag$sample11) && fixedFlag$sample27);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample49[j]);
			
			// Update the variable probability
			logProbability$v = (logProbability$v + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample5 using probability
	// distributions.
	private final void logProbabilityDistribution$sample5() {
		// Determine if we need to calculate the values for sample task 5 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample5) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample5) {
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
				double cv$distributionAccumulator = ((((((0.0 <= v1) && (v1 < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[v1])) && (weightings[v1] <= 1.0))?Math.log(weightings[v1]):Double.NEGATIVE_INFINITY);
				
				// Store the sample task probability
				logProbability$v1 = cv$distributionAccumulator;
				
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
				// Substituted "fixedFlag$sample5" with its value "true".
				fixedProbFlag$sample5 = true;
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$v1);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample5)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$v1);
		}
	}

	// Calculate the probability of the samples represented by sample11 using sampled
	// values.
	private final void logProbabilityValue$sample11() {
		// Determine if we need to calculate the values for sample task 11 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample11) {
			// Generating probabilities for sample task
			// The sample value to calculate the probability of generating
			int cv$sampleValue = v2[0];
			
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
			double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[cv$sampleValue])) && (weightings[cv$sampleValue] <= 1.0))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			logProbability$var11 = cv$distributionAccumulator;
			
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
			logProbability$v2 = (logProbability$v2 + cv$distributionAccumulator);
			
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
			if(fixedFlag$sample11)
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
			fixedProbFlag$sample11 = fixedFlag$sample11;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$v2 = (logProbability$v2 + logProbability$var11);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var11);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample11)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var11);
		}
	}

	// Calculate the probability of the samples represented by sample27 using sampled
	// values.
	private final void logProbabilityValue$sample27() {
		// Determine if we need to calculate the values for sample task 27 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample27) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = v2[(i + 1)];
				
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
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[cv$sampleValue])) && (weightings[cv$sampleValue] <= 1.0))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample27[i] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample27 = fixedFlag$sample27;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample27[i]);
			
			// Update the variable probability
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample49 using sampled
	// values.
	private final void logProbabilityValue$sample49() {
		// Determine if we need to calculate the values for sample task 49 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample49) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double var47 = ((double)((v1 + v2[j]) + v2[j]) / v2[j]);
				
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
				double cv$distributionAccumulator = (((0.0 <= var47) && (var47 <= 1.0))?Math.log((v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample49[j] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$v = (logProbability$v + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample49 = ((fixedFlag$sample5 && fixedFlag$sample11) && fixedFlag$sample27);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample49[j]);
			
			// Update the variable probability
			logProbability$v = (logProbability$v + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample5 using sampled values.
	private final void logProbabilityValue$sample5() {
		// Determine if we need to calculate the values for sample task 5 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample5) {
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
			double cv$distributionAccumulator = ((((((0.0 <= v1) && (v1 < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[v1])) && (weightings[v1] <= 1.0))?Math.log(weightings[v1]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			logProbability$v1 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample5)
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
			fixedProbFlag$sample5 = fixedFlag$sample5;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$v1);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample5)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$v1);
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var5$stateProbabilityGlobal
		// 
		// Allocation of cv$var5$stateProbabilityGlobal for single threaded execution
		cv$var5$stateProbabilityGlobal = new double[weightings.length];
		
		// Constructor for cv$var11$stateProbabilityGlobal
		// 
		// Allocation of cv$var11$stateProbabilityGlobal for single threaded execution
		cv$var11$stateProbabilityGlobal = new double[weightings.length];
		
		// Constructor for guard$sample11bernoulli48$global
		// 
		// Allocation of guard$sample11bernoulli48$global for single threaded execution
		guard$sample11bernoulli48$global = new boolean[length$value];
		
		// Constructor for cv$var27$stateProbabilityGlobal
		{
			// Allocation of cv$var27$stateProbabilityGlobal for multithreaded execution
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var27$stateProbabilityGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var27$stateProbabilityGlobal[cv$index] = new double[weightings.length];
		}
		
		// Allocation of guard$sample27bernoulli48$global for multithreaded execution
		// 
		// Get the thread count.
		int cv$threadCount = threadCount();
		
		// Allocate an array to hold a copy per thread
		guard$sample27bernoulli48$global = new boolean[cv$threadCount][];
		
		// Populate the array with a copy per thread
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			guard$sample27bernoulli48$global[cv$index] = new boolean[length$value];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If v2 has not been set already allocate space.
		if((!fixedFlag$sample11 || !fixedFlag$sample27))
			// Constructor for v2
			v2 = new int[(length$value + 1)];
		
		// Constructor for v
		v = new boolean[length$value];
		
		// Constructor for distribution$sample5
		distribution$sample5 = new double[weightings.length];
		
		// Constructor for distribution$sample11
		distribution$sample11 = new double[weightings.length];
		
		// Constructor for distribution$sample27
		distribution$sample27 = new double[length$value][];
		for(int i = 0; i < length$value; i += 1)
			distribution$sample27[i] = new double[weightings.length];
		
		// Constructor for constrainedFlag$sample27
		constrainedFlag$sample27 = new boolean[length$value];
		
		// Constructor for logProbability$sample27
		logProbability$sample27 = new double[length$value];
		
		// Constructor for logProbability$sample49
		logProbability$sample49 = new double[length$value];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample11)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample27)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings, weightings.length);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, size, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j = forStart$j; j < forEnd$j; j += 1)
						v[j] = DistributionSampling.sampleBernoulli(RNG$1, ((double)((v1 + v2[j]) + v2[j]) / v2[j]));
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample5) {
			for(int index$var4 = 0; index$var4 < weightings.length; index$var4 += 1)
				// Save the probability of each value
				// 
				// cv$distribution$sample5's comment
				// Create local copy of variable probabilities.
				distribution$sample5[index$var4] = ((((0 < weightings.length) && (0.0 <= weightings[index$var4])) && (weightings[index$var4] <= 1.0))?weightings[index$var4]:0.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample11) {
			for(int index$var10 = 0; index$var10 < weightings.length; index$var10 += 1)
				// Save the probability of each value
				// 
				// cv$distribution$sample11's comment
				// Create local copy of variable probabilities.
				distribution$sample11[index$var10] = ((((0 < weightings.length) && (0.0 <= weightings[index$var10])) && (weightings[index$var10] <= 1.0))?weightings[index$var10]:0.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample27)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1) {
							// Create local copy of variable probabilities.
							double[] cv$distribution$sample27 = distribution$sample27[i];
							for(int index$var26 = 0; index$var26 < weightings.length; index$var26 += 1)
								// Save the probability of each value
								// 
								// Probability for this value
								cv$distribution$sample27[index$var26] = ((((0 < weightings.length) && (0.0 <= weightings[index$var26])) && (weightings[index$var26] <= 1.0))?weightings[index$var26]:0.0);
						}
				}
			);

	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample11)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample27)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings, weightings.length);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, size, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j = forStart$j; j < forEnd$j; j += 1)
						v[j] = DistributionSampling.sampleBernoulli(RNG$1, ((double)((v1 + v2[j]) + v2[j]) / v2[j]));
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample11)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample27)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings, weightings.length);
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample11)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample27)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings, weightings.length);
				}
			);

	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample5)
				inferSample5();
			if(!fixedFlag$sample11)
				inferSample11();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample27)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, size, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int i = forStart$i; i < forEnd$i; i += 1)
								inferSample27(i, threadID$i, RNG$1);
					}
				);

		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample27)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, size, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int i = forStart$i; i < forEnd$i; i += 1)
								inferSample27(i, threadID$i, RNG$1);
					}
				);

			if(!fixedFlag$sample11)
				inferSample11();
			if(!fixedFlag$sample5)
				inferSample5();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample5)
			drawValueSample5();
		if(!constrainedFlag$sample11)
			drawValueSample11();
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, size, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!constrainedFlag$sample27[i])
							drawValueSample27(i, threadID$i, RNG$1);
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
		if(!fixedProbFlag$sample5)
			logProbability$v1 = Double.NaN;
		logProbability$v2 = 0.0;
		if(!fixedProbFlag$sample11)
			logProbability$var11 = Double.NaN;
		if(!fixedProbFlag$sample27) {
			for(int i = 0; i < size; i += 1)
				logProbability$sample27[i] = Double.NaN;
		}
		logProbability$v = 0.0;
		if(!fixedProbFlag$sample49) {
			for(int j = 0; j < size; j += 1)
				logProbability$sample49[j] = Double.NaN;
		}
	}

	// Method for initializing the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		size = length$value;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample27$1 = 0; index$constrainedFlag$sample27$1 < constrainedFlag$sample27.length; index$constrainedFlag$sample27$1 += 1)
			constrainedFlag$sample27[index$constrainedFlag$sample27$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		logProbabilityValue$sample49();
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
		logProbabilityDistribution$sample5();
		logProbabilityDistribution$sample11();
		logProbabilityDistribution$sample27();
		logProbabilityDistribution$sample49();
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
		logProbabilityValue$sample5();
		logProbabilityValue$sample11();
		logProbabilityValue$sample27();
		logProbabilityValue$sample49();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = v.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			v[cv$index1] = value[cv$index1];
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
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model DistributionTest6(double[] weightings, boolean[] value) {\n"
		     + "    int size = value.length;\n"
		     + "    \n"
		     + "    int v1 = categorical(weightings).sampleDistribution();\n"
		     + "    \n"
		     + "    int[] v2 = new int[size + 1];\n"
		     + "    v2[0] = categorical(weightings).sampleDistribution();\n"
		     + "    for(int i:[0..size))\n"
		     + "        v2[i + 1] = categorical(weightings).sampleDistribution();\n"
		     + "        \n"
		     + "    boolean[] v = new boolean[size];\n"
		     + "    for(int j:[0..size))\n"
		     + "        v[j] = bernoulli(((1.0*v1) + v2[j] + v2[j])/v2[j]).sample();\n"
		     + "        \n"
		     + "    v.observe(value);\n"
		     + "}\n"
		     + "";
	}
}