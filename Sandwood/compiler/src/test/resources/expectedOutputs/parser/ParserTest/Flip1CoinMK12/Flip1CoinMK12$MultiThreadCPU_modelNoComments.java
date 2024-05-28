package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK12$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK12$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedFlag$sample39 = false;
	private boolean fixedFlag$sample56 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean fixedProbFlag$sample39 = false;
	private boolean fixedProbFlag$sample56 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private boolean guard1;
	private int guard2;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$sample20;
	private double logProbability$sample32;
	private double logProbability$sample39;
	private double logProbability$var15;
	private double logProbability$var16;
	private double logProbability$var25;
	private double logProbability$var28;
	private double logProbability$var32;
	private double logProbability$var35;
	private double logProbability$var50;
	private int samples;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;
	private double var16;
	private double var28;
	private double var35;

	public Flip1CoinMK12$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	@Override
	public final void set$fixedFlag$sample20(boolean cv$value) {
		fixedFlag$sample20 = cv$value;
		fixedProbFlag$sample20 = (fixedFlag$sample20 && fixedProbFlag$sample20);
		fixedProbFlag$sample56 = (fixedFlag$sample20 && fixedProbFlag$sample56);
	}

	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	@Override
	public final void set$fixedFlag$sample32(boolean cv$value) {
		fixedFlag$sample32 = cv$value;
		fixedProbFlag$sample32 = (fixedFlag$sample32 && fixedProbFlag$sample32);
		fixedProbFlag$sample56 = (fixedFlag$sample32 && fixedProbFlag$sample56);
	}

	@Override
	public final boolean get$fixedFlag$sample39() {
		return fixedFlag$sample39;
	}

	@Override
	public final void set$fixedFlag$sample39(boolean cv$value) {
		fixedFlag$sample39 = cv$value;
		fixedProbFlag$sample39 = (fixedFlag$sample39 && fixedProbFlag$sample39);
		fixedProbFlag$sample56 = (fixedFlag$sample39 && fixedProbFlag$sample56);
	}

	@Override
	public final boolean get$fixedFlag$sample56() {
		return fixedFlag$sample56;
	}

	@Override
	public final void set$fixedFlag$sample56(boolean cv$value) {
		fixedFlag$sample56 = cv$value;
		fixedProbFlag$sample56 = (fixedFlag$sample56 && fixedProbFlag$sample56);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample56 = false;
	}

	@Override
	public final boolean[] get$flipsMeasured() {
		return flipsMeasured;
	}

	@Override
	public final void set$flipsMeasured(boolean[] cv$value) {
		flipsMeasured = cv$value;
	}

	@Override
	public final boolean get$guard1() {
		return guard1;
	}

	@Override
	public final void set$guard1(boolean cv$value) {
		guard1 = cv$value;
	}

	@Override
	public final int get$guard2() {
		return guard2;
	}

	@Override
	public final void set$guard2(int cv$value) {
		guard2 = cv$value;
	}

	@Override
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	@Override
	public final void set$length$flipsMeasured(int cv$value) {
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

	private final void logProbabilityValue$sample20() {
		if(!fixedProbFlag$sample20) {
			double cv$accumulator = 0.0;
			if(guard1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					if(guard1) {
						double cv$sampleValue = var16;
						{
							{
								double var12 = 1.0;
								double var14 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var12, var14));
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
				logProbability$var15 = cv$sampleAccumulator;
				logProbability$sample20 = cv$sampleProbability;
			}
			boolean cv$guard$bias = false;
			logProbability$var16 = (logProbability$var16 + cv$accumulator);
			{
				if(guard1) {
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						logProbability$bias = (logProbability$bias + cv$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample20 = fixedFlag$sample20;
		} else {
			double cv$accumulator = 0.0;
			if(guard1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample20;
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var15 = cv$rvAccumulator;
			}
			boolean cv$guard$bias = false;
			logProbability$var16 = (logProbability$var16 + cv$accumulator);
			{
				if(guard1) {
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						logProbability$bias = (logProbability$bias + cv$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample32() {
		if(!fixedProbFlag$sample32) {
			double cv$accumulator = 0.0;
			if(!guard1) {
				if((guard2 <= 2)) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						if(!guard1) {
							if((guard2 <= 2)) {
								double cv$sampleValue = (var28 * 2);
								{
									{
										double var22 = 1.0;
										double var24 = 1.0;
										double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var22, var24));
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
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$var25 = cv$sampleAccumulator;
					logProbability$sample32 = cv$sampleProbability;
				}
			}
			boolean cv$guard$bias = false;
			logProbability$var28 = (logProbability$var28 + cv$accumulator);
			{
				if(!guard1) {
					if((guard2 <= 2)) {
						if(!guard1) {
							if(!cv$guard$bias) {
								cv$guard$bias = true;
								logProbability$bias = (logProbability$bias + cv$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample32 = fixedFlag$sample32;
		} else {
			double cv$accumulator = 0.0;
			if(!guard1) {
				if((guard2 <= 2)) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample32;
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var25 = cv$rvAccumulator;
				}
			}
			boolean cv$guard$bias = false;
			logProbability$var28 = (logProbability$var28 + cv$accumulator);
			{
				if(!guard1) {
					if((guard2 <= 2)) {
						if(!guard1) {
							if(!cv$guard$bias) {
								cv$guard$bias = true;
								logProbability$bias = (logProbability$bias + cv$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample39() {
		if(!fixedProbFlag$sample39) {
			double cv$accumulator = 0.0;
			if(!guard1) {
				if(!(guard2 <= 2)) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						if(!guard1) {
							if(!(guard2 <= 2)) {
								double cv$sampleValue = (var35 * 3);
								{
									{
										double var29 = 1.0;
										double var31 = 1.0;
										double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var29, var31));
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
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$var32 = cv$sampleAccumulator;
					logProbability$sample39 = cv$sampleProbability;
				}
			}
			boolean cv$guard$bias = false;
			logProbability$var35 = (logProbability$var35 + cv$accumulator);
			{
				if(!guard1) {
					if(!(guard2 <= 2)) {
						if(!guard1) {
							if(!cv$guard$bias) {
								cv$guard$bias = true;
								logProbability$bias = (logProbability$bias + cv$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample39 = fixedFlag$sample39;
		} else {
			double cv$accumulator = 0.0;
			if(!guard1) {
				if(!(guard2 <= 2)) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample39;
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var32 = cv$rvAccumulator;
				}
			}
			boolean cv$guard$bias = false;
			logProbability$var35 = (logProbability$var35 + cv$accumulator);
			{
				if(!guard1) {
					if(!(guard2 <= 2)) {
						if(!guard1) {
							if(!cv$guard$bias) {
								cv$guard$bias = true;
								logProbability$bias = (logProbability$bias + cv$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample56() {
		if(!fixedProbFlag$sample56) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var49 = 0; var49 < samples; var49 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[var49];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, bias));
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var50 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample56 = (((fixedFlag$sample56 && fixedFlag$sample20) && fixedFlag$sample32) && fixedFlag$sample39);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var50;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bernoulli = cv$rvAccumulator;
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample20() {
		int cv$sum = 0;
		int cv$count = 0;
		{
			{
				{
					if(guard1) {
						{
							for(int var49 = 0; var49 < samples; var49 += 1) {
								cv$count = (cv$count + 1);
								if(flips[var49])
									cv$sum = (cv$sum + 1);
							}
						}
					}
				}
			}
		}
		var16 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		{
			if(guard1) {
				{
					bias = var16;
					if(!guard1) {
						double var36;
						if((guard2 <= 2))
							var36 = var28;
						else
							var36 = var35;
						bias = var36;
					}
				}
			}
		}
	}

	private final void sample32() {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = (var28 * 2);
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var26 = cv$proposedValue;
					var28 = (cv$currentValue / 2);
					{
						if(!guard1) {
							if((guard2 <= 2)) {
								if(!guard1) {
									{
										if(guard1)
											bias = var16;
										double var36;
										var36 = var28;
										if(!(guard2 <= 2))
											var36 = var35;
										bias = var36;
									}
								}
							}
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var22;
				{
					cv$temp$0$var22 = 1.0;
				}
				double cv$temp$1$var24;
				{
					cv$temp$1$var24 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$currentValue, cv$temp$0$var22, cv$temp$1$var24));
				{
					{
						if(!guard1) {
							if((guard2 <= 2)) {
								double traceTempVariable$var36$2_1 = var28;
								if(!guard1) {
									double traceTempVariable$bias$2_2 = traceTempVariable$var36$2_1;
									{
										for(int var49 = 0; var49 < samples; var49 += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$2$bias;
															{
																cv$temp$2$bias = traceTempVariable$bias$2_2;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var49], cv$temp$2$bias)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var49], cv$temp$2$bias)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var49], cv$temp$2$bias));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var49], cv$temp$2$bias)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var49], cv$temp$2$bias)));
															}
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
		}
		double cv$ratio = (cv$proposedProbability - cv$originalProbability);
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
			double var26 = cv$originalValue;
			var28 = (var26 / 2);
			{
				if(!guard1) {
					if((guard2 <= 2)) {
						if(!guard1) {
							{
								if(guard1)
									bias = var16;
								double var36;
								var36 = var28;
								if(!(guard2 <= 2))
									var36 = var35;
								bias = var36;
							}
						}
					}
				}
			}
		}
	}

	private final void sample39() {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = (var35 * 3);
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var33 = cv$proposedValue;
					var35 = (cv$currentValue / 3);
					{
						if(!guard1) {
							if(!(guard2 <= 2)) {
								if(!guard1) {
									{
										if(guard1)
											bias = var16;
										double var36;
										if((guard2 <= 2))
											var36 = var28;
										var36 = var35;
										bias = var36;
									}
								}
							}
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var29;
				{
					cv$temp$0$var29 = 1.0;
				}
				double cv$temp$1$var31;
				{
					cv$temp$1$var31 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$currentValue, cv$temp$0$var29, cv$temp$1$var31));
				{
					{
						if(!guard1) {
							if(!(guard2 <= 2)) {
								double traceTempVariable$var36$2_1 = var35;
								if(!guard1) {
									double traceTempVariable$bias$2_2 = traceTempVariable$var36$2_1;
									{
										for(int var49 = 0; var49 < samples; var49 += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$2$bias;
															{
																cv$temp$2$bias = traceTempVariable$bias$2_2;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var49], cv$temp$2$bias)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var49], cv$temp$2$bias)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var49], cv$temp$2$bias));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var49], cv$temp$2$bias)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var49], cv$temp$2$bias)));
															}
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
		}
		double cv$ratio = (cv$proposedProbability - cv$originalProbability);
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
			double var33 = cv$originalValue;
			var35 = (var33 / 3);
			{
				if(!guard1) {
					if(!(guard2 <= 2)) {
						if(!guard1) {
							{
								if(guard1)
									bias = var16;
								double var36;
								if((guard2 <= 2))
									var36 = var28;
								var36 = var35;
								bias = var36;
							}
						}
					}
				}
			}
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!setFlag$flips) {
			{
				flips = new boolean[length$flipsMeasured];
			}
		}
	}

	@Override
	public final void forwardGeneration() {
		if(guard1) {
			if(!fixedFlag$sample20)
				var16 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!fixedFlag$sample20)
				bias = var16;
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample32)
					var28 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
				if(!fixedFlag$sample32)
					bias = var28;
			} else {
				if(!fixedFlag$sample39)
					var35 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
				if(!fixedFlag$sample39)
					bias = var35;
			}
		}
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1) {
						if(!fixedFlag$sample56)
							flips[var49] = DistributionSampling.sampleBernoulli(RNG$1, bias);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(guard1) {
			if(!fixedFlag$sample20)
				var16 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!fixedFlag$sample20)
				bias = var16;
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample32)
					var28 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
				if(!fixedFlag$sample32)
					bias = var28;
			} else {
				if(!fixedFlag$sample39)
					var35 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
				if(!fixedFlag$sample39)
					bias = var35;
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(guard1) {
			if(!fixedFlag$sample20)
				var16 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!fixedFlag$sample20)
				bias = var16;
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample32)
					var28 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
				if(!fixedFlag$sample32)
					bias = var28;
			} else {
				if(!fixedFlag$sample39)
					var35 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
				if(!fixedFlag$sample39)
					bias = var35;
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(guard1) {
				if(!fixedFlag$sample20)
					sample20();
			} else {
				if((guard2 <= 2)) {
					if(!fixedFlag$sample32)
						sample32();
				} else {
					if(!fixedFlag$sample39)
						sample39();
				}
			}
		} else {
			if(guard1) {
				if(!fixedFlag$sample20)
					sample20();
			} else {
				if((guard2 <= 2)) {
					if(!fixedFlag$sample32)
						sample32();
				} else {
					if(!fixedFlag$sample39)
						sample39();
				}
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		samples = length$flipsMeasured;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var15 = 0.0;
		logProbability$bias = 0.0;
		logProbability$var16 = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$sample20 = 0.0;
		logProbability$var25 = 0.0;
		logProbability$var28 = 0.0;
		if(!fixedProbFlag$sample32)
			logProbability$sample32 = 0.0;
		logProbability$var32 = 0.0;
		logProbability$var35 = 0.0;
		if(!fixedProbFlag$sample39)
			logProbability$sample39 = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample56)
			logProbability$var50 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		if(fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(fixedFlag$sample39)
			logProbabilityValue$sample39();
		logProbabilityValue$sample56();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample32();
		logProbabilityValue$sample39();
		logProbabilityValue$sample56();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample32();
		logProbabilityValue$sample39();
		logProbabilityValue$sample56();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(guard1) {
			if(!fixedFlag$sample20)
				var16 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!fixedFlag$sample20)
				bias = var16;
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample32)
					var28 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
				if(!fixedFlag$sample32)
					bias = var28;
			} else {
				if(!fixedFlag$sample39)
					var35 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
				if(!fixedFlag$sample39)
					bias = var35;
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		for(int i = (samples - ((((samples - 1) - 0) % 1) + 1)); i >= ((0 - 1) + 1); i -= 1)
			flips[i] = flipsMeasured[i];
	}

	@Override
	public final void setIntermediates() {
		if(true) {
			if(guard1)
				bias = var16;
			else {
				double var36;
				if((guard2 <= 2))
					var36 = var28;
				else
					var36 = var35;
				bias = var36;
			}
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK12(boolean[] flipsMeasured, boolean guard1, int guard2) {\n    int samples = flipsMeasured.length;\n        \n    double bias;\n    if(guard1)\n      bias = beta(1.0, 1).sample();\n    else { \n        if(guard2 <= 2) {\n            bias = beta(1.0, 1).sample()/2;\n        } else\n            bias = beta(1.0, 1).sample()/3;\n    }\n        \n    Bernoulli bernoulli = bernoulli(bias);\n    boolean[] flips = bernoulli.sample(samples);\n\n    for(int i:[0..samples))\n        flips[i].observe(flipsMeasured[i]);\n}\n";
	}
}