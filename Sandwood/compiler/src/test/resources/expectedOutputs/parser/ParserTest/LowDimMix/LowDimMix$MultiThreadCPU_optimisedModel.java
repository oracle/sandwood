package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class LowDimMix$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements LowDimMix$CoreInterface {
	
	// Declare the variables for the model.
	private int N;
	private boolean[] component;
	private boolean[] constrainedFlag$sample101;
	private boolean[] constrainedFlag$sample20;
	private boolean[] constrainedFlag$sample83;
	private boolean constrainedFlag$sample88 = true;
	private double[][] cv$var97$stateProbabilityGlobal;
	private boolean fixedFlag$sample101 = false;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample83 = false;
	private boolean fixedFlag$sample88 = false;
	private boolean fixedProbFlag$sample101 = false;
	private boolean fixedProbFlag$sample138 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample83 = false;
	private boolean fixedProbFlag$sample88 = false;
	private boolean[][] guard$sample20if124$global;
	private int length$yObserved;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$component;
	private double logProbability$componentDistribution;
	private double logProbability$mu;
	private double logProbability$rawMu;
	private double[] logProbability$sample138;
	private double[] logProbability$sample20;
	private double logProbability$sigma;
	private double logProbability$theta;
	private double logProbability$var79;
	private double logProbability$var97;
	private double logProbability$y;
	private double[] mu;
	private double[] rawMu;
	private double[] sigma;
	private boolean system$gibbsForward = true;
	private double theta;
	private double[] y;
	private double[] yObserved;

	public LowDimMix$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for N.
	@Override
	public final int get$N() {
		return N;
	}

	// Getter for component.
	@Override
	public final boolean[] get$component() {
		return component;
	}

	// Setter for component.
	@Override
	public final void set$component(boolean[] cv$value, boolean allocated$) {
		// Set flags for all the side effects of component including if probabilities need
		// to be updated.
		component = cv$value;
		
		// Unset the fixed probability flag for sample 101 as it depends on component.
		fixedProbFlag$sample101 = false;
		
		// Unset the fixed probability flag for sample 138 as it depends on component.
		fixedProbFlag$sample138 = false;
	}

	// Getter for fixedFlag$sample101.
	@Override
	public final boolean get$fixedFlag$sample101() {
		return fixedFlag$sample101;
	}

	// Setter for fixedFlag$sample101.
	@Override
	public final void set$fixedFlag$sample101(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample101 including if probabilities
		// need to be updated.
		fixedFlag$sample101 = cv$value;
		
		// If the model has been allocated update the constraints flags
		if(allocated$) {
			// Set all the values in the array
			for(int index$constrainedFlag$sample101$1 = 0; index$constrainedFlag$sample101$1 < constrainedFlag$sample101.length; index$constrainedFlag$sample101$1 += 1)
				// Substituted "fixedFlag$sample101" with its value "cv$value".
				constrainedFlag$sample101[index$constrainedFlag$sample101$1] = cv$value;
		}
		
		// Should the probability of sample 101 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample101" with its value "cv$value".
		fixedProbFlag$sample101 = (cv$value && fixedProbFlag$sample101);
		
		// Should the probability of sample 138 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample101" with its value "cv$value".
		fixedProbFlag$sample138 = (cv$value && fixedProbFlag$sample138);
	}

	// Getter for fixedFlag$sample20.
	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	// Setter for fixedFlag$sample20.
	@Override
	public final void set$fixedFlag$sample20(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample20 including if probabilities
		// need to be updated.
		fixedFlag$sample20 = cv$value;
		
		// If the model has been allocated update the constraints flags
		if(allocated$) {
			// Set all the values in the array
			for(int index$constrainedFlag$sample20$1 = 0; index$constrainedFlag$sample20$1 < constrainedFlag$sample20.length; index$constrainedFlag$sample20$1 += 1)
				// Substituted "fixedFlag$sample20" with its value "cv$value".
				constrainedFlag$sample20[index$constrainedFlag$sample20$1] = cv$value;
		}
		
		// Should the probability of sample 20 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample20" with its value "cv$value".
		fixedProbFlag$sample20 = (cv$value && fixedProbFlag$sample20);
		
		// Should the probability of sample 138 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample20" with its value "cv$value".
		fixedProbFlag$sample138 = (cv$value && fixedProbFlag$sample138);
	}

	// Getter for fixedFlag$sample83.
	@Override
	public final boolean get$fixedFlag$sample83() {
		return fixedFlag$sample83;
	}

	// Setter for fixedFlag$sample83.
	@Override
	public final void set$fixedFlag$sample83(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample83 including if probabilities
		// need to be updated.
		fixedFlag$sample83 = cv$value;
		
		// If the model has been allocated update the constraints flags
		if(allocated$) {
			// Set all the values in the array
			for(int index$constrainedFlag$sample83$1 = 0; index$constrainedFlag$sample83$1 < constrainedFlag$sample83.length; index$constrainedFlag$sample83$1 += 1)
				// Substituted "fixedFlag$sample83" with its value "cv$value".
				constrainedFlag$sample83[index$constrainedFlag$sample83$1] = cv$value;
		}
		
		// Should the probability of sample 83 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample83" with its value "cv$value".
		fixedProbFlag$sample83 = (cv$value && fixedProbFlag$sample83);
		
		// Should the probability of sample 138 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample83" with its value "cv$value".
		fixedProbFlag$sample138 = (cv$value && fixedProbFlag$sample138);
	}

	// Getter for fixedFlag$sample88.
	@Override
	public final boolean get$fixedFlag$sample88() {
		return fixedFlag$sample88;
	}

	// Setter for fixedFlag$sample88.
	@Override
	public final void set$fixedFlag$sample88(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample88 including if probabilities
		// need to be updated.
		fixedFlag$sample88 = cv$value;
		
		// Substituted "fixedFlag$sample88" with its value "cv$value".
		constrainedFlag$sample88 = (cv$value || constrainedFlag$sample88);
		
		// Should the probability of sample 88 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample88" with its value "cv$value".
		fixedProbFlag$sample88 = (cv$value && fixedProbFlag$sample88);
		
		// Should the probability of sample 101 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample88" with its value "cv$value".
		fixedProbFlag$sample101 = (cv$value && fixedProbFlag$sample101);
	}

	// Getter for length$yObserved.
	@Override
	public final int get$length$yObserved() {
		return length$yObserved;
	}

	// Setter for length$yObserved.
	@Override
	public final void set$length$yObserved(int cv$value, boolean allocated$) {
		length$yObserved = cv$value;
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

	// Getter for logProbability$component.
	@Override
	public final double get$logProbability$component() {
		return logProbability$component;
	}

	// Getter for logProbability$componentDistribution.
	@Override
	public final double get$logProbability$componentDistribution() {
		return logProbability$componentDistribution;
	}

	// Getter for logProbability$mu.
	@Override
	public final double get$logProbability$mu() {
		return logProbability$mu;
	}

	// Getter for logProbability$rawMu.
	@Override
	public final double get$logProbability$rawMu() {
		return logProbability$rawMu;
	}

	// Getter for logProbability$sigma.
	@Override
	public final double get$logProbability$sigma() {
		return logProbability$sigma;
	}

	// Getter for logProbability$theta.
	@Override
	public final double get$logProbability$theta() {
		return logProbability$theta;
	}

	// Getter for logProbability$y.
	@Override
	public final double get$logProbability$y() {
		return logProbability$y;
	}

	// Getter for mu.
	@Override
	public final double[] get$mu() {
		return mu;
	}

	// Getter for rawMu.
	@Override
	public final double[] get$rawMu() {
		return rawMu;
	}

	// Setter for rawMu.
	@Override
	public final void set$rawMu(double[] cv$value, boolean allocated$) {
		// Set flags for all the side effects of rawMu including if probabilities need to
		// be updated.
		rawMu = cv$value;
		
		// Unset the fixed probability flag for sample 20 as it depends on rawMu.
		fixedProbFlag$sample20 = false;
		
		// Unset the fixed probability flag for sample 138 as it depends on rawMu.
		fixedProbFlag$sample138 = false;
	}

	// Getter for sigma.
	@Override
	public final double[] get$sigma() {
		return sigma;
	}

	// Setter for sigma.
	@Override
	public final void set$sigma(double[] cv$value, boolean allocated$) {
		// Set flags for all the side effects of sigma including if probabilities need to
		// be updated.
		sigma = cv$value;
		
		// Unset the fixed probability flag for sample 83 as it depends on sigma.
		fixedProbFlag$sample83 = false;
		
		// Unset the fixed probability flag for sample 138 as it depends on sigma.
		fixedProbFlag$sample138 = false;
	}

	// Getter for theta.
	@Override
	public final double get$theta() {
		return theta;
	}

	// Setter for theta.
	@Override
	public final void set$theta(double cv$value, boolean allocated$) {
		// Set flags for all the side effects of theta including if probabilities need to
		// be updated.
		theta = cv$value;
		
		// Unset the fixed probability flag for sample 88 as it depends on theta.
		fixedProbFlag$sample88 = false;
		
		// Unset the fixed probability flag for sample 101 as it depends on theta.
		fixedProbFlag$sample101 = false;
	}

	// Getter for y.
	@Override
	public final double[] get$y() {
		return y;
	}

	// Getter for yObserved.
	@Override
	public final double[] get$yObserved() {
		return yObserved;
	}

	// Setter for yObserved.
	@Override
	public final void set$yObserved(double[] cv$value, boolean allocated$) {
		yObserved = cv$value;
	}

	// Pick a value from the distribution for the unconditioned variable from sample101
	private final void drawValueSample101(int var96, int threadID$cv$var96, Rng RNG$) {
		component[var96] = DistributionSampling.sampleBernoulli(RNG$, theta);
	}

	// Pick a value from the distribution for the unconditioned variable from sample20
	private final void drawValueSample20(int var19, int threadID$cv$var19, Rng RNG$) {
		rawMu[var19] = (DistributionSampling.sampleGaussian(RNG$) * 2.0);
		
		// Guards to ensure that mu is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 20 and consumer double[] 41.
		// 
		// Guard to check that at most one copy of the code is executed for a given set of
		// loop iterations.
		boolean guard$sample20put43 = false;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var19 == 0)) {
			// The body will execute, so should not be executed again
			guard$sample20put43 = true;
			double var39;
			if((rawMu[0] < rawMu[1]))
				var39 = rawMu[0];
			else
				var39 = rawMu[1];
			mu[0] = var39;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(((var19 == 1) && !guard$sample20put43)) {
			// The body will execute, so should not be executed again
			guard$sample20put43 = true;
			double var39;
			if((rawMu[0] < rawMu[1]))
				var39 = rawMu[0];
			else
				var39 = rawMu[1];
			mu[0] = var39;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((((rawMu[0] < rawMu[1]) && (var19 == 0)) && !guard$sample20put43)) {
			// The body will execute, so should not be executed again
			guard$sample20put43 = true;
			mu[0] = rawMu[0];
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(((!(rawMu[0] < rawMu[1]) && (var19 == 1)) && !guard$sample20put43))
			mu[0] = rawMu[1];
		
		// Guards to ensure that mu is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 20 and consumer double[] 59.
		// 
		// Guard to check that at most one copy of the code is executed for a given set of
		// loop iterations.
		boolean guard$sample20put63 = false;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var19 == 0)) {
			// The body will execute, so should not be executed again
			guard$sample20put63 = true;
			double var57;
			if((rawMu[0] < rawMu[1]))
				var57 = rawMu[1];
			else
				var57 = rawMu[0];
			mu[1] = var57;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var19 == 1)) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!guard$sample20put63) {
				// The body will execute, so should not be executed again
				guard$sample20put63 = true;
				double var57;
				if((rawMu[0] < rawMu[1]))
					var57 = rawMu[1];
				else
					var57 = rawMu[0];
				mu[1] = var57;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((rawMu[0] < rawMu[1]) && !guard$sample20put63)) {
				// The body will execute, so should not be executed again
				guard$sample20put63 = true;
				mu[1] = rawMu[1];
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(((!(rawMu[0] < rawMu[1]) && (var19 == 0)) && !guard$sample20put63))
			mu[1] = rawMu[0];
	}

	// Pick a value from the distribution for the unconditioned variable from sample83
	private final void drawValueSample83(int var78) {
		sigma[var78] = (DistributionSampling.sampleTruncatedGaussian(RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample88
	private final void drawValueSample88() {
		theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 101 drawn from componentDistribution. Inference was performed using
	// variable marginalization.
	private final void inferSample101(int var96, int threadID$cv$var96, Rng RNG$) {
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var97$stateProbabilityGlobal[threadID$cv$var96];
		{
			// Guards to ensure that component is only updated when there is a valid path.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			component[var96] = false;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= theta) && (theta <= 1.0))?Math.log((1.0 - theta)):Double.NEGATIVE_INFINITY);
			
			// Processing conditional point124.
			// 
			// Looking for a path between Sample 101 and consumer double 118.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(component[var96]) {
				{
					// Variable declaration of componentSigma moved.
					double componentSigma = sigma[0];
					
					// Constructing a random variable input for use later.
					double var128 = (componentSigma * componentSigma);
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 138 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "n" with its value "var96".
					cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[var96] - mu[0]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				boolean[] guard$sample20if124 = guard$sample20if124$global[threadID$cv$var96];
				
				// Unrolled loop
				// 
				// Set the flags to false
				guard$sample20if124[0] = false;
				
				// Unrolled loop
				// 
				// Set the flags to false
				guard$sample20if124[1] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((rawMu[0] < rawMu[1]))
					// Unrolled loop
					// 
					// Set the flags to false
					guard$sample20if124[0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else
					// Unrolled loop
					// 
					// Set the flags to false
					guard$sample20if124[1] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample20if124[0]) {
					// Unrolled loop
					// The body will execute, so should not be executed again
					guard$sample20if124[0] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// This value is not used before it is set again, so removing the value declaration.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample20if124[1]) {
					// Unrolled loop
					// The body will execute, so should not be executed again
					guard$sample20if124[1] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// This value is not used before it is set again, so removing the value declaration.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((rawMu[0] < rawMu[1]) && !guard$sample20if124[0])) {
					// Unrolled loop
					// The body will execute, so should not be executed again
					guard$sample20if124[0] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// This value is not used before it is set again, so removing the value declaration.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((!(rawMu[0] < rawMu[1]) && !guard$sample20if124[1])) {
					// The body will execute, so should not be executed again
					guard$sample20if124[1] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// This value is not used before it is set again, so removing the value declaration.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				double traceTempVariable$componentSigma$58_1 = sigma[0];
				
				// Constructing a random variable input for use later.
				double var128 = (traceTempVariable$componentSigma$58_1 * traceTempVariable$componentSigma$58_1);
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 138 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "n" with its value "var96".
				// 
				// Substituted "componentMu" with its value "mu[0]".
				cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[var96] - mu[0]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 83 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Looking for a path between Sample 83 and consumer double 127.
				// 
				// Unrolled loop
				// 
				// Processing sample task 83 of consumer random variable null.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = ((((0.0 <= sigma[0]) && (sigma[0] <= 1.0E100))?DistributionSampling.logProbabilityGaussian((sigma[0] / 2.0)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				{
					// Variable declaration of componentSigma moved.
					double componentSigma = sigma[1];
					
					// Constructing a random variable input for use later.
					double var128 = (componentSigma * componentSigma);
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 138 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "n" with its value "var96".
					cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[var96] - mu[1]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				
				// Looking for a path between Sample 20 and consumer double 118.
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				boolean[] guard$sample20if124 = guard$sample20if124$global[threadID$cv$var96];
				
				// Unrolled loop
				// 
				// Set the flags to false
				guard$sample20if124[0] = false;
				
				// Unrolled loop
				// 
				// Set the flags to false
				guard$sample20if124[1] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((rawMu[0] < rawMu[1]))
					// Unrolled loop
					// 
					// Set the flags to false
					guard$sample20if124[1] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else
					// Unrolled loop
					// 
					// Set the flags to false
					guard$sample20if124[0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample20if124[0]) {
					// Unrolled loop
					// The body will execute, so should not be executed again
					guard$sample20if124[0] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// This value is not used before it is set again, so removing the value declaration.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample20if124[1]) {
					// Unrolled loop
					// The body will execute, so should not be executed again
					guard$sample20if124[1] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// This value is not used before it is set again, so removing the value declaration.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((rawMu[0] < rawMu[1]) && !guard$sample20if124[1])) {
					// Unrolled loop
					// The body will execute, so should not be executed again
					guard$sample20if124[1] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// This value is not used before it is set again, so removing the value declaration.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((!(rawMu[0] < rawMu[1]) && !guard$sample20if124[0])) {
					// The body will execute, so should not be executed again
					guard$sample20if124[0] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// This value is not used before it is set again, so removing the value declaration.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				double traceTempVariable$componentSigma$63_1 = sigma[1];
				
				// Constructing a random variable input for use later.
				double var128 = (traceTempVariable$componentSigma$63_1 * traceTempVariable$componentSigma$63_1);
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 138 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "n" with its value "var96".
				// 
				// Substituted "componentMu" with its value "mu[1]".
				cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[var96] - mu[1]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 83 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Processing sample task 83 of consumer random variable null.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = ((((0.0 <= sigma[1]) && (sigma[1] <= 1.0E100))?DistributionSampling.logProbabilityGaussian((sigma[1] / 2.0)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$stateProbabilityLocal[0] = cv$accumulatedProbabilities;
		}
		
		// Guards to ensure that component is only updated when there is a valid path.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		component[var96] = true;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= theta) && (theta <= 1.0))?Math.log(theta):Double.NEGATIVE_INFINITY);
		
		// Processing conditional point124.
		// 
		// Looking for a path between Sample 101 and consumer double 118.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(component[var96]) {
			{
				// Variable declaration of componentSigma moved.
				double componentSigma = sigma[0];
				
				// Constructing a random variable input for use later.
				double var128 = (componentSigma * componentSigma);
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 138 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "n" with its value "var96".
				cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[var96] - mu[0]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			boolean[] guard$sample20if124 = guard$sample20if124$global[threadID$cv$var96];
			
			// Unrolled loop
			// 
			// Set the flags to false
			guard$sample20if124[0] = false;
			
			// Unrolled loop
			// 
			// Set the flags to false
			guard$sample20if124[1] = false;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((rawMu[0] < rawMu[1]))
				// Unrolled loop
				// 
				// Set the flags to false
				guard$sample20if124[0] = false;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else
				// Unrolled loop
				// 
				// Set the flags to false
				guard$sample20if124[1] = false;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!guard$sample20if124[0]) {
				// Unrolled loop
				// The body will execute, so should not be executed again
				guard$sample20if124[0] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 20 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// This value is not used before it is set again, so removing the value declaration.
				// 
				// Processing sample task 20 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!guard$sample20if124[1]) {
				// Unrolled loop
				// The body will execute, so should not be executed again
				guard$sample20if124[1] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 20 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// This value is not used before it is set again, so removing the value declaration.
				// 
				// Processing sample task 20 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((rawMu[0] < rawMu[1]) && !guard$sample20if124[0])) {
				// Unrolled loop
				// The body will execute, so should not be executed again
				guard$sample20if124[0] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 20 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// This value is not used before it is set again, so removing the value declaration.
				// 
				// Processing sample task 20 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!(rawMu[0] < rawMu[1]) && !guard$sample20if124[1])) {
				// The body will execute, so should not be executed again
				guard$sample20if124[1] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 20 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// This value is not used before it is set again, so removing the value declaration.
				// 
				// Processing sample task 20 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			double traceTempVariable$componentSigma$58_1 = sigma[0];
			
			// Mark that the sample has observed constrained data.
			constrainedFlag$sample101[var96] = true;
			
			// Constructing a random variable input for use later.
			double var128 = (traceTempVariable$componentSigma$58_1 * traceTempVariable$componentSigma$58_1);
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 138 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "n" with its value "var96".
			// 
			// Substituted "componentMu" with its value "mu[0]".
			cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[var96] - mu[0]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 83 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Looking for a path between Sample 83 and consumer double 127.
			// 
			// Unrolled loop
			// 
			// Processing sample task 83 of consumer random variable null.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Constructing a random variable input for use later.
			cv$accumulatedProbabilities = ((((0.0 <= sigma[0]) && (sigma[0] <= 1.0E100))?DistributionSampling.logProbabilityGaussian((sigma[0] / 2.0)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			{
				// Variable declaration of componentSigma moved.
				double componentSigma = sigma[1];
				
				// Constructing a random variable input for use later.
				double var128 = (componentSigma * componentSigma);
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 138 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "n" with its value "var96".
				cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[var96] - mu[1]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Looking for a path between Sample 20 and consumer double 118.
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			boolean[] guard$sample20if124 = guard$sample20if124$global[threadID$cv$var96];
			
			// Unrolled loop
			// 
			// Set the flags to false
			guard$sample20if124[0] = false;
			
			// Unrolled loop
			// 
			// Set the flags to false
			guard$sample20if124[1] = false;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((rawMu[0] < rawMu[1]))
				// Unrolled loop
				// 
				// Set the flags to false
				guard$sample20if124[1] = false;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else
				// Unrolled loop
				// 
				// Set the flags to false
				guard$sample20if124[0] = false;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!guard$sample20if124[0]) {
				// Unrolled loop
				// The body will execute, so should not be executed again
				guard$sample20if124[0] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 20 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// This value is not used before it is set again, so removing the value declaration.
				// 
				// Processing sample task 20 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!guard$sample20if124[1]) {
				// Unrolled loop
				// The body will execute, so should not be executed again
				guard$sample20if124[1] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 20 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// This value is not used before it is set again, so removing the value declaration.
				// 
				// Processing sample task 20 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((rawMu[0] < rawMu[1]) && !guard$sample20if124[1])) {
				// Unrolled loop
				// The body will execute, so should not be executed again
				guard$sample20if124[1] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 20 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// This value is not used before it is set again, so removing the value declaration.
				// 
				// Processing sample task 20 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!(rawMu[0] < rawMu[1]) && !guard$sample20if124[0])) {
				// The body will execute, so should not be executed again
				guard$sample20if124[0] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 20 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// This value is not used before it is set again, so removing the value declaration.
				// 
				// Processing sample task 20 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			double traceTempVariable$componentSigma$63_1 = sigma[1];
			
			// Mark that the sample has observed constrained data.
			constrainedFlag$sample101[var96] = true;
			
			// Constructing a random variable input for use later.
			double var128 = (traceTempVariable$componentSigma$63_1 * traceTempVariable$componentSigma$63_1);
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 138 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "n" with its value "var96".
			// 
			// Substituted "componentMu" with its value "mu[1]".
			cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[var96] - mu[1]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 83 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Processing sample task 83 of consumer random variable null.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Constructing a random variable input for use later.
			cv$accumulatedProbabilities = ((((0.0 <= sigma[1]) && (sigma[1] <= 1.0E100))?DistributionSampling.logProbabilityGaussian((sigma[1] / 2.0)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$stateProbabilityLocal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample101[var96]) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			double cv$lseMax = cv$stateProbabilityLocal[0];
			
			// Unrolled loop
			double cv$lseElementValue = cv$stateProbabilityLocal[1];
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
				// Initialize the sum of the array elements
				cv$logSum = (Math.log((Math.exp((cv$stateProbabilityLocal[0] - cv$lseMax)) + Math.exp((cv$stateProbabilityLocal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
				// cv$numStates's comment
				// variable marginalization
				cv$stateProbabilityLocal[0] = 0.5;
				
				// cv$numStates's comment
				// variable marginalization
				cv$stateProbabilityLocal[1] = 0.5;
			} else {
				// Unrolled loop
				cv$stateProbabilityLocal[0] = Math.exp((cv$stateProbabilityLocal[0] - cv$logSum));
				cv$stateProbabilityLocal[1] = Math.exp((cv$stateProbabilityLocal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// cv$numStates's comment
			// variable marginalization
			for(int cv$indexName = 2; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Guards to ensure that component is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			// 
			// cv$numStates's comment
			// variable marginalization
			component[var96] = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 2) == 1);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 20 drawn from Gaussian 7. Inference was performed using Metropolis-Hastings.
	private final void inferSample20(int var19, int threadID$cv$var19, Rng RNG$) {
		constrainedFlag$sample20[var19] = false;
		
		// The original value of the sample
		double cv$originalValue = rawMu[var19];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Constructing a random variable input for use later.
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 2.0)) - 0.6931471805599453);
			
			// Looking for a path between Sample 20 and consumer Gaussian 129.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((rawMu[0] < rawMu[1]) && (var19 == 0))) {
				for(int n = 0; n < N; n += 1) {
					if(component[n]) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample20[0] = true;
						
						// Variable declaration of componentSigma moved.
						double componentSigma = sigma[0];
						
						// Constructing a random variable input for use later.
						double var128 = (componentSigma * componentSigma);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 138 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Set the current value to the current state of the tree.
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - cv$originalValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var19 == 1)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((rawMu[0] < rawMu[1])) {
					for(int n = 0; n < N; n += 1) {
						if(!component[n]) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample20[1] = true;
							
							// Variable declaration of componentSigma moved.
							double componentSigma = sigma[1];
							
							// Constructing a random variable input for use later.
							double var128 = (componentSigma * componentSigma);
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 138 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - cv$originalValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
				}
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					for(int n = 0; n < N; n += 1) {
						if(component[n]) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample20[1] = true;
							
							// Variable declaration of componentSigma moved.
							double componentSigma = sigma[0];
							
							// Constructing a random variable input for use later.
							double var128 = (componentSigma * componentSigma);
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 138 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - cv$originalValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!(rawMu[0] < rawMu[1]) && (var19 == 0))) {
				for(int n = 0; n < N; n += 1) {
					if(!component[n]) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample20[0] = true;
						
						// Variable declaration of componentSigma moved.
						double componentSigma = sigma[1];
						
						// Constructing a random variable input for use later.
						double var128 = (componentSigma * componentSigma);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 138 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Set the current value to the current state of the tree.
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - cv$originalValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Processing conditional point41.
			// 
			// Looking for a path between Sample 20 and consumer double 39.
			// 
			// Guard to check that at most one copy of the code is executed for a given set of
			// loop iterations.
			boolean guard$sample20if41 = false;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var19 == 0)) {
				// The body will execute, so should not be executed again
				guard$sample20if41 = true;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((rawMu[0] < rawMu[1])) {
					double traceTempVariable$var115$36_2 = rawMu[0];
					for(int n = 0; n < N; n += 1) {
						if(component[n]) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample20[0] = true;
							
							// Variable declaration of componentSigma moved.
							double componentSigma = sigma[0];
							
							// Constructing a random variable input for use later.
							double var128 = (componentSigma * componentSigma);
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 138 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var115$36_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					double traceTempVariable$var115$52_2 = rawMu[1];
					for(int n = 0; n < N; n += 1) {
						if(component[n]) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample20[0] = true;
							
							// Variable declaration of componentSigma moved.
							double componentSigma = sigma[0];
							
							// Constructing a random variable input for use later.
							double var128 = (componentSigma * componentSigma);
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 138 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var115$52_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((var19 == 1) && !guard$sample20if41)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((rawMu[0] < rawMu[1])) {
					double traceTempVariable$var115$37_2 = rawMu[0];
					for(int n = 0; n < N; n += 1) {
						if(component[n]) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample20[1] = true;
							
							// Variable declaration of componentSigma moved.
							double componentSigma = sigma[0];
							
							// Constructing a random variable input for use later.
							double var128 = (componentSigma * componentSigma);
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 138 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var115$37_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					double traceTempVariable$var115$53_2 = rawMu[1];
					for(int n = 0; n < N; n += 1) {
						if(component[n]) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample20[1] = true;
							
							// Variable declaration of componentSigma moved.
							double componentSigma = sigma[0];
							
							// Constructing a random variable input for use later.
							double var128 = (componentSigma * componentSigma);
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 138 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var115$53_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			
			// Processing conditional point61.
			// 
			// Looking for a path between Sample 20 and consumer double 57.
			// 
			// Guard to check that at most one copy of the code is executed for a given set of
			// loop iterations.
			boolean guard$sample20if61 = false;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var19 == 0)) {
				// The body will execute, so should not be executed again
				guard$sample20if61 = true;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((rawMu[0] < rawMu[1])) {
					double traceTempVariable$var117$72_2 = rawMu[1];
					for(int n = 0; n < N; n += 1) {
						if(!component[n]) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample20[0] = true;
							
							// Variable declaration of componentSigma moved.
							double componentSigma = sigma[1];
							
							// Constructing a random variable input for use later.
							double var128 = (componentSigma * componentSigma);
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 138 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var117$72_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					double traceTempVariable$var117$88_2 = rawMu[0];
					for(int n = 0; n < N; n += 1) {
						if(!component[n]) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample20[0] = true;
							
							// Variable declaration of componentSigma moved.
							double componentSigma = sigma[1];
							
							// Constructing a random variable input for use later.
							double var128 = (componentSigma * componentSigma);
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 138 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var117$88_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((var19 == 1) && !guard$sample20if61)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((rawMu[0] < rawMu[1])) {
					double traceTempVariable$var117$73_2 = rawMu[1];
					for(int n = 0; n < N; n += 1) {
						if(!component[n]) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample20[1] = true;
							
							// Variable declaration of componentSigma moved.
							double componentSigma = sigma[1];
							
							// Constructing a random variable input for use later.
							double var128 = (componentSigma * componentSigma);
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 138 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var117$73_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					double traceTempVariable$var117$89_2 = rawMu[0];
					for(int n = 0; n < N; n += 1) {
						if(!component[n]) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample20[1] = true;
							
							// Variable declaration of componentSigma moved.
							double componentSigma = sigma[1];
							
							// Constructing a random variable input for use later.
							double var128 = (componentSigma * componentSigma);
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 138 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var117$89_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(constrainedFlag$sample20[var19]) {
			{
				// Guards to ensure that rawMu is only updated when there is a valid path.
				rawMu[var19] = cv$proposedValue;
				
				// Guards to ensure that mu is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 20 and consumer double[] 41.
				// 
				// Guard to check that at most one copy of the code is executed for a given set of
				// loop iterations.
				boolean guard$sample20put43 = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var19 == 0)) {
					// The body will execute, so should not be executed again
					guard$sample20put43 = true;
					double var39;
					if((rawMu[0] < rawMu[1]))
						var39 = rawMu[0];
					else
						var39 = rawMu[1];
					mu[0] = var39;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((var19 == 1) && !guard$sample20put43)) {
					// The body will execute, so should not be executed again
					guard$sample20put43 = true;
					double var39;
					if((rawMu[0] < rawMu[1]))
						var39 = rawMu[0];
					else
						var39 = rawMu[1];
					mu[0] = var39;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((((rawMu[0] < rawMu[1]) && (var19 == 0)) && !guard$sample20put43)) {
					// The body will execute, so should not be executed again
					guard$sample20put43 = true;
					mu[0] = rawMu[0];
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((!(rawMu[0] < rawMu[1]) && (var19 == 1)) && !guard$sample20put43))
					mu[0] = rawMu[1];
				
				// Guards to ensure that mu is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 20 and consumer double[] 59.
				// 
				// Guard to check that at most one copy of the code is executed for a given set of
				// loop iterations.
				boolean guard$sample20put63 = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var19 == 0)) {
					// The body will execute, so should not be executed again
					guard$sample20put63 = true;
					double var57;
					if((rawMu[0] < rawMu[1]))
						var57 = rawMu[1];
					else
						var57 = rawMu[0];
					mu[1] = var57;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var19 == 1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample20put63) {
						// The body will execute, so should not be executed again
						guard$sample20put63 = true;
						double var57;
						if((rawMu[0] < rawMu[1]))
							var57 = rawMu[1];
						else
							var57 = rawMu[0];
						mu[1] = var57;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(((rawMu[0] < rawMu[1]) && !guard$sample20put63)) {
						// The body will execute, so should not be executed again
						guard$sample20put63 = true;
						mu[1] = rawMu[1];
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((!(rawMu[0] < rawMu[1]) && (var19 == 0)) && !guard$sample20put63))
					mu[1] = rawMu[0];
			}
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Constructing a random variable input for use later.
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 2.0)) - 0.6931471805599453);
			
			// Looking for a path between Sample 20 and consumer Gaussian 129.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((rawMu[0] < rawMu[1]) && (var19 == 0))) {
				for(int n = 0; n < N; n += 1) {
					if(component[n]) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample20[0] = true;
						
						// Variable declaration of componentSigma moved.
						double componentSigma = sigma[0];
						
						// Constructing a random variable input for use later.
						double var128 = (componentSigma * componentSigma);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 138 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - cv$proposedValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var19 == 1)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((rawMu[0] < rawMu[1])) {
					for(int n = 0; n < N; n += 1) {
						if(!component[n]) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample20[1] = true;
							
							// Variable declaration of componentSigma moved.
							double componentSigma = sigma[1];
							
							// Constructing a random variable input for use later.
							double var128 = (componentSigma * componentSigma);
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 138 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - cv$proposedValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
				}
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					for(int n = 0; n < N; n += 1) {
						if(component[n]) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample20[1] = true;
							
							// Variable declaration of componentSigma moved.
							double componentSigma = sigma[0];
							
							// Constructing a random variable input for use later.
							double var128 = (componentSigma * componentSigma);
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 138 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - cv$proposedValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!(rawMu[0] < rawMu[1]) && (var19 == 0))) {
				for(int n = 0; n < N; n += 1) {
					if(!component[n]) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample20[0] = true;
						
						// Variable declaration of componentSigma moved.
						double componentSigma = sigma[1];
						
						// Constructing a random variable input for use later.
						double var128 = (componentSigma * componentSigma);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 138 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - cv$proposedValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Processing conditional point41.
			// 
			// Looking for a path between Sample 20 and consumer double 39.
			// 
			// Guard to check that at most one copy of the code is executed for a given set of
			// loop iterations.
			boolean guard$sample20if41 = false;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var19 == 0)) {
				// The body will execute, so should not be executed again
				guard$sample20if41 = true;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((rawMu[0] < rawMu[1])) {
					double traceTempVariable$var115$36_2 = rawMu[0];
					for(int n = 0; n < N; n += 1) {
						if(component[n]) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample20[0] = true;
							
							// Variable declaration of componentSigma moved.
							double componentSigma = sigma[0];
							
							// Constructing a random variable input for use later.
							double var128 = (componentSigma * componentSigma);
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 138 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var115$36_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					double traceTempVariable$var115$52_2 = rawMu[1];
					for(int n = 0; n < N; n += 1) {
						if(component[n]) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample20[0] = true;
							
							// Variable declaration of componentSigma moved.
							double componentSigma = sigma[0];
							
							// Constructing a random variable input for use later.
							double var128 = (componentSigma * componentSigma);
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 138 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var115$52_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((var19 == 1) && !guard$sample20if41)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((rawMu[0] < rawMu[1])) {
					double traceTempVariable$var115$37_2 = rawMu[0];
					for(int n = 0; n < N; n += 1) {
						if(component[n]) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample20[1] = true;
							
							// Variable declaration of componentSigma moved.
							double componentSigma = sigma[0];
							
							// Constructing a random variable input for use later.
							double var128 = (componentSigma * componentSigma);
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 138 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var115$37_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					double traceTempVariable$var115$53_2 = rawMu[1];
					for(int n = 0; n < N; n += 1) {
						if(component[n]) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample20[1] = true;
							
							// Variable declaration of componentSigma moved.
							double componentSigma = sigma[0];
							
							// Constructing a random variable input for use later.
							double var128 = (componentSigma * componentSigma);
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 138 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var115$53_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			
			// Processing conditional point61.
			// 
			// Looking for a path between Sample 20 and consumer double 57.
			// 
			// Guard to check that at most one copy of the code is executed for a given set of
			// loop iterations.
			boolean guard$sample20if61 = false;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var19 == 0)) {
				// The body will execute, so should not be executed again
				guard$sample20if61 = true;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((rawMu[0] < rawMu[1])) {
					double traceTempVariable$var117$72_2 = rawMu[1];
					for(int n = 0; n < N; n += 1) {
						if(!component[n]) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample20[0] = true;
							
							// Variable declaration of componentSigma moved.
							double componentSigma = sigma[1];
							
							// Constructing a random variable input for use later.
							double var128 = (componentSigma * componentSigma);
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 138 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var117$72_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					double traceTempVariable$var117$88_2 = rawMu[0];
					for(int n = 0; n < N; n += 1) {
						if(!component[n]) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample20[0] = true;
							
							// Variable declaration of componentSigma moved.
							double componentSigma = sigma[1];
							
							// Constructing a random variable input for use later.
							double var128 = (componentSigma * componentSigma);
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 138 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var117$88_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((var19 == 1) && !guard$sample20if61)) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((rawMu[0] < rawMu[1])) {
					double traceTempVariable$var117$73_2 = rawMu[1];
					for(int n = 0; n < N; n += 1) {
						if(!component[n]) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample20[1] = true;
							
							// Variable declaration of componentSigma moved.
							double componentSigma = sigma[1];
							
							// Constructing a random variable input for use later.
							double var128 = (componentSigma * componentSigma);
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 138 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var117$73_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					double traceTempVariable$var117$89_2 = rawMu[0];
					for(int n = 0; n < N; n += 1) {
						if(!component[n]) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample20[1] = true;
							
							// Variable declaration of componentSigma moved.
							double componentSigma = sigma[1];
							
							// Constructing a random variable input for use later.
							double var128 = (componentSigma * componentSigma);
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 138 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var117$89_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 20 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Processing sample task 20 of consumer random variable null.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			
			// The probability ration for the proposed value and the current value.
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			
			// Test if the probability of the sample is sufficient to keep the value. This needs
			// to be less than or equal as otherwise if the proposed value is not possible and
			// the random value is 0 an impossible value will be accepted.
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio))) {
				// If it is not revert the changes.
				// 
				// Set the sample value
				// Guards to ensure that rawMu is only updated when there is a valid path.
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				rawMu[var19] = cv$originalValue;
				
				// Guards to ensure that mu is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 20 and consumer double[] 41.
				// 
				// Guard to check that at most one copy of the code is executed for a given set of
				// loop iterations.
				boolean guard$sample20put43 = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var19 == 0)) {
					// The body will execute, so should not be executed again
					guard$sample20put43 = true;
					double var39;
					if((rawMu[0] < rawMu[1]))
						var39 = rawMu[0];
					else
						var39 = rawMu[1];
					mu[0] = var39;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((var19 == 1) && !guard$sample20put43)) {
					// The body will execute, so should not be executed again
					guard$sample20put43 = true;
					double var39;
					if((rawMu[0] < rawMu[1]))
						var39 = rawMu[0];
					else
						var39 = rawMu[1];
					mu[0] = var39;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((((rawMu[0] < rawMu[1]) && (var19 == 0)) && !guard$sample20put43)) {
					// The body will execute, so should not be executed again
					guard$sample20put43 = true;
					mu[0] = rawMu[0];
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((!(rawMu[0] < rawMu[1]) && (var19 == 1)) && !guard$sample20put43))
					mu[0] = rawMu[1];
				
				// Guards to ensure that mu is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 20 and consumer double[] 59.
				// 
				// Guard to check that at most one copy of the code is executed for a given set of
				// loop iterations.
				boolean guard$sample20put63 = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var19 == 0)) {
					// The body will execute, so should not be executed again
					guard$sample20put63 = true;
					double var57;
					if((rawMu[0] < rawMu[1]))
						var57 = rawMu[1];
					else
						var57 = rawMu[0];
					mu[1] = var57;
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var19 == 1)) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample20put63) {
						// The body will execute, so should not be executed again
						guard$sample20put63 = true;
						double var57;
						if((rawMu[0] < rawMu[1]))
							var57 = rawMu[1];
						else
							var57 = rawMu[0];
						mu[1] = var57;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(((rawMu[0] < rawMu[1]) && !guard$sample20put63)) {
						// The body will execute, so should not be executed again
						guard$sample20put63 = true;
						mu[1] = rawMu[1];
					}
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((!(rawMu[0] < rawMu[1]) && (var19 == 0)) && !guard$sample20put63))
					mu[1] = rawMu[0];
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 83 drawn from TruncatedGaussian 66. Inference was performed using
	// Metropolis-Hastings.
	private final void inferSample83(int var78) {
		constrainedFlag$sample83[var78] = false;
		
		// The original value of the sample
		double cv$originalValue = sigma[var78];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Constructing a random variable input for use later.
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue <= 1.0E100))?DistributionSampling.logProbabilityGaussian((cv$originalValue / 2.0)):Double.NEGATIVE_INFINITY);
			
			// Looking for a path between Sample 83 and consumer Gaussian 129.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var78 == 0)) {
				for(int n = 0; n < N; n += 1) {
					if(component[n]) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample83[0] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Set the current value to the current state of the tree.
						double var128 = (cv$originalValue * cv$originalValue);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 138 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "componentMu" with its value "mu[0]".
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - mu[0]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var78 == 1)) {
				for(int n = 0; n < N; n += 1) {
					if(!component[n]) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample83[1] = true;
						
						// Constructing a random variable input for use later.
						// 
						// Set the current value to the current state of the tree.
						double var128 = (cv$originalValue * cv$originalValue);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 138 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "componentMu" with its value "mu[1]".
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - mu[1]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(constrainedFlag$sample83[var78]) {
			// Guards to ensure that sigma is only updated when there is a valid path.
			sigma[var78] = cv$proposedValue;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Constructing a random variable input for use later.
			double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0E100))?DistributionSampling.logProbabilityGaussian((cv$proposedValue / 2.0)):Double.NEGATIVE_INFINITY);
			
			// Looking for a path between Sample 83 and consumer Gaussian 129.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var78 == 0)) {
				for(int n = 0; n < N; n += 1) {
					if(component[n]) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample83[0] = true;
						
						// Constructing a random variable input for use later.
						double var128 = (cv$proposedValue * cv$proposedValue);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 138 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "componentMu" with its value "mu[0]".
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - mu[0]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var78 == 1)) {
				for(int n = 0; n < N; n += 1) {
					if(!component[n]) {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample83[1] = true;
						
						// Constructing a random variable input for use later.
						double var128 = (cv$proposedValue * cv$proposedValue);
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 138 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "componentMu" with its value "mu[1]".
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - mu[1]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// The probability ration for the proposed value and the current value.
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			
			// Test if the probability of the sample is sufficient to keep the value. This needs
			// to be less than or equal as otherwise if the proposed value is not possible and
			// the random value is 0 an impossible value will be accepted.
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				// If it is not revert the changes.
				// 
				// Set the sample value
				// 
				// Guards to ensure that sigma is only updated when there is a valid path.
				// 
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				sigma[var78] = cv$originalValue;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 88 drawn from Beta 83. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void inferSample88() {
		constrainedFlag$sample88 = false;
		
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		
		// Processing random variable 85.
		// 
		// Processing sample task 101 of consumer random variable componentDistribution.
		for(int var96 = 0; var96 < N; var96 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample101 || constrainedFlag$sample101[var96])) {
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample88 = true;
				
				// Include the value sampled by task 101 from random variable componentDistribution.
				// 
				// Increment the number of samples.
				cv$count = (cv$count + 1);
				
				// If the sample value was positive increase the count
				if(component[var96])
					cv$sum = (cv$sum + 1);
			}
		}
		if(constrainedFlag$sample88)
			// Write out the new value of the sample.
			theta = Conjugates.sampleConjugateBetaBinomial(RNG$, 5.0, 5.0, cv$sum, cv$count);
	}

	// Calculate the probability of the samples represented by sample101 using sampled
	// values.
	private final void logProbabilityValue$sample101() {
		// Determine if we need to calculate the values for sample task 101 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample101) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var96 = 0; var96 < N; var96 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= theta) && (theta <= 1.0))?Math.log((component[var96]?theta:(1.0 - theta))):Double.NEGATIVE_INFINITY));
			logProbability$componentDistribution = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var97 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$component = (logProbability$component + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample101)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample101 = (fixedFlag$sample101 && fixedFlag$sample88);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$componentDistribution = logProbability$var97;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$component = (logProbability$component + logProbability$var97);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var97);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample101)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var97);
		}
	}

	// Calculate the probability of the samples represented by sample138 using sampled
	// values.
	private final void logProbabilityValue$sample138() {
		// Determine if we need to calculate the values for sample task 138 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample138) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int n = 0; n < N; n += 1) {
				double componentMu;
				if(component[n])
					componentMu = mu[0];
				else
					componentMu = mu[1];
				double componentSigma;
				if(component[n])
					componentSigma = sigma[0];
				else
					componentSigma = sigma[1];
				double var128 = (componentSigma * componentSigma);
				
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
				double cv$distributionAccumulator = ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample138[n] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$y = (logProbability$y + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample138 = ((fixedFlag$sample20 && fixedFlag$sample83) && fixedFlag$sample101);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int n = 0; n < N; n += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample138[n]);
			
			// Update the variable probability
			logProbability$y = (logProbability$y + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample20 using sampled
	// values.
	private final void logProbabilityValue$sample20() {
		// Determine if we need to calculate the values for sample task 20 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample20) {
			// Generating probabilities for sample task
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator;
			{
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) - 0.6931471805599453);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				cv$sampleAccumulator = cv$weightedProbability;
				
				// Store the sample task probability
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				logProbability$sample20[0] = cv$weightedProbability;
				
				// Guard to ensure that mu is only updated once for this probability.
				boolean cv$guard$mu = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 20 and consumer double[] 41.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((rawMu[0] < rawMu[1])) {
					// Set the guard so the update is only applied once.
					cv$guard$mu = true;
					
					// Update the variable probability
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					logProbability$mu = (logProbability$mu + cv$weightedProbability);
				}
				
				// Looking for a path between Sample 20 and consumer double[] 59.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((!(rawMu[0] < rawMu[1]) && !cv$guard$mu))
					// Update the variable probability
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					logProbability$mu = (logProbability$mu + cv$weightedProbability);
			}
			
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) - 0.6931471805599453);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$weightedProbability);
			
			// Store the sample task probability
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			logProbability$sample20[1] = cv$weightedProbability;
			
			// Guard to ensure that mu is only updated once for this probability.
			boolean cv$guard$mu = false;
			
			// Add probability to constructed variables that have guards, so need per sample probabilities
			// from the combined probability
			// 
			// Looking for a path between Sample 20 and consumer double[] 41.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Guard to ensure that mu is only updated once for this probability.
			if(!(rawMu[0] < rawMu[1])) {
				// Set the guard so the update is only applied once.
				cv$guard$mu = true;
				
				// Update the variable probability
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				logProbability$mu = (logProbability$mu + cv$weightedProbability);
			}
			
			// Looking for a path between Sample 20 and consumer double[] 59.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((rawMu[0] < rawMu[1]) && !cv$guard$mu))
				// Update the variable probability
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				logProbability$mu = (logProbability$mu + cv$weightedProbability);
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$rawMu = (logProbability$rawMu + cv$sampleAccumulator);
			
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
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// This value is not used before it is set again, so removing the value declaration.
			double cv$rvAccumulator;
			{
				double cv$sampleValue = logProbability$sample20[0];
				cv$rvAccumulator = cv$sampleValue;
				
				// Guard to ensure that mu is only updated once for this probability.
				boolean cv$guard$mu = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 20 and consumer double[] 41.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((rawMu[0] < rawMu[1])) {
					// Set the guard so the update is only applied once.
					cv$guard$mu = true;
					
					// Update the variable probability
					logProbability$mu = (logProbability$mu + cv$sampleValue);
				}
				
				// Looking for a path between Sample 20 and consumer double[] 59.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((!(rawMu[0] < rawMu[1]) && !cv$guard$mu))
					// Update the variable probability
					logProbability$mu = (logProbability$mu + cv$sampleValue);
			}
			double cv$sampleValue = logProbability$sample20[1];
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			
			// Guard to ensure that mu is only updated once for this probability.
			boolean cv$guard$mu = false;
			
			// Add probability to constructed variables that have guards, so need per sample probabilities
			// from the combined probability
			// 
			// Looking for a path between Sample 20 and consumer double[] 41.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Guard to ensure that mu is only updated once for this probability.
			if(!(rawMu[0] < rawMu[1])) {
				// Set the guard so the update is only applied once.
				cv$guard$mu = true;
				
				// Update the variable probability
				logProbability$mu = (logProbability$mu + cv$sampleValue);
			}
			
			// Looking for a path between Sample 20 and consumer double[] 59.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((rawMu[0] < rawMu[1]) && !cv$guard$mu))
				// Update the variable probability
				logProbability$mu = (logProbability$mu + cv$sampleValue);
			
			// Update the variable probability
			logProbability$rawMu = (logProbability$rawMu + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample83 using sampled
	// values.
	private final void logProbabilityValue$sample83() {
		// Determine if we need to calculate the values for sample task 83 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample83) {
			// Generating probabilities for sample task
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator;
			{
				// The sample value to calculate the probability of generating
				double cv$sampleValue = sigma[0];
				
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
				cv$sampleAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue <= 1.0E100))?DistributionSampling.logProbabilityGaussian((cv$sampleValue / 2.0)):Double.NEGATIVE_INFINITY);
			}
			
			// The sample value to calculate the probability of generating
			double cv$sampleValue = sigma[1];
			
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
			cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue <= 1.0E100))?DistributionSampling.logProbabilityGaussian((cv$sampleValue / 2.0)):Double.NEGATIVE_INFINITY));
			
			// Store the random variable instance probability
			logProbability$var79 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$sigma = (logProbability$sigma + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample83)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample83 = fixedFlag$sample83;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$sigma = (logProbability$sigma + logProbability$var79);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var79);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample83)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var79);
		}
	}

	// Calculate the probability of the samples represented by sample88 using sampled
	// values.
	private final void logProbabilityValue$sample88() {
		// Determine if we need to calculate the values for sample task 88 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample88) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(theta, 5.0, 5.0);
			
			// Store the sample task probability
			logProbability$theta = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample88)
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
			fixedProbFlag$sample88 = fixedFlag$sample88;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$theta);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample88)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$theta);
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var97$stateProbabilityGlobal
		{
			// Allocation of cv$var97$stateProbabilityGlobal for multithreaded execution
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var97$stateProbabilityGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var97$stateProbabilityGlobal[cv$index] = new double[2];
		}
		
		// Allocation of guard$sample20if124$global for multithreaded execution
		// 
		// Get the thread count.
		int cv$threadCount = threadCount();
		
		// Allocate an array to hold a copy per thread
		guard$sample20if124$global = new boolean[cv$threadCount][];
		
		// Populate the array with a copy per thread
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			guard$sample20if124$global[cv$index] = new boolean[2];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If rawMu has not been set already allocate space.
		if(!fixedFlag$sample20)
			// Constructor for rawMu
			rawMu = new double[2];
		
		// Constructor for mu
		mu = new double[2];
		
		// If sigma has not been set already allocate space.
		if(!fixedFlag$sample83)
			// Constructor for sigma
			sigma = new double[2];
		
		// If component has not been set already allocate space.
		if(!fixedFlag$sample101)
			// Constructor for component
			component = new boolean[length$yObserved];
		
		// Constructor for y
		y = new double[length$yObserved];
		
		// Constructor for constrainedFlag$sample101
		constrainedFlag$sample101 = new boolean[length$yObserved];
		
		// Constructor for constrainedFlag$sample20
		constrainedFlag$sample20 = new boolean[2];
		
		// Constructor for constrainedFlag$sample83
		constrainedFlag$sample83 = new boolean[2];
		
		// Constructor for logProbability$sample20
		logProbability$sample20 = new double[2];
		
		// Constructor for logProbability$sample138
		logProbability$sample138 = new double[length$yObserved];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample20) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var19, int forEnd$var19, int threadID$var19, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var19 = forStart$var19; var19 < forEnd$var19; var19 += 1)
							rawMu[var19] = (DistributionSampling.sampleGaussian(RNG$1) * 2.0);
				}
			);
			
			// This value is not used before it is set again, so removing the value declaration.
			double var39;
			if((rawMu[0] < rawMu[1]))
				var39 = rawMu[0];
			else
				var39 = rawMu[1];
			mu[0] = var39;
			
			// This value is not used before it is set again, so removing the value declaration.
			double var57;
			if((rawMu[0] < rawMu[1]))
				var57 = rawMu[1];
			else
				var57 = rawMu[0];
			mu[1] = var57;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample83)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var78, int forEnd$var78, int threadID$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var78 = forStart$var78; var78 < forEnd$var78; var78 += 1)
							sigma[var78] = (DistributionSampling.sampleTruncatedGaussian(RNG$1, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
				}
			);

		if(!fixedFlag$sample88)
			theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample101)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, N, 1,
				(int forStart$var96, int forEnd$var96, int threadID$var96, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var96 = forStart$var96; var96 < forEnd$var96; var96 += 1)
							component[var96] = DistributionSampling.sampleBernoulli(RNG$1, theta);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, N, 1,
			(int forStart$n, int forEnd$n, int threadID$n, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int n = forStart$n; n < forEnd$n; n += 1) {
						double componentMu;
						if(component[n])
							componentMu = mu[0];
						else
							componentMu = mu[1];
						double componentSigma;
						if(component[n])
							componentSigma = sigma[0];
						else
							componentSigma = sigma[1];
						y[n] = ((componentSigma * DistributionSampling.sampleGaussian(RNG$1)) + componentMu);
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
		if(!fixedFlag$sample20)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var19, int forEnd$var19, int threadID$var19, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var19 = forStart$var19; var19 < forEnd$var19; var19 += 1)
							rawMu[var19] = (DistributionSampling.sampleGaussian(RNG$1) * 2.0);
				}
			);

		double var39;
		if((rawMu[0] < rawMu[1]))
			var39 = rawMu[0];
		else
			var39 = rawMu[1];
		mu[0] = var39;
		double var57;
		if((rawMu[0] < rawMu[1]))
			var57 = rawMu[1];
		else
			var57 = rawMu[0];
		mu[1] = var57;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample83)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var78, int forEnd$var78, int threadID$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var78 = forStart$var78; var78 < forEnd$var78; var78 += 1)
							sigma[var78] = (DistributionSampling.sampleTruncatedGaussian(RNG$1, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
				}
			);

		if(!fixedFlag$sample88)
			theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample101)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, N, 1,
				(int forStart$var96, int forEnd$var96, int threadID$var96, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var96 = forStart$var96; var96 < forEnd$var96; var96 += 1)
							component[var96] = DistributionSampling.sampleBernoulli(RNG$1, theta);
				}
			);

	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample20)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var19, int forEnd$var19, int threadID$var19, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var19 = forStart$var19; var19 < forEnd$var19; var19 += 1)
							rawMu[var19] = (DistributionSampling.sampleGaussian(RNG$1) * 2.0);
				}
			);

		double var39;
		if((rawMu[0] < rawMu[1]))
			var39 = rawMu[0];
		else
			var39 = rawMu[1];
		mu[0] = var39;
		double var57;
		if((rawMu[0] < rawMu[1]))
			var57 = rawMu[1];
		else
			var57 = rawMu[0];
		mu[1] = var57;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample83)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var78, int forEnd$var78, int threadID$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var78 = forStart$var78; var78 < forEnd$var78; var78 += 1)
							sigma[var78] = (DistributionSampling.sampleTruncatedGaussian(RNG$1, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
				}
			);

		if(!fixedFlag$sample88)
			theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample101)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, N, 1,
				(int forStart$var96, int forEnd$var96, int threadID$var96, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var96 = forStart$var96; var96 < forEnd$var96; var96 += 1)
							component[var96] = DistributionSampling.sampleBernoulli(RNG$1, theta);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, N, 1,
			(int forStart$n, int forEnd$n, int threadID$n, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int n = forStart$n; n < forEnd$n; n += 1) {
						double componentMu;
						if(component[n])
							componentMu = mu[0];
						else
							componentMu = mu[1];
						double componentSigma;
						if(component[n])
							componentSigma = sigma[0];
						else
							componentSigma = sigma[1];
						y[n] = ((componentSigma * DistributionSampling.sampleGaussian(RNG$1)) + componentMu);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample20) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var19, int forEnd$var19, int threadID$var19, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var19 = forStart$var19; var19 < forEnd$var19; var19 += 1)
							rawMu[var19] = (DistributionSampling.sampleGaussian(RNG$1) * 2.0);
				}
			);
			
			// This value is not used before it is set again, so removing the value declaration.
			double var39;
			if((rawMu[0] < rawMu[1]))
				var39 = rawMu[0];
			else
				var39 = rawMu[1];
			mu[0] = var39;
			
			// This value is not used before it is set again, so removing the value declaration.
			double var57;
			if((rawMu[0] < rawMu[1]))
				var57 = rawMu[1];
			else
				var57 = rawMu[0];
			mu[1] = var57;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample83)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var78, int forEnd$var78, int threadID$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var78 = forStart$var78; var78 < forEnd$var78; var78 += 1)
							sigma[var78] = (DistributionSampling.sampleTruncatedGaussian(RNG$1, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
				}
			);

		if(!fixedFlag$sample88)
			theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample101)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, N, 1,
				(int forStart$var96, int forEnd$var96, int threadID$var96, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var96 = forStart$var96; var96 < forEnd$var96; var96 += 1)
							component[var96] = DistributionSampling.sampleBernoulli(RNG$1, theta);
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample20)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var19, int forEnd$var19, int threadID$var19, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var19 = forStart$var19; var19 < forEnd$var19; var19 += 1)
							rawMu[var19] = (DistributionSampling.sampleGaussian(RNG$1) * 2.0);
				}
			);

		double var39;
		if((rawMu[0] < rawMu[1]))
			var39 = rawMu[0];
		else
			var39 = rawMu[1];
		mu[0] = var39;
		double var57;
		if((rawMu[0] < rawMu[1]))
			var57 = rawMu[1];
		else
			var57 = rawMu[0];
		mu[1] = var57;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample83)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var78, int forEnd$var78, int threadID$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var78 = forStart$var78; var78 < forEnd$var78; var78 += 1)
							sigma[var78] = (DistributionSampling.sampleTruncatedGaussian(RNG$1, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
				}
			);

		if(!fixedFlag$sample88)
			theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample101)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, N, 1,
				(int forStart$var96, int forEnd$var96, int threadID$var96, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var96 = forStart$var96; var96 < forEnd$var96; var96 += 1)
							component[var96] = DistributionSampling.sampleBernoulli(RNG$1, theta);
				}
			);

	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample20)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var19, int forEnd$var19, int threadID$var19, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var19 = forStart$var19; var19 < forEnd$var19; var19 += 1)
								inferSample20(var19, threadID$var19, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample83) {
				inferSample83(0);
				inferSample83(1);
			}
			if(!fixedFlag$sample88)
				inferSample88();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample101)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, N, 1,
					(int forStart$var96, int forEnd$var96, int threadID$var96, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var96 = forStart$var96; var96 < forEnd$var96; var96 += 1)
								inferSample101(var96, threadID$var96, RNG$1);
					}
				);

		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample101)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, N, 1,
					(int forStart$var96, int forEnd$var96, int threadID$var96, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var96 = forStart$var96; var96 < forEnd$var96; var96 += 1)
								inferSample101(var96, threadID$var96, RNG$1);
					}
				);

			if(!fixedFlag$sample88)
				inferSample88();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample83) {
				inferSample83(1);
				inferSample83(0);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample20)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var19, int forEnd$var19, int threadID$var19, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var19 = forStart$var19; var19 < forEnd$var19; var19 += 1)
								inferSample20(var19, threadID$var19, RNG$1);
					}
				);

		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$var19, int forEnd$var19, int threadID$var19, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var19 = forStart$var19; var19 < forEnd$var19; var19 += 1) {
						if(!constrainedFlag$sample20[var19])
							drawValueSample20(var19, threadID$var19, RNG$1);
					}
			}
		);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!constrainedFlag$sample83[0])
			drawValueSample83(0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!constrainedFlag$sample83[1])
			drawValueSample83(1);
		if(!constrainedFlag$sample88)
			drawValueSample88();
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, N, 1,
			(int forStart$var96, int forEnd$var96, int threadID$var96, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var96 = forStart$var96; var96 < forEnd$var96; var96 += 1) {
						if(!constrainedFlag$sample101[var96])
							drawValueSample101(var96, threadID$var96, RNG$1);
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
		logProbability$rawMu = 0.0;
		logProbability$mu = 0.0;
		if(!fixedProbFlag$sample20) {
			// Unrolled loop
			logProbability$sample20[0] = Double.NaN;
			logProbability$sample20[1] = Double.NaN;
		}
		logProbability$sigma = 0.0;
		if(!fixedProbFlag$sample83)
			logProbability$var79 = Double.NaN;
		if(!fixedProbFlag$sample88)
			logProbability$theta = Double.NaN;
		logProbability$componentDistribution = Double.NaN;
		logProbability$component = 0.0;
		if(!fixedProbFlag$sample101)
			logProbability$var97 = Double.NaN;
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample138) {
			for(int n = 0; n < N; n += 1)
				logProbability$sample138[n] = Double.NaN;
		}
	}

	// Method for initializing the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		N = length$yObserved;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample101$1 = 0; index$constrainedFlag$sample101$1 < constrainedFlag$sample101.length; index$constrainedFlag$sample101$1 += 1)
			constrainedFlag$sample101[index$constrainedFlag$sample101$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample20$1 = 0; index$constrainedFlag$sample20$1 < constrainedFlag$sample20.length; index$constrainedFlag$sample20$1 += 1)
			constrainedFlag$sample20[index$constrainedFlag$sample20$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample83$1 = 0; index$constrainedFlag$sample83$1 < constrainedFlag$sample83.length; index$constrainedFlag$sample83$1 += 1)
			constrainedFlag$sample83[index$constrainedFlag$sample83$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		if(fixedFlag$sample83)
			logProbabilityValue$sample83();
		if(fixedFlag$sample88)
			logProbabilityValue$sample88();
		if(fixedFlag$sample101)
			logProbabilityValue$sample101();
		logProbabilityValue$sample138();
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
		logProbabilityValue$sample83();
		logProbabilityValue$sample88();
		logProbabilityValue$sample101();
		logProbabilityValue$sample138();
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
		logProbabilityValue$sample83();
		logProbabilityValue$sample88();
		logProbabilityValue$sample101();
		logProbabilityValue$sample138();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = y.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			y[cv$index1] = yObserved[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		double var39;
		if((rawMu[0] < rawMu[1]))
			var39 = rawMu[0];
		else
			var39 = rawMu[1];
		mu[0] = var39;
		double var57;
		if((rawMu[0] < rawMu[1]))
			var57 = rawMu[1];
		else
			var57 = rawMu[0];
		mu[1] = var57;
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2026, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model LowDimMix(double[] yObserved) {\n"
		     + "    int N = yObserved.length;\n"
		     + "\n"
		     + "    // Stan parameter: ordered[2] mu; prior: mu ~ normal(0, 2)\n"
		     + "    // Sampling two unconstrained normal values and sorting them gives the same ordered support up to\n"
		     + "    // the constant normalisation factor for the ordered constraint.\n"
		     + "    double[] rawMu = gaussian(0.0, 2.0 * 2.0).sample(2);\n"
		     + "    double[] mu = new double[2];\n"
		     + "    mu[0] = rawMu[0] < rawMu[1] ? rawMu[0] : rawMu[1];\n"
		     + "    mu[1] = rawMu[0] < rawMu[1] ? rawMu[1] : rawMu[0];\n"
		     + "\n"
		     + "    // Stan parameter: array[2] real<lower=0> sigma; prior: sigma ~ normal(0, 2)\n"
		     + "    double[] sigma = truncatedGaussian(0.0, 2.0 * 2.0, 0.0, 1.0e100).sample(2);\n"
		     + "\n"
		     + "    // Stan parameter: real<lower=0, upper=1> theta; prior: theta ~ beta(5, 5)\n"
		     + "    double theta = beta(5.0, 5.0).sample();\n"
		     + "\n"
		     + "    // Stan likelihood:\n"
		     + "    // target += log_mix(theta, normal_lpdf(y[n] | mu[1], sigma[1]),\n"
		     + "    //                   normal_lpdf(y[n] | mu[2], sigma[2]));\n"
		     + "    // In Sandwood, represent the same two-component mixture with explicit latent component indicators.\n"
		     + "    Bernoulli componentDistribution = bernoulli(theta);\n"
		     + "    boolean[] component = componentDistribution.sample(N);\n"
		     + "    double[] y = new double[N];\n"
		     + "\n"
		     + "    for(int n = 0; n < N; n++) {\n"
		     + "        double componentMu = component[n] ? mu[0] : mu[1];\n"
		     + "        double componentSigma = component[n] ? sigma[0] : sigma[1];\n"
		     + "        y[n] = gaussian(componentMu, componentSigma * componentSigma).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    y.observe(yObserved);\n"
		     + "}\n"
		     + "";
	}
}