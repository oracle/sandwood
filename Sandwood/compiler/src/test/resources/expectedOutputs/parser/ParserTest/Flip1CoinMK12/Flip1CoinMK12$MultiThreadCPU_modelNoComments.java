package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK12$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK12$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample15 = false;
	private boolean fixedFlag$sample24 = false;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample41 = false;
	private boolean fixedProbFlag$sample15 = false;
	private boolean fixedProbFlag$sample24 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample41 = false;
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
	private double logProbability$sample15;
	private double logProbability$sample24;
	private double logProbability$sample31;
	private double logProbability$var10;
	private double logProbability$var11;
	private double logProbability$var17;
	private double logProbability$var20;
	private double logProbability$var24;
	private double logProbability$var27;
	private double logProbability$var35;
	private int samples;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;
	private double var11;
	private double var20;
	private double var27;

	public Flip1CoinMK12$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final boolean get$fixedFlag$sample15() {
		return fixedFlag$sample15;
	}

	@Override
	public final void set$fixedFlag$sample15(boolean cv$value) {
		fixedFlag$sample15 = cv$value;
		fixedProbFlag$sample15 = (fixedFlag$sample15 && fixedProbFlag$sample15);
		fixedProbFlag$sample41 = (fixedFlag$sample15 && fixedProbFlag$sample41);
	}

	@Override
	public final boolean get$fixedFlag$sample24() {
		return fixedFlag$sample24;
	}

	@Override
	public final void set$fixedFlag$sample24(boolean cv$value) {
		fixedFlag$sample24 = cv$value;
		fixedProbFlag$sample24 = (fixedFlag$sample24 && fixedProbFlag$sample24);
		fixedProbFlag$sample41 = (fixedFlag$sample24 && fixedProbFlag$sample41);
	}

	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	@Override
	public final void set$fixedFlag$sample31(boolean cv$value) {
		fixedFlag$sample31 = cv$value;
		fixedProbFlag$sample31 = (fixedFlag$sample31 && fixedProbFlag$sample31);
		fixedProbFlag$sample41 = (fixedFlag$sample31 && fixedProbFlag$sample41);
	}

	@Override
	public final boolean get$fixedFlag$sample41() {
		return fixedFlag$sample41;
	}

	@Override
	public final void set$fixedFlag$sample41(boolean cv$value) {
		fixedFlag$sample41 = cv$value;
		fixedProbFlag$sample41 = (fixedFlag$sample41 && fixedProbFlag$sample41);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
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

	private final void logProbabilityValue$sample15() {
		if(!fixedProbFlag$sample15) {
			double cv$accumulator = 0.0;
			if(guard1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = var11;
					{
						{
							double var7 = 1.0;
							double var9 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var7, var9));
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
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var10 = cv$sampleAccumulator;
				logProbability$sample15 = cv$sampleProbability;
			}
			boolean cv$guard$bias = false;
			logProbability$var11 = (logProbability$var11 + cv$accumulator);
			{
				if(guard1) {
					double traceTempVariable$bias$2_1 = var11;
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						logProbability$bias = (logProbability$bias + cv$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample15 = fixedFlag$sample15;
		} else {
			double cv$accumulator = 0.0;
			if(guard1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample15;
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var10 = cv$rvAccumulator;
			}
			boolean cv$guard$bias = false;
			logProbability$var11 = (logProbability$var11 + cv$accumulator);
			{
				if(guard1) {
					double traceTempVariable$bias$3_1 = var11;
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						logProbability$bias = (logProbability$bias + cv$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample24() {
		if(!fixedProbFlag$sample24) {
			double cv$accumulator = 0.0;
			if(!guard1) {
				if((guard2 <= 2)) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						double cv$sampleValue = (var20 * 2);
						{
							{
								double var14 = 1.0;
								double var16 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var14, var16));
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
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$var17 = cv$sampleAccumulator;
					logProbability$sample24 = cv$sampleProbability;
				}
			}
			boolean cv$guard$bias = false;
			logProbability$var20 = (logProbability$var20 + cv$accumulator);
			{
				if(!guard1) {
					if((guard2 <= 2)) {
						double traceTempVariable$var28$2_1 = var20;
						if(!guard1) {
							double traceTempVariable$bias$2_2 = traceTempVariable$var28$2_1;
							if(!cv$guard$bias) {
								cv$guard$bias = true;
								logProbability$bias = (logProbability$bias + cv$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample24 = fixedFlag$sample24;
		} else {
			double cv$accumulator = 0.0;
			if(!guard1) {
				if((guard2 <= 2)) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample24;
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var17 = cv$rvAccumulator;
				}
			}
			boolean cv$guard$bias = false;
			logProbability$var20 = (logProbability$var20 + cv$accumulator);
			{
				if(!guard1) {
					if((guard2 <= 2)) {
						double traceTempVariable$var28$3_1 = var20;
						if(!guard1) {
							double traceTempVariable$bias$3_2 = traceTempVariable$var28$3_1;
							if(!cv$guard$bias) {
								cv$guard$bias = true;
								logProbability$bias = (logProbability$bias + cv$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!fixedProbFlag$sample31) {
			double cv$accumulator = 0.0;
			if(!guard1) {
				if(!(guard2 <= 2)) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						double cv$sampleValue = (var27 * 3);
						{
							{
								double var21 = 1.0;
								double var23 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var21, var23));
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
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$var24 = cv$sampleAccumulator;
					logProbability$sample31 = cv$sampleProbability;
				}
			}
			boolean cv$guard$bias = false;
			logProbability$var27 = (logProbability$var27 + cv$accumulator);
			{
				if(!guard1) {
					if(!(guard2 <= 2)) {
						double traceTempVariable$var28$2_1 = var27;
						if(!guard1) {
							double traceTempVariable$bias$2_2 = traceTempVariable$var28$2_1;
							if(!cv$guard$bias) {
								cv$guard$bias = true;
								logProbability$bias = (logProbability$bias + cv$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample31 = fixedFlag$sample31;
		} else {
			double cv$accumulator = 0.0;
			if(!guard1) {
				if(!(guard2 <= 2)) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample31;
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var24 = cv$rvAccumulator;
				}
			}
			boolean cv$guard$bias = false;
			logProbability$var27 = (logProbability$var27 + cv$accumulator);
			{
				if(!guard1) {
					if(!(guard2 <= 2)) {
						double traceTempVariable$var28$3_1 = var27;
						if(!guard1) {
							double traceTempVariable$bias$3_2 = traceTempVariable$var28$3_1;
							if(!cv$guard$bias) {
								cv$guard$bias = true;
								logProbability$bias = (logProbability$bias + cv$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample41() {
		if(!fixedProbFlag$sample41) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var34 = 0; var34 < samples; var34 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[var34];
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
			logProbability$var35 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample41 = (((fixedFlag$sample41 && fixedFlag$sample15) && fixedFlag$sample24) && fixedFlag$sample31);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var35;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bernoulli = cv$rvAccumulator;
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample15() {
		int cv$sum = 0;
		int cv$count = 0;
		{
			{
				{
					if(guard1) {
						double traceTempVariable$bias$1_1 = var11;
						{
							for(int var34 = 0; var34 < samples; var34 += 1) {
								cv$count = (cv$count + 1);
								if(flips[var34])
									cv$sum = (cv$sum + 1);
							}
						}
					}
				}
			}
		}
		var11 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		{
			if(guard1) {
				double traceTempVariable$bias$4_1 = var11;
				{
					bias = traceTempVariable$bias$4_1;
				}
			}
		}
	}

	private final void sample24() {
		double cv$originalValue = (var20 * 2);
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var18 = cv$proposedValue;
					var20 = (cv$currentValue / 2);
					{
						if(!guard1) {
							if((guard2 <= 2)) {
								double traceTempVariable$var28$1_1 = var20;
								if(!guard1) {
									double traceTempVariable$bias$1_2 = traceTempVariable$var28$1_1;
									{
										bias = traceTempVariable$bias$1_2;
									}
								}
							}
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var14;
				{
					cv$temp$0$var14 = 1.0;
				}
				double cv$temp$1$var16;
				{
					cv$temp$1$var16 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$currentValue, cv$temp$0$var14, cv$temp$1$var16));
				{
					{
						if(!guard1) {
							if((guard2 <= 2)) {
								double traceTempVariable$var28$2_1 = var20;
								if(!guard1) {
									double traceTempVariable$bias$2_2 = traceTempVariable$var28$2_1;
									{
										for(int var34 = 0; var34 < samples; var34 += 1) {
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
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$2$bias)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$2$bias)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$2$bias));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$2$bias)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$2$bias)));
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
			double var18 = cv$originalValue;
			var20 = (var18 / 2);
			{
				if(!guard1) {
					if((guard2 <= 2)) {
						double traceTempVariable$var28$5_1 = var20;
						if(!guard1) {
							double traceTempVariable$bias$5_2 = traceTempVariable$var28$5_1;
							{
								bias = traceTempVariable$bias$5_2;
							}
						}
					}
				}
			}
		}
	}

	private final void sample31() {
		double cv$originalValue = (var27 * 3);
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var25 = cv$proposedValue;
					var27 = (cv$currentValue / 3);
					{
						if(!guard1) {
							if(!(guard2 <= 2)) {
								double traceTempVariable$var28$1_1 = var27;
								if(!guard1) {
									double traceTempVariable$bias$1_2 = traceTempVariable$var28$1_1;
									{
										bias = traceTempVariable$bias$1_2;
									}
								}
							}
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var21;
				{
					cv$temp$0$var21 = 1.0;
				}
				double cv$temp$1$var23;
				{
					cv$temp$1$var23 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$currentValue, cv$temp$0$var21, cv$temp$1$var23));
				{
					{
						if(!guard1) {
							if(!(guard2 <= 2)) {
								double traceTempVariable$var28$2_1 = var27;
								if(!guard1) {
									double traceTempVariable$bias$2_2 = traceTempVariable$var28$2_1;
									{
										for(int var34 = 0; var34 < samples; var34 += 1) {
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
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$2$bias)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$2$bias)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$2$bias));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$2$bias)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$2$bias)));
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
			double var25 = cv$originalValue;
			var27 = (var25 / 3);
			{
				if(!guard1) {
					if(!(guard2 <= 2)) {
						double traceTempVariable$var28$5_1 = var27;
						if(!guard1) {
							double traceTempVariable$bias$5_2 = traceTempVariable$var28$5_1;
							{
								bias = traceTempVariable$bias$5_2;
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
			if(!fixedFlag$sample15)
				var11 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!fixedFlag$sample15)
				bias = var11;
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample24)
					var20 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
				if(!fixedFlag$sample24)
					bias = var20;
			} else {
				if(!fixedFlag$sample31)
					var27 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
				if(!fixedFlag$sample31)
					bias = var27;
			}
		}
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$var34, int forEnd$var34, int threadID$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var34 = forStart$var34; var34 < forEnd$var34; var34 += 1) {
						if(!fixedFlag$sample41)
							flips[var34] = DistributionSampling.sampleBernoulli(RNG$1, bias);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(guard1) {
			if(!fixedFlag$sample15)
				var11 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!fixedFlag$sample15)
				bias = var11;
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample24)
					var20 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
				if(!fixedFlag$sample24)
					bias = var20;
			} else {
				if(!fixedFlag$sample31)
					var27 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
				if(!fixedFlag$sample31)
					bias = var27;
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(guard1) {
			if(!fixedFlag$sample15)
				var11 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!fixedFlag$sample15)
				bias = var11;
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample24)
					var20 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
				if(!fixedFlag$sample24)
					bias = var20;
			} else {
				if(!fixedFlag$sample31)
					var27 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
				if(!fixedFlag$sample31)
					bias = var27;
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(guard1) {
				if(!fixedFlag$sample15)
					sample15();
			} else {
				if((guard2 <= 2)) {
					if(!fixedFlag$sample24)
						sample24();
				} else {
					if(!fixedFlag$sample31)
						sample31();
				}
			}
		} else {
			if(guard1) {
				if(!fixedFlag$sample15)
					sample15();
			} else {
				if((guard2 <= 2)) {
					if(!fixedFlag$sample24)
						sample24();
				} else {
					if(!fixedFlag$sample31)
						sample31();
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
		logProbability$var10 = 0.0;
		logProbability$var11 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample15)
			logProbability$sample15 = 0.0;
		logProbability$var17 = 0.0;
		logProbability$var20 = 0.0;
		if(!fixedProbFlag$sample24)
			logProbability$sample24 = 0.0;
		logProbability$var24 = 0.0;
		logProbability$var27 = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$sample31 = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample41)
			logProbability$var35 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample15)
			logProbabilityValue$sample15();
		if(fixedFlag$sample24)
			logProbabilityValue$sample24();
		if(fixedFlag$sample31)
			logProbabilityValue$sample31();
		logProbabilityValue$sample41();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample15();
		logProbabilityValue$sample24();
		logProbabilityValue$sample31();
		logProbabilityValue$sample41();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample15();
		logProbabilityValue$sample24();
		logProbabilityValue$sample31();
		logProbabilityValue$sample41();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(guard1) {
			if(!fixedFlag$sample15)
				var11 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!fixedFlag$sample15)
				bias = var11;
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample24)
					var20 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
				if(!fixedFlag$sample24)
					bias = var20;
			} else {
				if(!fixedFlag$sample31)
					var27 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
				if(!fixedFlag$sample31)
					bias = var27;
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
				bias = var11;
			else {
				double var28;
				if((guard2 <= 2))
					var28 = var20;
				else
					var28 = var27;
				bias = var28;
			}
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK12(boolean[] flipsMeasured, boolean guard1, int guard2) {\n    int samples = flipsMeasured.length;\n        \n    double bias;\n    if(guard1)\n      bias = beta(1.0, 1).sample();\n    else { \n        if(guard2 <= 2) {\n            bias = beta(1.0, 1).sample()/2;\n        } else\n            bias = beta(1.0, 1).sample()/3;\n    }\n        \n    Bernoulli bernoulli = bernoulli(bias);\n    boolean[] flips = bernoulli.sample(samples);\n\n    for(int i:[0..samples))\n        flips[i].observe(flipsMeasured[i]);\n}\n";
	}
}