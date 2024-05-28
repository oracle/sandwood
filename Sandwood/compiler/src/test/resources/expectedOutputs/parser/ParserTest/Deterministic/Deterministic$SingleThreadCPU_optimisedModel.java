package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Deterministic$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Deterministic$CoreInterface {
	
	// Declare the variables for the model.
	private int[] a;
	private int[] b;
	private double[] cv$var31$countGlobal;
	private double[] cv$var56$stateProbabilityGlobal;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedFlag$sample58 = false;
	private boolean fixedFlag$sample78 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean fixedProbFlag$sample58 = false;
	private boolean fixedProbFlag$sample78 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$a;
	private double logProbability$b;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample58;
	private double[] logProbability$sample78;
	private double logProbability$var19;
	private double logProbability$var31;
	private double[] logProbability$var55;
	private double[] logProbability$var75;
	private double[][] m;
	private int n;
	private boolean setFlag$a = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
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
		// Set a with flag to mark that it has been set so another array doesn't need to be
		// constructed
		a = cv$value;
		setFlag$a = true;
		
		// Unset the fixed probability flag for sample 58 as it depends on a.
		fixedProbFlag$sample58 = false;
		
		// Unset the fixed probability flag for sample 78 as it depends on a.
		fixedProbFlag$sample78 = false;
	}

	// Getter for b.
	@Override
	public final int[] get$b() {
		return b;
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
		
		// Should the probability of sample 58 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample32" with its value "cv$value".
		fixedProbFlag$sample58 = (cv$value && fixedProbFlag$sample58);
	}

	// Getter for fixedFlag$sample58.
	@Override
	public final boolean get$fixedFlag$sample58() {
		return fixedFlag$sample58;
	}

	// Setter for fixedFlag$sample58.
	@Override
	public final void set$fixedFlag$sample58(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample58 including if probabilities
		// need to be updated.
		fixedFlag$sample58 = cv$value;
		
		// Should the probability of sample 58 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample58" with its value "cv$value".
		fixedProbFlag$sample58 = (cv$value && fixedProbFlag$sample58);
		
		// Should the probability of sample 78 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample58" with its value "cv$value".
		fixedProbFlag$sample78 = (cv$value && fixedProbFlag$sample78);
	}

	// Getter for fixedFlag$sample78.
	@Override
	public final boolean get$fixedFlag$sample78() {
		return fixedFlag$sample78;
	}

	// Setter for fixedFlag$sample78.
	@Override
	public final void set$fixedFlag$sample78(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample78 including if probabilities
		// need to be updated.
		fixedFlag$sample78 = cv$value;
		
		// Should the probability of sample 78 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample78" with its value "cv$value".
		fixedProbFlag$sample78 = (cv$value && fixedProbFlag$sample78);
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
		
		// Unset the fixed probability flag for sample 78 as it depends on flips.
		fixedProbFlag$sample78 = false;
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
		// Set m with flag to mark that it has been set so another array doesn't need to be
		// constructed
		m = cv$value;
		setFlag$m = true;
		
		// Unset the fixed probability flag for sample 32 as it depends on m.
		fixedProbFlag$sample32 = false;
		
		// Unset the fixed probability flag for sample 58 as it depends on m.
		fixedProbFlag$sample58 = false;
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

	// Calculate the probability of the samples represented by sample32 using sampled
	// values.
	private final void logProbabilityValue$sample32() {
		// Determine if we need to calculate the values for sample task 32 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample32) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var30 = 0; var30 < 5; var30 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var30], v));
			logProbability$var19 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var31 = cv$sampleAccumulator;
			
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
			logProbability$var19 = logProbability$var31;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var31);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var31);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample32)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var31);
		}
	}

	// Calculate the probability of the samples represented by sample58 using sampled
	// values.
	private final void logProbabilityValue$sample58() {
		// Determine if we need to calculate the values for sample task 58 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample58) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = a[i$var48];
				double[] var54 = m[b[i$var48]];
				
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
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var54.length))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
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
				logProbability$var55[(i$var48 - 1)] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample58[(i$var48 - 1)] = cv$distributionAccumulator;
				
				// Guard to ensure that b is only updated once for this probability.
				if((i$var48 < (n - 1)))
					// Update the variable probability
					logProbability$b = (logProbability$b + cv$distributionAccumulator);
			}
			
			// Update the variable probability
			logProbability$a = (logProbability$a + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample58 = (fixedFlag$sample58 && fixedFlag$sample32);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				double cv$sampleValue = logProbability$sample58[(i$var48 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var55[(i$var48 - 1)] = cv$sampleValue;
				
				// Guard to ensure that b is only updated once for this probability.
				if((i$var48 < (n - 1)))
					// Update the variable probability
					logProbability$b = (logProbability$b + cv$sampleValue);
			}
			
			// Update the variable probability
			logProbability$a = (logProbability$a + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample78 using sampled
	// values.
	private final void logProbabilityValue$sample78() {
		// Determine if we need to calculate the values for sample task 78 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample78) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
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
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[j], (1 / a[(j + 1)]));
				
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
				logProbability$var75[j] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample78[j] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample78 = (fixedFlag$sample78 && fixedFlag$sample58);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample78[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var75[j] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 32 drawn from Dirichlet 19. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample32(int var30) {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var31$countGlobal[cv$loopIndex] = 0.0;
		
		// Processing random variable 55.
		// 
		// Looking for a path between Sample 32 and consumer Categorical 55.
		for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
			if((var30 == b[i$var48]))
				// Processing sample task 58 of consumer random variable null.
				// 
				// Increment the sample counter with the value sampled by sample task 58 of random
				// variable var55
				// 
				// A local reference to the scratch space.
				cv$var31$countGlobal[a[i$var48]] = (cv$var31$countGlobal[a[i$var48]] + 1.0);
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var31$countGlobal, m[var30]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 58 drawn from Categorical 55. Inference was performed using variable
	// marginalization.
	private final void sample58(int i$var48) {
		// cv$noStates's comment
		// variable marginalization
		for(int cv$valuePos = 0; cv$valuePos < 5; cv$valuePos += 1) {
			// Value of the variable at this index
			a[i$var48] = cv$valuePos;
			
			// Guards to ensure that b is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 58 and consumer int[] 52.
			int index$i$1_1 = (i$var48 + 1);
			if((index$i$1_1 < n))
				b[index$i$1_1] = a[(index$i$1_1 - 1)];
			
			// Variable declaration of cv$temp$0$var54 moved.
			// 
			// Constructing a random variable input for use later.
			double[] cv$temp$0$var54 = m[b[i$var48]];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var54.length)?Math.log(cv$temp$0$var54[cv$valuePos]):Double.NEGATIVE_INFINITY);
			int index$i$2_2 = (i$var48 + 1);
			if((index$i$2_2 < n)) {
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 55.
				// 
				// Looking for a path between Sample 58 and consumer Categorical 55.
				// 
				// Value of the variable at this index
				double[] var54 = m[cv$valuePos];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 58 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 58 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "index$i$2_4" with its value "index$i$2_2".
				// 
				// Substituted "cv$temp$1$var54" with its value "var54".
				cv$accumulatedProbabilities = ((((0.0 <= a[index$i$2_2]) && (a[index$i$2_2] < var54.length))?Math.log(var54[a[index$i$2_2]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 78 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Processing sample task 78 of consumer random variable null.
			// 
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "j" with its value "(i$var48 - 1)".
			// 
			// cv$temp$2$var74's comment
			// Variable declaration of cv$temp$2$var74 moved.
			// 
			// Constructing a random variable input for use later.
			// 
			// Processing random variable 75.
			// 
			// Looking for a path between Sample 58 and consumer Bernoulli 75.
			// 
			// Value of the variable at this index
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[(i$var48 - 1)], (1 / cv$valuePos)) + cv$accumulatedProbabilities);
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var56$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
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
		double cv$lseMax = cv$var56$stateProbabilityGlobal[0];
		
		// Unrolled loop
		{
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var56$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		{
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var56$stateProbabilityGlobal[2];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		{
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var56$stateProbabilityGlobal[3];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		
		// Get a local reference to the scratch space.
		double cv$lseElementValue = cv$var56$stateProbabilityGlobal[4];
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
				cv$lseSum = (cv$lseSum + Math.exp((cv$var56$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
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
				// Get a local reference to the scratch space.
				cv$var56$stateProbabilityGlobal[cv$indexName] = 0.2;
		} else {
			// Normalize log space values and move to normal space
			// 
			// cv$noStates's comment
			// variable marginalization
			for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var56$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var56$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = 5; cv$indexName < cv$var56$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var56$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Get a local reference to the scratch space.
		a[i$var48] = DistributionSampling.sampleCategorical(RNG$, cv$var56$stateProbabilityGlobal);
		
		// Guards to ensure that b is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 58 and consumer int[] 52.
		int index$i$8_1 = (i$var48 + 1);
		if((index$i$8_1 < n))
			b[index$i$8_1] = a[(index$i$8_1 - 1)];
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Allocation of cv$var31$countGlobal for single threaded execution
		cv$var31$countGlobal = new double[5];
		
		// Allocation of cv$var56$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 56. Initially set to the value
		// of putTask 33.
		cv$var56$stateProbabilityGlobal = new double[5];
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
			for(int var30 = 0; var30 < 5; var30 += 1)
				m[var30] = new double[5];
		}
		
		// If a has not been set already allocate space.
		if(!setFlag$a)
			// Constructor for a
			a = new int[n];
		
		// Constructor for b
		b = new int[n];
		
		// If flips has not been set already allocate space.
		if(!setFlag$flips)
			// Constructor for flips
			flips = new boolean[n];
		
		// Constructor for logProbability$var55
		logProbability$var55 = new double[(n - 1)];
		
		// Constructor for logProbability$sample58
		logProbability$sample58 = new double[(n - 1)];
		
		// Constructor for logProbability$var75
		logProbability$var75 = new double[n];
		
		// Constructor for logProbability$sample78
		logProbability$sample78 = new double[n];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample32) {
			for(int var30 = 0; var30 < 5; var30 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var30]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample58) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				b[i$var48] = a[(i$var48 - 1)];
				a[i$var48] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var48]]);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample78) {
			for(int j = 0; j < n; j += 1)
				flips[j] = DistributionSampling.sampleBernoulli(RNG$, (1 / a[(j + 1)]));
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample32) {
			for(int var30 = 0; var30 < 5; var30 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var30]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample58) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				b[i$var48] = a[(i$var48 - 1)];
				a[i$var48] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var48]]);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample32) {
			for(int var30 = 0; var30 < 5; var30 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var30]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample58) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				b[i$var48] = a[(i$var48 - 1)];
				a[i$var48] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var48]]);
			}
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample32) {
				for(int var30 = 0; var30 < 5; var30 += 1)
					sample32(var30);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample58) {
				for(int i$var48 = 1; i$var48 < n; i$var48 += 1)
					sample58(i$var48);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample58) {
				for(int i$var48 = (n - 1); i$var48 >= 1; i$var48 -= 1)
					sample58(i$var48);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample32) {
				for(int var30 = 4; var30 >= 0; var30 -= 1)
					sample32(var30);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		for(int i$var16 = 0; i$var16 < 5; i$var16 += 1)
			v[i$var16] = 0.1;
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
		logProbability$var19 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample32)
			logProbability$var31 = 0.0;
		for(int i$var48 = 1; i$var48 < n; i$var48 += 1)
			logProbability$var55[(i$var48 - 1)] = 0.0;
		logProbability$b = 0.0;
		logProbability$a = 0.0;
		if(!fixedProbFlag$sample58) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1)
				logProbability$sample58[(i$var48 - 1)] = 0.0;
		}
		for(int j = 0; j < n; j += 1)
			logProbability$var75[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample78) {
			for(int j = 0; j < n; j += 1)
				logProbability$sample78[j] = 0.0;
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
		if(fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(fixedFlag$sample58)
			logProbabilityValue$sample58();
		logProbabilityValue$sample78();
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
		logProbabilityValue$sample32();
		logProbabilityValue$sample58();
		logProbabilityValue$sample78();
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
		logProbabilityValue$sample32();
		logProbabilityValue$sample58();
		logProbabilityValue$sample78();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample32) {
			for(int var30 = 0; var30 < 5; var30 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var30]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample58) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				b[i$var48] = a[(i$var48 - 1)];
				a[i$var48] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var48]]);
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
	public final void setIntermediates() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(setFlag$a) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1)
				b[i$var48] = a[(i$var48 - 1)];
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\n/**\n * A model for the fairness work.\n */\npublic model Deterministic(int n, boolean[] flipsMeasured) {\n    int states = 5;\n\n    double[] v = new double[states];\n    for(int i:[0..states))\n        v[i] = 0.1;\n    \n    double[][] m = dirichlet(v).sample(states);\n\n    int[] a = new int[n];\n    int[] b = new int[n];\n    a[0] = 0;\n    for(int i=1; i<n; i++) {\n        b[i] = a[i-1];\n        a[i] = categorical(m[b[i]]).sample();\n    }\n    \n    boolean[] flips = new boolean[n];\n            \n    for(int j:[0..n))\n        flips[j] = bernoulli(1/a[j+1]).sample();\n        flips.observe(flipsMeasured);\n}";
	}
}