package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Vulcano2012basicDG$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Vulcano2012basicDG$CoreInterface {
	
	// Declare the variables for the model.
	private int[][] ObsSales;
	private int[] arrivals;
	private boolean[][] avail;
	private double[] exped;
	private double[] expedNorm;
	private boolean fixedFlag$sample127 = false;
	private boolean fixedFlag$sample129 = false;
	private boolean fixedFlag$sample181 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedProbFlag$sample127 = false;
	private boolean fixedProbFlag$sample129 = false;
	private boolean fixedProbFlag$sample181 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean[] guard$sample45multinomial180$global;
	private boolean[][] guard$sample45put150$global;
	private boolean[][] guard$sample45put179$global;
	private boolean[] guard$sample45put86$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$arrivals;
	private double logProbability$exped;
	private double logProbability$expedNorm;
	private double logProbability$lambda;
	private double logProbability$sales;
	private double[] logProbability$sample127;
	private double[] logProbability$sample129;
	private double[] logProbability$sample181;
	private double[] logProbability$sample45;
	private double logProbability$ut;
	private double[] logProbability$var116;
	private double[] logProbability$var118;
	private double[] logProbability$var167;
	private double logProbability$var28;
	private double logProbability$weekly_rates;
	private double logProbability$weekly_sales;
	private double logProbability$weekly_ut;
	private int numTimeSteps;
	private int[][] sales;
	private boolean setFlag$arrivals = false;
	private boolean setFlag$lambda = false;
	private boolean setFlag$weekly_sales = false;
	private boolean system$gibbsForward = true;
	private double[] ut;
	private double[][] weekly_rates;
	private int[][] weekly_sales;
	private double[][] weekly_ut;

	public Vulcano2012basicDG$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for ObsSales.
	@Override
	public final int[][] get$ObsSales() {
		return ObsSales;
	}

	// Setter for ObsSales.
	@Override
	public final void set$ObsSales(int[][] cv$value) {
		// Set ObsSales with flag to mark that it has been set so another array doesn't need
		// to be constructed
		ObsSales = cv$value;
	}

	// Getter for arrivals.
	@Override
	public final int[] get$arrivals() {
		return arrivals;
	}

	// Setter for arrivals.
	@Override
	public final void set$arrivals(int[] cv$value) {
		// Set arrivals with flag to mark that it has been set so another array doesn't need
		// to be constructed
		arrivals = cv$value;
		setFlag$arrivals = true;
	}

	// Getter for avail.
	@Override
	public final boolean[][] get$avail() {
		return avail;
	}

	// Setter for avail.
	@Override
	public final void set$avail(boolean[][] cv$value) {
		// Set avail with flag to mark that it has been set so another array doesn't need
		// to be constructed
		avail = cv$value;
	}

	// Getter for fixedFlag$sample127.
	@Override
	public final boolean get$fixedFlag$sample127() {
		return fixedFlag$sample127;
	}

	// Setter for fixedFlag$sample127.
	@Override
	public final void set$fixedFlag$sample127(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample127 including if probabilities
		// need to be updated.
		fixedFlag$sample127 = cv$value;
		
		// Should the probability of sample 127 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample127" with its value "cv$value".
		fixedProbFlag$sample127 = (cv$value && fixedProbFlag$sample127);
		
		// Should the probability of sample 129 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample127" with its value "cv$value".
		fixedProbFlag$sample129 = (cv$value && fixedProbFlag$sample129);
	}

	// Getter for fixedFlag$sample129.
	@Override
	public final boolean get$fixedFlag$sample129() {
		return fixedFlag$sample129;
	}

	// Setter for fixedFlag$sample129.
	@Override
	public final void set$fixedFlag$sample129(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample129 including if probabilities
		// need to be updated.
		fixedFlag$sample129 = cv$value;
		
		// Should the probability of sample 129 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample129" with its value "cv$value".
		fixedProbFlag$sample129 = (cv$value && fixedProbFlag$sample129);
		
		// Should the probability of sample 181 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample129" with its value "cv$value".
		fixedProbFlag$sample181 = (cv$value && fixedProbFlag$sample181);
	}

	// Getter for fixedFlag$sample181.
	@Override
	public final boolean get$fixedFlag$sample181() {
		return fixedFlag$sample181;
	}

	// Setter for fixedFlag$sample181.
	@Override
	public final void set$fixedFlag$sample181(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample181 including if probabilities
		// need to be updated.
		fixedFlag$sample181 = cv$value;
		
		// Should the probability of sample 181 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample181" with its value "cv$value".
		fixedProbFlag$sample181 = (cv$value && fixedProbFlag$sample181);
	}

	// Getter for fixedFlag$sample45.
	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	// Setter for fixedFlag$sample45.
	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample45 including if probabilities
		// need to be updated.
		fixedFlag$sample45 = cv$value;
		
		// Should the probability of sample 45 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample45" with its value "cv$value".
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
		
		// Should the probability of sample 181 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample45" with its value "cv$value".
		fixedProbFlag$sample181 = (cv$value && fixedProbFlag$sample181);
	}

	// Getter for lambda.
	@Override
	public final double[] get$lambda() {
		return lambda;
	}

	// Setter for lambda.
	@Override
	public final void set$lambda(double[] cv$value) {
		// Set lambda with flag to mark that it has been set so another array doesn't need
		// to be constructed
		lambda = cv$value;
		setFlag$lambda = true;
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

	// Getter for logProbability$arrivals.
	@Override
	public final double get$logProbability$arrivals() {
		return logProbability$arrivals;
	}

	// Getter for logProbability$lambda.
	@Override
	public final double get$logProbability$lambda() {
		return logProbability$lambda;
	}

	// Getter for logProbability$weekly_sales.
	@Override
	public final double get$logProbability$weekly_sales() {
		return logProbability$weekly_sales;
	}

	// Getter for numTimeSteps.
	@Override
	public final int get$numTimeSteps() {
		return numTimeSteps;
	}

	// Getter for r.
	@Override
	public final double get$r() {
		return 0.3;
	}

	// Getter for weekly_sales.
	@Override
	public final int[][] get$weekly_sales() {
		return weekly_sales;
	}

	// Setter for weekly_sales.
	@Override
	public final void set$weekly_sales(int[][] cv$value) {
		// Set weekly_sales with flag to mark that it has been set so another array doesn't
		// need to be constructed
		weekly_sales = cv$value;
		setFlag$weekly_sales = true;
	}

	// Calculate the probability of the samples represented by sample127 using sampled
	// values.
	private final void logProbabilityValue$sample127() {
		// Determine if we need to calculate the values for sample task 127 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample127) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
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
					double cv$distributionAccumulator = DistributionSampling.logProbabilityGamma(lambda[t], 10.0, 10.0);
					
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
					logProbability$var116[t] = cv$distributionAccumulator;
					
					// Store the sample task probability
					logProbability$sample127[t] = cv$distributionAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample127)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample127 = fixedFlag$sample127;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Variable declaration of cv$rvAccumulator moved.
					double cv$rvAccumulator = logProbability$sample127[t];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var116[t] = cv$rvAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample127)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample129 using sampled
	// values.
	private final void logProbabilityValue$sample129() {
		// Determine if we need to calculate the values for sample task 129 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample129) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Reduction of array null
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					int reduceVar$numSales$13 = 0;
					
					// For each index in the array to be reduced
					for(int cv$reduction115Index = 0; cv$reduction115Index < ObsSales[t].length; cv$reduction115Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l$var109's comment
						// Set the right hand term to a value from the array var101
						reduceVar$numSales$13 = (reduceVar$numSales$13 + ObsSales[t][cv$reduction115Index]);
					
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
					double cv$distributionAccumulator = DistributionSampling.logProbabilityPoisson((arrivals[t] - reduceVar$numSales$13), lambda[t]);
					
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
					logProbability$var118[t] = cv$distributionAccumulator;
					
					// Store the sample task probability
					logProbability$sample129[t] = cv$distributionAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample129)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample129 = (fixedFlag$sample129 && fixedFlag$sample127);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Variable declaration of cv$rvAccumulator moved.
					double cv$rvAccumulator = logProbability$sample129[t];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var118[t] = cv$rvAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample129)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample181 using sampled
	// values.
	private final void logProbabilityValue$sample181() {
		// Determine if we need to calculate the values for sample task 181 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample181) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
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
					double cv$distributionAccumulator = DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]);
					
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
					logProbability$var167[t] = cv$distributionAccumulator;
					
					// Store the sample task probability
					logProbability$sample181[t] = cv$distributionAccumulator;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					if((0 < avail[0].length))
						// Update the variable probability
						logProbability$sales = (logProbability$sales + cv$distributionAccumulator);
				}
			}
			
			// Update the variable probability
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample181 = ((fixedFlag$sample181 && fixedFlag$sample45) && fixedFlag$sample129);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$sampleValue = logProbability$sample181[t];
					cv$accumulator = (cv$accumulator + cv$sampleValue);
					logProbability$var167[t] = cv$sampleValue;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					if((0 < avail[0].length))
						// Update the variable probability
						logProbability$sales = (logProbability$sales + cv$sampleValue);
				}
			}
			
			// Update the variable probability
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
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
			if((0 < numTimeSteps)) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int var38 = 0; var38 < avail[0].length; var38 += 1) {
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
					double cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(ut[var38]);
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
					
					// Store the sample task probability
					logProbability$sample45[var38] = cv$distributionAccumulator;
					
					// Guard to ensure that expedNorm is only updated once for this probability.
					boolean cv$guard$expedNorm = false;
					
					// Guard to ensure that weekly_ut is only updated once for this probability.
					boolean cv$guard$weekly_ut = false;
					
					// Guard to ensure that weekly_rates is only updated once for this probability.
					boolean cv$guard$weekly_rates = false;
					
					// Update the variable probability
					logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
					
					// Looking for a path between Sample 45 and consumer double[] 79.
					// 
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 < avail[0].length)) {
						// Set the guard so the update is only applied once.
						cv$guard$expedNorm = true;
						
						// Update the variable probability
						logProbability$expedNorm = (logProbability$expedNorm + cv$distributionAccumulator);
					}
					
					// Substituted "j$var50" with its value "var38".
					if(!cv$guard$expedNorm)
						// Update the variable probability
						logProbability$expedNorm = (logProbability$expedNorm + cv$distributionAccumulator);
					
					// Looking for a path between Sample 45 and consumer double[] 138.
					for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((avail[t][j$var75] && !cv$guard$weekly_ut)) {
								// Set the guard so the update is only applied once.
								cv$guard$weekly_ut = true;
								
								// Update the variable probability
								logProbability$weekly_ut = (logProbability$weekly_ut + cv$distributionAccumulator);
							}
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((!cv$guard$weekly_ut && avail[t][var38])) {
							// Set the guard so the update is only applied once.
							cv$guard$weekly_ut = true;
							
							// Update the variable probability
							logProbability$weekly_ut = (logProbability$weekly_ut + cv$distributionAccumulator);
						}
					}
					
					// Looking for a path between Sample 45 and consumer double[] 166.
					for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((avail[t][j$var75] && !cv$guard$weekly_rates)) {
								// Set the guard so the update is only applied once.
								cv$guard$weekly_rates = true;
								
								// Update the variable probability
								logProbability$weekly_rates = (logProbability$weekly_rates + cv$distributionAccumulator);
							}
						}
					}
					for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((avail[t][j$var75] && !cv$guard$weekly_rates)) {
								// Set the guard so the update is only applied once.
								cv$guard$weekly_rates = true;
								
								// Update the variable probability
								logProbability$weekly_rates = (logProbability$weekly_rates + cv$distributionAccumulator);
							}
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((!cv$guard$weekly_rates && avail[t][var38])) {
							// Set the guard so the update is only applied once.
							cv$guard$weekly_rates = true;
							
							// Update the variable probability
							logProbability$weekly_rates = (logProbability$weekly_rates + cv$distributionAccumulator);
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((!cv$guard$weekly_rates && avail[t][var38])) {
							// Set the guard so the update is only applied once.
							cv$guard$weekly_rates = true;
							
							// Update the variable probability
							logProbability$weekly_rates = (logProbability$weekly_rates + cv$distributionAccumulator);
						}
					}
				}
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				cv$accumulator = cv$sampleAccumulator;
				logProbability$var28 = cv$sampleAccumulator;
			}
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample45 = fixedFlag$sample45;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				double cv$rvAccumulator = 0.0;
				for(int var38 = 0; var38 < avail[0].length; var38 += 1) {
					double cv$sampleValue = logProbability$sample45[var38];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					
					// Guard to ensure that expedNorm is only updated once for this probability.
					boolean cv$guard$expedNorm = false;
					
					// Guard to ensure that weekly_ut is only updated once for this probability.
					boolean cv$guard$weekly_ut = false;
					
					// Guard to ensure that weekly_rates is only updated once for this probability.
					boolean cv$guard$weekly_rates = false;
					
					// Update the variable probability
					logProbability$exped = (logProbability$exped + cv$sampleValue);
					
					// Looking for a path between Sample 45 and consumer double[] 79.
					// 
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 < avail[0].length)) {
						// Set the guard so the update is only applied once.
						cv$guard$expedNorm = true;
						
						// Update the variable probability
						logProbability$expedNorm = (logProbability$expedNorm + cv$sampleValue);
					}
					
					// Substituted "j$var50" with its value "var38".
					if(!cv$guard$expedNorm)
						// Update the variable probability
						logProbability$expedNorm = (logProbability$expedNorm + cv$sampleValue);
					
					// Looking for a path between Sample 45 and consumer double[] 138.
					for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((avail[t][j$var75] && !cv$guard$weekly_ut)) {
								// Set the guard so the update is only applied once.
								cv$guard$weekly_ut = true;
								
								// Update the variable probability
								logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleValue);
							}
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((!cv$guard$weekly_ut && avail[t][var38])) {
							// Set the guard so the update is only applied once.
							cv$guard$weekly_ut = true;
							
							// Update the variable probability
							logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleValue);
						}
					}
					
					// Looking for a path between Sample 45 and consumer double[] 166.
					for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((avail[t][j$var75] && !cv$guard$weekly_rates)) {
								// Set the guard so the update is only applied once.
								cv$guard$weekly_rates = true;
								
								// Update the variable probability
								logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
							}
						}
					}
					for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((avail[t][j$var75] && !cv$guard$weekly_rates)) {
								// Set the guard so the update is only applied once.
								cv$guard$weekly_rates = true;
								
								// Update the variable probability
								logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
							}
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((!cv$guard$weekly_rates && avail[t][var38])) {
							// Set the guard so the update is only applied once.
							cv$guard$weekly_rates = true;
							
							// Update the variable probability
							logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((!cv$guard$weekly_rates && avail[t][var38])) {
							// Set the guard so the update is only applied once.
							cv$guard$weekly_rates = true;
							
							// Update the variable probability
							logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
						}
					}
				}
				cv$accumulator = cv$rvAccumulator;
				logProbability$var28 = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 127 drawn from Gamma 116. Inference was performed using a Gamma
	// to Poisson conjugate prior.
	private final void sample127(int t, int threadID$cv$t, Rng RNG$) {
		// Processing random variable 118.
		// 
		// Processing sample task 129 of consumer random variable null.
		// 
		// Reduction of array null
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		int reduceVar$numSales$9 = 0;
		
		// For each index in the array to be reduced
		for(int cv$reduction115Index = 0; cv$reduction115Index < ObsSales[t].length; cv$reduction115Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// l$var109's comment
			// Set the right hand term to a value from the array var101
			reduceVar$numSales$9 = (reduceVar$numSales$9 + ObsSales[t][cv$reduction115Index]);
		
		// Write out the new value of the sample.
		// 
		// Variable to record the number of samples from consuming random variables.
		lambda[t] = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, (arrivals[t] - reduceVar$numSales$9), 1);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 129 drawn from Poisson 118. Inference was performed using Metropolis-Hastings.
	private final void sample129(int t, int threadID$cv$t, Rng RNG$) {
		// Reduction of array null
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		int reduceVar$numSales$10 = 0;
		
		// For each index in the array to be reduced
		for(int cv$reduction115Index = 0; cv$reduction115Index < ObsSales[t].length; cv$reduction115Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// l$var109's comment
			// Set the right hand term to a value from the array var101
			reduceVar$numSales$10 = (reduceVar$numSales$10 + ObsSales[t][cv$reduction115Index]);
		
		// The original value of the sample
		int cv$originalValue = (arrivals[t] - reduceVar$numSales$10);
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		
		// Ensure the variance is at least 1
		if((cv$var < 1.0))
			cv$var = 1.0;
		
		// An offset for the current value
		double cv$offset = (Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$));
		
		// Make sure the offset is not 0
		cv$offset = ((cv$offset <= 0.0)?(cv$offset - 1):(cv$offset + 1));
		
		// The proposed new value for the sample
		int cv$proposedValue = (cv$originalValue + (int)cv$offset);
		
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
		// Recorded the probability of reaching sample task 181 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$lambda" with its value "lambda[t]".
		// 
		// Set the current value to the current state of the tree.
		double cv$originalProbability = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + DistributionSampling.logProbabilityPoisson(cv$originalValue, lambda[t]));
		
		// Update Sample and intermediate values
		// 
		// Reduction of array null
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		int reduceVar$numSales$11 = 0;
		
		// For each index in the array to be reduced
		for(int cv$reduction115Index = 0; cv$reduction115Index < ObsSales[t].length; cv$reduction115Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// l$var109's comment
			// Set the right hand term to a value from the array var101
			reduceVar$numSales$11 = (reduceVar$numSales$11 + ObsSales[t][cv$reduction115Index]);
		
		// Write out the new sample value.
		arrivals[t] = (reduceVar$numSales$11 + cv$proposedValue);
		
		// Variable declaration of cv$accumulatedProbabilities moved.
		// Declaration comment was:
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$lambda" with its value "lambda[t]".
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 181 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$lambda" with its value "lambda[t]".
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + DistributionSampling.logProbabilityPoisson(cv$proposedValue, lambda[t]));
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// The probability ration for the proposed value and the current value.
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			// If it is not revert the changes.
			// 
			// Set the sample value
			// Reduction of array null
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			int reduceVar$numSales$12 = 0;
			
			// For each index in the array to be reduced
			for(int cv$reduction115Index = 0; cv$reduction115Index < ObsSales[t].length; cv$reduction115Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l$var109's comment
				// Set the right hand term to a value from the array var101
				reduceVar$numSales$12 = (reduceVar$numSales$12 + ObsSales[t][cv$reduction115Index]);
			
			// Write out the new sample value.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			arrivals[t] = (reduceVar$numSales$12 + cv$originalValue);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 45 drawn from Gaussian 28. Inference was performed using Metropolis-Hastings.
	private final void sample45(int var38) {
		// The original value of the sample
		double cv$originalValue = ut[var38];
		
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
		
		// Unrolled loop
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var27" with its value "1.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$originalValue);
			for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(avail[t][j$var75])
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample45multinomial180$global[t] = false;
				}
			}
			for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(avail[t][j$var75])
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample45multinomial180$global[t] = false;
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(avail[t][var38])
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample45multinomial180$global[t] = false;
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(avail[t][var38])
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample45multinomial180$global[t] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((0 < exped.length) && (0 < avail[0].length))) {
				for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
					for(int t = 0; t < numTimeSteps; t += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((((0 < weekly_ut[t].length) && avail[t][j$var75]) && !guard$sample45multinomial180$global[t])) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							guard$sample45multinomial180$global[t] = true;
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 181 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Processing sample task 181 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "cv$temp$3$arrivals" with its value "arrivals[t]".
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
						}
					}
				}
				for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
					for(int t = 0; t < numTimeSteps; t += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((avail[t][j$var75] && !guard$sample45multinomial180$global[t])) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							guard$sample45multinomial180$global[t] = true;
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 181 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Processing sample task 181 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "cv$temp$5$arrivals" with its value "arrivals[t]".
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((((0 < weekly_ut[t].length) && !guard$sample45multinomial180$global[t]) && avail[t][var38])) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample45multinomial180$global[t] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 181 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 181 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$7$arrivals" with its value "arrivals[t]".
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((!guard$sample45multinomial180$global[t] && avail[t][var38])) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample45multinomial180$global[t] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 181 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 181 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$9$arrivals" with its value "arrivals[t]".
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
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
		
		// Update Sample and intermediate values
		ut[var38] = cv$proposedValue;
		
		// Guards to ensure that exped is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 45 and consumer double[] 53.
		// 
		// Substituted "j$var50" with its value "var38".
		exped[var38] = Math.exp(ut[var38]);
		
		// Guards to ensure that expedNorm is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 45 and consumer double[] 79.
		for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			guard$sample45put86$global[j$var75] = false;
		
		// Set the flags to false
		// 
		// Guard to check that at most one copy of the code is executed for a given random
		// variable instance.
		// 
		// Substituted "j$var75" with its value "var38".
		guard$sample45put86$global[var38] = false;
		for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			if(!guard$sample45put86$global[j$var75]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample45put86$global[j$var75] = true;
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$13 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction67Index = 0; cv$reduction67Index < avail[0].length; cv$reduction67Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l$var64's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$13 = (reduceVar$sum$13 + exped[cv$reduction67Index]);
				expedNorm[j$var75] = (exped[j$var75] / (reduceVar$sum$13 * 0.3));
			}
		}
		
		// Substituted "j$var50" with its value "var38".
		// 
		// Substituted "j$var75" with its value "var38".
		if(!guard$sample45put86$global[var38]) {
			// The body will execute, so should not be executed again
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var75" with its value "var38".
			guard$sample45put86$global[var38] = true;
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$14 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction67Index = 0; cv$reduction67Index < avail[0].length; cv$reduction67Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l$var64's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$14 = (reduceVar$sum$14 + exped[cv$reduction67Index]);
			
			// Substituted "j$var75" with its value "var38".
			expedNorm[var38] = (exped[var38] / (reduceVar$sum$14 * 0.3));
		}
		
		// Guards to ensure that weekly_ut is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 45 and consumer double[] 138.
		for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(avail[t][j$var75])
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample45put150$global[t][j$var75] = false;
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(avail[t][var38])
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var75" with its value "var38".
				guard$sample45put150$global[t][var38] = false;
		}
		for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((avail[t][j$var75] && !guard$sample45put150$global[t][j$var75])) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample45put150$global[t][j$var75] = true;
					
					// Substituted "j$var131" with its value "j$var75".
					weekly_ut[t][j$var75] = expedNorm[j$var75];
				}
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!guard$sample45put150$global[t][var38] && avail[t][var38])) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var75" with its value "var38".
				guard$sample45put150$global[t][var38] = true;
				
				// Substituted "j$var131" with its value "j$var75".
				// 
				// Substituted "j$var75" with its value "var38".
				weekly_ut[t][var38] = expedNorm[var38];
			}
		}
		for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(avail[t][j$var75]) {
					for(int j$var163 = 0; j$var163 <= avail[0].length; j$var163 += 1)
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample45put179$global[t][j$var163] = false;
				}
			}
		}
		for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(avail[t][j$var75])
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var163" with its value "j$var75".
					guard$sample45put179$global[t][j$var75] = false;
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(avail[t][var38]) {
				for(int j$var163 = 0; j$var163 <= avail[0].length; j$var163 += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample45put179$global[t][j$var163] = false;
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(avail[t][var38])
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// j$var163's comment
				// Substituted "j$var75" with its value "var38".
				guard$sample45put179$global[t][var38] = false;
		}
		for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(avail[t][j$var75]) {
					for(int j$var163 = 0; j$var163 <= avail[0].length; j$var163 += 1) {
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						if(!guard$sample45put179$global[t][j$var163]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							guard$sample45put179$global[t][j$var163] = true;
							
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$15 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction159Index = 0; cv$reduction159Index <= avail[0].length; cv$reduction159Index += 1)
								// Execute the reduction function, saving the result into the return value.
								// 
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l$var150's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$15 = (reduceVar$denom$15 + weekly_ut[t][cv$reduction159Index]);
							weekly_rates[t][j$var163] = (weekly_ut[t][j$var163] / reduceVar$denom$15);
						}
					}
				}
			}
		}
		for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((avail[t][j$var75] && !guard$sample45put179$global[t][j$var75])) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var163" with its value "j$var75".
					guard$sample45put179$global[t][j$var75] = true;
					
					// Reduction of array weekly_ut
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$denom$16 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction159Index = 0; cv$reduction159Index <= avail[0].length; cv$reduction159Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l$var150's comment
						// Set the right hand term to a value from the array weekly_ut
						reduceVar$denom$16 = (reduceVar$denom$16 + weekly_ut[t][cv$reduction159Index]);
					
					// Substituted "j$var163" with its value "j$var75".
					weekly_rates[t][j$var75] = (weekly_ut[t][j$var75] / reduceVar$denom$16);
				}
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(avail[t][var38]) {
				for(int j$var163 = 0; j$var163 <= avail[0].length; j$var163 += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!guard$sample45put179$global[t][j$var163]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample45put179$global[t][j$var163] = true;
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$17 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction159Index = 0; cv$reduction159Index <= avail[0].length; cv$reduction159Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
							// l$var150's comment
							// Set the right hand term to a value from the array weekly_ut
							reduceVar$denom$17 = (reduceVar$denom$17 + weekly_ut[t][cv$reduction159Index]);
						weekly_rates[t][j$var163] = (weekly_ut[t][j$var163] / reduceVar$denom$17);
					}
				}
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((avail[t][var38] && !guard$sample45put179$global[t][var38])) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// j$var163's comment
				// Substituted "j$var75" with its value "var38".
				guard$sample45put179$global[t][var38] = true;
				
				// Reduction of array weekly_ut
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$denom$18 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction159Index = 0; cv$reduction159Index <= avail[0].length; cv$reduction159Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l$var150's comment
					// Set the right hand term to a value from the array weekly_ut
					reduceVar$denom$18 = (reduceVar$denom$18 + weekly_ut[t][cv$reduction159Index]);
				
				// j$var163's comment
				// Substituted "j$var75" with its value "var38".
				weekly_rates[t][var38] = (weekly_ut[t][var38] / reduceVar$denom$18);
			}
		}
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var27" with its value "1.0".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$proposedValue);
		for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(avail[t][j$var75])
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample45multinomial180$global[t] = false;
			}
		}
		for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(avail[t][j$var75])
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample45multinomial180$global[t] = false;
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(avail[t][var38])
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample45multinomial180$global[t] = false;
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(avail[t][var38])
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample45multinomial180$global[t] = false;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(((0 < exped.length) && (0 < avail[0].length))) {
			for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((((0 < weekly_ut[t].length) && avail[t][j$var75]) && !guard$sample45multinomial180$global[t])) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample45multinomial180$global[t] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 181 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 181 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "cv$temp$3$arrivals" with its value "arrivals[t]".
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((avail[t][j$var75] && !guard$sample45multinomial180$global[t])) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample45multinomial180$global[t] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 181 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 181 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "cv$temp$5$arrivals" with its value "arrivals[t]".
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
					}
				}
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((((0 < weekly_ut[t].length) && !guard$sample45multinomial180$global[t]) && avail[t][var38])) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample45multinomial180$global[t] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 181 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 181 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$7$arrivals" with its value "arrivals[t]".
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!guard$sample45multinomial180$global[t] && avail[t][var38])) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample45multinomial180$global[t] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 181 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 181 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$9$arrivals" with its value "arrivals[t]".
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
			}
		}
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// The probability ration for the proposed value and the current value.
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			// If it is not revert the changes.
			// 
			// Set the sample value
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			ut[var38] = cv$originalValue;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 45 and consumer double[] 53.
			// 
			// Substituted "j$var50" with its value "var38".
			exped[var38] = Math.exp(ut[var38]);
			
			// Guards to ensure that expedNorm is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 45 and consumer double[] 79.
			for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample45put86$global[j$var75] = false;
			
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var75" with its value "var38".
			guard$sample45put86$global[var38] = false;
			for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!guard$sample45put86$global[j$var75]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample45put86$global[j$var75] = true;
					
					// Reduction of array exped
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$sum$19 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction67Index = 0; cv$reduction67Index < avail[0].length; cv$reduction67Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l$var64's comment
						// Set the right hand term to a value from the array exped
						reduceVar$sum$19 = (reduceVar$sum$19 + exped[cv$reduction67Index]);
					expedNorm[j$var75] = (exped[j$var75] / (reduceVar$sum$19 * 0.3));
				}
			}
			
			// Substituted "j$var50" with its value "var38".
			// 
			// Substituted "j$var75" with its value "var38".
			if(!guard$sample45put86$global[var38]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var75" with its value "var38".
				guard$sample45put86$global[var38] = true;
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$20 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction67Index = 0; cv$reduction67Index < avail[0].length; cv$reduction67Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l$var64's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$20 = (reduceVar$sum$20 + exped[cv$reduction67Index]);
				
				// Substituted "j$var75" with its value "var38".
				expedNorm[var38] = (exped[var38] / (reduceVar$sum$20 * 0.3));
			}
			
			// Guards to ensure that weekly_ut is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 45 and consumer double[] 138.
			for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(avail[t][j$var75])
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample45put150$global[t][j$var75] = false;
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(avail[t][var38])
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var75" with its value "var38".
					guard$sample45put150$global[t][var38] = false;
			}
			for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((avail[t][j$var75] && !guard$sample45put150$global[t][j$var75])) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample45put150$global[t][j$var75] = true;
						
						// Substituted "j$var131" with its value "j$var75".
						weekly_ut[t][j$var75] = expedNorm[j$var75];
					}
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((!guard$sample45put150$global[t][var38] && avail[t][var38])) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var75" with its value "var38".
					guard$sample45put150$global[t][var38] = true;
					
					// Substituted "j$var131" with its value "j$var75".
					// 
					// Substituted "j$var75" with its value "var38".
					weekly_ut[t][var38] = expedNorm[var38];
				}
			}
			for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(avail[t][j$var75]) {
						for(int j$var163 = 0; j$var163 <= avail[0].length; j$var163 += 1)
							// Set the flags to false
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							guard$sample45put179$global[t][j$var163] = false;
					}
				}
			}
			for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(avail[t][j$var75])
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "j$var163" with its value "j$var75".
						guard$sample45put179$global[t][j$var75] = false;
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(avail[t][var38]) {
					for(int j$var163 = 0; j$var163 <= avail[0].length; j$var163 += 1)
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample45put179$global[t][j$var163] = false;
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(avail[t][var38])
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// j$var163's comment
					// Substituted "j$var75" with its value "var38".
					guard$sample45put179$global[t][var38] = false;
			}
			for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(avail[t][j$var75]) {
						for(int j$var163 = 0; j$var163 <= avail[0].length; j$var163 += 1) {
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							if(!guard$sample45put179$global[t][j$var163]) {
								// The body will execute, so should not be executed again
								// 
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								guard$sample45put179$global[t][j$var163] = true;
								
								// Reduction of array weekly_ut
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								double reduceVar$denom$21 = 0.0;
								
								// For each index in the array to be reduced
								for(int cv$reduction159Index = 0; cv$reduction159Index <= avail[0].length; cv$reduction159Index += 1)
									// Execute the reduction function, saving the result into the return value.
									// 
									// Copy the result of the reduction into the variable returned by the reduction.
									// 
									// l$var150's comment
									// Set the right hand term to a value from the array weekly_ut
									reduceVar$denom$21 = (reduceVar$denom$21 + weekly_ut[t][cv$reduction159Index]);
								weekly_rates[t][j$var163] = (weekly_ut[t][j$var163] / reduceVar$denom$21);
							}
						}
					}
				}
			}
			for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((avail[t][j$var75] && !guard$sample45put179$global[t][j$var75])) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "j$var163" with its value "j$var75".
						guard$sample45put179$global[t][j$var75] = true;
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$22 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction159Index = 0; cv$reduction159Index <= avail[0].length; cv$reduction159Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
							// l$var150's comment
							// Set the right hand term to a value from the array weekly_ut
							reduceVar$denom$22 = (reduceVar$denom$22 + weekly_ut[t][cv$reduction159Index]);
						
						// Substituted "j$var163" with its value "j$var75".
						weekly_rates[t][j$var75] = (weekly_ut[t][j$var75] / reduceVar$denom$22);
					}
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(avail[t][var38]) {
					for(int j$var163 = 0; j$var163 <= avail[0].length; j$var163 += 1) {
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						if(!guard$sample45put179$global[t][j$var163]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							guard$sample45put179$global[t][j$var163] = true;
							
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$23 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction159Index = 0; cv$reduction159Index <= avail[0].length; cv$reduction159Index += 1)
								// Execute the reduction function, saving the result into the return value.
								// 
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l$var150's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$23 = (reduceVar$denom$23 + weekly_ut[t][cv$reduction159Index]);
							weekly_rates[t][j$var163] = (weekly_ut[t][j$var163] / reduceVar$denom$23);
						}
					}
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((avail[t][var38] && !guard$sample45put179$global[t][var38])) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// j$var163's comment
					// Substituted "j$var75" with its value "var38".
					guard$sample45put179$global[t][var38] = true;
					
					// Reduction of array weekly_ut
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$denom$24 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction159Index = 0; cv$reduction159Index <= avail[0].length; cv$reduction159Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l$var150's comment
						// Set the right hand term to a value from the array weekly_ut
						reduceVar$denom$24 = (reduceVar$denom$24 + weekly_ut[t][cv$reduction159Index]);
					
					// j$var163's comment
					// Substituted "j$var75" with its value "var38".
					weekly_rates[t][var38] = (weekly_ut[t][var38] / reduceVar$denom$24);
				}
			}
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for guard$sample45put86$global
		// 
		// Calculate the largest index of j that is possible and allocate an array to hold
		// the guard for each of these.
		int cv$max_j$var75 = 0;
		if((0 < avail.length))
			cv$max_j$var75 = avail[0].length;
		
		// Allocation of guard$sample45put86$global for single threaded execution
		guard$sample45put86$global = new boolean[cv$max_j$var75];
		
		// Constructor for guard$sample45put150$global
		{
			// Calculate the largest index of t that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_t = 0;
			
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var131 = 0;
			if((0 < avail.length)) {
				cv$max_j$var131 = avail[0].length;
				cv$max_t = avail.length;
			}
			
			// Allocation of guard$sample45put150$global for single threaded execution
			guard$sample45put150$global = new boolean[cv$max_t][cv$max_j$var131];
		}
		
		// Constructor for guard$sample45put179$global
		{
			// Calculate the largest index of t that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_t = 0;
			
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var163 = 0;
			if((0 < avail.length)) {
				cv$max_j$var163 = (avail[0].length + 1);
				cv$max_t = avail.length;
			}
			
			// Allocation of guard$sample45put179$global for single threaded execution
			guard$sample45put179$global = new boolean[cv$max_t][cv$max_j$var163];
		}
		
		// Constructor for guard$sample45multinomial180$global
		// 
		// Calculate the largest index of t that is possible and allocate an array to hold
		// the guard for each of these.
		int cv$max_t = 0;
		if((0 < avail.length))
			cv$max_t = avail.length;
		
		// Allocation of guard$sample45multinomial180$global for single threaded execution
		guard$sample45multinomial180$global = new boolean[cv$max_t];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < numTimeSteps)) {
			// Constructor for ut
			ut = new double[avail[0].length];
			
			// Constructor for exped
			exped = new double[avail[0].length];
			
			// Constructor for expedNorm
			expedNorm = new double[avail[0].length];
			
			// Constructor for sales
			sales = new int[avail.length][];
			for(int var89 = 0; var89 < avail.length; var89 += 1)
				sales[var89] = new int[avail[0].length];
		}
		
		// If lambda has not been set already allocate space.
		if(!setFlag$lambda)
			// Constructor for lambda
			lambda = new double[avail.length];
		
		// If arrivals has not been set already allocate space.
		if(!setFlag$arrivals)
			// Constructor for arrivals
			arrivals = new int[avail.length];
		
		// Constructor for weekly_rates
		if((0 < avail.length)) {
			for(int t = 0; t < avail.length; t += 1)
				weekly_rates[t] = new double[(avail[0].length + 1)];
		}
		weekly_rates = new double[avail.length][];
		
		// Constructor for weekly_ut
		if((0 < avail.length)) {
			for(int t = 0; t < avail.length; t += 1)
				weekly_ut[t] = new double[(avail[0].length + 1)];
		}
		weekly_ut = new double[avail.length][];
		
		// If weekly_sales has not been set already allocate space.
		if(!setFlag$weekly_sales) {
			// Constructor for weekly_sales
			if((0 < avail.length)) {
				for(int t = 0; t < avail.length; t += 1)
					weekly_sales[t] = new int[(avail[0].length + 1)];
			}
			weekly_sales = new int[avail.length][];
		}
		
		// Constructor for logProbability$sample45
		logProbability$sample45 = new double[avail[0].length];
		
		// Constructor for logProbability$var116
		logProbability$var116 = new double[avail.length];
		
		// Constructor for logProbability$sample127
		logProbability$sample127 = new double[avail.length];
		
		// Constructor for logProbability$var118
		logProbability$var118 = new double[avail.length];
		
		// Constructor for logProbability$sample129
		logProbability$sample129 = new double[avail.length];
		
		// Constructor for logProbability$var167
		logProbability$var167 = new double[avail.length];
		
		// Constructor for logProbability$sample181
		logProbability$sample181 = new double[avail.length];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if((0 < numTimeSteps)) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample45) {
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1)
								ut[var38] = DistributionSampling.sampleGaussian(RNG$1);
					}
				);
				
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var50, int forEnd$j$var50, int threadID$j$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int j$var50 = forStart$j$var50; j$var50 < forEnd$j$var50; j$var50 += 1)
								exped[j$var50] = Math.exp(ut[j$var50]);
					}
				);
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$21 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction67Index = 0; cv$reduction67Index < avail[0].length; cv$reduction67Index += 1)
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l$var64's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$21 = (reduceVar$sum$21 + exped[cv$reduction67Index]);
				
				// Alternative name for reduceVar$sum$21 to make it effectively final.
				double reduceVar$sum$21$1 = reduceVar$sum$21;
				
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var75, int forEnd$j$var75, int threadID$j$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int j$var75 = forStart$j$var75; j$var75 < forEnd$j$var75; j$var75 += 1)
								expedNorm[j$var75] = (exped[j$var75] / (reduceVar$sum$21$1 * 0.3));
					}
				);
			}
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							if(!fixedFlag$sample127)
								lambda[t] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(!fixedFlag$sample129) {
								// Reduction of array null
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								int reduceVar$numSales$14 = 0;
								
								// For each index in the array to be reduced
								for(int cv$reduction115Index = 0; cv$reduction115Index < ObsSales[t].length; cv$reduction115Index += 1)
									// Copy the result of the reduction into the variable returned by the reduction.
									// 
									// l$var109's comment
									// Set the right hand term to a value from the array var101
									reduceVar$numSales$14 = (reduceVar$numSales$14 + ObsSales[t][cv$reduction115Index]);
								arrivals[t] = (reduceVar$numSales$14 + DistributionSampling.samplePoisson(RNG$1, lambda[t]));
							}
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var131, int forEnd$j$var131, int threadID$j$var131, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var131 = forStart$j$var131; j$var131 < forEnd$j$var131; j$var131 += 1) {
											if(avail[t][j$var131]) {
												if(!fixedFlag$sample45)
													weekly_ut[t][j$var131] = expedNorm[j$var131];
											} else
												weekly_ut[t][j$var131] = 0.0;
										}
								}
							);
							weekly_ut[t][avail[0].length] = 1.0;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(!fixedFlag$sample45) {
								// Reduction of array weekly_ut
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								double reduceVar$denom$25 = 0.0;
								
								// For each index in the array to be reduced
								for(int cv$reduction159Index = 0; cv$reduction159Index <= avail[0].length; cv$reduction159Index += 1)
									// Copy the result of the reduction into the variable returned by the reduction.
									// 
									// l$var150's comment
									// Set the right hand term to a value from the array weekly_ut
									reduceVar$denom$25 = (reduceVar$denom$25 + weekly_ut[t][cv$reduction159Index]);
								
								// Alternative name for reduceVar$denom$25 to make it effectively final.
								double reduceVar$denom$25$2 = reduceVar$denom$25;
								
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
									(int forStart$j$var163, int forEnd$j$var163, int threadID$j$var163, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int j$var163 = forStart$j$var163; j$var163 < forEnd$j$var163; j$var163 += 1)
												weekly_rates[t][j$var163] = (weekly_ut[t][j$var163] / reduceVar$denom$25$2);
									}
								);
							}
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(!fixedFlag$sample181) {
								DistributionSampling.sampleMultinomial(RNG$1, weekly_rates[t], arrivals[t], weekly_sales[t]);
								int[] observed_weekly_sales = sales[t];
								
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, avail[0].length, 1,
									(int forStart$j$var178, int forEnd$j$var178, int threadID$j$var178, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int j$var178 = forStart$j$var178; j$var178 < forEnd$j$var178; j$var178 += 1)
												observed_weekly_sales[j$var178] = weekly_sales[t][j$var178];
									}
								);
							}
						}
				}
			);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if((0 < numTimeSteps)) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample45) {
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1)
								ut[var38] = DistributionSampling.sampleGaussian(RNG$1);
					}
				);
				
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var50, int forEnd$j$var50, int threadID$j$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int j$var50 = forStart$j$var50; j$var50 < forEnd$j$var50; j$var50 += 1)
								exped[j$var50] = Math.exp(ut[j$var50]);
					}
				);
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$23 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction67Index = 0; cv$reduction67Index < avail[0].length; cv$reduction67Index += 1)
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l$var64's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$23 = (reduceVar$sum$23 + exped[cv$reduction67Index]);
				
				// Alternative name for reduceVar$sum$23 to make it effectively final.
				double reduceVar$sum$23$1 = reduceVar$sum$23;
				
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var75, int forEnd$j$var75, int threadID$j$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int j$var75 = forStart$j$var75; j$var75 < forEnd$j$var75; j$var75 += 1)
								expedNorm[j$var75] = (exped[j$var75] / (reduceVar$sum$23$1 * 0.3));
					}
				);
			}
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							if(!fixedFlag$sample127)
								lambda[t] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(!fixedFlag$sample129) {
								// Reduction of array null
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								int reduceVar$numSales$16 = 0;
								
								// For each index in the array to be reduced
								for(int cv$reduction115Index = 0; cv$reduction115Index < ObsSales[t].length; cv$reduction115Index += 1)
									// Copy the result of the reduction into the variable returned by the reduction.
									// 
									// l$var109's comment
									// Set the right hand term to a value from the array var101
									reduceVar$numSales$16 = (reduceVar$numSales$16 + ObsSales[t][cv$reduction115Index]);
								arrivals[t] = (reduceVar$numSales$16 + DistributionSampling.samplePoisson(RNG$1, lambda[t]));
							}
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var131, int forEnd$j$var131, int threadID$j$var131, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var131 = forStart$j$var131; j$var131 < forEnd$j$var131; j$var131 += 1) {
											if(avail[t][j$var131]) {
												if(!fixedFlag$sample45)
													weekly_ut[t][j$var131] = expedNorm[j$var131];
											} else
												weekly_ut[t][j$var131] = 0.0;
										}
								}
							);
							weekly_ut[t][avail[0].length] = 1.0;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(!fixedFlag$sample45) {
								// Reduction of array weekly_ut
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								double reduceVar$denom$27 = 0.0;
								
								// For each index in the array to be reduced
								for(int cv$reduction159Index = 0; cv$reduction159Index <= avail[0].length; cv$reduction159Index += 1)
									// Copy the result of the reduction into the variable returned by the reduction.
									// 
									// l$var150's comment
									// Set the right hand term to a value from the array weekly_ut
									reduceVar$denom$27 = (reduceVar$denom$27 + weekly_ut[t][cv$reduction159Index]);
								
								// Alternative name for reduceVar$denom$27 to make it effectively final.
								double reduceVar$denom$27$2 = reduceVar$denom$27;
								
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
									(int forStart$j$var163, int forEnd$j$var163, int threadID$j$var163, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int j$var163 = forStart$j$var163; j$var163 < forEnd$j$var163; j$var163 += 1)
												weekly_rates[t][j$var163] = (weekly_ut[t][j$var163] / reduceVar$denom$27$2);
									}
								);
							}
						}
				}
			);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if((0 < numTimeSteps)) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample45) {
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1)
								ut[var38] = DistributionSampling.sampleGaussian(RNG$1);
					}
				);
				
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var50, int forEnd$j$var50, int threadID$j$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int j$var50 = forStart$j$var50; j$var50 < forEnd$j$var50; j$var50 += 1)
								exped[j$var50] = Math.exp(ut[j$var50]);
					}
				);
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$22 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction67Index = 0; cv$reduction67Index < avail[0].length; cv$reduction67Index += 1)
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l$var64's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$22 = (reduceVar$sum$22 + exped[cv$reduction67Index]);
				
				// Alternative name for reduceVar$sum$22 to make it effectively final.
				double reduceVar$sum$22$1 = reduceVar$sum$22;
				
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var75, int forEnd$j$var75, int threadID$j$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int j$var75 = forStart$j$var75; j$var75 < forEnd$j$var75; j$var75 += 1)
								expedNorm[j$var75] = (exped[j$var75] / (reduceVar$sum$22$1 * 0.3));
					}
				);
			}
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							if(!fixedFlag$sample127)
								lambda[t] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(!fixedFlag$sample129) {
								// Reduction of array null
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								int reduceVar$numSales$15 = 0;
								
								// For each index in the array to be reduced
								for(int cv$reduction115Index = 0; cv$reduction115Index < ObsSales[t].length; cv$reduction115Index += 1)
									// Copy the result of the reduction into the variable returned by the reduction.
									// 
									// l$var109's comment
									// Set the right hand term to a value from the array var101
									reduceVar$numSales$15 = (reduceVar$numSales$15 + ObsSales[t][cv$reduction115Index]);
								arrivals[t] = (reduceVar$numSales$15 + DistributionSampling.samplePoisson(RNG$1, lambda[t]));
							}
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var131, int forEnd$j$var131, int threadID$j$var131, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var131 = forStart$j$var131; j$var131 < forEnd$j$var131; j$var131 += 1) {
											if(avail[t][j$var131]) {
												if(!fixedFlag$sample45)
													weekly_ut[t][j$var131] = expedNorm[j$var131];
											} else
												weekly_ut[t][j$var131] = 0.0;
										}
								}
							);
							weekly_ut[t][avail[0].length] = 1.0;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(!fixedFlag$sample45) {
								// Reduction of array weekly_ut
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								double reduceVar$denom$26 = 0.0;
								
								// For each index in the array to be reduced
								for(int cv$reduction159Index = 0; cv$reduction159Index <= avail[0].length; cv$reduction159Index += 1)
									// Copy the result of the reduction into the variable returned by the reduction.
									// 
									// l$var150's comment
									// Set the right hand term to a value from the array weekly_ut
									reduceVar$denom$26 = (reduceVar$denom$26 + weekly_ut[t][cv$reduction159Index]);
								
								// Alternative name for reduceVar$denom$26 to make it effectively final.
								double reduceVar$denom$26$2 = reduceVar$denom$26;
								
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
									(int forStart$j$var163, int forEnd$j$var163, int threadID$j$var163, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int j$var163 = forStart$j$var163; j$var163 < forEnd$j$var163; j$var163 += 1)
												weekly_rates[t][j$var163] = (weekly_ut[t][j$var163] / reduceVar$denom$26$2);
									}
								);
							}
						}
				}
			);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if((0 < numTimeSteps)) {
			// Infer the samples in chronological order.
			if(system$gibbsForward) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample45) {
					for(int var38 = 0; var38 < avail[0].length; var38 += 1)
						sample45(var38);
				}
				
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, numTimeSteps, 1,
					(int forStart$t, int forEnd$t, int threadID$t, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int t = forStart$t; t < forEnd$t; t += 1) {
								if(!fixedFlag$sample127)
									sample127(t, threadID$t, RNG$1);
								if(!fixedFlag$sample129)
									sample129(t, threadID$t, RNG$1);
							}
					}
				);
			}
			// Infer the samples in reverse chronological order.
			else {
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, numTimeSteps, 1,
					(int forStart$t, int forEnd$t, int threadID$t, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int t = forStart$t; t < forEnd$t; t += 1) {
								if(!fixedFlag$sample129)
									sample129(t, threadID$t, RNG$1);
								if(!fixedFlag$sample127)
									sample127(t, threadID$t, RNG$1);
							}
					}
				);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample45) {
					for(int var38 = (avail[0].length - 1); var38 >= 0; var38 -= 1)
						sample45(var38);
				}
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		numTimeSteps = avail.length;
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
		logProbability$var28 = 0.0;
		logProbability$exped = 0.0;
		logProbability$weekly_rates = 0.0;
		logProbability$ut = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$expedNorm = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < numTimeSteps)) {
			if(!fixedProbFlag$sample45) {
				for(int var38 = 0; var38 < avail[0].length; var38 += 1)
					logProbability$sample45[var38] = 0.0;
			}
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var116[t] = 0.0;
		}
		logProbability$lambda = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < numTimeSteps)) {
			if(!fixedProbFlag$sample127) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample127[t] = 0.0;
			}
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var118[t] = 0.0;
		}
		logProbability$arrivals = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < numTimeSteps)) {
			if(!fixedProbFlag$sample129) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample129[t] = 0.0;
			}
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var167[t] = 0.0;
		}
		logProbability$sales = 0.0;
		logProbability$weekly_sales = 0.0;
		if((!fixedProbFlag$sample181 && (0 < numTimeSteps))) {
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$sample181[t] = 0.0;
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
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		if(fixedFlag$sample127)
			logProbabilityValue$sample127();
		if(fixedFlag$sample129)
			logProbabilityValue$sample129();
		logProbabilityValue$sample181();
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
		logProbabilityValue$sample45();
		logProbabilityValue$sample127();
		logProbabilityValue$sample129();
		logProbabilityValue$sample181();
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
		logProbabilityValue$sample45();
		logProbabilityValue$sample127();
		logProbabilityValue$sample129();
		logProbabilityValue$sample181();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if((0 < numTimeSteps)) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample45) {
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1)
								ut[var38] = DistributionSampling.sampleGaussian(RNG$1);
					}
				);
				
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var50, int forEnd$j$var50, int threadID$j$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int j$var50 = forStart$j$var50; j$var50 < forEnd$j$var50; j$var50 += 1)
								exped[j$var50] = Math.exp(ut[j$var50]);
					}
				);
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$24 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction67Index = 0; cv$reduction67Index < avail[0].length; cv$reduction67Index += 1)
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l$var64's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$24 = (reduceVar$sum$24 + exped[cv$reduction67Index]);
				
				// Alternative name for reduceVar$sum$24 to make it effectively final.
				double reduceVar$sum$24$1 = reduceVar$sum$24;
				
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, avail[0].length, 1,
					(int forStart$j$var75, int forEnd$j$var75, int threadID$j$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int j$var75 = forStart$j$var75; j$var75 < forEnd$j$var75; j$var75 += 1)
								expedNorm[j$var75] = (exped[j$var75] / (reduceVar$sum$24$1 * 0.3));
					}
				);
			}
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							if(!fixedFlag$sample127)
								lambda[t] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(!fixedFlag$sample129) {
								// Reduction of array null
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								int reduceVar$numSales$17 = 0;
								
								// For each index in the array to be reduced
								for(int cv$reduction115Index = 0; cv$reduction115Index < ObsSales[t].length; cv$reduction115Index += 1)
									// Copy the result of the reduction into the variable returned by the reduction.
									// 
									// l$var109's comment
									// Set the right hand term to a value from the array var101
									reduceVar$numSales$17 = (reduceVar$numSales$17 + ObsSales[t][cv$reduction115Index]);
								arrivals[t] = (reduceVar$numSales$17 + DistributionSampling.samplePoisson(RNG$1, lambda[t]));
							}
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var131, int forEnd$j$var131, int threadID$j$var131, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var131 = forStart$j$var131; j$var131 < forEnd$j$var131; j$var131 += 1) {
											if(avail[t][j$var131]) {
												if(!fixedFlag$sample45)
													weekly_ut[t][j$var131] = expedNorm[j$var131];
											} else
												weekly_ut[t][j$var131] = 0.0;
										}
								}
							);
							weekly_ut[t][avail[0].length] = 1.0;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(!fixedFlag$sample45) {
								// Reduction of array weekly_ut
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								double reduceVar$denom$28 = 0.0;
								
								// For each index in the array to be reduced
								for(int cv$reduction159Index = 0; cv$reduction159Index <= avail[0].length; cv$reduction159Index += 1)
									// Copy the result of the reduction into the variable returned by the reduction.
									// 
									// l$var150's comment
									// Set the right hand term to a value from the array weekly_ut
									reduceVar$denom$28 = (reduceVar$denom$28 + weekly_ut[t][cv$reduction159Index]);
								
								// Alternative name for reduceVar$denom$28 to make it effectively final.
								double reduceVar$denom$28$2 = reduceVar$denom$28;
								
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
									(int forStart$j$var163, int forEnd$j$var163, int threadID$j$var163, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int j$var163 = forStart$j$var163; j$var163 < forEnd$j$var163; j$var163 += 1)
												weekly_rates[t][j$var163] = (weekly_ut[t][j$var163] / reduceVar$denom$28$2);
									}
								);
							}
						}
				}
			);
		}
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propogateObservedValues() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < numTimeSteps)) {
			int cv$length1 = sales.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
				int[] cv$source2 = ObsSales[cv$index1];
				int[] cv$target2 = sales[cv$index1];
				int cv$length2 = cv$target2.length;
				for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
					cv$target2[cv$index2] = cv$source2[cv$index2];
			}
			for(int t = (numTimeSteps - 1); t >= 0; t -= 1) {
				for(int j$var178 = (avail[0].length - 1); j$var178 >= 0; j$var178 -= 1)
					// Substituted "observed_weekly_sales" with its value "sales[t]".
					weekly_sales[t][j$var178] = sales[t][j$var178];
			}
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		if((0 < numTimeSteps)) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var50, int forEnd$j$var50, int threadID$j$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var50 = forStart$j$var50; j$var50 < forEnd$j$var50; j$var50 += 1)
							exped[j$var50] = Math.exp(ut[j$var50]);
				}
			);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$25 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction67Index = 0; cv$reduction67Index < avail[0].length; cv$reduction67Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l$var64's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$25 = (reduceVar$sum$25 + exped[cv$reduction67Index]);
			
			// Alternative name for reduceVar$sum$25 to make it effectively final.
			double reduceVar$sum$25$1 = reduceVar$sum$25;
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var75, int forEnd$j$var75, int threadID$j$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j$var75 = forStart$j$var75; j$var75 < forEnd$j$var75; j$var75 += 1)
							expedNorm[j$var75] = (exped[j$var75] / (reduceVar$sum$25$1 * 0.3));
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var131, int forEnd$j$var131, int threadID$j$var131, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var131 = forStart$j$var131; j$var131 < forEnd$j$var131; j$var131 += 1) {
											if(avail[t][j$var131])
												weekly_ut[t][j$var131] = expedNorm[j$var131];
										}
								}
							);
							
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$29 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction159Index = 0; cv$reduction159Index <= avail[0].length; cv$reduction159Index += 1)
								// Execute the reduction function, saving the result into the return value.
								// 
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l$var150's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$29 = (reduceVar$denom$29 + weekly_ut[t][cv$reduction159Index]);
							
							// Alternative name for reduceVar$denom$29 to make it effectively final.
							double reduceVar$denom$29$2 = reduceVar$denom$29;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
								(int forStart$j$var163, int forEnd$j$var163, int threadID$j$var163, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j$var163 = forStart$j$var163; j$var163 < forEnd$j$var163; j$var163 += 1)
											weekly_rates[t][j$var163] = (weekly_ut[t][j$var163] / reduceVar$denom$29$2);
								}
							);
							if(setFlag$weekly_sales) {
								int[] observed_weekly_sales = sales[t];
								
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, avail[0].length, 1,
									(int forStart$j$var178, int forEnd$j$var178, int threadID$j$var178, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int j$var178 = forStart$j$var178; j$var178 < forEnd$j$var178; j$var178 += 1)
												observed_weekly_sales[j$var178] = weekly_sales[t][j$var178];
									}
								);
							}
						}
				}
			);
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n/*\n * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n */\npackage org.sandwood.compiler.tests.parser;\n\nmodel Vulcano2012basicDG(int[][] ObsSales, boolean[][] avail) {\n   // avail is the availability matrix, numTimeSteps-by-numProducts\n   // r is the normalization constant here, number between 0 and 1. \"Relative appeal of the outside option\"\n   double r = 0.3;\n    \n   int numTimeSteps = avail.length;\n   if(numTimeSteps > 0) {\n      int numProducts = avail[0].length;\n\n      // draw utilities\n      double[] ut = gaussian(0, 1).sample(numProducts);\n\n      //exponentiate right here (in the non-basic models move to the for loop)\n      double[] exped = new double[numProducts];\n      for(int j : [0..numProducts))\n         exped[j] = exp(ut[j]);\n\n      //Choices includes the choice to not buy anything.\n      int numChoices = numProducts + 1;\n\n      //now normalize\n      double[] expedNorm = new double[numProducts];\n      double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n      for(int j : [0..numProducts))\n         expedNorm[j] = exped[j]/(r*sum);\n\n      int[][] sales = new int[numTimeSteps][numProducts];\n\n      for (int t:[0..numTimeSteps)){\n         // Calculate the number of purchases made.\n         int numSales = reduce(ObsSales[t], 0, (k, l) -> { return k + l; });\n\n         // prior for the distribution of lambda for arrivals. These can be \n         // supplied as a vector if RGBU has some estimates, or just use some priors.\n         public double lambda = gamma(10,10).sample();\n         public int arrivals = numSales + poisson(lambda).sample();\n\n         // for each period t calculate choice probabilities and sales\n         double[] weekly_rates = new double[numChoices];\n         double[] weekly_ut = new double[numChoices];\n\n         for(int j : [0..numProducts)) {\n            if(avail[t][j])\n               weekly_ut[j] = expedNorm[j];\n            else\n               weekly_ut[j] = 0.0;\n         }\n         // Moved v_0 to the end of the array to keep indexing consistent everywhere else in \n         // the model and delayed the assignment of the value 1 to here. None of this is a \n         // sandwood requirement, I just thought it made the model eaiser to follow.\n         weekly_ut[numProducts] = 1.0;\n\n         double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n\n         for(int j : [0..numProducts]) \n            weekly_rates[j] = weekly_ut[j]/denom ;\n\n         int[] weekly_sales = multinomial(weekly_rates, arrivals).sample();\n\n         //getting rid of the no purchase observation (last one in the vector of weekly_sales)\n         int[] observed_weekly_sales = sales[t];\n         for (int j : [0..numProducts))\n            observed_weekly_sales[j] = weekly_sales[j] ;\n      }\n      // assert that generated sales match observed sales\n      sales.observe(ObsSales);\n   }\n}";
	}
}