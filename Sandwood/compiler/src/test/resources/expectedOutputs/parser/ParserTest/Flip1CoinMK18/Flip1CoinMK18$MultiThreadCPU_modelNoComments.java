package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK18$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK18$CoreInterface {
	private int a;
	private int b;
	private double[][][] bias;
	private int c;
	private boolean fixedFlag$sample11 = false;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedProbFlag$sample103 = false;
	private boolean fixedProbFlag$sample11 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$q;
	private double logProbability$t;
	private double logProbability$var10;
	private double logProbability$var16;
	private double logProbability$var97;
	private double q;
	private int samples;
	private boolean system$gibbsForward = true;
	private double t;

	public Flip1CoinMK18$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final int get$a() {
		return a;
	}

	@Override
	public final void set$a(int cv$value) {
		a = cv$value;
	}

	@Override
	public final int get$b() {
		return b;
	}

	@Override
	public final void set$b(int cv$value) {
		b = cv$value;
	}

	@Override
	public final double[][][] get$bias() {
		return bias;
	}

	@Override
	public final int get$c() {
		return c;
	}

	@Override
	public final void set$c(int cv$value) {
		c = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample11() {
		return fixedFlag$sample11;
	}

	@Override
	public final void set$fixedFlag$sample11(boolean cv$value) {
		fixedFlag$sample11 = cv$value;
		fixedProbFlag$sample11 = (fixedFlag$sample11 && fixedProbFlag$sample11);
		fixedProbFlag$sample103 = (fixedFlag$sample11 && fixedProbFlag$sample103);
	}

	@Override
	public final boolean get$fixedFlag$sample17() {
		return fixedFlag$sample17;
	}

	@Override
	public final void set$fixedFlag$sample17(boolean cv$value) {
		fixedFlag$sample17 = cv$value;
		fixedProbFlag$sample17 = (fixedFlag$sample17 && fixedProbFlag$sample17);
		fixedProbFlag$sample103 = (fixedFlag$sample17 && fixedProbFlag$sample103);
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
	public final void set$flipsMeasured(boolean[] cv$value) {
		flipsMeasured = cv$value;
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
	public final double get$logProbability$q() {
		return logProbability$q;
	}

	@Override
	public final double get$logProbability$t() {
		return logProbability$t;
	}

	@Override
	public final double get$q() {
		return q;
	}

	@Override
	public final void set$q(double cv$value) {
		q = cv$value;
		fixedProbFlag$sample11 = false;
		fixedProbFlag$sample103 = false;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	@Override
	public final void set$samples(int cv$value) {
		samples = cv$value;
	}

	@Override
	public final double get$t() {
		return t;
	}

	@Override
	public final void set$t(double cv$value) {
		t = cv$value;
		fixedProbFlag$sample17 = false;
		fixedProbFlag$sample103 = false;
	}

	private final void logProbabilityValue$sample103() {
		if(!fixedProbFlag$sample103) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var96 = 0; var96 < samples; var96 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[var96];
					{
						{
							double var84 = bias[a][b][c];
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var84));
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
			logProbability$var97 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample103 = (fixedFlag$sample11 && fixedFlag$sample17);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var97;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bernoulli = cv$rvAccumulator;
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample11() {
		if(!fixedProbFlag$sample11) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = q;
				{
					{
						double var8 = 1.0;
						double var9 = 1.0;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var8, var9));
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
			logProbability$q = cv$sampleProbability;
			boolean cv$guard$bias = false;
			{
				if(!cv$guard$bias) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
			}
			{
				if(!cv$guard$bias) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
				if(!cv$guard$bias) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
				if(!cv$guard$bias) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample11)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample11 = fixedFlag$sample11;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$q;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var10 = cv$rvAccumulator;
			boolean cv$guard$bias = false;
			{
				if(!cv$guard$bias) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
			}
			{
				if(!cv$guard$bias) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
				if(!cv$guard$bias) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
				if(!cv$guard$bias) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample11)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample17() {
		if(!fixedProbFlag$sample17) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = t;
				{
					{
						double var14 = 1.0;
						double var15 = 1.0;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var14, var15));
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
			logProbability$var16 = cv$sampleAccumulator;
			logProbability$t = cv$sampleProbability;
			boolean cv$guard$bias = false;
			{
				if(!cv$guard$bias) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
				if(!cv$guard$bias) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
				if(!cv$guard$bias) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
			}
			{
				if(!cv$guard$bias) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample17 = fixedFlag$sample17;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$t;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var16 = cv$rvAccumulator;
			boolean cv$guard$bias = false;
			{
				if(!cv$guard$bias) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
				if(!cv$guard$bias) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
				if(!cv$guard$bias) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
			}
			{
				if(!cv$guard$bias) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + cv$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample11() {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double cv$originalValue = q;
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					q = cv$proposedValue;
					{
						{
							double[][] var21 = bias[0];
							double[] var36 = var21[1];
							var36[0] = (1 - cv$currentValue);
						}
					}
					{
						boolean guard$sample11put86 = false;
						if(!guard$sample11put86) {
							guard$sample11put86 = true;
							{
								double[][] var52 = bias[1];
								double[] var54 = var52[0];
								var54[1] = (1 - cv$currentValue);
								double[] var67 = var52[1];
								var67[0] = (1 - cv$currentValue);
								var67[1] = cv$currentValue;
							}
						}
						if(!guard$sample11put86) {
							guard$sample11put86 = true;
							{
								double[][] var52 = bias[1];
								double[] var54 = var52[0];
								var54[1] = (1 - cv$currentValue);
								double[] var67 = var52[1];
								var67[0] = (1 - cv$currentValue);
								var67[1] = cv$currentValue;
							}
						}
						if(!guard$sample11put86) {
							guard$sample11put86 = true;
							{
								double[][] var52 = bias[1];
								double[] var54 = var52[0];
								var54[1] = (1 - cv$currentValue);
								double[] var67 = var52[1];
								var67[0] = (1 - cv$currentValue);
								var67[1] = cv$currentValue;
							}
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var8;
				{
					cv$temp$0$var8 = 1.0;
				}
				double cv$temp$1$var9;
				{
					cv$temp$1$var9 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$currentValue, cv$temp$0$var8, cv$temp$1$var9));
				{
					{
						double traceTempVariable$q$5_1 = cv$currentValue;
						double traceTempVariable$var84$5_2 = (1 - traceTempVariable$q$5_1);
						if((0 == a)) {
							if((1 == b)) {
								if((0 == c)) {
									{
										for(int var96 = 0; var96 < samples; var96 += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$2$var84;
															{
																double var84 = traceTempVariable$var84$5_2;
																cv$temp$2$var84 = var84;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$2$var84)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$2$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$2$var84));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$2$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$2$var84)));
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
						double traceTempVariable$q$6_1 = cv$currentValue;
						double traceTempVariable$var84$6_2 = (1 - traceTempVariable$q$6_1);
						if((1 == a)) {
							if((0 == b)) {
								if((1 == c)) {
									{
										for(int var96 = 0; var96 < samples; var96 += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$3$var84;
															{
																double var84 = traceTempVariable$var84$6_2;
																cv$temp$3$var84 = var84;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$3$var84)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$3$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$3$var84));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$3$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$3$var84)));
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
						double traceTempVariable$q$7_1 = cv$currentValue;
						double traceTempVariable$var84$7_2 = (1 - traceTempVariable$q$7_1);
						if((1 == a)) {
							if((1 == b)) {
								if((0 == c)) {
									{
										for(int var96 = 0; var96 < samples; var96 += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$4$var84;
															{
																double var84 = traceTempVariable$var84$7_2;
																cv$temp$4$var84 = var84;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$4$var84)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$4$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$4$var84));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$4$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$4$var84)));
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
						double traceTempVariable$var84$8_1 = cv$currentValue;
						if((1 == a)) {
							if((1 == b)) {
								if((1 == c)) {
									{
										for(int var96 = 0; var96 < samples; var96 += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$5$var84;
															{
																double var84 = traceTempVariable$var84$8_1;
																cv$temp$5$var84 = var84;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$5$var84)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$5$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$5$var84));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$5$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$5$var84)));
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
			q = cv$originalValue;
			{
				{
					double[][] var21 = bias[0];
					double[] var36 = var21[1];
					var36[0] = (1 - q);
				}
			}
			{
				boolean guard$sample11put86 = false;
				if(!guard$sample11put86) {
					guard$sample11put86 = true;
					{
						double[][] var52 = bias[1];
						double[] var54 = var52[0];
						var54[1] = (1 - q);
						double[] var67 = var52[1];
						var67[0] = (1 - q);
						var67[1] = q;
					}
				}
				if(!guard$sample11put86) {
					guard$sample11put86 = true;
					{
						double[][] var52 = bias[1];
						double[] var54 = var52[0];
						var54[1] = (1 - q);
						double[] var67 = var52[1];
						var67[0] = (1 - q);
						var67[1] = q;
					}
				}
				if(!guard$sample11put86) {
					guard$sample11put86 = true;
					{
						double[][] var52 = bias[1];
						double[] var54 = var52[0];
						var54[1] = (1 - q);
						double[] var67 = var52[1];
						var67[0] = (1 - q);
						var67[1] = q;
					}
				}
			}
		}
	}

	private final void sample17() {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double cv$originalValue = t;
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					t = cv$proposedValue;
					{
						boolean guard$sample17put52 = false;
						if(!guard$sample17put52) {
							guard$sample17put52 = true;
							{
								double[][] var21 = bias[0];
								double[] var23 = var21[0];
								var23[0] = cv$currentValue;
								var23[1] = (1 - cv$currentValue);
								double[] var36 = var21[1];
								var36[1] = cv$currentValue;
							}
						}
						if(!guard$sample17put52) {
							guard$sample17put52 = true;
							{
								double[][] var21 = bias[0];
								double[] var23 = var21[0];
								var23[0] = cv$currentValue;
								var23[1] = (1 - cv$currentValue);
								double[] var36 = var21[1];
								var36[1] = cv$currentValue;
							}
						}
						if(!guard$sample17put52) {
							guard$sample17put52 = true;
							{
								double[][] var21 = bias[0];
								double[] var23 = var21[0];
								var23[0] = cv$currentValue;
								var23[1] = (1 - cv$currentValue);
								double[] var36 = var21[1];
								var36[1] = cv$currentValue;
							}
						}
					}
					{
						{
							double[][] var52 = bias[1];
							double[] var54 = var52[0];
							var54[0] = cv$currentValue;
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
				double cv$temp$1$var15;
				{
					cv$temp$1$var15 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$currentValue, cv$temp$0$var14, cv$temp$1$var15));
				{
					{
						double traceTempVariable$var84$5_1 = cv$currentValue;
						if((0 == a)) {
							if((0 == b)) {
								if((0 == c)) {
									{
										for(int var96 = 0; var96 < samples; var96 += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$2$var84;
															{
																double var84 = traceTempVariable$var84$5_1;
																cv$temp$2$var84 = var84;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$2$var84)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$2$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$2$var84));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$2$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$2$var84)));
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
						double traceTempVariable$t$6_1 = cv$currentValue;
						double traceTempVariable$var84$6_2 = (1 - traceTempVariable$t$6_1);
						if((0 == a)) {
							if((0 == b)) {
								if((1 == c)) {
									{
										for(int var96 = 0; var96 < samples; var96 += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$3$var84;
															{
																double var84 = traceTempVariable$var84$6_2;
																cv$temp$3$var84 = var84;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$3$var84)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$3$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$3$var84));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$3$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$3$var84)));
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
						double traceTempVariable$var84$7_1 = cv$currentValue;
						if((0 == a)) {
							if((1 == b)) {
								if((1 == c)) {
									{
										for(int var96 = 0; var96 < samples; var96 += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$4$var84;
															{
																double var84 = traceTempVariable$var84$7_1;
																cv$temp$4$var84 = var84;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$4$var84)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$4$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$4$var84));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$4$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$4$var84)));
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
						double traceTempVariable$var84$8_1 = cv$currentValue;
						if((1 == a)) {
							if((0 == b)) {
								if((0 == c)) {
									{
										for(int var96 = 0; var96 < samples; var96 += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$5$var84;
															{
																double var84 = traceTempVariable$var84$8_1;
																cv$temp$5$var84 = var84;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$5$var84)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$5$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$5$var84));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$5$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var96], cv$temp$5$var84)));
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
			t = cv$originalValue;
			{
				boolean guard$sample17put52 = false;
				if(!guard$sample17put52) {
					guard$sample17put52 = true;
					{
						double[][] var21 = bias[0];
						double[] var23 = var21[0];
						var23[0] = t;
						var23[1] = (1 - t);
						double[] var36 = var21[1];
						var36[1] = t;
					}
				}
				if(!guard$sample17put52) {
					guard$sample17put52 = true;
					{
						double[][] var21 = bias[0];
						double[] var23 = var21[0];
						var23[0] = t;
						var23[1] = (1 - t);
						double[] var36 = var21[1];
						var36[1] = t;
					}
				}
				if(!guard$sample17put52) {
					guard$sample17put52 = true;
					{
						double[][] var21 = bias[0];
						double[] var23 = var21[0];
						var23[0] = t;
						var23[1] = (1 - t);
						double[] var36 = var21[1];
						var36[1] = t;
					}
				}
			}
			{
				{
					double[][] var52 = bias[1];
					double[] var54 = var52[0];
					var54[0] = t;
				}
			}
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		{
			bias = new double[2][][];
			double[][] subarray$0 = new double[2][];
			bias[0] = subarray$0;
			subarray$0[0] = new double[2];
			subarray$0[1] = new double[2];
			double[][] subarray$1 = new double[2][];
			bias[1] = subarray$1;
			subarray$1[0] = new double[2];
			subarray$1[1] = new double[2];
		}
		{
			flips = new boolean[samples];
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample11)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample17)
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		double[][] var21 = bias[0];
		double[] var23 = var21[0];
		if(!fixedFlag$sample17)
			var23[0] = t;
		if(!fixedFlag$sample17)
			var23[1] = (1 - t);
		double[] var36 = var21[1];
		if(!fixedFlag$sample17)
			var36[0] = (1 - q);
		if(!fixedFlag$sample17)
			var36[1] = t;
		double[][] var52 = bias[1];
		double[] var54 = var52[0];
		if(!fixedFlag$sample11)
			var54[0] = t;
		if(!fixedFlag$sample11)
			var54[1] = (1 - q);
		double[] var67 = var52[1];
		if(!fixedFlag$sample11)
			var67[0] = (1 - q);
		if(!fixedFlag$sample11)
			var67[1] = q;
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$var96, int forEnd$var96, int threadID$var96, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var96 = forStart$var96; var96 < forEnd$var96; var96 += 1)
						flips[var96] = DistributionSampling.sampleBernoulli(RNG$1, bias[a][b][c]);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample11)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample17)
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		double[][] var21 = bias[0];
		double[] var23 = var21[0];
		if(!fixedFlag$sample17)
			var23[0] = t;
		if(!fixedFlag$sample17)
			var23[1] = (1 - t);
		double[] var36 = var21[1];
		if(!fixedFlag$sample17)
			var36[0] = (1 - q);
		if(!fixedFlag$sample17)
			var36[1] = t;
		double[][] var52 = bias[1];
		double[] var54 = var52[0];
		if(!fixedFlag$sample11)
			var54[0] = t;
		if(!fixedFlag$sample11)
			var54[1] = (1 - q);
		double[] var67 = var52[1];
		if(!fixedFlag$sample11)
			var67[0] = (1 - q);
		if(!fixedFlag$sample11)
			var67[1] = q;
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample11)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample17)
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		double[][] var21 = bias[0];
		double[] var23 = var21[0];
		if(!fixedFlag$sample17)
			var23[0] = t;
		if(!fixedFlag$sample17)
			var23[1] = (1 - t);
		double[] var36 = var21[1];
		if(!fixedFlag$sample17)
			var36[0] = (1 - q);
		if(!fixedFlag$sample17)
			var36[1] = t;
		double[][] var52 = bias[1];
		double[] var54 = var52[0];
		if(!fixedFlag$sample11)
			var54[0] = t;
		if(!fixedFlag$sample11)
			var54[1] = (1 - q);
		double[] var67 = var52[1];
		if(!fixedFlag$sample11)
			var67[0] = (1 - q);
		if(!fixedFlag$sample11)
			var67[1] = q;
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample11)
				sample11();
			if(!fixedFlag$sample17)
				sample17();
		} else {
			if(!fixedFlag$sample17)
				sample17();
			if(!fixedFlag$sample11)
				sample11();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var10 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample11)
			logProbability$q = 0.0;
		logProbability$var16 = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$t = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample103)
			logProbability$var97 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample11)
			logProbabilityValue$sample11();
		if(fixedFlag$sample17)
			logProbabilityValue$sample17();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample11();
		logProbabilityValue$sample17();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample11();
		logProbabilityValue$sample17();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample11)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample17)
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		double[][] var21 = bias[0];
		double[] var23 = var21[0];
		if(!fixedFlag$sample17)
			var23[0] = t;
		if(!fixedFlag$sample17)
			var23[1] = (1 - t);
		double[] var36 = var21[1];
		if(!fixedFlag$sample17)
			var36[0] = (1 - q);
		if(!fixedFlag$sample17)
			var36[1] = t;
		double[][] var52 = bias[1];
		double[] var54 = var52[0];
		if(!fixedFlag$sample11)
			var54[0] = t;
		if(!fixedFlag$sample11)
			var54[1] = (1 - q);
		double[] var67 = var52[1];
		if(!fixedFlag$sample11)
			var67[0] = (1 - q);
		if(!fixedFlag$sample11)
			var67[1] = q;
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		boolean[] cv$source1 = flipsMeasured;
		boolean[] cv$target1 = flips;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		double[][] var21 = bias[0];
		double[] var23 = var21[0];
		if(fixedFlag$sample17)
			var23[0] = t;
		if(fixedFlag$sample17)
			var23[1] = (1 - t);
		double[] var36 = var21[1];
		if(fixedFlag$sample17)
			var36[0] = (1 - q);
		if(fixedFlag$sample17)
			var36[1] = t;
		double[][] var52 = bias[1];
		double[] var54 = var52[0];
		if(fixedFlag$sample11)
			var54[0] = t;
		if(fixedFlag$sample11)
			var54[1] = (1 - q);
		double[] var67 = var52[1];
		if(fixedFlag$sample11)
			var67[0] = (1 - q);
		if(fixedFlag$sample11)
			var67[1] = q;
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model Flip1CoinMK18(int samples, int a, int b, int c, boolean[] flipsMeasured) {\n"
		     + "    \n"
		     + "    double q = beta(1,1).sample();\n"
		     + "    double t = beta(1,1).sample();\n"
		     + "    double[][][] bias = {{{t, 1-t},{1-q, t}},{{t, 1-q},{1-q, q}}};\n"
		     + "    \n"
		     + "    Bernoulli bernoulli = bernoulli(bias[a][b][c]);\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "    \n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}