package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ReductionTest$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements ReductionTest$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private double[] cv$var18$countGlobal;
	private double[] cv$var35$stateProbabilityGlobal;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample30 = false;
	private boolean fixedFlag$sample38 = false;
	private boolean fixedFlag$sample54 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample30 = false;
	private boolean fixedProbFlag$sample38 = false;
	private boolean fixedProbFlag$sample54 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample38;
	private double[] logProbability$sample54;
	private double logProbability$st;
	private double logProbability$var13;
	private double logProbability$var18;
	private double logProbability$var22;
	private double logProbability$var27;
	private double[] logProbability$var34;
	private double[] logProbability$var49;
	private double[][] m;
	private int noCats;
	private int noFlips;
	private int noStates;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public ReductionTest$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for bias.
	@Override
	public final double[] get$bias() {
		return bias;
	}

	// Setter for bias.
	@Override
	public final void set$bias(double[] cv$value) {
		// Set flags for all the side effects of bias including if probabilities need to be
		// updated.
		// Set bias with flag to mark that it has been set so another array doesn't need to
		// be constructed
		bias = cv$value;
		setFlag$bias = true;
		
		// Unset the fixed probability flag for sample 30 as it depends on bias.
		fixedProbFlag$sample30 = false;
		
		// Unset the fixed probability flag for sample 54 as it depends on bias.
		fixedProbFlag$sample54 = false;
	}

	// Getter for fixedFlag$sample20.
	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	// Setter for fixedFlag$sample20.
	@Override
	public final void set$fixedFlag$sample20(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample20 including if probabilities
		// need to be updated.
		fixedFlag$sample20 = cv$value;
		
		// Should the probability of sample 20 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample20" with its value "cv$value".
		fixedProbFlag$sample20 = (cv$value && fixedProbFlag$sample20);
		
		// Should the probability of sample 38 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample20" with its value "cv$value".
		fixedProbFlag$sample38 = (cv$value && fixedProbFlag$sample38);
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
		
		// Should the probability of sample 54 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample30" with its value "cv$value".
		fixedProbFlag$sample54 = (cv$value && fixedProbFlag$sample54);
	}

	// Getter for fixedFlag$sample38.
	@Override
	public final boolean get$fixedFlag$sample38() {
		return fixedFlag$sample38;
	}

	// Setter for fixedFlag$sample38.
	@Override
	public final void set$fixedFlag$sample38(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample38 including if probabilities
		// need to be updated.
		fixedFlag$sample38 = cv$value;
		
		// Should the probability of sample 38 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample38" with its value "cv$value".
		fixedProbFlag$sample38 = (cv$value && fixedProbFlag$sample38);
		
		// Should the probability of sample 54 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample38" with its value "cv$value".
		fixedProbFlag$sample54 = (cv$value && fixedProbFlag$sample54);
	}

	// Getter for fixedFlag$sample54.
	@Override
	public final boolean get$fixedFlag$sample54() {
		return fixedFlag$sample54;
	}

	// Setter for fixedFlag$sample54.
	@Override
	public final void set$fixedFlag$sample54(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample54 including if probabilities
		// need to be updated.
		fixedFlag$sample54 = cv$value;
		
		// Should the probability of sample 54 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample54" with its value "cv$value".
		fixedProbFlag$sample54 = (cv$value && fixedProbFlag$sample54);
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
		
		// Unset the fixed probability flag for sample 54 as it depends on flips.
		fixedProbFlag$sample54 = false;
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

	// Getter for length$flipsMeasured.
	@Override
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	// Setter for length$flipsMeasured.
	@Override
	public final void set$length$flipsMeasured(int cv$value) {
		length$flipsMeasured = cv$value;
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
		
		// Unset the fixed probability flag for sample 20 as it depends on m.
		fixedProbFlag$sample20 = false;
		
		// Unset the fixed probability flag for sample 38 as it depends on m.
		fixedProbFlag$sample38 = false;
	}

	// Getter for noCats.
	@Override
	public final int get$noCats() {
		return noCats;
	}

	// Setter for noCats.
	@Override
	public final void set$noCats(int cv$value) {
		noCats = cv$value;
	}

	// Getter for noFlips.
	@Override
	public final int get$noFlips() {
		return noFlips;
	}

	// Getter for noStates.
	@Override
	public final int get$noStates() {
		return noStates;
	}

	// Getter for st.
	@Override
	public final int[] get$st() {
		return st;
	}

	// Setter for st.
	@Override
	public final void set$st(int[] cv$value) {
		// Set flags for all the side effects of st including if probabilities need to be
		// updated.
		// Set st with flag to mark that it has been set so another array doesn't need to
		// be constructed
		st = cv$value;
		setFlag$st = true;
		
		// Unset the fixed probability flag for sample 38 as it depends on st.
		fixedProbFlag$sample38 = false;
		
		// Unset the fixed probability flag for sample 54 as it depends on st.
		fixedProbFlag$sample54 = false;
	}

	// Getter for v.
	@Override
	public final double[] get$v() {
		return v;
	}

	// Calculate the probability of the samples represented by sample20 using sampled
	// values.
	private final void logProbabilityValue$sample20() {
		// Determine if we need to calculate the values for sample task 20 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample20) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var17 = 0; var17 < noCats; var17 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var17], v));
			logProbability$var13 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var18 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample20)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample20 = fixedFlag$sample20;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var13 = logProbability$var18;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var18);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var18);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample20)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var18);
		}
	}

	// Calculate the probability of the samples represented by sample30 using sampled
	// values.
	private final void logProbabilityValue$sample30() {
		// Determine if we need to calculate the values for sample task 30 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample30) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var26 = 0; var26 < noFlips; var26 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(bias[var26], 1.0, 1.0));
			logProbability$var22 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var27 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample30)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample30 = fixedFlag$sample30;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var22 = logProbability$var27;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$bias = (logProbability$bias + logProbability$var27);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var27);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample30)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var27);
		}
	}

	// Calculate the probability of the samples represented by sample38 using sampled
	// values.
	private final void logProbabilityValue$sample38() {
		// Determine if we need to calculate the values for sample task 38 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample38) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = st[i$var32];
				double[] var33 = m[i$var32];
				
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
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var33.length))?Math.log(var33[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
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
				logProbability$var34[i$var32] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample38[i$var32] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample38)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample38 = (fixedFlag$sample38 && fixedFlag$sample20);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample38[i$var32];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var34[i$var32] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample38)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample54 using sampled
	// values.
	private final void logProbabilityValue$sample54() {
		// Determine if we need to calculate the values for sample task 54 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample54) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1) {
				// Reduction of array st
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				int reduceVar$var47$2 = 0;
				
				// For each index in the array to be reduced
				for(int cv$reduction47Index = 0; cv$reduction47Index < noCats; cv$reduction47Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// j$var45's comment
					// Set the right hand term to a value from the array st
					reduceVar$var47$2 = (reduceVar$var47$2 + st[cv$reduction47Index]);
				
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
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[j$var40], bias[reduceVar$var47$2]);
				
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
				logProbability$var49[j$var40] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample54[j$var40] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample54 = ((fixedFlag$sample54 && fixedFlag$sample30) && fixedFlag$sample38);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample54[j$var40];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var49[j$var40] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 20 drawn from Dirichlet 13. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample20(int var17) {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var18$countGlobal[cv$loopIndex] = 0.0;
		
		// Processing sample task 38 of consumer random variable null.
		// 
		// Increment the sample counter with the value sampled by sample task 38 of random
		// variable var34
		// 
		// A local reference to the scratch space.
		cv$var18$countGlobal[st[var17]] = (cv$var18$countGlobal[st[var17]] + 1.0);
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var18$countGlobal, m[var17]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 30 drawn from Beta 22. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample30(int var26) {
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		
		// Processing random variable 49.
		// 
		// Looking for a path between Sample 30 and consumer Bernoulli 49.
		// 
		// Reduction of array st
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		int reduceVar$var47$0 = 0;
		
		// For each index in the array to be reduced
		for(int cv$reduction47Index = 0; cv$reduction47Index < noCats; cv$reduction47Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// j$var45's comment
			// Set the right hand term to a value from the array st
			reduceVar$var47$0 = (reduceVar$var47$0 + st[cv$reduction47Index]);
		if((var26 == reduceVar$var47$0)) {
			for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1) {
				// Processing sample task 54 of consumer random variable null.
				// 
				// Include the value sampled by task 54 from random variable var49.
				// Increment the number of samples.
				cv$count = (cv$count + 1);
				
				// If the sample value was positive increase the count
				if(flips[j$var40])
					cv$sum = (cv$sum + 1);
			}
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		bias[var26] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 38 drawn from Categorical 34. Inference was performed using variable
	// marginalization.
	private final void sample38(int i$var32) {
		// Variable declaration of cv$noStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
		// cv$noStates's comment
		// Calculate the number of states to evaluate.
		int cv$noStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Value of the variable at this index
			st[i$var32] = cv$valuePos;
			
			// Variable declaration of cv$temp$0$var33 moved.
			// 
			// Constructing a random variable input for use later.
			double[] cv$temp$0$var33 = m[i$var32];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var33.length)?Math.log(cv$temp$0$var33[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Reduction of array st
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			int reduceVar$var47$1 = 0;
			
			// Reduce for every value except a masked value which will be skipped.
			for(int cv$reduction128Index = 0; cv$reduction128Index < i$var32; cv$reduction128Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// j$var45's comment
				// Set the right hand term to a value from the array st
				reduceVar$var47$1 = (reduceVar$var47$1 + st[cv$reduction128Index]);
			for(int cv$reduction128Index = (i$var32 + 1); cv$reduction128Index < noCats; cv$reduction128Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// j$var45's comment
				// Set the right hand term to a value from the array st
				reduceVar$var47$1 = (reduceVar$var47$1 + st[cv$reduction128Index]);
			
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// Processing random variable 49.
			// 
			// Value of the variable at this index
			reduceVar$var47$1 = (cv$valuePos + reduceVar$var47$1);
			for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1)
				// Processing sample task 54 of consumer random variable null.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 54 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$1$var48's comment
				// Variable declaration of cv$temp$1$var48 moved.
				// 
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[j$var40], bias[reduceVar$var47$1]) + cv$accumulatedProbabilities);
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var35$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
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
		double cv$lseMax = cv$var35$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var35$stateProbabilityGlobal[cv$lseIndex];
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
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var35$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var35$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var35$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var35$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var35$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var35$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Get a local reference to the scratch space.
		st[i$var32] = DistributionSampling.sampleCategorical(RNG$, cv$var35$stateProbabilityGlobal);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var18$countGlobal
		// 
		// Calculate the longest array this random variable could produce and allocate an
		// array large enough to handle this.
		int cv$max = 0;
		if((0 < noCats))
			cv$max = (length$flipsMeasured / noCats);
		
		// Allocation of cv$var18$countGlobal for single threaded execution
		cv$var18$countGlobal = new double[cv$max];
		
		// Allocation of cv$var35$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 36. Initially set to the value
		// of putTask 21.
		cv$var35$stateProbabilityGlobal = new double[((0.0 <= noCats)?(length$flipsMeasured / noCats):((noCats < 0.0)?(length$flipsMeasured / noCats):length$flipsMeasured))];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for v
		v = new double[(length$flipsMeasured / noCats)];
		
		// If m has not been set already allocate space.
		if(!setFlag$m) {
			// Constructor for m
			m = new double[noCats][];
			for(int var17 = 0; var17 < noCats; var17 += 1)
				m[var17] = new double[(length$flipsMeasured / noCats)];
		}
		
		// If bias has not been set already allocate space.
		if(!setFlag$bias)
			// Constructor for bias
			bias = new double[length$flipsMeasured];
		
		// If st has not been set already allocate space.
		if(!setFlag$st)
			// Constructor for st
			st = new int[noCats];
		
		// If flips has not been set already allocate space.
		if(!setFlag$flips)
			// Constructor for flips
			flips = new boolean[length$flipsMeasured];
		
		// Constructor for logProbability$var34
		logProbability$var34 = new double[noCats];
		
		// Constructor for logProbability$sample38
		logProbability$sample38 = new double[noCats];
		
		// Constructor for logProbability$var49
		logProbability$var49 = new double[length$flipsMeasured];
		
		// Constructor for logProbability$sample54
		logProbability$sample54 = new double[length$flipsMeasured];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample20) {
			for(int var17 = 0; var17 < noCats; var17 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var17]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample30) {
			for(int var26 = 0; var26 < noFlips; var26 += 1)
				bias[var26] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample38) {
			for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1)
				st[i$var32] = DistributionSampling.sampleCategorical(RNG$, m[i$var32]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample54) {
			for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1) {
				// Reduction of array st
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				int reduceVar$var47$3 = 0;
				
				// For each index in the array to be reduced
				for(int cv$reduction47Index = 0; cv$reduction47Index < noCats; cv$reduction47Index += 1)
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// j$var45's comment
					// Set the right hand term to a value from the array st
					reduceVar$var47$3 = (reduceVar$var47$3 + st[cv$reduction47Index]);
				flips[j$var40] = DistributionSampling.sampleBernoulli(RNG$, bias[reduceVar$var47$3]);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample20) {
			for(int var17 = 0; var17 < noCats; var17 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var17]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample30) {
			for(int var26 = 0; var26 < noFlips; var26 += 1)
				bias[var26] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample38) {
			for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1)
				st[i$var32] = DistributionSampling.sampleCategorical(RNG$, m[i$var32]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample20) {
			for(int var17 = 0; var17 < noCats; var17 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var17]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample30) {
			for(int var26 = 0; var26 < noFlips; var26 += 1)
				bias[var26] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample38) {
			for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1)
				st[i$var32] = DistributionSampling.sampleCategorical(RNG$, m[i$var32]);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample20) {
				for(int var17 = 0; var17 < noCats; var17 += 1)
					sample20(var17);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample30) {
				for(int var26 = 0; var26 < noFlips; var26 += 1)
					sample30(var26);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample38) {
				for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1)
					sample38(i$var32);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample38) {
				for(int i$var32 = (noCats - 1); i$var32 >= 0; i$var32 -= 1)
					sample38(i$var32);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample30) {
				for(int var26 = (noFlips - 1); var26 >= 0; var26 -= 1)
					sample30(var26);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample20) {
				for(int var17 = (noCats - 1); var17 >= 0; var17 -= 1)
					sample20(var17);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		noFlips = length$flipsMeasured;
		noStates = (length$flipsMeasured / noCats);
		for(int i$var10 = 0; i$var10 < (length$flipsMeasured / noCats); i$var10 += 1)
			v[i$var10] = 0.1;
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
		logProbability$var13 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$var18 = 0.0;
		logProbability$var22 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample30)
			logProbability$var27 = 0.0;
		for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1)
			logProbability$var34[i$var32] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample38) {
			for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1)
				logProbability$sample38[i$var32] = 0.0;
		}
		for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1)
			logProbability$var49[j$var40] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample54) {
			for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1)
				logProbability$sample54[j$var40] = 0.0;
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
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		if(fixedFlag$sample30)
			logProbabilityValue$sample30();
		if(fixedFlag$sample38)
			logProbabilityValue$sample38();
		logProbabilityValue$sample54();
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
		logProbabilityValue$sample20();
		logProbabilityValue$sample30();
		logProbabilityValue$sample38();
		logProbabilityValue$sample54();
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
		logProbabilityValue$sample20();
		logProbabilityValue$sample30();
		logProbabilityValue$sample38();
		logProbabilityValue$sample54();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample20) {
			for(int var17 = 0; var17 < noCats; var17 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var17]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample30) {
			for(int var26 = 0; var26 < noFlips; var26 += 1)
				bias[var26] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample38) {
			for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1)
				st[i$var32] = DistributionSampling.sampleCategorical(RNG$, m[i$var32]);
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
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel ReductionTest(boolean[] flipsMeasured, int noCats) {\n    int noFlips = flipsMeasured.length;\n    int noStates = noFlips/noCats;\n    \n    double[] v = new double[noStates];\n    for(int i:[0..noStates))\n        v[i] = 0.1;\n    \n    double[][] m = dirichlet(v).sample(noCats);\n    \n    double[] bias = beta(1.0, 1.0).sample(noFlips);\n    \n    int[] st = new int[noCats];\n\n\n    for(int i:[0..noCats))\n        st[i] = categorical(m[i]).sample();\n            \n    boolean[] flips = new boolean[noFlips];\n            \n    for(int j:[0..noFlips))\n        flips[j] = bernoulli(bias[sum(st)]).sample();\n\n    flips.observe(flipsMeasured);\n    \n    private int sum(int[] a) {\n        return reduce(a, 0, (i,j) -> {\n            return i + j;\n        });\n    }\n}";
	}
}