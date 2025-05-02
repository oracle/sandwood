package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Deterministic$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Deterministic$CoreInterface {
	
	// Declare the variables for the model.
	private int[] a;
	private int[] b;
	private double[] cv$var29$countGlobal;
	private double[] cv$var54$stateProbabilityGlobal;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedFlag$sample55 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean fixedProbFlag$sample55 = false;
	private boolean fixedProbFlag$sample75 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$a;
	private double logProbability$b;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample55;
	private double[] logProbability$sample75;
	private double logProbability$var17;
	private double logProbability$var29;
	private double[] logProbability$var53;
	private double[] logProbability$var73;
	private double[][] m;
	private int n;
	private boolean system$gibbsForward = true;
	private double[] v;

	public Deterministic$SingleThreadCPU(ExecutionTarget target) {
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
		// Set flags for all the side effects of a including if probabilities need to be updated.
		// Set a
		a = cv$value;
		
		// Unset the fixed probability flag for sample 55 as it depends on a.
		fixedProbFlag$sample55 = false;
		
		// Unset the fixed probability flag for sample 75 as it depends on a.
		fixedProbFlag$sample75 = false;
	}

	// Getter for b.
	@Override
	public final int[] get$b() {
		return b;
	}

	// Getter for fixedFlag$sample29.
	@Override
	public final boolean get$fixedFlag$sample29() {
		return fixedFlag$sample29;
	}

	// Setter for fixedFlag$sample29.
	@Override
	public final void set$fixedFlag$sample29(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample29 including if probabilities
		// need to be updated.
		fixedFlag$sample29 = cv$value;
		
		// Should the probability of sample 29 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample29" with its value "cv$value".
		fixedProbFlag$sample29 = (cv$value && fixedProbFlag$sample29);
		
		// Should the probability of sample 55 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample29" with its value "cv$value".
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
	}

	// Getter for fixedFlag$sample55.
	@Override
	public final boolean get$fixedFlag$sample55() {
		return fixedFlag$sample55;
	}

	// Setter for fixedFlag$sample55.
	@Override
	public final void set$fixedFlag$sample55(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample55 including if probabilities
		// need to be updated.
		fixedFlag$sample55 = cv$value;
		
		// Should the probability of sample 55 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample55" with its value "cv$value".
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
		
		// Should the probability of sample 75 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample55" with its value "cv$value".
		fixedProbFlag$sample75 = (cv$value && fixedProbFlag$sample75);
	}

	// Getter for flips.
	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	// Getter for flipsMeasured.
	@Override
	public final boolean[] get$flipsMeasured() {
		return flipsMeasured;
	}

	// Setter for flipsMeasured.
	@Override
	public final void set$flipsMeasured(boolean[] cv$value) {
		// Set flipsMeasured
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
		// Set flags for all the side effects of m including if probabilities need to be updated.
		// Set m
		m = cv$value;
		
		// Unset the fixed probability flag for sample 29 as it depends on m.
		fixedProbFlag$sample29 = false;
		
		// Unset the fixed probability flag for sample 55 as it depends on m.
		fixedProbFlag$sample55 = false;
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

	// Calculate the probability of the samples represented by sample29 using sampled
	// values.
	private final void logProbabilityValue$sample29() {
		// Determine if we need to calculate the values for sample task 29 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample29) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var28 = 0; var28 < 5; var28 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var28], v, 5));
			logProbability$var17 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var29 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample29)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample29 = fixedFlag$sample29;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var17 = logProbability$var29;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var29);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var29);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample29)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var29);
		}
	}

	// Calculate the probability of the samples represented by sample55 using sampled
	// values.
	private final void logProbabilityValue$sample55() {
		// Determine if we need to calculate the values for sample task 55 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample55) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = a[i$var46];
				
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
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 5))?Math.log(m[b[i$var46]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
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
				logProbability$var53[(i$var46 - 1)] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample55[(i$var46 - 1)] = cv$distributionAccumulator;
				
				// Guard to ensure that b is only updated once for this probability.
				if((i$var46 < (n - 1)))
					// Update the variable probability
					logProbability$b = (logProbability$b + cv$distributionAccumulator);
			}
			
			// Update the variable probability
			logProbability$a = (logProbability$a + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample55 = (fixedFlag$sample55 && fixedFlag$sample29);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				double cv$sampleValue = logProbability$sample55[(i$var46 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var53[(i$var46 - 1)] = cv$sampleValue;
				
				// Guard to ensure that b is only updated once for this probability.
				if((i$var46 < (n - 1)))
					// Update the variable probability
					logProbability$b = (logProbability$b + cv$sampleValue);
			}
			
			// Update the variable probability
			logProbability$a = (logProbability$a + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample75 using sampled
	// values.
	private final void logProbabilityValue$sample75() {
		// Determine if we need to calculate the values for sample task 75 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample75) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double var72 = (double)(1 / a[(j + 1)]);
				
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
				double cv$distributionAccumulator = Math.log((flips[j]?var72:(1.0 - var72)));
				
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
				logProbability$var73[j] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample75[j] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample75 = fixedFlag$sample55;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample75[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var73[j] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 29 drawn from Dirichlet 17. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample29(int var28) {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var29$countGlobal[cv$loopIndex] = 0.0;
		
		// Processing random variable 53.
		// 
		// Looking for a path between Sample 29 and consumer Categorical 53.
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			if((var28 == b[i$var46]))
				// Processing sample task 55 of consumer random variable null.
				// 
				// Increment the sample counter with the value sampled by sample task 55 of random
				// variable var53
				// 
				// A local reference to the scratch space.
				cv$var29$countGlobal[a[i$var46]] = (cv$var29$countGlobal[a[i$var46]] + 1.0);
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var29$countGlobal, m[var28], 5);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 55 drawn from Categorical 53. Inference was performed using variable
	// marginalization.
	private final void sample55(int i$var46) {
		// cv$numNumStates's comment
		// variable marginalization
		for(int cv$valuePos = 0; cv$valuePos < 5; cv$valuePos += 1) {
			// Guards to ensure that a is only updated when there is a valid path.
			// 
			// Value of the variable at this index
			a[i$var46] = cv$valuePos;
			
			// Guards to ensure that b is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 55 and consumer int[] 50.
			int index$i$2_1 = (i$var46 + 1);
			if((index$i$2_1 < n))
				b[index$i$2_1] = a[(index$i$2_1 - 1)];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			// 
			// cv$temp$0$var52's comment
			// Constructing a random variable input for use later.
			double cv$accumulatedProbabilities = Math.log(m[b[i$var46]][cv$valuePos]);
			int index$i$3_2 = (i$var46 + 1);
			if((index$i$3_2 < n))
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 55 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 55 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "index$i$3_4" with its value "index$i$3_2".
				// 
				// Substituted "cv$temp$2$var52" with its value "var52".
				// 
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 53.
				// 
				// Looking for a path between Sample 55 and consumer Categorical 53.
				// 
				// Value of the variable at this index
				cv$accumulatedProbabilities = ((((0.0 <= a[index$i$3_2]) && (a[index$i$3_2] < 5))?Math.log(m[cv$valuePos][a[index$i$3_2]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			
			// Variable declaration of cv$temp$4$var72 moved.
			// 
			// Constructing a random variable input for use later.
			// 
			// Processing random variable 73.
			// 
			// Looking for a path between Sample 55 and consumer Bernoulli 73.
			// 
			// Value of the variable at this index
			double cv$temp$4$var72 = (double)(1 / cv$valuePos);
			
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
			// Substituted "j" with its value "(i$var46 - 1)".
			cv$accumulatedProbabilities = (Math.log((flips[(i$var46 - 1)]?cv$temp$4$var72:(1.0 - cv$temp$4$var72))) + cv$accumulatedProbabilities);
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var54$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
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
			// cv$numNumStates's comment
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
			// cv$numNumStates's comment
			// variable marginalization
			for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var54$stateProbabilityGlobal[cv$indexName] = 0.2;
		} else {
			// Normalize log space values and move to normal space
			// 
			// cv$numNumStates's comment
			// variable marginalization
			for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var54$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var54$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = 5; cv$indexName < cv$var54$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var54$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Guards to ensure that a is only updated when there is a valid path.
		// 
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// cv$numNumStates's comment
		// variable marginalization
		a[i$var46] = DistributionSampling.sampleCategorical(RNG$, cv$var54$stateProbabilityGlobal, 5);
		
		// Guards to ensure that b is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 55 and consumer int[] 50.
		int index$i$10_1 = (i$var46 + 1);
		if((index$i$10_1 < n))
			b[index$i$10_1] = a[(index$i$10_1 - 1)];
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var29$countGlobal
		// 
		// Allocation of cv$var29$countGlobal for single threaded execution
		cv$var29$countGlobal = new double[5];
		
		// Allocation of cv$var54$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 53. Initially set to the value
		// of putTask 30.
		cv$var54$stateProbabilityGlobal = new double[5];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for v
		v = new double[5];
		
		// If m has not been set already allocate space.
		if(!fixedFlag$sample29) {
			// Constructor for m
			m = new double[5][];
			for(int var28 = 0; var28 < 5; var28 += 1)
				m[var28] = new double[5];
		}
		
		// If a has not been set already allocate space.
		if(!fixedFlag$sample55)
			// Constructor for a
			a = new int[n];
		
		// Constructor for b
		b = new int[n];
		
		// Constructor for flips
		flips = new boolean[n];
		
		// Constructor for logProbability$var53
		logProbability$var53 = new double[(n - 1)];
		
		// Constructor for logProbability$sample55
		logProbability$sample55 = new double[(n - 1)];
		
		// Constructor for logProbability$var73
		logProbability$var73 = new double[n];
		
		// Constructor for logProbability$sample75
		logProbability$sample75 = new double[n];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample29) {
			for(int var28 = 0; var28 < 5; var28 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, 5, m[var28]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample55) {
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				b[i$var46] = a[(i$var46 - 1)];
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], 5);
			}
		}
		for(int j = 0; j < n; j += 1)
			flips[j] = DistributionSampling.sampleBernoulli(RNG$, (1 / a[(j + 1)]));
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample29) {
			for(int var28 = 0; var28 < 5; var28 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, 5, m[var28]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample55) {
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				b[i$var46] = a[(i$var46 - 1)];
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], 5);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample29) {
			for(int var28 = 0; var28 < 5; var28 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, 5, m[var28]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample55) {
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				b[i$var46] = a[(i$var46 - 1)];
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], 5);
			}
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample29) {
				for(int var28 = 0; var28 < 5; var28 += 1)
					sample29(var28);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample55) {
				for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
					sample55(i$var46);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample55) {
				for(int i$var46 = (n - 1); i$var46 >= 1; i$var46 -= 1)
					sample55(i$var46);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample29) {
				for(int var28 = 4; var28 >= 0; var28 -= 1)
					sample29(var28);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		for(int i$var14 = 0; i$var14 < 5; i$var14 += 1)
			v[i$var14] = 0.1;
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
		logProbability$var17 = Double.NaN;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample29)
			logProbability$var29 = Double.NaN;
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
			logProbability$var53[(i$var46 - 1)] = Double.NaN;
		logProbability$b = 0.0;
		logProbability$a = 0.0;
		if(!fixedProbFlag$sample55) {
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
				logProbability$sample55[(i$var46 - 1)] = Double.NaN;
		}
		for(int j = 0; j < n; j += 1)
			logProbability$var73[j] = Double.NaN;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample75) {
			for(int j = 0; j < n; j += 1)
				logProbability$sample75[j] = Double.NaN;
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
		if(fixedFlag$sample29)
			logProbabilityValue$sample29();
		if(fixedFlag$sample55)
			logProbabilityValue$sample55();
		logProbabilityValue$sample75();
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
		logProbabilityValue$sample29();
		logProbabilityValue$sample55();
		logProbabilityValue$sample75();
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
		logProbabilityValue$sample29();
		logProbabilityValue$sample55();
		logProbabilityValue$sample75();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample29) {
			for(int var28 = 0; var28 < 5; var28 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, 5, m[var28]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample55) {
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				b[i$var46] = a[(i$var46 - 1)];
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], 5);
			}
		}
		
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
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			flips[cv$index1] = flipsMeasured[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample55) {
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
				b[i$var46] = a[(i$var46 - 1)];
		}
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
		     + "/**\n"
		     + " * A model for the fairness work.\n"
		     + " */\n"
		     + "public model Deterministic(int n, boolean[] flipsMeasured) {\n"
		     + "    int states = 5;\n"
		     + "\n"
		     + "    double[] v = new double[states];\n"
		     + "    for(int i:[0..states))\n"
		     + "        v[i] = 0.1;\n"
		     + "    \n"
		     + "    double[][] m = dirichlet(v).sample(states);\n"
		     + "\n"
		     + "    int[] a = new int[n];\n"
		     + "    int[] b = new int[n];\n"
		     + "    a[0] = 0;\n"
		     + "    for(int i=1; i<n; i++) {\n"
		     + "        b[i] = a[i-1];\n"
		     + "        a[i] = categorical(m[b[i]]).sample();\n"
		     + "    }\n"
		     + "    \n"
		     + "    boolean[] flips = new boolean[n];\n"
		     + "            \n"
		     + "    for(int j:[0..n))\n"
		     + "        flips[j] = bernoulli(1/a[j+1]).sample();\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}";
	}
}