package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LDATest$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements LDATest$CoreInterface {
	
	// Declare the variables for the model.
	private double[] alpha;
	private double[] beta;
	private double[][] cv$var25$countGlobal;
	private double[][] cv$var33$countGlobal;
	private double[][] cv$var53$stateProbabilityGlobal;
	private int[][] documents;
	private boolean fixedFlag$sample26 = false;
	private boolean fixedFlag$sample36 = false;
	private boolean fixedFlag$sample61 = false;
	private boolean fixedFlag$sample64 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean fixedProbFlag$sample36 = false;
	private boolean fixedProbFlag$sample61 = false;
	private boolean fixedProbFlag$sample64 = false;
	private int[] length$documents;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$phi;
	private double logProbability$theta;
	private double logProbability$var20;
	private double logProbability$var25;
	private double logProbability$var27;
	private double logProbability$var33;
	private double logProbability$var52;
	private double logProbability$var55;
	private double logProbability$var56;
	private double logProbability$w;
	private double logProbability$z;
	private int noTopics;
	private double[][] phi;
	private boolean setFlag$phi = false;
	private boolean setFlag$theta = false;
	private boolean setFlag$w = false;
	private boolean setFlag$z = false;
	private boolean system$gibbsForward = true;
	private double[][] theta;
	private int vocabSize;
	private int[][] w;
	private int[][] z;

	public LDATest$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for alpha.
	@Override
	public final double[] get$alpha() {
		return alpha;
	}

	// Getter for beta.
	@Override
	public final double[] get$beta() {
		return beta;
	}

	// Getter for documents.
	@Override
	public final int[][] get$documents() {
		return documents;
	}

	// Setter for documents.
	@Override
	public final void set$documents(int[][] cv$value) {
		// Set documents with flag to mark that it has been set so another array doesn't need
		// to be constructed
		documents = cv$value;
	}

	// Getter for fixedFlag$sample26.
	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	// Setter for fixedFlag$sample26.
	@Override
	public final void set$fixedFlag$sample26(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample26 including if probabilities
		// need to be updated.
		fixedFlag$sample26 = cv$value;
		
		// Should the probability of sample 26 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample26" with its value "cv$value".
		fixedProbFlag$sample26 = (cv$value && fixedProbFlag$sample26);
		
		// Should the probability of sample 64 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample26" with its value "cv$value".
		fixedProbFlag$sample64 = (cv$value && fixedProbFlag$sample64);
	}

	// Getter for fixedFlag$sample36.
	@Override
	public final boolean get$fixedFlag$sample36() {
		return fixedFlag$sample36;
	}

	// Setter for fixedFlag$sample36.
	@Override
	public final void set$fixedFlag$sample36(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample36 including if probabilities
		// need to be updated.
		fixedFlag$sample36 = cv$value;
		
		// Should the probability of sample 36 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample36" with its value "cv$value".
		fixedProbFlag$sample36 = (cv$value && fixedProbFlag$sample36);
		
		// Should the probability of sample 61 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample36" with its value "cv$value".
		fixedProbFlag$sample61 = (cv$value && fixedProbFlag$sample61);
	}

	// Getter for fixedFlag$sample61.
	@Override
	public final boolean get$fixedFlag$sample61() {
		return fixedFlag$sample61;
	}

	// Setter for fixedFlag$sample61.
	@Override
	public final void set$fixedFlag$sample61(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample61 including if probabilities
		// need to be updated.
		fixedFlag$sample61 = cv$value;
		
		// Should the probability of sample 61 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample61" with its value "cv$value".
		fixedProbFlag$sample61 = (cv$value && fixedProbFlag$sample61);
		
		// Should the probability of sample 64 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample61" with its value "cv$value".
		fixedProbFlag$sample64 = (cv$value && fixedProbFlag$sample64);
	}

	// Getter for fixedFlag$sample64.
	@Override
	public final boolean get$fixedFlag$sample64() {
		return fixedFlag$sample64;
	}

	// Setter for fixedFlag$sample64.
	@Override
	public final void set$fixedFlag$sample64(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample64 including if probabilities
		// need to be updated.
		fixedFlag$sample64 = cv$value;
		
		// Should the probability of sample 64 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample64" with its value "cv$value".
		fixedProbFlag$sample64 = (cv$value && fixedProbFlag$sample64);
	}

	// Getter for length$documents.
	@Override
	public final int[] get$length$documents() {
		return length$documents;
	}

	// Setter for length$documents.
	@Override
	public final void set$length$documents(int[] cv$value) {
		// Set length$documents with flag to mark that it has been set so another array doesn't
		// need to be constructed
		length$documents = cv$value;
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

	// Getter for logProbability$phi.
	@Override
	public final double get$logProbability$phi() {
		return logProbability$phi;
	}

	// Getter for logProbability$theta.
	@Override
	public final double get$logProbability$theta() {
		return logProbability$theta;
	}

	// Getter for logProbability$w.
	@Override
	public final double get$logProbability$w() {
		return logProbability$w;
	}

	// Getter for logProbability$z.
	@Override
	public final double get$logProbability$z() {
		return logProbability$z;
	}

	// Getter for noTopics.
	@Override
	public final int get$noTopics() {
		return noTopics;
	}

	// Setter for noTopics.
	@Override
	public final void set$noTopics(int cv$value) {
		noTopics = cv$value;
	}

	// Getter for phi.
	@Override
	public final double[][] get$phi() {
		return phi;
	}

	// Setter for phi.
	@Override
	public final void set$phi(double[][] cv$value) {
		// Set flags for all the side effects of phi including if probabilities need to be
		// updated.
		// Set phi with flag to mark that it has been set so another array doesn't need to
		// be constructed
		phi = cv$value;
		setFlag$phi = true;
		
		// Unset the fixed probability flag for sample 26 as it depends on phi.
		fixedProbFlag$sample26 = false;
		
		// Unset the fixed probability flag for sample 64 as it depends on phi.
		fixedProbFlag$sample64 = false;
	}

	// Getter for theta.
	@Override
	public final double[][] get$theta() {
		return theta;
	}

	// Setter for theta.
	@Override
	public final void set$theta(double[][] cv$value) {
		// Set flags for all the side effects of theta including if probabilities need to
		// be updated.
		// Set theta with flag to mark that it has been set so another array doesn't need
		// to be constructed
		theta = cv$value;
		setFlag$theta = true;
		
		// Unset the fixed probability flag for sample 36 as it depends on theta.
		fixedProbFlag$sample36 = false;
		
		// Unset the fixed probability flag for sample 61 as it depends on theta.
		fixedProbFlag$sample61 = false;
	}

	// Getter for vocabSize.
	@Override
	public final int get$vocabSize() {
		return vocabSize;
	}

	// Setter for vocabSize.
	@Override
	public final void set$vocabSize(int cv$value) {
		vocabSize = cv$value;
	}

	// Getter for w.
	@Override
	public final int[][] get$w() {
		return w;
	}

	// Setter for w.
	@Override
	public final void set$w(int[][] cv$value) {
		// Set flags for all the side effects of w including if probabilities need to be updated.
		// Set w with flag to mark that it has been set so another array doesn't need to be
		// constructed
		w = cv$value;
		setFlag$w = true;
		
		// Unset the fixed probability flag for sample 64 as it depends on w.
		fixedProbFlag$sample64 = false;
	}

	// Getter for z.
	@Override
	public final int[][] get$z() {
		return z;
	}

	// Setter for z.
	@Override
	public final void set$z(int[][] cv$value) {
		// Set z with flag to mark that it has been set so another array doesn't need to be
		// constructed
		z = cv$value;
		setFlag$z = true;
	}

	// Calculate the probability of the samples represented by sample26 using sampled
	// values.
	private final void logProbabilityValue$sample26() {
		// Determine if we need to calculate the values for sample task 26 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample26) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var24 = 0; var24 < noTopics; var24 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(phi[var24], beta));
			logProbability$var20 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var25 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$phi = (logProbability$phi + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample26)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample26 = fixedFlag$sample26;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var20 = logProbability$var25;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$phi = (logProbability$phi + logProbability$var25);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var25);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample26)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var25);
		}
	}

	// Calculate the probability of the samples represented by sample36 using sampled
	// values.
	private final void logProbabilityValue$sample36() {
		// Determine if we need to calculate the values for sample task 36 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample36) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var32 = 0; var32 < length$documents.length; var32 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(theta[var32], alpha));
			logProbability$var27 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var33 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$theta = (logProbability$theta + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample36)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample36 = fixedFlag$sample36;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var27 = logProbability$var33;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$theta = (logProbability$theta + logProbability$var33);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var33);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample36)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var33);
		}
	}

	// Calculate the probability of the samples represented by sample61 using sampled
	// values.
	private final void logProbabilityValue$sample61() {
		// Determine if we need to calculate the values for sample task 61 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample61) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
				for(int j = 0; j < length$documents[i$var40]; j += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = z[i$var40][j];
					double[] var51 = theta[i$var40];
					
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
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var51.length))?Math.log(var51[cv$sampleValue]):Double.NEGATIVE_INFINITY));
				}
			}
			logProbability$var52 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$z = cv$sampleAccumulator;
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample61)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample61 = (fixedFlag$sample61 && fixedFlag$sample36);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var52 = logProbability$z;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$z);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample61)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$z);
		}
	}

	// Calculate the probability of the samples represented by sample64 using sampled
	// values.
	private final void logProbabilityValue$sample64() {
		// Determine if we need to calculate the values for sample task 64 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample64) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
				for(int j = 0; j < length$documents[i$var40]; j += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = w[i$var40][j];
					double[] var54 = phi[z[i$var40][j]];
					
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
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var54.length))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY));
				}
			}
			logProbability$var55 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var56 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$w = (logProbability$w + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample64 = ((fixedFlag$sample64 && fixedFlag$sample26) && fixedFlag$sample61);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var55 = logProbability$var56;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$w = (logProbability$w + logProbability$var56);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var56);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var56);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 26 drawn from Dirichlet 20. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample26(int var24, int threadID$cv$var24, Rng RNG$) {
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var25$countGlobal[threadID$cv$var24];
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < vocabSize; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		
		// Processing random variable 55.
		// 
		// Looking for a path between Sample 26 and consumer Categorical 55.
		for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
			for(int j = 0; j < length$documents[i$var40]; j += 1) {
				if((var24 == z[i$var40][j]))
					// Processing sample task 64 of consumer random variable null.
					// 
					// Increment the sample counter with the value sampled by sample task 64 of random
					// variable var55
					cv$countLocal[w[i$var40][j]] = (cv$countLocal[w[i$var40][j]] + 1.0);
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, beta, cv$countLocal, phi[var24]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 36 drawn from Dirichlet 27. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample36(int var32, int threadID$cv$var32, Rng RNG$) {
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var33$countGlobal[threadID$cv$var32];
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noTopics; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		
		// Substituted "i$var40" with its value "var32".
		for(int j = 0; j < length$documents[var32]; j += 1)
			// Processing sample task 61 of consumer random variable null.
			// 
			// Increment the sample counter with the value sampled by sample task 61 of random
			// variable var52
			// 
			// Substituted "i$var40" with its value "var32".
			cv$countLocal[z[var32][j]] = (cv$countLocal[z[var32][j]] + 1.0);
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, alpha, cv$countLocal, theta[var32]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 61 drawn from Categorical 52. Inference was performed using variable
	// marginalization.
	private final void sample61(int i$var40, int j, int threadID$cv$j, Rng RNG$) {
		// Variable declaration of cv$noStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
		// cv$noStates's comment
		// Calculate the number of states to evaluate.
		int cv$noStates = Math.max(0, noTopics);
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var53$stateProbabilityGlobal[threadID$cv$j];
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			z[i$var40][j] = cv$valuePos;
			
			// Variable declaration of cv$temp$0$var51 moved.
			// 
			// Constructing a random variable input for use later.
			double[] cv$temp$0$var51 = theta[i$var40];
			
			// Variable declaration of cv$temp$1$var54 moved.
			// 
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			double[] cv$temp$1$var54 = phi[cv$valuePos];
			
			// Save the calculated index value into the array of index value probabilities
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
			// Recorded the probability of reaching sample task 64 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			cv$stateProbabilityLocal[cv$valuePos] = ((((0.0 <= w[i$var40][j]) && (w[i$var40][j] < cv$temp$1$var54.length))?Math.log(cv$temp$1$var54[w[i$var40][j]]):Double.NEGATIVE_INFINITY) + ((cv$valuePos < cv$temp$0$var51.length)?Math.log(cv$temp$0$var51[cv$valuePos]):Double.NEGATIVE_INFINITY));
		}
		
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
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the new value of the sample.
		z[i$var40][j] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var25$countGlobal
		{
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			int cv$max = 0;
			if((0 < noTopics))
				// Calculate the longest array this random variable could produce and allocate an
				// array large enough to handle this.
				cv$max = Math.max(0, vocabSize);
			
			// Allocation of cv$var25$countGlobal for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var25$countGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var25$countGlobal[cv$index] = new double[cv$max];
		}
		
		// Constructor for cv$var33$countGlobal
		{
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			int cv$max = 0;
			if((0 < length$documents.length))
				// Calculate the longest array this random variable could produce and allocate an
				// array large enough to handle this.
				cv$max = Math.max(0, noTopics);
			
			// Allocation of cv$var33$countGlobal for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var33$countGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var33$countGlobal[cv$index] = new double[cv$max];
		}
		
		// Allocation of cv$var53$stateProbabilityGlobal for multithreaded execution
		// 
		// Get the thread count.
		int cv$threadCount = threadCount();
		
		// Allocate an array to hold a copy per thread
		cv$var53$stateProbabilityGlobal = new double[cv$threadCount][];
		
		// Populate the array with a copy per thread
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			// Variable to record the maximum value of Task Get 59. Initially set to the value
			// of putTask 37.
			cv$var53$stateProbabilityGlobal[cv$index] = new double[noTopics];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for alpha
		alpha = new double[noTopics];
		
		// Constructor for beta
		beta = new double[vocabSize];
		
		// If phi has not been set already allocate space.
		if(!setFlag$phi) {
			// Constructor for phi
			phi = new double[noTopics][];
			for(int var24 = 0; var24 < noTopics; var24 += 1)
				phi[var24] = new double[vocabSize];
		}
		
		// If theta has not been set already allocate space.
		if(!setFlag$theta) {
			// Constructor for theta
			theta = new double[length$documents.length][];
			for(int var32 = 0; var32 < length$documents.length; var32 += 1)
				theta[var32] = new double[noTopics];
		}
		
		// If w has not been set already allocate space.
		if(!setFlag$w) {
			// Constructor for w
			w = new int[length$documents.length][];
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1)
				w[i$var40] = new int[length$documents[i$var40]];
		}
		
		// If z has not been set already allocate space.
		if(!setFlag$z) {
			// Constructor for z
			z = new int[length$documents.length][];
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1)
				z[i$var40] = new int[length$documents[i$var40]];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample26)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noTopics, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, beta, phi[var24]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample36)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, alpha, theta[var32]);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$documents.length, 1,
			(int forStart$index$i$var40, int forEnd$index$i$var40, int threadID$index$i$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var40 = forStart$index$i$var40; index$i$var40 < forEnd$index$i$var40; index$i$var40 += 1) {
						int i$var40 = index$i$var40;
						int[] t = w[i$var40];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, length$documents[i$var40], 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j = forStart$j; j < forEnd$j; j += 1) {
										if(!fixedFlag$sample61)
											z[i$var40][j] = DistributionSampling.sampleCategorical(RNG$2, theta[i$var40]);
										if(!fixedFlag$sample64)
											t[j] = DistributionSampling.sampleCategorical(RNG$2, phi[z[i$var40][j]]);
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
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample26)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noTopics, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, beta, phi[var24]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample36)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, alpha, theta[var32]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample61)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$index$i$var40, int forEnd$index$i$var40, int threadID$index$i$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$i$var40 = forStart$index$i$var40; index$i$var40 < forEnd$index$i$var40; index$i$var40 += 1) {
							int i$var40 = index$i$var40;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, length$documents[i$var40], 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j = forStart$j; j < forEnd$j; j += 1)
											z[i$var40][j] = DistributionSampling.sampleCategorical(RNG$2, theta[i$var40]);
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
		if(!fixedFlag$sample26)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noTopics, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, beta, phi[var24]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample36)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, alpha, theta[var32]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample61)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$index$i$var40, int forEnd$index$i$var40, int threadID$index$i$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$i$var40 = forStart$index$i$var40; index$i$var40 < forEnd$index$i$var40; index$i$var40 += 1) {
							int i$var40 = index$i$var40;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, length$documents[i$var40], 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j = forStart$j; j < forEnd$j; j += 1)
											z[i$var40][j] = DistributionSampling.sampleCategorical(RNG$2, theta[i$var40]);
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
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample26)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noTopics, 1,
					(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
								sample26(var24, threadID$var24, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample36)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, length$documents.length, 1,
					(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1)
								sample36(var32, threadID$var32, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample61)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, length$documents.length, 1,
					(int forStart$index$i$var40, int forEnd$index$i$var40, int threadID$index$i$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int index$i$var40 = forStart$index$i$var40; index$i$var40 < forEnd$index$i$var40; index$i$var40 += 1) {
								int i$var40 = index$i$var40;
								
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, length$documents[i$var40], 1,
									(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int j = forStart$j; j < forEnd$j; j += 1)
												sample61(i$var40, j, threadID$j, RNG$2);
									}
								);
							}
					}
				);

		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample61)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, length$documents.length, 1,
					(int forStart$index$i$var40, int forEnd$index$i$var40, int threadID$index$i$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int index$i$var40 = forStart$index$i$var40; index$i$var40 < forEnd$index$i$var40; index$i$var40 += 1) {
								int i$var40 = index$i$var40;
								
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, length$documents[i$var40], 1,
									(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int j = forStart$j; j < forEnd$j; j += 1)
												sample61(i$var40, j, threadID$j, RNG$2);
									}
								);
							}
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample36)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, length$documents.length, 1,
					(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1)
								sample36(var32, threadID$var32, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample26)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noTopics, 1,
					(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
								sample26(var24, threadID$var24, RNG$1);
					}
				);

		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noTopics, 1,
			(int forStart$i$var11, int forEnd$i$var11, int threadID$i$var11, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var11 = forStart$i$var11; i$var11 < forEnd$i$var11; i$var11 += 1)
						alpha[i$var11] = 0.1;
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, vocabSize, 1,
			(int forStart$i$var17, int forEnd$i$var17, int threadID$i$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var17 = forStart$i$var17; i$var17 < forEnd$i$var17; i$var17 += 1)
						beta[i$var17] = 0.1;
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
		logProbability$var20 = 0.0;
		logProbability$phi = 0.0;
		if(!fixedProbFlag$sample26)
			logProbability$var25 = 0.0;
		logProbability$var27 = 0.0;
		logProbability$theta = 0.0;
		if(!fixedProbFlag$sample36)
			logProbability$var33 = 0.0;
		logProbability$var52 = 0.0;
		if(!fixedProbFlag$sample61)
			logProbability$z = 0.0;
		logProbability$var55 = 0.0;
		logProbability$w = 0.0;
		if(!fixedProbFlag$sample64)
			logProbability$var56 = 0.0;
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
		if(fixedFlag$sample26)
			logProbabilityValue$sample26();
		if(fixedFlag$sample36)
			logProbabilityValue$sample36();
		if(fixedFlag$sample61)
			logProbabilityValue$sample61();
		logProbabilityValue$sample64();
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
		logProbabilityValue$sample26();
		logProbabilityValue$sample36();
		logProbabilityValue$sample61();
		logProbabilityValue$sample64();
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
		logProbabilityValue$sample26();
		logProbabilityValue$sample36();
		logProbabilityValue$sample61();
		logProbabilityValue$sample64();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample26)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noTopics, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, beta, phi[var24]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample36)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, alpha, theta[var32]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample61)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$index$i$var40, int forEnd$index$i$var40, int threadID$index$i$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$i$var40 = forStart$index$i$var40; index$i$var40 < forEnd$index$i$var40; index$i$var40 += 1) {
							int i$var40 = index$i$var40;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, length$documents[i$var40], 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j = forStart$j; j < forEnd$j; j += 1)
											z[i$var40][j] = DistributionSampling.sampleCategorical(RNG$2, theta[i$var40]);
								}
							);
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
		// 
		// Deep copy between arrays
		int cv$length1 = w.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = documents[cv$index1];
			int[] cv$target2 = w[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel LDATest(int noTopics, int vocabSize, int[][] documents) {\n\n    double[] alpha = new double[noTopics];\n    for(int i:[0..noTopics))\n        alpha[i] = 0.1;\n\n    double[] beta = new double[vocabSize];\n    for(int i:[0..vocabSize))\n        beta[i] = 0.1;\n\n    double[][] phi = dirichlet(beta).sample(noTopics);\n    double[][] theta = dirichlet(alpha).sample(documents.length);\n    int[][] w = new int[documents.length][];\n\n    for(int i:[0..documents.length)) {\n        int[] t = new int[documents[i].length];\n        for(int j:[0..documents[i].length)) {\n            int z = categorical(theta[i]).sample();\n            t[j] = categorical(phi[z]).sample();\n        }\n        w[i] = t;\n    }\n\n    w.observe(documents);\n}\n";
	}
}