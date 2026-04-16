package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Conditional4$MultiThreadCPU extends CoreModelMultiThreadCPU implements Conditional4$CoreInterface {

	// Declare the variables for the model.
	double[] bias;
	boolean constrainedFlag$sample21 = true;
	boolean constrainedFlag$sample4 = true;
	boolean fixedFlag$sample21 = false;
	boolean fixedFlag$sample4 = false;
	boolean fixedProbFlag$sample21 = false;
	boolean fixedProbFlag$sample27 = false;
	boolean fixedProbFlag$sample4 = false;
	boolean guard;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$bernoulli;
	double logProbability$bias;
	double logProbability$guard;
	double logProbability$value;
	double logProbability$var19;
	double observedValue;
	boolean system$gibbsForward = true;
	double value;
	double var19;
	double[] cv$var4$stateProbabilityGlobal;

	public Conditional4$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for bias.
	@Override
	public final double[] get$bias() {
		return bias;
	}

	// Setter for bias.
	@Override
	public final void set$bias(double[] cv$value, boolean allocated$) {
		bias = cv$value;
	}

	// Getter for fixedFlag$sample21.
	@Override
	public final boolean get$fixedFlag$sample21() {
		return fixedFlag$sample21;
	}

	// Setter for fixedFlag$sample21.
	@Override
	public final void set$fixedFlag$sample21(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample21 including if probabilities
		// need to be updated.
		fixedFlag$sample21 = cv$value;
		
		// Substituted "fixedFlag$sample21" with its value "cv$value".
		constrainedFlag$sample21 = (cv$value || constrainedFlag$sample21);
		
		// Should the probability of sample 21 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample21" with its value "cv$value".
		fixedProbFlag$sample21 = (cv$value && fixedProbFlag$sample21);
		
		// Should the probability of sample 27 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample21" with its value "cv$value".
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
	}

	// Getter for fixedFlag$sample4.
	@Override
	public final boolean get$fixedFlag$sample4() {
		return fixedFlag$sample4;
	}

	// Setter for fixedFlag$sample4.
	@Override
	public final void set$fixedFlag$sample4(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample4 including if probabilities
		// need to be updated.
		fixedFlag$sample4 = cv$value;
		
		// Substituted "fixedFlag$sample4" with its value "cv$value".
		constrainedFlag$sample4 = (cv$value || constrainedFlag$sample4);
		
		// Should the probability of sample 4 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample4" with its value "cv$value".
		fixedProbFlag$sample4 = (cv$value && fixedProbFlag$sample4);
		
		// Should the probability of sample 21 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample4" with its value "cv$value".
		fixedProbFlag$sample21 = (cv$value && fixedProbFlag$sample21);
		
		// Should the probability of sample 27 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample4" with its value "cv$value".
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
	}

	// Getter for guard.
	@Override
	public final boolean get$guard() {
		return guard;
	}

	// Setter for guard.
	@Override
	public final void set$guard(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of guard including if probabilities need to
		// be updated.
		guard = cv$value;
		
		// Unset the fixed probability flag for sample 4 as it depends on guard.
		fixedProbFlag$sample4 = false;
		
		// Unset the fixed probability flag for sample 21 as it depends on guard.
		fixedProbFlag$sample21 = false;
		
		// Unset the fixed probability flag for sample 27 as it depends on guard.
		fixedProbFlag$sample27 = false;
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

	// Getter for logProbability$bernoulli.
	@Override
	public final double get$logProbability$bernoulli() {
		return logProbability$bernoulli;
	}

	// Getter for logProbability$bias.
	@Override
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	// Getter for logProbability$guard.
	@Override
	public final double get$logProbability$guard() {
		return logProbability$guard;
	}

	// Getter for logProbability$value.
	@Override
	public final double get$logProbability$value() {
		return logProbability$value;
	}

	// Getter for observedValue.
	@Override
	public final double get$observedValue() {
		return observedValue;
	}

	// Setter for observedValue.
	@Override
	public final void set$observedValue(double cv$value, boolean allocated$) {
		observedValue = cv$value;
	}

	// Getter for value.
	@Override
	public final double get$value() {
		return value;
	}

	// Getter for var19.
	@Override
	public final double get$var19() {
		return var19;
	}

	// Setter for var19.
	@Override
	public final void set$var19(double cv$value, boolean allocated$) {
		// Set flags for all the side effects of var19 including if probabilities need to
		// be updated.
		var19 = cv$value;
		
		// Unset the fixed probability flag for sample 21 as it depends on var19.
		fixedProbFlag$sample21 = false;
		
		// Unset the fixed probability flag for sample 27 as it depends on var19.
		fixedProbFlag$sample27 = false;
	}

	// Pick a value from the distribution for the unconditioned variable from sample21
	private final void drawValueSample21() {
		var19 = (DistributionSampling.sampleUniform(RNG$) * 0.5);
		
		// Guards to ensure that bias is only updated when there is a valid path.
		bias[0] = var19;
	}

	// Pick a value from the distribution for the unconditioned variable from sample4
	private final void drawValueSample4() {
		guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(guard)
			bias[0] = 0.5;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else
			bias[0] = var19;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 21 drawn from Uniform 18. Inference was performed using Metropolis-Hastings.
	private final void inferSample21() {
		constrainedFlag$sample21 = false;
		
		// The original value of the sample
		double cv$originalValue = var19;
		
		// Calculate a proposed variance.
		// 
						// The original value of the sample
		double cv$var = ((var19 * var19) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		// 
		// The original value of the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + var19);
		
		// Mark that the sample has observed constrained data.
		constrainedFlag$sample21 = true;
		
		// Variable declaration of cv$originalProbability moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 27 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
						// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
						// Set the current value to the current state of the tree.
		// 
		// The original value of the sample
		double cv$originalProbability = (DistributionSampling.logProbabilityBeta(value, var19, 1.0) + (((0.0 <= var19) && (var19 < 0.5))?0.6931471805599453:Double.NEGATIVE_INFINITY));
		
		// Update Sample and intermediate values
		// 
		// Write out the new value of the sample.
		var19 = cv$proposedValue;
		
		// Guards to ensure that bias is only updated when there is a valid path.
		bias[0] = cv$proposedValue;
		
		// Mark that the sample has observed constrained data.
		constrainedFlag$sample21 = true;
		
		// The probability ration for the proposed value and the current value.
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 27 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
						// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		double cv$ratio = ((DistributionSampling.logProbabilityBeta(value, cv$proposedValue, 1.0) + (((0.0 <= cv$proposedValue) && (cv$proposedValue < 0.5))?0.6931471805599453:Double.NEGATIVE_INFINITY)) - cv$originalProbability);
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio))) {
			// If it is not revert the changes.
			// 
			// Set the sample value
			// Write out the new value of the sample.
			var19 = cv$originalValue;
			
			// Guards to ensure that bias is only updated when there is a valid path.
			// 
			// Write out the new value of the sample.
			bias[0] = cv$originalValue;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 4 drawn from bernoulli. Inference was performed using variable marginalization.
	private final void inferSample4() {
		constrainedFlag$sample4 = false;
		
		// Write out the new value of the sample.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
						// Substituted "cv$valuePos" with its value "0".
		guard = false;
		bias[0] = var19;
		
		// Processing sample task 27 of consumer random variable null.
		// 
		// Mark that the sample has observed constrained data.
		constrainedFlag$sample4 = true;
		
		// Save the calculated index value into the array of index value probabilities
		// 
						// Get a local reference to the scratch space.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 21 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// Variable declaration of cv$accumulatedConsumerProbabilities moved.
		// Declaration comment was:
		// Processing sample task 21 of consumer random variable null.
		// 
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 27 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// Variable declaration of cv$accumulatedConsumerProbabilities moved.
		// Declaration comment was:
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		// 
		// Constructing a random variable input for use later.
		cv$var4$stateProbabilityGlobal[0] = (((((0.0 <= var19) && (var19 < 0.5))?0.6931471805599453:Double.NEGATIVE_INFINITY) + DistributionSampling.logProbabilityBeta(value, bias[0], 1.0)) - 0.6931471805599453);
		
		// Write out the new value of the sample.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		guard = true;
		
		// Looking for a path between Put 16 and consumer Beta 24.
		// 
		// Mark that the sample has observed constrained data.
		constrainedFlag$sample4 = true;
		
		// Save the calculated index value into the array of index value probabilities
		// 
						// Get a local reference to the scratch space.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		// 
		// Variable declaration of cv$accumulatedProbabilities moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 27 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// Variable declaration of cv$accumulatedConsumerProbabilities moved.
		// Declaration comment was:
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		cv$var4$stateProbabilityGlobal[1] = (DistributionSampling.logProbabilityBeta(value, 0.5, 1.0) - 0.6931471805599453);
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialize the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var4$stateProbabilityGlobal[0];
		
		// Unrolled loop
		// 
		// Get a local reference to the scratch space.
		double cv$lseElementValue = cv$var4$stateProbabilityGlobal[1];
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
			// Initialize the sum of the array elements
			cv$logSum = (Math.log((Math.exp((cv$var4$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var4$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Unrolled loop
									// Get a local reference to the scratch space.
			cv$var4$stateProbabilityGlobal[0] = 0.5;
			
									// Get a local reference to the scratch space.
			cv$var4$stateProbabilityGlobal[1] = 0.5;
		} else {
			// Unrolled loop
									// Get a local reference to the scratch space.
			cv$var4$stateProbabilityGlobal[0] = Math.exp((cv$var4$stateProbabilityGlobal[0] - cv$logSum));
			
									// Get a local reference to the scratch space.
			cv$var4$stateProbabilityGlobal[1] = Math.exp((cv$var4$stateProbabilityGlobal[1] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
						// Get a local reference to the scratch space.
		for(int cv$indexName = 2; cv$indexName < cv$var4$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var4$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the new value of the sample.
		// 
								// cv$numStates's comment
		// variable marginalization
		guard = (DistributionSampling.sampleCategorical(RNG$, cv$var4$stateProbabilityGlobal, 2) == 1);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(guard)
			bias[0] = 0.5;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else
			bias[0] = var19;
	}

	// Calculate the probability of the samples represented by sample21 using sampled
	// values.
	private final void logProbabilityValue$sample21() {
		// Determine if we need to calculate the values for sample task 21 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample21) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if(!guard) {
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
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
				cv$sampleAccumulator = (((0.0 <= var19) && (var19 < 0.5))?0.6931471805599453:Double.NEGATIVE_INFINITY);
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var19 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample21)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample21 = (fixedFlag$sample21 && fixedFlag$sample4);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$bias = (logProbability$bias + logProbability$var19);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var19);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample21)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var19);
		}
	}

	// Calculate the probability of the samples represented by sample27 using sampled
	// values.
	private final void logProbabilityValue$sample27() {
		// Determine if we need to calculate the values for sample task 27 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample27) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(value, bias[0], 1.0);
			
			// Store the sample task probability
			logProbability$value = cv$distributionAccumulator;
			
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
			fixedProbFlag$sample27 = (fixedFlag$sample4 && fixedFlag$sample21);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$value);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$value);
		}
	}

	// Calculate the probability of the samples represented by sample4 using sampled values.
	private final void logProbabilityValue$sample4() {
		// Determine if we need to calculate the values for sample task 4 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample4) {
			// Generating probabilities for sample task
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
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
			logProbability$bernoulli = -0.6931471805599453;
			
			// Store the sample task probability
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
			logProbability$guard = -0.6931471805599453;
			
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
			logProbability$$model = (logProbability$$model - 0.6931471805599453);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample4)
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
				logProbability$$evidence = (logProbability$$evidence - 0.6931471805599453);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample4 = fixedFlag$sample4;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$bernoulli = logProbability$guard;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$guard);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample4)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$guard);
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocate() {
		// Constructor for bias
		bias = new double[1];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// 
		// Constructor for cv$var4$stateProbabilityGlobal
		// 
		// Allocation of cv$var4$stateProbabilityGlobal for single threaded execution
		cv$var4$stateProbabilityGlobal = new double[2];
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(guard)
			bias[0] = 0.5;
		else {
			if(!fixedFlag$sample21)
				var19 = (DistributionSampling.sampleUniform(RNG$) * 0.5);
			if((!fixedFlag$sample4 || !fixedFlag$sample21))
				bias[0] = var19;
		}
		value = DistributionSampling.sampleBeta(RNG$, bias[0], 1.0);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(guard)
			bias[0] = 0.5;
		else {
			if(!fixedFlag$sample21)
				var19 = (DistributionSampling.sampleUniform(RNG$) * 0.5);
			bias[0] = var19;
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(guard)
			bias[0] = 0.5;
		else {
			if(!fixedFlag$sample21)
				var19 = (DistributionSampling.sampleUniform(RNG$) * 0.5);
			bias[0] = var19;
		}
		value = DistributionSampling.sampleBeta(RNG$, bias[0], 1.0);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(guard)
			bias[0] = 0.5;
		else {
			if(!fixedFlag$sample21)
				var19 = (DistributionSampling.sampleUniform(RNG$) * 0.5);
			if((!fixedFlag$sample4 || !fixedFlag$sample21))
				bias[0] = var19;
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(guard)
			bias[0] = 0.5;
		else {
			if(!fixedFlag$sample21)
				var19 = (DistributionSampling.sampleUniform(RNG$) * 0.5);
			bias[0] = var19;
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample4)
				inferSample4();
			if((!guard && !fixedFlag$sample21))
				inferSample21();
		}
		// Infer the samples in reverse chronological order.
		else {
			if((!guard && !fixedFlag$sample21))
				inferSample21();
			if(!fixedFlag$sample4)
				inferSample4();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample4)
			drawValueSample4();
		if((!guard && !constrainedFlag$sample21))
			drawValueSample21();
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
		logProbability$bernoulli = 0.0;
		if(!fixedProbFlag$sample4)
			logProbability$guard = Double.NaN;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample21)
			logProbability$var19 = Double.NaN;
		if(!fixedProbFlag$sample27)
			logProbability$value = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(fixedFlag$sample4)
			logProbabilityValue$sample4();
		if(fixedFlag$sample21)
			logProbabilityValue$sample21();
		logProbabilityValue$sample27();
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
		logProbabilityValue$sample4();
		logProbabilityValue$sample21();
		logProbabilityValue$sample27();
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
		logProbabilityValue$sample4();
		logProbabilityValue$sample21();
		logProbabilityValue$sample27();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		value = observedValue;
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		if(guard)
			bias[0] = 0.5;
		else
			bias[0] = var19;
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
		     + "public model Conditional4(double observedValue)  {\n"
		     + "        \n"
		     + "    //Construct a bernoulli\n"
		     + "    Bernoulli bernoulli = bernoulli(0.5);\n"
		     + "                \n"
		     + "    //Sample from it\n"
		     + "    boolean guard = bernoulli.sample();\n"
		     + "    \n"
		     + "    double[] bias = new double[1];\n"
		     + "        \n"
		     + "    if(guard)\n"
		     + "        bias[0] = 0.5;\n"
		     + "    else\n"
		     + "        bias[0] = uniform(0.0, 0.5).sample();\n"
		     + "    \n"
		     + "    double value = beta(bias[0],1.0).sample();\n"
		     + "    \n"
		     + "    //Link the sampled value to the observed value\n"
		     + "    value.observe(observedValue);\n"
		     + "}";
	}
}