package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

<<<<<<< Upstream, based on POW
final class Flip1CoinMK14$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK14$CoreInterface {
	private double b;
	private double bias;
	private double[] c;
	private boolean constrainedFlag$sample8 = true;
	private boolean fixedFlag$sample8 = false;
	private boolean fixedProbFlag$sample37 = false;
	private boolean fixedProbFlag$sample8 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private boolean guard1;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$var35;
	private int samples;
	private boolean system$gibbsForward = true;
=======
final class Flip1CoinMK14$MultiThreadCPU extends CoreModelMultiThreadCPU implements Flip1CoinMK14$CoreInterface {
double b;
	double bias;
	double[] c;
	boolean constrainedFlag$sample8 = true;
	boolean fixedFlag$sample8 = false;
	boolean fixedProbFlag$sample37 = false;
	boolean fixedProbFlag$sample8 = false;
	boolean[] flips;
	boolean[] flipsMeasured;
	boolean guard1;
	int length$flipsMeasured;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$b;
	double logProbability$bernoulli;
	double logProbability$bias;
	double logProbability$flips;
	double logProbability$sample8;
	double logProbability$var35;
	int samples;
	boolean system$gibbsForward = true;
>>>>>>> daee89e Adding in a class to hold just the state. This will be worked on further as the code generation progresses. Commit before adding inner classes to the outer classes. Updating output class structure checkpoint Checkpoint in the restructuring of the output classes to increase the shared code. Finished restructuring the classes, time to start using inner classes. Updates to tree structure Changing the structure of get field so that it can be used to get other types of field, read for getting data out of the scratch and model data classes. Removing unused imports Adding nodes to allow fields in an object ot be set. Moving rng package so that we can add other internal only variable types. Updates to the handling of transformations. Moving from sets to lists of generics Updating the structure of inner class. Changing the passing of fields to sub classes. Updating class structure

	public Flip1CoinMK14$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$b() {
		return b;
	}

	@Override
	public final void set$b(double cv$value, boolean allocated$) {
		b = cv$value;
		fixedProbFlag$sample8 = false;
		fixedProbFlag$sample37 = false;
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final boolean get$fixedFlag$sample8() {
		return fixedFlag$sample8;
	}

	@Override
	public final void set$fixedFlag$sample8(boolean cv$value, boolean allocated$) {
		fixedFlag$sample8 = cv$value;
		constrainedFlag$sample8 = (fixedFlag$sample8 || constrainedFlag$sample8);
		fixedProbFlag$sample8 = (fixedFlag$sample8 && fixedProbFlag$sample8);
		fixedProbFlag$sample37 = (fixedFlag$sample8 && fixedProbFlag$sample37);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final boolean[] get$flipsMeasured() {
		return flipsMeasured;
	}

	@Override
	public final void set$flipsMeasured(boolean[] cv$value, boolean allocated$) {
		flipsMeasured = cv$value;
	}

	@Override
	public final boolean get$guard1() {
		return guard1;
	}

	@Override
	public final void set$guard1(boolean cv$value, boolean allocated$) {
		guard1 = cv$value;
	}

	@Override
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	@Override
	public final void set$length$flipsMeasured(int cv$value, boolean allocated$) {
		length$flipsMeasured = cv$value;
	}

	@Override
	public final double get$logProbability$$evidence() {
		return logProbability$$evidence;
	}

	@Override
	public final double getCurrentLogProbability() {
		return logProbability$$model;
	}

	@Override
	public final double get$logProbability$b() {
		return logProbability$b;
	}

	@Override
	public final double get$logProbability$bernoulli() {
		return logProbability$bernoulli;
	}

	@Override
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$flips() {
		return logProbability$flips;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	private final void drawValueSample8() {
		b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		{
			{
				if(!guard1) {
					{
						c[0] = (b / 2);
					}
				}
			}
		}
		{
			{
				if(guard1) {
					{
						bias = b;
					}
				}
			}
			{
				if(!guard1) {
					if((0 == 0)) {
						if(!guard1) {
							{
								bias = c[0];
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample8() {
		if(true) {
			constrainedFlag$sample8 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = b;
			double cv$originalProbability = 0.0;
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			if((cv$var < 0.01))
				cv$var = 0.01;
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((constrainedFlag$sample8 || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						b = cv$proposedValue;
						{
							{
								if(!guard1) {
									{
										c[0] = (cv$currentValue / 2);
									}
								}
							}
						}
						{
							{
								if(guard1) {
									{
										bias = cv$currentValue;
									}
								}
							}
							{
								if(!guard1) {
									if((0 == 0)) {
										if(!guard1) {
											{
												bias = c[0];
											}
										}
									}
								}
							}
						}
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$currentValue, 1.0, 1.0));
						{
							{
								{
									if(guard1) {
										double traceTempVariable$bias$4_1 = cv$currentValue;
										double traceTempVariable$b$4_2 = cv$currentValue;
										{
											{
												for(int var34 = 0; var34 < samples; var34 += 1) {
													boolean cv$sampleConstrained = true;
													if(cv$sampleConstrained) {
														constrainedFlag$sample8 = true;
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															{
																{
																	{
																		{
																			if(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$4_1) && (traceTempVariable$bias$4_1 <= 1.0))?Math.log((flips[var34]?traceTempVariable$bias$4_1:(1.0 - traceTempVariable$bias$4_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$4_1) && (traceTempVariable$bias$4_1 <= 1.0))?Math.log((flips[var34]?traceTempVariable$bias$4_1:(1.0 - traceTempVariable$bias$4_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$4_1) && (traceTempVariable$bias$4_1 <= 1.0))?Math.log((flips[var34]?traceTempVariable$bias$4_1:(1.0 - traceTempVariable$bias$4_1))):Double.NEGATIVE_INFINITY));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$4_1) && (traceTempVariable$bias$4_1 <= 1.0))?Math.log((flips[var34]?traceTempVariable$bias$4_1:(1.0 - traceTempVariable$bias$4_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$4_1) && (traceTempVariable$bias$4_1 <= 1.0))?Math.log((flips[var34]?traceTempVariable$bias$4_1:(1.0 - traceTempVariable$bias$4_1))):Double.NEGATIVE_INFINITY)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
														cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
														if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
														else {
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
								{
									if(!guard1) {
										double traceTempVariable$b$5_1 = cv$currentValue;
										double traceTempVariable$var21$5_2 = (traceTempVariable$b$5_1 / 2);
										if((0 == 0)) {
											if(!guard1) {
												double traceTempVariable$bias$5_3 = traceTempVariable$var21$5_2;
												{
													{
														for(int var34 = 0; var34 < samples; var34 += 1) {
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																constrainedFlag$sample8 = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$5_3) && (traceTempVariable$bias$5_3 <= 1.0))?Math.log((flips[var34]?traceTempVariable$bias$5_3:(1.0 - traceTempVariable$bias$5_3))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$5_3) && (traceTempVariable$bias$5_3 <= 1.0))?Math.log((flips[var34]?traceTempVariable$bias$5_3:(1.0 - traceTempVariable$bias$5_3))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$5_3) && (traceTempVariable$bias$5_3 <= 1.0))?Math.log((flips[var34]?traceTempVariable$bias$5_3:(1.0 - traceTempVariable$bias$5_3))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$5_3) && (traceTempVariable$bias$5_3 <= 1.0))?Math.log((flips[var34]?traceTempVariable$bias$5_3:(1.0 - traceTempVariable$bias$5_3))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$5_3) && (traceTempVariable$bias$5_3 <= 1.0))?Math.log((flips[var34]?traceTempVariable$bias$5_3:(1.0 - traceTempVariable$bias$5_3))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
																cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																else {
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
						}
						if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
						else {
							if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
								cv$stateProbabilityValue = cv$accumulatedProbabilities;
							else
								cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
						}
					}
					if((cv$valuePos == 0))
						cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
					else
						cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
					double cv$ratio = (cv$proposedProbability - cv$originalProbability);
					if((cv$valuePos == 1)) {
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
							b = cv$originalValue;
							{
								{
									if(!guard1) {
										{
											c[0] = (b / 2);
										}
									}
								}
							}
							{
								{
									if(guard1) {
										{
											bias = b;
										}
									}
								}
								{
									if(!guard1) {
										if((0 == 0)) {
											if(!guard1) {
												{
													bias = c[0];
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
	}

	private final void logProbabilityValue$sample37() {
		if(!fixedProbFlag$sample37) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var34 = 0; var34 < samples; var34 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = flips[var34];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= bias) && (bias <= 1.0))?Math.log((cv$sampleValue?bias:(1.0 - bias))):Double.NEGATIVE_INFINITY));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var35 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample37 = fixedFlag$sample8;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var34 = 0; var34 < samples; var34 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var35;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bernoulli = cv$rvAccumulator;
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample8() {
		if(!fixedProbFlag$sample8) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = b;
					{
						{
							double var4 = 1.0;
							double var6 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var4, var6));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$b = cv$sampleProbability;
			boolean cv$guard$bias = false;
			{
				{
					if(guard1) {
						if(!cv$guard$bias) {
							cv$guard$bias = true;
							logProbability$bias = (logProbability$bias + cv$accumulator);
						}
					}
				}
				{
					if(!guard1) {
						if((0 == 0)) {
							if(!guard1) {
								if(!cv$guard$bias) {
									cv$guard$bias = true;
									logProbability$bias = (logProbability$bias + cv$accumulator);
								}
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample8)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample8 = fixedFlag$sample8;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$b;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			boolean cv$guard$bias = false;
			{
				{
					if(guard1) {
						if(!cv$guard$bias) {
							cv$guard$bias = true;
							logProbability$bias = (logProbability$bias + cv$accumulator);
						}
					}
				}
				{
					if(!guard1) {
						if((0 == 0)) {
							if(!guard1) {
								if(!cv$guard$bias) {
									cv$guard$bias = true;
									logProbability$bias = (logProbability$bias + cv$accumulator);
								}
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample8)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void allocate() {
		{
			if(!guard1)
				c = new double[1];
		}
		{
			flips = new boolean[length$flipsMeasured];
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample8)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(guard1) {
			if(!fixedFlag$sample8)
				bias = b;
		} else {
			if(!fixedFlag$sample8)
				c[0] = (b / 2);
			if(!fixedFlag$sample8)
				bias = c[0];
		}
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$var34, int forEnd$var34, int threadID$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var34 = forStart$var34; var34 < forEnd$var34; var34 += 1)
						flips[var34] = DistributionSampling.sampleBernoulli(RNG$1, bias);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample8)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(guard1) {
			if(!fixedFlag$sample8)
				bias = b;
		} else {
			c[0] = (b / 2);
			bias = c[0];
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample8)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(guard1) {
			if(!fixedFlag$sample8)
				bias = b;
		} else {
			c[0] = (b / 2);
			bias = c[0];
		}
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$var34, int forEnd$var34, int threadID$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var34 = forStart$var34; var34 < forEnd$var34; var34 += 1)
						flips[var34] = DistributionSampling.sampleBernoulli(RNG$1, bias);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample8)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(guard1) {
			if(!fixedFlag$sample8)
				bias = b;
		} else {
			if(!fixedFlag$sample8)
				c[0] = (b / 2);
			if(!fixedFlag$sample8)
				bias = c[0];
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample8)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(guard1) {
			if(!fixedFlag$sample8)
				bias = b;
		} else {
			c[0] = (b / 2);
			bias = c[0];
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample8)
				inferSample8();
		} else {
			if(!fixedFlag$sample8)
				inferSample8();
		}
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample8)
			drawValueSample8();
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample8)
			logProbability$b = Double.NaN;
		logProbability$bernoulli = Double.NaN;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample37)
			logProbability$var35 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		samples = length$flipsMeasured;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample8)
			logProbabilityValue$sample8();
		logProbabilityValue$sample37();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample8();
		logProbabilityValue$sample37();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample8();
		logProbabilityValue$sample37();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i = (samples - ((((samples - 1) - 0) % 1) + 1)); i >= ((0 - 1) + 1); i -= 1)
			flips[i] = flipsMeasured[i];
	}

	@Override
	public final void setIntermediates() {
		if(guard1) {
			if(fixedFlag$sample8)
				bias = b;
		} else {
			c[0] = (b / 2);
			bias = c[0];
		}
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
		     + "public model Flip1CoinMK14(boolean[] flipsMeasured, boolean guard1) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    double b = beta(1.0, 1).sample();\n"
		     + "    double bias;\n"
		     + "    if(guard1)\n"
		     + "      bias = b;\n"
		     + "    else {\n"
		     + "      double[] c = new double[1];\n"
		     + "      c[0] = b/2;\n"
		     + "      bias = c[0];\n"
		     + "    }\n"
		     + "        \n"
		     + "    Bernoulli bernoulli = bernoulli(bias);\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "\n"
		     + "    for(int i:[0..samples))\n"
		     + "        flips[i].observe(flipsMeasured[i]);\n"
		     + "}\n"
		     + "";
	}
}