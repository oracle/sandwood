package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class MultinomialBernoulli$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements MultinomialBernoulli$CoreInterface {
	
	// Declare the variables for the model.
	private double[] beta;
	private double[] cv$var16$countGlobal;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample42 = false;
	private boolean fixedFlag$sample47 = false;
	private boolean fixedFlag$sample52 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample42 = false;
	private boolean fixedProbFlag$sample47 = false;
	private boolean fixedProbFlag$sample52 = false;
	private int length;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b1;
	private double logProbability$b2;
	private double logProbability$b3;
	private double logProbability$output;
	private double logProbability$p;
	private double logProbability$prior;
	private double logProbability$var15;
	private double logProbability$var18;
	private double logProbability$var40;
	private double logProbability$var45;
	private double logProbability$var50;
	private boolean[] observed;
	private boolean[] output;
	private double[] p;
	private int[] prior;
	private boolean setFlag$output = false;
	private boolean setFlag$p = false;
	private boolean setFlag$prior = false;
	private boolean system$gibbsForward = true;

	public MultinomialBernoulli$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for beta.
	@Override
	public final double[] get$beta() {
		return beta;
	}

	// Getter for fixedFlag$sample17.
	@Override
	public final boolean get$fixedFlag$sample17() {
		return fixedFlag$sample17;
	}

	// Setter for fixedFlag$sample17.
	@Override
	public final void set$fixedFlag$sample17(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample17 including if probabilities
		// need to be updated.
		fixedFlag$sample17 = cv$value;
		
		// Should the probability of sample 17 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample17" with its value "cv$value".
		fixedProbFlag$sample17 = (cv$value && fixedProbFlag$sample17);
		
		// Should the probability of sample 20 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample17" with its value "cv$value".
		fixedProbFlag$sample20 = (cv$value && fixedProbFlag$sample20);
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
		
		// Should the probability of sample 42 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample20" with its value "cv$value".
		fixedProbFlag$sample42 = (cv$value && fixedProbFlag$sample42);
		
		// Should the probability of sample 47 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample20" with its value "cv$value".
		fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
		
		// Should the probability of sample 52 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample20" with its value "cv$value".
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
	}

	// Getter for fixedFlag$sample42.
	@Override
	public final boolean get$fixedFlag$sample42() {
		return fixedFlag$sample42;
	}

	// Setter for fixedFlag$sample42.
	@Override
	public final void set$fixedFlag$sample42(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample42 including if probabilities
		// need to be updated.
		fixedFlag$sample42 = cv$value;
		
		// Should the probability of sample 42 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample42" with its value "cv$value".
		fixedProbFlag$sample42 = (cv$value && fixedProbFlag$sample42);
	}

	// Getter for fixedFlag$sample47.
	@Override
	public final boolean get$fixedFlag$sample47() {
		return fixedFlag$sample47;
	}

	// Setter for fixedFlag$sample47.
	@Override
	public final void set$fixedFlag$sample47(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample47 including if probabilities
		// need to be updated.
		fixedFlag$sample47 = cv$value;
		
		// Should the probability of sample 47 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample47" with its value "cv$value".
		fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
	}

	// Getter for fixedFlag$sample52.
	@Override
	public final boolean get$fixedFlag$sample52() {
		return fixedFlag$sample52;
	}

	// Setter for fixedFlag$sample52.
	@Override
	public final void set$fixedFlag$sample52(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample52 including if probabilities
		// need to be updated.
		fixedFlag$sample52 = cv$value;
		
		// Should the probability of sample 52 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample52" with its value "cv$value".
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
	}

	// Getter for length.
	@Override
	public final int get$length() {
		return length;
	}

	// Getter for length$observed.
	@Override
	public final int get$length$observed() {
		return length$observed;
	}

	// Setter for length$observed.
	@Override
	public final void set$length$observed(int cv$value) {
		length$observed = cv$value;
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

	// Getter for logProbability$b1.
	@Override
	public final double get$logProbability$b1() {
		return logProbability$b1;
	}

	// Getter for logProbability$b2.
	@Override
	public final double get$logProbability$b2() {
		return logProbability$b2;
	}

	// Getter for logProbability$b3.
	@Override
	public final double get$logProbability$b3() {
		return logProbability$b3;
	}

	// Getter for logProbability$output.
	@Override
	public final double get$logProbability$output() {
		return logProbability$output;
	}

	// Getter for logProbability$p.
	@Override
	public final double get$logProbability$p() {
		return logProbability$p;
	}

	// Getter for logProbability$prior.
	@Override
	public final double get$logProbability$prior() {
		return logProbability$prior;
	}

	// Getter for n.
	@Override
	public final int get$n() {
		return 10;
	}

	// Getter for observed.
	@Override
	public final boolean[] get$observed() {
		return observed;
	}

	// Setter for observed.
	@Override
	public final void set$observed(boolean[] cv$value) {
		// Set observed with flag to mark that it has been set so another array doesn't need
		// to be constructed
		observed = cv$value;
	}

	// Getter for output.
	@Override
	public final boolean[] get$output() {
		return output;
	}

	// Setter for output.
	@Override
	public final void set$output(boolean[] cv$value) {
		// Set flags for all the side effects of output including if probabilities need to
		// be updated.
		// Set output with flag to mark that it has been set so another array doesn't need
		// to be constructed
		output = cv$value;
		setFlag$output = true;
		
		// Unset the fixed probability flag for sample 42 as it depends on output.
		fixedProbFlag$sample42 = false;
		
		// Unset the fixed probability flag for sample 47 as it depends on output.
		fixedProbFlag$sample47 = false;
		
		// Unset the fixed probability flag for sample 52 as it depends on output.
		fixedProbFlag$sample52 = false;
	}

	// Getter for p.
	@Override
	public final double[] get$p() {
		return p;
	}

	// Setter for p.
	@Override
	public final void set$p(double[] cv$value) {
		// Set flags for all the side effects of p including if probabilities need to be updated.
		// Set p with flag to mark that it has been set so another array doesn't need to be
		// constructed
		p = cv$value;
		setFlag$p = true;
		
		// Unset the fixed probability flag for sample 17 as it depends on p.
		fixedProbFlag$sample17 = false;
		
		// Unset the fixed probability flag for sample 20 as it depends on p.
		fixedProbFlag$sample20 = false;
	}

	// Getter for prior.
	@Override
	public final int[] get$prior() {
		return prior;
	}

	// Setter for prior.
	@Override
	public final void set$prior(int[] cv$value) {
		// Set flags for all the side effects of prior including if probabilities need to
		// be updated.
		// Set prior with flag to mark that it has been set so another array doesn't need
		// to be constructed
		prior = cv$value;
		setFlag$prior = true;
		
		// Unset the fixed probability flag for sample 20 as it depends on prior.
		fixedProbFlag$sample20 = false;
		
		// Unset the fixed probability flag for sample 42 as it depends on prior.
		fixedProbFlag$sample42 = false;
		
		// Unset the fixed probability flag for sample 47 as it depends on prior.
		fixedProbFlag$sample47 = false;
		
		// Unset the fixed probability flag for sample 52 as it depends on prior.
		fixedProbFlag$sample52 = false;
	}

	// Calculate the probability of the samples represented by sample17 using sampled
	// values.
	private final void logProbabilityValue$sample17() {
		// Determine if we need to calculate the values for sample task 17 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample17) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(p, beta);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var15 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$p = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample17)
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
			fixedProbFlag$sample17 = fixedFlag$sample17;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var15 = logProbability$p;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$p);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample17)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$p);
		}
	}

	// Calculate the probability of the samples represented by sample20 using sampled
	// values.
	private final void logProbabilityValue$sample20() {
		// Determine if we need to calculate the values for sample task 20 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample20) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityMultinomial(prior, p, 10);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var18 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$prior = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample20)
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
			fixedProbFlag$sample20 = (fixedFlag$sample20 && fixedFlag$sample17);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var18 = logProbability$prior;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$prior);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample20)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$prior);
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
			for(int i$var39 = 0; i$var39 < length; i$var39 += 3)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(output[i$var39], (prior[0] / 10)));
			logProbability$b1 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var40 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$output = (logProbability$output + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample42 = (fixedFlag$sample42 && fixedFlag$sample20);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$b1 = logProbability$var40;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$output = (logProbability$output + logProbability$var40);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var40);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var40);
		}
	}

	// Calculate the probability of the samples represented by sample47 using sampled
	// values.
	private final void logProbabilityValue$sample47() {
		// Determine if we need to calculate the values for sample task 47 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample47) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var44 = 1; i$var44 < length; i$var44 += 3)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(output[i$var44], (prior[1] / 10)));
			logProbability$b2 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var45 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$output = (logProbability$output + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample47 = (fixedFlag$sample47 && fixedFlag$sample20);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$b2 = logProbability$var45;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$output = (logProbability$output + logProbability$var45);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var45);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var45);
		}
	}

	// Calculate the probability of the samples represented by sample52 using sampled
	// values.
	private final void logProbabilityValue$sample52() {
		// Determine if we need to calculate the values for sample task 52 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample52) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var49 = 2; i$var49 < length; i$var49 += 3)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(output[i$var49], (prior[2] / 10)));
			logProbability$b3 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var50 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$output = (logProbability$output + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample52 = (fixedFlag$sample52 && fixedFlag$sample20);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$b3 = logProbability$var50;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$output = (logProbability$output + logProbability$var50);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var50);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var50);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 17 drawn from Dirichlet 15. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample17() {
		// Unrolled loop
		// 
		// A local reference to the scratch space.
		cv$var16$countGlobal[0] = 0.0;
		
		// A local reference to the scratch space.
		cv$var16$countGlobal[1] = 0.0;
		
		// A local reference to the scratch space.
		cv$var16$countGlobal[2] = 0.0;
		
		// A local reference to the scratch space.
		cv$var16$countGlobal[0] = (cv$var16$countGlobal[0] + prior[0]);
		
		// A local reference to the scratch space.
		cv$var16$countGlobal[1] = (cv$var16$countGlobal[1] + prior[1]);
		
		// A local reference to the scratch space.
		cv$var16$countGlobal[2] = (cv$var16$countGlobal[2] + prior[2]);
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, beta, cv$var16$countGlobal, p);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 20 drawn from Multinomial 18. Inference was performed using Metropolis-Hastings.
	private final void sample20() {
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the probability of the random variable generating the original sampled
		// value.
		double cv$originalProbability;
		
		// Count how many non zero entries there are.
		int cv$nonZeroCount = 0;
		
		// A reference local to the function for the sample variable.
		for(int cv$loopIndex = 0; cv$loopIndex < prior.length; cv$loopIndex += 1) {
			// A reference local to the function for the sample variable.
			if(!(prior[cv$loopIndex] == 0))
				cv$nonZeroCount = (cv$nonZeroCount + 1);
		}
		
		// Pick a value in the array to adjust.
		int cv$sourceIndex = (int)((double)cv$nonZeroCount * DistributionSampling.sampleUniform(RNG$));
		for(int cv$loopIndex = 0; cv$loopIndex <= cv$sourceIndex; cv$loopIndex += 1) {
			// A reference local to the function for the sample variable.
			if((prior[cv$loopIndex] == 0))
				cv$sourceIndex = (cv$sourceIndex + 1);
		}
		
		// Select the number of trials to remove from the selected category.
		// 
		// A reference local to the function for the sample variable.
		int cv$changeValue = (int)(((double)prior[cv$sourceIndex] * DistributionSampling.sampleUniform(RNG$)) + 1.0);
		
		// Select the destination of the moved trials.
		// 
		// A reference local to the function for the sample variable.
		int cv$destinationIndex = (int)((double)(prior.length - 1) * DistributionSampling.sampleUniform(RNG$));
		
		// Ensure the source and target are not equal
		if((cv$sourceIndex <= cv$destinationIndex))
			cv$destinationIndex = (cv$destinationIndex + 1);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$n" with its value "10".
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityMultinomial(prior, p, 10);
			
			// Processing random variable 24.
			// 
			// Processing sample task 42 of consumer random variable b1.
			for(int i$var39 = 0; i$var39 < length; i$var39 += 3)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 42 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$2$var23's comment
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var39], (prior[0] / 10)) + cv$accumulatedProbabilities);
			
			// Processing random variable 29.
			// 
			// Processing sample task 47 of consumer random variable b2.
			for(int i$var44 = 1; i$var44 < length; i$var44 += 3)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 47 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$3$var28's comment
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var44], (prior[1] / 10)) + cv$accumulatedProbabilities);
			
			// Processing random variable 34.
			// 
			// Processing sample task 52 of consumer random variable b3.
			for(int i$var49 = 2; i$var49 < length; i$var49 += 3)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 52 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$4$var33's comment
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var49], (prior[2] / 10)) + cv$accumulatedProbabilities);
			
			// Initialize an accumulator to take the product of all the distribution probabilities
			// in log space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Update Sample and intermediate values
		// 
		// Update the sample values
		// 
		// A reference local to the function for the sample variable.
		prior[cv$sourceIndex] = (prior[cv$sourceIndex] - cv$changeValue);
		
		// A reference local to the function for the sample variable.
		prior[cv$destinationIndex] = (prior[cv$destinationIndex] + cv$changeValue);
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$n" with its value "10".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityMultinomial(prior, p, 10);
		
		// Processing random variable 24.
		// 
		// Processing sample task 42 of consumer random variable b1.
		for(int i$var39 = 0; i$var39 < length; i$var39 += 3)
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 42 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// cv$temp$2$var23's comment
			// Constructing a random variable input for use later.
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var39], (prior[0] / 10)) + cv$accumulatedProbabilities);
		
		// Processing random variable 29.
		// 
		// Processing sample task 47 of consumer random variable b2.
		for(int i$var44 = 1; i$var44 < length; i$var44 += 3)
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 47 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// cv$temp$3$var28's comment
			// Constructing a random variable input for use later.
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var44], (prior[1] / 10)) + cv$accumulatedProbabilities);
		
		// Processing random variable 34.
		// 
		// Processing sample task 52 of consumer random variable b3.
		for(int i$var49 = 2; i$var49 < length; i$var49 += 3)
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 52 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// cv$temp$4$var33's comment
			// Constructing a random variable input for use later.
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var49], (prior[2] / 10)) + cv$accumulatedProbabilities);
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// Variable declaration of cv$proposedProbability moved.
		// Declaration comment was:
		// The probability of the random variable generating the new sample value.
		// 
		// Initialize an accumulator to take the product of all the distribution probabilities
		// in log space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		if(((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$)))) {
			// If it is not revert the sample value and intermediates to their original values.
			// 
			// Set the sample value
			// 
			// Calculate the new sample value
			// 
			// Update the sample values
			// A reference local to the function for the sample variable.
			prior[cv$sourceIndex] = (prior[cv$sourceIndex] + cv$changeValue);
			
			// A reference local to the function for the sample variable.
			prior[cv$destinationIndex] = (prior[cv$destinationIndex] - cv$changeValue);
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Constructor for cv$var16$countGlobal
		// 
		// Allocate scratch space.
		// 
		// Allocation of cv$var16$countGlobal for single threaded execution
		cv$var16$countGlobal = new double[3];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for beta
		beta = new double[3];
		
		// If p has not been set already allocate space.
		if(!setFlag$p)
			// Constructor for p
			p = new double[3];
		
		// If prior has not been set already allocate space.
		if(!setFlag$prior)
			// Constructor for prior
			prior = new int[3];
		
		// If output has not been set already allocate space.
		if(!setFlag$output)
			// Constructor for output
			output = new boolean[length$observed];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, beta, p);
		if(!fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(RNG$, p, 10, prior);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample42) {
			for(int i$var39 = 0; i$var39 < length; i$var39 += 3)
				output[i$var39] = DistributionSampling.sampleBernoulli(RNG$, (prior[0] / 10));
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample47) {
			for(int i$var44 = 1; i$var44 < length; i$var44 += 3)
				output[i$var44] = DistributionSampling.sampleBernoulli(RNG$, (prior[1] / 10));
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample52) {
			for(int i$var49 = 2; i$var49 < length; i$var49 += 3)
				output[i$var49] = DistributionSampling.sampleBernoulli(RNG$, (prior[2] / 10));
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, beta, p);
		if(!fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(RNG$, p, 10, prior);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, beta, p);
		if(!fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(RNG$, p, 10, prior);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample17)
				sample17();
			if(!fixedFlag$sample20)
				sample20();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!fixedFlag$sample20)
				sample20();
			if(!fixedFlag$sample17)
				sample17();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		beta[0] = 0.1;
		beta[1] = 0.1;
		beta[2] = 0.1;
		length = length$observed;
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
		logProbability$var15 = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$p = 0.0;
		logProbability$var18 = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$prior = 0.0;
		logProbability$b1 = 0.0;
		logProbability$output = 0.0;
		if(!fixedProbFlag$sample42)
			logProbability$var40 = 0.0;
		logProbability$b2 = 0.0;
		if(!fixedProbFlag$sample47)
			logProbability$var45 = 0.0;
		logProbability$b3 = 0.0;
		if(!fixedProbFlag$sample52)
			logProbability$var50 = 0.0;
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
		if(fixedFlag$sample17)
			logProbabilityValue$sample17();
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		logProbabilityValue$sample42();
		logProbabilityValue$sample47();
		logProbabilityValue$sample52();
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
		logProbabilityValue$sample17();
		logProbabilityValue$sample20();
		logProbabilityValue$sample42();
		logProbabilityValue$sample47();
		logProbabilityValue$sample52();
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
		logProbabilityValue$sample17();
		logProbabilityValue$sample20();
		logProbabilityValue$sample42();
		logProbabilityValue$sample47();
		logProbabilityValue$sample52();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, beta, p);
		if(!fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(RNG$, p, 10, prior);
		
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
		int cv$length1 = output.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			output[cv$index1] = observed[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model MultinomialBernoulli(boolean[] observed) {\n    double[] beta = {0.1, 0.1, 0.1};\n    double[] p = dirichlet(beta).sample();\n    int n = 10;\n    int[] prior = multinomial(p, n).sample();\n    Bernoulli b1 = new Bernoulli(prior[0]/n);\n    Bernoulli b2 = new Bernoulli(prior[1]/n);\n    Bernoulli b3 = new Bernoulli(prior[2]/n);\n    int length = observed.length;\n    boolean[] output = new boolean[length];\n    for(int i=0; i<length; i+=3)\n        output[i] = b1.sample();\n    for(int i=1; i<length; i+=3)\n        output[i] = b2.sample();\n    for(int i=2; i<length; i+=3)\n        output[i] = b3.sample();\n    output.observe(observed);\n}\n";
	}
}