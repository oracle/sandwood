package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class MultinomialBernoulli$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements MultinomialBernoulli$CoreInterface {
	
	// Declare the variables for the model.
	private double[] beta;
	private double[] cv$var17$countGlobal;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample48 = false;
	private boolean fixedProbFlag$sample60 = false;
	private boolean fixedProbFlag$sample72 = false;
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
	private double logProbability$var16;
	private double logProbability$var19;
	private double logProbability$var48;
	private double logProbability$var60;
	private double logProbability$var72;
	private int n;
	private boolean[] observed;
	private boolean[] output;
	private double[] p;
	private int[] prior;
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
		fixedProbFlag$sample17 = (fixedFlag$sample17 && fixedProbFlag$sample17);
		
		// Should the probability of sample 20 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample20 = (fixedFlag$sample17 && fixedProbFlag$sample20);
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
		fixedProbFlag$sample20 = (fixedFlag$sample20 && fixedProbFlag$sample20);
		
		// Should the probability of sample 48 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample48 = (fixedFlag$sample20 && fixedProbFlag$sample48);
		
		// Should the probability of sample 60 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample60 = (fixedFlag$sample20 && fixedProbFlag$sample60);
		
		// Should the probability of sample 72 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample72 = (fixedFlag$sample20 && fixedProbFlag$sample72);
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
		return n;
	}

	// Getter for observed.
	@Override
	public final boolean[] get$observed() {
		return observed;
	}

	// Setter for observed.
	@Override
	public final void set$observed(boolean[] cv$value) {
		// Set observed
		observed = cv$value;
	}

	// Getter for output.
	@Override
	public final boolean[] get$output() {
		return output;
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
		// Set p
		p = cv$value;
		
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
		// Set prior
		prior = cv$value;
		
		// Unset the fixed probability flag for sample 20 as it depends on prior.
		fixedProbFlag$sample20 = false;
		
		// Unset the fixed probability flag for sample 48 as it depends on prior.
		fixedProbFlag$sample48 = false;
		
		// Unset the fixed probability flag for sample 60 as it depends on prior.
		fixedProbFlag$sample60 = false;
		
		// Unset the fixed probability flag for sample 72 as it depends on prior.
		fixedProbFlag$sample72 = false;
	}

	// Calculate the probability of the samples represented by sample17 using sampled
	// values.
	private final void logProbabilityValue$sample17() {
		// Determine if we need to calculate the values for sample task 17 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample17) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				// The sample value to calculate the probability of generating
				double[] cv$sampleValue = p;
				{
					{
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, beta, 3));
						
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
				}
			}
			if((cv$probabilityReached == 0.0))
				// Return negative infinity if no distribution probability space is reached.
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				// Scale the probability relative to the observed distribution space.
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			
			// Add the probability of this sample task to the sample task accumulator.
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var16 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$p = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample17 = fixedFlag$sample17;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$p;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var16 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample17)
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
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				// The sample value to calculate the probability of generating
				int[] cv$sampleValue = prior;
				{
					{
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(cv$sampleValue, p, 3, n));
						
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
				}
			}
			if((cv$probabilityReached == 0.0))
				// Return negative infinity if no distribution probability space is reached.
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				// Scale the probability relative to the observed distribution space.
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			
			// Add the probability of this sample task to the sample task accumulator.
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var19 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$prior = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample20 = (fixedFlag$sample20 && fixedFlag$sample17);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$prior;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var19 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample48 using sampled
	// values.
	private final void logProbabilityValue$sample48() {
		// Determine if we need to calculate the values for sample task 48 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample48) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var47 = 0; i$var47 < length; i$var47 += 3) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = output[i$var47];
					{
						{
							double var24 = (double)(prior[0] / n);
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var24));
							
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
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$b1 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var48 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$output = (logProbability$output + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample48 = fixedFlag$sample20;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var48;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$b1 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$output = (logProbability$output + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample60 using sampled
	// values.
	private final void logProbabilityValue$sample60() {
		// Determine if we need to calculate the values for sample task 60 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample60) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var59 = 1; i$var59 < length; i$var59 += 3) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = output[i$var59];
					{
						{
							double var29 = (double)(prior[1] / n);
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var29));
							
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
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$b2 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var60 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$output = (logProbability$output + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample60 = fixedFlag$sample20;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var60;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$b2 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$output = (logProbability$output + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample72 using sampled
	// values.
	private final void logProbabilityValue$sample72() {
		// Determine if we need to calculate the values for sample task 72 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample72) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var71 = 2; i$var71 < length; i$var71 += 3) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = output[i$var71];
					{
						{
							double var34 = (double)(prior[2] / n);
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var34));
							
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
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$b3 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var72 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$output = (logProbability$output + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample72 = fixedFlag$sample20;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var72;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$b3 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$output = (logProbability$output + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 17 drawn from Dirichlet 16. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample17() {
		if(true) {
			// A reference local to the function for the sample variable.
			double[] cv$targetLocal = p;
			
			// A local reference to the scratch space.
			double[] cv$countLocal = cv$var17$countGlobal;
			
			// Get the length of the array
			int cv$arrayLength = 3;
			
			// Initialize the array values to 0.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				// Processing random variable 19.
				{
					{
						// Processing sample task 20 of consumer random variable null.
						{
							{
								{
									{
										{
											int[] cv$sampleValue = prior;
											
											// Update all the counts
											for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
												cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (1.0 * cv$sampleValue[cv$loopIndex]));
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			Conjugates.sampleConjugateDirichletCategorical(RNG$, beta, cv$countLocal, cv$targetLocal, 3);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 20 drawn from Multinomial 19. Inference was performed using Metropolis-Hastings.
	private final void sample20() {
		if(!(n == 0)) {
			// A reference local to the function for the sample variable.
			int[] cv$targetLocal = prior;
			
			// Calculate the probability of the random variable generating the original sampled
			// value.
			double cv$originalProbability = 0.0;
			
			// The probability of the random variable generating the new sample value.
			double cv$proposedProbability = 0.0;
			int cv$arrayLength = 3;
			
			// Count how many non zero entries there are.
			int cv$nonZeroCount = 0;
			for(int cv$loopIndex = 0; cv$loopIndex < 3; cv$loopIndex += 1) {
				if(!(cv$targetLocal[cv$loopIndex] == 0))
					cv$nonZeroCount = (cv$nonZeroCount + 1);
			}
			
			// Pick a value in the array to adjust.
			int cv$sourceIndex = (int)(0.0 + ((cv$nonZeroCount - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			for(int cv$loopIndex = 0; cv$loopIndex < (cv$sourceIndex + 1); cv$loopIndex += 1) {
				if((cv$targetLocal[cv$loopIndex] == 0))
					cv$sourceIndex = (cv$sourceIndex + 1);
			}
			
			// Select the number of trials to remove from the selected category.
			int cv$changeValue = (int)(1.0 + (((cv$targetLocal[cv$sourceIndex] + 1.0) - 1.0) * DistributionSampling.sampleUniform(RNG$)));
			
			// Select the destination of the moved trials.
			int cv$destinationIndex = (int)(0.0 + (((cv$arrayLength - 1) - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			
			// Ensure the source and target are not equal
			if((cv$sourceIndex <= cv$destinationIndex))
				cv$destinationIndex = (cv$destinationIndex + 1);
			for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
				// Initialize the summed probabilities to 0 in log space.
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				
				// Initialize a counter to track the reached distributions.
				double cv$reachedDistributionSourceRV = 0.0;
				
				// Initialize an accumulator to take the product of all the distribution probabilities
				// in log space.
				double cv$accumulatedDistributionProbabilities = 0.0;
				if((cv$valuePos == 1)) {
					// Update Sample and intermediate values
					{
						// Update the sample values
						cv$targetLocal[cv$sourceIndex] = (cv$targetLocal[cv$sourceIndex] - cv$changeValue);
						cv$targetLocal[cv$destinationIndex] = (cv$targetLocal[cv$destinationIndex] + cv$changeValue);
					}
				}
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] cv$temp$0$p;
					{
						cv$temp$0$p = p;
					}
					int cv$temp$1$$var131;
					{
						// Constructing a random variable input for use later.
						int $var131 = 3;
						cv$temp$1$$var131 = $var131;
					}
					int cv$temp$2$n;
					{
						cv$temp$2$n = n;
					}
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(cv$targetLocal, cv$temp$0$p, cv$temp$1$$var131, cv$temp$2$n));
					
					// Processing random variable 25.
					{
						{
							// Processing sample task 48 of consumer random variable b1.
							{
								for(int i$var47 = 0; i$var47 < length; i$var47 += 3) {
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double cv$temp$3$var24;
													{
														// Constructing a random variable input for use later.
														double var24 = (double)(prior[0] / n);
														cv$temp$3$var24 = var24;
													}
													
													// Record the probability of sample task 48 generating output with current configuration.
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var47], cv$temp$3$var24)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var47], cv$temp$3$var24)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var47], cv$temp$3$var24));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var47], cv$temp$3$var24)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var47], cv$temp$3$var24)));
													}
													
													// Recorded the probability of reaching sample task 48 with the current configuration.
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
						}
					}
					
					// Processing random variable 30.
					{
						{
							// Processing sample task 60 of consumer random variable b2.
							{
								for(int i$var59 = 1; i$var59 < length; i$var59 += 3) {
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double cv$temp$4$var29;
													{
														// Constructing a random variable input for use later.
														double var29 = (double)(prior[1] / n);
														cv$temp$4$var29 = var29;
													}
													
													// Record the probability of sample task 60 generating output with current configuration.
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var59], cv$temp$4$var29)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var59], cv$temp$4$var29)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var59], cv$temp$4$var29));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var59], cv$temp$4$var29)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var59], cv$temp$4$var29)));
													}
													
													// Recorded the probability of reaching sample task 60 with the current configuration.
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
						}
					}
					
					// Processing random variable 35.
					{
						{
							// Processing sample task 72 of consumer random variable b3.
							{
								for(int i$var71 = 2; i$var71 < length; i$var71 += 3) {
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double cv$temp$5$var34;
													{
														// Constructing a random variable input for use later.
														double var34 = (double)(prior[2] / n);
														cv$temp$5$var34 = var34;
													}
													
													// Record the probability of sample task 72 generating output with current configuration.
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var71], cv$temp$5$var34)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var71], cv$temp$5$var34)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var71], cv$temp$5$var34));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var71], cv$temp$5$var34)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var71], cv$temp$5$var34)));
													}
													
													// Recorded the probability of reaching sample task 72 with the current configuration.
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
						}
					}
					
					// Add the values for the source and any standard consumers for this configuration
					// of arguments to the source.
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						// If the second value is -infinity.
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				
				// Save the probability of the original value.
				if((cv$valuePos == 0))
					cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
				
				// Save the probability of the proposed value.
				else
					cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			
			// Ratio of the probability of proposed and original sample values
			double cv$ratio = (cv$proposedProbability - cv$originalProbability);
			
			// Test if the probability of the sample is sufficient to keep the value. This needs
			// to be less than or equal as otherwise if the proposed value is not possible and
			// the random value is 0 an impossible value will be accepted.
			if(((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)))))) {
				// If it is not revert the sample value and intermediates to their original values.
				// 
				// Set the sample value
				// 
				// Calculate the new sample value
				// 
				// Update the sample values
				cv$targetLocal[cv$sourceIndex] = (cv$targetLocal[cv$sourceIndex] + cv$changeValue);
				cv$targetLocal[cv$destinationIndex] = (cv$targetLocal[cv$destinationIndex] - cv$changeValue);
			}
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocation of cv$var17$countGlobal for single threaded execution
		cv$var17$countGlobal = new double[3];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for beta
		{
			beta = new double[3];
		}
		
		// If p has not been set already allocate space.
		if(!fixedFlag$sample17) {
			// Constructor for p
			{
				p = new double[3];
			}
		}
		
		// If prior has not been set already allocate space.
		if(!fixedFlag$sample20) {
			// Constructor for prior
			{
				prior = new int[3];
			}
		}
		
		// Constructor for output
		{
			output = new boolean[length$observed];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, beta, 3, p);
		if(!fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(RNG$, p, 3, n, prior);
		for(int i$var47 = 0; i$var47 < length; i$var47 += 3)
			output[i$var47] = DistributionSampling.sampleBernoulli(RNG$, (prior[0] / n));
		for(int i$var59 = 1; i$var59 < length; i$var59 += 3)
			output[i$var59] = DistributionSampling.sampleBernoulli(RNG$, (prior[1] / n));
		for(int i$var71 = 2; i$var71 < length; i$var71 += 3)
			output[i$var71] = DistributionSampling.sampleBernoulli(RNG$, (prior[2] / n));
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, beta, 3, p);
		if(!fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(RNG$, p, 3, n, prior);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, beta, 3, p);
		if(!fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(RNG$, p, 3, n, prior);
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
		n = 10;
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
		logProbability$var16 = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$p = 0.0;
		logProbability$var19 = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$prior = 0.0;
		logProbability$b1 = 0.0;
		logProbability$output = 0.0;
		if(!fixedProbFlag$sample48)
			logProbability$var48 = 0.0;
		logProbability$b2 = 0.0;
		if(!fixedProbFlag$sample60)
			logProbability$var60 = 0.0;
		logProbability$b3 = 0.0;
		if(!fixedProbFlag$sample72)
			logProbability$var72 = 0.0;
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
		logProbabilityValue$sample48();
		logProbabilityValue$sample60();
		logProbabilityValue$sample72();
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
		logProbabilityValue$sample48();
		logProbabilityValue$sample60();
		logProbabilityValue$sample72();
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
		logProbabilityValue$sample48();
		logProbabilityValue$sample60();
		logProbabilityValue$sample72();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, beta, 3, p);
		if(!fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(RNG$, p, 3, n, prior);
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Deep copy between arrays
		boolean[] cv$source1 = observed;
		boolean[] cv$target1 = output;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
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
		     + "public model MultinomialBernoulli(boolean[] observed) {\n"
		     + "    double[] beta = {0.1, 0.1, 0.1};\n"
		     + "    double[] p = dirichlet(beta).sample();\n"
		     + "    int n = 10;\n"
		     + "    int[] prior = multinomial(p, n).sample();\n"
		     + "    Bernoulli b1 = new Bernoulli(prior[0]/n);\n"
		     + "    Bernoulli b2 = new Bernoulli(prior[1]/n);\n"
		     + "    Bernoulli b3 = new Bernoulli(prior[2]/n);\n"
		     + "    int length = observed.length;\n"
		     + "    boolean[] output = new boolean[length];\n"
		     + "    for(int i=0; i<length; i+=3)\n"
		     + "        output[i] = b1.sample();\n"
		     + "    for(int i=1; i<length; i+=3)\n"
		     + "        output[i] = b2.sample();\n"
		     + "    for(int i=2; i<length; i+=3)\n"
		     + "        output[i] = b3.sample();\n"
		     + "    output.observe(observed);\n"
		     + "}\n"
		     + "";
	}
}