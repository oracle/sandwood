package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DiscreteChoiceRandCoeff$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DiscreteChoiceRandCoeff$CoreInterface {
	
	// Declare the variables for the model.
	private int[] ObsChoices;
	private int[][] Prices;
	private double b;
	private double[] beta;
	private int[] choices;
	private double[][] exped;
	private boolean fixedFlag$sample22 = false;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample41 = false;
	private boolean fixedFlag$sample76 = false;
	private boolean fixedProbFlag$sample22 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample41 = false;
	private boolean fixedProbFlag$sample76 = false;
	private boolean[] guard$sample22categorical75$global;
	private boolean[][] guard$sample22put74$global;
	private boolean[][] guard$sample41categorical75$global;
	private boolean[][][] guard$sample41put74$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b;
	private double logProbability$beta;
	private double logProbability$choices;
	private double logProbability$exped;
	private double logProbability$prob;
	private double[] logProbability$sample22;
	private double[] logProbability$sample41;
	private double[] logProbability$sample76;
	private double logProbability$sigma;
	private double logProbability$ut;
	private double logProbability$var15;
	private double logProbability$var26;
	private double logProbability$var32;
	private double logProbability$var34;
	private double[] logProbability$var72;
	private int noObs;
	private int noProducts;
	private double[][] prob;
	private boolean setFlag$beta = false;
	private boolean setFlag$choices = false;
	private boolean setFlag$ut = false;
	private double sigma;
	private boolean system$gibbsForward = true;
	private double[] ut;

	public DiscreteChoiceRandCoeff$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for ObsChoices.
	@Override
	public final int[] get$ObsChoices() {
		return ObsChoices;
	}

	// Setter for ObsChoices.
	@Override
	public final void set$ObsChoices(int[] cv$value) {
		// Set ObsChoices with flag to mark that it has been set so another array doesn't
		// need to be constructed
		ObsChoices = cv$value;
	}

	// Getter for Prices.
	@Override
	public final int[][] get$Prices() {
		return Prices;
	}

	// Setter for Prices.
	@Override
	public final void set$Prices(int[][] cv$value) {
		// Set Prices with flag to mark that it has been set so another array doesn't need
		// to be constructed
		Prices = cv$value;
	}

	// Getter for b.
	@Override
	public final double get$b() {
		return b;
	}

	// Setter for b.
	@Override
	public final void set$b(double cv$value) {
		b = cv$value;
	}

	// Getter for beta.
	@Override
	public final double[] get$beta() {
		return beta;
	}

	// Setter for beta.
	@Override
	public final void set$beta(double[] cv$value) {
		// Set beta with flag to mark that it has been set so another array doesn't need to
		// be constructed
		beta = cv$value;
		setFlag$beta = true;
	}

	// Getter for choices.
	@Override
	public final int[] get$choices() {
		return choices;
	}

	// Setter for choices.
	@Override
	public final void set$choices(int[] cv$value) {
		// Set choices with flag to mark that it has been set so another array doesn't need
		// to be constructed
		choices = cv$value;
		setFlag$choices = true;
	}

	// Getter for fixedFlag$sample22.
	@Override
	public final boolean get$fixedFlag$sample22() {
		return fixedFlag$sample22;
	}

	// Setter for fixedFlag$sample22.
	@Override
	public final void set$fixedFlag$sample22(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample22 including if probabilities
		// need to be updated.
		fixedFlag$sample22 = cv$value;
		
		// Should the probability of sample 22 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample22 = (fixedFlag$sample22 && fixedProbFlag$sample22);
		
		// Should the probability of sample 76 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample76 = (fixedFlag$sample22 && fixedProbFlag$sample76);
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
		fixedProbFlag$sample29 = (fixedFlag$sample29 && fixedProbFlag$sample29);
		
		// Should the probability of sample 41 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample41 = (fixedFlag$sample29 && fixedProbFlag$sample41);
	}

	// Getter for fixedFlag$sample35.
	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	// Setter for fixedFlag$sample35.
	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample35 including if probabilities
		// need to be updated.
		fixedFlag$sample35 = cv$value;
		
		// Should the probability of sample 35 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedProbFlag$sample35);
		
		// Should the probability of sample 41 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample41 = (fixedFlag$sample35 && fixedProbFlag$sample41);
	}

	// Getter for fixedFlag$sample41.
	@Override
	public final boolean get$fixedFlag$sample41() {
		return fixedFlag$sample41;
	}

	// Setter for fixedFlag$sample41.
	@Override
	public final void set$fixedFlag$sample41(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample41 including if probabilities
		// need to be updated.
		fixedFlag$sample41 = cv$value;
		
		// Should the probability of sample 41 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample41 = (fixedFlag$sample41 && fixedProbFlag$sample41);
		
		// Should the probability of sample 76 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample76 = (fixedFlag$sample41 && fixedProbFlag$sample76);
	}

	// Getter for fixedFlag$sample76.
	@Override
	public final boolean get$fixedFlag$sample76() {
		return fixedFlag$sample76;
	}

	// Setter for fixedFlag$sample76.
	@Override
	public final void set$fixedFlag$sample76(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample76 including if probabilities
		// need to be updated.
		fixedFlag$sample76 = cv$value;
		
		// Should the probability of sample 76 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample76 = (fixedFlag$sample76 && fixedProbFlag$sample76);
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

	// Getter for logProbability$b.
	@Override
	public final double get$logProbability$b() {
		return logProbability$b;
	}

	// Getter for logProbability$beta.
	@Override
	public final double get$logProbability$beta() {
		return logProbability$beta;
	}

	// Getter for logProbability$choices.
	@Override
	public final double get$logProbability$choices() {
		return logProbability$choices;
	}

	// Getter for logProbability$prob.
	@Override
	public final double get$logProbability$prob() {
		return logProbability$prob;
	}

	// Getter for logProbability$sigma.
	@Override
	public final double get$logProbability$sigma() {
		return logProbability$sigma;
	}

	// Getter for logProbability$ut.
	@Override
	public final double get$logProbability$ut() {
		return logProbability$ut;
	}

	// Getter for noObs.
	@Override
	public final int get$noObs() {
		return noObs;
	}

	// Setter for noObs.
	@Override
	public final void set$noObs(int cv$value) {
		noObs = cv$value;
	}

	// Getter for noProducts.
	@Override
	public final int get$noProducts() {
		return noProducts;
	}

	// Setter for noProducts.
	@Override
	public final void set$noProducts(int cv$value) {
		noProducts = cv$value;
	}

	// Getter for prob.
	@Override
	public final double[][] get$prob() {
		return prob;
	}

	// Getter for sigma.
	@Override
	public final double get$sigma() {
		return sigma;
	}

	// Setter for sigma.
	@Override
	public final void set$sigma(double cv$value) {
		sigma = cv$value;
	}

	// Getter for ut.
	@Override
	public final double[] get$ut() {
		return ut;
	}

	// Setter for ut.
	@Override
	public final void set$ut(double[] cv$value) {
		// Set ut with flag to mark that it has been set so another array doesn't need to
		// be constructed
		ut = cv$value;
		setFlag$ut = true;
	}

	// Calculate the probability of the samples represented by sample22 using sampled
	// values.
	private final void logProbabilityValue$sample22() {
		// Determine if we need to calculate the values for sample task 22 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample22) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var19 = 0; var19 < noProducts; var19 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = ut[var19];
					{
						{
							double var13 = 0.0;
							double var14 = 10.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var13) / Math.sqrt(var14))) - (0.5 * Math.log(var14))));
							
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
				
				// Store the sample task probability
				logProbability$sample22[((var19 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that exped is only updated once for this probability.
				boolean cv$guard$exped = false;
				
				// Guard to ensure that prob is only updated once for this probability.
				boolean cv$guard$prob = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 22 and consumer double[] 56.
				{
					for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
						if((var19 == j$var48)) {
							for(int i = 0; i < noObs; i += 1) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$exped) {
									// Set the guard so the update is only applied once.
									cv$guard$exped = true;
									
									// Update the variable probability
									logProbability$exped = (logProbability$exped + cv$sampleProbability);
								}
							}
						}
					}
				}
				
				// Looking for a path between Sample 22 and consumer double[] 71.
				{
					for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
						if((var19 == j$var48)) {
							for(int i = 0; i < noObs; i += 1) {
								if(((0 <= j$var48) && (j$var48 < noProducts))) {
									{
										for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
											// If the probability of the variable has not already been updated
											if(!cv$guard$prob) {
												// Set the guard so the update is only applied once.
												cv$guard$prob = true;
												
												// Update the variable probability
												logProbability$prob = (logProbability$prob + cv$sampleProbability);
											}
										}
									}
								}
							}
						}
					}
					for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
						if((var19 == j$var48)) {
							for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
								if((j$var48 == j$var68)) {
									for(int i = 0; i < noObs; i += 1) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$prob) {
											// Set the guard so the update is only applied once.
											cv$guard$prob = true;
											
											// Update the variable probability
											logProbability$prob = (logProbability$prob + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var15 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample22 = fixedFlag$sample22;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int var19 = 0; var19 < noProducts; var19 += 1) {
				double cv$sampleValue = logProbability$sample22[((var19 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Guard to ensure that exped is only updated once for this probability.
				boolean cv$guard$exped = false;
				
				// Guard to ensure that prob is only updated once for this probability.
				boolean cv$guard$prob = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 22 and consumer double[] 56.
				{
					for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
						if((var19 == j$var48)) {
							for(int i = 0; i < noObs; i += 1) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$exped) {
									// Set the guard so the update is only applied once.
									cv$guard$exped = true;
									
									// Update the variable probability
									logProbability$exped = (logProbability$exped + cv$sampleValue);
								}
							}
						}
					}
				}
				
				// Looking for a path between Sample 22 and consumer double[] 71.
				{
					for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
						if((var19 == j$var48)) {
							for(int i = 0; i < noObs; i += 1) {
								if(((0 <= j$var48) && (j$var48 < noProducts))) {
									{
										for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
											// If the probability of the variable has not already been updated
											if(!cv$guard$prob) {
												// Set the guard so the update is only applied once.
												cv$guard$prob = true;
												
												// Update the variable probability
												logProbability$prob = (logProbability$prob + cv$sampleValue);
											}
										}
									}
								}
							}
						}
					}
					for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
						if((var19 == j$var48)) {
							for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
								if((j$var48 == j$var68)) {
									for(int i = 0; i < noObs; i += 1) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$prob) {
											// Set the guard so the update is only applied once.
											cv$guard$prob = true;
											
											// Update the variable probability
											logProbability$prob = (logProbability$prob + cv$sampleValue);
										}
									}
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var15 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample29 using sampled
	// values.
	private final void logProbabilityValue$sample29() {
		// Determine if we need to calculate the values for sample task 29 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample29) {
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
				double cv$sampleValue = b;
				{
					{
						double var24 = 0.0;
						double var25 = 10.0;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var24) / Math.sqrt(var25))) - (0.5 * Math.log(var25))));
						
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
			logProbability$var26 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$b = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample29 = fixedFlag$sample29;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$b;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var26 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample35 using sampled
	// values.
	private final void logProbabilityValue$sample35() {
		// Determine if we need to calculate the values for sample task 35 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample35) {
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
				double cv$sampleValue = sigma;
				{
					{
						double var30 = 2.0;
						double var31 = 2.0;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var30, var31));
						
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
			logProbability$var32 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$sigma = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample35 = fixedFlag$sample35;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$sigma;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var32 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample41 using sampled
	// values.
	private final void logProbabilityValue$sample41() {
		// Determine if we need to calculate the values for sample task 41 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample41) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var38 = 0; var38 < noObs; var38 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = beta[var38];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - b) / Math.sqrt(sigma))) - (0.5 * Math.log(sigma))));
							
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
				
				// Store the sample task probability
				logProbability$sample41[((var38 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that exped is only updated once for this probability.
				boolean cv$guard$exped = false;
				
				// Guard to ensure that prob is only updated once for this probability.
				boolean cv$guard$prob = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 41 and consumer double[] 56.
				{
					for(int i = 0; i < noObs; i += 1) {
						if((var38 == i)) {
							for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$exped) {
									// Set the guard so the update is only applied once.
									cv$guard$exped = true;
									
									// Update the variable probability
									logProbability$exped = (logProbability$exped + cv$sampleProbability);
								}
							}
						}
					}
				}
				
				// Looking for a path between Sample 41 and consumer double[] 71.
				{
					for(int i = 0; i < noObs; i += 1) {
						if((var38 == i)) {
							for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
								if(((0 <= j$var48) && (j$var48 < noProducts))) {
									{
										for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
											// If the probability of the variable has not already been updated
											if(!cv$guard$prob) {
												// Set the guard so the update is only applied once.
												cv$guard$prob = true;
												
												// Update the variable probability
												logProbability$prob = (logProbability$prob + cv$sampleProbability);
											}
										}
									}
								}
							}
						}
					}
					for(int i = 0; i < noObs; i += 1) {
						if((var38 == i)) {
							for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var48 == j$var68)) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$prob) {
											// Set the guard so the update is only applied once.
											cv$guard$prob = true;
											
											// Update the variable probability
											logProbability$prob = (logProbability$prob + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var34 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$beta = (logProbability$beta + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample41)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample41 = ((fixedFlag$sample41 && fixedFlag$sample29) && fixedFlag$sample35);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int var38 = 0; var38 < noObs; var38 += 1) {
				double cv$sampleValue = logProbability$sample41[((var38 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Guard to ensure that exped is only updated once for this probability.
				boolean cv$guard$exped = false;
				
				// Guard to ensure that prob is only updated once for this probability.
				boolean cv$guard$prob = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 41 and consumer double[] 56.
				{
					for(int i = 0; i < noObs; i += 1) {
						if((var38 == i)) {
							for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$exped) {
									// Set the guard so the update is only applied once.
									cv$guard$exped = true;
									
									// Update the variable probability
									logProbability$exped = (logProbability$exped + cv$sampleValue);
								}
							}
						}
					}
				}
				
				// Looking for a path between Sample 41 and consumer double[] 71.
				{
					for(int i = 0; i < noObs; i += 1) {
						if((var38 == i)) {
							for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
								if(((0 <= j$var48) && (j$var48 < noProducts))) {
									{
										for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
											// If the probability of the variable has not already been updated
											if(!cv$guard$prob) {
												// Set the guard so the update is only applied once.
												cv$guard$prob = true;
												
												// Update the variable probability
												logProbability$prob = (logProbability$prob + cv$sampleValue);
											}
										}
									}
								}
							}
						}
					}
					for(int i = 0; i < noObs; i += 1) {
						if((var38 == i)) {
							for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var48 == j$var68)) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$prob) {
											// Set the guard so the update is only applied once.
											cv$guard$prob = true;
											
											// Update the variable probability
											logProbability$prob = (logProbability$prob + cv$sampleValue);
										}
									}
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var34 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$beta = (logProbability$beta + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample41)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample76 using sampled
	// values.
	private final void logProbabilityValue$sample76() {
		// Determine if we need to calculate the values for sample task 76 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample76) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i = 0; i < noObs; i += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = choices[i];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < prob[((i - 0) / 1)].length))?Math.log(prob[((i - 0) / 1)][cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
				logProbability$var72[((i - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample76[((i - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$choices = (logProbability$choices + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample76 = ((fixedFlag$sample76 && fixedFlag$sample22) && fixedFlag$sample41);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < noObs; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample76[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var72[((i - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$choices = (logProbability$choices + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 22 drawn from Gaussian 15. Inference was performed using Metropolis-Hastings.
	private final void sample22(int var19) {
		// The original value of the sample
		double cv$originalValue = ut[var19];
		
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability = 0.0;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		
		// Ensure the variance is at least 0.01
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		
		// The probability of the random variable generating the new sample value.
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// The value currently being tested
			double cv$currentValue;
			if((cv$valuePos == 0))
				// Set the current value to the current state of the tree.
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				
				// Update Sample and intermediate values
				{
					// Write out the value of the sample to a temporary variable prior to updating the
					// intermediate variables.
					double var20 = cv$proposedValue;
					ut[var19] = cv$currentValue;
					
					// Guards to ensure that exped is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 22 and consumer double[] 56.
					{
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							if((var19 == j$var48)) {
								for(int i = 0; i < noObs; i += 1)
									exped[((i - 0) / 1)][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
							}
						}
					}
					
					// Guards to ensure that prob is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 22 and consumer double[] 71.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[][] guard$sample22put74 = guard$sample22put74$global;
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							if((var19 == j$var48)) {
								for(int i = 0; i < noObs; i += 1) {
									if(((0 <= j$var48) && (j$var48 < noProducts))) {
										{
											for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1)
												// Set the flags to false
												guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = false;
										}
									}
								}
							}
						}
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							if((var19 == j$var48)) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var48 == j$var68)) {
										for(int i = 0; i < noObs; i += 1)
											// Set the flags to false
											guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							if((var19 == j$var48)) {
								for(int i = 0; i < noObs; i += 1) {
									if(((0 <= j$var48) && (j$var48 < noProducts))) {
										{
											for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
												if(!guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)]) {
													// The body will execute, so should not be executed again
													guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = true;
													{
														// Reduction of array exped
														// 
														// A generated name to prevent name collisions if the reduction is implemented more
														// than once in inference and probability code. Initialize the variable to the unit
														// value
														double reduceVar$sum$15 = 0.0;
														
														// For each index in the array to be reduced
														for(int cv$reduction961Index = 0; cv$reduction961Index < noProducts; cv$reduction961Index += 1) {
															// Set the left hand term of the reduction function to the return variable value.
															double k = reduceVar$sum$15;
															
															// Set the right hand term to a value from the array exped
															double l = exped[((i - 0) / 1)][cv$reduction961Index];
															
															// Execute the reduction function, saving the result into the return value.
															// 
															// Copy the result of the reduction into the variable returned by the reduction.
															reduceVar$sum$15 = (k + l);
														}
														prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$15);
													}
												}
											}
										}
									}
								}
							}
						}
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							if((var19 == j$var48)) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var48 == j$var68)) {
										for(int i = 0; i < noObs; i += 1) {
											if(!guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = true;
												{
													// Reduction of array exped
													// 
													// A generated name to prevent name collisions if the reduction is implemented more
													// than once in inference and probability code. Initialize the variable to the unit
													// value
													double reduceVar$sum$16 = 0.0;
													
													// For each index in the array to be reduced
													for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1) {
														// Set the left hand term of the reduction function to the return variable value.
														double k = reduceVar$sum$16;
														
														// Set the right hand term to a value from the array exped
														double l = exped[((i - 0) / 1)][cv$reduction63Index];
														
														// Execute the reduction function, saving the result into the return value.
														// 
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$sum$16 = (k + l);
													}
													prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$16);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var13;
				{
					cv$temp$0$var13 = 0.0;
				}
				double cv$temp$1$var14;
				{
					cv$temp$1$var14 = 10.0;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var13) / Math.sqrt(cv$temp$1$var14))) - (0.5 * Math.log(cv$temp$1$var14))));
				
				// Processing random variable 72.
				{
					// Looking for a path between Sample 22 and consumer Categorical 72.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[] guard$sample22categorical75 = guard$sample22categorical75$global;
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							if((var19 == j$var48)) {
								for(int i = 0; i < noObs; i += 1) {
									if(((0 <= j$var48) && (j$var48 < noProducts))) {
										{
											// Set the flags to false
											guard$sample22categorical75[((i - 0) / 1)] = false;
										}
									}
								}
							}
						}
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							if((var19 == j$var48)) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var48 == j$var68)) {
										for(int i = 0; i < noObs; i += 1)
											// Set the flags to false
											guard$sample22categorical75[((i - 0) / 1)] = false;
									}
								}
							}
						}
						double traceTempVariable$var49$8_1 = cv$currentValue;
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							if((var19 == j$var48)) {
								for(int i = 0; i < noObs; i += 1) {
									double traceTempVariable$k$8_4 = Math.exp((traceTempVariable$var49$8_1 - (beta[i] * Prices[i][j$var48])));
									if(((0 <= j$var48) && (j$var48 < noProducts))) {
										{
											if((0 < noProducts)) {
												// Reduction of array exped
												// 
												// A generated name to prevent name collisions if the reduction is implemented more
												// than once in inference and probability code. Initialize the variable to the unit
												// value
												double reduceVar$sum$17 = 0.0;
												
												// Reduce for every value except a masked value which will be skipped.
												for(int cv$reduction1039Index = 0; cv$reduction1039Index < j$var48; cv$reduction1039Index += 1) {
													// Set the left hand term of the reduction function to the return variable value.
													double k = reduceVar$sum$17;
													
													// Set the right hand term to a value from the array exped
													double l = exped[((i - 0) / 1)][cv$reduction1039Index];
													
													// Execute the reduction function, saving the result into the return value.
													// 
													// Copy the result of the reduction into the variable returned by the reduction.
													reduceVar$sum$17 = (k + l);
												}
												for(int cv$reduction1039Index = (j$var48 + 1); cv$reduction1039Index < noProducts; cv$reduction1039Index += 1) {
													// Set the left hand term of the reduction function to the return variable value.
													double k = reduceVar$sum$17;
													
													// Set the right hand term to a value from the array exped
													double l = exped[((i - 0) / 1)][cv$reduction1039Index];
													
													// Execute the reduction function, saving the result into the return value.
													// 
													// Execute the reduction function, saving the result into the return value.
													// 
													// Copy the result of the reduction into the variable returned by the reduction.
													reduceVar$sum$17 = (k + l);
												}
												double cv$reduced63 = reduceVar$sum$17;
												
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$17 = (traceTempVariable$k$8_4 + cv$reduced63);
												double traceTempVariable$sum$8_5 = reduceVar$sum$17;
												if(!guard$sample22categorical75[((i - 0) / 1)]) {
													// The body will execute, so should not be executed again
													guard$sample22categorical75[((i - 0) / 1)] = true;
													
													// Processing sample task 76 of consumer random variable null.
													{
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
																		double[] cv$temp$2$prob;
																		{
																			cv$temp$2$prob = prob[((i - 0) / 1)];
																		}
																		
																		// Record the probability of sample task 76 generating output with current configuration.
																		if(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)));
																		}
																		
																		// Recorded the probability of reaching sample task 76 with the current configuration.
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
									}
								}
							}
						}
						double traceTempVariable$var49$9_1 = cv$currentValue;
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							if((var19 == j$var48)) {
								for(int i = 0; i < noObs; i += 1) {
									double traceTempVariable$var69$9_4 = Math.exp((traceTempVariable$var49$9_1 - (beta[i] * Prices[i][j$var48])));
									for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
										if((j$var48 == j$var68)) {
											if(!guard$sample22categorical75[((i - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample22categorical75[((i - 0) / 1)] = true;
												
												// Processing sample task 76 of consumer random variable null.
												{
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
																	double[] cv$temp$3$prob;
																	{
																		cv$temp$3$prob = prob[((i - 0) / 1)];
																	}
																	
																	// Record the probability of sample task 76 generating output with current configuration.
																	if(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[i]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[i]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[i]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[i]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[i]]):Double.NEGATIVE_INFINITY)));
																	}
																	
																	// Recorded the probability of reaching sample task 76 with the current configuration.
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
		
		// The probability ration for the proposed value and the current value.
		double cv$ratio = (cv$proposedProbability - cv$originalProbability);
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
			// If it is not revert the changes.
			// 
			// Set the sample value
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			double var20 = cv$originalValue;
			ut[var19] = var20;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 22 and consumer double[] 56.
			{
				for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
					if((var19 == j$var48)) {
						for(int i = 0; i < noObs; i += 1)
							exped[((i - 0) / 1)][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
					}
				}
			}
			
			// Guards to ensure that prob is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 22 and consumer double[] 71.
			{
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				boolean[][] guard$sample22put74 = guard$sample22put74$global;
				for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
					if((var19 == j$var48)) {
						for(int i = 0; i < noObs; i += 1) {
							if(((0 <= j$var48) && (j$var48 < noProducts))) {
								{
									for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1)
										// Set the flags to false
										guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = false;
								}
							}
						}
					}
				}
				for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
					if((var19 == j$var48)) {
						for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
							if((j$var48 == j$var68)) {
								for(int i = 0; i < noObs; i += 1)
									// Set the flags to false
									guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
					if((var19 == j$var48)) {
						for(int i = 0; i < noObs; i += 1) {
							if(((0 <= j$var48) && (j$var48 < noProducts))) {
								{
									for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
										if(!guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)]) {
											// The body will execute, so should not be executed again
											guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = true;
											{
												// Reduction of array exped
												// 
												// A generated name to prevent name collisions if the reduction is implemented more
												// than once in inference and probability code. Initialize the variable to the unit
												// value
												double reduceVar$sum$18 = 0.0;
												
												// For each index in the array to be reduced
												for(int cv$reduction1125Index = 0; cv$reduction1125Index < noProducts; cv$reduction1125Index += 1) {
													// Set the left hand term of the reduction function to the return variable value.
													double k = reduceVar$sum$18;
													
													// Set the right hand term to a value from the array exped
													double l = exped[((i - 0) / 1)][cv$reduction1125Index];
													
													// Execute the reduction function, saving the result into the return value.
													// 
													// Copy the result of the reduction into the variable returned by the reduction.
													reduceVar$sum$18 = (k + l);
												}
												prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$18);
											}
										}
									}
								}
							}
						}
					}
				}
				for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
					if((var19 == j$var48)) {
						for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
							if((j$var48 == j$var68)) {
								for(int i = 0; i < noObs; i += 1) {
									if(!guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)]) {
										// The body will execute, so should not be executed again
										guard$sample22put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = true;
										{
											// Reduction of array exped
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$sum$19 = 0.0;
											
											// For each index in the array to be reduced
											for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double k = reduceVar$sum$19;
												
												// Set the right hand term to a value from the array exped
												double l = exped[((i - 0) / 1)][cv$reduction63Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$19 = (k + l);
											}
											prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$19);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 29 drawn from Gaussian 26. Inference was performed using a Gaussian
	// to Gaussian conjugate prior.
	private final void sample29() {
		// State to record the weighting of each sample that is consumed. This is the:
		// sum of the sample denominator*(the sample value - the sample nominator).
		double cv$sum = 0.0;
		
		// State for storing the sum of the squares of the sample denominators.
		double cv$denominatorSquareSum = 0.0;
		
		// Flag to record if we have a value for Sigma.
		boolean cv$sigmaNotFound = true;
		
		// State for the value of sigma once we find it.
		double cv$sigmaValue = 1.0;
		{
			// Processing random variable 34.
			{
				{
					// Processing sample task 41 of consumer random variable null.
					{
						for(int var38 = 0; var38 < noObs; var38 += 1) {
							// State for tracking the changes that happen to the sampled value between it being
							// consumed and it being produced.
							double cv$denominator = 1.0;
							double cv$numerator = 0.0;
							
							// Record the value of a sample generated by a consuming sample 41 of random variable
							// var34.
							// 
							// Add the denominator squared to the sample denominator
							cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
							
							// Add the weighting of the sample to the sum.
							cv$sum = (cv$sum + (cv$denominator * (beta[var38] - cv$numerator)));
							
							// If we have not got the value of sigma yet record it and set a flag so it is not
							// recorded again.
							if(cv$sigmaNotFound) {
								cv$sigmaValue = sigma;
								cv$sigmaNotFound = false;
							}
						}
					}
				}
			}
		}
		
		// Write out the new value of the sample.
		b = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 35 drawn from InverseGamma 32. Inference was performed using a Inverse
	// Gamma to Gaussian conjugate prior.
	private final void sample35() {
		// Variable to track the sum of the difference between the samples and the random
		// variables mean squared.
		double cv$sum = 0.0;
		
		// Variable to record the number of samples from consuming random variables.
		int cv$count = 0;
		{
			// Processing random variable 34.
			{
				{
					// Processing sample task 41 of consumer random variable null.
					{
						for(int var38 = 0; var38 < noObs; var38 += 1) {
							// The mean parameter for Gaussian var34.
							double cv$var34$mu = b;
							
							// Consume sample task 41 from random variable var34.
							// 
							// The difference between the mean parameter and the value sampled from the Gaussian.
							double cv$var34$diff = (cv$var34$mu - beta[var38]);
							
							// Include this sample by adding the square of the difference to the sum.
							cv$sum = (cv$sum + (cv$var34$diff * cv$var34$diff));
							
							// Increment the number of samples in the calculation.
							cv$count = (cv$count + 1);
						}
					}
				}
			}
		}
		
		// Write out the new value of the sample.
		sigma = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 2.0, 2.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 41 drawn from Gaussian 34. Inference was performed using Metropolis-Hastings.
	private final void sample41(int var38, int threadID$cv$var38, Rng RNG$) {
		// The original value of the sample
		double cv$originalValue = beta[var38];
		
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability = 0.0;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		
		// Ensure the variance is at least 0.01
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		
		// The probability of the random variable generating the new sample value.
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// The value currently being tested
			double cv$currentValue;
			if((cv$valuePos == 0))
				// Set the current value to the current state of the tree.
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				
				// Update Sample and intermediate values
				{
					// Write out the value of the sample to a temporary variable prior to updating the
					// intermediate variables.
					double var39 = cv$proposedValue;
					beta[var38] = cv$currentValue;
					
					// Guards to ensure that exped is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 41 and consumer double[] 56.
					{
						for(int i = 0; i < noObs; i += 1) {
							if((var38 == i)) {
								for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1)
									exped[((i - 0) / 1)][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
							}
						}
					}
					
					// Guards to ensure that prob is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 41 and consumer double[] 71.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[][] guard$sample41put74 = guard$sample41put74$global[threadID$cv$var38];
						for(int i = 0; i < noObs; i += 1) {
							if((var38 == i)) {
								for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
									if(((0 <= j$var48) && (j$var48 < noProducts))) {
										{
											for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1)
												// Set the flags to false
												guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = false;
										}
									}
								}
							}
						}
						for(int i = 0; i < noObs; i += 1) {
							if((var38 == i)) {
								for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
									for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
										if((j$var48 == j$var68))
											// Set the flags to false
											guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int i = 0; i < noObs; i += 1) {
							if((var38 == i)) {
								for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
									if(((0 <= j$var48) && (j$var48 < noProducts))) {
										{
											for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
												if(!guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)]) {
													// The body will execute, so should not be executed again
													guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = true;
													{
														// Reduction of array exped
														// 
														// A generated name to prevent name collisions if the reduction is implemented more
														// than once in inference and probability code. Initialize the variable to the unit
														// value
														double reduceVar$sum$20 = 0.0;
														
														// For each index in the array to be reduced
														for(int cv$reduction1240Index = 0; cv$reduction1240Index < noProducts; cv$reduction1240Index += 1) {
															// Set the left hand term of the reduction function to the return variable value.
															double k = reduceVar$sum$20;
															
															// Set the right hand term to a value from the array exped
															double l = exped[((i - 0) / 1)][cv$reduction1240Index];
															
															// Execute the reduction function, saving the result into the return value.
															// 
															// Copy the result of the reduction into the variable returned by the reduction.
															reduceVar$sum$20 = (k + l);
														}
														prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$20);
													}
												}
											}
										}
									}
								}
							}
						}
						for(int i = 0; i < noObs; i += 1) {
							if((var38 == i)) {
								for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
									for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
										if((j$var48 == j$var68)) {
											if(!guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = true;
												{
													// Reduction of array exped
													// 
													// A generated name to prevent name collisions if the reduction is implemented more
													// than once in inference and probability code. Initialize the variable to the unit
													// value
													double reduceVar$sum$21 = 0.0;
													
													// For each index in the array to be reduced
													for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1) {
														// Set the left hand term of the reduction function to the return variable value.
														double k = reduceVar$sum$21;
														
														// Set the right hand term to a value from the array exped
														double l = exped[((i - 0) / 1)][cv$reduction63Index];
														
														// Execute the reduction function, saving the result into the return value.
														// 
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$sum$21 = (k + l);
													}
													prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$21);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$b;
				{
					cv$temp$0$b = b;
				}
				double cv$temp$1$sigma;
				{
					cv$temp$1$sigma = sigma;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$b) / Math.sqrt(cv$temp$1$sigma))) - (0.5 * Math.log(cv$temp$1$sigma))));
				
				// Processing random variable 72.
				{
					// Looking for a path between Sample 41 and consumer Categorical 72.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[] guard$sample41categorical75 = guard$sample41categorical75$global[threadID$cv$var38];
						for(int i = 0; i < noObs; i += 1) {
							if((var38 == i)) {
								for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
									if(((0 <= j$var48) && (j$var48 < noProducts))) {
										{
											// Set the flags to false
											guard$sample41categorical75[((i - 0) / 1)] = false;
										}
									}
								}
							}
						}
						for(int i = 0; i < noObs; i += 1) {
							if((var38 == i)) {
								for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
									for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
										if((j$var48 == j$var68))
											// Set the flags to false
											guard$sample41categorical75[((i - 0) / 1)] = false;
									}
								}
							}
						}
						double traceTempVariable$var50$8_1 = cv$currentValue;
						for(int i = 0; i < noObs; i += 1) {
							if((var38 == i)) {
								for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
									double traceTempVariable$k$8_4 = Math.exp((ut[j$var48] - (traceTempVariable$var50$8_1 * Prices[i][j$var48])));
									if(((0 <= j$var48) && (j$var48 < noProducts))) {
										{
											if((0 < noProducts)) {
												// Reduction of array exped
												// 
												// A generated name to prevent name collisions if the reduction is implemented more
												// than once in inference and probability code. Initialize the variable to the unit
												// value
												double reduceVar$sum$22 = 0.0;
												
												// Reduce for every value except a masked value which will be skipped.
												for(int cv$reduction1318Index = 0; cv$reduction1318Index < j$var48; cv$reduction1318Index += 1) {
													// Set the left hand term of the reduction function to the return variable value.
													double k = reduceVar$sum$22;
													
													// Set the right hand term to a value from the array exped
													double l = exped[((i - 0) / 1)][cv$reduction1318Index];
													
													// Execute the reduction function, saving the result into the return value.
													// 
													// Copy the result of the reduction into the variable returned by the reduction.
													reduceVar$sum$22 = (k + l);
												}
												for(int cv$reduction1318Index = (j$var48 + 1); cv$reduction1318Index < noProducts; cv$reduction1318Index += 1) {
													// Set the left hand term of the reduction function to the return variable value.
													double k = reduceVar$sum$22;
													
													// Set the right hand term to a value from the array exped
													double l = exped[((i - 0) / 1)][cv$reduction1318Index];
													
													// Execute the reduction function, saving the result into the return value.
													// 
													// Execute the reduction function, saving the result into the return value.
													// 
													// Copy the result of the reduction into the variable returned by the reduction.
													reduceVar$sum$22 = (k + l);
												}
												double cv$reduced63 = reduceVar$sum$22;
												
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$22 = (traceTempVariable$k$8_4 + cv$reduced63);
												double traceTempVariable$sum$8_5 = reduceVar$sum$22;
												if(!guard$sample41categorical75[((i - 0) / 1)]) {
													// The body will execute, so should not be executed again
													guard$sample41categorical75[((i - 0) / 1)] = true;
													
													// Processing sample task 76 of consumer random variable null.
													{
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
																		double[] cv$temp$2$prob;
																		{
																			cv$temp$2$prob = prob[((i - 0) / 1)];
																		}
																		
																		// Record the probability of sample task 76 generating output with current configuration.
																		if(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)));
																		}
																		
																		// Recorded the probability of reaching sample task 76 with the current configuration.
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
									}
								}
							}
						}
						double traceTempVariable$var50$9_1 = cv$currentValue;
						for(int i = 0; i < noObs; i += 1) {
							if((var38 == i)) {
								for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
									double traceTempVariable$var69$9_4 = Math.exp((ut[j$var48] - (traceTempVariable$var50$9_1 * Prices[i][j$var48])));
									for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
										if((j$var48 == j$var68)) {
											if(!guard$sample41categorical75[((i - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample41categorical75[((i - 0) / 1)] = true;
												
												// Processing sample task 76 of consumer random variable null.
												{
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
																	double[] cv$temp$3$prob;
																	{
																		cv$temp$3$prob = prob[((i - 0) / 1)];
																	}
																	
																	// Record the probability of sample task 76 generating output with current configuration.
																	if(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[i]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[i]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[i]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[i]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[i]]):Double.NEGATIVE_INFINITY)));
																	}
																	
																	// Recorded the probability of reaching sample task 76 with the current configuration.
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
		
		// The probability ration for the proposed value and the current value.
		double cv$ratio = (cv$proposedProbability - cv$originalProbability);
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
			// If it is not revert the changes.
			// 
			// Set the sample value
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			double var39 = cv$originalValue;
			beta[var38] = var39;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 41 and consumer double[] 56.
			{
				for(int i = 0; i < noObs; i += 1) {
					if((var38 == i)) {
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1)
							exped[((i - 0) / 1)][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
					}
				}
			}
			
			// Guards to ensure that prob is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 41 and consumer double[] 71.
			{
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				boolean[][] guard$sample41put74 = guard$sample41put74$global[threadID$cv$var38];
				for(int i = 0; i < noObs; i += 1) {
					if((var38 == i)) {
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							if(((0 <= j$var48) && (j$var48 < noProducts))) {
								{
									for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1)
										// Set the flags to false
										guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = false;
								}
							}
						}
					}
				}
				for(int i = 0; i < noObs; i += 1) {
					if((var38 == i)) {
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
								if((j$var48 == j$var68))
									// Set the flags to false
									guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int i = 0; i < noObs; i += 1) {
					if((var38 == i)) {
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							if(((0 <= j$var48) && (j$var48 < noProducts))) {
								{
									for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
										if(!guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)]) {
											// The body will execute, so should not be executed again
											guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = true;
											{
												// Reduction of array exped
												// 
												// A generated name to prevent name collisions if the reduction is implemented more
												// than once in inference and probability code. Initialize the variable to the unit
												// value
												double reduceVar$sum$23 = 0.0;
												
												// For each index in the array to be reduced
												for(int cv$reduction1404Index = 0; cv$reduction1404Index < noProducts; cv$reduction1404Index += 1) {
													// Set the left hand term of the reduction function to the return variable value.
													double k = reduceVar$sum$23;
													
													// Set the right hand term to a value from the array exped
													double l = exped[((i - 0) / 1)][cv$reduction1404Index];
													
													// Execute the reduction function, saving the result into the return value.
													// 
													// Copy the result of the reduction into the variable returned by the reduction.
													reduceVar$sum$23 = (k + l);
												}
												prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$23);
											}
										}
									}
								}
							}
						}
					}
				}
				for(int i = 0; i < noObs; i += 1) {
					if((var38 == i)) {
						for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
							for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
								if((j$var48 == j$var68)) {
									if(!guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)]) {
										// The body will execute, so should not be executed again
										guard$sample41put74[((i - 0) / 1)][((j$var68 - 0) / 1)] = true;
										{
											// Reduction of array exped
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$sum$24 = 0.0;
											
											// For each index in the array to be reduced
											for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double k = reduceVar$sum$24;
												
												// Set the right hand term to a value from the array exped
												double l = exped[((i - 0) / 1)][cv$reduction63Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$24 = (k + l);
											}
											prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$24);
										}
									}
								}
							}
						}
					}
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
		// Constructor for guard$sample22put74$global
		{
			// Calculate the largest index of i that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_i = 0;
			
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var68 = 0;
			for(int i = 0; i < noObs; i += 1)
				cv$max_j$var68 = Math.max(cv$max_j$var68, ((noProducts - 0) / 1));
			cv$max_i = Math.max(cv$max_i, ((noObs - 0) / 1));
			
			// Allocation of guard$sample22put74$global for single threaded execution
			guard$sample22put74$global = new boolean[cv$max_i][cv$max_j$var68];
		}
		
		// Constructor for guard$sample22categorical75$global
		{
			// Calculate the largest index of i that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_i = 0;
			cv$max_i = Math.max(cv$max_i, ((noObs - 0) / 1));
			
			// Allocation of guard$sample22categorical75$global for single threaded execution
			guard$sample22categorical75$global = new boolean[cv$max_i];
		}
		
		// Constructor for guard$sample41put74$global
		{
			// Calculate the largest index of i that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_i = 0;
			
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var68 = 0;
			for(int i = 0; i < noObs; i += 1)
				cv$max_j$var68 = Math.max(cv$max_j$var68, ((noProducts - 0) / 1));
			cv$max_i = Math.max(cv$max_i, ((noObs - 0) / 1));
			
			// Allocation of guard$sample41put74$global for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				guard$sample41put74$global = new boolean[cv$threadCount][][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					guard$sample41put74$global[cv$index] = new boolean[cv$max_i][cv$max_j$var68];
			}
		}
		
		// Constructor for guard$sample41categorical75$global
		{
			// Calculate the largest index of i that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_i = 0;
			cv$max_i = Math.max(cv$max_i, ((noObs - 0) / 1));
			
			// Allocation of guard$sample41categorical75$global for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				guard$sample41categorical75$global = new boolean[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					guard$sample41categorical75$global[cv$index] = new boolean[cv$max_i];
			}
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If ut has not been set already allocate space.
		if(!setFlag$ut) {
			// Constructor for ut
			{
				ut = new double[noProducts];
			}
		}
		
		// If beta has not been set already allocate space.
		if(!setFlag$beta) {
			// Constructor for beta
			{
				beta = new double[noObs];
			}
		}
		
		// If choices has not been set already allocate space.
		if(!setFlag$choices) {
			// Constructor for choices
			{
				choices = new int[noObs];
			}
		}
		
		// Constructor for exped
		{
			exped = new double[((((noObs - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < noObs; i += 1)
				exped[((i - 0) / 1)] = new double[noProducts];
		}
		
		// Constructor for prob
		{
			prob = new double[((((noObs - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < noObs; i += 1)
				prob[((i - 0) / 1)] = new double[noProducts];
		}
		
		// Constructor for logProbability$sample22
		{
			logProbability$sample22 = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample41
		{
			logProbability$sample41 = new double[((((noObs - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$var72
		{
			logProbability$var72 = new double[((((noObs - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample76
		{
			logProbability$sample76 = new double[((((noObs - 1) - 0) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var19, int forEnd$var19, int threadID$var19, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var19 = forStart$var19; var19 < forEnd$var19; var19 += 1) {
						if(!fixedFlag$sample22)
							ut[var19] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample29)
			b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample35)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1) {
						if(!fixedFlag$sample41)
							beta[var38] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var48, int forEnd$j$var48, int threadID$j$var48, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var48 = forStart$j$var48; j$var48 < forEnd$j$var48; j$var48 += 1) {
										if(!(fixedFlag$sample22 && fixedFlag$sample41))
											exped[((i - 0) / 1)][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
									}
							}
						);
						
						// Reduction of array exped
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$sum$25 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$sum$25;
							
							// Set the right hand term to a value from the array exped
							double l = exped[((i - 0) / 1)][cv$reduction63Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!(fixedFlag$sample22 && fixedFlag$sample41))
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$sum$25 = (k + l);
						}
						
						// Alternative value for reduceVar$sum$25 to make it effectively final.
						double reduceVar$sum$25$1 = reduceVar$sum$25;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1) {
										if(!(fixedFlag$sample22 && fixedFlag$sample41))
											prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$25$1);
									}
							}
						);
						if(!fixedFlag$sample76)
							choices[i] = DistributionSampling.sampleCategorical(RNG$1, prob[((i - 0) / 1)]);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var19, int forEnd$var19, int threadID$var19, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var19 = forStart$var19; var19 < forEnd$var19; var19 += 1) {
						if(!fixedFlag$sample22)
							ut[var19] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample29)
			b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample35)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1) {
						if(!fixedFlag$sample41)
							beta[var38] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var48, int forEnd$j$var48, int threadID$j$var48, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var48 = forStart$j$var48; j$var48 < forEnd$j$var48; j$var48 += 1) {
										if(!(fixedFlag$sample22 && fixedFlag$sample41))
											exped[((i - 0) / 1)][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
									}
							}
						);
						
						// Reduction of array exped
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$sum$27 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$sum$27;
							
							// Set the right hand term to a value from the array exped
							double l = exped[((i - 0) / 1)][cv$reduction63Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!(fixedFlag$sample22 && fixedFlag$sample41))
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$sum$27 = (k + l);
						}
						
						// Alternative value for reduceVar$sum$27 to make it effectively final.
						double reduceVar$sum$27$1 = reduceVar$sum$27;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1) {
										if(!(fixedFlag$sample22 && fixedFlag$sample41))
											prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$27$1);
									}
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
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var19, int forEnd$var19, int threadID$var19, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var19 = forStart$var19; var19 < forEnd$var19; var19 += 1) {
						if(!fixedFlag$sample22)
							ut[var19] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample29)
			b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample35)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1) {
						if(!fixedFlag$sample41)
							beta[var38] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var48, int forEnd$j$var48, int threadID$j$var48, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var48 = forStart$j$var48; j$var48 < forEnd$j$var48; j$var48 += 1) {
										if(!(fixedFlag$sample22 && fixedFlag$sample41))
											exped[((i - 0) / 1)][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
									}
							}
						);
						
						// Reduction of array exped
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$sum$26 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$sum$26;
							
							// Set the right hand term to a value from the array exped
							double l = exped[((i - 0) / 1)][cv$reduction63Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!(fixedFlag$sample22 && fixedFlag$sample41))
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$sum$26 = (k + l);
						}
						
						// Alternative value for reduceVar$sum$26 to make it effectively final.
						double reduceVar$sum$26$1 = reduceVar$sum$26;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1) {
										if(!(fixedFlag$sample22 && fixedFlag$sample41))
											prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$26$1);
									}
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
			for(int var19 = 0; var19 < noProducts; var19 += 1) {
				if(!fixedFlag$sample22)
					sample22(var19);
			}
			if(!fixedFlag$sample29)
				sample29();
			if(!fixedFlag$sample35)
				sample35();
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1) {
							if(!fixedFlag$sample41)
								sample41(var38, threadID$var38, RNG$1);
						}
				}
			);
		}
		// Infer the samples in reverse chronological order.
		else {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1) {
							if(!fixedFlag$sample41)
								sample41(var38, threadID$var38, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample35)
				sample35();
			if(!fixedFlag$sample29)
				sample29();
			for(int var19 = (noProducts - ((((noProducts - 1) - 0) % 1) + 1)); var19 >= ((0 - 1) + 1); var19 -= 1) {
				if(!fixedFlag$sample22)
					sample22(var19);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {}

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
		logProbability$ut = 0.0;
		logProbability$prob = 0.0;
		logProbability$exped = 0.0;
		if(!fixedProbFlag$sample22) {
			for(int var19 = 0; var19 < noProducts; var19 += 1)
				logProbability$sample22[((var19 - 0) / 1)] = 0.0;
		}
		logProbability$var26 = 0.0;
		if(!fixedProbFlag$sample29)
			logProbability$b = 0.0;
		logProbability$var32 = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$sigma = 0.0;
		logProbability$var34 = 0.0;
		logProbability$beta = 0.0;
		if(!fixedProbFlag$sample41) {
			for(int var38 = 0; var38 < noObs; var38 += 1)
				logProbability$sample41[((var38 - 0) / 1)] = 0.0;
		}
		for(int i = 0; i < noObs; i += 1)
			logProbability$var72[((i - 0) / 1)] = 0.0;
		logProbability$choices = 0.0;
		if(!fixedProbFlag$sample76) {
			for(int i = 0; i < noObs; i += 1)
				logProbability$sample76[((i - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample22)
			logProbabilityValue$sample22();
		if(fixedFlag$sample29)
			logProbabilityValue$sample29();
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		if(fixedFlag$sample41)
			logProbabilityValue$sample41();
		logProbabilityValue$sample76();
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
		logProbabilityValue$sample22();
		logProbabilityValue$sample29();
		logProbabilityValue$sample35();
		logProbabilityValue$sample41();
		logProbabilityValue$sample76();
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
		logProbabilityValue$sample22();
		logProbabilityValue$sample29();
		logProbabilityValue$sample35();
		logProbabilityValue$sample41();
		logProbabilityValue$sample76();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var19, int forEnd$var19, int threadID$var19, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var19 = forStart$var19; var19 < forEnd$var19; var19 += 1) {
						if(!fixedFlag$sample22)
							ut[var19] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample29)
			b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample35)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1) {
						if(!fixedFlag$sample41)
							beta[var38] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var48, int forEnd$j$var48, int threadID$j$var48, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var48 = forStart$j$var48; j$var48 < forEnd$j$var48; j$var48 += 1) {
										if(!(fixedFlag$sample22 && fixedFlag$sample41))
											exped[((i - 0) / 1)][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
									}
							}
						);
						
						// Reduction of array exped
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$sum$28 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$sum$28;
							
							// Set the right hand term to a value from the array exped
							double l = exped[((i - 0) / 1)][cv$reduction63Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!(fixedFlag$sample22 && fixedFlag$sample41))
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$sum$28 = (k + l);
						}
						
						// Alternative value for reduceVar$sum$28 to make it effectively final.
						double reduceVar$sum$28$1 = reduceVar$sum$28;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1) {
										if(!(fixedFlag$sample22 && fixedFlag$sample41))
											prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$28$1);
									}
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
		// Deep copy between arrays
		int[] cv$source1 = ObsChoices;
		int[] cv$target1 = choices;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var48, int forEnd$j$var48, int threadID$j$var48, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var48 = forStart$j$var48; j$var48 < forEnd$j$var48; j$var48 += 1) {
										if((setFlag$ut && setFlag$beta))
											exped[((i - 0) / 1)][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
									}
							}
						);
						
						// Reduction of array exped
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						double reduceVar$sum$29 = 0.0;
						
						// For each index in the array to be reduced
						for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$sum$29;
							
							// Set the right hand term to a value from the array exped
							double l = exped[((i - 0) / 1)][cv$reduction63Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$sum$29 = (k + l);
						}
						
						// Alternative value for reduceVar$sum$29 to make it effectively final.
						double reduceVar$sum$29$1 = reduceVar$sum$29;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1) {
										if((setFlag$ut && setFlag$beta))
											prob[((i - 0) / 1)][j$var68] = (exped[((i - 0) / 1)][j$var68] / reduceVar$sum$29$1);
									}
							}
						);
					}
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\n\nmodel DiscreteChoiceRandCoeff(int noProducts, int noObs, int[] ObsChoices, int[][] Prices) {\n    // we just need an uninformative prior for utility intercepts\n\n    // draw utilities\n    double[] ut = gaussian(0, 10).sample(noProducts);\n    //can we set the first element to 0? like ut[0] <~ 0\n\n    //priors for distribution of beta\n    double b = gaussian(0,10).sample();\n    double sigma =  inverseGamma(2,2).sample();\n\n    double[] beta = gaussian(b, sigma).sample(noObs);\n\n    int[] choices = new int[noObs];\n\n    for (int i:[0..noObs)){\n        // calculate choice probabilities for consumer i\n\n        double[] exped = new double[noProducts];\n        for(int j : [0..noProducts)) {\n            exped[j] = exp(ut[j]- beta[i]*Prices[i][j]);\n            }\n        double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n        public double[] prob = new double[noProducts];\n        for (int j : [0..noProducts)) {\n            prob[j] = exped[j] / sum;\n        }\n        // emit choices of consumer i\n        choices[i] = categorical(prob).sample();\n                                }\n\n    // assert that generated choices match observed choices\n    choices.observe(ObsChoices);\n}";
	}
}