package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DistributionTest7$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements DistributionTest7$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private int cat;
	private boolean constrainedFlag$sample31 = true;
	private boolean constrainedFlag$sample45 = true;
	private double[] cv$var31$stateProbabilityGlobal;
	private double[] cv$var43$stateProbabilityGlobal;
	private double data;
	private double[] distribution$sample31;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample51 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$cat;
	private double logProbability$data;
	private double logProbability$result;
	private double logProbability$sample45;
	private double logProbability$var43;
	private double observedData;
	private double[] prob;
	private int result;
	private boolean system$gibbsForward = true;
	private int var43;

	public DistributionTest7$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for bias.
	@Override
	public final double[] get$bias() {
		return bias;
	}

	// Getter for cat.
	@Override
	public final int get$cat() {
		return cat;
	}

	// Setter for cat.
	@Override
	public final void set$cat(int cv$value, boolean allocated$) {
		// Set flags for all the side effects of cat including if probabilities need to be
		// updated.
		cat = cv$value;
		
		// Unset the fixed probability flag for sample 31 as it depends on cat.
		fixedProbFlag$sample31 = false;
		
		// Unset the fixed probability flag for sample 45 as it depends on cat.
		fixedProbFlag$sample45 = false;
		
		// Unset the fixed probability flag for sample 51 as it depends on cat.
		fixedProbFlag$sample51 = false;
	}

	// Getter for data.
	@Override
	public final double get$data() {
		return data;
	}

	// Getter for distribution$sample31.
	@Override
	public final double[] get$distribution$sample31() {
		return distribution$sample31;
	}

	// Setter for distribution$sample31.
	@Override
	public final void set$distribution$sample31(double[] cv$value, boolean allocated$) {
		distribution$sample31 = cv$value;
	}

	// Getter for fixedFlag$sample31.
	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	// Setter for fixedFlag$sample31.
	@Override
	public final void set$fixedFlag$sample31(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample31 including if probabilities
		// need to be updated.
		fixedFlag$sample31 = cv$value;
		
		// Substituted "fixedFlag$sample31" with its value "cv$value".
		constrainedFlag$sample31 = (cv$value || constrainedFlag$sample31);
		
		// Should the probability of sample 31 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample31" with its value "cv$value".
		fixedProbFlag$sample31 = (cv$value && fixedProbFlag$sample31);
		
		// Should the probability of sample 45 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample31" with its value "cv$value".
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
		
		// Should the probability of sample 51 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample31" with its value "cv$value".
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
	}

	// Getter for fixedFlag$sample45.
	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	// Setter for fixedFlag$sample45.
	@Override
	public final void set$fixedFlag$sample45(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample45 including if probabilities
		// need to be updated.
		fixedFlag$sample45 = cv$value;
		
		// Substituted "fixedFlag$sample45" with its value "cv$value".
		constrainedFlag$sample45 = (cv$value || constrainedFlag$sample45);
		
		// Should the probability of sample 45 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample45" with its value "cv$value".
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
		
		// Should the probability of sample 51 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample45" with its value "cv$value".
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
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

	// Getter for logProbability$cat.
	@Override
	public final double get$logProbability$cat() {
		return logProbability$cat;
	}

	// Getter for logProbability$data.
	@Override
	public final double get$logProbability$data() {
		return logProbability$data;
	}

	// Getter for logProbability$result.
	@Override
	public final double get$logProbability$result() {
		return logProbability$result;
	}

	// Getter for observedData.
	@Override
	public final double get$observedData() {
		return observedData;
	}

	// Setter for observedData.
	@Override
	public final void set$observedData(double cv$value, boolean allocated$) {
		observedData = cv$value;
	}

	// Getter for prob.
	@Override
	public final double[] get$prob() {
		return prob;
	}

	// Getter for result.
	@Override
	public final int get$result() {
		return result;
	}

	// Getter for var43.
	@Override
	public final int get$var43() {
		return var43;
	}

	// Setter for var43.
	@Override
	public final void set$var43(int cv$value, boolean allocated$) {
		// Set flags for all the side effects of var43 including if probabilities need to
		// be updated.
		var43 = cv$value;
		
		// Unset the fixed probability flag for sample 45 as it depends on var43.
		fixedProbFlag$sample45 = false;
		
		// Unset the fixed probability flag for sample 51 as it depends on var43.
		fixedProbFlag$sample51 = false;
	}

	// Pick a value from the distribution for the unconditioned variable from sample31
	private final void drawValueSample31() {
		cat = DistributionSampling.sampleCategorical(RNG$, prob, 3);
		if((cat == 1))
			result = 5;
		
		// Guards to ensure that result is only updated when there is a valid path.
		else
			result = var43;
	}

	// Pick a value from the distribution for the unconditioned variable from sample45
	private final void drawValueSample45() {
		var43 = DistributionSampling.sampleBinomial(RNG$, bias[cat], 10);
		result = var43;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 31 drawn from Categorical 30. Inference was performed using variable
	// marginalization.
	private final void inferSample31() {
		constrainedFlag$sample31 = false;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Substituted "cv$valuePos" with its value "0".
		if((fixedFlag$sample45 || constrainedFlag$sample45))
			// Mark that the sample has observed constrained data.
			constrainedFlag$sample31 = true;
		
		// Mark that the sample has observed constrained data.
		constrainedFlag$sample31 = true;
		
		// Processing sample task 51 of consumer random variable null.
		// 
		// Mark that the sample has observed constrained data.
		constrainedFlag$sample31 = true;
		
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
		// Recorded the probability of reaching sample task 51 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		cv$var31$stateProbabilityGlobal[0] = Double.NEGATIVE_INFINITY;
		
		// Mark that the sample has observed constrained data.
		constrainedFlag$sample31 = true;
		
		// Processing sample task 51 of consumer random variable null.
		// 
		// Mark that the sample has observed constrained data.
		constrainedFlag$sample31 = true;
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		cv$var31$stateProbabilityGlobal[1] = (((0.0 <= prob[1]) && (prob[1] <= 1.0))?Math.log(prob[1]):Double.NEGATIVE_INFINITY);
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "2".
		double cv$accumulatedProbabilities = (((0.0 <= prob[2]) && (prob[2] <= 1.0))?Math.log(prob[2]):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Substituted "cv$valuePos" with its value "2".
		if((fixedFlag$sample45 || constrainedFlag$sample45)) {
			// Mark that the sample has observed constrained data.
			constrainedFlag$sample31 = true;
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 45 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBinomial(var43, 0.5, 10) + cv$accumulatedProbabilities);
		}
		
		// Mark that the sample has observed constrained data.
		constrainedFlag$sample31 = true;
		
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 51 with the current configuration.
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
		// 
		// Constructing a random variable input for use later.
		// 
		// Enumerating the possible arguments for the variable Gaussian 48 which is consuming
		// the output of Sample task 31.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "2".
		cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((data - var43) / 1.4142135623730951)) + cv$accumulatedProbabilities) - 0.34657359027997264);
		
		// Processing sample task 51 of consumer random variable null.
		// 
		// Mark that the sample has observed constrained data.
		constrainedFlag$sample31 = true;
		
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 51 with the current configuration.
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
		// 
		// Constructing a random variable input for use later.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "2".
		cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((data - var43) / 1.4142135623730951)) + cv$accumulatedProbabilities) - 0.34657359027997264);
		
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 45 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// Variable declaration of cv$accumulatedConsumerProbabilities moved.
		// Declaration comment was:
		// Processing sample task 45 of consumer random variable null.
		// 
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		// 
		// Constructing a random variable input for use later.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "2".
		cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBinomial(var43, bias[2], 10) + cv$accumulatedProbabilities);
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var31$stateProbabilityGlobal[2] = cv$accumulatedProbabilities;
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialize the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var31$stateProbabilityGlobal[0];
		
		// Unrolled loop
		{
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var31$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		
		// Get a local reference to the scratch space.
		double cv$lseElementValue = cv$var31$stateProbabilityGlobal[2];
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
			// Get a local reference to the scratch space.
			// 
			// Initialize the sum of the array elements
			cv$logSum = (Math.log(((Math.exp((cv$var31$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var31$stateProbabilityGlobal[1] - cv$lseMax))) + Math.exp((cv$var31$stateProbabilityGlobal[2] - cv$lseMax)))) + cv$lseMax);
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Unrolled loop
			// Local copy of the probability array
			distribution$sample31[0] = 0.3333333333333333;
			
			// Local copy of the probability array
			distribution$sample31[1] = 0.3333333333333333;
			
			// Local copy of the probability array
			distribution$sample31[2] = 0.3333333333333333;
		} else {
			// Unrolled loop
			// Local copy of the probability array
			distribution$sample31[0] = Math.exp((cv$var31$stateProbabilityGlobal[0] - cv$logSum));
			
			// Local copy of the probability array
			distribution$sample31[1] = Math.exp((cv$var31$stateProbabilityGlobal[1] - cv$logSum));
			
			// Local copy of the probability array
			distribution$sample31[2] = Math.exp((cv$var31$stateProbabilityGlobal[2] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = 3; cv$indexName < cv$var31$stateProbabilityGlobal.length; cv$indexName += 1)
			// Local copy of the probability array
			distribution$sample31[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 45 drawn from Binomial 42. Inference was performed using variable
	// marginalization.
	private final void inferSample45() {
		constrainedFlag$sample45 = false;
		
		// Calculate the number of states to evaluate.
		int cv$numStates = 0;
		
		// Exploring all the possible state counts for random variable 42.
		// 
		// Enumerating the possible arguments for Binomial 42.
		// 
		// Enumerating the possible arguments for Binomial 42.
		// 
		// Enumerating the possible arguments for Binomial 42.
		if(fixedFlag$sample31) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == cat))
				// variable marginalization
				cv$numStates = 11;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((2 == cat))
				// variable marginalization
				cv$numStates = 11;
		} else
			// variable marginalization
			cv$numStates = 11;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 42 creating
			// sample task 45.
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			var43 = cv$valuePos;
			
			// Value of the variable at this index
			result = cv$valuePos;
			
			// Enumerating the possible arguments for Binomial 42.
			// 
			// Enumerating the possible arguments for Binomial 42.
			// 
			// Enumerating the possible arguments for Binomial 42.
			if(fixedFlag$sample31) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == cat)) {
					// Record the reached probability density.
					// 
					// Initialize a counter to track the reached distributions.
					cv$reachedDistributionSourceRV = 1.0;
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample45 = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 51 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((2 == cat)) {
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample45 = true;
					
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
					// Recorded the probability of reaching sample task 51 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					cv$stateProbabilityValue = ((DistributionSampling.logProbabilityGaussian(((data - cv$valuePos) / 1.4142135623730951)) + DistributionSampling.logProbabilityBinomial(cv$valuePos, 0.5, 10)) - 0.34657359027997264);
				}
			} else {
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample45 = true;
				
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "index$sample31$31" with its value "1".
				double cv$probabilitySample31Value32 = distribution$sample31[1];
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				// 
				// Value of the variable at this index
				cv$stateProbabilityValue = (Math.log(cv$probabilitySample31Value32) + DistributionSampling.logProbabilityBinomial(cv$valuePos, 0.3, 10));
				
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "index$sample31$38" with its value "2".
				double cv$probabilitySample31Value39 = distribution$sample31[2];
				
				// Record the reached probability density.
				// 
				// Record the reached probability density.
				// 
				// Record the reached probability density.
				// 
				// Initialize a counter to track the reached distributions.
				// 
				// cv$probabilitySample31Value25's comment
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "index$sample31$24" with its value "0".
				cv$reachedDistributionSourceRV = ((distribution$sample31[0] + cv$probabilitySample31Value32) + cv$probabilitySample31Value39);
				
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample45 = true;
				
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
				// Recorded the probability of reaching sample task 51 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				// 
				// Value of the variable at this index
				double cv$accumulatedProbabilities = (((DistributionSampling.logProbabilityGaussian(((data - cv$valuePos) / 1.4142135623730951)) + Math.log(cv$probabilitySample31Value39)) + DistributionSampling.logProbabilityBinomial(cv$valuePos, 0.5, 10)) - 0.34657359027997264);
				
				// Add the values for the source and any standard consumers for this configuration
				// of arguments to the source.
				if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
					cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
				else {
					// If the second value is -infinity.
					// 
					// cv$probabilitySample31Value32's comment
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample31$31" with its value "1".
					// 
					// cv$probabilitySample31Value32's comment
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample31$31" with its value "1".
					// 
					// cv$probabilitySample31Value32's comment
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample31$31" with its value "1".
					if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					else
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			cv$var43$stateProbabilityGlobal[cv$valuePos] = (cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV));
		}
		if(constrainedFlag$sample45) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var43$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = cv$var43$stateProbabilityGlobal[cv$lseIndex];
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
					cv$lseSum = (cv$lseSum + Math.exp((cv$var43$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
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
					cv$var43$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Get a local reference to the scratch space.
					cv$var43$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var43$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var43$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var43$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
			// Get a local reference to the scratch space.
			var43 = DistributionSampling.sampleCategorical(RNG$, cv$var43$stateProbabilityGlobal, cv$numStates);
			result = var43;
		}
	}

	// Calculate the probability of the samples represented by sample31 using probability
	// distributions.
	private final void logProbabilityDistribution$sample31() {
		// Determine if we need to calculate the values for sample task 31 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample31) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample31) {
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
				double cv$distributionAccumulator = (((((0.0 <= cat) && (cat < 3)) && (0.0 <= prob[cat])) && (prob[cat] <= 1.0))?Math.log(prob[cat]):Double.NEGATIVE_INFINITY);
				
				// Store the sample task probability
				logProbability$cat = cv$distributionAccumulator;
				
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
				// Substituted "fixedFlag$sample31" with its value "true".
				fixedProbFlag$sample31 = true;
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$cat);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample31)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$cat);
		}
	}

	// Calculate the probability of the samples represented by sample45 using probability
	// distributions.
	private final void logProbabilityDistribution$sample45() {
		// Determine if we need to calculate the values for sample task 45 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample45) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			if(!(cat == 1)) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Enumerating the possible arguments for Binomial 42.
				// 
				// Enumerating the possible arguments for Binomial 42.
				// 
				// Enumerating the possible arguments for Binomial 42.
				if(fixedFlag$sample31) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == cat)) {
						// Store the value of the function call, so the function call is only made once.
						// 
						// The sample value to calculate the probability of generating
						cv$distributionAccumulator = DistributionSampling.logProbabilityBinomial(var43, 0.2, 10);
						
						// Add the probability of this distribution configuration to the accumulator.
						// 
						// An accumulator for the distributed probability space covered.
						cv$probabilityReached = 1.0;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((2 == cat)) {
						// Store the value of the function call, so the function call is only made once.
						// 
						// The sample value to calculate the probability of generating
						double cv$weightedProbability = DistributionSampling.logProbabilityBinomial(var43, 0.5, 10);
						
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
					// Substituted "index$sample31$3" with its value "0".
					double cv$probabilitySample31Value4 = distribution$sample31[0];
					
					// Store the value of the function call, so the function call is only made once.
					// 
					// The sample value to calculate the probability of generating
					cv$distributionAccumulator = (Math.log(cv$probabilitySample31Value4) + DistributionSampling.logProbabilityBinomial(var43, 0.2, 10));
					{
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample31$10" with its value "1".
						double cv$probabilitySample31Value11 = distribution$sample31[1];
						
						// Store the value of the function call, so the function call is only made once.
						// 
						// The sample value to calculate the probability of generating
						double cv$weightedProbability = (Math.log(cv$probabilitySample31Value11) + DistributionSampling.logProbabilityBinomial(var43, 0.3, 10));
						
						// Add the probability of this sample task to the distribution accumulator.
						if((cv$weightedProbability < cv$distributionAccumulator))
							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
						else {
							// If the second value is -infinity.
							// 
							// cv$probabilitySample31Value4's comment
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$sample31$3" with its value "0".
							// 
							// cv$probabilitySample31Value4's comment
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$sample31$3" with its value "0".
							// 
							// cv$probabilitySample31Value4's comment
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$sample31$3" with its value "0".
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
						cv$probabilityReached = (cv$probabilitySample31Value4 + cv$probabilitySample31Value11);
					}
					
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample31$17" with its value "2".
					double cv$probabilitySample31Value18 = distribution$sample31[2];
					
					// Store the value of the function call, so the function call is only made once.
					// 
					// The sample value to calculate the probability of generating
					double cv$weightedProbability = (Math.log(cv$probabilitySample31Value18) + DistributionSampling.logProbabilityBinomial(var43, 0.5, 10));
					
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
					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample31Value18);
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
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample45 = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$var43 = (logProbability$var43 + cv$accumulator);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((!(cat == 1) && fixedFlag$sample31) && fixedFlag$sample45))
				// Update the variable probability
				logProbability$result = (logProbability$result + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedFlag$sample31);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			if(!(cat == 1))
				cv$accumulator = logProbability$sample45;
			
			// Update the variable probability
			logProbability$var43 = (logProbability$var43 + cv$accumulator);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((!(cat == 1) && fixedFlag$sample31) && fixedFlag$sample45))
				// Update the variable probability
				logProbability$result = (logProbability$result + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample51 using probability
	// distributions.
	private final void logProbabilityDistribution$sample51() {
		// Determine if we need to calculate the values for sample task 51 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample51) {
			// Generating probabilities for sample task
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			
			// Enumerating the possible arguments for Gaussian 48.
			if(fixedFlag$sample31) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!(cat == 1)) {
					// Store the value of the function call, so the function call is only made once.
					// 
					// The sample value to calculate the probability of generating
					cv$distributionAccumulator = ((0.0 < (double)cat)?(DistributionSampling.logProbabilityGaussian(((data - var43) / Math.sqrt(cat))) - (Math.log(cat) * 0.5)):Double.NEGATIVE_INFINITY);
					
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					cv$probabilityReached = 1.0;
				}
			} else {
				// Unrolled loop
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "index$sample31$3" with its value "2".
				double cv$probabilitySample31Value4 = distribution$sample31[2];
				
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				// 
				// Substituted "index$sample31$3" with its value "2".
				cv$distributionAccumulator = ((Math.log(cv$probabilitySample31Value4) + DistributionSampling.logProbabilityGaussian(((data - var43) / 1.4142135623730951))) - 0.34657359027997264);
				
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// cv$probabilitySample31Value4's comment
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "index$sample31$3" with its value "0".
				cv$probabilityReached = (distribution$sample31[0] + cv$probabilitySample31Value4);
			}
			if((cv$probabilityReached == 0.0))
				// Return negative infinity if no distribution probability space is reached.
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				// Scale the probability relative to the observed distribution space.
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			
			// Store the sample task probability
			logProbability$data = cv$distributionAccumulator;
			
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
			fixedProbFlag$sample51 = (fixedFlag$sample31 && fixedFlag$sample45);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$data);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$data);
		}
	}

	// Calculate the probability of the samples represented by sample31 using sampled
	// values.
	private final void logProbabilityValue$sample31() {
		// Determine if we need to calculate the values for sample task 31 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample31) {
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
			double cv$distributionAccumulator = (((((0.0 <= cat) && (cat < 3)) && (0.0 <= prob[cat])) && (prob[cat] <= 1.0))?Math.log(prob[cat]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			logProbability$cat = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample31)
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
			fixedProbFlag$sample31 = fixedFlag$sample31;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$cat);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample31)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$cat);
		}
	}

	// Calculate the probability of the samples represented by sample45 using sampled
	// values.
	private final void logProbabilityValue$sample45() {
		// Determine if we need to calculate the values for sample task 45 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample45) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			if(!(cat == 1)) {
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
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBinomial(var43, bias[cat], 10);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample45 = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$var43 = (logProbability$var43 + cv$accumulator);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!(cat == 1))
				// Update the variable probability
				logProbability$result = (logProbability$result + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedFlag$sample31);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			if(!(cat == 1))
				cv$accumulator = logProbability$sample45;
			
			// Update the variable probability
			logProbability$var43 = (logProbability$var43 + cv$accumulator);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!(cat == 1))
				// Update the variable probability
				logProbability$result = (logProbability$result + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample51 using sampled
	// values.
	private final void logProbabilityValue$sample51() {
		// Determine if we need to calculate the values for sample task 51 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample51) {
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
			double cv$distributionAccumulator = ((0.0 < (double)cat)?(DistributionSampling.logProbabilityGaussian(((data - result) / Math.sqrt(cat))) - (Math.log(cat) * 0.5)):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			logProbability$data = cv$distributionAccumulator;
			
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
			fixedProbFlag$sample51 = (fixedFlag$sample31 && fixedFlag$sample45);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$data);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$data);
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var31$stateProbabilityGlobal
		// 
		// Allocation of cv$var31$stateProbabilityGlobal for single threaded execution
		cv$var31$stateProbabilityGlobal = new double[3];
		
		// Constructor for cv$var43$stateProbabilityGlobal
		// 
		// Allocation of cv$var43$stateProbabilityGlobal for single threaded execution
		cv$var43$stateProbabilityGlobal = new double[11];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for bias
		bias = new double[3];
		
		// Constructor for prob
		prob = new double[3];
		
		// Constructor for distribution$sample31
		distribution$sample31 = new double[3];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample31)
			cat = DistributionSampling.sampleCategorical(RNG$, prob, 3);
		if((cat == 1)) {
			if(!fixedFlag$sample31)
				result = 5;
		} else {
			if(!fixedFlag$sample45)
				var43 = DistributionSampling.sampleBinomial(RNG$, bias[cat], 10);
			if((!fixedFlag$sample31 || !fixedFlag$sample45))
				result = var43;
		}
		data = ((Math.sqrt(cat) * DistributionSampling.sampleGaussian(RNG$)) + result);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample31) {
			// Save the probability of each value
			// 
			// cv$distribution$sample31's comment
			// Create local copy of variable probabilities.
			// 
			// Probability for this value
			distribution$sample31[0] = (((0.0 <= prob[0]) && (prob[0] <= 1.0))?prob[0]:0.0);
			
			// Save the probability of each value
			// 
			// cv$distribution$sample31's comment
			// Create local copy of variable probabilities.
			// 
			// Probability for this value
			distribution$sample31[1] = (((0.0 <= prob[1]) && (prob[1] <= 1.0))?prob[1]:0.0);
			
			// Save the probability of each value
			// 
			// cv$distribution$sample31's comment
			// Create local copy of variable probabilities.
			// 
			// Probability for this value
			distribution$sample31[2] = (((0.0 <= prob[2]) && (prob[2] <= 1.0))?prob[2]:0.0);
		}
		if((!(cat == 1) && !fixedFlag$sample45))
			var43 = DistributionSampling.sampleBinomial(RNG$, bias[cat], 10);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample31)
			cat = DistributionSampling.sampleCategorical(RNG$, prob, 3);
		if((cat == 1))
			result = 5;
		else {
			if(!fixedFlag$sample45)
				var43 = DistributionSampling.sampleBinomial(RNG$, bias[cat], 10);
			if((!fixedFlag$sample31 || !fixedFlag$sample45))
				result = var43;
		}
		data = ((Math.sqrt(cat) * DistributionSampling.sampleGaussian(RNG$)) + result);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample31)
			cat = DistributionSampling.sampleCategorical(RNG$, prob, 3);
		if((cat == 1)) {
			if(!fixedFlag$sample31)
				result = 5;
		} else {
			if(!fixedFlag$sample45)
				var43 = DistributionSampling.sampleBinomial(RNG$, bias[cat], 10);
			if((!fixedFlag$sample31 || !fixedFlag$sample45))
				result = var43;
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample31)
			cat = DistributionSampling.sampleCategorical(RNG$, prob, 3);
		if((cat == 1))
			result = 5;
		else {
			if(!fixedFlag$sample45)
				var43 = DistributionSampling.sampleBinomial(RNG$, bias[cat], 10);
			if((!fixedFlag$sample31 || !fixedFlag$sample45))
				result = var43;
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample31)
				inferSample31();
			if((!(cat == 1) && !fixedFlag$sample45))
				inferSample45();
		}
		// Infer the samples in reverse chronological order.
		else {
			if((!(cat == 1) && !fixedFlag$sample45))
				inferSample45();
			if(!fixedFlag$sample31)
				inferSample31();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample31)
			drawValueSample31();
		if((!(cat == 1) && !constrainedFlag$sample45))
			drawValueSample45();
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
		if(!fixedProbFlag$sample31)
			logProbability$cat = Double.NaN;
		logProbability$var43 = 0.0;
		logProbability$result = 0.0;
		if(!fixedProbFlag$sample45)
			logProbability$sample45 = Double.NaN;
		if(!fixedProbFlag$sample51)
			logProbability$data = Double.NaN;
	}

	// Method for initializing the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		bias[0] = 0.2;
		bias[1] = 0.3;
		bias[2] = 0.5;
		prob[0] = 0.2;
		prob[1] = 0.4;
		prob[2] = 0.4;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		logProbabilityValue$sample51();
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
		logProbabilityDistribution$sample31();
		logProbabilityDistribution$sample45();
		logProbabilityDistribution$sample51();
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
		logProbabilityValue$sample31();
		logProbabilityValue$sample45();
		logProbabilityValue$sample51();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		data = observedData;
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		if(!(cat == 1)) {
			if((fixedFlag$sample31 && fixedFlag$sample45))
				result = var43;
		} else
			result = 5;
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model DistributionTest7(double observedData ) {\n"
		     + "\n"
		     + "    double[] bias = {0.2, 0.3, 0.5};\n"
		     + "    double[] prob = {0.2, 0.4, 0.4};\n"
		     + "\n"
		     + "    int cat = categorical(prob).sampleDistribution();\n"
		     + "    int result;\n"
		     + "    if(cat != 1) {\n"
		     + "        result = binomial(bias[cat], 10).sample();\n"
		     + "    } else {\n"
		     + "        result = 5;\n"
		     + "    }\n"
		     + "    \n"
		     + "\n"
		     + "    double data = gaussian(result, (double) cat).sample();\n"
		     + "\n"
		     + "    data.observe(observedData);\n"
		     + "\n"
		     + "    }";
	}
}