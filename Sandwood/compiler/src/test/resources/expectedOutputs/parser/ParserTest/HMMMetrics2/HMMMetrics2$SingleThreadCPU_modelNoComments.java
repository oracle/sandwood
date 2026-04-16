package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMMetrics2$SingleThreadCPU extends CoreModelSingleThreadCPU implements HMMMetrics2$CoreInterface {
boolean[] constrainedFlag$sample104;
	boolean[][] constrainedFlag$sample123;
	boolean constrainedFlag$sample19 = true;
	boolean[] constrainedFlag$sample32;
	boolean[] constrainedFlag$sample52;
	boolean[] constrainedFlag$sample68;
	boolean[] constrainedFlag$sample84;
	double[][] distribution$sample104;
	double[][][] distribution$sample123;
	boolean fixedFlag$sample104 = false;
	boolean fixedFlag$sample123 = false;
	boolean fixedFlag$sample157 = false;
	boolean fixedFlag$sample19 = false;
	boolean fixedFlag$sample32 = false;
	boolean fixedFlag$sample52 = false;
	boolean fixedFlag$sample68 = false;
	boolean fixedFlag$sample84 = false;
	boolean fixedProbFlag$sample104 = false;
	boolean fixedProbFlag$sample123 = false;
	boolean fixedProbFlag$sample145 = false;
	boolean fixedProbFlag$sample157 = false;
	boolean fixedProbFlag$sample19 = false;
	boolean fixedProbFlag$sample32 = false;
	boolean fixedProbFlag$sample52 = false;
	boolean fixedProbFlag$sample68 = false;
	boolean fixedProbFlag$sample84 = false;
	double[] initialStateDistribution;
	int[] length$metric;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$initialStateDistribution;
	double logProbability$m;
	double logProbability$metric_g;
	double logProbability$metric_mean;
	double logProbability$metric_valid_1d;
	double logProbability$metric_valid_bias;
	double logProbability$metric_valid_g;
	double logProbability$metric_var;
	double[] logProbability$sample104;
	double[][] logProbability$sample123;
	double[][] logProbability$sample145;
	double[][] logProbability$sample157;
	double logProbability$st;
	double logProbability$var151;
	double logProbability$var32;
	double logProbability$var51;
	double logProbability$var67;
	double logProbability$var83;
	double[][] m;
	double[][] metric;
	double[][] metric_g;
	double[] metric_mean;
	boolean[][] metric_valid;
	double[] metric_valid_bias;
	boolean[][] metric_valid_g;
	double[] metric_var;
	int noSamples;
	int noStates;
	int[][] st;
	boolean system$gibbsForward = true;
	double[] v;
	double[][] var151;
	double[] cv$distributionAccumulator$var120;
	double[] cv$var102$stateProbabilityGlobal;
	double[] cv$var121$stateProbabilityGlobal;
	double[] cv$var19$countGlobal;
	double[] cv$var32$countGlobal;
	boolean[][] guard$sample104gaussian156$global;
	boolean[][] guard$sample123gaussian156$global;

	public HMMMetrics2$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][] get$distribution$sample104() {
		return distribution$sample104;
	}

	@Override
	public final void set$distribution$sample104(double[][] cv$value, boolean allocated$) {
		distribution$sample104 = cv$value;
	}

	@Override
	public final double[][][] get$distribution$sample123() {
		return distribution$sample123;
	}

	@Override
	public final void set$distribution$sample123(double[][][] cv$value, boolean allocated$) {
		distribution$sample123 = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample104() {
		return fixedFlag$sample104;
	}

	@Override
	public final void set$fixedFlag$sample104(boolean cv$value, boolean allocated$) {
		fixedFlag$sample104 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample104$1 = 0; index$constrainedFlag$sample104$1 < constrainedFlag$sample104.length; index$constrainedFlag$sample104$1 += 1)
				constrainedFlag$sample104[index$constrainedFlag$sample104$1] = true;
		}
		fixedProbFlag$sample104 = (fixedFlag$sample104 && fixedProbFlag$sample104);
		fixedProbFlag$sample123 = (fixedFlag$sample104 && fixedProbFlag$sample123);
		fixedProbFlag$sample145 = (fixedFlag$sample104 && fixedProbFlag$sample145);
		fixedProbFlag$sample157 = (fixedFlag$sample104 && fixedProbFlag$sample157);
	}

	@Override
	public final boolean get$fixedFlag$sample123() {
		return fixedFlag$sample123;
	}

	@Override
	public final void set$fixedFlag$sample123(boolean cv$value, boolean allocated$) {
		fixedFlag$sample123 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample123$1 = 0; index$constrainedFlag$sample123$1 < constrainedFlag$sample123.length; index$constrainedFlag$sample123$1 += 1) {
				boolean[] cv$constrainedFlag$sample123$1 = constrainedFlag$sample123[index$constrainedFlag$sample123$1];
				for(int index$constrainedFlag$sample123$2 = 0; index$constrainedFlag$sample123$2 < cv$constrainedFlag$sample123$1.length; index$constrainedFlag$sample123$2 += 1)
					cv$constrainedFlag$sample123$1[index$constrainedFlag$sample123$2] = true;
			}
		}
		fixedProbFlag$sample123 = (fixedFlag$sample123 && fixedProbFlag$sample123);
		fixedProbFlag$sample145 = (fixedFlag$sample123 && fixedProbFlag$sample145);
		fixedProbFlag$sample157 = (fixedFlag$sample123 && fixedProbFlag$sample157);
	}

	@Override
	public final boolean get$fixedFlag$sample157() {
		return fixedFlag$sample157;
	}

	@Override
	public final void set$fixedFlag$sample157(boolean cv$value, boolean allocated$) {
		fixedFlag$sample157 = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample19() {
		return fixedFlag$sample19;
	}

	@Override
	public final void set$fixedFlag$sample19(boolean cv$value, boolean allocated$) {
		fixedFlag$sample19 = cv$value;
		constrainedFlag$sample19 = (fixedFlag$sample19 || constrainedFlag$sample19);
		fixedProbFlag$sample19 = (fixedFlag$sample19 && fixedProbFlag$sample19);
		fixedProbFlag$sample104 = (fixedFlag$sample19 && fixedProbFlag$sample104);
	}

	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	@Override
	public final void set$fixedFlag$sample32(boolean cv$value, boolean allocated$) {
		fixedFlag$sample32 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample32$1 = 0; index$constrainedFlag$sample32$1 < constrainedFlag$sample32.length; index$constrainedFlag$sample32$1 += 1)
				constrainedFlag$sample32[index$constrainedFlag$sample32$1] = true;
		}
		fixedProbFlag$sample32 = (fixedFlag$sample32 && fixedProbFlag$sample32);
		fixedProbFlag$sample123 = (fixedFlag$sample32 && fixedProbFlag$sample123);
	}

	@Override
	public final boolean get$fixedFlag$sample52() {
		return fixedFlag$sample52;
	}

	@Override
	public final void set$fixedFlag$sample52(boolean cv$value, boolean allocated$) {
		fixedFlag$sample52 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample52$1 = 0; index$constrainedFlag$sample52$1 < constrainedFlag$sample52.length; index$constrainedFlag$sample52$1 += 1)
				constrainedFlag$sample52[index$constrainedFlag$sample52$1] = true;
		}
		fixedProbFlag$sample52 = (fixedFlag$sample52 && fixedProbFlag$sample52);
		fixedProbFlag$sample157 = (fixedFlag$sample52 && fixedProbFlag$sample157);
	}

	@Override
	public final boolean get$fixedFlag$sample68() {
		return fixedFlag$sample68;
	}

	@Override
	public final void set$fixedFlag$sample68(boolean cv$value, boolean allocated$) {
		fixedFlag$sample68 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample68$1 = 0; index$constrainedFlag$sample68$1 < constrainedFlag$sample68.length; index$constrainedFlag$sample68$1 += 1)
				constrainedFlag$sample68[index$constrainedFlag$sample68$1] = true;
		}
		fixedProbFlag$sample68 = (fixedFlag$sample68 && fixedProbFlag$sample68);
		fixedProbFlag$sample157 = (fixedFlag$sample68 && fixedProbFlag$sample157);
	}

	@Override
	public final boolean get$fixedFlag$sample84() {
		return fixedFlag$sample84;
	}

	@Override
	public final void set$fixedFlag$sample84(boolean cv$value, boolean allocated$) {
		fixedFlag$sample84 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample84$1 = 0; index$constrainedFlag$sample84$1 < constrainedFlag$sample84.length; index$constrainedFlag$sample84$1 += 1)
				constrainedFlag$sample84[index$constrainedFlag$sample84$1] = true;
		}
		fixedProbFlag$sample84 = (fixedFlag$sample84 && fixedProbFlag$sample84);
		fixedProbFlag$sample145 = (fixedFlag$sample84 && fixedProbFlag$sample145);
	}

	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	@Override
	public final void set$initialStateDistribution(double[] cv$value, boolean allocated$) {
		initialStateDistribution = cv$value;
		fixedProbFlag$sample19 = false;
		fixedProbFlag$sample104 = false;
	}

	@Override
	public final int[] get$length$metric() {
		return length$metric;
	}

	@Override
	public final void set$length$metric(int[] cv$value, boolean allocated$) {
		length$metric = cv$value;
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
	public final double get$logProbability$initialStateDistribution() {
		return logProbability$initialStateDistribution;
	}

	@Override
	public final double get$logProbability$m() {
		return logProbability$m;
	}

	@Override
	public final double get$logProbability$metric_g() {
		return logProbability$metric_g;
	}

	@Override
	public final double get$logProbability$metric_mean() {
		return logProbability$metric_mean;
	}

	@Override
	public final double get$logProbability$metric_valid_bias() {
		return logProbability$metric_valid_bias;
	}

	@Override
	public final double get$logProbability$metric_valid_g() {
		return logProbability$metric_valid_g;
	}

	@Override
	public final double get$logProbability$metric_var() {
		return logProbability$metric_var;
	}

	@Override
	public final double get$logProbability$st() {
		return logProbability$st;
	}

	@Override
	public final double[][] get$m() {
		return m;
	}

	@Override
	public final void set$m(double[][] cv$value, boolean allocated$) {
		m = cv$value;
		fixedProbFlag$sample32 = false;
		fixedProbFlag$sample123 = false;
	}

	@Override
	public final double[][] get$metric() {
		return metric;
	}

	@Override
	public final void set$metric(double[][] cv$value, boolean allocated$) {
		metric = cv$value;
	}

	@Override
	public final double[][] get$metric_g() {
		return metric_g;
	}

	@Override
	public final double[] get$metric_mean() {
		return metric_mean;
	}

	@Override
	public final void set$metric_mean(double[] cv$value, boolean allocated$) {
		metric_mean = cv$value;
		fixedProbFlag$sample52 = false;
		fixedProbFlag$sample157 = false;
	}

	@Override
	public final boolean[][] get$metric_valid() {
		return metric_valid;
	}

	@Override
	public final void set$metric_valid(boolean[][] cv$value, boolean allocated$) {
		metric_valid = cv$value;
	}

	@Override
	public final double[] get$metric_valid_bias() {
		return metric_valid_bias;
	}

	@Override
	public final void set$metric_valid_bias(double[] cv$value, boolean allocated$) {
		metric_valid_bias = cv$value;
		fixedProbFlag$sample84 = false;
		fixedProbFlag$sample145 = false;
	}

	@Override
	public final boolean[][] get$metric_valid_g() {
		return metric_valid_g;
	}

	@Override
	public final double[] get$metric_var() {
		return metric_var;
	}

	@Override
	public final void set$metric_var(double[] cv$value, boolean allocated$) {
		metric_var = cv$value;
		fixedProbFlag$sample68 = false;
		fixedProbFlag$sample157 = false;
	}

	@Override
	public final int get$noSamples() {
		return noSamples;
	}

	@Override
	public final int get$noStates() {
		return noStates;
	}

	@Override
	public final void set$noStates(int cv$value, boolean allocated$) {
		noStates = cv$value;
	}

	@Override
	public final int[][] get$st() {
		return st;
	}

	@Override
	public final void set$st(int[][] cv$value, boolean allocated$) {
		st = cv$value;
		fixedProbFlag$sample104 = false;
		fixedProbFlag$sample123 = false;
		fixedProbFlag$sample145 = false;
		fixedProbFlag$sample157 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void drawValueSample104(int sample) {
		int index$sample$1 = sample;
		int[] var99 = st[sample];
		var99[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
	}

	private final void drawValueSample123(int sample, int timeStep$var113) {
		int index$timeStep$1 = timeStep$var113;
		int index$sample$2 = sample;
		int[] var114 = st[sample];
		var114[timeStep$var113] = DistributionSampling.sampleCategorical(RNG$, m[st[sample][(timeStep$var113 - 1)]], noStates);
	}

	private final void drawValueSample19() {
		DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
	}

	private final void drawValueSample32(int var31) {
		double[] var32 = m[var31];
		DistributionSampling.sampleDirichlet(RNG$, v, noStates, var32);
	}

	private final void drawValueSample52(int var50) {
		metric_mean[var50] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
	}

	private final void drawValueSample68(int var66) {
		metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
	}

	private final void drawValueSample84(int var82) {
		metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	private final void inferSample104(int sample) {
		int index$sample$1 = sample;
		if(true) {
			constrainedFlag$sample104[((sample - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, noStates);
			}
			double[] cv$stateProbabilityLocal = cv$var102$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < noStates)) && (0 < noStates)) && (0.0 <= initialStateDistribution[cv$currentValue])) && (initialStateDistribution[cv$currentValue] <= 1.0))?Math.log(initialStateDistribution[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$var118$2_1 = cv$currentValue;
								for(int index$sample$2_2 = 0; index$sample$2_2 < noSamples; index$sample$2_2 += 1) {
									if((sample == index$sample$2_2)) {
										for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$2_2]; timeStep$var113 += 1) {
											if((0 == (timeStep$var113 - 1))) {
												if(fixedFlag$sample123) {
													{
														{
															int index$timeStep$4 = timeStep$var113;
															int index$sample$5 = index$sample$2_2;
															boolean cv$sampleConstrained = (fixedFlag$sample123 || constrainedFlag$sample123[((index$sample$2_2 - 0) / 1)][((timeStep$var113 - 1) / 1)]);
															if(cv$sampleConstrained) {
																constrainedFlag$sample104[((sample - 0) / 1)] = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		for(int var31 = 0; var31 < noStates; var31 += 1) {
																			if((var31 == traceTempVariable$var118$2_1)) {
																				{
																					{
																						{
																							double[] var119 = m[traceTempVariable$var118$2_1];
																							if(((Math.log(1.0) + ((((((0.0 <= st[index$sample$2_2][timeStep$var113]) && (st[index$sample$2_2][timeStep$var113] < noStates)) && (0 < noStates)) && (0.0 <= var119[st[index$sample$2_2][timeStep$var113]])) && (var119[st[index$sample$2_2][timeStep$var113]] <= 1.0))?Math.log(var119[st[index$sample$2_2][timeStep$var113]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= st[index$sample$2_2][timeStep$var113]) && (st[index$sample$2_2][timeStep$var113] < noStates)) && (0 < noStates)) && (0.0 <= var119[st[index$sample$2_2][timeStep$var113]])) && (var119[st[index$sample$2_2][timeStep$var113]] <= 1.0))?Math.log(var119[st[index$sample$2_2][timeStep$var113]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= st[index$sample$2_2][timeStep$var113]) && (st[index$sample$2_2][timeStep$var113] < noStates)) && (0 < noStates)) && (0.0 <= var119[st[index$sample$2_2][timeStep$var113]])) && (var119[st[index$sample$2_2][timeStep$var113]] <= 1.0))?Math.log(var119[st[index$sample$2_2][timeStep$var113]]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= st[index$sample$2_2][timeStep$var113]) && (st[index$sample$2_2][timeStep$var113] < noStates)) && (0 < noStates)) && (0.0 <= var119[st[index$sample$2_2][timeStep$var113]])) && (var119[st[index$sample$2_2][timeStep$var113]] <= 1.0))?Math.log(var119[st[index$sample$2_2][timeStep$var113]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= st[index$sample$2_2][timeStep$var113]) && (st[index$sample$2_2][timeStep$var113] < noStates)) && (0 < noStates)) && (0.0 <= var119[st[index$sample$2_2][timeStep$var113]])) && (var119[st[index$sample$2_2][timeStep$var113]] <= 1.0))?Math.log(var119[st[index$sample$2_2][timeStep$var113]]):Double.NEGATIVE_INFINITY)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
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
					}
					{
						{
							{
								int traceTempVariable$currentState$8_1 = cv$currentValue;
								for(int index$sample$8_2 = 0; index$sample$8_2 < noSamples; index$sample$8_2 += 1) {
									if((sample == index$sample$8_2)) {
										for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$8_2]; timeStep$var136 += 1) {
											if((0 == timeStep$var136)) {
												{
													{
														boolean cv$sampleConstrained = true;
														if(cv$sampleConstrained) {
															constrainedFlag$sample104[((sample - 0) / 1)] = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	for(int var82 = 0; var82 < noStates; var82 += 1) {
																		if((var82 == traceTempVariable$currentState$8_1)) {
																			{
																				{
																					{
																						double var139 = metric_valid_bias[traceTempVariable$currentState$8_1];
																						if(((Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$8_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$8_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$8_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$8_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$8_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																					}
																				}
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
					{
						{
							boolean[][] guard$sample104gaussian156 = guard$sample104gaussian156$global;
							{
								for(int index$sample$12_1 = 0; index$sample$12_1 < noSamples; index$sample$12_1 += 1) {
									if((sample == index$sample$12_1)) {
										for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$12_1]; timeStep$var136 += 1) {
											if((0 == timeStep$var136)) {
												if(metric_valid_g[index$sample$12_1][timeStep$var136])
													guard$sample104gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
											}
										}
									}
								}
							}
							{
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									if((sample == index$sample$13_1)) {
										for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$13_1]; timeStep$var136 += 1) {
											if((0 == timeStep$var136)) {
												if(metric_valid_g[index$sample$13_1][timeStep$var136])
													guard$sample104gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
											}
										}
									}
								}
							}
							{
								int traceTempVariable$currentState$14_1 = cv$currentValue;
								for(int index$sample$14_2 = 0; index$sample$14_2 < noSamples; index$sample$14_2 += 1) {
									if((sample == index$sample$14_2)) {
										for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$14_2]; timeStep$var136 += 1) {
											if((0 == timeStep$var136)) {
												if(metric_valid_g[index$sample$14_2][timeStep$var136]) {
													if(!guard$sample104gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
														guard$sample104gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
														{
															{
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	constrainedFlag$sample104[((sample - 0) / 1)] = true;
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			for(int var50 = 0; var50 < noStates; var50 += 1) {
																				if((var50 == traceTempVariable$currentState$14_1)) {
																					{
																						int traceTempVariable$currentState$19_1 = cv$currentValue;
																						if((index$sample$1 == index$sample$14_2)) {
																							if((0 == timeStep$var136)) {
																								{
																									for(int var66 = 0; var66 < noStates; var66 += 1) {
																										if((var66 == traceTempVariable$currentState$19_1)) {
																											{
																												{
																													{
																														double var148 = metric_mean[traceTempVariable$currentState$19_1];
																														double var149 = metric_var[traceTempVariable$currentState$19_1];
																														if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$20 = 0; index$sample$20 < noSamples; index$sample$20 += 1) {
																						if(!(index$sample$20 == index$sample$1)) {
																							for(int index$sample104$21 = 0; index$sample104$21 < noStates; index$sample104$21 += 1) {
																								int distributionTempVariable$var102$23 = index$sample104$21;
																								double cv$probabilitySample104Value22 = (1.0 * distribution$sample104[((index$sample$20 - 0) / 1)][index$sample104$21]);
																								{
																									int traceTempVariable$currentState$24_1 = distributionTempVariable$var102$23;
																									if((index$sample$20 == index$sample$14_2)) {
																										if((0 == timeStep$var136)) {
																											{
																												for(int var66 = 0; var66 < noStates; var66 += 1) {
																													if((var66 == traceTempVariable$currentState$24_1)) {
																														{
																															{
																																{
																																	double var148 = metric_mean[traceTempVariable$currentState$24_1];
																																	double var149 = metric_var[traceTempVariable$currentState$24_1];
																																	if(((Math.log(cv$probabilitySample104Value22) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value22) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value22) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value22) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value22) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value22);
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
																				}
																			}
																		}
																		{
																			for(int var50 = 0; var50 < noStates; var50 += 1) {
																				if((var50 == traceTempVariable$currentState$14_1)) {
																					if(fixedFlag$sample123) {
																						{
																							for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																								for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$28_1]; timeStep$var113 += 1) {
																									if((index$sample$28_1 == index$sample$14_2)) {
																										if((timeStep$var113 == timeStep$var136)) {
																											{
																												for(int var66 = 0; var66 < noStates; var66 += 1) {
																													if((var66 == traceTempVariable$currentState$14_1)) {
																														{
																															{
																																{
																																	double var148 = metric_mean[traceTempVariable$currentState$14_1];
																																	double var149 = metric_var[traceTempVariable$currentState$14_1];
																																	if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																					} else {
																						for(int index$sample$29 = 0; index$sample$29 < noSamples; index$sample$29 += 1) {
																							for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$29]; timeStep$var113 += 1) {
																								if(true) {
																									for(int index$sample123$31 = 0; index$sample123$31 < noStates; index$sample123$31 += 1) {
																										int distributionTempVariable$var121$33 = index$sample123$31;
																										double cv$probabilitySample123Value32 = (1.0 * distribution$sample123[((index$sample$29 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$31]);
																										{
																											int traceTempVariable$currentState$34_1 = distributionTempVariable$var121$33;
																											if((index$sample$29 == index$sample$14_2)) {
																												if((timeStep$var113 == timeStep$var136)) {
																													{
																														for(int var66 = 0; var66 < noStates; var66 += 1) {
																															if((var66 == traceTempVariable$currentState$34_1)) {
																																{
																																	{
																																		{
																																			double var148 = metric_mean[traceTempVariable$currentState$34_1];
																																			double var149 = metric_var[traceTempVariable$currentState$34_1];
																																			if(((Math.log(cv$probabilitySample123Value32) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value32) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value32) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value32) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value32) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value32);
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
																						}
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
							{
								int traceTempVariable$currentState$15_1 = cv$currentValue;
								for(int index$sample$15_2 = 0; index$sample$15_2 < noSamples; index$sample$15_2 += 1) {
									if((sample == index$sample$15_2)) {
										for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$15_2]; timeStep$var136 += 1) {
											if((0 == timeStep$var136)) {
												if(metric_valid_g[index$sample$15_2][timeStep$var136]) {
													if(!guard$sample104gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
														guard$sample104gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
														{
															{
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	constrainedFlag$sample104[((sample - 0) / 1)] = true;
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			int traceTempVariable$currentState$37_1 = cv$currentValue;
																			if((index$sample$1 == index$sample$15_2)) {
																				if((0 == timeStep$var136)) {
																					{
																						for(int var50 = 0; var50 < noStates; var50 += 1) {
																							if((var50 == traceTempVariable$currentState$37_1)) {
																								{
																									for(int var66 = 0; var66 < noStates; var66 += 1) {
																										if((var66 == traceTempVariable$currentState$37_1)) {
																											{
																												{
																													{
																														double var148 = metric_mean[traceTempVariable$currentState$37_1];
																														double var149 = metric_var[traceTempVariable$currentState$37_1];
																														if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																		for(int index$sample$38 = 0; index$sample$38 < noSamples; index$sample$38 += 1) {
																			if(!(index$sample$38 == index$sample$1)) {
																				for(int index$sample104$39 = 0; index$sample104$39 < noStates; index$sample104$39 += 1) {
																					int distributionTempVariable$var102$41 = index$sample104$39;
																					double cv$probabilitySample104Value40 = (1.0 * distribution$sample104[((index$sample$38 - 0) / 1)][index$sample104$39]);
																					{
																						int traceTempVariable$currentState$42_1 = distributionTempVariable$var102$41;
																						if((index$sample$38 == index$sample$15_2)) {
																							if((0 == timeStep$var136)) {
																								{
																									for(int var50 = 0; var50 < noStates; var50 += 1) {
																										if((var50 == traceTempVariable$currentState$42_1)) {
																											{
																												for(int var66 = 0; var66 < noStates; var66 += 1) {
																													if((var66 == traceTempVariable$currentState$42_1)) {
																														{
																															{
																																{
																																	double var148 = metric_mean[traceTempVariable$currentState$42_1];
																																	double var149 = metric_var[traceTempVariable$currentState$42_1];
																																	if(((Math.log(cv$probabilitySample104Value40) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value40) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value40) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value40) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value40) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value40);
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
																				}
																			}
																		}
																		if(fixedFlag$sample123) {
																			{
																				for(int index$sample$47_1 = 0; index$sample$47_1 < noSamples; index$sample$47_1 += 1) {
																					for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$47_1]; timeStep$var113 += 1) {
																						if((index$sample$47_1 == index$sample$15_2)) {
																							if((timeStep$var113 == timeStep$var136)) {
																								{
																									for(int var50 = 0; var50 < noStates; var50 += 1) {
																										if((var50 == traceTempVariable$currentState$15_1)) {
																											{
																												for(int var66 = 0; var66 < noStates; var66 += 1) {
																													if((var66 == traceTempVariable$currentState$15_1)) {
																														{
																															{
																																{
																																	double var148 = metric_mean[traceTempVariable$currentState$15_1];
																																	double var149 = metric_var[traceTempVariable$currentState$15_1];
																																	if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																				}
																			}
																		} else {
																			for(int index$sample$48 = 0; index$sample$48 < noSamples; index$sample$48 += 1) {
																				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$48]; timeStep$var113 += 1) {
																					if(true) {
																						for(int index$sample123$50 = 0; index$sample123$50 < noStates; index$sample123$50 += 1) {
																							int distributionTempVariable$var121$52 = index$sample123$50;
																							double cv$probabilitySample123Value51 = (1.0 * distribution$sample123[((index$sample$48 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$50]);
																							{
																								int traceTempVariable$currentState$53_1 = distributionTempVariable$var121$52;
																								if((index$sample$48 == index$sample$15_2)) {
																									if((timeStep$var113 == timeStep$var136)) {
																										{
																											for(int var50 = 0; var50 < noStates; var50 += 1) {
																												if((var50 == traceTempVariable$currentState$53_1)) {
																													{
																														for(int var66 = 0; var66 < noStates; var66 += 1) {
																															if((var66 == traceTempVariable$currentState$53_1)) {
																																{
																																	{
																																		{
																																			double var148 = metric_mean[traceTempVariable$currentState$53_1];
																																			double var149 = metric_var[traceTempVariable$currentState$53_1];
																																			if(((Math.log(cv$probabilitySample123Value51) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value51) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value51) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value51) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value51) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value51);
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
																						}
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
				{
					{
						{
							int traceTempVariable$var118$66_1 = cv$currentValue;
							for(int index$sample$66_2 = 0; index$sample$66_2 < noSamples; index$sample$66_2 += 1) {
								if((sample == index$sample$66_2)) {
									for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$66_2]; timeStep$var113 += 1) {
										if((0 == (timeStep$var113 - 1))) {
											if(!fixedFlag$sample123) {
												{
													{
														int index$timeStep$68 = timeStep$var113;
														int index$sample$69 = index$sample$66_2;
														double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var120;
														for(int cv$i = 0; cv$i < noStates; cv$i += 1)
															cv$accumulatedConsumerDistributions[cv$i] = 0.0;
														double cv$reachedDistributionProbability = 0.0;
														{
															for(int var31 = 0; var31 < noStates; var31 += 1) {
																if((var31 == traceTempVariable$var118$66_1)) {
																	{
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		double[] var119 = m[traceTempVariable$var118$66_1];
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																		cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
																		DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, var119, noStates);
																	}
																}
															}
														}
														double[] cv$sampleDistribution = distribution$sample123[((index$sample$66_2 - 0) / 1)][((timeStep$var113 - 1) / 1)];
														double cv$overlap = 0.0;
														for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
															double cv$normalisedDistValue = (cv$accumulatedConsumerDistributions[cv$i] / cv$reachedDistributionProbability);
															double cv$sampleDistValue = cv$sampleDistribution[cv$i];
															if((cv$sampleDistValue < cv$normalisedDistValue))
																cv$overlap = (cv$overlap + cv$sampleDistValue);
															else
																cv$overlap = (cv$overlap + cv$normalisedDistValue);
														}
														cv$accumulatedDistributionProbabilities = (cv$accumulatedDistributionProbabilities + Math.log(((cv$overlap * cv$reachedDistributionProbability) + (1.0 - Math.min(cv$reachedDistributionProbability, 1.0)))));
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
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(constrainedFlag$sample104[((sample - 0) / 1)]) {
				double[] cv$localProbability = distribution$sample104[((sample - 0) / 1)];
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
			}
		}
	}

	private final void inferSample123(int sample, int timeStep$var113) {
		int index$timeStep$1 = timeStep$var113;
		int index$sample$2 = sample;
		if(true) {
			constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = false;
			int cv$numStates = 0;
			if(fixedFlag$sample104) {
				{
					for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
						if((index$sample$3_1 == sample)) {
							if((0 == (timeStep$var113 - 1))) {
								{
									for(int var31 = 0; var31 < noStates; var31 += 1) {
										if((var31 == st[sample][(timeStep$var113 - 1)]))
											cv$numStates = Math.max(cv$numStates, noStates);
									}
								}
							}
						}
					}
				}
			} else {
				for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
					if(true) {
						for(int index$sample104$5 = 0; index$sample104$5 < noStates; index$sample104$5 += 1) {
							int distributionTempVariable$var102$7 = index$sample104$5;
							double cv$probabilitySample104Value6 = (1.0 * distribution$sample104[((index$sample$4 - 0) / 1)][index$sample104$5]);
							{
								int traceTempVariable$var118$8_1 = distributionTempVariable$var102$7;
								if((index$sample$4 == sample)) {
									if((0 == (timeStep$var113 - 1))) {
										{
											for(int var31 = 0; var31 < noStates; var31 += 1) {
												if((var31 == traceTempVariable$var118$8_1))
													cv$numStates = Math.max(cv$numStates, noStates);
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
				if((index$sample$2 == sample)) {
					if((index$timeStep$1 == (timeStep$var113 - 1))) {
						{
							for(int var31 = 0; var31 < noStates; var31 += 1) {
								if((var31 == st[sample][(timeStep$var113 - 1)]))
									cv$numStates = Math.max(cv$numStates, noStates);
							}
						}
					}
				}
			}
			for(int index$sample$12 = 0; index$sample$12 < noSamples; index$sample$12 += 1) {
				for(int index$timeStep$13 = 1; index$timeStep$13 < length$metric[index$sample$12]; index$timeStep$13 += 1) {
					if(!((index$timeStep$13 == index$timeStep$1) && (index$sample$12 == index$sample$2))) {
						for(int index$sample123$14 = 0; index$sample123$14 < noStates; index$sample123$14 += 1) {
							int distributionTempVariable$var121$16 = index$sample123$14;
							double cv$probabilitySample123Value15 = (1.0 * distribution$sample123[((index$sample$12 - 0) / 1)][((index$timeStep$13 - 1) / 1)][index$sample123$14]);
							{
								int traceTempVariable$var118$17_1 = distributionTempVariable$var121$16;
								if((index$sample$12 == sample)) {
									if((index$timeStep$13 == (timeStep$var113 - 1))) {
										{
											for(int var31 = 0; var31 < noStates; var31 += 1) {
												if((var31 == traceTempVariable$var118$17_1))
													cv$numStates = Math.max(cv$numStates, noStates);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			double[] cv$stateProbabilityLocal = cv$var121$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				if(fixedFlag$sample104) {
					{
						for(int index$sample$20_1 = 0; index$sample$20_1 < noSamples; index$sample$20_1 += 1) {
							if((index$sample$20_1 == sample)) {
								if((0 == (timeStep$var113 - 1))) {
									{
										for(int var31 = 0; var31 < noStates; var31 += 1) {
											if((var31 == st[sample][(timeStep$var113 - 1)])) {
												cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
												double[] var119 = m[st[sample][(timeStep$var113 - 1)]];
												double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < noStates)) && (0 < noStates)) && (0.0 <= var119[cv$currentValue])) && (var119[cv$currentValue] <= 1.0))?Math.log(var119[cv$currentValue]):Double.NEGATIVE_INFINITY));
												{
													{
														{
															int traceTempVariable$var118$37_1 = cv$currentValue;
														}
													}
												}
												{
													{
														{
															int traceTempVariable$currentState$41_1 = cv$currentValue;
															for(int index$sample$41_2 = 0; index$sample$41_2 < noSamples; index$sample$41_2 += 1) {
																if((sample == index$sample$41_2)) {
																	for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$41_2]; timeStep$var136 += 1) {
																		if((timeStep$var113 == timeStep$var136)) {
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								for(int var82 = 0; var82 < noStates; var82 += 1) {
																									if((var82 == traceTempVariable$currentState$41_1)) {
																										{
																											{
																												{
																													double var139 = metric_valid_bias[traceTempVariable$currentState$41_1];
																													if(((Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$41_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$41_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$41_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$41_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$41_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																												}
																											}
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
												{
													{
														boolean[][] guard$sample123gaussian156 = guard$sample123gaussian156$global;
														{
															for(int index$sample$57_1 = 0; index$sample$57_1 < noSamples; index$sample$57_1 += 1) {
																if((sample == index$sample$57_1)) {
																	for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$57_1]; timeStep$var136 += 1) {
																		if((timeStep$var113 == timeStep$var136)) {
																			if(metric_valid_g[index$sample$57_1][timeStep$var136])
																				guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
														{
															for(int index$sample$61_1 = 0; index$sample$61_1 < noSamples; index$sample$61_1 += 1) {
																if((sample == index$sample$61_1)) {
																	for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$61_1]; timeStep$var136 += 1) {
																		if((timeStep$var113 == timeStep$var136)) {
																			if(metric_valid_g[index$sample$61_1][timeStep$var136])
																				guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
														{
															int traceTempVariable$currentState$65_1 = cv$currentValue;
															for(int index$sample$65_2 = 0; index$sample$65_2 < noSamples; index$sample$65_2 += 1) {
																if((sample == index$sample$65_2)) {
																	for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$65_2]; timeStep$var136 += 1) {
																		if((timeStep$var113 == timeStep$var136)) {
																			if(metric_valid_g[index$sample$65_2][timeStep$var136]) {
																				if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																					guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																					{
																						{
																							boolean cv$sampleConstrained = true;
																							if(cv$sampleConstrained) {
																								constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																								double cv$consumerDistributionProbabilityAccumulator = 1.0;
																								{
																									{
																										for(int var50 = 0; var50 < noStates; var50 += 1) {
																											if((var50 == traceTempVariable$currentState$65_1)) {
																												{
																													for(int index$sample$82_1 = 0; index$sample$82_1 < noSamples; index$sample$82_1 += 1) {
																														if((index$sample$82_1 == index$sample$65_2)) {
																															if((0 == timeStep$var136)) {
																																{
																																	for(int var66 = 0; var66 < noStates; var66 += 1) {
																																		if((var66 == traceTempVariable$currentState$65_1)) {
																																			{
																																				{
																																					{
																																						double var148 = metric_mean[traceTempVariable$currentState$65_1];
																																						double var149 = metric_var[traceTempVariable$currentState$65_1];
																																						if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																									}
																									{
																										for(int var50 = 0; var50 < noStates; var50 += 1) {
																											if((var50 == traceTempVariable$currentState$65_1)) {
																												{
																													int traceTempVariable$currentState$85_1 = cv$currentValue;
																													if((index$sample$2 == index$sample$65_2)) {
																														if((index$timeStep$1 == timeStep$var136)) {
																															{
																																for(int var66 = 0; var66 < noStates; var66 += 1) {
																																	if((var66 == traceTempVariable$currentState$85_1)) {
																																		{
																																			{
																																				{
																																					double var148 = metric_mean[traceTempVariable$currentState$85_1];
																																					double var149 = metric_var[traceTempVariable$currentState$85_1];
																																					if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																												for(int index$sample$86 = 0; index$sample$86 < noSamples; index$sample$86 += 1) {
																													for(int index$timeStep$87 = 1; index$timeStep$87 < length$metric[index$sample$86]; index$timeStep$87 += 1) {
																														if(!((index$timeStep$87 == index$timeStep$1) && (index$sample$86 == index$sample$2))) {
																															for(int index$sample123$88 = 0; index$sample123$88 < noStates; index$sample123$88 += 1) {
																																int distributionTempVariable$var121$90 = index$sample123$88;
																																double cv$probabilitySample123Value89 = (1.0 * distribution$sample123[((index$sample$86 - 0) / 1)][((index$timeStep$87 - 1) / 1)][index$sample123$88]);
																																{
																																	int traceTempVariable$currentState$91_1 = distributionTempVariable$var121$90;
																																	if((index$sample$86 == index$sample$65_2)) {
																																		if((index$timeStep$87 == timeStep$var136)) {
																																			{
																																				for(int var66 = 0; var66 < noStates; var66 += 1) {
																																					if((var66 == traceTempVariable$currentState$91_1)) {
																																						{
																																							{
																																								{
																																									double var148 = metric_mean[traceTempVariable$currentState$91_1];
																																									double var149 = metric_var[traceTempVariable$currentState$91_1];
																																									if(((Math.log(cv$probabilitySample123Value89) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value89) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value89) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value89) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value89) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value89);
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
														{
															int traceTempVariable$currentState$69_1 = cv$currentValue;
															for(int index$sample$69_2 = 0; index$sample$69_2 < noSamples; index$sample$69_2 += 1) {
																if((sample == index$sample$69_2)) {
																	for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$69_2]; timeStep$var136 += 1) {
																		if((timeStep$var113 == timeStep$var136)) {
																			if(metric_valid_g[index$sample$69_2][timeStep$var136]) {
																				if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																					guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																					{
																						{
																							boolean cv$sampleConstrained = true;
																							if(cv$sampleConstrained) {
																								constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																								double cv$consumerDistributionProbabilityAccumulator = 1.0;
																								{
																									{
																										for(int index$sample$153_1 = 0; index$sample$153_1 < noSamples; index$sample$153_1 += 1) {
																											if((index$sample$153_1 == index$sample$69_2)) {
																												if((0 == timeStep$var136)) {
																													{
																														for(int var50 = 0; var50 < noStates; var50 += 1) {
																															if((var50 == traceTempVariable$currentState$69_1)) {
																																{
																																	for(int var66 = 0; var66 < noStates; var66 += 1) {
																																		if((var66 == traceTempVariable$currentState$69_1)) {
																																			{
																																				{
																																					{
																																						double var148 = metric_mean[traceTempVariable$currentState$69_1];
																																						double var149 = metric_var[traceTempVariable$currentState$69_1];
																																						if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																									}
																									{
																										int traceTempVariable$currentState$156_1 = cv$currentValue;
																										if((index$sample$2 == index$sample$69_2)) {
																											if((index$timeStep$1 == timeStep$var136)) {
																												{
																													for(int var50 = 0; var50 < noStates; var50 += 1) {
																														if((var50 == traceTempVariable$currentState$156_1)) {
																															{
																																for(int var66 = 0; var66 < noStates; var66 += 1) {
																																	if((var66 == traceTempVariable$currentState$156_1)) {
																																		{
																																			{
																																				{
																																					double var148 = metric_mean[traceTempVariable$currentState$156_1];
																																					double var149 = metric_var[traceTempVariable$currentState$156_1];
																																					if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																									for(int index$sample$157 = 0; index$sample$157 < noSamples; index$sample$157 += 1) {
																										for(int index$timeStep$158 = 1; index$timeStep$158 < length$metric[index$sample$157]; index$timeStep$158 += 1) {
																											if(!((index$timeStep$158 == index$timeStep$1) && (index$sample$157 == index$sample$2))) {
																												for(int index$sample123$159 = 0; index$sample123$159 < noStates; index$sample123$159 += 1) {
																													int distributionTempVariable$var121$161 = index$sample123$159;
																													double cv$probabilitySample123Value160 = (1.0 * distribution$sample123[((index$sample$157 - 0) / 1)][((index$timeStep$158 - 1) / 1)][index$sample123$159]);
																													{
																														int traceTempVariable$currentState$162_1 = distributionTempVariable$var121$161;
																														if((index$sample$157 == index$sample$69_2)) {
																															if((index$timeStep$158 == timeStep$var136)) {
																																{
																																	for(int var50 = 0; var50 < noStates; var50 += 1) {
																																		if((var50 == traceTempVariable$currentState$162_1)) {
																																			{
																																				for(int var66 = 0; var66 < noStates; var66 += 1) {
																																					if((var66 == traceTempVariable$currentState$162_1)) {
																																						{
																																							{
																																								{
																																									double var148 = metric_mean[traceTempVariable$currentState$162_1];
																																									double var149 = metric_var[traceTempVariable$currentState$162_1];
																																									if(((Math.log(cv$probabilitySample123Value160) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value160) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value160) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value160) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value160) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value160);
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
										}
									}
								}
							}
						}
					}
				} else {
					for(int index$sample$21 = 0; index$sample$21 < noSamples; index$sample$21 += 1) {
						if(true) {
							for(int index$sample104$22 = 0; index$sample104$22 < noStates; index$sample104$22 += 1) {
								int distributionTempVariable$var102$24 = index$sample104$22;
								double cv$probabilitySample104Value23 = (1.0 * distribution$sample104[((index$sample$21 - 0) / 1)][index$sample104$22]);
								{
									int traceTempVariable$var118$25_1 = distributionTempVariable$var102$24;
									if((index$sample$21 == sample)) {
										if((0 == (timeStep$var113 - 1))) {
											{
												for(int var31 = 0; var31 < noStates; var31 += 1) {
													if((var31 == traceTempVariable$var118$25_1)) {
														cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample104Value23);
														double[] var119 = m[traceTempVariable$var118$25_1];
														double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample104Value23) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < noStates)) && (0 < noStates)) && (0.0 <= var119[cv$currentValue])) && (var119[cv$currentValue] <= 1.0))?Math.log(var119[cv$currentValue]):Double.NEGATIVE_INFINITY));
														{
															{
																{
																	int traceTempVariable$var118$38_1 = cv$currentValue;
																}
															}
														}
														{
															{
																{
																	int traceTempVariable$currentState$42_1 = cv$currentValue;
																	for(int index$sample$42_2 = 0; index$sample$42_2 < noSamples; index$sample$42_2 += 1) {
																		if((sample == index$sample$42_2)) {
																			for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$42_2]; timeStep$var136 += 1) {
																				if((timeStep$var113 == timeStep$var136)) {
																					{
																						{
																							boolean cv$sampleConstrained = true;
																							if(cv$sampleConstrained) {
																								constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																								double cv$consumerDistributionProbabilityAccumulator = 1.0;
																								{
																									{
																										for(int var82 = 0; var82 < noStates; var82 += 1) {
																											if((var82 == traceTempVariable$currentState$42_1)) {
																												{
																													{
																														{
																															double var139 = metric_valid_bias[traceTempVariable$currentState$42_1];
																															if(((Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$42_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$42_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$42_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$42_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$42_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																														}
																													}
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
														{
															{
																boolean[][] guard$sample123gaussian156 = guard$sample123gaussian156$global;
																{
																	for(int index$sample$58_1 = 0; index$sample$58_1 < noSamples; index$sample$58_1 += 1) {
																		if((sample == index$sample$58_1)) {
																			for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$58_1]; timeStep$var136 += 1) {
																				if((timeStep$var113 == timeStep$var136)) {
																					if(metric_valid_g[index$sample$58_1][timeStep$var136])
																						guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
																				}
																			}
																		}
																	}
																}
																{
																	for(int index$sample$62_1 = 0; index$sample$62_1 < noSamples; index$sample$62_1 += 1) {
																		if((sample == index$sample$62_1)) {
																			for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$62_1]; timeStep$var136 += 1) {
																				if((timeStep$var113 == timeStep$var136)) {
																					if(metric_valid_g[index$sample$62_1][timeStep$var136])
																						guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
																				}
																			}
																		}
																	}
																}
																{
																	int traceTempVariable$currentState$66_1 = cv$currentValue;
																	for(int index$sample$66_2 = 0; index$sample$66_2 < noSamples; index$sample$66_2 += 1) {
																		if((sample == index$sample$66_2)) {
																			for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$66_2]; timeStep$var136 += 1) {
																				if((timeStep$var113 == timeStep$var136)) {
																					if(metric_valid_g[index$sample$66_2][timeStep$var136]) {
																						if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																							guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																							{
																								{
																									boolean cv$sampleConstrained = true;
																									if(cv$sampleConstrained) {
																										constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																										double cv$consumerDistributionProbabilityAccumulator = 1.0;
																										{
																											{
																												for(int var50 = 0; var50 < noStates; var50 += 1) {
																													if((var50 == traceTempVariable$currentState$66_1)) {
																														{
																															int traceTempVariable$currentState$95_1 = distributionTempVariable$var102$24;
																															if((index$sample$21 == index$sample$66_2)) {
																																if((0 == timeStep$var136)) {
																																	{
																																		for(int var66 = 0; var66 < noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$95_1)) {
																																				{
																																					{
																																						{
																																							double var148 = metric_mean[traceTempVariable$currentState$95_1];
																																							double var149 = metric_var[traceTempVariable$currentState$95_1];
																																							if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																														for(int index$sample$96 = 0; index$sample$96 < noSamples; index$sample$96 += 1) {
																															if(!(index$sample$96 == index$sample$21)) {
																																for(int index$sample104$97 = 0; index$sample104$97 < noStates; index$sample104$97 += 1) {
																																	int distributionTempVariable$var102$99 = index$sample104$97;
																																	double cv$probabilitySample104Value98 = (1.0 * distribution$sample104[((index$sample$96 - 0) / 1)][index$sample104$97]);
																																	{
																																		int traceTempVariable$currentState$100_1 = distributionTempVariable$var102$99;
																																		if((index$sample$96 == index$sample$66_2)) {
																																			if((0 == timeStep$var136)) {
																																				{
																																					for(int var66 = 0; var66 < noStates; var66 += 1) {
																																						if((var66 == traceTempVariable$currentState$100_1)) {
																																							{
																																								{
																																									{
																																										double var148 = metric_mean[traceTempVariable$currentState$100_1];
																																										double var149 = metric_var[traceTempVariable$currentState$100_1];
																																										if(((Math.log(cv$probabilitySample104Value98) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value98) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value98) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value98) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value98) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																										}
																																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value98);
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
																													}
																												}
																											}
																											{
																												for(int var50 = 0; var50 < noStates; var50 += 1) {
																													if((var50 == traceTempVariable$currentState$66_1)) {
																														{
																															int traceTempVariable$currentState$104_1 = cv$currentValue;
																															if((index$sample$2 == index$sample$66_2)) {
																																if((index$timeStep$1 == timeStep$var136)) {
																																	{
																																		for(int var66 = 0; var66 < noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$104_1)) {
																																				{
																																					{
																																						{
																																							double var148 = metric_mean[traceTempVariable$currentState$104_1];
																																							double var149 = metric_var[traceTempVariable$currentState$104_1];
																																							if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																														for(int index$sample$105 = 0; index$sample$105 < noSamples; index$sample$105 += 1) {
																															for(int index$timeStep$106 = 1; index$timeStep$106 < length$metric[index$sample$105]; index$timeStep$106 += 1) {
																																if(!((index$timeStep$106 == index$timeStep$1) && (index$sample$105 == index$sample$2))) {
																																	for(int index$sample123$107 = 0; index$sample123$107 < noStates; index$sample123$107 += 1) {
																																		int distributionTempVariable$var121$109 = index$sample123$107;
																																		double cv$probabilitySample123Value108 = (1.0 * distribution$sample123[((index$sample$105 - 0) / 1)][((index$timeStep$106 - 1) / 1)][index$sample123$107]);
																																		{
																																			int traceTempVariable$currentState$110_1 = distributionTempVariable$var121$109;
																																			if((index$sample$105 == index$sample$66_2)) {
																																				if((index$timeStep$106 == timeStep$var136)) {
																																					{
																																						for(int var66 = 0; var66 < noStates; var66 += 1) {
																																							if((var66 == traceTempVariable$currentState$110_1)) {
																																								{
																																									{
																																										{
																																											double var148 = metric_mean[traceTempVariable$currentState$110_1];
																																											double var149 = metric_var[traceTempVariable$currentState$110_1];
																																											if(((Math.log(cv$probabilitySample123Value108) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value108) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																											else {
																																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value108) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																												else
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value108) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value108) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																											}
																																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value108);
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
																{
																	int traceTempVariable$currentState$70_1 = cv$currentValue;
																	for(int index$sample$70_2 = 0; index$sample$70_2 < noSamples; index$sample$70_2 += 1) {
																		if((sample == index$sample$70_2)) {
																			for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$70_2]; timeStep$var136 += 1) {
																				if((timeStep$var113 == timeStep$var136)) {
																					if(metric_valid_g[index$sample$70_2][timeStep$var136]) {
																						if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																							guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																							{
																								{
																									boolean cv$sampleConstrained = true;
																									if(cv$sampleConstrained) {
																										constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																										double cv$consumerDistributionProbabilityAccumulator = 1.0;
																										{
																											{
																												int traceTempVariable$currentState$167_1 = distributionTempVariable$var102$24;
																												if((index$sample$21 == index$sample$70_2)) {
																													if((0 == timeStep$var136)) {
																														{
																															for(int var50 = 0; var50 < noStates; var50 += 1) {
																																if((var50 == traceTempVariable$currentState$167_1)) {
																																	{
																																		for(int var66 = 0; var66 < noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$167_1)) {
																																				{
																																					{
																																						{
																																							double var148 = metric_mean[traceTempVariable$currentState$167_1];
																																							double var149 = metric_var[traceTempVariable$currentState$167_1];
																																							if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																											for(int index$sample$168 = 0; index$sample$168 < noSamples; index$sample$168 += 1) {
																												if(!(index$sample$168 == index$sample$21)) {
																													for(int index$sample104$169 = 0; index$sample104$169 < noStates; index$sample104$169 += 1) {
																														int distributionTempVariable$var102$171 = index$sample104$169;
																														double cv$probabilitySample104Value170 = (1.0 * distribution$sample104[((index$sample$168 - 0) / 1)][index$sample104$169]);
																														{
																															int traceTempVariable$currentState$172_1 = distributionTempVariable$var102$171;
																															if((index$sample$168 == index$sample$70_2)) {
																																if((0 == timeStep$var136)) {
																																	{
																																		for(int var50 = 0; var50 < noStates; var50 += 1) {
																																			if((var50 == traceTempVariable$currentState$172_1)) {
																																				{
																																					for(int var66 = 0; var66 < noStates; var66 += 1) {
																																						if((var66 == traceTempVariable$currentState$172_1)) {
																																							{
																																								{
																																									{
																																										double var148 = metric_mean[traceTempVariable$currentState$172_1];
																																										double var149 = metric_var[traceTempVariable$currentState$172_1];
																																										if(((Math.log(cv$probabilitySample104Value170) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value170) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value170) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value170) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value170) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																										}
																																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value170);
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
																													}
																												}
																											}
																											{
																												int traceTempVariable$currentState$177_1 = cv$currentValue;
																												if((index$sample$2 == index$sample$70_2)) {
																													if((index$timeStep$1 == timeStep$var136)) {
																														{
																															for(int var50 = 0; var50 < noStates; var50 += 1) {
																																if((var50 == traceTempVariable$currentState$177_1)) {
																																	{
																																		for(int var66 = 0; var66 < noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$177_1)) {
																																				{
																																					{
																																						{
																																							double var148 = metric_mean[traceTempVariable$currentState$177_1];
																																							double var149 = metric_var[traceTempVariable$currentState$177_1];
																																							if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																											for(int index$sample$178 = 0; index$sample$178 < noSamples; index$sample$178 += 1) {
																												for(int index$timeStep$179 = 1; index$timeStep$179 < length$metric[index$sample$178]; index$timeStep$179 += 1) {
																													if(!((index$timeStep$179 == index$timeStep$1) && (index$sample$178 == index$sample$2))) {
																														for(int index$sample123$180 = 0; index$sample123$180 < noStates; index$sample123$180 += 1) {
																															int distributionTempVariable$var121$182 = index$sample123$180;
																															double cv$probabilitySample123Value181 = (1.0 * distribution$sample123[((index$sample$178 - 0) / 1)][((index$timeStep$179 - 1) / 1)][index$sample123$180]);
																															{
																																int traceTempVariable$currentState$183_1 = distributionTempVariable$var121$182;
																																if((index$sample$178 == index$sample$70_2)) {
																																	if((index$timeStep$179 == timeStep$var136)) {
																																		{
																																			for(int var50 = 0; var50 < noStates; var50 += 1) {
																																				if((var50 == traceTempVariable$currentState$183_1)) {
																																					{
																																						for(int var66 = 0; var66 < noStates; var66 += 1) {
																																							if((var66 == traceTempVariable$currentState$183_1)) {
																																								{
																																									{
																																										{
																																											double var148 = metric_mean[traceTempVariable$currentState$183_1];
																																											double var149 = metric_var[traceTempVariable$currentState$183_1];
																																											if(((Math.log(cv$probabilitySample123Value181) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value181) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																											else {
																																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value181) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																												else
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value181) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value181) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																											}
																																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value181);
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
					int traceTempVariable$var118$28_1 = cv$currentValue;
					if((index$sample$2 == sample)) {
						if((index$timeStep$1 == (timeStep$var113 - 1))) {
							{
								for(int var31 = 0; var31 < noStates; var31 += 1) {
									if((var31 == traceTempVariable$var118$28_1)) {
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
										double[] var119 = m[traceTempVariable$var118$28_1];
										double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < noStates)) && (0 < noStates)) && (0.0 <= var119[cv$currentValue])) && (var119[cv$currentValue] <= 1.0))?Math.log(var119[cv$currentValue]):Double.NEGATIVE_INFINITY));
										{
											{
												{
													int traceTempVariable$var118$39_1 = cv$currentValue;
												}
											}
										}
										{
											{
												{
													int traceTempVariable$currentState$43_1 = cv$currentValue;
													for(int index$sample$43_2 = 0; index$sample$43_2 < noSamples; index$sample$43_2 += 1) {
														if((sample == index$sample$43_2)) {
															for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$43_2]; timeStep$var136 += 1) {
																if((timeStep$var113 == timeStep$var136)) {
																	{
																		{
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						for(int var82 = 0; var82 < noStates; var82 += 1) {
																							if((var82 == traceTempVariable$currentState$43_1)) {
																								{
																									{
																										{
																											double var139 = metric_valid_bias[traceTempVariable$currentState$43_1];
																											if(((Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$43_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$43_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$43_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$43_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$43_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																										}
																									}
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
										{
											{
												boolean[][] guard$sample123gaussian156 = guard$sample123gaussian156$global;
												{
													for(int index$sample$59_1 = 0; index$sample$59_1 < noSamples; index$sample$59_1 += 1) {
														if((sample == index$sample$59_1)) {
															for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$59_1]; timeStep$var136 += 1) {
																if((timeStep$var113 == timeStep$var136)) {
																	if(metric_valid_g[index$sample$59_1][timeStep$var136])
																		guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
																}
															}
														}
													}
												}
												{
													for(int index$sample$63_1 = 0; index$sample$63_1 < noSamples; index$sample$63_1 += 1) {
														if((sample == index$sample$63_1)) {
															for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$63_1]; timeStep$var136 += 1) {
																if((timeStep$var113 == timeStep$var136)) {
																	if(metric_valid_g[index$sample$63_1][timeStep$var136])
																		guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
																}
															}
														}
													}
												}
												{
													int traceTempVariable$currentState$67_1 = cv$currentValue;
													for(int index$sample$67_2 = 0; index$sample$67_2 < noSamples; index$sample$67_2 += 1) {
														if((sample == index$sample$67_2)) {
															for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$67_2]; timeStep$var136 += 1) {
																if((timeStep$var113 == timeStep$var136)) {
																	if(metric_valid_g[index$sample$67_2][timeStep$var136]) {
																		if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																			guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								for(int var50 = 0; var50 < noStates; var50 += 1) {
																									if((var50 == traceTempVariable$currentState$67_1)) {
																										if(fixedFlag$sample104) {
																											{
																												for(int index$sample$114_1 = 0; index$sample$114_1 < noSamples; index$sample$114_1 += 1) {
																													if((index$sample$114_1 == index$sample$67_2)) {
																														if((0 == timeStep$var136)) {
																															{
																																for(int var66 = 0; var66 < noStates; var66 += 1) {
																																	if((var66 == traceTempVariable$currentState$67_1)) {
																																		{
																																			{
																																				{
																																					double var148 = metric_mean[traceTempVariable$currentState$67_1];
																																					double var149 = metric_var[traceTempVariable$currentState$67_1];
																																					if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																										} else {
																											for(int index$sample$115 = 0; index$sample$115 < noSamples; index$sample$115 += 1) {
																												if(true) {
																													for(int index$sample104$116 = 0; index$sample104$116 < noStates; index$sample104$116 += 1) {
																														int distributionTempVariable$var102$118 = index$sample104$116;
																														double cv$probabilitySample104Value117 = (1.0 * distribution$sample104[((index$sample$115 - 0) / 1)][index$sample104$116]);
																														{
																															int traceTempVariable$currentState$119_1 = distributionTempVariable$var102$118;
																															if((index$sample$115 == index$sample$67_2)) {
																																if((0 == timeStep$var136)) {
																																	{
																																		for(int var66 = 0; var66 < noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$119_1)) {
																																				{
																																					{
																																						{
																																							double var148 = metric_mean[traceTempVariable$currentState$119_1];
																																							double var149 = metric_var[traceTempVariable$currentState$119_1];
																																							if(((Math.log(cv$probabilitySample104Value117) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value117) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value117) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value117) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value117) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value117);
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
																										}
																									}
																								}
																							}
																							{
																								for(int var50 = 0; var50 < noStates; var50 += 1) {
																									if((var50 == traceTempVariable$currentState$67_1)) {
																										{
																											int traceTempVariable$currentState$123_1 = cv$currentValue;
																											if((index$sample$2 == index$sample$67_2)) {
																												if((index$timeStep$1 == timeStep$var136)) {
																													{
																														for(int var66 = 0; var66 < noStates; var66 += 1) {
																															if((var66 == traceTempVariable$currentState$123_1)) {
																																{
																																	{
																																		{
																																			double var148 = metric_mean[traceTempVariable$currentState$123_1];
																																			double var149 = metric_var[traceTempVariable$currentState$123_1];
																																			if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																										for(int index$sample$124 = 0; index$sample$124 < noSamples; index$sample$124 += 1) {
																											for(int index$timeStep$125 = 1; index$timeStep$125 < length$metric[index$sample$124]; index$timeStep$125 += 1) {
																												if(!((index$timeStep$125 == index$timeStep$1) && (index$sample$124 == index$sample$2))) {
																													for(int index$sample123$126 = 0; index$sample123$126 < noStates; index$sample123$126 += 1) {
																														int distributionTempVariable$var121$128 = index$sample123$126;
																														double cv$probabilitySample123Value127 = (1.0 * distribution$sample123[((index$sample$124 - 0) / 1)][((index$timeStep$125 - 1) / 1)][index$sample123$126]);
																														{
																															int traceTempVariable$currentState$129_1 = distributionTempVariable$var121$128;
																															if((index$sample$124 == index$sample$67_2)) {
																																if((index$timeStep$125 == timeStep$var136)) {
																																	{
																																		for(int var66 = 0; var66 < noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$129_1)) {
																																				{
																																					{
																																						{
																																							double var148 = metric_mean[traceTempVariable$currentState$129_1];
																																							double var149 = metric_var[traceTempVariable$currentState$129_1];
																																							if(((Math.log(cv$probabilitySample123Value127) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value127) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value127) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value127) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value127) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value127);
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
												{
													int traceTempVariable$currentState$71_1 = cv$currentValue;
													for(int index$sample$71_2 = 0; index$sample$71_2 < noSamples; index$sample$71_2 += 1) {
														if((sample == index$sample$71_2)) {
															for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$71_2]; timeStep$var136 += 1) {
																if((timeStep$var113 == timeStep$var136)) {
																	if(metric_valid_g[index$sample$71_2][timeStep$var136]) {
																		if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																			guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							if(fixedFlag$sample104) {
																								{
																									for(int index$sample$188_1 = 0; index$sample$188_1 < noSamples; index$sample$188_1 += 1) {
																										if((index$sample$188_1 == index$sample$71_2)) {
																											if((0 == timeStep$var136)) {
																												{
																													for(int var50 = 0; var50 < noStates; var50 += 1) {
																														if((var50 == traceTempVariable$currentState$71_1)) {
																															{
																																for(int var66 = 0; var66 < noStates; var66 += 1) {
																																	if((var66 == traceTempVariable$currentState$71_1)) {
																																		{
																																			{
																																				{
																																					double var148 = metric_mean[traceTempVariable$currentState$71_1];
																																					double var149 = metric_var[traceTempVariable$currentState$71_1];
																																					if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																								}
																							} else {
																								for(int index$sample$189 = 0; index$sample$189 < noSamples; index$sample$189 += 1) {
																									if(true) {
																										for(int index$sample104$190 = 0; index$sample104$190 < noStates; index$sample104$190 += 1) {
																											int distributionTempVariable$var102$192 = index$sample104$190;
																											double cv$probabilitySample104Value191 = (1.0 * distribution$sample104[((index$sample$189 - 0) / 1)][index$sample104$190]);
																											{
																												int traceTempVariable$currentState$193_1 = distributionTempVariable$var102$192;
																												if((index$sample$189 == index$sample$71_2)) {
																													if((0 == timeStep$var136)) {
																														{
																															for(int var50 = 0; var50 < noStates; var50 += 1) {
																																if((var50 == traceTempVariable$currentState$193_1)) {
																																	{
																																		for(int var66 = 0; var66 < noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$193_1)) {
																																				{
																																					{
																																						{
																																							double var148 = metric_mean[traceTempVariable$currentState$193_1];
																																							double var149 = metric_var[traceTempVariable$currentState$193_1];
																																							if(((Math.log(cv$probabilitySample104Value191) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value191) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value191) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value191) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value191) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value191);
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
																										}
																									}
																								}
																							}
																							{
																								int traceTempVariable$currentState$198_1 = cv$currentValue;
																								if((index$sample$2 == index$sample$71_2)) {
																									if((index$timeStep$1 == timeStep$var136)) {
																										{
																											for(int var50 = 0; var50 < noStates; var50 += 1) {
																												if((var50 == traceTempVariable$currentState$198_1)) {
																													{
																														for(int var66 = 0; var66 < noStates; var66 += 1) {
																															if((var66 == traceTempVariable$currentState$198_1)) {
																																{
																																	{
																																		{
																																			double var148 = metric_mean[traceTempVariable$currentState$198_1];
																																			double var149 = metric_var[traceTempVariable$currentState$198_1];
																																			if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																							for(int index$sample$199 = 0; index$sample$199 < noSamples; index$sample$199 += 1) {
																								for(int index$timeStep$200 = 1; index$timeStep$200 < length$metric[index$sample$199]; index$timeStep$200 += 1) {
																									if(!((index$timeStep$200 == index$timeStep$1) && (index$sample$199 == index$sample$2))) {
																										for(int index$sample123$201 = 0; index$sample123$201 < noStates; index$sample123$201 += 1) {
																											int distributionTempVariable$var121$203 = index$sample123$201;
																											double cv$probabilitySample123Value202 = (1.0 * distribution$sample123[((index$sample$199 - 0) / 1)][((index$timeStep$200 - 1) / 1)][index$sample123$201]);
																											{
																												int traceTempVariable$currentState$204_1 = distributionTempVariable$var121$203;
																												if((index$sample$199 == index$sample$71_2)) {
																													if((index$timeStep$200 == timeStep$var136)) {
																														{
																															for(int var50 = 0; var50 < noStates; var50 += 1) {
																																if((var50 == traceTempVariable$currentState$204_1)) {
																																	{
																																		for(int var66 = 0; var66 < noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$204_1)) {
																																				{
																																					{
																																						{
																																							double var148 = metric_mean[traceTempVariable$currentState$204_1];
																																							double var149 = metric_var[traceTempVariable$currentState$204_1];
																																							if(((Math.log(cv$probabilitySample123Value202) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value202) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value202) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value202) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value202) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value202);
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
								}
							}
						}
					}
				}
				for(int index$sample$29 = 0; index$sample$29 < noSamples; index$sample$29 += 1) {
					for(int index$timeStep$30 = 1; index$timeStep$30 < length$metric[index$sample$29]; index$timeStep$30 += 1) {
						if(!((index$timeStep$30 == index$timeStep$1) && (index$sample$29 == index$sample$2))) {
							for(int index$sample123$31 = 0; index$sample123$31 < noStates; index$sample123$31 += 1) {
								int distributionTempVariable$var121$33 = index$sample123$31;
								double cv$probabilitySample123Value32 = (1.0 * distribution$sample123[((index$sample$29 - 0) / 1)][((index$timeStep$30 - 1) / 1)][index$sample123$31]);
								{
									int traceTempVariable$var118$34_1 = distributionTempVariable$var121$33;
									if((index$sample$29 == sample)) {
										if((index$timeStep$30 == (timeStep$var113 - 1))) {
											{
												for(int var31 = 0; var31 < noStates; var31 += 1) {
													if((var31 == traceTempVariable$var118$34_1)) {
														cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample123Value32);
														double[] var119 = m[traceTempVariable$var118$34_1];
														double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample123Value32) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < noStates)) && (0 < noStates)) && (0.0 <= var119[cv$currentValue])) && (var119[cv$currentValue] <= 1.0))?Math.log(var119[cv$currentValue]):Double.NEGATIVE_INFINITY));
														{
															{
																{
																	int traceTempVariable$var118$40_1 = distributionTempVariable$var121$33;
																}
															}
														}
														{
															{
																{
																	int traceTempVariable$currentState$44_1 = distributionTempVariable$var121$33;
																	for(int index$sample$44_2 = 0; index$sample$44_2 < noSamples; index$sample$44_2 += 1) {
																		if((sample == index$sample$44_2)) {
																			for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$44_2]; timeStep$var136 += 1) {
																				if((timeStep$var113 == timeStep$var136)) {
																					{
																						{
																							boolean cv$sampleConstrained = true;
																							if(cv$sampleConstrained) {
																								constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																								double cv$consumerDistributionProbabilityAccumulator = 1.0;
																								{
																									{
																										for(int var82 = 0; var82 < noStates; var82 += 1) {
																											if((var82 == traceTempVariable$currentState$44_1)) {
																												{
																													{
																														{
																															double var139 = metric_valid_bias[traceTempVariable$currentState$44_1];
																															if(((Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$44_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$44_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$44_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$44_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[index$sample$44_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																														}
																													}
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
														{
															{
																boolean[][] guard$sample123gaussian156 = guard$sample123gaussian156$global;
																{
																	for(int index$sample$60_1 = 0; index$sample$60_1 < noSamples; index$sample$60_1 += 1) {
																		if((sample == index$sample$60_1)) {
																			for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$60_1]; timeStep$var136 += 1) {
																				if((timeStep$var113 == timeStep$var136)) {
																					if(metric_valid_g[index$sample$60_1][timeStep$var136])
																						guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
																				}
																			}
																		}
																	}
																}
																{
																	for(int index$sample$64_1 = 0; index$sample$64_1 < noSamples; index$sample$64_1 += 1) {
																		if((sample == index$sample$64_1)) {
																			for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$64_1]; timeStep$var136 += 1) {
																				if((timeStep$var113 == timeStep$var136)) {
																					if(metric_valid_g[index$sample$64_1][timeStep$var136])
																						guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
																				}
																			}
																		}
																	}
																}
																{
																	int traceTempVariable$currentState$68_1 = distributionTempVariable$var121$33;
																	for(int index$sample$68_2 = 0; index$sample$68_2 < noSamples; index$sample$68_2 += 1) {
																		if((sample == index$sample$68_2)) {
																			for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$68_2]; timeStep$var136 += 1) {
																				if((timeStep$var113 == timeStep$var136)) {
																					if(metric_valid_g[index$sample$68_2][timeStep$var136]) {
																						if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																							guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																							{
																								{
																									boolean cv$sampleConstrained = true;
																									if(cv$sampleConstrained) {
																										constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																										double cv$consumerDistributionProbabilityAccumulator = 1.0;
																										{
																											{
																												for(int var50 = 0; var50 < noStates; var50 += 1) {
																													if((var50 == traceTempVariable$currentState$68_1)) {
																														if(fixedFlag$sample104) {
																															{
																																for(int index$sample$133_1 = 0; index$sample$133_1 < noSamples; index$sample$133_1 += 1) {
																																	if((index$sample$133_1 == index$sample$68_2)) {
																																		if((0 == timeStep$var136)) {
																																			{
																																				for(int var66 = 0; var66 < noStates; var66 += 1) {
																																					if((var66 == traceTempVariable$currentState$68_1)) {
																																						{
																																							{
																																								{
																																									double var148 = metric_mean[traceTempVariable$currentState$68_1];
																																									double var149 = metric_var[traceTempVariable$currentState$68_1];
																																									if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																														} else {
																															for(int index$sample$134 = 0; index$sample$134 < noSamples; index$sample$134 += 1) {
																																if(true) {
																																	for(int index$sample104$135 = 0; index$sample104$135 < noStates; index$sample104$135 += 1) {
																																		int distributionTempVariable$var102$137 = index$sample104$135;
																																		double cv$probabilitySample104Value136 = (1.0 * distribution$sample104[((index$sample$134 - 0) / 1)][index$sample104$135]);
																																		{
																																			int traceTempVariable$currentState$138_1 = distributionTempVariable$var102$137;
																																			if((index$sample$134 == index$sample$68_2)) {
																																				if((0 == timeStep$var136)) {
																																					{
																																						for(int var66 = 0; var66 < noStates; var66 += 1) {
																																							if((var66 == traceTempVariable$currentState$138_1)) {
																																								{
																																									{
																																										{
																																											double var148 = metric_mean[traceTempVariable$currentState$138_1];
																																											double var149 = metric_var[traceTempVariable$currentState$138_1];
																																											if(((Math.log(cv$probabilitySample104Value136) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value136) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																											else {
																																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value136) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																												else
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value136) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value136) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																											}
																																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value136);
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
																														}
																													}
																												}
																											}
																											{
																												for(int var50 = 0; var50 < noStates; var50 += 1) {
																													if((var50 == traceTempVariable$currentState$68_1)) {
																														{
																															int traceTempVariable$currentState$142_1 = cv$currentValue;
																															if((index$sample$2 == index$sample$68_2)) {
																																if((index$timeStep$1 == timeStep$var136)) {
																																	{
																																		for(int var66 = 0; var66 < noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$142_1)) {
																																				{
																																					{
																																						{
																																							double var148 = metric_mean[traceTempVariable$currentState$142_1];
																																							double var149 = metric_var[traceTempVariable$currentState$142_1];
																																							if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																															int traceTempVariable$currentState$143_1 = distributionTempVariable$var121$33;
																															if((index$sample$29 == index$sample$68_2)) {
																																if((index$timeStep$30 == timeStep$var136)) {
																																	{
																																		for(int var66 = 0; var66 < noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$143_1)) {
																																				{
																																					{
																																						{
																																							double var148 = metric_mean[traceTempVariable$currentState$143_1];
																																							double var149 = metric_var[traceTempVariable$currentState$143_1];
																																							if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																														for(int index$sample$144 = 0; index$sample$144 < noSamples; index$sample$144 += 1) {
																															for(int index$timeStep$145 = 1; index$timeStep$145 < length$metric[index$sample$144]; index$timeStep$145 += 1) {
																																if((!((index$timeStep$145 == index$timeStep$1) && (index$sample$144 == index$sample$2)) && !((index$timeStep$145 == index$timeStep$30) && (index$sample$144 == index$sample$29)))) {
																																	for(int index$sample123$146 = 0; index$sample123$146 < noStates; index$sample123$146 += 1) {
																																		int distributionTempVariable$var121$148 = index$sample123$146;
																																		double cv$probabilitySample123Value147 = (1.0 * distribution$sample123[((index$sample$144 - 0) / 1)][((index$timeStep$145 - 1) / 1)][index$sample123$146]);
																																		{
																																			int traceTempVariable$currentState$149_1 = distributionTempVariable$var121$148;
																																			if((index$sample$144 == index$sample$68_2)) {
																																				if((index$timeStep$145 == timeStep$var136)) {
																																					{
																																						for(int var66 = 0; var66 < noStates; var66 += 1) {
																																							if((var66 == traceTempVariable$currentState$149_1)) {
																																								{
																																									{
																																										{
																																											double var148 = metric_mean[traceTempVariable$currentState$149_1];
																																											double var149 = metric_var[traceTempVariable$currentState$149_1];
																																											if(((Math.log(cv$probabilitySample123Value147) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value147) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																											else {
																																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value147) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																												else
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value147) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value147) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																											}
																																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value147);
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
																{
																	int traceTempVariable$currentState$72_1 = distributionTempVariable$var121$33;
																	for(int index$sample$72_2 = 0; index$sample$72_2 < noSamples; index$sample$72_2 += 1) {
																		if((sample == index$sample$72_2)) {
																			for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$72_2]; timeStep$var136 += 1) {
																				if((timeStep$var113 == timeStep$var136)) {
																					if(metric_valid_g[index$sample$72_2][timeStep$var136]) {
																						if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																							guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																							{
																								{
																									boolean cv$sampleConstrained = true;
																									if(cv$sampleConstrained) {
																										constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																										double cv$consumerDistributionProbabilityAccumulator = 1.0;
																										{
																											if(fixedFlag$sample104) {
																												{
																													for(int index$sample$209_1 = 0; index$sample$209_1 < noSamples; index$sample$209_1 += 1) {
																														if((index$sample$209_1 == index$sample$72_2)) {
																															if((0 == timeStep$var136)) {
																																{
																																	for(int var50 = 0; var50 < noStates; var50 += 1) {
																																		if((var50 == traceTempVariable$currentState$72_1)) {
																																			{
																																				for(int var66 = 0; var66 < noStates; var66 += 1) {
																																					if((var66 == traceTempVariable$currentState$72_1)) {
																																						{
																																							{
																																								{
																																									double var148 = metric_mean[traceTempVariable$currentState$72_1];
																																									double var149 = metric_var[traceTempVariable$currentState$72_1];
																																									if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																												}
																											} else {
																												for(int index$sample$210 = 0; index$sample$210 < noSamples; index$sample$210 += 1) {
																													if(true) {
																														for(int index$sample104$211 = 0; index$sample104$211 < noStates; index$sample104$211 += 1) {
																															int distributionTempVariable$var102$213 = index$sample104$211;
																															double cv$probabilitySample104Value212 = (1.0 * distribution$sample104[((index$sample$210 - 0) / 1)][index$sample104$211]);
																															{
																																int traceTempVariable$currentState$214_1 = distributionTempVariable$var102$213;
																																if((index$sample$210 == index$sample$72_2)) {
																																	if((0 == timeStep$var136)) {
																																		{
																																			for(int var50 = 0; var50 < noStates; var50 += 1) {
																																				if((var50 == traceTempVariable$currentState$214_1)) {
																																					{
																																						for(int var66 = 0; var66 < noStates; var66 += 1) {
																																							if((var66 == traceTempVariable$currentState$214_1)) {
																																								{
																																									{
																																										{
																																											double var148 = metric_mean[traceTempVariable$currentState$214_1];
																																											double var149 = metric_var[traceTempVariable$currentState$214_1];
																																											if(((Math.log(cv$probabilitySample104Value212) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value212) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																											else {
																																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value212) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																												else
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value212) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value212) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																											}
																																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value212);
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
																														}
																													}
																												}
																											}
																											{
																												int traceTempVariable$currentState$219_1 = cv$currentValue;
																												if((index$sample$2 == index$sample$72_2)) {
																													if((index$timeStep$1 == timeStep$var136)) {
																														{
																															for(int var50 = 0; var50 < noStates; var50 += 1) {
																																if((var50 == traceTempVariable$currentState$219_1)) {
																																	{
																																		for(int var66 = 0; var66 < noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$219_1)) {
																																				{
																																					{
																																						{
																																							double var148 = metric_mean[traceTempVariable$currentState$219_1];
																																							double var149 = metric_var[traceTempVariable$currentState$219_1];
																																							if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																											{
																												int traceTempVariable$currentState$220_1 = distributionTempVariable$var121$33;
																												if((index$sample$29 == index$sample$72_2)) {
																													if((index$timeStep$30 == timeStep$var136)) {
																														{
																															for(int var50 = 0; var50 < noStates; var50 += 1) {
																																if((var50 == traceTempVariable$currentState$220_1)) {
																																	{
																																		for(int var66 = 0; var66 < noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$220_1)) {
																																				{
																																					{
																																						{
																																							double var148 = metric_mean[traceTempVariable$currentState$220_1];
																																							double var149 = metric_var[traceTempVariable$currentState$220_1];
																																							if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																											for(int index$sample$221 = 0; index$sample$221 < noSamples; index$sample$221 += 1) {
																												for(int index$timeStep$222 = 1; index$timeStep$222 < length$metric[index$sample$221]; index$timeStep$222 += 1) {
																													if((!((index$timeStep$222 == index$timeStep$1) && (index$sample$221 == index$sample$2)) && !((index$timeStep$222 == index$timeStep$30) && (index$sample$221 == index$sample$29)))) {
																														for(int index$sample123$223 = 0; index$sample123$223 < noStates; index$sample123$223 += 1) {
																															int distributionTempVariable$var121$225 = index$sample123$223;
																															double cv$probabilitySample123Value224 = (1.0 * distribution$sample123[((index$sample$221 - 0) / 1)][((index$timeStep$222 - 1) / 1)][index$sample123$223]);
																															{
																																int traceTempVariable$currentState$226_1 = distributionTempVariable$var121$225;
																																if((index$sample$221 == index$sample$72_2)) {
																																	if((index$timeStep$222 == timeStep$var136)) {
																																		{
																																			for(int var50 = 0; var50 < noStates; var50 += 1) {
																																				if((var50 == traceTempVariable$currentState$226_1)) {
																																					{
																																						for(int var66 = 0; var66 < noStates; var66 += 1) {
																																							if((var66 == traceTempVariable$currentState$226_1)) {
																																								{
																																									{
																																										{
																																											double var148 = metric_mean[traceTempVariable$currentState$226_1];
																																											double var149 = metric_var[traceTempVariable$currentState$226_1];
																																											if(((Math.log(cv$probabilitySample123Value224) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value224) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																											else {
																																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value224) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																												else
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value224) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value224) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																											}
																																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value224);
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
					{
						{
							int traceTempVariable$var118$265_1 = cv$currentValue;
							for(int index$sample$265_2 = 0; index$sample$265_2 < noSamples; index$sample$265_2 += 1) {
								if((sample == index$sample$265_2)) {
									for(int index$timeStep$265_3 = 1; index$timeStep$265_3 < length$metric[index$sample$265_2]; index$timeStep$265_3 += 1) {
										if((timeStep$var113 == (index$timeStep$265_3 - 1))) {
											{
												{
													int index$timeStep$267 = index$timeStep$265_3;
													int index$sample$268 = index$sample$265_2;
													double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var120;
													for(int cv$i = 0; cv$i < noStates; cv$i += 1)
														cv$accumulatedConsumerDistributions[cv$i] = 0.0;
													double cv$reachedDistributionProbability = 0.0;
													{
														for(int var31 = 0; var31 < noStates; var31 += 1) {
															if((var31 == traceTempVariable$var118$265_1)) {
																{
																	double scopeVariable$reachedSourceProbability = 0.0;
																	if(fixedFlag$sample104) {
																		{
																			for(int index$sample$270_1 = 0; index$sample$270_1 < noSamples; index$sample$270_1 += 1) {
																				if((index$sample$270_1 == sample)) {
																					if((0 == (timeStep$var113 - 1))) {
																						{
																							for(int index$var31$276_1 = 0; index$var31$276_1 < noStates; index$var31$276_1 += 1) {
																								if((index$var31$276_1 == st[sample][(timeStep$var113 - 1)]))
																									scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																	} else {
																		for(int index$sample$271 = 0; index$sample$271 < noSamples; index$sample$271 += 1) {
																			if(true) {
																				for(int index$sample104$272 = 0; index$sample104$272 < noStates; index$sample104$272 += 1) {
																					int distributionTempVariable$var102$274 = index$sample104$272;
																					double cv$probabilitySample104Value273 = (1.0 * distribution$sample104[((index$sample$271 - 0) / 1)][index$sample104$272]);
																					{
																						int traceTempVariable$var118$275_1 = distributionTempVariable$var102$274;
																						if((index$sample$271 == sample)) {
																							if((0 == (timeStep$var113 - 1))) {
																								{
																									for(int index$var31$277_1 = 0; index$var31$277_1 < noStates; index$var31$277_1 += 1) {
																										if((index$var31$277_1 == traceTempVariable$var118$275_1))
																											scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample104Value273);
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
																		int traceTempVariable$var118$278_1 = cv$currentValue;
																		if((index$sample$2 == sample)) {
																			if((index$timeStep$1 == (timeStep$var113 - 1))) {
																				{
																					for(int index$var31$285_1 = 0; index$var31$285_1 < noStates; index$var31$285_1 += 1) {
																						if((index$var31$285_1 == traceTempVariable$var118$278_1))
																							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																					}
																				}
																			}
																		}
																	}
																	for(int index$sample$279 = 0; index$sample$279 < noSamples; index$sample$279 += 1) {
																		for(int index$timeStep$280 = 1; index$timeStep$280 < length$metric[index$sample$279]; index$timeStep$280 += 1) {
																			if((!((index$timeStep$280 == index$timeStep$1) && (index$sample$279 == index$sample$2)) && !((index$timeStep$280 == index$timeStep$267) && (index$sample$279 == index$sample$268)))) {
																				for(int index$sample123$281 = 0; index$sample123$281 < noStates; index$sample123$281 += 1) {
																					int distributionTempVariable$var121$283 = index$sample123$281;
																					double cv$probabilitySample123Value282 = (1.0 * distribution$sample123[((index$sample$279 - 0) / 1)][((index$timeStep$280 - 1) / 1)][index$sample123$281]);
																					{
																						int traceTempVariable$var118$284_1 = distributionTempVariable$var121$283;
																						if((index$sample$279 == sample)) {
																							if((index$timeStep$280 == (timeStep$var113 - 1))) {
																								{
																									for(int index$var31$286_1 = 0; index$var31$286_1 < noStates; index$var31$286_1 += 1) {
																										if((index$var31$286_1 == traceTempVariable$var118$284_1))
																											scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample123Value282);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																	double[] var119 = m[traceTempVariable$var118$265_1];
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																	cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
																	DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, var119, noStates);
																}
															}
														}
													}
													double[] cv$sampleDistribution = distribution$sample123[((index$sample$265_2 - 0) / 1)][((index$timeStep$265_3 - 1) / 1)];
													double cv$overlap = 0.0;
													for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
														double cv$normalisedDistValue = (cv$accumulatedConsumerDistributions[cv$i] / cv$reachedDistributionProbability);
														double cv$sampleDistValue = cv$sampleDistribution[cv$i];
														if((cv$sampleDistValue < cv$normalisedDistValue))
															cv$overlap = (cv$overlap + cv$sampleDistValue);
														else
															cv$overlap = (cv$overlap + cv$normalisedDistValue);
													}
													cv$accumulatedDistributionProbabilities = (cv$accumulatedDistributionProbabilities + Math.log(((cv$overlap * cv$reachedDistributionProbability) + (1.0 - Math.min(cv$reachedDistributionProbability, 1.0)))));
												}
											}
										}
									}
								}
							}
						}
					}
				}
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)]) {
				double[] cv$localProbability = distribution$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)];
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
			}
		}
	}

	private final void inferSample19() {
		if(true) {
			constrainedFlag$sample19 = false;
			double[] cv$targetLocal = initialStateDistribution;
			double[] cv$countLocal = cv$var19$countGlobal;
			int cv$arrayLength = noStates;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							for(int sample = 0; sample < noSamples; sample += 1) {
								if(fixedFlag$sample104) {
									{
										{
											int index$sample$3 = sample;
											boolean cv$sampleConstrained = (fixedFlag$sample104 || constrainedFlag$sample104[((sample - 0) / 1)]);
											if(cv$sampleConstrained) {
												constrainedFlag$sample19 = true;
												{
													{
														{
															{
																{
																	cv$countLocal[st[sample][0]] = (cv$countLocal[st[sample][0]] + 1.0);
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
				}
			}
			{
				{
					{
						for(int sample = 0; sample < noSamples; sample += 1) {
							if(!fixedFlag$sample104) {
								{
									{
										int index$sample$7 = sample;
										{
											{
												double scopeVariable$reachedSourceProbability = 0.0;
												{
													scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
												double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
												for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
													cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample104[((sample - 0) / 1)][cv$loopIndex] * cv$distributionProbability));
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(constrainedFlag$sample19)
				Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, noStates);
		}
	}

	private final void inferSample32(int var31) {
		if(true) {
			constrainedFlag$sample32[((var31 - 0) / 1)] = false;
			double[] cv$targetLocal = m[var31];
			double[] cv$countLocal = cv$var32$countGlobal;
			int cv$arrayLength = noStates;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						for(int sample = 0; sample < noSamples; sample += 1) {
							for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
								if(fixedFlag$sample104) {
									{
										for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
											if((index$sample$3_1 == sample)) {
												if((0 == (timeStep$var113 - 1))) {
													{
														if((var31 == st[sample][(timeStep$var113 - 1)])) {
															if(fixedFlag$sample123) {
																{
																	{
																		int index$timeStep$23 = timeStep$var113;
																		int index$sample$24 = sample;
																		boolean cv$sampleConstrained = (fixedFlag$sample123 || constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)]);
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample32[((var31 - 0) / 1)] = true;
																			{
																				{
																					{
																						{
																							{
																								cv$countLocal[st[sample][timeStep$var113]] = (cv$countLocal[st[sample][timeStep$var113]] + 1.0);
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
											}
										}
									}
								} else {
									for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
										if(true) {
											for(int index$sample104$5 = 0; index$sample104$5 < noStates; index$sample104$5 += 1) {
												int distributionTempVariable$var102$7 = index$sample104$5;
												double cv$probabilitySample104Value6 = (1.0 * distribution$sample104[((index$sample$4 - 0) / 1)][index$sample104$5]);
												{
													int traceTempVariable$var118$8_1 = distributionTempVariable$var102$7;
													if((index$sample$4 == sample)) {
														if((0 == (timeStep$var113 - 1))) {
															{
																if((var31 == traceTempVariable$var118$8_1)) {
																	if(fixedFlag$sample123) {
																		{
																			{
																				int index$timeStep$26 = timeStep$var113;
																				int index$sample$27 = sample;
																				boolean cv$sampleConstrained = (fixedFlag$sample123 || constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)]);
																				if(cv$sampleConstrained) {
																					constrainedFlag$sample32[((var31 - 0) / 1)] = true;
																					{
																						{
																							{
																								{
																									{
																										cv$countLocal[st[sample][timeStep$var113]] = (cv$countLocal[st[sample][timeStep$var113]] + cv$probabilitySample104Value6);
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
													}
												}
											}
										}
									}
								}
							}
						}
						for(int sample = 0; sample < noSamples; sample += 1) {
							for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
								if(fixedFlag$sample123) {
									{
										for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
											for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1]; index$timeStep$13_2 += 1) {
												if((index$sample$13_1 == sample)) {
													if((index$timeStep$13_2 == (timeStep$var113 - 1))) {
														{
															if((var31 == st[sample][(timeStep$var113 - 1)])) {
																if(fixedFlag$sample123) {
																	{
																		{
																			int index$timeStep$29 = timeStep$var113;
																			int index$sample$30 = sample;
																			boolean cv$sampleConstrained = (fixedFlag$sample123 || constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)]);
																			if(cv$sampleConstrained) {
																				constrainedFlag$sample32[((var31 - 0) / 1)] = true;
																				{
																					{
																						{
																							{
																								{
																									cv$countLocal[st[sample][timeStep$var113]] = (cv$countLocal[st[sample][timeStep$var113]] + 1.0);
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
												}
											}
										}
									}
								} else {
									for(int index$sample$14 = 0; index$sample$14 < noSamples; index$sample$14 += 1) {
										for(int index$timeStep$15 = 1; index$timeStep$15 < length$metric[index$sample$14]; index$timeStep$15 += 1) {
											if(true) {
												for(int index$sample123$16 = 0; index$sample123$16 < noStates; index$sample123$16 += 1) {
													int distributionTempVariable$var121$18 = index$sample123$16;
													double cv$probabilitySample123Value17 = (1.0 * distribution$sample123[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample123$16]);
													{
														int traceTempVariable$var118$19_1 = distributionTempVariable$var121$18;
														if((index$sample$14 == sample)) {
															if((index$timeStep$15 == (timeStep$var113 - 1))) {
																{
																	if((var31 == traceTempVariable$var118$19_1)) {
																		if(fixedFlag$sample123) {
																			{
																				{
																					int index$timeStep$32 = timeStep$var113;
																					int index$sample$33 = sample;
																					boolean cv$sampleConstrained = (fixedFlag$sample123 || constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)]);
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample32[((var31 - 0) / 1)] = true;
																						{
																							{
																								{
																									{
																										{
																											cv$countLocal[st[sample][timeStep$var113]] = (cv$countLocal[st[sample][timeStep$var113]] + cv$probabilitySample123Value17);
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
			{
				{
					for(int sample = 0; sample < noSamples; sample += 1) {
						for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
							if(fixedFlag$sample104) {
								{
									for(int index$sample$40_1 = 0; index$sample$40_1 < noSamples; index$sample$40_1 += 1) {
										if((index$sample$40_1 == sample)) {
											if((0 == (timeStep$var113 - 1))) {
												{
													if((var31 == st[sample][(timeStep$var113 - 1)])) {
														if(!fixedFlag$sample123) {
															{
																{
																	int index$timeStep$60 = timeStep$var113;
																	int index$sample$61 = sample;
																	{
																		{
																			double scopeVariable$reachedSourceProbability = 0.0;
																			{
																				scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																			}
																			double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																				cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
							} else {
								for(int index$sample$41 = 0; index$sample$41 < noSamples; index$sample$41 += 1) {
									if(true) {
										for(int index$sample104$42 = 0; index$sample104$42 < noStates; index$sample104$42 += 1) {
											int distributionTempVariable$var102$44 = index$sample104$42;
											double cv$probabilitySample104Value43 = (1.0 * distribution$sample104[((index$sample$41 - 0) / 1)][index$sample104$42]);
											{
												int traceTempVariable$var118$45_1 = distributionTempVariable$var102$44;
												if((index$sample$41 == sample)) {
													if((0 == (timeStep$var113 - 1))) {
														{
															if((var31 == traceTempVariable$var118$45_1)) {
																if(!fixedFlag$sample123) {
																	{
																		{
																			int index$timeStep$63 = timeStep$var113;
																			int index$sample$64 = sample;
																			{
																				{
																					double scopeVariable$reachedSourceProbability = 0.0;
																					{
																						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																					}
																					double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample104Value43);
																					for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																						cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
								}
							}
						}
					}
					for(int sample = 0; sample < noSamples; sample += 1) {
						for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
							if(fixedFlag$sample123) {
								{
									for(int index$sample$50_1 = 0; index$sample$50_1 < noSamples; index$sample$50_1 += 1) {
										for(int index$timeStep$50_2 = 1; index$timeStep$50_2 < length$metric[index$sample$50_1]; index$timeStep$50_2 += 1) {
											if((index$sample$50_1 == sample)) {
												if((index$timeStep$50_2 == (timeStep$var113 - 1))) {
													{
														if((var31 == st[sample][(timeStep$var113 - 1)])) {
															if(!fixedFlag$sample123) {
																{
																	{
																		int index$timeStep$66 = timeStep$var113;
																		int index$sample$67 = sample;
																		{
																			{
																				double scopeVariable$reachedSourceProbability = 0.0;
																				{
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																				}
																				double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																				for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																					cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
							} else {
								for(int index$sample$51 = 0; index$sample$51 < noSamples; index$sample$51 += 1) {
									for(int index$timeStep$52 = 1; index$timeStep$52 < length$metric[index$sample$51]; index$timeStep$52 += 1) {
										if(true) {
											for(int index$sample123$53 = 0; index$sample123$53 < noStates; index$sample123$53 += 1) {
												int distributionTempVariable$var121$55 = index$sample123$53;
												double cv$probabilitySample123Value54 = (1.0 * distribution$sample123[((index$sample$51 - 0) / 1)][((index$timeStep$52 - 1) / 1)][index$sample123$53]);
												{
													int traceTempVariable$var118$56_1 = distributionTempVariable$var121$55;
													if((index$sample$51 == sample)) {
														if((index$timeStep$52 == (timeStep$var113 - 1))) {
															{
																if((var31 == traceTempVariable$var118$56_1)) {
																	if(!fixedFlag$sample123) {
																		{
																			{
																				int index$timeStep$69 = timeStep$var113;
																				int index$sample$70 = sample;
																				{
																					{
																						double scopeVariable$reachedSourceProbability = 0.0;
																						{
																							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																						}
																						double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample123Value54);
																						for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
									}
								}
							}
						}
					}
				}
			}
			if(constrainedFlag$sample32[((var31 - 0) / 1)])
				Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, noStates);
		}
	}

	private final void inferSample52(int var50) {
		if(true) {
			constrainedFlag$sample52[((var50 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = metric_mean[var50];
			double cv$originalProbability = 0.0;
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((constrainedFlag$sample52[((var50 - 0) / 1)] || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						double var51 = cv$proposedValue;
						{
							{
								{
									metric_mean[var50] = cv$currentValue;
								}
							}
						}
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < 100.0))?(-Math.log((100.0 - 0.0))):Double.NEGATIVE_INFINITY));
						{
							{
								for(int sample = 0; sample < noSamples; sample += 1) {
									for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
										if(metric_valid_g[sample][timeStep$var136]) {
											if(fixedFlag$sample104) {
												{
													for(int index$sample$4_1 = 0; index$sample$4_1 < noSamples; index$sample$4_1 += 1) {
														if((index$sample$4_1 == sample)) {
															if((0 == timeStep$var136)) {
																{
																	double traceTempVariable$var148$10_1 = cv$currentValue;
																	if((var50 == st[sample][timeStep$var136])) {
																		{
																			{
																				boolean cv$sampleConstrained = true;
																				if(cv$sampleConstrained) {
																					constrainedFlag$sample52[((var50 - 0) / 1)] = true;
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							for(int index$sample$27_1 = 0; index$sample$27_1 < noSamples; index$sample$27_1 += 1) {
																								if((index$sample$27_1 == sample)) {
																									if((0 == timeStep$var136)) {
																										{
																											for(int var66 = 0; var66 < noStates; var66 += 1) {
																												if((var66 == st[sample][timeStep$var136])) {
																													{
																														{
																															{
																																double var149 = metric_var[st[sample][timeStep$var136]];
																																if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																						if(fixedFlag$sample123) {
																							{
																								for(int index$sample$29_1 = 0; index$sample$29_1 < noSamples; index$sample$29_1 += 1) {
																									for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$29_1]; timeStep$var113 += 1) {
																										if((index$sample$29_1 == sample)) {
																											if((timeStep$var113 == timeStep$var136)) {
																												{
																													for(int var66 = 0; var66 < noStates; var66 += 1) {
																														if((var66 == st[sample][timeStep$var136])) {
																															{
																																{
																																	{
																																		double var149 = metric_var[st[sample][timeStep$var136]];
																																		if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																						} else {
																							for(int index$sample$30 = 0; index$sample$30 < noSamples; index$sample$30 += 1) {
																								for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$30]; timeStep$var113 += 1) {
																									if(true) {
																										for(int index$sample123$32 = 0; index$sample123$32 < noStates; index$sample123$32 += 1) {
																											int distributionTempVariable$var121$34 = index$sample123$32;
																											double cv$probabilitySample123Value33 = (1.0 * distribution$sample123[((index$sample$30 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$32]);
																											{
																												int traceTempVariable$currentState$35_1 = distributionTempVariable$var121$34;
																												if((index$sample$30 == sample)) {
																													if((timeStep$var113 == timeStep$var136)) {
																														{
																															for(int var66 = 0; var66 < noStates; var66 += 1) {
																																if((var66 == traceTempVariable$currentState$35_1)) {
																																	{
																																		{
																																			{
																																				double var149 = metric_var[traceTempVariable$currentState$35_1];
																																				if(((Math.log(cv$probabilitySample123Value33) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value33) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value33) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value33) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value33) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value33);
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
											} else {
												for(int index$sample$5 = 0; index$sample$5 < noSamples; index$sample$5 += 1) {
													if(true) {
														for(int index$sample104$6 = 0; index$sample104$6 < noStates; index$sample104$6 += 1) {
															int distributionTempVariable$var102$8 = index$sample104$6;
															double cv$probabilitySample104Value7 = (1.0 * distribution$sample104[((index$sample$5 - 0) / 1)][index$sample104$6]);
															{
																int traceTempVariable$currentState$9_1 = distributionTempVariable$var102$8;
																if((index$sample$5 == sample)) {
																	if((0 == timeStep$var136)) {
																		{
																			double traceTempVariable$var148$11_1 = cv$currentValue;
																			if((var50 == traceTempVariable$currentState$9_1)) {
																				{
																					{
																						boolean cv$sampleConstrained = true;
																						if(cv$sampleConstrained) {
																							constrainedFlag$sample52[((var50 - 0) / 1)] = true;
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								{
																									int traceTempVariable$currentState$38_1 = distributionTempVariable$var102$8;
																									if((index$sample$5 == sample)) {
																										if((0 == timeStep$var136)) {
																											{
																												for(int var66 = 0; var66 < noStates; var66 += 1) {
																													if((var66 == traceTempVariable$currentState$38_1)) {
																														{
																															{
																																{
																																	double var149 = metric_var[traceTempVariable$currentState$38_1];
																																	if(((Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value7);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																								for(int index$sample$39 = 0; index$sample$39 < noSamples; index$sample$39 += 1) {
																									if(!(index$sample$39 == index$sample$5)) {
																										for(int index$sample104$40 = 0; index$sample104$40 < noStates; index$sample104$40 += 1) {
																											int distributionTempVariable$var102$42 = index$sample104$40;
																											double cv$probabilitySample104Value41 = (cv$probabilitySample104Value7 * distribution$sample104[((index$sample$39 - 0) / 1)][index$sample104$40]);
																											{
																												int traceTempVariable$currentState$43_1 = distributionTempVariable$var102$42;
																												if((index$sample$39 == sample)) {
																													if((0 == timeStep$var136)) {
																														{
																															for(int var66 = 0; var66 < noStates; var66 += 1) {
																																if((var66 == traceTempVariable$currentState$43_1)) {
																																	{
																																		{
																																			{
																																				double var149 = metric_var[traceTempVariable$currentState$43_1];
																																				if(((Math.log(cv$probabilitySample104Value41) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value41) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value41) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value41) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value41) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value41);
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
																								if(fixedFlag$sample123) {
																									{
																										for(int index$sample$46_1 = 0; index$sample$46_1 < noSamples; index$sample$46_1 += 1) {
																											for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$46_1]; timeStep$var113 += 1) {
																												if((index$sample$46_1 == sample)) {
																													if((timeStep$var113 == timeStep$var136)) {
																														{
																															for(int var66 = 0; var66 < noStates; var66 += 1) {
																																if((var66 == traceTempVariable$currentState$9_1)) {
																																	{
																																		{
																																			{
																																				double var149 = metric_var[traceTempVariable$currentState$9_1];
																																				if(((Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value7);
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
																								} else {
																									for(int index$sample$47 = 0; index$sample$47 < noSamples; index$sample$47 += 1) {
																										for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$47]; timeStep$var113 += 1) {
																											if(true) {
																												for(int index$sample123$49 = 0; index$sample123$49 < noStates; index$sample123$49 += 1) {
																													int distributionTempVariable$var121$51 = index$sample123$49;
																													double cv$probabilitySample123Value50 = (cv$probabilitySample104Value7 * distribution$sample123[((index$sample$47 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$49]);
																													{
																														int traceTempVariable$currentState$52_1 = distributionTempVariable$var121$51;
																														if((index$sample$47 == sample)) {
																															if((timeStep$var113 == timeStep$var136)) {
																																{
																																	for(int var66 = 0; var66 < noStates; var66 += 1) {
																																		if((var66 == traceTempVariable$currentState$52_1)) {
																																			{
																																				{
																																					{
																																						double var149 = metric_var[traceTempVariable$currentState$52_1];
																																						if(((Math.log(cv$probabilitySample123Value50) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value50) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value50) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value50) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value50) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value50);
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
												}
											}
										}
									}
								}
								for(int sample = 0; sample < noSamples; sample += 1) {
									for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
										if(metric_valid_g[sample][timeStep$var136]) {
											if(fixedFlag$sample123) {
												{
													for(int index$sample$14_1 = 0; index$sample$14_1 < noSamples; index$sample$14_1 += 1) {
														for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$14_1]; timeStep$var113 += 1) {
															if((index$sample$14_1 == sample)) {
																if((timeStep$var113 == timeStep$var136)) {
																	{
																		double traceTempVariable$var148$21_1 = cv$currentValue;
																		if((var50 == st[sample][timeStep$var136])) {
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample52[((var50 - 0) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							if(fixedFlag$sample104) {
																								{
																									for(int index$sample$55_1 = 0; index$sample$55_1 < noSamples; index$sample$55_1 += 1) {
																										if((index$sample$55_1 == sample)) {
																											if((0 == timeStep$var136)) {
																												{
																													for(int var66 = 0; var66 < noStates; var66 += 1) {
																														if((var66 == st[sample][timeStep$var136])) {
																															{
																																{
																																	{
																																		double var149 = metric_var[st[sample][timeStep$var136]];
																																		if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																							} else {
																								for(int index$sample$56 = 0; index$sample$56 < noSamples; index$sample$56 += 1) {
																									if(true) {
																										for(int index$sample104$57 = 0; index$sample104$57 < noStates; index$sample104$57 += 1) {
																											int distributionTempVariable$var102$59 = index$sample104$57;
																											double cv$probabilitySample104Value58 = (1.0 * distribution$sample104[((index$sample$56 - 0) / 1)][index$sample104$57]);
																											{
																												int traceTempVariable$currentState$60_1 = distributionTempVariable$var102$59;
																												if((index$sample$56 == sample)) {
																													if((0 == timeStep$var136)) {
																														{
																															for(int var66 = 0; var66 < noStates; var66 += 1) {
																																if((var66 == traceTempVariable$currentState$60_1)) {
																																	{
																																		{
																																			{
																																				double var149 = metric_var[traceTempVariable$currentState$60_1];
																																				if(((Math.log(cv$probabilitySample104Value58) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value58) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value58) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value58) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value58) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value58);
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
																							}
																							{
																								for(int index$sample$63_1 = 0; index$sample$63_1 < noSamples; index$sample$63_1 += 1) {
																									for(int index$timeStep$63_2 = 1; index$timeStep$63_2 < length$metric[index$sample$63_1]; index$timeStep$63_2 += 1) {
																										if((index$sample$63_1 == sample)) {
																											if((index$timeStep$63_2 == timeStep$var136)) {
																												{
																													for(int var66 = 0; var66 < noStates; var66 += 1) {
																														if((var66 == st[sample][timeStep$var136])) {
																															{
																																{
																																	{
																																		double var149 = metric_var[st[sample][timeStep$var136]];
																																		if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
											} else {
												for(int index$sample$15 = 0; index$sample$15 < noSamples; index$sample$15 += 1) {
													for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$15]; timeStep$var113 += 1) {
														if(true) {
															for(int index$sample123$17 = 0; index$sample123$17 < noStates; index$sample123$17 += 1) {
																int distributionTempVariable$var121$19 = index$sample123$17;
																double cv$probabilitySample123Value18 = (1.0 * distribution$sample123[((index$sample$15 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$17]);
																{
																	int traceTempVariable$currentState$20_1 = distributionTempVariable$var121$19;
																	if((index$sample$15 == sample)) {
																		if((timeStep$var113 == timeStep$var136)) {
																			{
																				double traceTempVariable$var148$22_1 = cv$currentValue;
																				if((var50 == traceTempVariable$currentState$20_1)) {
																					{
																						{
																							boolean cv$sampleConstrained = true;
																							if(cv$sampleConstrained) {
																								constrainedFlag$sample52[((var50 - 0) / 1)] = true;
																								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																								double cv$consumerDistributionProbabilityAccumulator = 1.0;
																								{
																									if(fixedFlag$sample104) {
																										{
																											for(int index$sample$65_1 = 0; index$sample$65_1 < noSamples; index$sample$65_1 += 1) {
																												if((index$sample$65_1 == sample)) {
																													if((0 == timeStep$var136)) {
																														{
																															for(int var66 = 0; var66 < noStates; var66 += 1) {
																																if((var66 == traceTempVariable$currentState$20_1)) {
																																	{
																																		{
																																			{
																																				double var149 = metric_var[traceTempVariable$currentState$20_1];
																																				if(((Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value18);
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
																									} else {
																										for(int index$sample$66 = 0; index$sample$66 < noSamples; index$sample$66 += 1) {
																											if(true) {
																												for(int index$sample104$67 = 0; index$sample104$67 < noStates; index$sample104$67 += 1) {
																													int distributionTempVariable$var102$69 = index$sample104$67;
																													double cv$probabilitySample104Value68 = (cv$probabilitySample123Value18 * distribution$sample104[((index$sample$66 - 0) / 1)][index$sample104$67]);
																													{
																														int traceTempVariable$currentState$70_1 = distributionTempVariable$var102$69;
																														if((index$sample$66 == sample)) {
																															if((0 == timeStep$var136)) {
																																{
																																	for(int var66 = 0; var66 < noStates; var66 += 1) {
																																		if((var66 == traceTempVariable$currentState$70_1)) {
																																			{
																																				{
																																					{
																																						double var149 = metric_var[traceTempVariable$currentState$70_1];
																																						if(((Math.log(cv$probabilitySample104Value68) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value68) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value68) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value68) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value68) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value68);
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
																									}
																									{
																										int traceTempVariable$currentState$73_1 = distributionTempVariable$var121$19;
																										if((index$sample$15 == sample)) {
																											if((timeStep$var113 == timeStep$var136)) {
																												{
																													for(int var66 = 0; var66 < noStates; var66 += 1) {
																														if((var66 == traceTempVariable$currentState$73_1)) {
																															{
																																{
																																	{
																																		double var149 = metric_var[traceTempVariable$currentState$73_1];
																																		if(((Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value18);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																									for(int index$sample$74 = 0; index$sample$74 < noSamples; index$sample$74 += 1) {
																										for(int index$timeStep$75 = 1; index$timeStep$75 < length$metric[index$sample$74]; index$timeStep$75 += 1) {
																											if(!((index$timeStep$75 == timeStep$var113) && (index$sample$74 == index$sample$15))) {
																												for(int index$sample123$76 = 0; index$sample123$76 < noStates; index$sample123$76 += 1) {
																													int distributionTempVariable$var121$78 = index$sample123$76;
																													double cv$probabilitySample123Value77 = (cv$probabilitySample123Value18 * distribution$sample123[((index$sample$74 - 0) / 1)][((index$timeStep$75 - 1) / 1)][index$sample123$76]);
																													{
																														int traceTempVariable$currentState$79_1 = distributionTempVariable$var121$78;
																														if((index$sample$74 == sample)) {
																															if((index$timeStep$75 == timeStep$var136)) {
																																{
																																	for(int var66 = 0; var66 < noStates; var66 += 1) {
																																		if((var66 == traceTempVariable$currentState$79_1)) {
																																			{
																																				{
																																					{
																																						double var149 = metric_var[traceTempVariable$currentState$79_1];
																																						if(((Math.log(cv$probabilitySample123Value77) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value77) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value77) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value77) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value77) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value77);
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
							double var51 = cv$originalValue;
							{
								{
									{
										metric_mean[var50] = var51;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample68(int var66) {
		if(true) {
			constrainedFlag$sample68[((var66 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = metric_var[var66];
			double cv$originalProbability = 0.0;
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((constrainedFlag$sample68[((var66 - 0) / 1)] || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						double var67 = cv$proposedValue;
						{
							{
								{
									metric_var[var66] = cv$currentValue;
								}
							}
						}
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, 1.0, 1.0));
						{
							{
								for(int sample = 0; sample < noSamples; sample += 1) {
									for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
										if(metric_valid_g[sample][timeStep$var136]) {
											if(fixedFlag$sample104) {
												{
													for(int index$sample$4_1 = 0; index$sample$4_1 < noSamples; index$sample$4_1 += 1) {
														if((index$sample$4_1 == sample)) {
															if((0 == timeStep$var136)) {
																{
																	double traceTempVariable$var149$10_1 = cv$currentValue;
																	if((var66 == st[sample][timeStep$var136])) {
																		{
																			{
																				boolean cv$sampleConstrained = true;
																				if(cv$sampleConstrained) {
																					constrainedFlag$sample68[((var66 - 0) / 1)] = true;
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							for(int index$sample$27_1 = 0; index$sample$27_1 < noSamples; index$sample$27_1 += 1) {
																								if((index$sample$27_1 == sample)) {
																									if((0 == timeStep$var136)) {
																										{
																											for(int var50 = 0; var50 < noStates; var50 += 1) {
																												if((var50 == st[sample][timeStep$var136])) {
																													{
																														{
																															{
																																double var148 = metric_mean[st[sample][timeStep$var136]];
																																if(((Math.log(1.0) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																						if(fixedFlag$sample123) {
																							{
																								for(int index$sample$29_1 = 0; index$sample$29_1 < noSamples; index$sample$29_1 += 1) {
																									for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$29_1]; timeStep$var113 += 1) {
																										if((index$sample$29_1 == sample)) {
																											if((timeStep$var113 == timeStep$var136)) {
																												{
																													for(int var50 = 0; var50 < noStates; var50 += 1) {
																														if((var50 == st[sample][timeStep$var136])) {
																															{
																																{
																																	{
																																		double var148 = metric_mean[st[sample][timeStep$var136]];
																																		if(((Math.log(1.0) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																						} else {
																							for(int index$sample$30 = 0; index$sample$30 < noSamples; index$sample$30 += 1) {
																								for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$30]; timeStep$var113 += 1) {
																									if(true) {
																										for(int index$sample123$32 = 0; index$sample123$32 < noStates; index$sample123$32 += 1) {
																											int distributionTempVariable$var121$34 = index$sample123$32;
																											double cv$probabilitySample123Value33 = (1.0 * distribution$sample123[((index$sample$30 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$32]);
																											{
																												int traceTempVariable$currentState$35_1 = distributionTempVariable$var121$34;
																												if((index$sample$30 == sample)) {
																													if((timeStep$var113 == timeStep$var136)) {
																														{
																															for(int var50 = 0; var50 < noStates; var50 += 1) {
																																if((var50 == traceTempVariable$currentState$35_1)) {
																																	{
																																		{
																																			{
																																				double var148 = metric_mean[traceTempVariable$currentState$35_1];
																																				if(((Math.log(cv$probabilitySample123Value33) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value33) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value33) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value33) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value33) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value33);
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
											} else {
												for(int index$sample$5 = 0; index$sample$5 < noSamples; index$sample$5 += 1) {
													if(true) {
														for(int index$sample104$6 = 0; index$sample104$6 < noStates; index$sample104$6 += 1) {
															int distributionTempVariable$var102$8 = index$sample104$6;
															double cv$probabilitySample104Value7 = (1.0 * distribution$sample104[((index$sample$5 - 0) / 1)][index$sample104$6]);
															{
																int traceTempVariable$currentState$9_1 = distributionTempVariable$var102$8;
																if((index$sample$5 == sample)) {
																	if((0 == timeStep$var136)) {
																		{
																			double traceTempVariable$var149$11_1 = cv$currentValue;
																			if((var66 == traceTempVariable$currentState$9_1)) {
																				{
																					{
																						boolean cv$sampleConstrained = true;
																						if(cv$sampleConstrained) {
																							constrainedFlag$sample68[((var66 - 0) / 1)] = true;
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								{
																									int traceTempVariable$currentState$38_1 = distributionTempVariable$var102$8;
																									if((index$sample$5 == sample)) {
																										if((0 == timeStep$var136)) {
																											{
																												for(int var50 = 0; var50 < noStates; var50 += 1) {
																													if((var50 == traceTempVariable$currentState$38_1)) {
																														{
																															{
																																{
																																	double var148 = metric_mean[traceTempVariable$currentState$38_1];
																																	if(((Math.log(cv$probabilitySample104Value7) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value7) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value7) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value7) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value7);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																								for(int index$sample$39 = 0; index$sample$39 < noSamples; index$sample$39 += 1) {
																									if(!(index$sample$39 == index$sample$5)) {
																										for(int index$sample104$40 = 0; index$sample104$40 < noStates; index$sample104$40 += 1) {
																											int distributionTempVariable$var102$42 = index$sample104$40;
																											double cv$probabilitySample104Value41 = (cv$probabilitySample104Value7 * distribution$sample104[((index$sample$39 - 0) / 1)][index$sample104$40]);
																											{
																												int traceTempVariable$currentState$43_1 = distributionTempVariable$var102$42;
																												if((index$sample$39 == sample)) {
																													if((0 == timeStep$var136)) {
																														{
																															for(int var50 = 0; var50 < noStates; var50 += 1) {
																																if((var50 == traceTempVariable$currentState$43_1)) {
																																	{
																																		{
																																			{
																																				double var148 = metric_mean[traceTempVariable$currentState$43_1];
																																				if(((Math.log(cv$probabilitySample104Value41) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value41) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value41) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value41) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value41) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value41);
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
																								if(fixedFlag$sample123) {
																									{
																										for(int index$sample$46_1 = 0; index$sample$46_1 < noSamples; index$sample$46_1 += 1) {
																											for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$46_1]; timeStep$var113 += 1) {
																												if((index$sample$46_1 == sample)) {
																													if((timeStep$var113 == timeStep$var136)) {
																														{
																															for(int var50 = 0; var50 < noStates; var50 += 1) {
																																if((var50 == traceTempVariable$currentState$9_1)) {
																																	{
																																		{
																																			{
																																				double var148 = metric_mean[traceTempVariable$currentState$9_1];
																																				if(((Math.log(cv$probabilitySample104Value7) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value7) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value7) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value7) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value7);
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
																								} else {
																									for(int index$sample$47 = 0; index$sample$47 < noSamples; index$sample$47 += 1) {
																										for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$47]; timeStep$var113 += 1) {
																											if(true) {
																												for(int index$sample123$49 = 0; index$sample123$49 < noStates; index$sample123$49 += 1) {
																													int distributionTempVariable$var121$51 = index$sample123$49;
																													double cv$probabilitySample123Value50 = (cv$probabilitySample104Value7 * distribution$sample123[((index$sample$47 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$49]);
																													{
																														int traceTempVariable$currentState$52_1 = distributionTempVariable$var121$51;
																														if((index$sample$47 == sample)) {
																															if((timeStep$var113 == timeStep$var136)) {
																																{
																																	for(int var50 = 0; var50 < noStates; var50 += 1) {
																																		if((var50 == traceTempVariable$currentState$52_1)) {
																																			{
																																				{
																																					{
																																						double var148 = metric_mean[traceTempVariable$currentState$52_1];
																																						if(((Math.log(cv$probabilitySample123Value50) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value50) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value50) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value50) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value50) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value50);
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
												}
											}
										}
									}
								}
								for(int sample = 0; sample < noSamples; sample += 1) {
									for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
										if(metric_valid_g[sample][timeStep$var136]) {
											if(fixedFlag$sample123) {
												{
													for(int index$sample$14_1 = 0; index$sample$14_1 < noSamples; index$sample$14_1 += 1) {
														for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$14_1]; timeStep$var113 += 1) {
															if((index$sample$14_1 == sample)) {
																if((timeStep$var113 == timeStep$var136)) {
																	{
																		double traceTempVariable$var149$21_1 = cv$currentValue;
																		if((var66 == st[sample][timeStep$var136])) {
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample68[((var66 - 0) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							if(fixedFlag$sample104) {
																								{
																									for(int index$sample$55_1 = 0; index$sample$55_1 < noSamples; index$sample$55_1 += 1) {
																										if((index$sample$55_1 == sample)) {
																											if((0 == timeStep$var136)) {
																												{
																													for(int var50 = 0; var50 < noStates; var50 += 1) {
																														if((var50 == st[sample][timeStep$var136])) {
																															{
																																{
																																	{
																																		double var148 = metric_mean[st[sample][timeStep$var136]];
																																		if(((Math.log(1.0) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																							} else {
																								for(int index$sample$56 = 0; index$sample$56 < noSamples; index$sample$56 += 1) {
																									if(true) {
																										for(int index$sample104$57 = 0; index$sample104$57 < noStates; index$sample104$57 += 1) {
																											int distributionTempVariable$var102$59 = index$sample104$57;
																											double cv$probabilitySample104Value58 = (1.0 * distribution$sample104[((index$sample$56 - 0) / 1)][index$sample104$57]);
																											{
																												int traceTempVariable$currentState$60_1 = distributionTempVariable$var102$59;
																												if((index$sample$56 == sample)) {
																													if((0 == timeStep$var136)) {
																														{
																															for(int var50 = 0; var50 < noStates; var50 += 1) {
																																if((var50 == traceTempVariable$currentState$60_1)) {
																																	{
																																		{
																																			{
																																				double var148 = metric_mean[traceTempVariable$currentState$60_1];
																																				if(((Math.log(cv$probabilitySample104Value58) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value58) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value58) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value58) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value58) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value58);
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
																							}
																							{
																								for(int index$sample$63_1 = 0; index$sample$63_1 < noSamples; index$sample$63_1 += 1) {
																									for(int index$timeStep$63_2 = 1; index$timeStep$63_2 < length$metric[index$sample$63_1]; index$timeStep$63_2 += 1) {
																										if((index$sample$63_1 == sample)) {
																											if((index$timeStep$63_2 == timeStep$var136)) {
																												{
																													for(int var50 = 0; var50 < noStates; var50 += 1) {
																														if((var50 == st[sample][timeStep$var136])) {
																															{
																																{
																																	{
																																		double var148 = metric_mean[st[sample][timeStep$var136]];
																																		if(((Math.log(1.0) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
											} else {
												for(int index$sample$15 = 0; index$sample$15 < noSamples; index$sample$15 += 1) {
													for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$15]; timeStep$var113 += 1) {
														if(true) {
															for(int index$sample123$17 = 0; index$sample123$17 < noStates; index$sample123$17 += 1) {
																int distributionTempVariable$var121$19 = index$sample123$17;
																double cv$probabilitySample123Value18 = (1.0 * distribution$sample123[((index$sample$15 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$17]);
																{
																	int traceTempVariable$currentState$20_1 = distributionTempVariable$var121$19;
																	if((index$sample$15 == sample)) {
																		if((timeStep$var113 == timeStep$var136)) {
																			{
																				double traceTempVariable$var149$22_1 = cv$currentValue;
																				if((var66 == traceTempVariable$currentState$20_1)) {
																					{
																						{
																							boolean cv$sampleConstrained = true;
																							if(cv$sampleConstrained) {
																								constrainedFlag$sample68[((var66 - 0) / 1)] = true;
																								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																								double cv$consumerDistributionProbabilityAccumulator = 1.0;
																								{
																									if(fixedFlag$sample104) {
																										{
																											for(int index$sample$65_1 = 0; index$sample$65_1 < noSamples; index$sample$65_1 += 1) {
																												if((index$sample$65_1 == sample)) {
																													if((0 == timeStep$var136)) {
																														{
																															for(int var50 = 0; var50 < noStates; var50 += 1) {
																																if((var50 == traceTempVariable$currentState$20_1)) {
																																	{
																																		{
																																			{
																																				double var148 = metric_mean[traceTempVariable$currentState$20_1];
																																				if(((Math.log(cv$probabilitySample123Value18) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value18) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value18) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value18) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value18);
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
																									} else {
																										for(int index$sample$66 = 0; index$sample$66 < noSamples; index$sample$66 += 1) {
																											if(true) {
																												for(int index$sample104$67 = 0; index$sample104$67 < noStates; index$sample104$67 += 1) {
																													int distributionTempVariable$var102$69 = index$sample104$67;
																													double cv$probabilitySample104Value68 = (cv$probabilitySample123Value18 * distribution$sample104[((index$sample$66 - 0) / 1)][index$sample104$67]);
																													{
																														int traceTempVariable$currentState$70_1 = distributionTempVariable$var102$69;
																														if((index$sample$66 == sample)) {
																															if((0 == timeStep$var136)) {
																																{
																																	for(int var50 = 0; var50 < noStates; var50 += 1) {
																																		if((var50 == traceTempVariable$currentState$70_1)) {
																																			{
																																				{
																																					{
																																						double var148 = metric_mean[traceTempVariable$currentState$70_1];
																																						if(((Math.log(cv$probabilitySample104Value68) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value68) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value68) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value68) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value68) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value68);
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
																									}
																									{
																										int traceTempVariable$currentState$73_1 = distributionTempVariable$var121$19;
																										if((index$sample$15 == sample)) {
																											if((timeStep$var113 == timeStep$var136)) {
																												{
																													for(int var50 = 0; var50 < noStates; var50 += 1) {
																														if((var50 == traceTempVariable$currentState$73_1)) {
																															{
																																{
																																	{
																																		double var148 = metric_mean[traceTempVariable$currentState$73_1];
																																		if(((Math.log(cv$probabilitySample123Value18) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value18) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value18) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value18) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value18);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																									for(int index$sample$74 = 0; index$sample$74 < noSamples; index$sample$74 += 1) {
																										for(int index$timeStep$75 = 1; index$timeStep$75 < length$metric[index$sample$74]; index$timeStep$75 += 1) {
																											if(!((index$timeStep$75 == timeStep$var113) && (index$sample$74 == index$sample$15))) {
																												for(int index$sample123$76 = 0; index$sample123$76 < noStates; index$sample123$76 += 1) {
																													int distributionTempVariable$var121$78 = index$sample123$76;
																													double cv$probabilitySample123Value77 = (cv$probabilitySample123Value18 * distribution$sample123[((index$sample$74 - 0) / 1)][((index$timeStep$75 - 1) / 1)][index$sample123$76]);
																													{
																														int traceTempVariable$currentState$79_1 = distributionTempVariable$var121$78;
																														if((index$sample$74 == sample)) {
																															if((index$timeStep$75 == timeStep$var136)) {
																																{
																																	for(int var50 = 0; var50 < noStates; var50 += 1) {
																																		if((var50 == traceTempVariable$currentState$79_1)) {
																																			{
																																				{
																																					{
																																						double var148 = metric_mean[traceTempVariable$currentState$79_1];
																																						if(((Math.log(cv$probabilitySample123Value77) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value77) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value77) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value77) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value77) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value77);
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
							double var67 = cv$originalValue;
							{
								{
									{
										metric_var[var66] = var67;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample84(int var82) {
		if(true) {
			constrainedFlag$sample84[((var82 - 0) / 1)] = false;
			double cv$sum = 0.0;
			double cv$count = 0.0;
			{
				{
					{
						for(int sample = 0; sample < noSamples; sample += 1) {
							for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
								if(fixedFlag$sample104) {
									{
										for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
											if((index$sample$3_1 == sample)) {
												if((0 == timeStep$var136)) {
													{
														if((var82 == st[sample][timeStep$var136])) {
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample84[((var82 - 0) / 1)] = true;
																		{
																			{
																				{
																					{
																						{
																							cv$count = (cv$count + 1.0);
																							if(metric_valid_g[sample][timeStep$var136])
																								cv$sum = (cv$sum + 1.0);
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
										}
									}
								} else {
									for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
										if(true) {
											for(int index$sample104$5 = 0; index$sample104$5 < noStates; index$sample104$5 += 1) {
												int distributionTempVariable$var102$7 = index$sample104$5;
												double cv$probabilitySample104Value6 = (1.0 * distribution$sample104[((index$sample$4 - 0) / 1)][index$sample104$5]);
												{
													int traceTempVariable$currentState$8_1 = distributionTempVariable$var102$7;
													if((index$sample$4 == sample)) {
														if((0 == timeStep$var136)) {
															{
																if((var82 == traceTempVariable$currentState$8_1)) {
																	{
																		{
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				constrainedFlag$sample84[((var82 - 0) / 1)] = true;
																				{
																					{
																						{
																							{
																								{
																									cv$count = (cv$count + cv$probabilitySample104Value6);
																									if(metric_valid_g[sample][timeStep$var136])
																										cv$sum = (cv$sum + cv$probabilitySample104Value6);
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
												}
											}
										}
									}
								}
							}
						}
						for(int sample = 0; sample < noSamples; sample += 1) {
							for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
								if(fixedFlag$sample123) {
									{
										for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
											for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$13_1]; timeStep$var113 += 1) {
												if((index$sample$13_1 == sample)) {
													if((timeStep$var113 == timeStep$var136)) {
														{
															if((var82 == st[sample][timeStep$var136])) {
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample84[((var82 - 0) / 1)] = true;
																			{
																				{
																					{
																						{
																							{
																								cv$count = (cv$count + 1.0);
																								if(metric_valid_g[sample][timeStep$var136])
																									cv$sum = (cv$sum + 1.0);
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
											}
										}
									}
								} else {
									for(int index$sample$14 = 0; index$sample$14 < noSamples; index$sample$14 += 1) {
										for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$14]; timeStep$var113 += 1) {
											if(true) {
												for(int index$sample123$16 = 0; index$sample123$16 < noStates; index$sample123$16 += 1) {
													int distributionTempVariable$var121$18 = index$sample123$16;
													double cv$probabilitySample123Value17 = (1.0 * distribution$sample123[((index$sample$14 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$16]);
													{
														int traceTempVariable$currentState$19_1 = distributionTempVariable$var121$18;
														if((index$sample$14 == sample)) {
															if((timeStep$var113 == timeStep$var136)) {
																{
																	if((var82 == traceTempVariable$currentState$19_1)) {
																		{
																			{
																				boolean cv$sampleConstrained = true;
																				if(cv$sampleConstrained) {
																					constrainedFlag$sample84[((var82 - 0) / 1)] = true;
																					{
																						{
																							{
																								{
																									{
																										cv$count = (cv$count + cv$probabilitySample123Value17);
																										if(metric_valid_g[sample][timeStep$var136])
																											cv$sum = (cv$sum + cv$probabilitySample123Value17);
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
			if(constrainedFlag$sample84[((var82 - 0) / 1)]) {
				double var83 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
				{
					{
						{
							metric_valid_bias[var82] = var83;
						}
					}
				}
			}
		}
	}

	private final void logProbabilityDistribution$sample104() {
		if(!fixedProbFlag$sample104) {
			if(fixedFlag$sample104) {
				double cv$accumulator = 0.0;
				boolean cv$sampleReached = false;
				for(int sample = 0; sample < noSamples; sample += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$sample$1 = sample;
					{
						{
							int cv$sampleValue = st[sample][0];
							{
								{
									double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= initialStateDistribution[cv$sampleValue])) && (initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$sample104[((sample - 0) / 1)] = cv$sampleProbability;
				}
				if(fixedFlag$sample104)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample104)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample104 = (fixedFlag$sample104 && fixedFlag$sample19);
			}
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < noSamples; sample += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample104[((sample - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			if(fixedFlag$sample104)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample104)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample123() {
		if(!fixedProbFlag$sample123) {
			if(fixedFlag$sample123) {
				double cv$accumulator = 0.0;
				boolean cv$sampleReached = false;
				for(int sample = 0; sample < noSamples; sample += 1) {
					for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int index$timeStep$1 = timeStep$var113;
						int index$sample$2 = sample;
						{
							{
								int cv$sampleValue = st[sample][timeStep$var113];
								if(fixedFlag$sample104) {
									{
										for(int index$sample$4_1 = 0; index$sample$4_1 < noSamples; index$sample$4_1 += 1) {
											if((index$sample$4_1 == sample)) {
												if((0 == (timeStep$var113 - 1))) {
													{
														for(int var31 = 0; var31 < noStates; var31 += 1) {
															if((var31 == st[sample][(timeStep$var113 - 1)])) {
																{
																	double[] var119 = m[st[sample][(timeStep$var113 - 1)]];
																	double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
											}
										}
									}
								} else {
									for(int index$sample$5 = 0; index$sample$5 < noSamples; index$sample$5 += 1) {
										if(true) {
											for(int index$sample104$6 = 0; index$sample104$6 < noStates; index$sample104$6 += 1) {
												int distributionTempVariable$var102$8 = index$sample104$6;
												double cv$probabilitySample104Value7 = (1.0 * distribution$sample104[((index$sample$5 - 0) / 1)][index$sample104$6]);
												{
													int traceTempVariable$var118$9_1 = distributionTempVariable$var102$8;
													if((index$sample$5 == sample)) {
														if((0 == (timeStep$var113 - 1))) {
															{
																for(int var31 = 0; var31 < noStates; var31 += 1) {
																	if((var31 == traceTempVariable$var118$9_1)) {
																		{
																			double[] var119 = m[traceTempVariable$var118$9_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample104Value7) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value7);
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
								{
									if((index$sample$2 == sample)) {
										if((index$timeStep$1 == (timeStep$var113 - 1))) {
											{
												for(int var31 = 0; var31 < noStates; var31 += 1) {
													if((var31 == st[sample][(timeStep$var113 - 1)])) {
														{
															double[] var119 = m[st[sample][(timeStep$var113 - 1)]];
															double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
									}
								}
								if(fixedFlag$sample123) {
									{
										for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
											for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1]; index$timeStep$13_2 += 1) {
												if((index$sample$13_1 == sample)) {
													if((index$timeStep$13_2 == (timeStep$var113 - 1))) {
														{
															for(int var31 = 0; var31 < noStates; var31 += 1) {
																if((var31 == st[sample][(timeStep$var113 - 1)])) {
																	{
																		double[] var119 = m[st[sample][(timeStep$var113 - 1)]];
																		double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
												}
											}
										}
									}
								} else {
									for(int index$sample$14 = 0; index$sample$14 < noSamples; index$sample$14 += 1) {
										for(int index$timeStep$15 = 1; index$timeStep$15 < length$metric[index$sample$14]; index$timeStep$15 += 1) {
											if(!((index$timeStep$15 == index$timeStep$1) && (index$sample$14 == index$sample$2))) {
												for(int index$sample123$16 = 0; index$sample123$16 < noStates; index$sample123$16 += 1) {
													int distributionTempVariable$var121$18 = index$sample123$16;
													double cv$probabilitySample123Value17 = (1.0 * distribution$sample123[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample123$16]);
													{
														int traceTempVariable$var118$19_1 = distributionTempVariable$var121$18;
														if((index$sample$14 == sample)) {
															if((index$timeStep$15 == (timeStep$var113 - 1))) {
																{
																	for(int var31 = 0; var31 < noStates; var31 += 1) {
																		if((var31 == traceTempVariable$var118$19_1)) {
																			{
																				double[] var119 = m[traceTempVariable$var118$19_1];
																				double cv$weightedProbability = (Math.log(cv$probabilitySample123Value17) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample123Value17);
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
							}
						}
						if((cv$probabilityReached == 0.0))
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						double cv$sampleProbability = cv$distributionAccumulator;
						cv$sampleReached = true;
						cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
						cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
						logProbability$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = cv$sampleProbability;
					}
				}
				if(fixedFlag$sample123)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample123)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample123 = ((fixedFlag$sample123 && fixedFlag$sample32) && fixedFlag$sample104);
			}
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			if(fixedFlag$sample123)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample123)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample145() {
		if(!fixedProbFlag$sample145) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							boolean cv$sampleValue = metric_valid_g[sample][timeStep$var136];
							if(fixedFlag$sample104) {
								{
									for(int index$sample$2_1 = 0; index$sample$2_1 < noSamples; index$sample$2_1 += 1) {
										if((index$sample$2_1 == sample)) {
											if((0 == timeStep$var136)) {
												{
													for(int var82 = 0; var82 < noStates; var82 += 1) {
														if((var82 == st[sample][timeStep$var136])) {
															{
																double var139 = metric_valid_bias[st[sample][timeStep$var136]];
																double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((cv$sampleValue?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY));
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
										}
									}
								}
							} else {
								for(int index$sample$3 = 0; index$sample$3 < noSamples; index$sample$3 += 1) {
									if(true) {
										for(int index$sample104$4 = 0; index$sample104$4 < noStates; index$sample104$4 += 1) {
											int distributionTempVariable$var102$6 = index$sample104$4;
											double cv$probabilitySample104Value5 = (1.0 * distribution$sample104[((index$sample$3 - 0) / 1)][index$sample104$4]);
											{
												int traceTempVariable$currentState$7_1 = distributionTempVariable$var102$6;
												if((index$sample$3 == sample)) {
													if((0 == timeStep$var136)) {
														{
															for(int var82 = 0; var82 < noStates; var82 += 1) {
																if((var82 == traceTempVariable$currentState$7_1)) {
																	{
																		double var139 = metric_valid_bias[traceTempVariable$currentState$7_1];
																		double cv$weightedProbability = (Math.log(cv$probabilitySample104Value5) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((cv$sampleValue?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY));
																		if((cv$weightedProbability < cv$distributionAccumulator))
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																		else {
																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																				cv$distributionAccumulator = cv$weightedProbability;
																			else
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																		}
																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value5);
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
							if(fixedFlag$sample123) {
								{
									for(int index$sample$10_1 = 0; index$sample$10_1 < noSamples; index$sample$10_1 += 1) {
										for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$10_1]; timeStep$var113 += 1) {
											if((index$sample$10_1 == sample)) {
												if((timeStep$var113 == timeStep$var136)) {
													{
														for(int var82 = 0; var82 < noStates; var82 += 1) {
															if((var82 == st[sample][timeStep$var136])) {
																{
																	double var139 = metric_valid_bias[st[sample][timeStep$var136]];
																	double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((cv$sampleValue?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY));
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
											}
										}
									}
								}
							} else {
								for(int index$sample$11 = 0; index$sample$11 < noSamples; index$sample$11 += 1) {
									for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$11]; timeStep$var113 += 1) {
										if(true) {
											for(int index$sample123$13 = 0; index$sample123$13 < noStates; index$sample123$13 += 1) {
												int distributionTempVariable$var121$15 = index$sample123$13;
												double cv$probabilitySample123Value14 = (1.0 * distribution$sample123[((index$sample$11 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$13]);
												{
													int traceTempVariable$currentState$16_1 = distributionTempVariable$var121$15;
													if((index$sample$11 == sample)) {
														if((timeStep$var113 == timeStep$var136)) {
															{
																for(int var82 = 0; var82 < noStates; var82 += 1) {
																	if((var82 == traceTempVariable$currentState$16_1)) {
																		{
																			double var139 = metric_valid_bias[traceTempVariable$currentState$16_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample123Value14) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((cv$sampleValue?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample123Value14);
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
						}
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					cv$sampleReached = true;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$sample145[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = cv$sampleProbability;
				}
			}
			boolean cv$guard$metric_valid_g = false;
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			{
				{
					if(!cv$guard$metric_valid_g) {
						cv$guard$metric_valid_g = true;
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample145 = ((fixedFlag$sample84 && fixedFlag$sample104) && fixedFlag$sample123);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample145[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			boolean cv$guard$metric_valid_g = false;
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			{
				{
					if(!cv$guard$metric_valid_g) {
						cv$guard$metric_valid_g = true;
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample157() {
		if(!fixedProbFlag$sample157) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if(metric_valid_g[sample][timeStep$var136]) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							{
								double cv$sampleValue = var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)];
								if(fixedFlag$sample104) {
									{
										for(int index$sample$2_1 = 0; index$sample$2_1 < noSamples; index$sample$2_1 += 1) {
											if((index$sample$2_1 == sample)) {
												if((0 == timeStep$var136)) {
													{
														for(int var50 = 0; var50 < noStates; var50 += 1) {
															if((var50 == st[sample][timeStep$var136])) {
																{
																	for(int index$sample$10_1 = 0; index$sample$10_1 < noSamples; index$sample$10_1 += 1) {
																		if((index$sample$10_1 == sample)) {
																			if((0 == timeStep$var136)) {
																				{
																					for(int var66 = 0; var66 < noStates; var66 += 1) {
																						if((var66 == st[sample][timeStep$var136])) {
																							{
																								double var148 = metric_mean[st[sample][timeStep$var136]];
																								double var149 = metric_var[st[sample][timeStep$var136]];
																								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
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
								} else {
									for(int index$sample$3 = 0; index$sample$3 < noSamples; index$sample$3 += 1) {
										if(true) {
											for(int index$sample104$4 = 0; index$sample104$4 < noStates; index$sample104$4 += 1) {
												int distributionTempVariable$var102$6 = index$sample104$4;
												double cv$probabilitySample104Value5 = (1.0 * distribution$sample104[((index$sample$3 - 0) / 1)][index$sample104$4]);
												{
													int traceTempVariable$currentState$7_1 = distributionTempVariable$var102$6;
													if((index$sample$3 == sample)) {
														if((0 == timeStep$var136)) {
															{
																for(int var50 = 0; var50 < noStates; var50 += 1) {
																	if((var50 == traceTempVariable$currentState$7_1)) {
																		{
																			int traceTempVariable$currentState$11_1 = distributionTempVariable$var102$6;
																			if((index$sample$3 == sample)) {
																				if((0 == timeStep$var136)) {
																					{
																						for(int var66 = 0; var66 < noStates; var66 += 1) {
																							if((var66 == traceTempVariable$currentState$11_1)) {
																								{
																									double var148 = metric_mean[traceTempVariable$currentState$11_1];
																									double var149 = metric_var[traceTempVariable$currentState$11_1];
																									double cv$weightedProbability = (Math.log(cv$probabilitySample104Value5) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																									if((cv$weightedProbability < cv$distributionAccumulator))
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																									else {
																										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																											cv$distributionAccumulator = cv$weightedProbability;
																										else
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																									}
																									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value5);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		for(int index$sample$12 = 0; index$sample$12 < noSamples; index$sample$12 += 1) {
																			if(!(index$sample$12 == index$sample$3)) {
																				for(int index$sample104$13 = 0; index$sample104$13 < noStates; index$sample104$13 += 1) {
																					int distributionTempVariable$var102$15 = index$sample104$13;
																					double cv$probabilitySample104Value14 = (cv$probabilitySample104Value5 * distribution$sample104[((index$sample$12 - 0) / 1)][index$sample104$13]);
																					{
																						int traceTempVariable$currentState$16_1 = distributionTempVariable$var102$15;
																						if((index$sample$12 == sample)) {
																							if((0 == timeStep$var136)) {
																								{
																									for(int var66 = 0; var66 < noStates; var66 += 1) {
																										if((var66 == traceTempVariable$currentState$16_1)) {
																											{
																												double var148 = metric_mean[traceTempVariable$currentState$16_1];
																												double var149 = metric_var[traceTempVariable$currentState$16_1];
																												double cv$weightedProbability = (Math.log(cv$probabilitySample104Value14) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																												if((cv$weightedProbability < cv$distributionAccumulator))
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																												else {
																													if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																														cv$distributionAccumulator = cv$weightedProbability;
																													else
																														cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																												}
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value14);
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
															}
														}
													}
												}
											}
										}
									}
								}
								if(fixedFlag$sample104) {
									{
										for(int index$sample$20_1 = 0; index$sample$20_1 < noSamples; index$sample$20_1 += 1) {
											if((index$sample$20_1 == sample)) {
												if((0 == timeStep$var136)) {
													{
														for(int var50 = 0; var50 < noStates; var50 += 1) {
															if((var50 == st[sample][timeStep$var136])) {
																if(fixedFlag$sample123) {
																	{
																		for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																			for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$28_1]; timeStep$var113 += 1) {
																				if((index$sample$28_1 == sample)) {
																					if((timeStep$var113 == timeStep$var136)) {
																						{
																							for(int var66 = 0; var66 < noStates; var66 += 1) {
																								if((var66 == st[sample][timeStep$var136])) {
																									{
																										double var148 = metric_mean[st[sample][timeStep$var136]];
																										double var149 = metric_var[st[sample][timeStep$var136]];
																										double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
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
																				}
																			}
																		}
																	}
																} else {
																	for(int index$sample$29 = 0; index$sample$29 < noSamples; index$sample$29 += 1) {
																		for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$29]; timeStep$var113 += 1) {
																			if(true) {
																				for(int index$sample123$31 = 0; index$sample123$31 < noStates; index$sample123$31 += 1) {
																					int distributionTempVariable$var121$33 = index$sample123$31;
																					double cv$probabilitySample123Value32 = (1.0 * distribution$sample123[((index$sample$29 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$31]);
																					{
																						int traceTempVariable$currentState$34_1 = distributionTempVariable$var121$33;
																						if((index$sample$29 == sample)) {
																							if((timeStep$var113 == timeStep$var136)) {
																								{
																									for(int var66 = 0; var66 < noStates; var66 += 1) {
																										if((var66 == traceTempVariable$currentState$34_1)) {
																											{
																												double var148 = metric_mean[traceTempVariable$currentState$34_1];
																												double var149 = metric_var[traceTempVariable$currentState$34_1];
																												double cv$weightedProbability = (Math.log(cv$probabilitySample123Value32) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																												if((cv$weightedProbability < cv$distributionAccumulator))
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																												else {
																													if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																														cv$distributionAccumulator = cv$weightedProbability;
																													else
																														cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																												}
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample123Value32);
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
															}
														}
													}
												}
											}
										}
									}
								} else {
									for(int index$sample$21 = 0; index$sample$21 < noSamples; index$sample$21 += 1) {
										if(true) {
											for(int index$sample104$22 = 0; index$sample104$22 < noStates; index$sample104$22 += 1) {
												int distributionTempVariable$var102$24 = index$sample104$22;
												double cv$probabilitySample104Value23 = (1.0 * distribution$sample104[((index$sample$21 - 0) / 1)][index$sample104$22]);
												{
													int traceTempVariable$currentState$25_1 = distributionTempVariable$var102$24;
													if((index$sample$21 == sample)) {
														if((0 == timeStep$var136)) {
															{
																for(int var50 = 0; var50 < noStates; var50 += 1) {
																	if((var50 == traceTempVariable$currentState$25_1)) {
																		if(fixedFlag$sample123) {
																			{
																				for(int index$sample$35_1 = 0; index$sample$35_1 < noSamples; index$sample$35_1 += 1) {
																					for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$35_1]; timeStep$var113 += 1) {
																						if((index$sample$35_1 == sample)) {
																							if((timeStep$var113 == timeStep$var136)) {
																								{
																									for(int var66 = 0; var66 < noStates; var66 += 1) {
																										if((var66 == traceTempVariable$currentState$25_1)) {
																											{
																												double var148 = metric_mean[traceTempVariable$currentState$25_1];
																												double var149 = metric_var[traceTempVariable$currentState$25_1];
																												double cv$weightedProbability = (Math.log(cv$probabilitySample104Value23) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																												if((cv$weightedProbability < cv$distributionAccumulator))
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																												else {
																													if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																														cv$distributionAccumulator = cv$weightedProbability;
																													else
																														cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																												}
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value23);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		} else {
																			for(int index$sample$36 = 0; index$sample$36 < noSamples; index$sample$36 += 1) {
																				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$36]; timeStep$var113 += 1) {
																					if(true) {
																						for(int index$sample123$38 = 0; index$sample123$38 < noStates; index$sample123$38 += 1) {
																							int distributionTempVariable$var121$40 = index$sample123$38;
																							double cv$probabilitySample123Value39 = (cv$probabilitySample104Value23 * distribution$sample123[((index$sample$36 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$38]);
																							{
																								int traceTempVariable$currentState$41_1 = distributionTempVariable$var121$40;
																								if((index$sample$36 == sample)) {
																									if((timeStep$var113 == timeStep$var136)) {
																										{
																											for(int var66 = 0; var66 < noStates; var66 += 1) {
																												if((var66 == traceTempVariable$currentState$41_1)) {
																													{
																														double var148 = metric_mean[traceTempVariable$currentState$41_1];
																														double var149 = metric_var[traceTempVariable$currentState$41_1];
																														double cv$weightedProbability = (Math.log(cv$probabilitySample123Value39) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																														if((cv$weightedProbability < cv$distributionAccumulator))
																															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																														else {
																															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																cv$distributionAccumulator = cv$weightedProbability;
																															else
																																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																														}
																														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample123Value39);
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
								if(fixedFlag$sample123) {
									{
										for(int index$sample$46_1 = 0; index$sample$46_1 < noSamples; index$sample$46_1 += 1) {
											for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$46_1]; timeStep$var113 += 1) {
												if((index$sample$46_1 == sample)) {
													if((timeStep$var113 == timeStep$var136)) {
														{
															for(int var50 = 0; var50 < noStates; var50 += 1) {
																if((var50 == st[sample][timeStep$var136])) {
																	{
																		for(int index$sample$55_1 = 0; index$sample$55_1 < noSamples; index$sample$55_1 += 1) {
																			for(int index$timeStep$55_2 = 1; index$timeStep$55_2 < length$metric[index$sample$55_1]; index$timeStep$55_2 += 1) {
																				if((index$sample$55_1 == sample)) {
																					if((index$timeStep$55_2 == timeStep$var136)) {
																						{
																							for(int var66 = 0; var66 < noStates; var66 += 1) {
																								if((var66 == st[sample][timeStep$var136])) {
																									{
																										double var148 = metric_mean[st[sample][timeStep$var136]];
																										double var149 = metric_var[st[sample][timeStep$var136]];
																										double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
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
								} else {
									for(int index$sample$47 = 0; index$sample$47 < noSamples; index$sample$47 += 1) {
										for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$47]; timeStep$var113 += 1) {
											if(true) {
												for(int index$sample123$49 = 0; index$sample123$49 < noStates; index$sample123$49 += 1) {
													int distributionTempVariable$var121$51 = index$sample123$49;
													double cv$probabilitySample123Value50 = (1.0 * distribution$sample123[((index$sample$47 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$49]);
													{
														int traceTempVariable$currentState$52_1 = distributionTempVariable$var121$51;
														if((index$sample$47 == sample)) {
															if((timeStep$var113 == timeStep$var136)) {
																{
																	for(int var50 = 0; var50 < noStates; var50 += 1) {
																		if((var50 == traceTempVariable$currentState$52_1)) {
																			{
																				int traceTempVariable$currentState$56_1 = distributionTempVariable$var121$51;
																				if((index$sample$47 == sample)) {
																					if((timeStep$var113 == timeStep$var136)) {
																						{
																							for(int var66 = 0; var66 < noStates; var66 += 1) {
																								if((var66 == traceTempVariable$currentState$56_1)) {
																									{
																										double var148 = metric_mean[traceTempVariable$currentState$56_1];
																										double var149 = metric_var[traceTempVariable$currentState$56_1];
																										double cv$weightedProbability = (Math.log(cv$probabilitySample123Value50) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																										if((cv$weightedProbability < cv$distributionAccumulator))
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																										else {
																											if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																												cv$distributionAccumulator = cv$weightedProbability;
																											else
																												cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																										}
																										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample123Value50);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			for(int index$sample$57 = 0; index$sample$57 < noSamples; index$sample$57 += 1) {
																				for(int index$timeStep$58 = 1; index$timeStep$58 < length$metric[index$sample$57]; index$timeStep$58 += 1) {
																					if(!((index$timeStep$58 == timeStep$var113) && (index$sample$57 == index$sample$47))) {
																						for(int index$sample123$59 = 0; index$sample123$59 < noStates; index$sample123$59 += 1) {
																							int distributionTempVariable$var121$61 = index$sample123$59;
																							double cv$probabilitySample123Value60 = (cv$probabilitySample123Value50 * distribution$sample123[((index$sample$57 - 0) / 1)][((index$timeStep$58 - 1) / 1)][index$sample123$59]);
																							{
																								int traceTempVariable$currentState$62_1 = distributionTempVariable$var121$61;
																								if((index$sample$57 == sample)) {
																									if((index$timeStep$58 == timeStep$var136)) {
																										{
																											for(int var66 = 0; var66 < noStates; var66 += 1) {
																												if((var66 == traceTempVariable$currentState$62_1)) {
																													{
																														double var148 = metric_mean[traceTempVariable$currentState$62_1];
																														double var149 = metric_var[traceTempVariable$currentState$62_1];
																														double cv$weightedProbability = (Math.log(cv$probabilitySample123Value60) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																														if((cv$weightedProbability < cv$distributionAccumulator))
																															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																														else {
																															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																cv$distributionAccumulator = cv$weightedProbability;
																															else
																																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																														}
																														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample123Value60);
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
								if(fixedFlag$sample123) {
									{
										for(int index$sample$66_1 = 0; index$sample$66_1 < noSamples; index$sample$66_1 += 1) {
											for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$66_1]; timeStep$var113 += 1) {
												if((index$sample$66_1 == sample)) {
													if((timeStep$var113 == timeStep$var136)) {
														{
															for(int var50 = 0; var50 < noStates; var50 += 1) {
																if((var50 == st[sample][timeStep$var136])) {
																	if(fixedFlag$sample104) {
																		{
																			for(int index$sample$75_1 = 0; index$sample$75_1 < noSamples; index$sample$75_1 += 1) {
																				if((index$sample$75_1 == sample)) {
																					if((0 == timeStep$var136)) {
																						{
																							for(int var66 = 0; var66 < noStates; var66 += 1) {
																								if((var66 == st[sample][timeStep$var136])) {
																									{
																										double var148 = metric_mean[st[sample][timeStep$var136]];
																										double var149 = metric_var[st[sample][timeStep$var136]];
																										double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
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
																				}
																			}
																		}
																	} else {
																		for(int index$sample$76 = 0; index$sample$76 < noSamples; index$sample$76 += 1) {
																			if(true) {
																				for(int index$sample104$77 = 0; index$sample104$77 < noStates; index$sample104$77 += 1) {
																					int distributionTempVariable$var102$79 = index$sample104$77;
																					double cv$probabilitySample104Value78 = (1.0 * distribution$sample104[((index$sample$76 - 0) / 1)][index$sample104$77]);
																					{
																						int traceTempVariable$currentState$80_1 = distributionTempVariable$var102$79;
																						if((index$sample$76 == sample)) {
																							if((0 == timeStep$var136)) {
																								{
																									for(int var66 = 0; var66 < noStates; var66 += 1) {
																										if((var66 == traceTempVariable$currentState$80_1)) {
																											{
																												double var148 = metric_mean[traceTempVariable$currentState$80_1];
																												double var149 = metric_var[traceTempVariable$currentState$80_1];
																												double cv$weightedProbability = (Math.log(cv$probabilitySample104Value78) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																												if((cv$weightedProbability < cv$distributionAccumulator))
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																												else {
																													if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																														cv$distributionAccumulator = cv$weightedProbability;
																													else
																														cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																												}
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value78);
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
															}
														}
													}
												}
											}
										}
									}
								} else {
									for(int index$sample$67 = 0; index$sample$67 < noSamples; index$sample$67 += 1) {
										for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$67]; timeStep$var113 += 1) {
											if(true) {
												for(int index$sample123$69 = 0; index$sample123$69 < noStates; index$sample123$69 += 1) {
													int distributionTempVariable$var121$71 = index$sample123$69;
													double cv$probabilitySample123Value70 = (1.0 * distribution$sample123[((index$sample$67 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$69]);
													{
														int traceTempVariable$currentState$72_1 = distributionTempVariable$var121$71;
														if((index$sample$67 == sample)) {
															if((timeStep$var113 == timeStep$var136)) {
																{
																	for(int var50 = 0; var50 < noStates; var50 += 1) {
																		if((var50 == traceTempVariable$currentState$72_1)) {
																			if(fixedFlag$sample104) {
																				{
																					for(int index$sample$81_1 = 0; index$sample$81_1 < noSamples; index$sample$81_1 += 1) {
																						if((index$sample$81_1 == sample)) {
																							if((0 == timeStep$var136)) {
																								{
																									for(int var66 = 0; var66 < noStates; var66 += 1) {
																										if((var66 == traceTempVariable$currentState$72_1)) {
																											{
																												double var148 = metric_mean[traceTempVariable$currentState$72_1];
																												double var149 = metric_var[traceTempVariable$currentState$72_1];
																												double cv$weightedProbability = (Math.log(cv$probabilitySample123Value70) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																												if((cv$weightedProbability < cv$distributionAccumulator))
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																												else {
																													if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																														cv$distributionAccumulator = cv$weightedProbability;
																													else
																														cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																												}
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample123Value70);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			} else {
																				for(int index$sample$82 = 0; index$sample$82 < noSamples; index$sample$82 += 1) {
																					if(true) {
																						for(int index$sample104$83 = 0; index$sample104$83 < noStates; index$sample104$83 += 1) {
																							int distributionTempVariable$var102$85 = index$sample104$83;
																							double cv$probabilitySample104Value84 = (cv$probabilitySample123Value70 * distribution$sample104[((index$sample$82 - 0) / 1)][index$sample104$83]);
																							{
																								int traceTempVariable$currentState$86_1 = distributionTempVariable$var102$85;
																								if((index$sample$82 == sample)) {
																									if((0 == timeStep$var136)) {
																										{
																											for(int var66 = 0; var66 < noStates; var66 += 1) {
																												if((var66 == traceTempVariable$currentState$86_1)) {
																													{
																														double var148 = metric_mean[traceTempVariable$currentState$86_1];
																														double var149 = metric_var[traceTempVariable$currentState$86_1];
																														double cv$weightedProbability = (Math.log(cv$probabilitySample104Value84) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																														if((cv$weightedProbability < cv$distributionAccumulator))
																															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																														else {
																															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																cv$distributionAccumulator = cv$weightedProbability;
																															else
																																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																														}
																														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value84);
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
						if((cv$probabilityReached == 0.0))
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						double cv$sampleProbability = cv$distributionAccumulator;
						cv$sampleReached = true;
						cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
						cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
						logProbability$sample157[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = cv$sampleProbability;
					}
				}
			}
			boolean cv$guard$metric_g = false;
			logProbability$var151 = (logProbability$var151 + cv$accumulator);
			{
				{
					if(!cv$guard$metric_g) {
						cv$guard$metric_g = true;
						logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample157 = (((fixedFlag$sample52 && fixedFlag$sample68) && fixedFlag$sample104) && fixedFlag$sample123);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if(metric_valid_g[sample][timeStep$var136]) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample157[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$sampleReached = true;
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					}
				}
			}
			boolean cv$guard$metric_g = false;
			logProbability$var151 = (logProbability$var151 + cv$accumulator);
			{
				{
					if(!cv$guard$metric_g) {
						cv$guard$metric_g = true;
						logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample104() {
		if(!fixedProbFlag$sample104) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < noSamples; sample += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$sample$1 = sample;
				{
					{
						int cv$sampleValue = st[sample][0];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= initialStateDistribution[cv$sampleValue])) && (initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample104[((sample - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample104)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample104 = (fixedFlag$sample104 && fixedFlag$sample19);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < noSamples; sample += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample104[((sample - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample104)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample123() {
		if(!fixedProbFlag$sample123) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$timeStep$1 = timeStep$var113;
					int index$sample$2 = sample;
					{
						{
							int cv$sampleValue = st[sample][timeStep$var113];
							{
								{
									double[] var119 = m[st[sample][(timeStep$var113 - 1)]];
									double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample123)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample123 = ((fixedFlag$sample123 && fixedFlag$sample32) && fixedFlag$sample104);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample123)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample145() {
		if(!fixedProbFlag$sample145) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							boolean cv$sampleValue = metric_valid_g[sample][timeStep$var136];
							{
								{
									double var139 = metric_valid_bias[st[sample][timeStep$var136]];
									double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((cv$sampleValue?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY));
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
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$sample145[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = cv$sampleProbability;
				}
			}
			boolean cv$guard$metric_valid_g = false;
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			{
				{
					if(!cv$guard$metric_valid_g) {
						cv$guard$metric_valid_g = true;
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample145 = ((fixedFlag$sample84 && fixedFlag$sample104) && fixedFlag$sample123);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample145[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			boolean cv$guard$metric_valid_g = false;
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			{
				{
					if(!cv$guard$metric_valid_g) {
						cv$guard$metric_valid_g = true;
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample157() {
		if(!fixedProbFlag$sample157) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if(metric_valid_g[sample][timeStep$var136]) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							{
								double cv$sampleValue = var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)];
								{
									{
										double var148 = metric_mean[st[sample][timeStep$var136]];
										double var149 = metric_var[st[sample][timeStep$var136]];
										double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
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
						cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
						logProbability$sample157[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = cv$sampleProbability;
					}
				}
			}
			boolean cv$guard$metric_g = false;
			logProbability$var151 = (logProbability$var151 + cv$accumulator);
			{
				{
					if(!cv$guard$metric_g) {
						cv$guard$metric_g = true;
						logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample157 = (((fixedFlag$sample52 && fixedFlag$sample68) && fixedFlag$sample104) && fixedFlag$sample123);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if(metric_valid_g[sample][timeStep$var136]) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample157[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$sampleReached = true;
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					}
				}
			}
			boolean cv$guard$metric_g = false;
			logProbability$var151 = (logProbability$var151 + cv$accumulator);
			{
				{
					if(!cv$guard$metric_g) {
						cv$guard$metric_g = true;
						logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample19() {
		if(!fixedProbFlag$sample19) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double[] cv$sampleValue = initialStateDistribution;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, noStates));
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
			logProbability$initialStateDistribution = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample19)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample19 = fixedFlag$sample19;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$initialStateDistribution;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample19)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample32() {
		if(!fixedProbFlag$sample32) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var31 = 0; var31 < noStates; var31 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double[] cv$sampleValue = m[var31];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, noStates));
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
			logProbability$var32 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample32 = fixedFlag$sample32;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var31 = 0; var31 < noStates; var31 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var32;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample52() {
		if(!fixedProbFlag$sample52) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var50 = 0; var50 < noStates; var50 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = metric_mean[var50];
						{
							{
								double var37 = 0.0;
								double var38 = 100.0;
								double cv$weightedProbability = (Math.log(1.0) + (((var37 <= cv$sampleValue) && (cv$sampleValue < var38))?(-Math.log((var38 - var37))):Double.NEGATIVE_INFINITY));
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
			logProbability$var51 = cv$sampleAccumulator;
			logProbability$metric_mean = (logProbability$metric_mean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample52)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample52 = fixedFlag$sample52;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var50 = 0; var50 < noStates; var50 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var51;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$metric_mean = (logProbability$metric_mean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample52)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample68() {
		if(!fixedProbFlag$sample68) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var66 = 0; var66 < noStates; var66 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = metric_var[var66];
						{
							{
								double var53 = 1.0;
								double var54 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var53, var54));
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
			logProbability$var67 = cv$sampleAccumulator;
			logProbability$metric_var = (logProbability$metric_var + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample68)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample68 = fixedFlag$sample68;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var66 = 0; var66 < noStates; var66 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var67;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$metric_var = (logProbability$metric_var + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample68)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample84() {
		if(!fixedProbFlag$sample84) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var82 = 0; var82 < noStates; var82 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = metric_valid_bias[var82];
						{
							{
								double var69 = 1.0;
								double var70 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var69, var70));
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
			logProbability$var83 = cv$sampleAccumulator;
			logProbability$metric_valid_bias = (logProbability$metric_valid_bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample84)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample84 = fixedFlag$sample84;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var82 = 0; var82 < noStates; var82 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var83;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$metric_valid_bias = (logProbability$metric_valid_bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample84)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void allocate() {
		{
			v = new double[noStates];
		}
		if(!fixedFlag$sample19) {
			{
				initialStateDistribution = new double[noStates];
			}
		}
		if(!fixedFlag$sample32) {
			{
				m = new double[noStates][];
				for(int var31 = 0; var31 < noStates; var31 += 1)
					m[var31] = new double[noStates];
			}
		}
		if((!fixedFlag$sample104 || !fixedFlag$sample123)) {
			{
				st = new int[length$metric.length][];
				for(int sample = 0; sample < length$metric.length; sample += 1)
					st[sample] = new int[length$metric[sample]];
			}
		}
		{
			metric_g = new double[length$metric.length][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				metric_g[sample] = new double[length$metric[sample]];
		}
		{
			metric_valid_g = new boolean[length$metric.length][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				metric_valid_g[sample] = new boolean[length$metric[sample]];
		}
		if(!fixedFlag$sample52) {
			{
				metric_mean = new double[noStates];
			}
		}
		if(!fixedFlag$sample68) {
			{
				metric_var = new double[noStates];
			}
		}
		if(!fixedFlag$sample84) {
			{
				metric_valid_bias = new double[noStates];
			}
		}
		{
			var151 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				var151[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 0) / 1) + 1)];
		}
		{
			distribution$sample104 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				distribution$sample104[((sample - 0) / 1)] = new double[noStates];
		}
		{
			distribution$sample123 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample = 0; sample < length$metric.length; sample += 1) {
				double[][] subarray$0 = new double[((((length$metric[sample] - 1) - 1) / 1) + 1)][];
				distribution$sample123[((sample - 0) / 1)] = subarray$0;
				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1)
					subarray$0[((timeStep$var113 - 1) / 1)] = new double[noStates];
			}
		}
		{
			constrainedFlag$sample32 = new boolean[((((noStates - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample123 = new boolean[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				constrainedFlag$sample123[((sample - 0) / 1)] = new boolean[((((length$metric[sample] - 1) - 1) / 1) + 1)];
		}
		{
			constrainedFlag$sample104 = new boolean[((((length$metric.length - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample84 = new boolean[((((noStates - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample68 = new boolean[((((noStates - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample52 = new boolean[((((noStates - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample104 = new double[((((length$metric.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample123 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$sample123[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample145 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$sample145[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample157 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$sample157[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void allocateScratch() {
		{
			cv$var19$countGlobal = new double[noStates];
		}
		{
			cv$var32$countGlobal = new double[noStates];
		}
		{
			int cv$var33$max = noStates;
			cv$distributionAccumulator$var120 = new double[cv$var33$max];
		}
		{
			cv$var102$stateProbabilityGlobal = new double[noStates];
		}
		{
			int cv$max_sample = 0;
			int cv$max_timeStep$var136 = 0;
			for(int sample = 0; sample < length$metric.length; sample += 1)
				cv$max_timeStep$var136 = Math.max(cv$max_timeStep$var136, ((length$metric[sample] - 0) / 1));
			cv$max_sample = Math.max(cv$max_sample, ((length$metric.length - 0) / 1));
			guard$sample104gaussian156$global = new boolean[cv$max_sample][cv$max_timeStep$var136];
		}
		{
			int cv$var33$max = noStates;
			cv$var121$stateProbabilityGlobal = new double[cv$var33$max];
		}
		{
			int cv$max_sample = 0;
			int cv$max_timeStep$var136 = 0;
			for(int sample = 0; sample < length$metric.length; sample += 1)
				cv$max_timeStep$var136 = Math.max(cv$max_timeStep$var136, ((length$metric[sample] - 0) / 1));
			cv$max_sample = Math.max(cv$max_sample, ((length$metric.length - 0) / 1));
			guard$sample123gaussian156$global = new boolean[cv$max_sample][cv$max_timeStep$var136];
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		for(int var31 = 0; var31 < noStates; var31 += 1) {
			double[] var32 = m[var31];
			if(!fixedFlag$sample32)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, var32);
		}
		for(int var50 = 0; var50 < noStates; var50 += 1) {
			if(!fixedFlag$sample52)
				metric_mean[var50] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
		for(int var66 = 0; var66 < noStates; var66 += 1) {
			if(!fixedFlag$sample68)
				metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int var82 = 0; var82 < noStates; var82 += 1) {
			if(!fixedFlag$sample84)
				metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			int[] var99 = st[sample];
			if(!fixedFlag$sample104)
				var99[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
			int[] var114 = st[sample];
			for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
				if(!fixedFlag$sample123)
					var114[timeStep$var113] = DistributionSampling.sampleCategorical(RNG$, m[st[sample][(timeStep$var113 - 1)]], noStates);
			}
			boolean[] metric_valid_1d = metric_valid_g[sample];
			double[] metric_1d = metric_g[sample];
			for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
				metric_valid_1d[timeStep$var136] = DistributionSampling.sampleBernoulli(RNG$, metric_valid_bias[st[sample][timeStep$var136]]);
				if(metric_valid_1d[timeStep$var136]) {
					if(!fixedFlag$sample157)
						var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = ((Math.sqrt(metric_var[st[sample][timeStep$var136]]) * DistributionSampling.sampleGaussian(RNG$)) + metric_mean[st[sample][timeStep$var136]]);
					metric_1d[timeStep$var136] = var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)];
				}
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		for(int var31 = 0; var31 < noStates; var31 += 1) {
			double[] var32 = m[var31];
			if(!fixedFlag$sample32)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, var32);
		}
		for(int var50 = 0; var50 < noStates; var50 += 1) {
			if(!fixedFlag$sample52)
				metric_mean[var50] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
		for(int var66 = 0; var66 < noStates; var66 += 1) {
			if(!fixedFlag$sample68)
				metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int var82 = 0; var82 < noStates; var82 += 1) {
			if(!fixedFlag$sample84)
				metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			double[] cv$distribution$sample104 = distribution$sample104[((sample - 0) / 1)];
			for(int index$var101 = 0; index$var101 < noStates; index$var101 += 1) {
				double cv$value = ((((((0.0 <= index$var101) && (index$var101 < noStates)) && (0 < noStates)) && (0.0 <= initialStateDistribution[index$var101])) && (initialStateDistribution[index$var101] <= 1.0))?initialStateDistribution[index$var101]:0.0);
				if(!fixedFlag$sample104)
					cv$distribution$sample104[index$var101] = cv$value;
			}
			for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
				double[] cv$distribution$sample123 = distribution$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)];
				for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1) {
					if(!fixedFlag$sample123)
						cv$distribution$sample123[index$var120] = 0.0;
				}
				if(fixedFlag$sample104) {
					{
						for(int index$sample$1_1 = 0; index$sample$1_1 < noSamples; index$sample$1_1 += 1) {
							if((index$sample$1_1 == sample)) {
								if((0 == (timeStep$var113 - 1))) {
									{
										for(int var31 = 0; var31 < noStates; var31 += 1) {
											if((var31 == st[sample][(timeStep$var113 - 1)])) {
												{
													double[] var119 = m[st[sample][(timeStep$var113 - 1)]];
													for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1) {
														if(!fixedFlag$sample123)
															cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (1.0 * ((((((0.0 <= index$var120) && (index$var120 < noStates)) && (0 < noStates)) && (0.0 <= var119[index$var120])) && (var119[index$var120] <= 1.0))?var119[index$var120]:0.0)));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				} else {
					for(int index$sample$2 = 0; index$sample$2 < noSamples; index$sample$2 += 1) {
						if(true) {
							for(int index$sample104$3 = 0; index$sample104$3 < noStates; index$sample104$3 += 1) {
								int distributionTempVariable$var102$5 = index$sample104$3;
								double cv$probabilitySample104Value4 = (1.0 * distribution$sample104[((index$sample$2 - 0) / 1)][index$sample104$3]);
								{
									int traceTempVariable$var118$6_1 = distributionTempVariable$var102$5;
									if((index$sample$2 == sample)) {
										if((0 == (timeStep$var113 - 1))) {
											{
												for(int var31 = 0; var31 < noStates; var31 += 1) {
													if((var31 == traceTempVariable$var118$6_1)) {
														{
															double[] var119 = m[traceTempVariable$var118$6_1];
															for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1) {
																if(!fixedFlag$sample123)
																	cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (cv$probabilitySample104Value4 * ((((((0.0 <= index$var120) && (index$var120 < noStates)) && (0 < noStates)) && (0.0 <= var119[index$var120])) && (var119[index$var120] <= 1.0))?var119[index$var120]:0.0)));
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
				if(fixedFlag$sample123) {
					{
						for(int index$sample$9_1 = 0; index$sample$9_1 < noSamples; index$sample$9_1 += 1) {
							for(int index$timeStep$9_2 = 1; index$timeStep$9_2 < length$metric[index$sample$9_1]; index$timeStep$9_2 += 1) {
								if((index$sample$9_1 == sample)) {
									if((index$timeStep$9_2 == (timeStep$var113 - 1))) {
										{
											for(int var31 = 0; var31 < noStates; var31 += 1) {
												if((var31 == st[sample][(timeStep$var113 - 1)])) {
													{
														double[] var119 = m[st[sample][(timeStep$var113 - 1)]];
														for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1) {
															if(!fixedFlag$sample123)
																cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (1.0 * ((((((0.0 <= index$var120) && (index$var120 < noStates)) && (0 < noStates)) && (0.0 <= var119[index$var120])) && (var119[index$var120] <= 1.0))?var119[index$var120]:0.0)));
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
				} else {
					for(int index$sample$10 = 0; index$sample$10 < noSamples; index$sample$10 += 1) {
						for(int index$timeStep$11 = 1; index$timeStep$11 < length$metric[index$sample$10]; index$timeStep$11 += 1) {
							if(true) {
								for(int index$sample123$12 = 0; index$sample123$12 < noStates; index$sample123$12 += 1) {
									int distributionTempVariable$var121$14 = index$sample123$12;
									double cv$probabilitySample123Value13 = (1.0 * distribution$sample123[((index$sample$10 - 0) / 1)][((index$timeStep$11 - 1) / 1)][index$sample123$12]);
									{
										int traceTempVariable$var118$15_1 = distributionTempVariable$var121$14;
										if((index$sample$10 == sample)) {
											if((index$timeStep$11 == (timeStep$var113 - 1))) {
												{
													for(int var31 = 0; var31 < noStates; var31 += 1) {
														if((var31 == traceTempVariable$var118$15_1)) {
															{
																double[] var119 = m[traceTempVariable$var118$15_1];
																for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1) {
																	if(!fixedFlag$sample123)
																		cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (cv$probabilitySample123Value13 * ((((((0.0 <= index$var120) && (index$var120 < noStates)) && (0 < noStates)) && (0.0 <= var119[index$var120])) && (var119[index$var120] <= 1.0))?var119[index$var120]:0.0)));
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
				}
				double cv$var120$sum = 0.0;
				for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1) {
					if(!fixedFlag$sample123)
						cv$var120$sum = (cv$var120$sum + cv$distribution$sample123[index$var120]);
				}
				for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1) {
					if(!fixedFlag$sample123)
						cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] / cv$var120$sum);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		for(int var31 = 0; var31 < noStates; var31 += 1) {
			double[] var32 = m[var31];
			if(!fixedFlag$sample32)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, var32);
		}
		for(int var50 = 0; var50 < noStates; var50 += 1) {
			if(!fixedFlag$sample52)
				metric_mean[var50] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
		for(int var66 = 0; var66 < noStates; var66 += 1) {
			if(!fixedFlag$sample68)
				metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int var82 = 0; var82 < noStates; var82 += 1) {
			if(!fixedFlag$sample84)
				metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			int[] var99 = st[sample];
			if(!fixedFlag$sample104)
				var99[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
			int[] var114 = st[sample];
			for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
				if(!fixedFlag$sample123)
					var114[timeStep$var113] = DistributionSampling.sampleCategorical(RNG$, m[st[sample][(timeStep$var113 - 1)]], noStates);
			}
			boolean[] metric_valid_1d = metric_valid_g[sample];
			double[] metric_1d = metric_g[sample];
			for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
				metric_valid_1d[timeStep$var136] = DistributionSampling.sampleBernoulli(RNG$, metric_valid_bias[st[sample][timeStep$var136]]);
				if(metric_valid_1d[timeStep$var136]) {
					if(!fixedFlag$sample157)
						var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = ((Math.sqrt(metric_var[st[sample][timeStep$var136]]) * DistributionSampling.sampleGaussian(RNG$)) + metric_mean[st[sample][timeStep$var136]]);
					metric_1d[timeStep$var136] = var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)];
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		for(int var31 = 0; var31 < noStates; var31 += 1) {
			double[] var32 = m[var31];
			if(!fixedFlag$sample32)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, var32);
		}
		for(int var50 = 0; var50 < noStates; var50 += 1) {
			if(!fixedFlag$sample52)
				metric_mean[var50] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
		for(int var66 = 0; var66 < noStates; var66 += 1) {
			if(!fixedFlag$sample68)
				metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int var82 = 0; var82 < noStates; var82 += 1) {
			if(!fixedFlag$sample84)
				metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			int[] var99 = st[sample];
			if(!fixedFlag$sample104)
				var99[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
			int[] var114 = st[sample];
			for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
				if(!fixedFlag$sample123)
					var114[timeStep$var113] = DistributionSampling.sampleCategorical(RNG$, m[st[sample][(timeStep$var113 - 1)]], noStates);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		for(int var31 = 0; var31 < noStates; var31 += 1) {
			double[] var32 = m[var31];
			if(!fixedFlag$sample32)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, var32);
		}
		for(int var50 = 0; var50 < noStates; var50 += 1) {
			if(!fixedFlag$sample52)
				metric_mean[var50] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
		for(int var66 = 0; var66 < noStates; var66 += 1) {
			if(!fixedFlag$sample68)
				metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int var82 = 0; var82 < noStates; var82 += 1) {
			if(!fixedFlag$sample84)
				metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			int[] var99 = st[sample];
			if(!fixedFlag$sample104)
				var99[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
			int[] var114 = st[sample];
			for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
				if(!fixedFlag$sample123)
					var114[timeStep$var113] = DistributionSampling.sampleCategorical(RNG$, m[st[sample][(timeStep$var113 - 1)]], noStates);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample19)
				inferSample19();
			for(int var31 = 0; var31 < noStates; var31 += 1) {
				if(!fixedFlag$sample32)
					inferSample32(var31);
			}
			for(int var50 = 0; var50 < noStates; var50 += 1) {
				if(!fixedFlag$sample52)
					inferSample52(var50);
			}
			for(int var66 = 0; var66 < noStates; var66 += 1) {
				if(!fixedFlag$sample68)
					inferSample68(var66);
			}
			for(int var82 = 0; var82 < noStates; var82 += 1) {
				if(!fixedFlag$sample84)
					inferSample84(var82);
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				if(!fixedFlag$sample104)
					inferSample104(sample);
				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
					if(!fixedFlag$sample123)
						inferSample123(sample, timeStep$var113);
				}
			}
		} else {
			for(int sample = (noSamples - ((((noSamples - 1) - 0) % 1) + 1)); sample >= ((0 - 1) + 1); sample -= 1) {
				for(int timeStep$var113 = (length$metric[sample] - ((((length$metric[sample] - 1) - 1) % 1) + 1)); timeStep$var113 >= ((1 - 1) + 1); timeStep$var113 -= 1) {
					if(!fixedFlag$sample123)
						inferSample123(sample, timeStep$var113);
				}
				if(!fixedFlag$sample104)
					inferSample104(sample);
			}
			for(int var82 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var82 >= ((0 - 1) + 1); var82 -= 1) {
				if(!fixedFlag$sample84)
					inferSample84(var82);
			}
			for(int var66 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var66 >= ((0 - 1) + 1); var66 -= 1) {
				if(!fixedFlag$sample68)
					inferSample68(var66);
			}
			for(int var50 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var50 >= ((0 - 1) + 1); var50 -= 1) {
				if(!fixedFlag$sample52)
					inferSample52(var50);
			}
			for(int var31 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var31 >= ((0 - 1) + 1); var31 -= 1) {
				if(!fixedFlag$sample32)
					inferSample32(var31);
			}
			if(!fixedFlag$sample19)
				inferSample19();
		}
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample19)
			drawValueSample19();
		for(int var31 = 0; var31 < noStates; var31 += 1) {
			if(!constrainedFlag$sample32[((var31 - 0) / 1)])
				drawValueSample32(var31);
		}
		for(int var50 = 0; var50 < noStates; var50 += 1) {
			if(!constrainedFlag$sample52[((var50 - 0) / 1)])
				drawValueSample52(var50);
		}
		for(int var66 = 0; var66 < noStates; var66 += 1) {
			if(!constrainedFlag$sample68[((var66 - 0) / 1)])
				drawValueSample68(var66);
		}
		for(int var82 = 0; var82 < noStates; var82 += 1) {
			if(!constrainedFlag$sample84[((var82 - 0) / 1)])
				drawValueSample84(var82);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			if(!constrainedFlag$sample104[((sample - 0) / 1)])
				drawValueSample104(sample);
			for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
				if(!constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)])
					drawValueSample123(sample, timeStep$var113);
			}
		}
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		if(!fixedProbFlag$sample19)
			logProbability$initialStateDistribution = Double.NaN;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample32)
			logProbability$var32 = Double.NaN;
		logProbability$metric_mean = 0.0;
		if(!fixedProbFlag$sample52)
			logProbability$var51 = Double.NaN;
		logProbability$metric_var = 0.0;
		if(!fixedProbFlag$sample68)
			logProbability$var67 = Double.NaN;
		logProbability$metric_valid_bias = 0.0;
		if(!fixedProbFlag$sample84)
			logProbability$var83 = Double.NaN;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample104) {
			for(int sample = 0; sample < noSamples; sample += 1)
				logProbability$sample104[((sample - 0) / 1)] = Double.NaN;
		}
		if(!fixedProbFlag$sample123) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1)
					logProbability$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = Double.NaN;
			}
		}
		logProbability$metric_valid_1d = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample145) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1)
					logProbability$sample145[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = Double.NaN;
			}
		}
		logProbability$var151 = 0.0;
		logProbability$metric_g = 0.0;
		if(!fixedProbFlag$sample157) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1)
					logProbability$sample157[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = Double.NaN;
			}
		}
	}

	@Override
	public final void initializeModel() {
		noSamples = length$metric.length;
		for(int var15 = 0; var15 < noStates; var15 += 1)
			v[var15] = 0.1;
		for(int index$constrainedFlag$sample32$1 = 0; index$constrainedFlag$sample32$1 < constrainedFlag$sample32.length; index$constrainedFlag$sample32$1 += 1)
			constrainedFlag$sample32[index$constrainedFlag$sample32$1] = true;
		for(int index$constrainedFlag$sample123$1 = 0; index$constrainedFlag$sample123$1 < constrainedFlag$sample123.length; index$constrainedFlag$sample123$1 += 1) {
			boolean[] cv$constrainedFlag$sample123$1 = constrainedFlag$sample123[index$constrainedFlag$sample123$1];
			for(int index$constrainedFlag$sample123$2 = 0; index$constrainedFlag$sample123$2 < cv$constrainedFlag$sample123$1.length; index$constrainedFlag$sample123$2 += 1)
				cv$constrainedFlag$sample123$1[index$constrainedFlag$sample123$2] = true;
		}
		for(int index$constrainedFlag$sample104$1 = 0; index$constrainedFlag$sample104$1 < constrainedFlag$sample104.length; index$constrainedFlag$sample104$1 += 1)
			constrainedFlag$sample104[index$constrainedFlag$sample104$1] = true;
		for(int index$constrainedFlag$sample84$1 = 0; index$constrainedFlag$sample84$1 < constrainedFlag$sample84.length; index$constrainedFlag$sample84$1 += 1)
			constrainedFlag$sample84[index$constrainedFlag$sample84$1] = true;
		for(int index$constrainedFlag$sample68$1 = 0; index$constrainedFlag$sample68$1 < constrainedFlag$sample68.length; index$constrainedFlag$sample68$1 += 1)
			constrainedFlag$sample68[index$constrainedFlag$sample68$1] = true;
		for(int index$constrainedFlag$sample52$1 = 0; index$constrainedFlag$sample52$1 < constrainedFlag$sample52.length; index$constrainedFlag$sample52$1 += 1)
			constrainedFlag$sample52[index$constrainedFlag$sample52$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample19)
			logProbabilityValue$sample19();
		if(fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(fixedFlag$sample52)
			logProbabilityValue$sample52();
		if(fixedFlag$sample68)
			logProbabilityValue$sample68();
		if(fixedFlag$sample84)
			logProbabilityValue$sample84();
		logProbabilityValue$sample145();
		logProbabilityValue$sample157();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample19();
		logProbabilityValue$sample32();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample84();
		logProbabilityDistribution$sample104();
		logProbabilityDistribution$sample123();
		logProbabilityDistribution$sample145();
		logProbabilityDistribution$sample157();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample19();
		logProbabilityValue$sample32();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample84();
		logProbabilityValue$sample104();
		logProbabilityValue$sample123();
		logProbabilityValue$sample145();
		logProbabilityValue$sample157();
	}

	@Override
	public final void propogateObservedValues() {
		fixedFlag$sample157 = false;
		{
			{
				boolean[][] cv$source1 = metric_valid;
				boolean[][] cv$target1 = metric_valid_g;
				int cv$length1 = cv$target1.length;
				for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
					boolean[] cv$source2 = cv$source1[cv$index1];
					boolean[] cv$target2 = cv$target1[cv$index1];
					int cv$length2 = cv$target2.length;
					for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
						cv$target2[cv$index2] = cv$source2[cv$index2];
				}
			}
			for(int sample = (noSamples - ((((noSamples - 1) - 0) % 1) + 1)); sample >= ((0 - 1) + 1); sample -= 1) {
				for(int timeStep$var136 = (length$metric[sample] - ((((length$metric[sample] - 1) - 0) % 1) + 1)); timeStep$var136 >= ((0 - 1) + 1); timeStep$var136 -= 1) {
					metric_g[sample][timeStep$var136] = metric[sample][timeStep$var136];
					if(metric_valid_g[sample][timeStep$var136]) {
						{
							{
								{
									{
										for(int index$timeStep$2_1 = 0; index$timeStep$2_1 < length$metric[sample]; index$timeStep$2_1 += 1) {
											if((timeStep$var136 == index$timeStep$2_1))
												var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = metric_g[sample][timeStep$var136];
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

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model HMMMetrics2(\n"
		     + "               double[][] metric,\n"
		     + "               boolean[][] metric_valid, \n"
		     + "               int noStates) {\n"
		     + "    \n"
		     + "    int noSamples = metric.length;\n"
		     + "\n"
		     + "    // Construct arrays describing the probability of a move from 1 state to another.\n"
		     + "    double[] v = new double[noStates] <~ 0.1;\n"
		     + "    double[] initialStateDistribution = dirichlet(v).sample();\n"
		     + "    double[][] m = dirichlet(v).sample(noStates);\n"
		     + "\n"
		     + "    //Allocate space for states\n"
		     + "    int[][] st = new int[noSamples][];\n"
		     + "\n"
		     + "    //Allocate space for generated metrics \n"
		     + "    double[][] metric_g = new double[noSamples][];\n"
		     + "    boolean[][] metric_valid_g = new boolean[noSamples][];\n"
		     + "    \n"
		     + "    //Calculate priors for the metric\n"
		     + "    double[] metric_mean = uniform(0.0, 100.0).sample(noStates);\n"
		     + "    double[] metric_var = inverseGamma(1.0, 1.0).sample(noStates);\n"
		     + "    double[] metric_valid_bias = beta(1.0, 1.0).sample(noStates);\n"
		     + "    \n"
		     + "    // Compute the values of each metric value\n"
		     + "    for(int sample = 0; sample < noSamples; sample++) {\n"
		     + "        //Calculate all the state transitions\n"
		     + "        int streamLength = metric[sample].length;\n"
		     + "        \n"
		     + "        // Allocate space for the state.\n"
		     + "        st[sample] = new int[streamLength];\n"
		     + "        \n"
		     + "        // Set the initial state by sampling from a categorical with learnt weightings.\n"
		     + "        st[sample][0] = categorical(initialStateDistribution).sampleDistribution();\n"
		     + "        \n"
		     + "        // Calculate the remaining weightings\n"
		     + "        for(int timeStep = 1; timeStep < streamLength; timeStep++)\n"
		     + "            st[sample][timeStep] = categorical(m[st[sample][timeStep-1]]).sampleDistribution();\n"
		     + "        \n"
		     + "        //Calculate metric values\n"
		     + "        double[] metric_1d = new double[streamLength];\n"
		     + "        metric_g[sample] = metric_1d;\n"
		     + "\n"
		     + "        boolean[] metric_valid_1d = new boolean[streamLength];\n"
		     + "        metric_valid_g[sample] = metric_valid_1d;\n"
		     + "\n"
		     + "        //Generate values.\n"
		     + "        for(int timeStep = 0; timeStep < streamLength; timeStep++){\n"
		     + "            int currentState = st[sample][timeStep];\n"
		     + "            \n"
		     + "            metric_valid_1d[timeStep] = bernoulli(metric_valid_bias[currentState]).sample();\n"
		     + "            if(metric_valid_1d[timeStep])\n"
		     + "                metric_1d[timeStep] = gaussian(metric_mean[currentState], metric_var[currentState]).sample();\n"
		     + "            // Observing here as a cast is required and doing it inside the for loops removes the need duplicate them later.\n"
		     + "            metric_1d[timeStep].observe(metric[sample][timeStep]);\n"
		     + "        }\n"
		     + "    }\n"
		     + "\n"
		     + "    //Tie the values to the measured values.\n"
		     + "    metric_valid_g.observe(metric_valid);\n"
		     + "}";
	}
}