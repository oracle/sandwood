package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements RaggedArray2$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] a;
	private double[][] b;
	private double[] c;
	private double[] cv$var77$stateProbabilityGlobal;
	private double[] cv$var80$stateProbabilityGlobal;
	private boolean fixedFlag$sample81 = false;
	private boolean fixedFlag$sample84 = false;
	private boolean fixedProbFlag$sample100 = false;
	private boolean fixedProbFlag$sample81 = false;
	private boolean fixedProbFlag$sample84 = false;
	private int i;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$i;
	private double logProbability$obs;
	private double logProbability$var76;
	private double logProbability$var79;
	private double logProbability$var83;
	private double logProbability$var96;
	private double logProbability$y;
	private boolean[] obs;
	private boolean[] obs_measured;
	private double p;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray2$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for a.
	@Override
	public final double[][] get$a() {
		return a;
	}

	// Getter for b.
	@Override
	public final double[][] get$b() {
		return b;
	}

	// Getter for c.
	@Override
	public final double[] get$c() {
		return c;
	}

	// Getter for fixedFlag$sample81.
	@Override
	public final boolean get$fixedFlag$sample81() {
		return fixedFlag$sample81;
	}

	// Setter for fixedFlag$sample81.
	@Override
	public final void set$fixedFlag$sample81(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample81 including if probabilities
		// need to be updated.
		fixedFlag$sample81 = cv$value;
		
		// Should the probability of sample 81 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample81" with its value "cv$value".
		fixedProbFlag$sample81 = (cv$value && fixedProbFlag$sample81);
		
		// Should the probability of sample 84 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample81" with its value "cv$value".
		fixedProbFlag$sample84 = (cv$value && fixedProbFlag$sample84);
		
		// Should the probability of sample 100 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample81" with its value "cv$value".
		fixedProbFlag$sample100 = (cv$value && fixedProbFlag$sample100);
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
		
		// Should the probability of sample 100 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample84" with its value "cv$value".
		fixedProbFlag$sample100 = (cv$value && fixedProbFlag$sample100);
	}

	// Getter for i.
	@Override
	public final int get$i() {
		return i;
	}

	// Setter for i.
	@Override
	public final void set$i(int cv$value) {
		// Set flags for all the side effects of i including if probabilities need to be updated.
		i = cv$value;
		
		// Unset the fixed probability flag for sample 84 as it depends on i.
		fixedProbFlag$sample84 = false;
		
		// Unset the fixed probability flag for sample 100 as it depends on i.
		fixedProbFlag$sample100 = false;
	}

	// Getter for length$obs_measured.
	@Override
	public final int get$length$obs_measured() {
		return length$obs_measured;
	}

	// Setter for length$obs_measured.
	@Override
	public final void set$length$obs_measured(int cv$value) {
		length$obs_measured = cv$value;
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

	// Getter for logProbability$i.
	@Override
	public final double get$logProbability$i() {
		return logProbability$i;
	}

	// Getter for logProbability$obs.
	@Override
	public final double get$logProbability$obs() {
		return logProbability$obs;
	}

	// Getter for logProbability$y.
	@Override
	public final double get$logProbability$y() {
		return logProbability$y;
	}

	// Getter for obs.
	@Override
	public final boolean[] get$obs() {
		return obs;
	}

	// Getter for obs_measured.
	@Override
	public final boolean[] get$obs_measured() {
		return obs_measured;
	}

	// Setter for obs_measured.
	@Override
	public final void set$obs_measured(boolean[] cv$value) {
		// Set obs_measured
		obs_measured = cv$value;
	}

	// Getter for p.
	@Override
	public final double get$p() {
		return p;
	}

	// Getter for y.
	@Override
	public final int get$y() {
		return y;
	}

	// Setter for y.
	@Override
	public final void set$y(int cv$value) {
		// Set flags for all the side effects of y including if probabilities need to be updated.
		y = cv$value;
		
		// Unset the fixed probability flag for sample 81 as it depends on y.
		fixedProbFlag$sample81 = false;
		
		// Unset the fixed probability flag for sample 84 as it depends on y.
		fixedProbFlag$sample84 = false;
		
		// Unset the fixed probability flag for sample 100 as it depends on y.
		fixedProbFlag$sample100 = false;
	}

	// Calculate the probability of the samples represented by sample100 using sampled
	// values.
	private final void logProbabilityValue$sample100() {
		// Determine if we need to calculate the values for sample task 100 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample100) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var95 = 0; var95 < length$obs_measured; var95 += 1) {
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + Math.log((obs[var95]?p:(1.0 - p))));
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(cv$sampleReached) {
				logProbability$var83 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				logProbability$var96 = cv$sampleAccumulator;
			}
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$obs = (logProbability$obs + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample100 = (fixedFlag$sample81 && fixedFlag$sample84);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if((0 < length$obs_measured))
				// Record that the sample was reached.
				cv$sampleReached = true;
			if(cv$sampleReached)
				logProbability$var83 = logProbability$var96;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$obs = (logProbability$obs + logProbability$var96);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var96);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var96);
		}
	}

	// Calculate the probability of the samples represented by sample81 using sampled
	// values.
	private final void logProbabilityValue$sample81() {
		// Determine if we need to calculate the values for sample task 81 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample81) {
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
			double cv$distributionAccumulator = (((0.0 <= y) && (y < 2))?Math.log(c[y]):Double.NEGATIVE_INFINITY);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var76 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$y = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample81)
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
			fixedProbFlag$sample81 = fixedFlag$sample81;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var76 = logProbability$y;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$y);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample81)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$y);
		}
	}

	// Calculate the probability of the samples represented by sample84 using sampled
	// values.
	private final void logProbabilityValue$sample84() {
		// Determine if we need to calculate the values for sample task 84 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample84) {
			// Generating probabilities for sample task
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$82_11 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$82_11 = 3;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$82_11 = 2;
			
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
			double cv$distributionAccumulator = (((0.0 <= i) && (i < lengthCV$a$82_11))?Math.log(a[y][i]):Double.NEGATIVE_INFINITY);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var79 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$i = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample84)
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
			fixedProbFlag$sample84 = (fixedFlag$sample84 && fixedFlag$sample81);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var79 = logProbability$i;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$i);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample84)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$i);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 81 drawn from Categorical 76. Inference was performed using variable
	// marginalization.
	private final void sample81() {
		{
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			y = 0;
			
			// Guards to ensure that p is only updated when there is a valid path.
			// 
			// Write out the new sample value.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			p = b[0][i];
			
			// Variable declaration of cv$accumulatedProbabilities moved.
			// Declaration comment was:
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$c" with its value "c".
			// 
			// Substituted "cv$valuePos" with its value "0".
			// 
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 84 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$c" with its value "c".
			// 
			// Substituted "cv$valuePos" with its value "0".
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Processing sample task 84 of consumer random variable null.
			// 
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// cv$temp$2$var78's comment
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = ((((0.0 <= i) && (i < 2))?Math.log(a[0][i]):Double.NEGATIVE_INFINITY) + Math.log(c[0]));
			
			// Processing sample task 100 of consumer random variable null.
			for(int var95 = 0; var95 < length$obs_measured; var95 += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 100 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$4$p" with its value "p".
				cv$accumulatedProbabilities = (Math.log((obs[var95]?p:(1.0 - p))) + cv$accumulatedProbabilities);
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var77$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		y = 1;
		
		// Guards to ensure that p is only updated when there is a valid path.
		// 
		// Write out the new sample value.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		p = b[1][i];
		
		// Variable declaration of cv$accumulatedProbabilities moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$c" with its value "c".
		// 
		// Substituted "cv$valuePos" with its value "1".
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 84 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$c" with its value "c".
		// 
		// Substituted "cv$valuePos" with its value "1".
		// 
		// Variable declaration of cv$accumulatedConsumerProbabilities moved.
		// Declaration comment was:
		// Processing sample task 84 of consumer random variable null.
		// 
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		// 
		// cv$temp$2$var78's comment
		// Constructing a random variable input for use later.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = ((((0.0 <= i) && (i < 3))?Math.log(a[1][i]):Double.NEGATIVE_INFINITY) + Math.log(c[1]));
		
		// Processing sample task 100 of consumer random variable null.
		for(int var95 = 0; var95 < length$obs_measured; var95 += 1)
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 100 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "cv$temp$4$p" with its value "p".
			cv$accumulatedProbabilities = (Math.log((obs[var95]?p:(1.0 - p))) + cv$accumulatedProbabilities);
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var77$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var77$stateProbabilityGlobal[0];
		
		// Unrolled loop
		// 
		// Get a local reference to the scratch space.
		double cv$lseElementValue = cv$var77$stateProbabilityGlobal[1];
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
			// Initialise the sum of the array elements
			cv$logSum = (Math.log((Math.exp((cv$var77$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var77$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Unrolled loop
			// Get a local reference to the scratch space.
			cv$var77$stateProbabilityGlobal[0] = 0.5;
			
			// Get a local reference to the scratch space.
			cv$var77$stateProbabilityGlobal[1] = 0.5;
		} else {
			// Unrolled loop
			// Get a local reference to the scratch space.
			cv$var77$stateProbabilityGlobal[0] = Math.exp((cv$var77$stateProbabilityGlobal[0] - cv$logSum));
			
			// Get a local reference to the scratch space.
			cv$var77$stateProbabilityGlobal[1] = Math.exp((cv$var77$stateProbabilityGlobal[1] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = 2; cv$indexName < cv$var77$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var77$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the new value of the sample.
		// 
		// cv$numNumStates's comment
		// variable marginalization
		y = DistributionSampling.sampleCategorical(RNG$, cv$var77$stateProbabilityGlobal, 2);
		
		// Guards to ensure that p is only updated when there is a valid path.
		// 
		// Write out the new sample value.
		p = b[y][i];
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 84 drawn from Categorical 79. Inference was performed using variable
	// marginalization.
	private final void sample84() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$82_9 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == y))
			lengthCV$a$82_9 = 3;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == y))
			lengthCV$a$82_9 = 2;
		
		// Variable declaration of cv$numNumStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
		// cv$numNumStates's comment
		// Calculate the number of states to evaluate.
		int cv$numNumStates = Math.max(0, lengthCV$a$82_9);
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			i = cv$valuePos;
			
			// Guards to ensure that p is only updated when there is a valid path.
			// 
			// Write out the new sample value.
			// 
			// Value of the variable at this index
			p = b[y][cv$valuePos];
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$82_10 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$82_10 = 3;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$82_10 = 2;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			// 
			// cv$temp$0$var78's comment
			// Constructing a random variable input for use later.
			double cv$accumulatedProbabilities = ((cv$valuePos < lengthCV$a$82_10)?Math.log(a[y][cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Processing sample task 100 of consumer random variable null.
			for(int var95 = 0; var95 < length$obs_measured; var95 += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 100 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$2$p" with its value "p".
				cv$accumulatedProbabilities = (Math.log((obs[var95]?p:(1.0 - p))) + cv$accumulatedProbabilities);
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var80$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
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
		double cv$lseMax = cv$var80$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var80$stateProbabilityGlobal[cv$lseIndex];
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
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var80$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var80$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var80$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var80$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var80$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var80$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the new value of the sample.
		// 
		// Get a local reference to the scratch space.
		i = DistributionSampling.sampleCategorical(RNG$, cv$var80$stateProbabilityGlobal, cv$numNumStates);
		
		// Guards to ensure that p is only updated when there is a valid path.
		// 
		// Write out the new sample value.
		p = b[y][i];
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var77$stateProbabilityGlobal
		// 
		// Allocation of cv$var77$stateProbabilityGlobal for single threaded execution
		cv$var77$stateProbabilityGlobal = new double[2];
		
		// Allocation of cv$var80$stateProbabilityGlobal for single threaded execution
		// 
		// Test if the input to putTask 34 is larger than the current values.
		cv$var80$stateProbabilityGlobal = new double[3];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for a
		a = new double[2][];
		a[0] = new double[2];
		a[1] = new double[3];
		
		// Constructor for b
		b = new double[2][];
		b[0] = new double[2];
		b[1] = new double[3];
		
		// Constructor for c
		c = new double[2];
		
		// Constructor for obs
		obs = new boolean[length$obs_measured];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample81)
			y = DistributionSampling.sampleCategorical(RNG$, c, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample84) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$82_12 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$82_12 = 3;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$82_12 = 2;
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$82_12);
		}
		if((!fixedFlag$sample81 || !fixedFlag$sample84))
			p = b[y][i];
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$obs_measured, 1,
			(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
						obs[var95] = DistributionSampling.sampleBernoulli(RNG$1, p);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample81)
			y = DistributionSampling.sampleCategorical(RNG$, c, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample84) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$82_14 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$82_14 = 3;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$82_14 = 2;
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$82_14);
		}
		if((!fixedFlag$sample81 || !fixedFlag$sample84))
			p = b[y][i];
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample81)
			y = DistributionSampling.sampleCategorical(RNG$, c, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample84) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$82_13 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$82_13 = 3;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$82_13 = 2;
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$82_13);
		}
		if((!fixedFlag$sample81 || !fixedFlag$sample84))
			p = b[y][i];
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample81)
				sample81();
			if(!fixedFlag$sample84)
				sample84();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!fixedFlag$sample84)
				sample84();
			if(!fixedFlag$sample81)
				sample81();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		double[] var5 = a[0];
		var5[0] = 0.4;
		var5[1] = 0.6;
		double[] var18 = a[1];
		var18[0] = 0.2;
		var18[1] = 0.3;
		var18[2] = 0.5;
		double[] var37 = b[0];
		var37[0] = 0.2;
		var37[1] = 0.8;
		double[] var50 = b[1];
		var50[0] = 0.4;
		var50[1] = 0.2;
		var50[2] = 0.6;
		c[0] = 0.35;
		c[1] = 0.65;
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
		logProbability$var76 = 0.0;
		if(!fixedProbFlag$sample81)
			logProbability$y = Double.NaN;
		logProbability$var79 = 0.0;
		if(!fixedProbFlag$sample84)
			logProbability$i = Double.NaN;
		logProbability$var83 = Double.NaN;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample100)
			logProbability$var96 = Double.NaN;
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
		if(fixedFlag$sample81)
			logProbabilityValue$sample81();
		if(fixedFlag$sample84)
			logProbabilityValue$sample84();
		logProbabilityValue$sample100();
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
		logProbabilityValue$sample81();
		logProbabilityValue$sample84();
		logProbabilityValue$sample100();
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
		logProbabilityValue$sample81();
		logProbabilityValue$sample84();
		logProbabilityValue$sample100();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample81)
			y = DistributionSampling.sampleCategorical(RNG$, c, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample84) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$82_15 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$82_15 = 3;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$82_15 = 2;
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$82_15);
		}
		if((!fixedFlag$sample81 || !fixedFlag$sample84))
			p = b[y][i];
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = obs.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			obs[cv$index1] = obs_measured[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		if((fixedFlag$sample81 && fixedFlag$sample84))
			p = b[y][i];
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
		     + " package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model RaggedArray2(boolean[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    double[][] b = {{0.2, 0.8}, {0.4, 0.2, 0.6}};\n"
		     + "    double[] c = { 0.35, 0.65 };\n"
		     + "    int y = categorical(c).sample();\n"
		     + "    int i = categorical(a[y]).sample();\n"
		     + "    double p = b[y][i];\n"
		     + "    boolean[] obs = bernoulli(p).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}