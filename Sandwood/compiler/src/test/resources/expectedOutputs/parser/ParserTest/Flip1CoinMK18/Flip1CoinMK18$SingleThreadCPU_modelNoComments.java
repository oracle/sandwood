package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK18$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip1CoinMK18$CoreInterface {
	private int a;
	private int b;
	private double[][][] bias;
	private int c;
	private boolean fixedFlag$sample14 = false;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample85 = false;
	private boolean fixedProbFlag$sample14 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample85 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$q;
	private double logProbability$t;
	private double logProbability$var12;
	private double logProbability$var18;
	private double logProbability$var78;
	private double q;
	private int samples;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;
	private double t;

	public Flip1CoinMK18$SingleThreadCPU(ExecutionTarget target) {
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
	public final boolean get$fixedFlag$sample14() {
		return fixedFlag$sample14;
	}

	@Override
	public final void set$fixedFlag$sample14(boolean cv$value) {
		fixedFlag$sample14 = cv$value;
		fixedProbFlag$sample14 = (fixedFlag$sample14 && fixedProbFlag$sample14);
		fixedProbFlag$sample85 = (fixedFlag$sample14 && fixedProbFlag$sample85);
	}

	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	@Override
	public final void set$fixedFlag$sample20(boolean cv$value) {
		fixedFlag$sample20 = cv$value;
		fixedProbFlag$sample20 = (fixedFlag$sample20 && fixedProbFlag$sample20);
		fixedProbFlag$sample85 = (fixedFlag$sample20 && fixedProbFlag$sample85);
	}

	@Override
	public final boolean get$fixedFlag$sample85() {
		return fixedFlag$sample85;
	}

	@Override
	public final void set$fixedFlag$sample85(boolean cv$value) {
		fixedFlag$sample85 = cv$value;
		fixedProbFlag$sample85 = (fixedFlag$sample85 && fixedProbFlag$sample85);
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
	}

	private final void logProbabilityValue$sample14() {
		if(!fixedProbFlag$sample14) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = q;
				{
					{
						double var10 = 1.0;
						double var11 = 1.0;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var10, var11));
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
			logProbability$var12 = cv$sampleAccumulator;
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
			if(fixedFlag$sample14)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample14 = fixedFlag$sample14;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$q;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var12 = cv$rvAccumulator;
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
			if(fixedFlag$sample14)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample20() {
		if(!fixedProbFlag$sample20) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = t;
				{
					{
						double var16 = 1.0;
						double var17 = 1.0;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var16, var17));
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
			logProbability$var18 = cv$sampleAccumulator;
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
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample20 = fixedFlag$sample20;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$t;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var18 = cv$rvAccumulator;
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
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample85() {
		if(!fixedProbFlag$sample85) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var77 = 0; var77 < samples; var77 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[var77];
					{
						{
							double var72 = bias[a][b][c];
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var72));
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
			logProbability$var78 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample85 = ((fixedFlag$sample85 && fixedFlag$sample14) && fixedFlag$sample20);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var78;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bernoulli = cv$rvAccumulator;
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample14() {
		double cv$originalValue = q;
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
					q = cv$proposedValue;
					{
						{
							double[][] var23 = bias[0];
							double[] var35 = var23[1];
							var35[0] = (1 - cv$currentValue);
						}
					}
					{
						boolean guard$sample14put75 = false;
						if(!guard$sample14put75) {
							guard$sample14put75 = true;
							{
								double[][] var47 = bias[1];
								double[] var49 = var47[0];
								var49[1] = (1 - cv$currentValue);
								double[] var59 = var47[1];
								var59[0] = (1 - cv$currentValue);
								var59[1] = cv$currentValue;
							}
						}
						if(!guard$sample14put75) {
							guard$sample14put75 = true;
							{
								double[][] var47 = bias[1];
								double[] var49 = var47[0];
								var49[1] = (1 - cv$currentValue);
								double[] var59 = var47[1];
								var59[0] = (1 - cv$currentValue);
								var59[1] = cv$currentValue;
							}
						}
						if(!guard$sample14put75) {
							guard$sample14put75 = true;
							{
								double[][] var47 = bias[1];
								double[] var49 = var47[0];
								var49[1] = (1 - cv$currentValue);
								double[] var59 = var47[1];
								var59[0] = (1 - cv$currentValue);
								var59[1] = cv$currentValue;
							}
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var10;
				{
					cv$temp$0$var10 = 1.0;
				}
				double cv$temp$1$var11;
				{
					cv$temp$1$var11 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$currentValue, cv$temp$0$var10, cv$temp$1$var11));
				{
					{
						double traceTempVariable$q$5_1 = cv$currentValue;
						double traceTempVariable$var72$5_2 = (1 - traceTempVariable$q$5_1);
						if((0 == a)) {
							if((1 == b)) {
								if((0 == c)) {
									{
										for(int var77 = 0; var77 < samples; var77 += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$2$var72;
															{
																double var72 = traceTempVariable$var72$5_2;
																cv$temp$2$var72 = var72;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$2$var72)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$2$var72)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$2$var72));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$2$var72)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$2$var72)));
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
						double traceTempVariable$var72$6_2 = (1 - traceTempVariable$q$6_1);
						if((1 == a)) {
							if((0 == b)) {
								if((1 == c)) {
									{
										for(int var77 = 0; var77 < samples; var77 += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$3$var72;
															{
																double var72 = traceTempVariable$var72$6_2;
																cv$temp$3$var72 = var72;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$3$var72)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$3$var72)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$3$var72));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$3$var72)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$3$var72)));
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
						double traceTempVariable$var72$7_2 = (1 - traceTempVariable$q$7_1);
						if((1 == a)) {
							if((1 == b)) {
								if((0 == c)) {
									{
										for(int var77 = 0; var77 < samples; var77 += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$4$var72;
															{
																double var72 = traceTempVariable$var72$7_2;
																cv$temp$4$var72 = var72;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$4$var72)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$4$var72)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$4$var72));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$4$var72)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$4$var72)));
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
						double traceTempVariable$var72$8_1 = cv$currentValue;
						if((1 == a)) {
							if((1 == b)) {
								if((1 == c)) {
									{
										for(int var77 = 0; var77 < samples; var77 += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$5$var72;
															{
																double var72 = traceTempVariable$var72$8_1;
																cv$temp$5$var72 = var72;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$5$var72)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$5$var72)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$5$var72));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$5$var72)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$5$var72)));
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
					double[][] var23 = bias[0];
					double[] var35 = var23[1];
					var35[0] = (1 - q);
				}
			}
			{
				boolean guard$sample14put75 = false;
				if(!guard$sample14put75) {
					guard$sample14put75 = true;
					{
						double[][] var47 = bias[1];
						double[] var49 = var47[0];
						var49[1] = (1 - q);
						double[] var59 = var47[1];
						var59[0] = (1 - q);
						var59[1] = q;
					}
				}
				if(!guard$sample14put75) {
					guard$sample14put75 = true;
					{
						double[][] var47 = bias[1];
						double[] var49 = var47[0];
						var49[1] = (1 - q);
						double[] var59 = var47[1];
						var59[0] = (1 - q);
						var59[1] = q;
					}
				}
				if(!guard$sample14put75) {
					guard$sample14put75 = true;
					{
						double[][] var47 = bias[1];
						double[] var49 = var47[0];
						var49[1] = (1 - q);
						double[] var59 = var47[1];
						var59[0] = (1 - q);
						var59[1] = q;
					}
				}
			}
		}
	}

	private final void sample20() {
		double cv$originalValue = t;
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
					t = cv$proposedValue;
					{
						boolean guard$sample20put48 = false;
						if(!guard$sample20put48) {
							guard$sample20put48 = true;
							{
								double[][] var23 = bias[0];
								double[] var25 = var23[0];
								var25[0] = cv$currentValue;
								var25[1] = (1 - cv$currentValue);
								double[] var35 = var23[1];
								var35[1] = cv$currentValue;
							}
						}
						if(!guard$sample20put48) {
							guard$sample20put48 = true;
							{
								double[][] var23 = bias[0];
								double[] var25 = var23[0];
								var25[0] = cv$currentValue;
								var25[1] = (1 - cv$currentValue);
								double[] var35 = var23[1];
								var35[1] = cv$currentValue;
							}
						}
						if(!guard$sample20put48) {
							guard$sample20put48 = true;
							{
								double[][] var23 = bias[0];
								double[] var25 = var23[0];
								var25[0] = cv$currentValue;
								var25[1] = (1 - cv$currentValue);
								double[] var35 = var23[1];
								var35[1] = cv$currentValue;
							}
						}
					}
					{
						{
							double[][] var47 = bias[1];
							double[] var49 = var47[0];
							var49[0] = cv$currentValue;
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var16;
				{
					cv$temp$0$var16 = 1.0;
				}
				double cv$temp$1$var17;
				{
					cv$temp$1$var17 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$currentValue, cv$temp$0$var16, cv$temp$1$var17));
				{
					{
						double traceTempVariable$var72$5_1 = cv$currentValue;
						if((0 == a)) {
							if((0 == b)) {
								if((0 == c)) {
									{
										for(int var77 = 0; var77 < samples; var77 += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$2$var72;
															{
																double var72 = traceTempVariable$var72$5_1;
																cv$temp$2$var72 = var72;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$2$var72)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$2$var72)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$2$var72));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$2$var72)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$2$var72)));
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
						double traceTempVariable$var72$6_2 = (1 - traceTempVariable$t$6_1);
						if((0 == a)) {
							if((0 == b)) {
								if((1 == c)) {
									{
										for(int var77 = 0; var77 < samples; var77 += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$3$var72;
															{
																double var72 = traceTempVariable$var72$6_2;
																cv$temp$3$var72 = var72;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$3$var72)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$3$var72)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$3$var72));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$3$var72)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$3$var72)));
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
						double traceTempVariable$var72$7_1 = cv$currentValue;
						if((0 == a)) {
							if((1 == b)) {
								if((1 == c)) {
									{
										for(int var77 = 0; var77 < samples; var77 += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$4$var72;
															{
																double var72 = traceTempVariable$var72$7_1;
																cv$temp$4$var72 = var72;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$4$var72)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$4$var72)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$4$var72));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$4$var72)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$4$var72)));
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
						double traceTempVariable$var72$8_1 = cv$currentValue;
						if((1 == a)) {
							if((0 == b)) {
								if((0 == c)) {
									{
										for(int var77 = 0; var77 < samples; var77 += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$5$var72;
															{
																double var72 = traceTempVariable$var72$8_1;
																cv$temp$5$var72 = var72;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$5$var72)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$5$var72)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$5$var72));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$5$var72)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var77], cv$temp$5$var72)));
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
				boolean guard$sample20put48 = false;
				if(!guard$sample20put48) {
					guard$sample20put48 = true;
					{
						double[][] var23 = bias[0];
						double[] var25 = var23[0];
						var25[0] = t;
						var25[1] = (1 - t);
						double[] var35 = var23[1];
						var35[1] = t;
					}
				}
				if(!guard$sample20put48) {
					guard$sample20put48 = true;
					{
						double[][] var23 = bias[0];
						double[] var25 = var23[0];
						var25[0] = t;
						var25[1] = (1 - t);
						double[] var35 = var23[1];
						var35[1] = t;
					}
				}
				if(!guard$sample20put48) {
					guard$sample20put48 = true;
					{
						double[][] var23 = bias[0];
						double[] var25 = var23[0];
						var25[0] = t;
						var25[1] = (1 - t);
						double[] var35 = var23[1];
						var35[1] = t;
					}
				}
			}
			{
				{
					double[][] var47 = bias[1];
					double[] var49 = var47[0];
					var49[0] = t;
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
		if(!setFlag$flips) {
			{
				flips = new boolean[samples];
			}
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample14)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample20)
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		double[][] var23 = bias[0];
		double[] var25 = var23[0];
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var25[0] = t;
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var25[1] = (1 - t);
		double[] var35 = var23[1];
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var35[0] = (1 - q);
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var35[1] = t;
		double[][] var47 = bias[1];
		double[] var49 = var47[0];
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var49[0] = t;
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var49[1] = (1 - q);
		double[] var59 = var47[1];
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var59[0] = (1 - q);
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var59[1] = q;
		for(int var77 = 0; var77 < samples; var77 += 1) {
			if(!fixedFlag$sample85)
				flips[var77] = DistributionSampling.sampleBernoulli(RNG$, bias[a][b][c]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample14)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample20)
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		double[][] var23 = bias[0];
		double[] var25 = var23[0];
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var25[0] = t;
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var25[1] = (1 - t);
		double[] var35 = var23[1];
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var35[0] = (1 - q);
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var35[1] = t;
		double[][] var47 = bias[1];
		double[] var49 = var47[0];
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var49[0] = t;
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var49[1] = (1 - q);
		double[] var59 = var47[1];
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var59[0] = (1 - q);
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var59[1] = q;
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample14)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample20)
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		double[][] var23 = bias[0];
		double[] var25 = var23[0];
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var25[0] = t;
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var25[1] = (1 - t);
		double[] var35 = var23[1];
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var35[0] = (1 - q);
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var35[1] = t;
		double[][] var47 = bias[1];
		double[] var49 = var47[0];
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var49[0] = t;
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var49[1] = (1 - q);
		double[] var59 = var47[1];
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var59[0] = (1 - q);
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var59[1] = q;
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample14)
				sample14();
			if(!fixedFlag$sample20)
				sample20();
		} else {
			if(!fixedFlag$sample20)
				sample20();
			if(!fixedFlag$sample14)
				sample14();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var12 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample14)
			logProbability$q = 0.0;
		logProbability$var18 = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$t = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample85)
			logProbability$var78 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample14)
			logProbabilityValue$sample14();
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		logProbabilityValue$sample85();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample14();
		logProbabilityValue$sample20();
		logProbabilityValue$sample85();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample14();
		logProbabilityValue$sample20();
		logProbabilityValue$sample85();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample14)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample20)
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		double[][] var23 = bias[0];
		double[] var25 = var23[0];
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var25[0] = t;
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var25[1] = (1 - t);
		double[] var35 = var23[1];
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var35[0] = (1 - q);
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var35[1] = t;
		double[][] var47 = bias[1];
		double[] var49 = var47[0];
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var49[0] = t;
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var49[1] = (1 - q);
		double[] var59 = var47[1];
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var59[0] = (1 - q);
		if(!(fixedFlag$sample14 && fixedFlag$sample20))
			var59[1] = q;
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		boolean[] cv$source1 = flipsMeasured;
		boolean[] cv$target1 = flips;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		if(true) {
			double[][] var23 = bias[0];
			double[] var25 = var23[0];
			var25[0] = t;
			var25[1] = (1 - t);
			double[] var35 = var23[1];
			var35[0] = (1 - q);
			var35[1] = t;
		}
		if(true) {
			double[][] var47 = bias[1];
			double[] var49 = var47[0];
			var49[0] = t;
			var49[1] = (1 - q);
			double[] var59 = var47[1];
			var59[0] = (1 - q);
			var59[1] = q;
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2025, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK18(int samples, int a, int b, int c, boolean[] flipsMeasured) {\n    \n    double q = beta(1,1).sample();\n    double t = beta(1,1).sample();\n    double[][][] bias = {{{t, 1-t},{1-q, t}},{{t, 1-q},{1-q, q}}};\n    \n    Bernoulli bernoulli = bernoulli(bias[a][b][c]);\n    boolean[] flips = bernoulli.sample(samples);\n    \n    flips.observe(flipsMeasured);\n}\n";
	}
}