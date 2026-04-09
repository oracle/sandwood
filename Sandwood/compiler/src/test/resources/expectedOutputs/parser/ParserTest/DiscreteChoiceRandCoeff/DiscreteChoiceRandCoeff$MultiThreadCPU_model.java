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
	private boolean fixedFlag$sample21 = false;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample47 = false;
	private boolean fixedProbFlag$sample103 = false;
	private boolean fixedProbFlag$sample21 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample47 = false;
	private boolean[] guard$sample21categorical102$global;
	private boolean[][] guard$sample21put101$global;
	private boolean[][] guard$sample47categorical102$global;
	private boolean[][][] guard$sample47put101$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b;
	private double logProbability$beta;
	private double logProbability$choices;
	private double logProbability$exped;
	private double logProbability$prob;
	private double[] logProbability$sample103;
	private double[] logProbability$sample21;
	private double[] logProbability$sample47;
	private double logProbability$sigma;
	private double logProbability$ut;
	private double[] logProbability$var101;
	private double logProbability$var27;
	private double logProbability$var33;
	private double logProbability$var35;
	private double logProbability$var9;
	private int noObs;
	private int noProducts;
	private double[][] prob;
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
		// Set ObsChoices
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
		// Set Prices
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
		// Set flags for all the side effects of b including if probabilities need to be updated.
		b = cv$value;
		
		// Unset the fixed probability flag for sample 28 as it depends on b.
		fixedProbFlag$sample28 = false;
		
		// Unset the fixed probability flag for sample 47 as it depends on b.
		fixedProbFlag$sample47 = false;
	}

	// Getter for beta.
	@Override
	public final double[] get$beta() {
		return beta;
	}

	// Setter for beta.
	@Override
	public final void set$beta(double[] cv$value) {
		// Set flags for all the side effects of beta including if probabilities need to be
		// updated.
		// Set beta
		beta = cv$value;
		
		// Unset the fixed probability flag for sample 47 as it depends on beta.
		fixedProbFlag$sample47 = false;
		
		// Unset the fixed probability flag for sample 103 as it depends on beta.
		fixedProbFlag$sample103 = false;
	}

	// Getter for choices.
	@Override
	public final int[] get$choices() {
		return choices;
	}

	// Getter for fixedFlag$sample21.
	@Override
	public final boolean get$fixedFlag$sample21() {
		return fixedFlag$sample21;
	}

	// Setter for fixedFlag$sample21.
	@Override
	public final void set$fixedFlag$sample21(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample21 including if probabilities
		// need to be updated.
		fixedFlag$sample21 = cv$value;
		
		// Should the probability of sample 21 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample21 = (fixedFlag$sample21 && fixedProbFlag$sample21);
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample103 = (fixedFlag$sample21 && fixedProbFlag$sample103);
	}

	// Getter for fixedFlag$sample28.
	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	// Setter for fixedFlag$sample28.
	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample28 including if probabilities
		// need to be updated.
		fixedFlag$sample28 = cv$value;
		
		// Should the probability of sample 28 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample28 = (fixedFlag$sample28 && fixedProbFlag$sample28);
		
		// Should the probability of sample 47 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample47 = (fixedFlag$sample28 && fixedProbFlag$sample47);
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
		fixedProbFlag$sample34 = (fixedFlag$sample34 && fixedProbFlag$sample34);
		
		// Should the probability of sample 47 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample47 = (fixedFlag$sample34 && fixedProbFlag$sample47);
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
		fixedProbFlag$sample47 = (fixedFlag$sample47 && fixedProbFlag$sample47);
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample103 = (fixedFlag$sample47 && fixedProbFlag$sample103);
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
		// Set flags for all the side effects of sigma including if probabilities need to
		// be updated.
		sigma = cv$value;
		
		// Unset the fixed probability flag for sample 34 as it depends on sigma.
		fixedProbFlag$sample34 = false;
		
		// Unset the fixed probability flag for sample 47 as it depends on sigma.
		fixedProbFlag$sample47 = false;
	}

	// Getter for ut.
	@Override
	public final double[] get$ut() {
		return ut;
	}

	// Setter for ut.
	@Override
	public final void set$ut(double[] cv$value) {
		// Set flags for all the side effects of ut including if probabilities need to be
		// updated.
		// Set ut
		ut = cv$value;
		
		// Unset the fixed probability flag for sample 21 as it depends on ut.
		fixedProbFlag$sample21 = false;
		
		// Unset the fixed probability flag for sample 103 as it depends on ut.
		fixedProbFlag$sample103 = false;
	}

	// Calculate the probability of the samples represented by sample103 using sampled
	// values.
	private final void logProbabilityValue$sample103() {
		// Determine if we need to calculate the values for sample task 103 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample103) {
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
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noProducts))?Math.log(prob[((i - 0) / 1)][cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
				logProbability$var101[((i - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample103[((i - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$choices = (logProbability$choices + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample103 = (fixedFlag$sample21 && fixedFlag$sample47);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < noObs; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample103[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var101[((i - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$choices = (logProbability$choices + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample21 using sampled
	// values.
	private final void logProbabilityValue$sample21() {
		// Determine if we need to calculate the values for sample task 21 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample21) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var20 = 0; var20 < noProducts; var20 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = ut[var20];
					{
						{
							double var7 = 0.0;
							double var8 = 10.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var7) / Math.sqrt(var8))) - (0.5 * Math.log(var8))));
							
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
				logProbability$sample21[((var20 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that exped is only updated once for this probability.
				boolean cv$guard$exped = false;
				
				// Guard to ensure that prob is only updated once for this probability.
				boolean cv$guard$prob = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 21 and consumer double[] 77.
				{
					for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
						if((var20 == j$var69)) {
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
				
				// Looking for a path between Sample 21 and consumer double[] 100.
				{
					for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
						if((var20 == j$var69)) {
							for(int i = 0; i < noObs; i += 1) {
								if(((0 <= j$var69) && (j$var69 < noProducts))) {
									{
										for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
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
					for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
						if((var20 == j$var69)) {
							for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
								if((j$var69 == j$var97)) {
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
			logProbability$var9 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample21 = fixedFlag$sample21;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int var20 = 0; var20 < noProducts; var20 += 1) {
				double cv$sampleValue = logProbability$sample21[((var20 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Guard to ensure that exped is only updated once for this probability.
				boolean cv$guard$exped = false;
				
				// Guard to ensure that prob is only updated once for this probability.
				boolean cv$guard$prob = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 21 and consumer double[] 77.
				{
					for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
						if((var20 == j$var69)) {
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
				
				// Looking for a path between Sample 21 and consumer double[] 100.
				{
					for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
						if((var20 == j$var69)) {
							for(int i = 0; i < noObs; i += 1) {
								if(((0 <= j$var69) && (j$var69 < noProducts))) {
									{
										for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
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
					for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
						if((var20 == j$var69)) {
							for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
								if((j$var69 == j$var97)) {
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
			logProbability$var9 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample28 using sampled
	// values.
	private final void logProbabilityValue$sample28() {
		// Determine if we need to calculate the values for sample task 28 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample28) {
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
						double var25 = 0.0;
						double var26 = 10.0;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var25) / Math.sqrt(var26))) - (0.5 * Math.log(var26))));
						
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
			logProbability$var27 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$b = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample28 = fixedFlag$sample28;
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
			logProbability$var27 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample28)
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
						double var31 = 2.0;
						double var32 = 2.0;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var31, var32));
						
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
			logProbability$var33 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$sigma = cv$sampleProbability;
			
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
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$sigma;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var33 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample47 using sampled
	// values.
	private final void logProbabilityValue$sample47() {
		// Determine if we need to calculate the values for sample task 47 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample47) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var46 = 0; var46 < noObs; var46 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = beta[var46];
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
				logProbability$sample47[((var46 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that exped is only updated once for this probability.
				boolean cv$guard$exped = false;
				
				// Guard to ensure that prob is only updated once for this probability.
				boolean cv$guard$prob = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 47 and consumer double[] 77.
				{
					for(int i = 0; i < noObs; i += 1) {
						if((var46 == i)) {
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
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
				
				// Looking for a path between Sample 47 and consumer double[] 100.
				{
					for(int i = 0; i < noObs; i += 1) {
						if((var46 == i)) {
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								if(((0 <= j$var69) && (j$var69 < noProducts))) {
									{
										for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
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
						if((var46 == i)) {
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
									if((j$var69 == j$var97)) {
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
			logProbability$var35 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$beta = (logProbability$beta + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample47 = ((fixedFlag$sample47 && fixedFlag$sample28) && fixedFlag$sample34);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int var46 = 0; var46 < noObs; var46 += 1) {
				double cv$sampleValue = logProbability$sample47[((var46 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Guard to ensure that exped is only updated once for this probability.
				boolean cv$guard$exped = false;
				
				// Guard to ensure that prob is only updated once for this probability.
				boolean cv$guard$prob = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 47 and consumer double[] 77.
				{
					for(int i = 0; i < noObs; i += 1) {
						if((var46 == i)) {
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
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
				
				// Looking for a path between Sample 47 and consumer double[] 100.
				{
					for(int i = 0; i < noObs; i += 1) {
						if((var46 == i)) {
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								if(((0 <= j$var69) && (j$var69 < noProducts))) {
									{
										for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
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
						if((var46 == i)) {
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
									if((j$var69 == j$var97)) {
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
			logProbability$var35 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$beta = (logProbability$beta + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 21 drawn from Gaussian 9. Inference was performed using Metropolis-Hastings.
	private final void sample21(int var20) {
		if(true) {
			// Calculate the number of states to evaluate.
			int cv$numNumStates = 0;
			{
				// Metropolis-Hastings
				cv$numNumStates = Math.max(cv$numNumStates, 2);
			}
			
			// The original value of the sample
			double cv$originalValue = ut[var20];
			
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
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
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
						double var21 = cv$proposedValue;
						
						// Guards to ensure that ut is only updated when there is a valid path.
						{
							{
								ut[var20] = cv$currentValue;
							}
						}
						
						// Guards to ensure that exped is only updated when there is a valid path.
						// 
						// Looking for a path between Sample 21 and consumer double[] 77.
						{
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								if((var20 == j$var69)) {
									for(int i = 0; i < noObs; i += 1)
										exped[((i - 0) / 1)][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
								}
							}
						}
						
						// Guards to ensure that prob is only updated when there is a valid path.
						// 
						// Looking for a path between Sample 21 and consumer double[] 100.
						{
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							boolean[][] guard$sample21put101 = guard$sample21put101$global;
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								if((var20 == j$var69)) {
									for(int i = 0; i < noObs; i += 1) {
										if(((0 <= j$var69) && (j$var69 < noProducts))) {
											{
												for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
													// Set the flags to false
													guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
											}
										}
									}
								}
							}
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								if((var20 == j$var69)) {
									for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
										if((j$var69 == j$var97)) {
											for(int i = 0; i < noObs; i += 1)
												// Set the flags to false
												guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
										}
									}
								}
							}
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								if((var20 == j$var69)) {
									for(int i = 0; i < noObs; i += 1) {
										if(((0 <= j$var69) && (j$var69 < noProducts))) {
											{
												for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
													if(!guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
														{
															// Reduction of array exped
															// 
															// A generated name to prevent name collisions if the reduction is implemented more
															// than once in inference and probability code. Initialize the variable to the unit
															// value
															double reduceVar$sum$15 = 0.0;
															
															// For each index in the array to be reduced
															for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
																// Set the left hand term of the reduction function to the return variable value.
																double k = reduceVar$sum$15;
																
																// Set the right hand term to a value from the array exped
																double l = exped[((i - 0) / 1)][cv$reduction82Index];
																
																// Execute the reduction function, saving the result into the return value.
																// 
																// Copy the result of the reduction into the variable returned by the reduction.
																reduceVar$sum$15 = (k + l);
															}
															prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$15);
														}
													}
												}
											}
										}
									}
								}
							}
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								if((var20 == j$var69)) {
									for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
										if((j$var69 == j$var97)) {
											for(int i = 0; i < noObs; i += 1) {
												if(!guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
													// The body will execute, so should not be executed again
													guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
													{
														// Reduction of array exped
														// 
														// A generated name to prevent name collisions if the reduction is implemented more
														// than once in inference and probability code. Initialize the variable to the unit
														// value
														double reduceVar$sum$16 = 0.0;
														
														// For each index in the array to be reduced
														for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
															// Set the left hand term of the reduction function to the return variable value.
															double k = reduceVar$sum$16;
															
															// Set the right hand term to a value from the array exped
															double l = exped[((i - 0) / 1)][cv$reduction82Index];
															
															// Execute the reduction function, saving the result into the return value.
															// 
															// Copy the result of the reduction into the variable returned by the reduction.
															reduceVar$sum$16 = (k + l);
														}
														prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$16);
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
					double cv$temp$0$var7;
					{
						cv$temp$0$var7 = 0.0;
					}
					double cv$temp$1$var8;
					{
						cv$temp$1$var8 = 10.0;
					}
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var7) / Math.sqrt(cv$temp$1$var8))) - (0.5 * Math.log(cv$temp$1$var8))));
					
					// Processing random variable 101.
					{
						// Looking for a path between Sample 21 and consumer Categorical 101.
						{
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							boolean[] guard$sample21categorical102 = guard$sample21categorical102$global;
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								if((var20 == j$var69)) {
									for(int i = 0; i < noObs; i += 1) {
										if(((0 <= j$var69) && (j$var69 < noProducts))) {
											{
												// Set the flags to false
												guard$sample21categorical102[((i - 0) / 1)] = false;
											}
										}
									}
								}
							}
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								if((var20 == j$var69)) {
									for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
										if((j$var69 == j$var97)) {
											for(int i = 0; i < noObs; i += 1)
												// Set the flags to false
												guard$sample21categorical102[((i - 0) / 1)] = false;
										}
									}
								}
							}
							double traceTempVariable$var70$9_1 = cv$currentValue;
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								if((var20 == j$var69)) {
									for(int i = 0; i < noObs; i += 1) {
										double traceTempVariable$k$9_4 = Math.exp((traceTempVariable$var70$9_1 - (beta[i] * Prices[i][j$var69])));
										if(((0 <= j$var69) && (j$var69 < noProducts))) {
											{
												if((0 < noProducts)) {
													// Reduction of array exped
													// 
													// A generated name to prevent name collisions if the reduction is implemented more
													// than once in inference and probability code. Initialize the variable to the unit
													// value
													double reduceVar$sum$17 = 0.0;
													
													// Reduce for every value except a masked value which will be skipped.
													for(int cv$reduction1488Index = 0; cv$reduction1488Index < j$var69; cv$reduction1488Index += 1) {
														// Set the left hand term of the reduction function to the return variable value.
														double k = reduceVar$sum$17;
														
														// Set the right hand term to a value from the array exped
														double l = exped[((i - 0) / 1)][cv$reduction1488Index];
														
														// Execute the reduction function, saving the result into the return value.
														// 
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$sum$17 = (k + l);
													}
													for(int cv$reduction1488Index = (j$var69 + 1); cv$reduction1488Index < noProducts; cv$reduction1488Index += 1) {
														// Set the left hand term of the reduction function to the return variable value.
														double k = reduceVar$sum$17;
														
														// Set the right hand term to a value from the array exped
														double l = exped[((i - 0) / 1)][cv$reduction1488Index];
														
														// Execute the reduction function, saving the result into the return value.
														// 
														// Execute the reduction function, saving the result into the return value.
														// 
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$sum$17 = (k + l);
													}
													double cv$reduced82 = reduceVar$sum$17;
													
													// Copy the result of the reduction into the variable returned by the reduction.
													reduceVar$sum$17 = (traceTempVariable$k$9_4 + cv$reduced82);
													double traceTempVariable$sum$9_5 = reduceVar$sum$17;
													if(!guard$sample21categorical102[((i - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample21categorical102[((i - 0) / 1)] = true;
														
														// Processing sample task 103 of consumer random variable null.
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
																			int cv$temp$3$$var1123;
																			{
																				// Constructing a random variable input for use later.
																				int $var1123 = noProducts;
																				cv$temp$3$$var1123 = $var1123;
																			}
																			
																			// Record the probability of sample task 103 generating output with current configuration.
																			if(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$$var1123))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$$var1123))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$$var1123))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$$var1123))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$$var1123))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)));
																			}
																			
																			// Recorded the probability of reaching sample task 103 with the current configuration.
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
							double traceTempVariable$var70$10_1 = cv$currentValue;
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								if((var20 == j$var69)) {
									for(int i = 0; i < noObs; i += 1) {
										double traceTempVariable$var98$10_4 = Math.exp((traceTempVariable$var70$10_1 - (beta[i] * Prices[i][j$var69])));
										for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
											if((j$var69 == j$var97)) {
												if(!guard$sample21categorical102[((i - 0) / 1)]) {
													// The body will execute, so should not be executed again
													guard$sample21categorical102[((i - 0) / 1)] = true;
													
													// Processing sample task 103 of consumer random variable null.
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
																		double[] cv$temp$4$prob;
																		{
																			cv$temp$4$prob = prob[((i - 0) / 1)];
																		}
																		int cv$temp$5$$var1126;
																		{
																			// Constructing a random variable input for use later.
																			int $var1126 = noProducts;
																			cv$temp$5$$var1126 = $var1126;
																		}
																		
																		// Record the probability of sample task 103 generating output with current configuration.
																		if(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$5$$var1126))?Math.log(cv$temp$4$prob[choices[i]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$5$$var1126))?Math.log(cv$temp$4$prob[choices[i]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$5$$var1126))?Math.log(cv$temp$4$prob[choices[i]]):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$5$$var1126))?Math.log(cv$temp$4$prob[choices[i]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$5$$var1126))?Math.log(cv$temp$4$prob[choices[i]]):Double.NEGATIVE_INFINITY)));
																		}
																		
																		// Recorded the probability of reaching sample task 103 with the current configuration.
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
				double var21 = cv$originalValue;
				
				// Guards to ensure that ut is only updated when there is a valid path.
				{
					{
						ut[var20] = var21;
					}
				}
				
				// Guards to ensure that exped is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 21 and consumer double[] 77.
				{
					for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
						if((var20 == j$var69)) {
							for(int i = 0; i < noObs; i += 1)
								exped[((i - 0) / 1)][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
						}
					}
				}
				
				// Guards to ensure that prob is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 21 and consumer double[] 100.
				{
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					boolean[][] guard$sample21put101 = guard$sample21put101$global;
					for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
						if((var20 == j$var69)) {
							for(int i = 0; i < noObs; i += 1) {
								if(((0 <= j$var69) && (j$var69 < noProducts))) {
									{
										for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
											// Set the flags to false
											guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
									}
								}
							}
						}
					}
					for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
						if((var20 == j$var69)) {
							for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
								if((j$var69 == j$var97)) {
									for(int i = 0; i < noObs; i += 1)
										// Set the flags to false
										guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
								}
							}
						}
					}
					for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
						if((var20 == j$var69)) {
							for(int i = 0; i < noObs; i += 1) {
								if(((0 <= j$var69) && (j$var69 < noProducts))) {
									{
										for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
											if(!guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
												{
													// Reduction of array exped
													// 
													// A generated name to prevent name collisions if the reduction is implemented more
													// than once in inference and probability code. Initialize the variable to the unit
													// value
													double reduceVar$sum$18 = 0.0;
													
													// For each index in the array to be reduced
													for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
														// Set the left hand term of the reduction function to the return variable value.
														double k = reduceVar$sum$18;
														
														// Set the right hand term to a value from the array exped
														double l = exped[((i - 0) / 1)][cv$reduction82Index];
														
														// Execute the reduction function, saving the result into the return value.
														// 
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$sum$18 = (k + l);
													}
													prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$18);
												}
											}
										}
									}
								}
							}
						}
					}
					for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
						if((var20 == j$var69)) {
							for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
								if((j$var69 == j$var97)) {
									for(int i = 0; i < noObs; i += 1) {
										if(!guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
											// The body will execute, so should not be executed again
											guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
											{
												// Reduction of array exped
												// 
												// A generated name to prevent name collisions if the reduction is implemented more
												// than once in inference and probability code. Initialize the variable to the unit
												// value
												double reduceVar$sum$19 = 0.0;
												
												// For each index in the array to be reduced
												for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
													// Set the left hand term of the reduction function to the return variable value.
													double k = reduceVar$sum$19;
													
													// Set the right hand term to a value from the array exped
													double l = exped[((i - 0) / 1)][cv$reduction82Index];
													
													// Execute the reduction function, saving the result into the return value.
													// 
													// Copy the result of the reduction into the variable returned by the reduction.
													reduceVar$sum$19 = (k + l);
												}
												prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$19);
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
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 28 drawn from Gaussian 27. Inference was performed using a Gaussian
	// to Gaussian conjugate prior.
	private final void sample28() {
		if(true) {
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
				// Processing random variable 35.
				{
					{
						// Processing sample task 47 of consumer random variable null.
						{
							for(int var46 = 0; var46 < noObs; var46 += 1) {
								// State for tracking the changes that happen to the sampled value between it being
								// consumed and it being produced.
								double cv$denominator = 1.0;
								double cv$numerator = 0.0;
								
								// Record the value of a sample generated by a consuming sample 47 of random variable
								// var35.
								// 
								// Add the denominator squared to the sample denominator
								cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
								
								// Add the weighting of the sample to the sum.
								cv$sum = (cv$sum + (cv$denominator * (beta[var46] - cv$numerator)));
								
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
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 34 drawn from InverseGamma 33. Inference was performed using a Inverse
	// Gamma to Gaussian conjugate prior.
	private final void sample34() {
		if(true) {
			// Variable to track the sum of the difference between the samples and the random
			// variables mean squared.
			double cv$sum = 0.0;
			
			// Variable to record the number of samples from consuming random variables.
			int cv$count = 0;
			{
				// Processing random variable 35.
				{
					{
						// Processing sample task 47 of consumer random variable null.
						{
							for(int var46 = 0; var46 < noObs; var46 += 1) {
								// The mean parameter for Gaussian var35.
								double cv$var35$mu = b;
								
								// Consume sample task 47 from random variable var35.
								// 
								// The difference between the mean parameter and the value sampled from the Gaussian.
								double cv$var35$diff = (cv$var35$mu - beta[var46]);
								
								// Include this sample by adding the square of the difference to the sum.
								cv$sum = (cv$sum + (cv$var35$diff * cv$var35$diff));
								
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
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 47 drawn from Gaussian 35. Inference was performed using Metropolis-Hastings.
	private final void sample47(int var46, int threadID$cv$var46, Rng RNG$) {
		if(true) {
			// Calculate the number of states to evaluate.
			int cv$numNumStates = 0;
			{
				// Metropolis-Hastings
				cv$numNumStates = Math.max(cv$numNumStates, 2);
			}
			
			// The original value of the sample
			double cv$originalValue = beta[var46];
			
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
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
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
						double var47 = cv$proposedValue;
						
						// Guards to ensure that beta is only updated when there is a valid path.
						{
							{
								beta[var46] = cv$currentValue;
							}
						}
						
						// Guards to ensure that exped is only updated when there is a valid path.
						// 
						// Looking for a path between Sample 47 and consumer double[] 77.
						{
							for(int i = 0; i < noObs; i += 1) {
								if((var46 == i)) {
									for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
										exped[((i - 0) / 1)][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
								}
							}
						}
						
						// Guards to ensure that prob is only updated when there is a valid path.
						// 
						// Looking for a path between Sample 47 and consumer double[] 100.
						{
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							boolean[][] guard$sample47put101 = guard$sample47put101$global[threadID$cv$var46];
							for(int i = 0; i < noObs; i += 1) {
								if((var46 == i)) {
									for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
										if(((0 <= j$var69) && (j$var69 < noProducts))) {
											{
												for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
													// Set the flags to false
													guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
											}
										}
									}
								}
							}
							for(int i = 0; i < noObs; i += 1) {
								if((var46 == i)) {
									for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
										for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
											if((j$var69 == j$var97))
												// Set the flags to false
												guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
										}
									}
								}
							}
							for(int i = 0; i < noObs; i += 1) {
								if((var46 == i)) {
									for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
										if(((0 <= j$var69) && (j$var69 < noProducts))) {
											{
												for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
													if(!guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
														{
															// Reduction of array exped
															// 
															// A generated name to prevent name collisions if the reduction is implemented more
															// than once in inference and probability code. Initialize the variable to the unit
															// value
															double reduceVar$sum$20 = 0.0;
															
															// For each index in the array to be reduced
															for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
																// Set the left hand term of the reduction function to the return variable value.
																double k = reduceVar$sum$20;
																
																// Set the right hand term to a value from the array exped
																double l = exped[((i - 0) / 1)][cv$reduction82Index];
																
																// Execute the reduction function, saving the result into the return value.
																// 
																// Copy the result of the reduction into the variable returned by the reduction.
																reduceVar$sum$20 = (k + l);
															}
															prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$20);
														}
													}
												}
											}
										}
									}
								}
							}
							for(int i = 0; i < noObs; i += 1) {
								if((var46 == i)) {
									for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
										for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
											if((j$var69 == j$var97)) {
												if(!guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
													// The body will execute, so should not be executed again
													guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
													{
														// Reduction of array exped
														// 
														// A generated name to prevent name collisions if the reduction is implemented more
														// than once in inference and probability code. Initialize the variable to the unit
														// value
														double reduceVar$sum$21 = 0.0;
														
														// For each index in the array to be reduced
														for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
															// Set the left hand term of the reduction function to the return variable value.
															double k = reduceVar$sum$21;
															
															// Set the right hand term to a value from the array exped
															double l = exped[((i - 0) / 1)][cv$reduction82Index];
															
															// Execute the reduction function, saving the result into the return value.
															// 
															// Copy the result of the reduction into the variable returned by the reduction.
															reduceVar$sum$21 = (k + l);
														}
														prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$21);
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
					
					// Processing random variable 101.
					{
						// Looking for a path between Sample 47 and consumer Categorical 101.
						{
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							boolean[] guard$sample47categorical102 = guard$sample47categorical102$global[threadID$cv$var46];
							for(int i = 0; i < noObs; i += 1) {
								if((var46 == i)) {
									for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
										if(((0 <= j$var69) && (j$var69 < noProducts))) {
											{
												// Set the flags to false
												guard$sample47categorical102[((i - 0) / 1)] = false;
											}
										}
									}
								}
							}
							for(int i = 0; i < noObs; i += 1) {
								if((var46 == i)) {
									for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
										for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
											if((j$var69 == j$var97))
												// Set the flags to false
												guard$sample47categorical102[((i - 0) / 1)] = false;
										}
									}
								}
							}
							double traceTempVariable$var71$9_1 = cv$currentValue;
							for(int i = 0; i < noObs; i += 1) {
								if((var46 == i)) {
									for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
										double traceTempVariable$k$9_4 = Math.exp((ut[j$var69] - (traceTempVariable$var71$9_1 * Prices[i][j$var69])));
										if(((0 <= j$var69) && (j$var69 < noProducts))) {
											{
												if((0 < noProducts)) {
													// Reduction of array exped
													// 
													// A generated name to prevent name collisions if the reduction is implemented more
													// than once in inference and probability code. Initialize the variable to the unit
													// value
													double reduceVar$sum$22 = 0.0;
													
													// Reduce for every value except a masked value which will be skipped.
													for(int cv$reduction1894Index = 0; cv$reduction1894Index < j$var69; cv$reduction1894Index += 1) {
														// Set the left hand term of the reduction function to the return variable value.
														double k = reduceVar$sum$22;
														
														// Set the right hand term to a value from the array exped
														double l = exped[((i - 0) / 1)][cv$reduction1894Index];
														
														// Execute the reduction function, saving the result into the return value.
														// 
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$sum$22 = (k + l);
													}
													for(int cv$reduction1894Index = (j$var69 + 1); cv$reduction1894Index < noProducts; cv$reduction1894Index += 1) {
														// Set the left hand term of the reduction function to the return variable value.
														double k = reduceVar$sum$22;
														
														// Set the right hand term to a value from the array exped
														double l = exped[((i - 0) / 1)][cv$reduction1894Index];
														
														// Execute the reduction function, saving the result into the return value.
														// 
														// Execute the reduction function, saving the result into the return value.
														// 
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$sum$22 = (k + l);
													}
													double cv$reduced82 = reduceVar$sum$22;
													
													// Copy the result of the reduction into the variable returned by the reduction.
													reduceVar$sum$22 = (traceTempVariable$k$9_4 + cv$reduced82);
													double traceTempVariable$sum$9_5 = reduceVar$sum$22;
													if(!guard$sample47categorical102[((i - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample47categorical102[((i - 0) / 1)] = true;
														
														// Processing sample task 103 of consumer random variable null.
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
																			int cv$temp$3$$var1392;
																			{
																				// Constructing a random variable input for use later.
																				int $var1392 = noProducts;
																				cv$temp$3$$var1392 = $var1392;
																			}
																			
																			// Record the probability of sample task 103 generating output with current configuration.
																			if(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$$var1392))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$$var1392))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$$var1392))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$$var1392))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$3$$var1392))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY)));
																			}
																			
																			// Recorded the probability of reaching sample task 103 with the current configuration.
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
							double traceTempVariable$var71$10_1 = cv$currentValue;
							for(int i = 0; i < noObs; i += 1) {
								if((var46 == i)) {
									for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
										double traceTempVariable$var98$10_4 = Math.exp((ut[j$var69] - (traceTempVariable$var71$10_1 * Prices[i][j$var69])));
										for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
											if((j$var69 == j$var97)) {
												if(!guard$sample47categorical102[((i - 0) / 1)]) {
													// The body will execute, so should not be executed again
													guard$sample47categorical102[((i - 0) / 1)] = true;
													
													// Processing sample task 103 of consumer random variable null.
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
																		double[] cv$temp$4$prob;
																		{
																			cv$temp$4$prob = prob[((i - 0) / 1)];
																		}
																		int cv$temp$5$$var1395;
																		{
																			// Constructing a random variable input for use later.
																			int $var1395 = noProducts;
																			cv$temp$5$$var1395 = $var1395;
																		}
																		
																		// Record the probability of sample task 103 generating output with current configuration.
																		if(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$5$$var1395))?Math.log(cv$temp$4$prob[choices[i]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$5$$var1395))?Math.log(cv$temp$4$prob[choices[i]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$5$$var1395))?Math.log(cv$temp$4$prob[choices[i]]):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$5$$var1395))?Math.log(cv$temp$4$prob[choices[i]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= choices[i]) && (choices[i] < cv$temp$5$$var1395))?Math.log(cv$temp$4$prob[choices[i]]):Double.NEGATIVE_INFINITY)));
																		}
																		
																		// Recorded the probability of reaching sample task 103 with the current configuration.
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
				double var47 = cv$originalValue;
				
				// Guards to ensure that beta is only updated when there is a valid path.
				{
					{
						beta[var46] = var47;
					}
				}
				
				// Guards to ensure that exped is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 47 and consumer double[] 77.
				{
					for(int i = 0; i < noObs; i += 1) {
						if((var46 == i)) {
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
								exped[((i - 0) / 1)][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
						}
					}
				}
				
				// Guards to ensure that prob is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 47 and consumer double[] 100.
				{
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					boolean[][] guard$sample47put101 = guard$sample47put101$global[threadID$cv$var46];
					for(int i = 0; i < noObs; i += 1) {
						if((var46 == i)) {
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								if(((0 <= j$var69) && (j$var69 < noProducts))) {
									{
										for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
											// Set the flags to false
											guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
									}
								}
							}
						}
					}
					for(int i = 0; i < noObs; i += 1) {
						if((var46 == i)) {
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
									if((j$var69 == j$var97))
										// Set the flags to false
										guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
								}
							}
						}
					}
					for(int i = 0; i < noObs; i += 1) {
						if((var46 == i)) {
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								if(((0 <= j$var69) && (j$var69 < noProducts))) {
									{
										for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
											if(!guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
												{
													// Reduction of array exped
													// 
													// A generated name to prevent name collisions if the reduction is implemented more
													// than once in inference and probability code. Initialize the variable to the unit
													// value
													double reduceVar$sum$23 = 0.0;
													
													// For each index in the array to be reduced
													for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
														// Set the left hand term of the reduction function to the return variable value.
														double k = reduceVar$sum$23;
														
														// Set the right hand term to a value from the array exped
														double l = exped[((i - 0) / 1)][cv$reduction82Index];
														
														// Execute the reduction function, saving the result into the return value.
														// 
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$sum$23 = (k + l);
													}
													prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$23);
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i = 0; i < noObs; i += 1) {
						if((var46 == i)) {
							for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
								for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
									if((j$var69 == j$var97)) {
										if(!guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
											// The body will execute, so should not be executed again
											guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
											{
												// Reduction of array exped
												// 
												// A generated name to prevent name collisions if the reduction is implemented more
												// than once in inference and probability code. Initialize the variable to the unit
												// value
												double reduceVar$sum$24 = 0.0;
												
												// For each index in the array to be reduced
												for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
													// Set the left hand term of the reduction function to the return variable value.
													double k = reduceVar$sum$24;
													
													// Set the right hand term to a value from the array exped
													double l = exped[((i - 0) / 1)][cv$reduction82Index];
													
													// Execute the reduction function, saving the result into the return value.
													// 
													// Copy the result of the reduction into the variable returned by the reduction.
													reduceVar$sum$24 = (k + l);
												}
												prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$24);
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
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for guard$sample21put101$global
		{
			// Calculate the largest index of i that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_i = 0;
			
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var97 = 0;
			for(int i = 0; i < noObs; i += 1)
				cv$max_j$var97 = Math.max(cv$max_j$var97, ((noProducts - 0) / 1));
			cv$max_i = Math.max(cv$max_i, ((noObs - 0) / 1));
			
			// Allocation of guard$sample21put101$global for single threaded execution
			guard$sample21put101$global = new boolean[cv$max_i][cv$max_j$var97];
		}
		
		// Constructor for guard$sample21categorical102$global
		{
			// Calculate the largest index of i that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_i = 0;
			cv$max_i = Math.max(cv$max_i, ((noObs - 0) / 1));
			
			// Allocation of guard$sample21categorical102$global for single threaded execution
			guard$sample21categorical102$global = new boolean[cv$max_i];
		}
		
		// Constructor for guard$sample47put101$global
		{
			// Calculate the largest index of i that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_i = 0;
			
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var97 = 0;
			for(int i = 0; i < noObs; i += 1)
				cv$max_j$var97 = Math.max(cv$max_j$var97, ((noProducts - 0) / 1));
			cv$max_i = Math.max(cv$max_i, ((noObs - 0) / 1));
			
			// Allocation of guard$sample47put101$global for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				guard$sample47put101$global = new boolean[cv$threadCount][][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					guard$sample47put101$global[cv$index] = new boolean[cv$max_i][cv$max_j$var97];
			}
		}
		
		// Constructor for guard$sample47categorical102$global
		{
			// Calculate the largest index of i that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_i = 0;
			cv$max_i = Math.max(cv$max_i, ((noObs - 0) / 1));
			
			// Allocation of guard$sample47categorical102$global for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				guard$sample47categorical102$global = new boolean[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					guard$sample47categorical102$global[cv$index] = new boolean[cv$max_i];
			}
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If ut has not been set already allocate space.
		if(!fixedFlag$sample21) {
			// Constructor for ut
			{
				ut = new double[noProducts];
			}
		}
		
		// If beta has not been set already allocate space.
		if(!fixedFlag$sample47) {
			// Constructor for beta
			{
				beta = new double[noObs];
			}
		}
		
		// Constructor for choices
		{
			choices = new int[noObs];
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
		
		// Constructor for logProbability$sample21
		{
			logProbability$sample21 = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample47
		{
			logProbability$sample47 = new double[((((noObs - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$var101
		{
			logProbability$var101 = new double[((((noObs - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample103
		{
			logProbability$sample103 = new double[((((noObs - 1) - 0) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1) {
						if(!fixedFlag$sample21)
							ut[var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample28)
			b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample34)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
						if(!fixedFlag$sample47)
							beta[var46] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
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
						int threadID$i = threadID$index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1) {
										if(!(fixedFlag$sample21 && fixedFlag$sample47))
											exped[((i - 0) / 1)][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
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
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$sum$25;
							
							// Set the right hand term to a value from the array exped
							double l = exped[((i - 0) / 1)][cv$reduction82Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!(fixedFlag$sample21 && fixedFlag$sample47))
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$sum$25 = (k + l);
						}
						
						// Alternative name for reduceVar$sum$25 to make it effectively final.
						double reduceVar$sum$25$1 = reduceVar$sum$25;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1) {
										if(!(fixedFlag$sample21 && fixedFlag$sample47))
											prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$25$1);
									}
							}
						);
						choices[i] = DistributionSampling.sampleCategorical(RNG$1, prob[((i - 0) / 1)], noProducts);
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
			(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1) {
						if(!fixedFlag$sample21)
							ut[var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample28)
			b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample34)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
						if(!fixedFlag$sample47)
							beta[var46] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
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
						int threadID$i = threadID$index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1) {
										if(!(fixedFlag$sample21 && fixedFlag$sample47))
											exped[((i - 0) / 1)][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
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
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$sum$27;
							
							// Set the right hand term to a value from the array exped
							double l = exped[((i - 0) / 1)][cv$reduction82Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!(fixedFlag$sample21 && fixedFlag$sample47))
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$sum$27 = (k + l);
						}
						
						// Alternative name for reduceVar$sum$27 to make it effectively final.
						double reduceVar$sum$27$1 = reduceVar$sum$27;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1) {
										if(!(fixedFlag$sample21 && fixedFlag$sample47))
											prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$27$1);
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
			(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1) {
						if(!fixedFlag$sample21)
							ut[var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample28)
			b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample34)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
						if(!fixedFlag$sample47)
							beta[var46] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
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
						int threadID$i = threadID$index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1) {
										if(!(fixedFlag$sample21 && fixedFlag$sample47))
											exped[((i - 0) / 1)][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
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
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$sum$26;
							
							// Set the right hand term to a value from the array exped
							double l = exped[((i - 0) / 1)][cv$reduction82Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!(fixedFlag$sample21 && fixedFlag$sample47))
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$sum$26 = (k + l);
						}
						
						// Alternative name for reduceVar$sum$26 to make it effectively final.
						double reduceVar$sum$26$1 = reduceVar$sum$26;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1) {
										if(!(fixedFlag$sample21 && fixedFlag$sample47))
											prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$26$1);
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
			for(int var20 = 0; var20 < noProducts; var20 += 1) {
				if(!fixedFlag$sample21)
					sample21(var20);
			}
			if(!fixedFlag$sample28)
				sample28();
			if(!fixedFlag$sample34)
				sample34();
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
							if(!fixedFlag$sample47)
								sample47(var46, threadID$var46, RNG$1);
						}
				}
			);
		}
		// Infer the samples in reverse chronological order.
		else {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
							if(!fixedFlag$sample47)
								sample47(var46, threadID$var46, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample34)
				sample34();
			if(!fixedFlag$sample28)
				sample28();
			for(int var20 = (noProducts - ((((noProducts - 1) - 0) % 1) + 1)); var20 >= ((0 - 1) + 1); var20 -= 1) {
				if(!fixedFlag$sample21)
					sample21(var20);
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
		logProbability$var9 = 0.0;
		logProbability$ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$prob = 0.0;
		if(!fixedProbFlag$sample21) {
			for(int var20 = 0; var20 < noProducts; var20 += 1)
				logProbability$sample21[((var20 - 0) / 1)] = 0.0;
		}
		logProbability$var27 = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$b = 0.0;
		logProbability$var33 = 0.0;
		if(!fixedProbFlag$sample34)
			logProbability$sigma = 0.0;
		logProbability$var35 = 0.0;
		logProbability$beta = 0.0;
		if(!fixedProbFlag$sample47) {
			for(int var46 = 0; var46 < noObs; var46 += 1)
				logProbability$sample47[((var46 - 0) / 1)] = 0.0;
		}
		for(int i = 0; i < noObs; i += 1)
			logProbability$var101[((i - 0) / 1)] = 0.0;
		logProbability$choices = 0.0;
		if(!fixedProbFlag$sample103) {
			for(int i = 0; i < noObs; i += 1)
				logProbability$sample103[((i - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample21)
			logProbabilityValue$sample21();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(fixedFlag$sample47)
			logProbabilityValue$sample47();
		logProbabilityValue$sample103();
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
		logProbabilityValue$sample21();
		logProbabilityValue$sample28();
		logProbabilityValue$sample34();
		logProbabilityValue$sample47();
		logProbabilityValue$sample103();
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
		logProbabilityValue$sample21();
		logProbabilityValue$sample28();
		logProbabilityValue$sample34();
		logProbabilityValue$sample47();
		logProbabilityValue$sample103();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1) {
						if(!fixedFlag$sample21)
							ut[var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample28)
			b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample34)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
						if(!fixedFlag$sample47)
							beta[var46] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
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
						int threadID$i = threadID$index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1) {
										if(!(fixedFlag$sample21 && fixedFlag$sample47))
											exped[((i - 0) / 1)][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
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
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$sum$28;
							
							// Set the right hand term to a value from the array exped
							double l = exped[((i - 0) / 1)][cv$reduction82Index];
							
							// Execute the reduction function, saving the result into the return value.
							if(!(fixedFlag$sample21 && fixedFlag$sample47))
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$sum$28 = (k + l);
						}
						
						// Alternative name for reduceVar$sum$28 to make it effectively final.
						double reduceVar$sum$28$1 = reduceVar$sum$28;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1) {
										if(!(fixedFlag$sample21 && fixedFlag$sample47))
											prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$28$1);
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
	public final void propagateObservedValues() {
		// Deep copy between arrays
		int[] cv$source1 = ObsChoices;
		int[] cv$target1 = choices;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1) {
										if((fixedFlag$sample21 && fixedFlag$sample47))
											exped[((i - 0) / 1)][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
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
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							double k = reduceVar$sum$29;
							
							// Set the right hand term to a value from the array exped
							double l = exped[((i - 0) / 1)][cv$reduction82Index];
							
							// Execute the reduction function, saving the result into the return value.
							if((fixedFlag$sample21 && fixedFlag$sample47))
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$sum$29 = (k + l);
						}
						
						// Alternative name for reduceVar$sum$29 to make it effectively final.
						double reduceVar$sum$29$1 = reduceVar$sum$29;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1) {
										if((fixedFlag$sample21 && fixedFlag$sample47))
											prob[((i - 0) / 1)][j$var97] = (exped[((i - 0) / 1)][j$var97] / reduceVar$sum$29$1);
									}
							}
						);
					}
			}
		);
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
		     + "\n"
		     + "model DiscreteChoiceRandCoeff(int noProducts, int noObs, int[] ObsChoices, int[][] Prices) {\n"
		     + "    // we just need an uninformative prior for utility intercepts\n"
		     + "\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = gaussian(0, 10).sample(noProducts);\n"
		     + "    //can we set the first element to 0? like ut[0] <~ 0\n"
		     + "\n"
		     + "    //priors for distribution of beta\n"
		     + "    double b = gaussian(0,10).sample();\n"
		     + "    double sigma =  inverseGamma(2,2).sample();\n"
		     + "\n"
		     + "    double[] beta = gaussian(b, sigma).sample(noObs);\n"
		     + "\n"
		     + "    int[] choices = new int[noObs];\n"
		     + "\n"
		     + "    for (int i:[0..noObs)){\n"
		     + "        // calculate choice probabilities for consumer i\n"
		     + "\n"
		     + "        double[] exped = new double[noProducts];\n"
		     + "        for(int j : [0..noProducts)) {\n"
		     + "            exped[j] = exp(ut[j]- beta[i]*Prices[i][j]);\n"
		     + "            }\n"
		     + "        double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n"
		     + "        public double[] prob = new double[noProducts];\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            prob[j] = exped[j] / sum;\n"
		     + "        }\n"
		     + "        // emit choices of consumer i\n"
		     + "        choices[i] = categorical(prob).sample();\n"
		     + "                                }\n"
		     + "\n"
		     + "    // assert that generated choices match observed choices\n"
		     + "    choices.observe(ObsChoices);\n"
		     + "}";
	}
}