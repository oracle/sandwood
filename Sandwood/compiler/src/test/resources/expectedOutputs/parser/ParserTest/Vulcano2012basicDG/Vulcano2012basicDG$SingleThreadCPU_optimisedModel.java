package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Vulcano2012basicDG$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Vulcano2012basicDG$CoreInterface {
	
	// Declare the variables for the model.
	private int[][] ObsSales;
	private int[] arrivals;
	private boolean[][] avail;
	private double[] exped;
	private double[] expedNorm;
	private boolean fixedFlag$sample125 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample88 = false;
	private boolean fixedFlag$sample90 = false;
	private boolean fixedProbFlag$sample125 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample88 = false;
	private boolean fixedProbFlag$sample90 = false;
	private boolean[] guard$sample34multinomial124$global;
	private boolean[][] guard$sample34put102$global;
	private boolean[][] guard$sample34put123$global;
	private boolean[] guard$sample34put61$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$arrivals;
	private double logProbability$exped;
	private double logProbability$expedNorm;
	private double logProbability$lambda;
	private double logProbability$sales;
	private double[] logProbability$sample125;
	private double[] logProbability$sample34;
	private double[] logProbability$sample88;
	private double[] logProbability$sample90;
	private double logProbability$ut;
	private double[] logProbability$var111;
	private double logProbability$var23;
	private double[] logProbability$var77;
	private double[] logProbability$var79;
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

	public Vulcano2012basicDG$SingleThreadCPU(ExecutionTarget target) {
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

	// Getter for fixedFlag$sample125.
	@Override
	public final boolean get$fixedFlag$sample125() {
		return fixedFlag$sample125;
	}

	// Setter for fixedFlag$sample125.
	@Override
	public final void set$fixedFlag$sample125(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample125 including if probabilities
		// need to be updated.
		fixedFlag$sample125 = cv$value;
		
		// Should the probability of sample 125 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample125" with its value "cv$value".
		fixedProbFlag$sample125 = (cv$value && fixedProbFlag$sample125);
	}

	// Getter for fixedFlag$sample34.
	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	// Setter for fixedFlag$sample34.
	@Override
	public final void set$fixedFlag$sample34(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample34 including if probabilities
		// need to be updated.
		fixedFlag$sample34 = cv$value;
		
		// Should the probability of sample 34 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample34" with its value "cv$value".
		fixedProbFlag$sample34 = (cv$value && fixedProbFlag$sample34);
		
		// Should the probability of sample 125 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample34" with its value "cv$value".
		fixedProbFlag$sample125 = (cv$value && fixedProbFlag$sample125);
	}

	// Getter for fixedFlag$sample88.
	@Override
	public final boolean get$fixedFlag$sample88() {
		return fixedFlag$sample88;
	}

	// Setter for fixedFlag$sample88.
	@Override
	public final void set$fixedFlag$sample88(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample88 including if probabilities
		// need to be updated.
		fixedFlag$sample88 = cv$value;
		
		// Should the probability of sample 88 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample88" with its value "cv$value".
		fixedProbFlag$sample88 = (cv$value && fixedProbFlag$sample88);
		
		// Should the probability of sample 90 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample88" with its value "cv$value".
		fixedProbFlag$sample90 = (cv$value && fixedProbFlag$sample90);
	}

	// Getter for fixedFlag$sample90.
	@Override
	public final boolean get$fixedFlag$sample90() {
		return fixedFlag$sample90;
	}

	// Setter for fixedFlag$sample90.
	@Override
	public final void set$fixedFlag$sample90(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample90 including if probabilities
		// need to be updated.
		fixedFlag$sample90 = cv$value;
		
		// Should the probability of sample 90 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample90" with its value "cv$value".
		fixedProbFlag$sample90 = (cv$value && fixedProbFlag$sample90);
		
		// Should the probability of sample 125 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample90" with its value "cv$value".
		fixedProbFlag$sample125 = (cv$value && fixedProbFlag$sample125);
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

	// Calculate the probability of the samples represented by sample125 using sampled
	// values.
	private final void logProbabilityValue$sample125() {
		// Determine if we need to calculate the values for sample task 125 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample125) {
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
					logProbability$var111[t] = cv$distributionAccumulator;
					
					// Store the sample task probability
					logProbability$sample125[t] = cv$distributionAccumulator;
					
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
			fixedProbFlag$sample125 = ((fixedFlag$sample125 && fixedFlag$sample34) && fixedFlag$sample90);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$sampleValue = logProbability$sample125[t];
					cv$accumulator = (cv$accumulator + cv$sampleValue);
					logProbability$var111[t] = cv$sampleValue;
					
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

	// Calculate the probability of the samples represented by sample34 using sampled
	// values.
	private final void logProbabilityValue$sample34() {
		// Determine if we need to calculate the values for sample task 34 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample34) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int var27 = 0; var27 < avail[0].length; var27 += 1) {
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
					double cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(ut[var27]);
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
					
					// Store the sample task probability
					logProbability$sample34[var27] = cv$distributionAccumulator;
					
					// Guard to ensure that expedNorm is only updated once for this probability.
					boolean cv$guard$expedNorm = false;
					
					// Guard to ensure that weekly_ut is only updated once for this probability.
					boolean cv$guard$weekly_ut = false;
					
					// Guard to ensure that weekly_rates is only updated once for this probability.
					boolean cv$guard$weekly_rates = false;
					
					// Update the variable probability
					logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
					
					// Looking for a path between Sample 34 and consumer double[] 54.
					// 
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 < avail[0].length)) {
						// Set the guard so the update is only applied once.
						cv$guard$expedNorm = true;
						
						// Update the variable probability
						logProbability$expedNorm = (logProbability$expedNorm + cv$distributionAccumulator);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!cv$guard$expedNorm)
						// Update the variable probability
						logProbability$expedNorm = (logProbability$expedNorm + cv$distributionAccumulator);
					
					// Looking for a path between Sample 34 and consumer double[] 90.
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((avail[t][j$var50] && !cv$guard$weekly_ut)) {
								// Set the guard so the update is only applied once.
								cv$guard$weekly_ut = true;
								
								// Update the variable probability
								logProbability$weekly_ut = (logProbability$weekly_ut + cv$distributionAccumulator);
							}
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((!cv$guard$weekly_ut && avail[t][var27])) {
							// Set the guard so the update is only applied once.
							cv$guard$weekly_ut = true;
							
							// Update the variable probability
							logProbability$weekly_ut = (logProbability$weekly_ut + cv$distributionAccumulator);
						}
					}
					
					// Looking for a path between Sample 34 and consumer double[] 110.
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((avail[t][j$var50] && !cv$guard$weekly_rates)) {
								// Set the guard so the update is only applied once.
								cv$guard$weekly_rates = true;
								
								// Update the variable probability
								logProbability$weekly_rates = (logProbability$weekly_rates + cv$distributionAccumulator);
							}
						}
					}
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((avail[t][j$var50] && !cv$guard$weekly_rates)) {
								// Set the guard so the update is only applied once.
								cv$guard$weekly_rates = true;
								
								// Update the variable probability
								logProbability$weekly_rates = (logProbability$weekly_rates + cv$distributionAccumulator);
							}
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Substituted "j$var50" with its value "var27".
						if((!cv$guard$weekly_rates && avail[t][var27])) {
							// Set the guard so the update is only applied once.
							cv$guard$weekly_rates = true;
							
							// Update the variable probability
							logProbability$weekly_rates = (logProbability$weekly_rates + cv$distributionAccumulator);
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Substituted "j$var50" with its value "var27".
						if((!cv$guard$weekly_rates && avail[t][var27])) {
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
				logProbability$var23 = cv$sampleAccumulator;
			}
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample34 = fixedFlag$sample34;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				double cv$rvAccumulator = 0.0;
				for(int var27 = 0; var27 < avail[0].length; var27 += 1) {
					double cv$sampleValue = logProbability$sample34[var27];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					
					// Guard to ensure that expedNorm is only updated once for this probability.
					boolean cv$guard$expedNorm = false;
					
					// Guard to ensure that weekly_ut is only updated once for this probability.
					boolean cv$guard$weekly_ut = false;
					
					// Guard to ensure that weekly_rates is only updated once for this probability.
					boolean cv$guard$weekly_rates = false;
					
					// Update the variable probability
					logProbability$exped = (logProbability$exped + cv$sampleValue);
					
					// Looking for a path between Sample 34 and consumer double[] 54.
					// 
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 < avail[0].length)) {
						// Set the guard so the update is only applied once.
						cv$guard$expedNorm = true;
						
						// Update the variable probability
						logProbability$expedNorm = (logProbability$expedNorm + cv$sampleValue);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!cv$guard$expedNorm)
						// Update the variable probability
						logProbability$expedNorm = (logProbability$expedNorm + cv$sampleValue);
					
					// Looking for a path between Sample 34 and consumer double[] 90.
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((avail[t][j$var50] && !cv$guard$weekly_ut)) {
								// Set the guard so the update is only applied once.
								cv$guard$weekly_ut = true;
								
								// Update the variable probability
								logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleValue);
							}
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((!cv$guard$weekly_ut && avail[t][var27])) {
							// Set the guard so the update is only applied once.
							cv$guard$weekly_ut = true;
							
							// Update the variable probability
							logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleValue);
						}
					}
					
					// Looking for a path between Sample 34 and consumer double[] 110.
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((avail[t][j$var50] && !cv$guard$weekly_rates)) {
								// Set the guard so the update is only applied once.
								cv$guard$weekly_rates = true;
								
								// Update the variable probability
								logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
							}
						}
					}
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						for(int t = 0; t < numTimeSteps; t += 1) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((avail[t][j$var50] && !cv$guard$weekly_rates)) {
								// Set the guard so the update is only applied once.
								cv$guard$weekly_rates = true;
								
								// Update the variable probability
								logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
							}
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Substituted "j$var50" with its value "var27".
						if((!cv$guard$weekly_rates && avail[t][var27])) {
							// Set the guard so the update is only applied once.
							cv$guard$weekly_rates = true;
							
							// Update the variable probability
							logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
						}
					}
					for(int t = 0; t < numTimeSteps; t += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Substituted "j$var50" with its value "var27".
						if((!cv$guard$weekly_rates && avail[t][var27])) {
							// Set the guard so the update is only applied once.
							cv$guard$weekly_rates = true;
							
							// Update the variable probability
							logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
						}
					}
				}
				cv$accumulator = cv$rvAccumulator;
				logProbability$var23 = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample88 using sampled
	// values.
	private final void logProbabilityValue$sample88() {
		// Determine if we need to calculate the values for sample task 88 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample88) {
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
					logProbability$var77[t] = cv$distributionAccumulator;
					
					// Store the sample task probability
					logProbability$sample88[t] = cv$distributionAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample88 = fixedFlag$sample88;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Variable declaration of cv$rvAccumulator moved.
					double cv$rvAccumulator = logProbability$sample88[t];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var77[t] = cv$rvAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample90 using sampled
	// values.
	private final void logProbabilityValue$sample90() {
		// Determine if we need to calculate the values for sample task 90 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample90) {
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
					int reduceVar$numSales$4 = 0;
					
					// For each index in the array to be reduced
					for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l$var70's comment
						// Set the right hand term to a value from the array var64
						reduceVar$numSales$4 = (reduceVar$numSales$4 + ObsSales[t][cv$reduction78Index]);
					
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
					double cv$distributionAccumulator = DistributionSampling.logProbabilityPoisson((arrivals[t] - reduceVar$numSales$4), lambda[t]);
					
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
					logProbability$var79[t] = cv$distributionAccumulator;
					
					// Store the sample task probability
					logProbability$sample90[t] = cv$distributionAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample90)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample90 = (fixedFlag$sample90 && fixedFlag$sample88);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Variable declaration of cv$rvAccumulator moved.
					double cv$rvAccumulator = logProbability$sample90[t];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var79[t] = cv$rvAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample90)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 34 drawn from Gaussian 23. Inference was performed using Metropolis-Hastings.
	private final void sample34(int var27) {
		// The original value of the sample
		double cv$originalValue = ut[var27];
		
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
			// Substituted "cv$temp$1$var22" with its value "1.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$originalValue);
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(avail[t][j$var50])
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample34multinomial124$global[t] = false;
				}
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(avail[t][j$var50])
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample34multinomial124$global[t] = false;
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Substituted "j$var50" with its value "var27".
				if(avail[t][var27])
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample34multinomial124$global[t] = false;
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Substituted "j$var50" with its value "var27".
				if(avail[t][var27])
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample34multinomial124$global[t] = false;
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((0 < exped.length) && (0 < avail[0].length))) {
				for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
					for(int t = 0; t < numTimeSteps; t += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((((0 < weekly_ut[t].length) && avail[t][j$var50]) && !guard$sample34multinomial124$global[t])) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							guard$sample34multinomial124$global[t] = true;
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 125 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Processing sample task 125 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// Substituted "cv$temp$3$arrivals" with its value "arrivals[t]".
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
						}
					}
				}
				for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
					for(int t = 0; t < numTimeSteps; t += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((avail[t][j$var50] && !guard$sample34multinomial124$global[t])) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							guard$sample34multinomial124$global[t] = true;
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 125 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Processing sample task 125 of consumer random variable null.
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
				// 
				// Substituted "j$var50" with its value "var27".
				if((((0 < weekly_ut[t].length) && !guard$sample34multinomial124$global[t]) && avail[t][var27])) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample34multinomial124$global[t] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 125 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 125 of consumer random variable null.
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
				// 
				// Substituted "j$var50" with its value "var27".
				if((!guard$sample34multinomial124$global[t] && avail[t][var27])) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample34multinomial124$global[t] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 125 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 125 of consumer random variable null.
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
		ut[var27] = cv$proposedValue;
		
		// Guards to ensure that exped is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 34 and consumer double[] 36.
		// 
		// Substituted "j$var33" with its value "var27".
		exped[var27] = Math.exp(ut[var27]);
		
		// Guards to ensure that expedNorm is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 34 and consumer double[] 54.
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1)
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			guard$sample34put61$global[j$var50] = false;
		
		// Set the flags to false
		// 
		// Guard to check that at most one copy of the code is executed for a given random
		// variable instance.
		// 
		// Substituted "j$var50" with its value "var27".
		guard$sample34put61$global[var27] = false;
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			if(!guard$sample34put61$global[j$var50]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample34put61$global[j$var50] = true;
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$0 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction277Index = 0; cv$reduction277Index < avail[0].length; cv$reduction277Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l$var45's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$0 = (reduceVar$sum$0 + exped[cv$reduction277Index]);
				expedNorm[j$var50] = (exped[j$var50] / (reduceVar$sum$0 * 0.3));
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Substituted "j$var50" with its value "var27".
		if(!guard$sample34put61$global[var27]) {
			// The body will execute, so should not be executed again
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var50" with its value "var27".
			guard$sample34put61$global[var27] = true;
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$1 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l$var45's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$1 = (reduceVar$sum$1 + exped[cv$reduction50Index]);
			
			// Substituted "j$var50" with its value "var27".
			expedNorm[var27] = (exped[var27] / (reduceVar$sum$1 * 0.3));
		}
		
		// Guards to ensure that weekly_ut is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 34 and consumer double[] 90.
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(avail[t][j$var50])
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample34put102$global[t][j$var50] = false;
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(avail[t][var27])
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var50" with its value "var27".
				guard$sample34put102$global[t][var27] = false;
		}
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((avail[t][j$var50] && !guard$sample34put102$global[t][j$var50])) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample34put102$global[t][j$var50] = true;
					
					// Substituted "j$var86" with its value "j$var50".
					weekly_ut[t][j$var50] = expedNorm[j$var50];
				}
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!guard$sample34put102$global[t][var27] && avail[t][var27])) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var50" with its value "var27".
				guard$sample34put102$global[t][var27] = true;
				
				// Substituted "j$var86" with its value "j$var50".
				// 
				// Substituted "j$var50" with its value "var27".
				weekly_ut[t][var27] = expedNorm[var27];
			}
		}
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(avail[t][j$var50]) {
					for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1)
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample34put123$global[t][j$var107] = false;
				}
			}
		}
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(avail[t][j$var50])
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var107" with its value "j$var50".
					guard$sample34put123$global[t][j$var50] = false;
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Substituted "j$var50" with its value "var27".
			if(avail[t][var27]) {
				for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1)
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample34put123$global[t][j$var107] = false;
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Substituted "j$var50" with its value "var27".
			if(avail[t][var27])
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// j$var107's comment
				// Substituted "j$var50" with its value "var27".
				guard$sample34put123$global[t][var27] = false;
		}
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(avail[t][j$var50]) {
					for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1) {
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						if(!guard$sample34put123$global[t][j$var107]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							guard$sample34put123$global[t][j$var107] = true;
							
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$0 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction716Index = 0; cv$reduction716Index <= avail[0].length; cv$reduction716Index += 1)
								// Execute the reduction function, saving the result into the return value.
								// 
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l$var100's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$0 = (reduceVar$denom$0 + weekly_ut[t][cv$reduction716Index]);
							weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$0);
						}
					}
				}
			}
		}
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((avail[t][j$var50] && !guard$sample34put123$global[t][j$var50])) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var107" with its value "j$var50".
					guard$sample34put123$global[t][j$var50] = true;
					
					// Reduction of array weekly_ut
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$denom$1 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction111Index = 0; cv$reduction111Index <= avail[0].length; cv$reduction111Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l$var100's comment
						// Set the right hand term to a value from the array weekly_ut
						reduceVar$denom$1 = (reduceVar$denom$1 + weekly_ut[t][cv$reduction111Index]);
					
					// Substituted "j$var107" with its value "j$var50".
					weekly_rates[t][j$var50] = (weekly_ut[t][j$var50] / reduceVar$denom$1);
				}
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Substituted "j$var50" with its value "var27".
			if(avail[t][var27]) {
				for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1) {
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					if(!guard$sample34put123$global[t][j$var107]) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample34put123$global[t][j$var107] = true;
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$2 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction816Index = 0; cv$reduction816Index <= avail[0].length; cv$reduction816Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
							// l$var100's comment
							// Set the right hand term to a value from the array weekly_ut
							reduceVar$denom$2 = (reduceVar$denom$2 + weekly_ut[t][cv$reduction816Index]);
						weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$2);
					}
				}
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Substituted "j$var50" with its value "var27".
			if((avail[t][var27] && !guard$sample34put123$global[t][var27])) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// j$var107's comment
				// Substituted "j$var50" with its value "var27".
				guard$sample34put123$global[t][var27] = true;
				
				// Reduction of array weekly_ut
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$denom$3 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction111Index = 0; cv$reduction111Index <= avail[0].length; cv$reduction111Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l$var100's comment
					// Set the right hand term to a value from the array weekly_ut
					reduceVar$denom$3 = (reduceVar$denom$3 + weekly_ut[t][cv$reduction111Index]);
				
				// j$var107's comment
				// Substituted "j$var50" with its value "var27".
				weekly_rates[t][var27] = (weekly_ut[t][var27] / reduceVar$denom$3);
			}
		}
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var22" with its value "1.0".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$proposedValue);
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(avail[t][j$var50])
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample34multinomial124$global[t] = false;
			}
		}
		for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(avail[t][j$var50])
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample34multinomial124$global[t] = false;
			}
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Substituted "j$var50" with its value "var27".
			if(avail[t][var27])
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample34multinomial124$global[t] = false;
		}
		for(int t = 0; t < numTimeSteps; t += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Substituted "j$var50" with its value "var27".
			if(avail[t][var27])
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample34multinomial124$global[t] = false;
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(((0 < exped.length) && (0 < avail[0].length))) {
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((((0 < weekly_ut[t].length) && avail[t][j$var50]) && !guard$sample34multinomial124$global[t])) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample34multinomial124$global[t] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 125 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 125 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "cv$temp$3$arrivals" with its value "arrivals[t]".
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t], weekly_rates[t], arrivals[t]) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((avail[t][j$var50] && !guard$sample34multinomial124$global[t])) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample34multinomial124$global[t] = true;
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 125 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 125 of consumer random variable null.
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
			// 
			// Substituted "j$var50" with its value "var27".
			if((((0 < weekly_ut[t].length) && !guard$sample34multinomial124$global[t]) && avail[t][var27])) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample34multinomial124$global[t] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 125 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 125 of consumer random variable null.
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
			// 
			// Substituted "j$var50" with its value "var27".
			if((!guard$sample34multinomial124$global[t] && avail[t][var27])) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample34multinomial124$global[t] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 125 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 125 of consumer random variable null.
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
			ut[var27] = cv$originalValue;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 34 and consumer double[] 36.
			// 
			// Substituted "j$var33" with its value "var27".
			exped[var27] = Math.exp(ut[var27]);
			
			// Guards to ensure that expedNorm is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 34 and consumer double[] 54.
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1)
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample34put61$global[j$var50] = false;
			
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// Substituted "j$var50" with its value "var27".
			guard$sample34put61$global[var27] = false;
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				if(!guard$sample34put61$global[j$var50]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample34put61$global[j$var50] = true;
					
					// Reduction of array exped
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$sum$6 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction1462Index = 0; cv$reduction1462Index < avail[0].length; cv$reduction1462Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l$var45's comment
						// Set the right hand term to a value from the array exped
						reduceVar$sum$6 = (reduceVar$sum$6 + exped[cv$reduction1462Index]);
					expedNorm[j$var50] = (exped[j$var50] / (reduceVar$sum$6 * 0.3));
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Substituted "j$var50" with its value "var27".
			if(!guard$sample34put61$global[var27]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// Substituted "j$var50" with its value "var27".
				guard$sample34put61$global[var27] = true;
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$7 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l$var45's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$7 = (reduceVar$sum$7 + exped[cv$reduction50Index]);
				
				// Substituted "j$var50" with its value "var27".
				expedNorm[var27] = (exped[var27] / (reduceVar$sum$7 * 0.3));
			}
			
			// Guards to ensure that weekly_ut is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 34 and consumer double[] 90.
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(avail[t][j$var50])
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample34put102$global[t][j$var50] = false;
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(avail[t][var27])
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var50" with its value "var27".
					guard$sample34put102$global[t][var27] = false;
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((avail[t][j$var50] && !guard$sample34put102$global[t][j$var50])) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample34put102$global[t][j$var50] = true;
						
						// Substituted "j$var86" with its value "j$var50".
						weekly_ut[t][j$var50] = expedNorm[j$var50];
					}
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((!guard$sample34put102$global[t][var27] && avail[t][var27])) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// Substituted "j$var50" with its value "var27".
					guard$sample34put102$global[t][var27] = true;
					
					// Substituted "j$var86" with its value "j$var50".
					// 
					// Substituted "j$var50" with its value "var27".
					weekly_ut[t][var27] = expedNorm[var27];
				}
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(avail[t][j$var50]) {
						for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1)
							// Set the flags to false
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							guard$sample34put123$global[t][j$var107] = false;
					}
				}
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(avail[t][j$var50])
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "j$var107" with its value "j$var50".
						guard$sample34put123$global[t][j$var50] = false;
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Substituted "j$var50" with its value "var27".
				if(avail[t][var27]) {
					for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1)
						// Set the flags to false
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						guard$sample34put123$global[t][j$var107] = false;
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Substituted "j$var50" with its value "var27".
				if(avail[t][var27])
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// j$var107's comment
					// Substituted "j$var50" with its value "var27".
					guard$sample34put123$global[t][var27] = false;
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(avail[t][j$var50]) {
						for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1) {
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							if(!guard$sample34put123$global[t][j$var107]) {
								// The body will execute, so should not be executed again
								// 
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								guard$sample34put123$global[t][j$var107] = true;
								
								// Reduction of array weekly_ut
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								double reduceVar$denom$6 = 0.0;
								
								// For each index in the array to be reduced
								for(int cv$reduction1901Index = 0; cv$reduction1901Index <= avail[0].length; cv$reduction1901Index += 1)
									// Execute the reduction function, saving the result into the return value.
									// 
									// Copy the result of the reduction into the variable returned by the reduction.
									// 
									// l$var100's comment
									// Set the right hand term to a value from the array weekly_ut
									reduceVar$denom$6 = (reduceVar$denom$6 + weekly_ut[t][cv$reduction1901Index]);
								weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$6);
							}
						}
					}
				}
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((avail[t][j$var50] && !guard$sample34put123$global[t][j$var50])) {
						// The body will execute, so should not be executed again
						// 
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						// 
						// Substituted "j$var107" with its value "j$var50".
						guard$sample34put123$global[t][j$var50] = true;
						
						// Reduction of array weekly_ut
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$denom$7 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction111Index = 0; cv$reduction111Index <= avail[0].length; cv$reduction111Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
							// l$var100's comment
							// Set the right hand term to a value from the array weekly_ut
							reduceVar$denom$7 = (reduceVar$denom$7 + weekly_ut[t][cv$reduction111Index]);
						
						// Substituted "j$var107" with its value "j$var50".
						weekly_rates[t][j$var50] = (weekly_ut[t][j$var50] / reduceVar$denom$7);
					}
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Substituted "j$var50" with its value "var27".
				if(avail[t][var27]) {
					for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1) {
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						if(!guard$sample34put123$global[t][j$var107]) {
							// The body will execute, so should not be executed again
							// 
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							guard$sample34put123$global[t][j$var107] = true;
							
							// Reduction of array weekly_ut
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							double reduceVar$denom$8 = 0.0;
							
							// For each index in the array to be reduced
							for(int cv$reduction2001Index = 0; cv$reduction2001Index <= avail[0].length; cv$reduction2001Index += 1)
								// Execute the reduction function, saving the result into the return value.
								// 
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
								// l$var100's comment
								// Set the right hand term to a value from the array weekly_ut
								reduceVar$denom$8 = (reduceVar$denom$8 + weekly_ut[t][cv$reduction2001Index]);
							weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$8);
						}
					}
				}
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Substituted "j$var50" with its value "var27".
				if((avail[t][var27] && !guard$sample34put123$global[t][var27])) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					// 
					// j$var107's comment
					// Substituted "j$var50" with its value "var27".
					guard$sample34put123$global[t][var27] = true;
					
					// Reduction of array weekly_ut
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$denom$9 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction111Index = 0; cv$reduction111Index <= avail[0].length; cv$reduction111Index += 1)
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l$var100's comment
						// Set the right hand term to a value from the array weekly_ut
						reduceVar$denom$9 = (reduceVar$denom$9 + weekly_ut[t][cv$reduction111Index]);
					
					// j$var107's comment
					// Substituted "j$var50" with its value "var27".
					weekly_rates[t][var27] = (weekly_ut[t][var27] / reduceVar$denom$9);
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 88 drawn from Gamma 77. Inference was performed using a Gamma to
	// Poisson conjugate prior.
	private final void sample88(int t) {
		// Processing random variable 79.
		// 
		// Processing sample task 90 of consumer random variable null.
		// 
		// Reduction of array null
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		int reduceVar$numSales$0 = 0;
		
		// For each index in the array to be reduced
		for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// l$var70's comment
			// Set the right hand term to a value from the array var64
			reduceVar$numSales$0 = (reduceVar$numSales$0 + ObsSales[t][cv$reduction78Index]);
		
		// Write out the new value of the sample.
		// 
		// Variable to record the number of samples from consuming random variables.
		lambda[t] = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, (arrivals[t] - reduceVar$numSales$0), 1);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 90 drawn from Poisson 79. Inference was performed using Metropolis-Hastings.
	private final void sample90(int t) {
		// Reduction of array null
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		int reduceVar$numSales$1 = 0;
		
		// For each index in the array to be reduced
		for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// l$var70's comment
			// Set the right hand term to a value from the array var64
			reduceVar$numSales$1 = (reduceVar$numSales$1 + ObsSales[t][cv$reduction78Index]);
		
		// The original value of the sample
		int cv$originalValue = (arrivals[t] - reduceVar$numSales$1);
		
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
		// Recorded the probability of reaching sample task 125 with the current configuration.
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
		int reduceVar$numSales$2 = 0;
		
		// For each index in the array to be reduced
		for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
			// l$var70's comment
			// Set the right hand term to a value from the array var64
			reduceVar$numSales$2 = (reduceVar$numSales$2 + ObsSales[t][cv$reduction78Index]);
		
		// Write out the new sample value.
		arrivals[t] = (reduceVar$numSales$2 + cv$proposedValue);
		
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
		// Recorded the probability of reaching sample task 125 with the current configuration.
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
		// Variable declaration of cv$proposedProbability moved.
		// Declaration comment was:
		// The probability of the random variable generating the new sample value.
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
			int reduceVar$numSales$3 = 0;
			
			// For each index in the array to be reduced
			for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l$var70's comment
				// Set the right hand term to a value from the array var64
				reduceVar$numSales$3 = (reduceVar$numSales$3 + ObsSales[t][cv$reduction78Index]);
			
			// Write out the new sample value.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			arrivals[t] = (reduceVar$numSales$3 + cv$originalValue);
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for guard$sample34put61$global
		// 
		// Calculate the largest index of j that is possible and allocate an array to hold
		// the guard for each of these.
		int cv$max_j$var50 = 0;
		if((0 < avail.length))
			cv$max_j$var50 = avail[0].length;
		
		// Allocation of guard$sample34put61$global for single threaded execution
		guard$sample34put61$global = new boolean[cv$max_j$var50];
		
		// Constructor for guard$sample34put102$global
		{
			// Calculate the largest index of t that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_t = 0;
			
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var86 = 0;
			if((0 < avail.length)) {
				cv$max_j$var86 = avail[0].length;
				cv$max_t = avail.length;
			}
			
			// Allocation of guard$sample34put102$global for single threaded execution
			guard$sample34put102$global = new boolean[cv$max_t][cv$max_j$var86];
		}
		
		// Constructor for guard$sample34put123$global
		{
			// Calculate the largest index of t that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_t = 0;
			
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var107 = 0;
			if((0 < avail.length)) {
				cv$max_j$var107 = (avail[0].length + 1);
				cv$max_t = avail.length;
			}
			
			// Allocation of guard$sample34put123$global for single threaded execution
			guard$sample34put123$global = new boolean[cv$max_t][cv$max_j$var107];
		}
		
		// Constructor for guard$sample34multinomial124$global
		// 
		// Calculate the largest index of t that is possible and allocate an array to hold
		// the guard for each of these.
		int cv$max_t = 0;
		if((0 < avail.length))
			cv$max_t = avail.length;
		
		// Allocation of guard$sample34multinomial124$global for single threaded execution
		guard$sample34multinomial124$global = new boolean[cv$max_t];
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
			for(int var58 = 0; var58 < avail.length; var58 += 1)
				sales[var58] = new int[avail[0].length];
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
		
		// Constructor for logProbability$sample34
		logProbability$sample34 = new double[avail[0].length];
		
		// Constructor for logProbability$var77
		logProbability$var77 = new double[avail.length];
		
		// Constructor for logProbability$sample88
		logProbability$sample88 = new double[avail.length];
		
		// Constructor for logProbability$var79
		logProbability$var79 = new double[avail.length];
		
		// Constructor for logProbability$sample90
		logProbability$sample90 = new double[avail.length];
		
		// Constructor for logProbability$var111
		logProbability$var111 = new double[avail.length];
		
		// Constructor for logProbability$sample125
		logProbability$sample125 = new double[avail.length];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if((0 < numTimeSteps)) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample34) {
				for(int var27 = 0; var27 < avail[0].length; var27 += 1)
					ut[var27] = DistributionSampling.sampleGaussian(RNG$);
				for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1)
					exped[j$var33] = Math.exp(ut[j$var33]);
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$8 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1)
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l$var45's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$8 = (reduceVar$sum$8 + exped[cv$reduction50Index]);
				for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1)
					expedNorm[j$var50] = (exped[j$var50] / (reduceVar$sum$8 * 0.3));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(!fixedFlag$sample88)
					lambda[t] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample90) {
					// Reduction of array null
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					int reduceVar$numSales$5 = 0;
					
					// For each index in the array to be reduced
					for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1)
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l$var70's comment
						// Set the right hand term to a value from the array var64
						reduceVar$numSales$5 = (reduceVar$numSales$5 + ObsSales[t][cv$reduction78Index]);
					arrivals[t] = (reduceVar$numSales$5 + DistributionSampling.samplePoisson(RNG$, lambda[t]));
				}
				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
					if(avail[t][j$var86]) {
						if(!fixedFlag$sample34)
							weekly_ut[t][j$var86] = expedNorm[j$var86];
					} else
						weekly_ut[t][j$var86] = 0.0;
				}
				weekly_ut[t][avail[0].length] = 1.0;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample34) {
					// Reduction of array weekly_ut
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$denom$10 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction111Index = 0; cv$reduction111Index <= avail[0].length; cv$reduction111Index += 1)
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l$var100's comment
						// Set the right hand term to a value from the array weekly_ut
						reduceVar$denom$10 = (reduceVar$denom$10 + weekly_ut[t][cv$reduction111Index]);
					for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1)
						weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$10);
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample125) {
					DistributionSampling.sampleMultinomial(RNG$, weekly_rates[t], arrivals[t], weekly_sales[t]);
					int[] observed_weekly_sales = sales[t];
					for(int j$var116 = 0; j$var116 < avail[0].length; j$var116 += 1)
						observed_weekly_sales[j$var116] = weekly_sales[t][j$var116];
				}
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if((0 < numTimeSteps)) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample34) {
				for(int var27 = 0; var27 < avail[0].length; var27 += 1)
					ut[var27] = DistributionSampling.sampleGaussian(RNG$);
				for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1)
					exped[j$var33] = Math.exp(ut[j$var33]);
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$10 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1)
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l$var45's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$10 = (reduceVar$sum$10 + exped[cv$reduction50Index]);
				for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1)
					expedNorm[j$var50] = (exped[j$var50] / (reduceVar$sum$10 * 0.3));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(!fixedFlag$sample88)
					lambda[t] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample90) {
					// Reduction of array null
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					int reduceVar$numSales$7 = 0;
					
					// For each index in the array to be reduced
					for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1)
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l$var70's comment
						// Set the right hand term to a value from the array var64
						reduceVar$numSales$7 = (reduceVar$numSales$7 + ObsSales[t][cv$reduction78Index]);
					arrivals[t] = (reduceVar$numSales$7 + DistributionSampling.samplePoisson(RNG$, lambda[t]));
				}
				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
					if(avail[t][j$var86]) {
						if(!fixedFlag$sample34)
							weekly_ut[t][j$var86] = expedNorm[j$var86];
					} else
						weekly_ut[t][j$var86] = 0.0;
				}
				weekly_ut[t][avail[0].length] = 1.0;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample34) {
					// Reduction of array weekly_ut
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$denom$12 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction111Index = 0; cv$reduction111Index <= avail[0].length; cv$reduction111Index += 1)
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l$var100's comment
						// Set the right hand term to a value from the array weekly_ut
						reduceVar$denom$12 = (reduceVar$denom$12 + weekly_ut[t][cv$reduction111Index]);
					for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1)
						weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$12);
				}
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if((0 < numTimeSteps)) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample34) {
				for(int var27 = 0; var27 < avail[0].length; var27 += 1)
					ut[var27] = DistributionSampling.sampleGaussian(RNG$);
				for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1)
					exped[j$var33] = Math.exp(ut[j$var33]);
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$9 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1)
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l$var45's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$9 = (reduceVar$sum$9 + exped[cv$reduction50Index]);
				for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1)
					expedNorm[j$var50] = (exped[j$var50] / (reduceVar$sum$9 * 0.3));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(!fixedFlag$sample88)
					lambda[t] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample90) {
					// Reduction of array null
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					int reduceVar$numSales$6 = 0;
					
					// For each index in the array to be reduced
					for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1)
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l$var70's comment
						// Set the right hand term to a value from the array var64
						reduceVar$numSales$6 = (reduceVar$numSales$6 + ObsSales[t][cv$reduction78Index]);
					arrivals[t] = (reduceVar$numSales$6 + DistributionSampling.samplePoisson(RNG$, lambda[t]));
				}
				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
					if(avail[t][j$var86]) {
						if(!fixedFlag$sample34)
							weekly_ut[t][j$var86] = expedNorm[j$var86];
					} else
						weekly_ut[t][j$var86] = 0.0;
				}
				weekly_ut[t][avail[0].length] = 1.0;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample34) {
					// Reduction of array weekly_ut
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$denom$11 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction111Index = 0; cv$reduction111Index <= avail[0].length; cv$reduction111Index += 1)
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l$var100's comment
						// Set the right hand term to a value from the array weekly_ut
						reduceVar$denom$11 = (reduceVar$denom$11 + weekly_ut[t][cv$reduction111Index]);
					for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1)
						weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$11);
				}
			}
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
				if(!fixedFlag$sample34) {
					for(int var27 = 0; var27 < avail[0].length; var27 += 1)
						sample34(var27);
				}
				for(int t = 0; t < numTimeSteps; t += 1) {
					if(!fixedFlag$sample88)
						sample88(t);
					if(!fixedFlag$sample90)
						sample90(t);
				}
			}
			// Infer the samples in reverse chronological order.
			else {
				for(int t = (numTimeSteps - 1); t >= 0; t -= 1) {
					if(!fixedFlag$sample90)
						sample90(t);
					if(!fixedFlag$sample88)
						sample88(t);
				}
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample34) {
					for(int var27 = (avail[0].length - 1); var27 >= 0; var27 -= 1)
						sample34(var27);
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
		logProbability$var23 = 0.0;
		logProbability$exped = 0.0;
		logProbability$expedNorm = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$ut = 0.0;
		logProbability$weekly_rates = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < numTimeSteps)) {
			if(!fixedProbFlag$sample34) {
				for(int var27 = 0; var27 < avail[0].length; var27 += 1)
					logProbability$sample34[var27] = 0.0;
			}
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var77[t] = 0.0;
		}
		logProbability$lambda = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < numTimeSteps)) {
			if(!fixedProbFlag$sample88) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample88[t] = 0.0;
			}
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var79[t] = 0.0;
		}
		logProbability$arrivals = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < numTimeSteps)) {
			if(!fixedProbFlag$sample90) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample90[t] = 0.0;
			}
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var111[t] = 0.0;
		}
		logProbability$sales = 0.0;
		logProbability$weekly_sales = 0.0;
		if((!fixedProbFlag$sample125 && (0 < numTimeSteps))) {
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$sample125[t] = 0.0;
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
		if(fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(fixedFlag$sample88)
			logProbabilityValue$sample88();
		if(fixedFlag$sample90)
			logProbabilityValue$sample90();
		logProbabilityValue$sample125();
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
		logProbabilityValue$sample34();
		logProbabilityValue$sample88();
		logProbabilityValue$sample90();
		logProbabilityValue$sample125();
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
		logProbabilityValue$sample34();
		logProbabilityValue$sample88();
		logProbabilityValue$sample90();
		logProbabilityValue$sample125();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if((0 < numTimeSteps)) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample34) {
				for(int var27 = 0; var27 < avail[0].length; var27 += 1)
					ut[var27] = DistributionSampling.sampleGaussian(RNG$);
				for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1)
					exped[j$var33] = Math.exp(ut[j$var33]);
				
				// Reduction of array exped
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$sum$11 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1)
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l$var45's comment
					// Set the right hand term to a value from the array exped
					reduceVar$sum$11 = (reduceVar$sum$11 + exped[cv$reduction50Index]);
				for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1)
					expedNorm[j$var50] = (exped[j$var50] / (reduceVar$sum$11 * 0.3));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(!fixedFlag$sample88)
					lambda[t] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample90) {
					// Reduction of array null
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					int reduceVar$numSales$8 = 0;
					
					// For each index in the array to be reduced
					for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1)
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l$var70's comment
						// Set the right hand term to a value from the array var64
						reduceVar$numSales$8 = (reduceVar$numSales$8 + ObsSales[t][cv$reduction78Index]);
					arrivals[t] = (reduceVar$numSales$8 + DistributionSampling.samplePoisson(RNG$, lambda[t]));
				}
				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
					if(avail[t][j$var86]) {
						if(!fixedFlag$sample34)
							weekly_ut[t][j$var86] = expedNorm[j$var86];
					} else
						weekly_ut[t][j$var86] = 0.0;
				}
				weekly_ut[t][avail[0].length] = 1.0;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!fixedFlag$sample34) {
					// Reduction of array weekly_ut
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					double reduceVar$denom$13 = 0.0;
					
					// For each index in the array to be reduced
					for(int cv$reduction111Index = 0; cv$reduction111Index <= avail[0].length; cv$reduction111Index += 1)
						// Copy the result of the reduction into the variable returned by the reduction.
						// 
						// l$var100's comment
						// Set the right hand term to a value from the array weekly_ut
						reduceVar$denom$13 = (reduceVar$denom$13 + weekly_ut[t][cv$reduction111Index]);
					for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1)
						weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$13);
				}
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
				// Variable declaration of observed_weekly_sales moved.
				int[] observed_weekly_sales = sales[t];
				for(int j$var116 = (avail[0].length - 1); j$var116 >= 0; j$var116 -= 1)
					weekly_sales[t][j$var116] = observed_weekly_sales[j$var116];
			}
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		if((0 < numTimeSteps)) {
			for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1)
				exped[j$var33] = Math.exp(ut[j$var33]);
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$12 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
				// l$var45's comment
				// Set the right hand term to a value from the array exped
				reduceVar$sum$12 = (reduceVar$sum$12 + exped[cv$reduction50Index]);
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1)
				expedNorm[j$var50] = (exped[j$var50] / (reduceVar$sum$12 * 0.3));
			for(int t = 0; t < numTimeSteps; t += 1) {
				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
					if(avail[t][j$var86])
						weekly_ut[t][j$var86] = expedNorm[j$var86];
				}
				
				// Reduction of array weekly_ut
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$denom$14 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction111Index = 0; cv$reduction111Index <= avail[0].length; cv$reduction111Index += 1)
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					// 
					// l$var100's comment
					// Set the right hand term to a value from the array weekly_ut
					reduceVar$denom$14 = (reduceVar$denom$14 + weekly_ut[t][cv$reduction111Index]);
				for(int j$var107 = 0; j$var107 <= avail[0].length; j$var107 += 1)
					weekly_rates[t][j$var107] = (weekly_ut[t][j$var107] / reduceVar$denom$14);
				if(setFlag$weekly_sales) {
					int[] observed_weekly_sales = sales[t];
					for(int j$var116 = 0; j$var116 < avail[0].length; j$var116 += 1)
						observed_weekly_sales[j$var116] = weekly_sales[t][j$var116];
				}
			}
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n/*\n * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n */\npackage org.sandwood.compiler.tests.parser;\n\nmodel Vulcano2012basicDG(int[][] ObsSales, boolean[][] avail) {\n   // avail is the availability matrix, numTimeSteps-by-numProducts\n   // r is the normalization constant here, number between 0 and 1. \"Relative appeal of the outside option\"\n   double r = 0.3;\n    \n   int numTimeSteps = avail.length;\n   if(numTimeSteps > 0) {\n      int numProducts = avail[0].length;\n\n      // draw utilities\n      double[] ut = gaussian(0, 1).sample(numProducts);\n\n      //exponentiate right here (in the non-basic models move to the for loop)\n      double[] exped = new double[numProducts];\n      for(int j : [0..numProducts))\n         exped[j] = exp(ut[j]);\n\n      //Choices includes the choice to not buy anything.\n      int numChoices = numProducts + 1;\n\n      //now normalize\n      double[] expedNorm = new double[numProducts];\n      double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n      for(int j : [0..numProducts))\n         expedNorm[j] = exped[j]/(r*sum);\n\n      int[][] sales = new int[numTimeSteps][numProducts];\n\n      for (int t:[0..numTimeSteps)){\n         // Calculate the number of purchases made.\n         int numSales = reduce(ObsSales[t], 0, (k, l) -> { return k + l; });\n\n         // prior for the distribution of lambda for arrivals. These can be \n         // supplied as a vector if RGBU has some estimates, or just use some priors.\n         public double lambda = gamma(10,10).sample();\n         public int arrivals = numSales + poisson(lambda).sample();\n\n         // for each period t calculate choice probabilities and sales\n         double[] weekly_rates = new double[numChoices];\n         double[] weekly_ut = new double[numChoices];\n\n         for(int j : [0..numProducts)) {\n            if(avail[t][j])\n               weekly_ut[j] = expedNorm[j];\n            else\n               weekly_ut[j] = 0.0;\n         }\n         // Moved v_0 to the end of the array to keep indexing consistent everywhere else in \n         // the model and delayed the assignment of the value 1 to here. None of this is a \n         // sandwood requirement, I just thought it made the model eaiser to follow.\n         weekly_ut[numProducts] = 1.0;\n\n         double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n\n         for(int j : [0..numProducts]) \n            weekly_rates[j] = weekly_ut[j]/denom ;\n\n         int[] weekly_sales = multinomial(weekly_rates, arrivals).sample();\n\n         //getting rid of the no purchase observation (last one in the vector of weekly_sales)\n         int[] observed_weekly_sales = sales[t];\n         for (int j : [0..numProducts))\n            observed_weekly_sales[j] = weekly_sales[j] ;\n      }\n      // assert that generated sales match observed sales\n      sales.observe(ObsSales);\n   }\n}";
	}
}