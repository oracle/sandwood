package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMMMetrics2$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMMMetrics2.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMMetrics2$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[][] cv$distributionAccumulator$var120;
		double[][] cv$var102$stateProbabilityGlobal;
		double[][] cv$var121$stateProbabilityGlobal;
		double[] cv$var19$countGlobal;
		double[][] cv$var32$countGlobal;
		boolean[][][] guard$sample104gaussian156$global;
		boolean[][][] guard$sample123gaussian156$global;

		@Override
		public final void allocateScratch() {
			{
				cv$var19$countGlobal = new double[state.noStates];
			}
			{
				{
					int cv$threadCount = threadCount();
					cv$var32$countGlobal = new double[cv$threadCount][];
					for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
						cv$var32$countGlobal[cv$index] = new double[state.noStates];
				}
			}
			{
				int cv$var33$max = state.noStates;
				{
					int cv$threadCount = threadCount();
					cv$distributionAccumulator$var120 = new double[cv$threadCount][];
					for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
						cv$distributionAccumulator$var120[cv$index] = new double[cv$var33$max];
				}
			}
			{
				{
					int cv$threadCount = threadCount();
					cv$var102$stateProbabilityGlobal = new double[cv$threadCount][];
					for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
						cv$var102$stateProbabilityGlobal[cv$index] = new double[state.noStates];
				}
			}
			{
				int cv$max_sample = 0;
				int cv$max_timeStep$var136 = 0;
				for(int sample = 0; sample < state.length$metric.length; sample += 1)
					cv$max_timeStep$var136 = Math.max(cv$max_timeStep$var136, ((state.length$metric[sample] - 0) / 1));
				cv$max_sample = Math.max(cv$max_sample, ((state.length$metric.length - 0) / 1));
				{
					int cv$threadCount = threadCount();
					guard$sample104gaussian156$global = new boolean[cv$threadCount][][];
					for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
						guard$sample104gaussian156$global[cv$index] = new boolean[cv$max_sample][cv$max_timeStep$var136];
				}
			}
			{
				int cv$var33$max = state.noStates;
				{
					int cv$threadCount = threadCount();
					cv$var121$stateProbabilityGlobal = new double[cv$threadCount][];
					for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
						cv$var121$stateProbabilityGlobal[cv$index] = new double[cv$var33$max];
				}
			}
			{
				int cv$max_sample = 0;
				int cv$max_timeStep$var136 = 0;
				for(int sample = 0; sample < state.length$metric.length; sample += 1)
					cv$max_timeStep$var136 = Math.max(cv$max_timeStep$var136, ((state.length$metric[sample] - 0) / 1));
				cv$max_sample = Math.max(cv$max_sample, ((state.length$metric.length - 0) / 1));
				{
					int cv$threadCount = threadCount();
					guard$sample123gaussian156$global = new boolean[cv$threadCount][][];
					for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
						guard$sample123gaussian156$global[cv$index] = new boolean[cv$max_sample][cv$max_timeStep$var136];
				}
			}
		}
	}


	public HMMMetrics2$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample104(int sample, int threadID$cv$sample, Rng RNG$) {
		int index$sample$1 = sample;
		int[] var99 = state.st[sample];
		var99[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
	}

	private final void drawValueSample123(int sample, int timeStep$var113, int threadID$cv$sample, Rng RNG$) {
		int index$timeStep$1 = timeStep$var113;
		int index$sample$2 = sample;
		int[] var114 = state.st[sample];
		var114[timeStep$var113] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample][(timeStep$var113 - 1)]], state.noStates);
	}

	private final void drawValueSample19() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
	}

	private final void drawValueSample32(int var31, int threadID$cv$var31, Rng RNG$) {
		double[] var32 = state.m[var31];
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, var32);
	}

	private final void drawValueSample52(int var50, int threadID$cv$var50, Rng RNG$) {
		state.metric_mean[var50] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
	}

	private final void drawValueSample68(int var66, int threadID$cv$var66, Rng RNG$) {
		state.metric_var[var66] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
	}

	private final void drawValueSample84(int var82, int threadID$cv$var82, Rng RNG$) {
		state.metric_valid_bias[var82] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void inferSample104(int sample, int threadID$cv$sample, Rng RNG$) {
		int index$sample$1 = sample;
		if(true) {
			state.constrainedFlag$sample104[((sample - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, state.noStates);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var102$stateProbabilityGlobal[threadID$cv$sample];
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[cv$currentValue])) && (state.initialStateDistribution[cv$currentValue] <= 1.0))?Math.log(state.initialStateDistribution[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$var118$2_1 = cv$currentValue;
								for(int index$sample$2_2 = 0; index$sample$2_2 < state.noSamples; index$sample$2_2 += 1) {
									if((sample == index$sample$2_2)) {
										for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$2_2]; timeStep$var113 += 1) {
											if((0 == (timeStep$var113 - 1))) {
												if(state.fixedFlag$sample123) {
													{
														{
															int index$timeStep$4 = timeStep$var113;
															int index$sample$5 = index$sample$2_2;
															boolean cv$sampleConstrained = (state.fixedFlag$sample123 || state.constrainedFlag$sample123[((index$sample$2_2 - 0) / 1)][((timeStep$var113 - 1) / 1)]);
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample104[((sample - 0) / 1)] = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		for(int var31 = 0; var31 < state.noStates; var31 += 1) {
																			if((var31 == traceTempVariable$var118$2_1)) {
																				{
																					{
																						{
																							double[] var119 = state.m[traceTempVariable$var118$2_1];
																							if(((Math.log(1.0) + ((((((0.0 <= state.st[index$sample$2_2][timeStep$var113]) && (state.st[index$sample$2_2][timeStep$var113] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var119[state.st[index$sample$2_2][timeStep$var113]])) && (var119[state.st[index$sample$2_2][timeStep$var113]] <= 1.0))?Math.log(var119[state.st[index$sample$2_2][timeStep$var113]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.st[index$sample$2_2][timeStep$var113]) && (state.st[index$sample$2_2][timeStep$var113] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var119[state.st[index$sample$2_2][timeStep$var113]])) && (var119[state.st[index$sample$2_2][timeStep$var113]] <= 1.0))?Math.log(var119[state.st[index$sample$2_2][timeStep$var113]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.st[index$sample$2_2][timeStep$var113]) && (state.st[index$sample$2_2][timeStep$var113] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var119[state.st[index$sample$2_2][timeStep$var113]])) && (var119[state.st[index$sample$2_2][timeStep$var113]] <= 1.0))?Math.log(var119[state.st[index$sample$2_2][timeStep$var113]]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.st[index$sample$2_2][timeStep$var113]) && (state.st[index$sample$2_2][timeStep$var113] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var119[state.st[index$sample$2_2][timeStep$var113]])) && (var119[state.st[index$sample$2_2][timeStep$var113]] <= 1.0))?Math.log(var119[state.st[index$sample$2_2][timeStep$var113]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.st[index$sample$2_2][timeStep$var113]) && (state.st[index$sample$2_2][timeStep$var113] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var119[state.st[index$sample$2_2][timeStep$var113]])) && (var119[state.st[index$sample$2_2][timeStep$var113]] <= 1.0))?Math.log(var119[state.st[index$sample$2_2][timeStep$var113]]):Double.NEGATIVE_INFINITY)));
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
								for(int index$sample$8_2 = 0; index$sample$8_2 < state.noSamples; index$sample$8_2 += 1) {
									if((sample == index$sample$8_2)) {
										for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$8_2]; timeStep$var136 += 1) {
											if((0 == timeStep$var136)) {
												{
													{
														boolean cv$sampleConstrained = true;
														if(cv$sampleConstrained) {
															state.constrainedFlag$sample104[((sample - 0) / 1)] = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	for(int var82 = 0; var82 < state.noStates; var82 += 1) {
																		if((var82 == traceTempVariable$currentState$8_1)) {
																			{
																				{
																					{
																						double var139 = state.metric_valid_bias[traceTempVariable$currentState$8_1];
																						if(((Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$8_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$8_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$8_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$8_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$8_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)));
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
							boolean[][] guard$sample104gaussian156 = scratch.guard$sample104gaussian156$global[threadID$cv$sample];
							{
								for(int index$sample$12_1 = 0; index$sample$12_1 < state.noSamples; index$sample$12_1 += 1) {
									if((sample == index$sample$12_1)) {
										for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$12_1]; timeStep$var136 += 1) {
											if((0 == timeStep$var136)) {
												if(state.metric_valid_g[index$sample$12_1][timeStep$var136])
													guard$sample104gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
											}
										}
									}
								}
							}
							{
								for(int index$sample$13_1 = 0; index$sample$13_1 < state.noSamples; index$sample$13_1 += 1) {
									if((sample == index$sample$13_1)) {
										for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$13_1]; timeStep$var136 += 1) {
											if((0 == timeStep$var136)) {
												if(state.metric_valid_g[index$sample$13_1][timeStep$var136])
													guard$sample104gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
											}
										}
									}
								}
							}
							{
								int traceTempVariable$currentState$14_1 = cv$currentValue;
								for(int index$sample$14_2 = 0; index$sample$14_2 < state.noSamples; index$sample$14_2 += 1) {
									if((sample == index$sample$14_2)) {
										for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$14_2]; timeStep$var136 += 1) {
											if((0 == timeStep$var136)) {
												if(state.metric_valid_g[index$sample$14_2][timeStep$var136]) {
													if(!guard$sample104gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
														guard$sample104gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
														{
															{
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	state.constrainedFlag$sample104[((sample - 0) / 1)] = true;
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																				if((var50 == traceTempVariable$currentState$14_1)) {
																					{
																						int traceTempVariable$currentState$19_1 = cv$currentValue;
																						if((index$sample$1 == index$sample$14_2)) {
																							if((0 == timeStep$var136)) {
																								{
																									for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																										if((var66 == traceTempVariable$currentState$19_1)) {
																											{
																												{
																													{
																														double var148 = state.metric_mean[traceTempVariable$currentState$19_1];
																														double var149 = state.metric_var[traceTempVariable$currentState$19_1];
																														if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																					for(int index$sample$20 = 0; index$sample$20 < state.noSamples; index$sample$20 += 1) {
																						if(!(index$sample$20 == index$sample$1)) {
																							for(int index$sample104$21 = 0; index$sample104$21 < state.noStates; index$sample104$21 += 1) {
																								int distributionTempVariable$var102$23 = index$sample104$21;
																								double cv$probabilitySample104Value22 = (1.0 * state.distribution$sample104[((index$sample$20 - 0) / 1)][index$sample104$21]);
																								{
																									int traceTempVariable$currentState$24_1 = distributionTempVariable$var102$23;
																									if((index$sample$20 == index$sample$14_2)) {
																										if((0 == timeStep$var136)) {
																											{
																												for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																													if((var66 == traceTempVariable$currentState$24_1)) {
																														{
																															{
																																{
																																	double var148 = state.metric_mean[traceTempVariable$currentState$24_1];
																																	double var149 = state.metric_var[traceTempVariable$currentState$24_1];
																																	if(((Math.log(cv$probabilitySample104Value22) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value22) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value22) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value22) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value22) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																			for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																				if((var50 == traceTempVariable$currentState$14_1)) {
																					if(state.fixedFlag$sample123) {
																						{
																							for(int index$sample$28_1 = 0; index$sample$28_1 < state.noSamples; index$sample$28_1 += 1) {
																								for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$28_1]; timeStep$var113 += 1) {
																									if((index$sample$28_1 == index$sample$14_2)) {
																										if((timeStep$var113 == timeStep$var136)) {
																											{
																												for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																													if((var66 == traceTempVariable$currentState$14_1)) {
																														{
																															{
																																{
																																	double var148 = state.metric_mean[traceTempVariable$currentState$14_1];
																																	double var149 = state.metric_var[traceTempVariable$currentState$14_1];
																																	if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																						for(int index$sample$29 = 0; index$sample$29 < state.noSamples; index$sample$29 += 1) {
																							for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$29]; timeStep$var113 += 1) {
																								if(true) {
																									for(int index$sample123$31 = 0; index$sample123$31 < state.noStates; index$sample123$31 += 1) {
																										int distributionTempVariable$var121$33 = index$sample123$31;
																										double cv$probabilitySample123Value32 = (1.0 * state.distribution$sample123[((index$sample$29 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$31]);
																										{
																											int traceTempVariable$currentState$34_1 = distributionTempVariable$var121$33;
																											if((index$sample$29 == index$sample$14_2)) {
																												if((timeStep$var113 == timeStep$var136)) {
																													{
																														for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																															if((var66 == traceTempVariable$currentState$34_1)) {
																																{
																																	{
																																		{
																																			double var148 = state.metric_mean[traceTempVariable$currentState$34_1];
																																			double var149 = state.metric_var[traceTempVariable$currentState$34_1];
																																			if(((Math.log(cv$probabilitySample123Value32) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value32) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value32) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value32) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value32) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$14_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
								for(int index$sample$15_2 = 0; index$sample$15_2 < state.noSamples; index$sample$15_2 += 1) {
									if((sample == index$sample$15_2)) {
										for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$15_2]; timeStep$var136 += 1) {
											if((0 == timeStep$var136)) {
												if(state.metric_valid_g[index$sample$15_2][timeStep$var136]) {
													if(!guard$sample104gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
														guard$sample104gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
														{
															{
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	state.constrainedFlag$sample104[((sample - 0) / 1)] = true;
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			int traceTempVariable$currentState$37_1 = cv$currentValue;
																			if((index$sample$1 == index$sample$15_2)) {
																				if((0 == timeStep$var136)) {
																					{
																						for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																							if((var50 == traceTempVariable$currentState$37_1)) {
																								{
																									for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																										if((var66 == traceTempVariable$currentState$37_1)) {
																											{
																												{
																													{
																														double var148 = state.metric_mean[traceTempVariable$currentState$37_1];
																														double var149 = state.metric_var[traceTempVariable$currentState$37_1];
																														if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																		for(int index$sample$38 = 0; index$sample$38 < state.noSamples; index$sample$38 += 1) {
																			if(!(index$sample$38 == index$sample$1)) {
																				for(int index$sample104$39 = 0; index$sample104$39 < state.noStates; index$sample104$39 += 1) {
																					int distributionTempVariable$var102$41 = index$sample104$39;
																					double cv$probabilitySample104Value40 = (1.0 * state.distribution$sample104[((index$sample$38 - 0) / 1)][index$sample104$39]);
																					{
																						int traceTempVariable$currentState$42_1 = distributionTempVariable$var102$41;
																						if((index$sample$38 == index$sample$15_2)) {
																							if((0 == timeStep$var136)) {
																								{
																									for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																										if((var50 == traceTempVariable$currentState$42_1)) {
																											{
																												for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																													if((var66 == traceTempVariable$currentState$42_1)) {
																														{
																															{
																																{
																																	double var148 = state.metric_mean[traceTempVariable$currentState$42_1];
																																	double var149 = state.metric_var[traceTempVariable$currentState$42_1];
																																	if(((Math.log(cv$probabilitySample104Value40) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value40) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value40) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value40) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value40) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																		if(state.fixedFlag$sample123) {
																			{
																				for(int index$sample$47_1 = 0; index$sample$47_1 < state.noSamples; index$sample$47_1 += 1) {
																					for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$47_1]; timeStep$var113 += 1) {
																						if((index$sample$47_1 == index$sample$15_2)) {
																							if((timeStep$var113 == timeStep$var136)) {
																								{
																									for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																										if((var50 == traceTempVariable$currentState$15_1)) {
																											{
																												for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																													if((var66 == traceTempVariable$currentState$15_1)) {
																														{
																															{
																																{
																																	double var148 = state.metric_mean[traceTempVariable$currentState$15_1];
																																	double var149 = state.metric_var[traceTempVariable$currentState$15_1];
																																	if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																			for(int index$sample$48 = 0; index$sample$48 < state.noSamples; index$sample$48 += 1) {
																				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$48]; timeStep$var113 += 1) {
																					if(true) {
																						for(int index$sample123$50 = 0; index$sample123$50 < state.noStates; index$sample123$50 += 1) {
																							int distributionTempVariable$var121$52 = index$sample123$50;
																							double cv$probabilitySample123Value51 = (1.0 * state.distribution$sample123[((index$sample$48 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$50]);
																							{
																								int traceTempVariable$currentState$53_1 = distributionTempVariable$var121$52;
																								if((index$sample$48 == index$sample$15_2)) {
																									if((timeStep$var113 == timeStep$var136)) {
																										{
																											for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																												if((var50 == traceTempVariable$currentState$53_1)) {
																													{
																														for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																															if((var66 == traceTempVariable$currentState$53_1)) {
																																{
																																	{
																																		{
																																			double var148 = state.metric_mean[traceTempVariable$currentState$53_1];
																																			double var149 = state.metric_var[traceTempVariable$currentState$53_1];
																																			if(((Math.log(cv$probabilitySample123Value51) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value51) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value51) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value51) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value51) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$15_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
							for(int index$sample$66_2 = 0; index$sample$66_2 < state.noSamples; index$sample$66_2 += 1) {
								if((sample == index$sample$66_2)) {
									for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$66_2]; timeStep$var113 += 1) {
										if((0 == (timeStep$var113 - 1))) {
											if(!state.fixedFlag$sample123) {
												{
													{
														int index$timeStep$68 = timeStep$var113;
														int index$sample$69 = index$sample$66_2;
														double[] cv$accumulatedConsumerDistributions = scratch.cv$distributionAccumulator$var120[threadID$cv$sample];
														for(int cv$i = 0; cv$i < state.noStates; cv$i += 1)
															cv$accumulatedConsumerDistributions[cv$i] = 0.0;
														double cv$reachedDistributionProbability = 0.0;
														{
															for(int var31 = 0; var31 < state.noStates; var31 += 1) {
																if((var31 == traceTempVariable$var118$66_1)) {
																	{
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		double[] var119 = state.m[traceTempVariable$var118$66_1];
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																		cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
																		DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, var119, state.noStates);
																	}
																}
															}
														}
														double[] cv$sampleDistribution = state.distribution$sample123[((index$sample$66_2 - 0) / 1)][((timeStep$var113 - 1) / 1)];
														double cv$overlap = 0.0;
														for(int cv$i = 0; cv$i < state.noStates; cv$i += 1) {
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
			if(state.constrainedFlag$sample104[((sample - 0) / 1)]) {
				double[] cv$localProbability = state.distribution$sample104[((sample - 0) / 1)];
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

	private final void inferSample123(int sample, int timeStep$var113, int threadID$cv$sample, Rng RNG$) {
		int index$timeStep$1 = timeStep$var113;
		int index$sample$2 = sample;
		if(true) {
			state.constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = false;
			int cv$numStates = 0;
			if(state.fixedFlag$sample104) {
				{
					for(int index$sample$3_1 = 0; index$sample$3_1 < state.noSamples; index$sample$3_1 += 1) {
						if((index$sample$3_1 == sample)) {
							if((0 == (timeStep$var113 - 1))) {
								{
									for(int var31 = 0; var31 < state.noStates; var31 += 1) {
										if((var31 == state.st[sample][(timeStep$var113 - 1)]))
											cv$numStates = Math.max(cv$numStates, state.noStates);
									}
								}
							}
						}
					}
				}
			} else {
				for(int index$sample$4 = 0; index$sample$4 < state.noSamples; index$sample$4 += 1) {
					if(true) {
						for(int index$sample104$5 = 0; index$sample104$5 < state.noStates; index$sample104$5 += 1) {
							int distributionTempVariable$var102$7 = index$sample104$5;
							double cv$probabilitySample104Value6 = (1.0 * state.distribution$sample104[((index$sample$4 - 0) / 1)][index$sample104$5]);
							{
								int traceTempVariable$var118$8_1 = distributionTempVariable$var102$7;
								if((index$sample$4 == sample)) {
									if((0 == (timeStep$var113 - 1))) {
										{
											for(int var31 = 0; var31 < state.noStates; var31 += 1) {
												if((var31 == traceTempVariable$var118$8_1))
													cv$numStates = Math.max(cv$numStates, state.noStates);
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
							for(int var31 = 0; var31 < state.noStates; var31 += 1) {
								if((var31 == state.st[sample][(timeStep$var113 - 1)]))
									cv$numStates = Math.max(cv$numStates, state.noStates);
							}
						}
					}
				}
			}
			for(int index$sample$12 = 0; index$sample$12 < state.noSamples; index$sample$12 += 1) {
				for(int index$timeStep$13 = 1; index$timeStep$13 < state.length$metric[index$sample$12]; index$timeStep$13 += 1) {
					if(!((index$timeStep$13 == index$timeStep$1) && (index$sample$12 == index$sample$2))) {
						for(int index$sample123$14 = 0; index$sample123$14 < state.noStates; index$sample123$14 += 1) {
							int distributionTempVariable$var121$16 = index$sample123$14;
							double cv$probabilitySample123Value15 = (1.0 * state.distribution$sample123[((index$sample$12 - 0) / 1)][((index$timeStep$13 - 1) / 1)][index$sample123$14]);
							{
								int traceTempVariable$var118$17_1 = distributionTempVariable$var121$16;
								if((index$sample$12 == sample)) {
									if((index$timeStep$13 == (timeStep$var113 - 1))) {
										{
											for(int var31 = 0; var31 < state.noStates; var31 += 1) {
												if((var31 == traceTempVariable$var118$17_1))
													cv$numStates = Math.max(cv$numStates, state.noStates);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var121$stateProbabilityGlobal[threadID$cv$sample];
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				if(state.fixedFlag$sample104) {
					{
						for(int index$sample$20_1 = 0; index$sample$20_1 < state.noSamples; index$sample$20_1 += 1) {
							if((index$sample$20_1 == sample)) {
								if((0 == (timeStep$var113 - 1))) {
									{
										for(int var31 = 0; var31 < state.noStates; var31 += 1) {
											if((var31 == state.st[sample][(timeStep$var113 - 1)])) {
												cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
												double[] var119 = state.m[state.st[sample][(timeStep$var113 - 1)]];
												double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var119[cv$currentValue])) && (var119[cv$currentValue] <= 1.0))?Math.log(var119[cv$currentValue]):Double.NEGATIVE_INFINITY));
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
															for(int index$sample$41_2 = 0; index$sample$41_2 < state.noSamples; index$sample$41_2 += 1) {
																if((sample == index$sample$41_2)) {
																	for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$41_2]; timeStep$var136 += 1) {
																		if((timeStep$var113 == timeStep$var136)) {
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						state.constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								for(int var82 = 0; var82 < state.noStates; var82 += 1) {
																									if((var82 == traceTempVariable$currentState$41_1)) {
																										{
																											{
																												{
																													double var139 = state.metric_valid_bias[traceTempVariable$currentState$41_1];
																													if(((Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$41_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$41_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$41_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$41_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$41_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)));
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
														boolean[][] guard$sample123gaussian156 = scratch.guard$sample123gaussian156$global[threadID$cv$sample];
														{
															for(int index$sample$57_1 = 0; index$sample$57_1 < state.noSamples; index$sample$57_1 += 1) {
																if((sample == index$sample$57_1)) {
																	for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$57_1]; timeStep$var136 += 1) {
																		if((timeStep$var113 == timeStep$var136)) {
																			if(state.metric_valid_g[index$sample$57_1][timeStep$var136])
																				guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
														{
															for(int index$sample$61_1 = 0; index$sample$61_1 < state.noSamples; index$sample$61_1 += 1) {
																if((sample == index$sample$61_1)) {
																	for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$61_1]; timeStep$var136 += 1) {
																		if((timeStep$var113 == timeStep$var136)) {
																			if(state.metric_valid_g[index$sample$61_1][timeStep$var136])
																				guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
														{
															int traceTempVariable$currentState$65_1 = cv$currentValue;
															for(int index$sample$65_2 = 0; index$sample$65_2 < state.noSamples; index$sample$65_2 += 1) {
																if((sample == index$sample$65_2)) {
																	for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$65_2]; timeStep$var136 += 1) {
																		if((timeStep$var113 == timeStep$var136)) {
																			if(state.metric_valid_g[index$sample$65_2][timeStep$var136]) {
																				if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																					guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																					{
																						{
																							boolean cv$sampleConstrained = true;
																							if(cv$sampleConstrained) {
																								state.constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																								double cv$consumerDistributionProbabilityAccumulator = 1.0;
																								{
																									{
																										for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																											if((var50 == traceTempVariable$currentState$65_1)) {
																												{
																													for(int index$sample$82_1 = 0; index$sample$82_1 < state.noSamples; index$sample$82_1 += 1) {
																														if((index$sample$82_1 == index$sample$65_2)) {
																															if((0 == timeStep$var136)) {
																																{
																																	for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																		if((var66 == traceTempVariable$currentState$65_1)) {
																																			{
																																				{
																																					{
																																						double var148 = state.metric_mean[traceTempVariable$currentState$65_1];
																																						double var149 = state.metric_var[traceTempVariable$currentState$65_1];
																																						if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																										for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																											if((var50 == traceTempVariable$currentState$65_1)) {
																												{
																													int traceTempVariable$currentState$85_1 = cv$currentValue;
																													if((index$sample$2 == index$sample$65_2)) {
																														if((index$timeStep$1 == timeStep$var136)) {
																															{
																																for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																	if((var66 == traceTempVariable$currentState$85_1)) {
																																		{
																																			{
																																				{
																																					double var148 = state.metric_mean[traceTempVariable$currentState$85_1];
																																					double var149 = state.metric_var[traceTempVariable$currentState$85_1];
																																					if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																												for(int index$sample$86 = 0; index$sample$86 < state.noSamples; index$sample$86 += 1) {
																													for(int index$timeStep$87 = 1; index$timeStep$87 < state.length$metric[index$sample$86]; index$timeStep$87 += 1) {
																														if(!((index$timeStep$87 == index$timeStep$1) && (index$sample$86 == index$sample$2))) {
																															for(int index$sample123$88 = 0; index$sample123$88 < state.noStates; index$sample123$88 += 1) {
																																int distributionTempVariable$var121$90 = index$sample123$88;
																																double cv$probabilitySample123Value89 = (1.0 * state.distribution$sample123[((index$sample$86 - 0) / 1)][((index$timeStep$87 - 1) / 1)][index$sample123$88]);
																																{
																																	int traceTempVariable$currentState$91_1 = distributionTempVariable$var121$90;
																																	if((index$sample$86 == index$sample$65_2)) {
																																		if((index$timeStep$87 == timeStep$var136)) {
																																			{
																																				for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																					if((var66 == traceTempVariable$currentState$91_1)) {
																																						{
																																							{
																																								{
																																									double var148 = state.metric_mean[traceTempVariable$currentState$91_1];
																																									double var149 = state.metric_var[traceTempVariable$currentState$91_1];
																																									if(((Math.log(cv$probabilitySample123Value89) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value89) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value89) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value89) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value89) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$65_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
															for(int index$sample$69_2 = 0; index$sample$69_2 < state.noSamples; index$sample$69_2 += 1) {
																if((sample == index$sample$69_2)) {
																	for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$69_2]; timeStep$var136 += 1) {
																		if((timeStep$var113 == timeStep$var136)) {
																			if(state.metric_valid_g[index$sample$69_2][timeStep$var136]) {
																				if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																					guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																					{
																						{
																							boolean cv$sampleConstrained = true;
																							if(cv$sampleConstrained) {
																								state.constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																								double cv$consumerDistributionProbabilityAccumulator = 1.0;
																								{
																									{
																										for(int index$sample$153_1 = 0; index$sample$153_1 < state.noSamples; index$sample$153_1 += 1) {
																											if((index$sample$153_1 == index$sample$69_2)) {
																												if((0 == timeStep$var136)) {
																													{
																														for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																															if((var50 == traceTempVariable$currentState$69_1)) {
																																{
																																	for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																		if((var66 == traceTempVariable$currentState$69_1)) {
																																			{
																																				{
																																					{
																																						double var148 = state.metric_mean[traceTempVariable$currentState$69_1];
																																						double var149 = state.metric_var[traceTempVariable$currentState$69_1];
																																						if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																													for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																														if((var50 == traceTempVariable$currentState$156_1)) {
																															{
																																for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																	if((var66 == traceTempVariable$currentState$156_1)) {
																																		{
																																			{
																																				{
																																					double var148 = state.metric_mean[traceTempVariable$currentState$156_1];
																																					double var149 = state.metric_var[traceTempVariable$currentState$156_1];
																																					if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																									for(int index$sample$157 = 0; index$sample$157 < state.noSamples; index$sample$157 += 1) {
																										for(int index$timeStep$158 = 1; index$timeStep$158 < state.length$metric[index$sample$157]; index$timeStep$158 += 1) {
																											if(!((index$timeStep$158 == index$timeStep$1) && (index$sample$157 == index$sample$2))) {
																												for(int index$sample123$159 = 0; index$sample123$159 < state.noStates; index$sample123$159 += 1) {
																													int distributionTempVariable$var121$161 = index$sample123$159;
																													double cv$probabilitySample123Value160 = (1.0 * state.distribution$sample123[((index$sample$157 - 0) / 1)][((index$timeStep$158 - 1) / 1)][index$sample123$159]);
																													{
																														int traceTempVariable$currentState$162_1 = distributionTempVariable$var121$161;
																														if((index$sample$157 == index$sample$69_2)) {
																															if((index$timeStep$158 == timeStep$var136)) {
																																{
																																	for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																																		if((var50 == traceTempVariable$currentState$162_1)) {
																																			{
																																				for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																					if((var66 == traceTempVariable$currentState$162_1)) {
																																						{
																																							{
																																								{
																																									double var148 = state.metric_mean[traceTempVariable$currentState$162_1];
																																									double var149 = state.metric_var[traceTempVariable$currentState$162_1];
																																									if(((Math.log(cv$probabilitySample123Value160) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value160) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value160) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value160) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value160) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$69_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
					for(int index$sample$21 = 0; index$sample$21 < state.noSamples; index$sample$21 += 1) {
						if(true) {
							for(int index$sample104$22 = 0; index$sample104$22 < state.noStates; index$sample104$22 += 1) {
								int distributionTempVariable$var102$24 = index$sample104$22;
								double cv$probabilitySample104Value23 = (1.0 * state.distribution$sample104[((index$sample$21 - 0) / 1)][index$sample104$22]);
								{
									int traceTempVariable$var118$25_1 = distributionTempVariable$var102$24;
									if((index$sample$21 == sample)) {
										if((0 == (timeStep$var113 - 1))) {
											{
												for(int var31 = 0; var31 < state.noStates; var31 += 1) {
													if((var31 == traceTempVariable$var118$25_1)) {
														cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample104Value23);
														double[] var119 = state.m[traceTempVariable$var118$25_1];
														double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample104Value23) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var119[cv$currentValue])) && (var119[cv$currentValue] <= 1.0))?Math.log(var119[cv$currentValue]):Double.NEGATIVE_INFINITY));
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
																	for(int index$sample$42_2 = 0; index$sample$42_2 < state.noSamples; index$sample$42_2 += 1) {
																		if((sample == index$sample$42_2)) {
																			for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$42_2]; timeStep$var136 += 1) {
																				if((timeStep$var113 == timeStep$var136)) {
																					{
																						{
																							boolean cv$sampleConstrained = true;
																							if(cv$sampleConstrained) {
																								state.constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																								double cv$consumerDistributionProbabilityAccumulator = 1.0;
																								{
																									{
																										for(int var82 = 0; var82 < state.noStates; var82 += 1) {
																											if((var82 == traceTempVariable$currentState$42_1)) {
																												{
																													{
																														{
																															double var139 = state.metric_valid_bias[traceTempVariable$currentState$42_1];
																															if(((Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$42_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$42_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$42_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$42_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$42_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)));
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
																boolean[][] guard$sample123gaussian156 = scratch.guard$sample123gaussian156$global[threadID$cv$sample];
																{
																	for(int index$sample$58_1 = 0; index$sample$58_1 < state.noSamples; index$sample$58_1 += 1) {
																		if((sample == index$sample$58_1)) {
																			for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$58_1]; timeStep$var136 += 1) {
																				if((timeStep$var113 == timeStep$var136)) {
																					if(state.metric_valid_g[index$sample$58_1][timeStep$var136])
																						guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
																				}
																			}
																		}
																	}
																}
																{
																	for(int index$sample$62_1 = 0; index$sample$62_1 < state.noSamples; index$sample$62_1 += 1) {
																		if((sample == index$sample$62_1)) {
																			for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$62_1]; timeStep$var136 += 1) {
																				if((timeStep$var113 == timeStep$var136)) {
																					if(state.metric_valid_g[index$sample$62_1][timeStep$var136])
																						guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
																				}
																			}
																		}
																	}
																}
																{
																	int traceTempVariable$currentState$66_1 = cv$currentValue;
																	for(int index$sample$66_2 = 0; index$sample$66_2 < state.noSamples; index$sample$66_2 += 1) {
																		if((sample == index$sample$66_2)) {
																			for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$66_2]; timeStep$var136 += 1) {
																				if((timeStep$var113 == timeStep$var136)) {
																					if(state.metric_valid_g[index$sample$66_2][timeStep$var136]) {
																						if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																							guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																							{
																								{
																									boolean cv$sampleConstrained = true;
																									if(cv$sampleConstrained) {
																										state.constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																										double cv$consumerDistributionProbabilityAccumulator = 1.0;
																										{
																											{
																												for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																													if((var50 == traceTempVariable$currentState$66_1)) {
																														{
																															int traceTempVariable$currentState$95_1 = distributionTempVariable$var102$24;
																															if((index$sample$21 == index$sample$66_2)) {
																																if((0 == timeStep$var136)) {
																																	{
																																		for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$95_1)) {
																																				{
																																					{
																																						{
																																							double var148 = state.metric_mean[traceTempVariable$currentState$95_1];
																																							double var149 = state.metric_var[traceTempVariable$currentState$95_1];
																																							if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																														for(int index$sample$96 = 0; index$sample$96 < state.noSamples; index$sample$96 += 1) {
																															if(!(index$sample$96 == index$sample$21)) {
																																for(int index$sample104$97 = 0; index$sample104$97 < state.noStates; index$sample104$97 += 1) {
																																	int distributionTempVariable$var102$99 = index$sample104$97;
																																	double cv$probabilitySample104Value98 = (1.0 * state.distribution$sample104[((index$sample$96 - 0) / 1)][index$sample104$97]);
																																	{
																																		int traceTempVariable$currentState$100_1 = distributionTempVariable$var102$99;
																																		if((index$sample$96 == index$sample$66_2)) {
																																			if((0 == timeStep$var136)) {
																																				{
																																					for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																						if((var66 == traceTempVariable$currentState$100_1)) {
																																							{
																																								{
																																									{
																																										double var148 = state.metric_mean[traceTempVariable$currentState$100_1];
																																										double var149 = state.metric_var[traceTempVariable$currentState$100_1];
																																										if(((Math.log(cv$probabilitySample104Value98) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value98) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value98) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value98) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value98) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																												for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																													if((var50 == traceTempVariable$currentState$66_1)) {
																														{
																															int traceTempVariable$currentState$104_1 = cv$currentValue;
																															if((index$sample$2 == index$sample$66_2)) {
																																if((index$timeStep$1 == timeStep$var136)) {
																																	{
																																		for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$104_1)) {
																																				{
																																					{
																																						{
																																							double var148 = state.metric_mean[traceTempVariable$currentState$104_1];
																																							double var149 = state.metric_var[traceTempVariable$currentState$104_1];
																																							if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																														for(int index$sample$105 = 0; index$sample$105 < state.noSamples; index$sample$105 += 1) {
																															for(int index$timeStep$106 = 1; index$timeStep$106 < state.length$metric[index$sample$105]; index$timeStep$106 += 1) {
																																if(!((index$timeStep$106 == index$timeStep$1) && (index$sample$105 == index$sample$2))) {
																																	for(int index$sample123$107 = 0; index$sample123$107 < state.noStates; index$sample123$107 += 1) {
																																		int distributionTempVariable$var121$109 = index$sample123$107;
																																		double cv$probabilitySample123Value108 = (1.0 * state.distribution$sample123[((index$sample$105 - 0) / 1)][((index$timeStep$106 - 1) / 1)][index$sample123$107]);
																																		{
																																			int traceTempVariable$currentState$110_1 = distributionTempVariable$var121$109;
																																			if((index$sample$105 == index$sample$66_2)) {
																																				if((index$timeStep$106 == timeStep$var136)) {
																																					{
																																						for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																							if((var66 == traceTempVariable$currentState$110_1)) {
																																								{
																																									{
																																										{
																																											double var148 = state.metric_mean[traceTempVariable$currentState$110_1];
																																											double var149 = state.metric_var[traceTempVariable$currentState$110_1];
																																											if(((Math.log(cv$probabilitySample123Value108) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value108) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																											else {
																																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value108) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																												else
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value108) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value108) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$66_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																	for(int index$sample$70_2 = 0; index$sample$70_2 < state.noSamples; index$sample$70_2 += 1) {
																		if((sample == index$sample$70_2)) {
																			for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$70_2]; timeStep$var136 += 1) {
																				if((timeStep$var113 == timeStep$var136)) {
																					if(state.metric_valid_g[index$sample$70_2][timeStep$var136]) {
																						if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																							guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																							{
																								{
																									boolean cv$sampleConstrained = true;
																									if(cv$sampleConstrained) {
																										state.constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																										double cv$consumerDistributionProbabilityAccumulator = 1.0;
																										{
																											{
																												int traceTempVariable$currentState$167_1 = distributionTempVariable$var102$24;
																												if((index$sample$21 == index$sample$70_2)) {
																													if((0 == timeStep$var136)) {
																														{
																															for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																																if((var50 == traceTempVariable$currentState$167_1)) {
																																	{
																																		for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$167_1)) {
																																				{
																																					{
																																						{
																																							double var148 = state.metric_mean[traceTempVariable$currentState$167_1];
																																							double var149 = state.metric_var[traceTempVariable$currentState$167_1];
																																							if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																											for(int index$sample$168 = 0; index$sample$168 < state.noSamples; index$sample$168 += 1) {
																												if(!(index$sample$168 == index$sample$21)) {
																													for(int index$sample104$169 = 0; index$sample104$169 < state.noStates; index$sample104$169 += 1) {
																														int distributionTempVariable$var102$171 = index$sample104$169;
																														double cv$probabilitySample104Value170 = (1.0 * state.distribution$sample104[((index$sample$168 - 0) / 1)][index$sample104$169]);
																														{
																															int traceTempVariable$currentState$172_1 = distributionTempVariable$var102$171;
																															if((index$sample$168 == index$sample$70_2)) {
																																if((0 == timeStep$var136)) {
																																	{
																																		for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																																			if((var50 == traceTempVariable$currentState$172_1)) {
																																				{
																																					for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																						if((var66 == traceTempVariable$currentState$172_1)) {
																																							{
																																								{
																																									{
																																										double var148 = state.metric_mean[traceTempVariable$currentState$172_1];
																																										double var149 = state.metric_var[traceTempVariable$currentState$172_1];
																																										if(((Math.log(cv$probabilitySample104Value170) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value170) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value170) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value170) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value170) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																															for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																																if((var50 == traceTempVariable$currentState$177_1)) {
																																	{
																																		for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$177_1)) {
																																				{
																																					{
																																						{
																																							double var148 = state.metric_mean[traceTempVariable$currentState$177_1];
																																							double var149 = state.metric_var[traceTempVariable$currentState$177_1];
																																							if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																											for(int index$sample$178 = 0; index$sample$178 < state.noSamples; index$sample$178 += 1) {
																												for(int index$timeStep$179 = 1; index$timeStep$179 < state.length$metric[index$sample$178]; index$timeStep$179 += 1) {
																													if(!((index$timeStep$179 == index$timeStep$1) && (index$sample$178 == index$sample$2))) {
																														for(int index$sample123$180 = 0; index$sample123$180 < state.noStates; index$sample123$180 += 1) {
																															int distributionTempVariable$var121$182 = index$sample123$180;
																															double cv$probabilitySample123Value181 = (1.0 * state.distribution$sample123[((index$sample$178 - 0) / 1)][((index$timeStep$179 - 1) / 1)][index$sample123$180]);
																															{
																																int traceTempVariable$currentState$183_1 = distributionTempVariable$var121$182;
																																if((index$sample$178 == index$sample$70_2)) {
																																	if((index$timeStep$179 == timeStep$var136)) {
																																		{
																																			for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																																				if((var50 == traceTempVariable$currentState$183_1)) {
																																					{
																																						for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																							if((var66 == traceTempVariable$currentState$183_1)) {
																																								{
																																									{
																																										{
																																											double var148 = state.metric_mean[traceTempVariable$currentState$183_1];
																																											double var149 = state.metric_var[traceTempVariable$currentState$183_1];
																																											if(((Math.log(cv$probabilitySample123Value181) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value181) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																											else {
																																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value181) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																												else
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value181) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value181) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$70_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
								for(int var31 = 0; var31 < state.noStates; var31 += 1) {
									if((var31 == traceTempVariable$var118$28_1)) {
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
										double[] var119 = state.m[traceTempVariable$var118$28_1];
										double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var119[cv$currentValue])) && (var119[cv$currentValue] <= 1.0))?Math.log(var119[cv$currentValue]):Double.NEGATIVE_INFINITY));
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
													for(int index$sample$43_2 = 0; index$sample$43_2 < state.noSamples; index$sample$43_2 += 1) {
														if((sample == index$sample$43_2)) {
															for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$43_2]; timeStep$var136 += 1) {
																if((timeStep$var113 == timeStep$var136)) {
																	{
																		{
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				state.constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						for(int var82 = 0; var82 < state.noStates; var82 += 1) {
																							if((var82 == traceTempVariable$currentState$43_1)) {
																								{
																									{
																										{
																											double var139 = state.metric_valid_bias[traceTempVariable$currentState$43_1];
																											if(((Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$43_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$43_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$43_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$43_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$43_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)));
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
												boolean[][] guard$sample123gaussian156 = scratch.guard$sample123gaussian156$global[threadID$cv$sample];
												{
													for(int index$sample$59_1 = 0; index$sample$59_1 < state.noSamples; index$sample$59_1 += 1) {
														if((sample == index$sample$59_1)) {
															for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$59_1]; timeStep$var136 += 1) {
																if((timeStep$var113 == timeStep$var136)) {
																	if(state.metric_valid_g[index$sample$59_1][timeStep$var136])
																		guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
																}
															}
														}
													}
												}
												{
													for(int index$sample$63_1 = 0; index$sample$63_1 < state.noSamples; index$sample$63_1 += 1) {
														if((sample == index$sample$63_1)) {
															for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$63_1]; timeStep$var136 += 1) {
																if((timeStep$var113 == timeStep$var136)) {
																	if(state.metric_valid_g[index$sample$63_1][timeStep$var136])
																		guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
																}
															}
														}
													}
												}
												{
													int traceTempVariable$currentState$67_1 = cv$currentValue;
													for(int index$sample$67_2 = 0; index$sample$67_2 < state.noSamples; index$sample$67_2 += 1) {
														if((sample == index$sample$67_2)) {
															for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$67_2]; timeStep$var136 += 1) {
																if((timeStep$var113 == timeStep$var136)) {
																	if(state.metric_valid_g[index$sample$67_2][timeStep$var136]) {
																		if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																			guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						state.constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																									if((var50 == traceTempVariable$currentState$67_1)) {
																										if(state.fixedFlag$sample104) {
																											{
																												for(int index$sample$114_1 = 0; index$sample$114_1 < state.noSamples; index$sample$114_1 += 1) {
																													if((index$sample$114_1 == index$sample$67_2)) {
																														if((0 == timeStep$var136)) {
																															{
																																for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																	if((var66 == traceTempVariable$currentState$67_1)) {
																																		{
																																			{
																																				{
																																					double var148 = state.metric_mean[traceTempVariable$currentState$67_1];
																																					double var149 = state.metric_var[traceTempVariable$currentState$67_1];
																																					if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																											for(int index$sample$115 = 0; index$sample$115 < state.noSamples; index$sample$115 += 1) {
																												if(true) {
																													for(int index$sample104$116 = 0; index$sample104$116 < state.noStates; index$sample104$116 += 1) {
																														int distributionTempVariable$var102$118 = index$sample104$116;
																														double cv$probabilitySample104Value117 = (1.0 * state.distribution$sample104[((index$sample$115 - 0) / 1)][index$sample104$116]);
																														{
																															int traceTempVariable$currentState$119_1 = distributionTempVariable$var102$118;
																															if((index$sample$115 == index$sample$67_2)) {
																																if((0 == timeStep$var136)) {
																																	{
																																		for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$119_1)) {
																																				{
																																					{
																																						{
																																							double var148 = state.metric_mean[traceTempVariable$currentState$119_1];
																																							double var149 = state.metric_var[traceTempVariable$currentState$119_1];
																																							if(((Math.log(cv$probabilitySample104Value117) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value117) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value117) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value117) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value117) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																								for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																									if((var50 == traceTempVariable$currentState$67_1)) {
																										{
																											int traceTempVariable$currentState$123_1 = cv$currentValue;
																											if((index$sample$2 == index$sample$67_2)) {
																												if((index$timeStep$1 == timeStep$var136)) {
																													{
																														for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																															if((var66 == traceTempVariable$currentState$123_1)) {
																																{
																																	{
																																		{
																																			double var148 = state.metric_mean[traceTempVariable$currentState$123_1];
																																			double var149 = state.metric_var[traceTempVariable$currentState$123_1];
																																			if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																										for(int index$sample$124 = 0; index$sample$124 < state.noSamples; index$sample$124 += 1) {
																											for(int index$timeStep$125 = 1; index$timeStep$125 < state.length$metric[index$sample$124]; index$timeStep$125 += 1) {
																												if(!((index$timeStep$125 == index$timeStep$1) && (index$sample$124 == index$sample$2))) {
																													for(int index$sample123$126 = 0; index$sample123$126 < state.noStates; index$sample123$126 += 1) {
																														int distributionTempVariable$var121$128 = index$sample123$126;
																														double cv$probabilitySample123Value127 = (1.0 * state.distribution$sample123[((index$sample$124 - 0) / 1)][((index$timeStep$125 - 1) / 1)][index$sample123$126]);
																														{
																															int traceTempVariable$currentState$129_1 = distributionTempVariable$var121$128;
																															if((index$sample$124 == index$sample$67_2)) {
																																if((index$timeStep$125 == timeStep$var136)) {
																																	{
																																		for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$129_1)) {
																																				{
																																					{
																																						{
																																							double var148 = state.metric_mean[traceTempVariable$currentState$129_1];
																																							double var149 = state.metric_var[traceTempVariable$currentState$129_1];
																																							if(((Math.log(cv$probabilitySample123Value127) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value127) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value127) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value127) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value127) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$67_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
													for(int index$sample$71_2 = 0; index$sample$71_2 < state.noSamples; index$sample$71_2 += 1) {
														if((sample == index$sample$71_2)) {
															for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$71_2]; timeStep$var136 += 1) {
																if((timeStep$var113 == timeStep$var136)) {
																	if(state.metric_valid_g[index$sample$71_2][timeStep$var136]) {
																		if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																			guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						state.constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							if(state.fixedFlag$sample104) {
																								{
																									for(int index$sample$188_1 = 0; index$sample$188_1 < state.noSamples; index$sample$188_1 += 1) {
																										if((index$sample$188_1 == index$sample$71_2)) {
																											if((0 == timeStep$var136)) {
																												{
																													for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																														if((var50 == traceTempVariable$currentState$71_1)) {
																															{
																																for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																	if((var66 == traceTempVariable$currentState$71_1)) {
																																		{
																																			{
																																				{
																																					double var148 = state.metric_mean[traceTempVariable$currentState$71_1];
																																					double var149 = state.metric_var[traceTempVariable$currentState$71_1];
																																					if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																								for(int index$sample$189 = 0; index$sample$189 < state.noSamples; index$sample$189 += 1) {
																									if(true) {
																										for(int index$sample104$190 = 0; index$sample104$190 < state.noStates; index$sample104$190 += 1) {
																											int distributionTempVariable$var102$192 = index$sample104$190;
																											double cv$probabilitySample104Value191 = (1.0 * state.distribution$sample104[((index$sample$189 - 0) / 1)][index$sample104$190]);
																											{
																												int traceTempVariable$currentState$193_1 = distributionTempVariable$var102$192;
																												if((index$sample$189 == index$sample$71_2)) {
																													if((0 == timeStep$var136)) {
																														{
																															for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																																if((var50 == traceTempVariable$currentState$193_1)) {
																																	{
																																		for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$193_1)) {
																																				{
																																					{
																																						{
																																							double var148 = state.metric_mean[traceTempVariable$currentState$193_1];
																																							double var149 = state.metric_var[traceTempVariable$currentState$193_1];
																																							if(((Math.log(cv$probabilitySample104Value191) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value191) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value191) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value191) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value191) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																											for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																												if((var50 == traceTempVariable$currentState$198_1)) {
																													{
																														for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																															if((var66 == traceTempVariable$currentState$198_1)) {
																																{
																																	{
																																		{
																																			double var148 = state.metric_mean[traceTempVariable$currentState$198_1];
																																			double var149 = state.metric_var[traceTempVariable$currentState$198_1];
																																			if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																							for(int index$sample$199 = 0; index$sample$199 < state.noSamples; index$sample$199 += 1) {
																								for(int index$timeStep$200 = 1; index$timeStep$200 < state.length$metric[index$sample$199]; index$timeStep$200 += 1) {
																									if(!((index$timeStep$200 == index$timeStep$1) && (index$sample$199 == index$sample$2))) {
																										for(int index$sample123$201 = 0; index$sample123$201 < state.noStates; index$sample123$201 += 1) {
																											int distributionTempVariable$var121$203 = index$sample123$201;
																											double cv$probabilitySample123Value202 = (1.0 * state.distribution$sample123[((index$sample$199 - 0) / 1)][((index$timeStep$200 - 1) / 1)][index$sample123$201]);
																											{
																												int traceTempVariable$currentState$204_1 = distributionTempVariable$var121$203;
																												if((index$sample$199 == index$sample$71_2)) {
																													if((index$timeStep$200 == timeStep$var136)) {
																														{
																															for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																																if((var50 == traceTempVariable$currentState$204_1)) {
																																	{
																																		for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$204_1)) {
																																				{
																																					{
																																						{
																																							double var148 = state.metric_mean[traceTempVariable$currentState$204_1];
																																							double var149 = state.metric_var[traceTempVariable$currentState$204_1];
																																							if(((Math.log(cv$probabilitySample123Value202) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value202) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value202) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value202) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value202) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$71_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
				for(int index$sample$29 = 0; index$sample$29 < state.noSamples; index$sample$29 += 1) {
					for(int index$timeStep$30 = 1; index$timeStep$30 < state.length$metric[index$sample$29]; index$timeStep$30 += 1) {
						if(!((index$timeStep$30 == index$timeStep$1) && (index$sample$29 == index$sample$2))) {
							for(int index$sample123$31 = 0; index$sample123$31 < state.noStates; index$sample123$31 += 1) {
								int distributionTempVariable$var121$33 = index$sample123$31;
								double cv$probabilitySample123Value32 = (1.0 * state.distribution$sample123[((index$sample$29 - 0) / 1)][((index$timeStep$30 - 1) / 1)][index$sample123$31]);
								{
									int traceTempVariable$var118$34_1 = distributionTempVariable$var121$33;
									if((index$sample$29 == sample)) {
										if((index$timeStep$30 == (timeStep$var113 - 1))) {
											{
												for(int var31 = 0; var31 < state.noStates; var31 += 1) {
													if((var31 == traceTempVariable$var118$34_1)) {
														cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample123Value32);
														double[] var119 = state.m[traceTempVariable$var118$34_1];
														double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample123Value32) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var119[cv$currentValue])) && (var119[cv$currentValue] <= 1.0))?Math.log(var119[cv$currentValue]):Double.NEGATIVE_INFINITY));
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
																	for(int index$sample$44_2 = 0; index$sample$44_2 < state.noSamples; index$sample$44_2 += 1) {
																		if((sample == index$sample$44_2)) {
																			for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$44_2]; timeStep$var136 += 1) {
																				if((timeStep$var113 == timeStep$var136)) {
																					{
																						{
																							boolean cv$sampleConstrained = true;
																							if(cv$sampleConstrained) {
																								state.constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																								double cv$consumerDistributionProbabilityAccumulator = 1.0;
																								{
																									{
																										for(int var82 = 0; var82 < state.noStates; var82 += 1) {
																											if((var82 == traceTempVariable$currentState$44_1)) {
																												{
																													{
																														{
																															double var139 = state.metric_valid_bias[traceTempVariable$currentState$44_1];
																															if(((Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$44_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$44_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$44_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$44_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[index$sample$44_2][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY)));
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
																boolean[][] guard$sample123gaussian156 = scratch.guard$sample123gaussian156$global[threadID$cv$sample];
																{
																	for(int index$sample$60_1 = 0; index$sample$60_1 < state.noSamples; index$sample$60_1 += 1) {
																		if((sample == index$sample$60_1)) {
																			for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$60_1]; timeStep$var136 += 1) {
																				if((timeStep$var113 == timeStep$var136)) {
																					if(state.metric_valid_g[index$sample$60_1][timeStep$var136])
																						guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
																				}
																			}
																		}
																	}
																}
																{
																	for(int index$sample$64_1 = 0; index$sample$64_1 < state.noSamples; index$sample$64_1 += 1) {
																		if((sample == index$sample$64_1)) {
																			for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$64_1]; timeStep$var136 += 1) {
																				if((timeStep$var113 == timeStep$var136)) {
																					if(state.metric_valid_g[index$sample$64_1][timeStep$var136])
																						guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
																				}
																			}
																		}
																	}
																}
																{
																	int traceTempVariable$currentState$68_1 = distributionTempVariable$var121$33;
																	for(int index$sample$68_2 = 0; index$sample$68_2 < state.noSamples; index$sample$68_2 += 1) {
																		if((sample == index$sample$68_2)) {
																			for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$68_2]; timeStep$var136 += 1) {
																				if((timeStep$var113 == timeStep$var136)) {
																					if(state.metric_valid_g[index$sample$68_2][timeStep$var136]) {
																						if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																							guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																							{
																								{
																									boolean cv$sampleConstrained = true;
																									if(cv$sampleConstrained) {
																										state.constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																										double cv$consumerDistributionProbabilityAccumulator = 1.0;
																										{
																											{
																												for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																													if((var50 == traceTempVariable$currentState$68_1)) {
																														if(state.fixedFlag$sample104) {
																															{
																																for(int index$sample$133_1 = 0; index$sample$133_1 < state.noSamples; index$sample$133_1 += 1) {
																																	if((index$sample$133_1 == index$sample$68_2)) {
																																		if((0 == timeStep$var136)) {
																																			{
																																				for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																					if((var66 == traceTempVariable$currentState$68_1)) {
																																						{
																																							{
																																								{
																																									double var148 = state.metric_mean[traceTempVariable$currentState$68_1];
																																									double var149 = state.metric_var[traceTempVariable$currentState$68_1];
																																									if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																															for(int index$sample$134 = 0; index$sample$134 < state.noSamples; index$sample$134 += 1) {
																																if(true) {
																																	for(int index$sample104$135 = 0; index$sample104$135 < state.noStates; index$sample104$135 += 1) {
																																		int distributionTempVariable$var102$137 = index$sample104$135;
																																		double cv$probabilitySample104Value136 = (1.0 * state.distribution$sample104[((index$sample$134 - 0) / 1)][index$sample104$135]);
																																		{
																																			int traceTempVariable$currentState$138_1 = distributionTempVariable$var102$137;
																																			if((index$sample$134 == index$sample$68_2)) {
																																				if((0 == timeStep$var136)) {
																																					{
																																						for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																							if((var66 == traceTempVariable$currentState$138_1)) {
																																								{
																																									{
																																										{
																																											double var148 = state.metric_mean[traceTempVariable$currentState$138_1];
																																											double var149 = state.metric_var[traceTempVariable$currentState$138_1];
																																											if(((Math.log(cv$probabilitySample104Value136) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value136) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																											else {
																																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value136) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																												else
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value136) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value136) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																												for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																													if((var50 == traceTempVariable$currentState$68_1)) {
																														{
																															int traceTempVariable$currentState$142_1 = cv$currentValue;
																															if((index$sample$2 == index$sample$68_2)) {
																																if((index$timeStep$1 == timeStep$var136)) {
																																	{
																																		for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$142_1)) {
																																				{
																																					{
																																						{
																																							double var148 = state.metric_mean[traceTempVariable$currentState$142_1];
																																							double var149 = state.metric_var[traceTempVariable$currentState$142_1];
																																							if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																																		for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$143_1)) {
																																				{
																																					{
																																						{
																																							double var148 = state.metric_mean[traceTempVariable$currentState$143_1];
																																							double var149 = state.metric_var[traceTempVariable$currentState$143_1];
																																							if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																														for(int index$sample$144 = 0; index$sample$144 < state.noSamples; index$sample$144 += 1) {
																															for(int index$timeStep$145 = 1; index$timeStep$145 < state.length$metric[index$sample$144]; index$timeStep$145 += 1) {
																																if((!((index$timeStep$145 == index$timeStep$1) && (index$sample$144 == index$sample$2)) && !((index$timeStep$145 == index$timeStep$30) && (index$sample$144 == index$sample$29)))) {
																																	for(int index$sample123$146 = 0; index$sample123$146 < state.noStates; index$sample123$146 += 1) {
																																		int distributionTempVariable$var121$148 = index$sample123$146;
																																		double cv$probabilitySample123Value147 = (1.0 * state.distribution$sample123[((index$sample$144 - 0) / 1)][((index$timeStep$145 - 1) / 1)][index$sample123$146]);
																																		{
																																			int traceTempVariable$currentState$149_1 = distributionTempVariable$var121$148;
																																			if((index$sample$144 == index$sample$68_2)) {
																																				if((index$timeStep$145 == timeStep$var136)) {
																																					{
																																						for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																							if((var66 == traceTempVariable$currentState$149_1)) {
																																								{
																																									{
																																										{
																																											double var148 = state.metric_mean[traceTempVariable$currentState$149_1];
																																											double var149 = state.metric_var[traceTempVariable$currentState$149_1];
																																											if(((Math.log(cv$probabilitySample123Value147) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value147) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																											else {
																																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value147) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																												else
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value147) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value147) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$68_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																	for(int index$sample$72_2 = 0; index$sample$72_2 < state.noSamples; index$sample$72_2 += 1) {
																		if((sample == index$sample$72_2)) {
																			for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[index$sample$72_2]; timeStep$var136 += 1) {
																				if((timeStep$var113 == timeStep$var136)) {
																					if(state.metric_valid_g[index$sample$72_2][timeStep$var136]) {
																						if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																							guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																							{
																								{
																									boolean cv$sampleConstrained = true;
																									if(cv$sampleConstrained) {
																										state.constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = true;
																										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																										double cv$consumerDistributionProbabilityAccumulator = 1.0;
																										{
																											if(state.fixedFlag$sample104) {
																												{
																													for(int index$sample$209_1 = 0; index$sample$209_1 < state.noSamples; index$sample$209_1 += 1) {
																														if((index$sample$209_1 == index$sample$72_2)) {
																															if((0 == timeStep$var136)) {
																																{
																																	for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																																		if((var50 == traceTempVariable$currentState$72_1)) {
																																			{
																																				for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																					if((var66 == traceTempVariable$currentState$72_1)) {
																																						{
																																							{
																																								{
																																									double var148 = state.metric_mean[traceTempVariable$currentState$72_1];
																																									double var149 = state.metric_var[traceTempVariable$currentState$72_1];
																																									if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																												for(int index$sample$210 = 0; index$sample$210 < state.noSamples; index$sample$210 += 1) {
																													if(true) {
																														for(int index$sample104$211 = 0; index$sample104$211 < state.noStates; index$sample104$211 += 1) {
																															int distributionTempVariable$var102$213 = index$sample104$211;
																															double cv$probabilitySample104Value212 = (1.0 * state.distribution$sample104[((index$sample$210 - 0) / 1)][index$sample104$211]);
																															{
																																int traceTempVariable$currentState$214_1 = distributionTempVariable$var102$213;
																																if((index$sample$210 == index$sample$72_2)) {
																																	if((0 == timeStep$var136)) {
																																		{
																																			for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																																				if((var50 == traceTempVariable$currentState$214_1)) {
																																					{
																																						for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																							if((var66 == traceTempVariable$currentState$214_1)) {
																																								{
																																									{
																																										{
																																											double var148 = state.metric_mean[traceTempVariable$currentState$214_1];
																																											double var149 = state.metric_var[traceTempVariable$currentState$214_1];
																																											if(((Math.log(cv$probabilitySample104Value212) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value212) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																											else {
																																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value212) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																												else
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value212) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value212) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																															for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																																if((var50 == traceTempVariable$currentState$219_1)) {
																																	{
																																		for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$219_1)) {
																																				{
																																					{
																																						{
																																							double var148 = state.metric_mean[traceTempVariable$currentState$219_1];
																																							double var149 = state.metric_var[traceTempVariable$currentState$219_1];
																																							if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																															for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																																if((var50 == traceTempVariable$currentState$220_1)) {
																																	{
																																		for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																			if((var66 == traceTempVariable$currentState$220_1)) {
																																				{
																																					{
																																						{
																																							double var148 = state.metric_mean[traceTempVariable$currentState$220_1];
																																							double var149 = state.metric_var[traceTempVariable$currentState$220_1];
																																							if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																											for(int index$sample$221 = 0; index$sample$221 < state.noSamples; index$sample$221 += 1) {
																												for(int index$timeStep$222 = 1; index$timeStep$222 < state.length$metric[index$sample$221]; index$timeStep$222 += 1) {
																													if((!((index$timeStep$222 == index$timeStep$1) && (index$sample$221 == index$sample$2)) && !((index$timeStep$222 == index$timeStep$30) && (index$sample$221 == index$sample$29)))) {
																														for(int index$sample123$223 = 0; index$sample123$223 < state.noStates; index$sample123$223 += 1) {
																															int distributionTempVariable$var121$225 = index$sample123$223;
																															double cv$probabilitySample123Value224 = (1.0 * state.distribution$sample123[((index$sample$221 - 0) / 1)][((index$timeStep$222 - 1) / 1)][index$sample123$223]);
																															{
																																int traceTempVariable$currentState$226_1 = distributionTempVariable$var121$225;
																																if((index$sample$221 == index$sample$72_2)) {
																																	if((index$timeStep$222 == timeStep$var136)) {
																																		{
																																			for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																																				if((var50 == traceTempVariable$currentState$226_1)) {
																																					{
																																						for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																							if((var66 == traceTempVariable$currentState$226_1)) {
																																								{
																																									{
																																										{
																																											double var148 = state.metric_mean[traceTempVariable$currentState$226_1];
																																											double var149 = state.metric_var[traceTempVariable$currentState$226_1];
																																											if(((Math.log(cv$probabilitySample123Value224) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value224) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																											else {
																																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value224) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																												else
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value224) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value224) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((index$sample$72_2 - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
							for(int index$sample$265_2 = 0; index$sample$265_2 < state.noSamples; index$sample$265_2 += 1) {
								if((sample == index$sample$265_2)) {
									for(int index$timeStep$265_3 = 1; index$timeStep$265_3 < state.length$metric[index$sample$265_2]; index$timeStep$265_3 += 1) {
										if((timeStep$var113 == (index$timeStep$265_3 - 1))) {
											{
												{
													int index$timeStep$267 = index$timeStep$265_3;
													int index$sample$268 = index$sample$265_2;
													double[] cv$accumulatedConsumerDistributions = scratch.cv$distributionAccumulator$var120[threadID$cv$sample];
													for(int cv$i = 0; cv$i < state.noStates; cv$i += 1)
														cv$accumulatedConsumerDistributions[cv$i] = 0.0;
													double cv$reachedDistributionProbability = 0.0;
													{
														for(int var31 = 0; var31 < state.noStates; var31 += 1) {
															if((var31 == traceTempVariable$var118$265_1)) {
																{
																	double scopeVariable$reachedSourceProbability = 0.0;
																	if(state.fixedFlag$sample104) {
																		{
																			for(int index$sample$270_1 = 0; index$sample$270_1 < state.noSamples; index$sample$270_1 += 1) {
																				if((index$sample$270_1 == sample)) {
																					if((0 == (timeStep$var113 - 1))) {
																						{
																							for(int index$var31$276_1 = 0; index$var31$276_1 < state.noStates; index$var31$276_1 += 1) {
																								if((index$var31$276_1 == state.st[sample][(timeStep$var113 - 1)]))
																									scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																	} else {
																		for(int index$sample$271 = 0; index$sample$271 < state.noSamples; index$sample$271 += 1) {
																			if(true) {
																				for(int index$sample104$272 = 0; index$sample104$272 < state.noStates; index$sample104$272 += 1) {
																					int distributionTempVariable$var102$274 = index$sample104$272;
																					double cv$probabilitySample104Value273 = (1.0 * state.distribution$sample104[((index$sample$271 - 0) / 1)][index$sample104$272]);
																					{
																						int traceTempVariable$var118$275_1 = distributionTempVariable$var102$274;
																						if((index$sample$271 == sample)) {
																							if((0 == (timeStep$var113 - 1))) {
																								{
																									for(int index$var31$277_1 = 0; index$var31$277_1 < state.noStates; index$var31$277_1 += 1) {
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
																					for(int index$var31$285_1 = 0; index$var31$285_1 < state.noStates; index$var31$285_1 += 1) {
																						if((index$var31$285_1 == traceTempVariable$var118$278_1))
																							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																					}
																				}
																			}
																		}
																	}
																	for(int index$sample$279 = 0; index$sample$279 < state.noSamples; index$sample$279 += 1) {
																		for(int index$timeStep$280 = 1; index$timeStep$280 < state.length$metric[index$sample$279]; index$timeStep$280 += 1) {
																			if((!((index$timeStep$280 == index$timeStep$1) && (index$sample$279 == index$sample$2)) && !((index$timeStep$280 == index$timeStep$267) && (index$sample$279 == index$sample$268)))) {
																				for(int index$sample123$281 = 0; index$sample123$281 < state.noStates; index$sample123$281 += 1) {
																					int distributionTempVariable$var121$283 = index$sample123$281;
																					double cv$probabilitySample123Value282 = (1.0 * state.distribution$sample123[((index$sample$279 - 0) / 1)][((index$timeStep$280 - 1) / 1)][index$sample123$281]);
																					{
																						int traceTempVariable$var118$284_1 = distributionTempVariable$var121$283;
																						if((index$sample$279 == sample)) {
																							if((index$timeStep$280 == (timeStep$var113 - 1))) {
																								{
																									for(int index$var31$286_1 = 0; index$var31$286_1 < state.noStates; index$var31$286_1 += 1) {
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
																	double[] var119 = state.m[traceTempVariable$var118$265_1];
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																	cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
																	DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, var119, state.noStates);
																}
															}
														}
													}
													double[] cv$sampleDistribution = state.distribution$sample123[((index$sample$265_2 - 0) / 1)][((index$timeStep$265_3 - 1) / 1)];
													double cv$overlap = 0.0;
													for(int cv$i = 0; cv$i < state.noStates; cv$i += 1) {
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
			if(state.constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)]) {
				double[] cv$localProbability = state.distribution$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)];
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
			state.constrainedFlag$sample19 = false;
			double[] cv$targetLocal = state.initialStateDistribution;
			double[] cv$countLocal = scratch.cv$var19$countGlobal;
			int cv$arrayLength = state.noStates;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							for(int sample = 0; sample < state.noSamples; sample += 1) {
								if(state.fixedFlag$sample104) {
									{
										{
											int index$sample$3 = sample;
											boolean cv$sampleConstrained = (state.fixedFlag$sample104 || state.constrainedFlag$sample104[((sample - 0) / 1)]);
											if(cv$sampleConstrained) {
												state.constrainedFlag$sample19 = true;
												{
													{
														{
															{
																{
																	cv$countLocal[state.st[sample][0]] = (cv$countLocal[state.st[sample][0]] + 1.0);
																}
															}
														}
													}
												}
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
						for(int sample = 0; sample < state.noSamples; sample += 1) {
							if(!state.fixedFlag$sample104) {
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
													cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample104[((sample - 0) / 1)][cv$loopIndex] * cv$distributionProbability));
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(state.constrainedFlag$sample19)
				Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, cv$countLocal, cv$targetLocal, state.noStates);
		}
	}

	private final void inferSample32(int var31, int threadID$cv$var31, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample32[((var31 - 0) / 1)] = false;
			double[] cv$targetLocal = state.m[var31];
			double[] cv$countLocal = scratch.cv$var32$countGlobal[threadID$cv$var31];
			int cv$arrayLength = state.noStates;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						for(int sample = 0; sample < state.noSamples; sample += 1) {
							for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
								if(state.fixedFlag$sample104) {
									{
										for(int index$sample$3_1 = 0; index$sample$3_1 < state.noSamples; index$sample$3_1 += 1) {
											if((index$sample$3_1 == sample)) {
												if((0 == (timeStep$var113 - 1))) {
													{
														if((var31 == state.st[sample][(timeStep$var113 - 1)])) {
															if(state.fixedFlag$sample123) {
																{
																	{
																		int index$timeStep$23 = timeStep$var113;
																		int index$sample$24 = sample;
																		boolean cv$sampleConstrained = (state.fixedFlag$sample123 || state.constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)]);
																		if(cv$sampleConstrained) {
																			state.constrainedFlag$sample32[((var31 - 0) / 1)] = true;
																			{
																				{
																					{
																						{
																							{
																								cv$countLocal[state.st[sample][timeStep$var113]] = (cv$countLocal[state.st[sample][timeStep$var113]] + 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
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
									for(int index$sample$4 = 0; index$sample$4 < state.noSamples; index$sample$4 += 1) {
										if(true) {
											for(int index$sample104$5 = 0; index$sample104$5 < state.noStates; index$sample104$5 += 1) {
												int distributionTempVariable$var102$7 = index$sample104$5;
												double cv$probabilitySample104Value6 = (1.0 * state.distribution$sample104[((index$sample$4 - 0) / 1)][index$sample104$5]);
												{
													int traceTempVariable$var118$8_1 = distributionTempVariable$var102$7;
													if((index$sample$4 == sample)) {
														if((0 == (timeStep$var113 - 1))) {
															{
																if((var31 == traceTempVariable$var118$8_1)) {
																	if(state.fixedFlag$sample123) {
																		{
																			{
																				int index$timeStep$26 = timeStep$var113;
																				int index$sample$27 = sample;
																				boolean cv$sampleConstrained = (state.fixedFlag$sample123 || state.constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)]);
																				if(cv$sampleConstrained) {
																					state.constrainedFlag$sample32[((var31 - 0) / 1)] = true;
																					{
																						{
																							{
																								{
																									{
																										cv$countLocal[state.st[sample][timeStep$var113]] = (cv$countLocal[state.st[sample][timeStep$var113]] + cv$probabilitySample104Value6);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						for(int sample = 0; sample < state.noSamples; sample += 1) {
							for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
								if(state.fixedFlag$sample123) {
									{
										for(int index$sample$13_1 = 0; index$sample$13_1 < state.noSamples; index$sample$13_1 += 1) {
											for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < state.length$metric[index$sample$13_1]; index$timeStep$13_2 += 1) {
												if((index$sample$13_1 == sample)) {
													if((index$timeStep$13_2 == (timeStep$var113 - 1))) {
														{
															if((var31 == state.st[sample][(timeStep$var113 - 1)])) {
																if(state.fixedFlag$sample123) {
																	{
																		{
																			int index$timeStep$29 = timeStep$var113;
																			int index$sample$30 = sample;
																			boolean cv$sampleConstrained = (state.fixedFlag$sample123 || state.constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)]);
																			if(cv$sampleConstrained) {
																				state.constrainedFlag$sample32[((var31 - 0) / 1)] = true;
																				{
																					{
																						{
																							{
																								{
																									cv$countLocal[state.st[sample][timeStep$var113]] = (cv$countLocal[state.st[sample][timeStep$var113]] + 1.0);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
									for(int index$sample$14 = 0; index$sample$14 < state.noSamples; index$sample$14 += 1) {
										for(int index$timeStep$15 = 1; index$timeStep$15 < state.length$metric[index$sample$14]; index$timeStep$15 += 1) {
											if(true) {
												for(int index$sample123$16 = 0; index$sample123$16 < state.noStates; index$sample123$16 += 1) {
													int distributionTempVariable$var121$18 = index$sample123$16;
													double cv$probabilitySample123Value17 = (1.0 * state.distribution$sample123[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample123$16]);
													{
														int traceTempVariable$var118$19_1 = distributionTempVariable$var121$18;
														if((index$sample$14 == sample)) {
															if((index$timeStep$15 == (timeStep$var113 - 1))) {
																{
																	if((var31 == traceTempVariable$var118$19_1)) {
																		if(state.fixedFlag$sample123) {
																			{
																				{
																					int index$timeStep$32 = timeStep$var113;
																					int index$sample$33 = sample;
																					boolean cv$sampleConstrained = (state.fixedFlag$sample123 || state.constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)]);
																					if(cv$sampleConstrained) {
																						state.constrainedFlag$sample32[((var31 - 0) / 1)] = true;
																						{
																							{
																								{
																									{
																										{
																											cv$countLocal[state.st[sample][timeStep$var113]] = (cv$countLocal[state.st[sample][timeStep$var113]] + cv$probabilitySample123Value17);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
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
					for(int sample = 0; sample < state.noSamples; sample += 1) {
						for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
							if(state.fixedFlag$sample104) {
								{
									for(int index$sample$40_1 = 0; index$sample$40_1 < state.noSamples; index$sample$40_1 += 1) {
										if((index$sample$40_1 == sample)) {
											if((0 == (timeStep$var113 - 1))) {
												{
													if((var31 == state.st[sample][(timeStep$var113 - 1)])) {
														if(!state.fixedFlag$sample123) {
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
																				cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																		}
																	}
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
								for(int index$sample$41 = 0; index$sample$41 < state.noSamples; index$sample$41 += 1) {
									if(true) {
										for(int index$sample104$42 = 0; index$sample104$42 < state.noStates; index$sample104$42 += 1) {
											int distributionTempVariable$var102$44 = index$sample104$42;
											double cv$probabilitySample104Value43 = (1.0 * state.distribution$sample104[((index$sample$41 - 0) / 1)][index$sample104$42]);
											{
												int traceTempVariable$var118$45_1 = distributionTempVariable$var102$44;
												if((index$sample$41 == sample)) {
													if((0 == (timeStep$var113 - 1))) {
														{
															if((var31 == traceTempVariable$var118$45_1)) {
																if(!state.fixedFlag$sample123) {
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
																						cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int sample = 0; sample < state.noSamples; sample += 1) {
						for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
							if(state.fixedFlag$sample123) {
								{
									for(int index$sample$50_1 = 0; index$sample$50_1 < state.noSamples; index$sample$50_1 += 1) {
										for(int index$timeStep$50_2 = 1; index$timeStep$50_2 < state.length$metric[index$sample$50_1]; index$timeStep$50_2 += 1) {
											if((index$sample$50_1 == sample)) {
												if((index$timeStep$50_2 == (timeStep$var113 - 1))) {
													{
														if((var31 == state.st[sample][(timeStep$var113 - 1)])) {
															if(!state.fixedFlag$sample123) {
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
																					cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																			}
																		}
																	}
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
								for(int index$sample$51 = 0; index$sample$51 < state.noSamples; index$sample$51 += 1) {
									for(int index$timeStep$52 = 1; index$timeStep$52 < state.length$metric[index$sample$51]; index$timeStep$52 += 1) {
										if(true) {
											for(int index$sample123$53 = 0; index$sample123$53 < state.noStates; index$sample123$53 += 1) {
												int distributionTempVariable$var121$55 = index$sample123$53;
												double cv$probabilitySample123Value54 = (1.0 * state.distribution$sample123[((index$sample$51 - 0) / 1)][((index$timeStep$52 - 1) / 1)][index$sample123$53]);
												{
													int traceTempVariable$var118$56_1 = distributionTempVariable$var121$55;
													if((index$sample$51 == sample)) {
														if((index$timeStep$52 == (timeStep$var113 - 1))) {
															{
																if((var31 == traceTempVariable$var118$56_1)) {
																	if(!state.fixedFlag$sample123) {
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
																							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(state.constrainedFlag$sample32[((var31 - 0) / 1)])
				Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, cv$countLocal, cv$targetLocal, state.noStates);
		}
	}

	private final void inferSample52(int var50, int threadID$cv$var50, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample52[((var50 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = state.metric_mean[var50];
			double cv$originalProbability = 0.0;
<<<<<<< Upstream, based on origin/Adding_types_to_variables_descriptions_so_that_global_local_and_scratch_accesses_can_be_separated
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
=======
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			if((cv$var < 0.01))
				cv$var = 0.01;
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
>>>>>>> 599badf Starting to add scratch the correct way. Adding a transformation to rewrite trees with accesses to scratch space. This will want changing so that we pass in a transformer rather than a series of flags at the end. Adding scratch state to the model. Changes that are only related to the addition of inner classes to hold the state. More adding state Updates to state location Adding state and scratch classes
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample52[((var50 - 0) / 1)] || (cv$valuePos == 0))) {
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
									state.metric_mean[var50] = cv$currentValue;
								}
							}
						}
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < 100.0))?(-Math.log((100.0 - 0.0))):Double.NEGATIVE_INFINITY));
						{
							{
								for(int sample = 0; sample < state.noSamples; sample += 1) {
									for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
										if(state.metric_valid_g[sample][timeStep$var136]) {
											if(state.fixedFlag$sample104) {
												{
													for(int index$sample$4_1 = 0; index$sample$4_1 < state.noSamples; index$sample$4_1 += 1) {
														if((index$sample$4_1 == sample)) {
															if((0 == timeStep$var136)) {
																{
																	double traceTempVariable$var148$10_1 = cv$currentValue;
																	if((var50 == state.st[sample][timeStep$var136])) {
																		{
																			{
																				boolean cv$sampleConstrained = true;
																				if(cv$sampleConstrained) {
																					state.constrainedFlag$sample52[((var50 - 0) / 1)] = true;
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							for(int index$sample$27_1 = 0; index$sample$27_1 < state.noSamples; index$sample$27_1 += 1) {
																								if((index$sample$27_1 == sample)) {
																									if((0 == timeStep$var136)) {
																										{
																											for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																												if((var66 == state.st[sample][timeStep$var136])) {
																													{
																														{
																															{
																																double var149 = state.metric_var[state.st[sample][timeStep$var136]];
																																if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																						if(state.fixedFlag$sample123) {
																							{
																								for(int index$sample$29_1 = 0; index$sample$29_1 < state.noSamples; index$sample$29_1 += 1) {
																									for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$29_1]; timeStep$var113 += 1) {
																										if((index$sample$29_1 == sample)) {
																											if((timeStep$var113 == timeStep$var136)) {
																												{
																													for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																														if((var66 == state.st[sample][timeStep$var136])) {
																															{
																																{
																																	{
																																		double var149 = state.metric_var[state.st[sample][timeStep$var136]];
																																		if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																							for(int index$sample$30 = 0; index$sample$30 < state.noSamples; index$sample$30 += 1) {
																								for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$30]; timeStep$var113 += 1) {
																									if(true) {
																										for(int index$sample123$32 = 0; index$sample123$32 < state.noStates; index$sample123$32 += 1) {
																											int distributionTempVariable$var121$34 = index$sample123$32;
																											double cv$probabilitySample123Value33 = (1.0 * state.distribution$sample123[((index$sample$30 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$32]);
																											{
																												int traceTempVariable$currentState$35_1 = distributionTempVariable$var121$34;
																												if((index$sample$30 == sample)) {
																													if((timeStep$var113 == timeStep$var136)) {
																														{
																															for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																if((var66 == traceTempVariable$currentState$35_1)) {
																																	{
																																		{
																																			{
																																				double var149 = state.metric_var[traceTempVariable$currentState$35_1];
																																				if(((Math.log(cv$probabilitySample123Value33) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value33) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value33) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value33) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value33) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$10_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
												for(int index$sample$5 = 0; index$sample$5 < state.noSamples; index$sample$5 += 1) {
													if(true) {
														for(int index$sample104$6 = 0; index$sample104$6 < state.noStates; index$sample104$6 += 1) {
															int distributionTempVariable$var102$8 = index$sample104$6;
															double cv$probabilitySample104Value7 = (1.0 * state.distribution$sample104[((index$sample$5 - 0) / 1)][index$sample104$6]);
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
																							state.constrainedFlag$sample52[((var50 - 0) / 1)] = true;
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								{
																									int traceTempVariable$currentState$38_1 = distributionTempVariable$var102$8;
																									if((index$sample$5 == sample)) {
																										if((0 == timeStep$var136)) {
																											{
																												for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																													if((var66 == traceTempVariable$currentState$38_1)) {
																														{
																															{
																																{
																																	double var149 = state.metric_var[traceTempVariable$currentState$38_1];
																																	if(((Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																								for(int index$sample$39 = 0; index$sample$39 < state.noSamples; index$sample$39 += 1) {
																									if(!(index$sample$39 == index$sample$5)) {
																										for(int index$sample104$40 = 0; index$sample104$40 < state.noStates; index$sample104$40 += 1) {
																											int distributionTempVariable$var102$42 = index$sample104$40;
																											double cv$probabilitySample104Value41 = (cv$probabilitySample104Value7 * state.distribution$sample104[((index$sample$39 - 0) / 1)][index$sample104$40]);
																											{
																												int traceTempVariable$currentState$43_1 = distributionTempVariable$var102$42;
																												if((index$sample$39 == sample)) {
																													if((0 == timeStep$var136)) {
																														{
																															for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																if((var66 == traceTempVariable$currentState$43_1)) {
																																	{
																																		{
																																			{
																																				double var149 = state.metric_var[traceTempVariable$currentState$43_1];
																																				if(((Math.log(cv$probabilitySample104Value41) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value41) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value41) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value41) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value41) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																								if(state.fixedFlag$sample123) {
																									{
																										for(int index$sample$46_1 = 0; index$sample$46_1 < state.noSamples; index$sample$46_1 += 1) {
																											for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$46_1]; timeStep$var113 += 1) {
																												if((index$sample$46_1 == sample)) {
																													if((timeStep$var113 == timeStep$var136)) {
																														{
																															for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																if((var66 == traceTempVariable$currentState$9_1)) {
																																	{
																																		{
																																			{
																																				double var149 = state.metric_var[traceTempVariable$currentState$9_1];
																																				if(((Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																									for(int index$sample$47 = 0; index$sample$47 < state.noSamples; index$sample$47 += 1) {
																										for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$47]; timeStep$var113 += 1) {
																											if(true) {
																												for(int index$sample123$49 = 0; index$sample123$49 < state.noStates; index$sample123$49 += 1) {
																													int distributionTempVariable$var121$51 = index$sample123$49;
																													double cv$probabilitySample123Value50 = (cv$probabilitySample104Value7 * state.distribution$sample123[((index$sample$47 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$49]);
																													{
																														int traceTempVariable$currentState$52_1 = distributionTempVariable$var121$51;
																														if((index$sample$47 == sample)) {
																															if((timeStep$var113 == timeStep$var136)) {
																																{
																																	for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																		if((var66 == traceTempVariable$currentState$52_1)) {
																																			{
																																				{
																																					{
																																						double var149 = state.metric_var[traceTempVariable$currentState$52_1];
																																						if(((Math.log(cv$probabilitySample123Value50) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value50) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value50) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value50) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value50) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$11_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
								for(int sample = 0; sample < state.noSamples; sample += 1) {
									for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
										if(state.metric_valid_g[sample][timeStep$var136]) {
											if(state.fixedFlag$sample123) {
												{
													for(int index$sample$14_1 = 0; index$sample$14_1 < state.noSamples; index$sample$14_1 += 1) {
														for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$14_1]; timeStep$var113 += 1) {
															if((index$sample$14_1 == sample)) {
																if((timeStep$var113 == timeStep$var136)) {
																	{
																		double traceTempVariable$var148$21_1 = cv$currentValue;
																		if((var50 == state.st[sample][timeStep$var136])) {
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						state.constrainedFlag$sample52[((var50 - 0) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							if(state.fixedFlag$sample104) {
																								{
																									for(int index$sample$55_1 = 0; index$sample$55_1 < state.noSamples; index$sample$55_1 += 1) {
																										if((index$sample$55_1 == sample)) {
																											if((0 == timeStep$var136)) {
																												{
																													for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																														if((var66 == state.st[sample][timeStep$var136])) {
																															{
																																{
																																	{
																																		double var149 = state.metric_var[state.st[sample][timeStep$var136]];
																																		if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																								for(int index$sample$56 = 0; index$sample$56 < state.noSamples; index$sample$56 += 1) {
																									if(true) {
																										for(int index$sample104$57 = 0; index$sample104$57 < state.noStates; index$sample104$57 += 1) {
																											int distributionTempVariable$var102$59 = index$sample104$57;
																											double cv$probabilitySample104Value58 = (1.0 * state.distribution$sample104[((index$sample$56 - 0) / 1)][index$sample104$57]);
																											{
																												int traceTempVariable$currentState$60_1 = distributionTempVariable$var102$59;
																												if((index$sample$56 == sample)) {
																													if((0 == timeStep$var136)) {
																														{
																															for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																if((var66 == traceTempVariable$currentState$60_1)) {
																																	{
																																		{
																																			{
																																				double var149 = state.metric_var[traceTempVariable$currentState$60_1];
																																				if(((Math.log(cv$probabilitySample104Value58) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value58) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value58) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value58) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value58) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																								for(int index$sample$63_1 = 0; index$sample$63_1 < state.noSamples; index$sample$63_1 += 1) {
																									for(int index$timeStep$63_2 = 1; index$timeStep$63_2 < state.length$metric[index$sample$63_1]; index$timeStep$63_2 += 1) {
																										if((index$sample$63_1 == sample)) {
																											if((index$timeStep$63_2 == timeStep$var136)) {
																												{
																													for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																														if((var66 == state.st[sample][timeStep$var136])) {
																															{
																																{
																																	{
																																		double var149 = state.metric_var[state.st[sample][timeStep$var136]];
																																		if(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$21_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
												for(int index$sample$15 = 0; index$sample$15 < state.noSamples; index$sample$15 += 1) {
													for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$15]; timeStep$var113 += 1) {
														if(true) {
															for(int index$sample123$17 = 0; index$sample123$17 < state.noStates; index$sample123$17 += 1) {
																int distributionTempVariable$var121$19 = index$sample123$17;
																double cv$probabilitySample123Value18 = (1.0 * state.distribution$sample123[((index$sample$15 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$17]);
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
																								state.constrainedFlag$sample52[((var50 - 0) / 1)] = true;
																								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																								double cv$consumerDistributionProbabilityAccumulator = 1.0;
																								{
																									if(state.fixedFlag$sample104) {
																										{
																											for(int index$sample$65_1 = 0; index$sample$65_1 < state.noSamples; index$sample$65_1 += 1) {
																												if((index$sample$65_1 == sample)) {
																													if((0 == timeStep$var136)) {
																														{
																															for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																if((var66 == traceTempVariable$currentState$20_1)) {
																																	{
																																		{
																																			{
																																				double var149 = state.metric_var[traceTempVariable$currentState$20_1];
																																				if(((Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																										for(int index$sample$66 = 0; index$sample$66 < state.noSamples; index$sample$66 += 1) {
																											if(true) {
																												for(int index$sample104$67 = 0; index$sample104$67 < state.noStates; index$sample104$67 += 1) {
																													int distributionTempVariable$var102$69 = index$sample104$67;
																													double cv$probabilitySample104Value68 = (cv$probabilitySample123Value18 * state.distribution$sample104[((index$sample$66 - 0) / 1)][index$sample104$67]);
																													{
																														int traceTempVariable$currentState$70_1 = distributionTempVariable$var102$69;
																														if((index$sample$66 == sample)) {
																															if((0 == timeStep$var136)) {
																																{
																																	for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																		if((var66 == traceTempVariable$currentState$70_1)) {
																																			{
																																				{
																																					{
																																						double var149 = state.metric_var[traceTempVariable$currentState$70_1];
																																						if(((Math.log(cv$probabilitySample104Value68) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value68) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value68) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value68) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value68) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																													for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																														if((var66 == traceTempVariable$currentState$73_1)) {
																															{
																																{
																																	{
																																		double var149 = state.metric_var[traceTempVariable$currentState$73_1];
																																		if(((Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
																									for(int index$sample$74 = 0; index$sample$74 < state.noSamples; index$sample$74 += 1) {
																										for(int index$timeStep$75 = 1; index$timeStep$75 < state.length$metric[index$sample$74]; index$timeStep$75 += 1) {
																											if(!((index$timeStep$75 == timeStep$var113) && (index$sample$74 == index$sample$15))) {
																												for(int index$sample123$76 = 0; index$sample123$76 < state.noStates; index$sample123$76 += 1) {
																													int distributionTempVariable$var121$78 = index$sample123$76;
																													double cv$probabilitySample123Value77 = (cv$probabilitySample123Value18 * state.distribution$sample123[((index$sample$74 - 0) / 1)][((index$timeStep$75 - 1) / 1)][index$sample123$76]);
																													{
																														int traceTempVariable$currentState$79_1 = distributionTempVariable$var121$78;
																														if((index$sample$74 == sample)) {
																															if((index$timeStep$75 == timeStep$var136)) {
																																{
																																	for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																																		if((var66 == traceTempVariable$currentState$79_1)) {
																																			{
																																				{
																																					{
																																						double var149 = state.metric_var[traceTempVariable$currentState$79_1];
																																						if(((Math.log(cv$probabilitySample123Value77) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value77) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value77) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value77) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value77) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - traceTempVariable$var148$22_1) / Math.sqrt(var149))) - (0.5 * Math.log(var149))):Double.NEGATIVE_INFINITY)));
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
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$))))) || Double.isNaN(cv$ratio))) {
							double var51 = cv$originalValue;
							{
								{
									{
										state.metric_mean[var50] = var51;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample68(int var66, int threadID$cv$var66, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample68[((var66 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = state.metric_var[var66];
			double cv$originalProbability = 0.0;
<<<<<<< Upstream, based on origin/Adding_types_to_variables_descriptions_so_that_global_local_and_scratch_accesses_can_be_separated
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
=======
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			if((cv$var < 0.01))
				cv$var = 0.01;
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
>>>>>>> 599badf Starting to add scratch the correct way. Adding a transformation to rewrite trees with accesses to scratch space. This will want changing so that we pass in a transformer rather than a series of flags at the end. Adding scratch state to the model. Changes that are only related to the addition of inner classes to hold the state. More adding state Updates to state location Adding state and scratch classes
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample68[((var66 - 0) / 1)] || (cv$valuePos == 0))) {
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
									state.metric_var[var66] = cv$currentValue;
								}
							}
						}
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, 1.0, 1.0));
						{
							{
								for(int sample = 0; sample < state.noSamples; sample += 1) {
									for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
										if(state.metric_valid_g[sample][timeStep$var136]) {
											if(state.fixedFlag$sample104) {
												{
													for(int index$sample$4_1 = 0; index$sample$4_1 < state.noSamples; index$sample$4_1 += 1) {
														if((index$sample$4_1 == sample)) {
															if((0 == timeStep$var136)) {
																{
																	double traceTempVariable$var149$10_1 = cv$currentValue;
																	if((var66 == state.st[sample][timeStep$var136])) {
																		{
																			{
																				boolean cv$sampleConstrained = true;
																				if(cv$sampleConstrained) {
																					state.constrainedFlag$sample68[((var66 - 0) / 1)] = true;
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							for(int index$sample$27_1 = 0; index$sample$27_1 < state.noSamples; index$sample$27_1 += 1) {
																								if((index$sample$27_1 == sample)) {
																									if((0 == timeStep$var136)) {
																										{
																											for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																												if((var50 == state.st[sample][timeStep$var136])) {
																													{
																														{
																															{
																																double var148 = state.metric_mean[state.st[sample][timeStep$var136]];
																																if(((Math.log(1.0) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)));
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
																						if(state.fixedFlag$sample123) {
																							{
																								for(int index$sample$29_1 = 0; index$sample$29_1 < state.noSamples; index$sample$29_1 += 1) {
																									for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$29_1]; timeStep$var113 += 1) {
																										if((index$sample$29_1 == sample)) {
																											if((timeStep$var113 == timeStep$var136)) {
																												{
																													for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																														if((var50 == state.st[sample][timeStep$var136])) {
																															{
																																{
																																	{
																																		double var148 = state.metric_mean[state.st[sample][timeStep$var136]];
																																		if(((Math.log(1.0) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)));
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
																							for(int index$sample$30 = 0; index$sample$30 < state.noSamples; index$sample$30 += 1) {
																								for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$30]; timeStep$var113 += 1) {
																									if(true) {
																										for(int index$sample123$32 = 0; index$sample123$32 < state.noStates; index$sample123$32 += 1) {
																											int distributionTempVariable$var121$34 = index$sample123$32;
																											double cv$probabilitySample123Value33 = (1.0 * state.distribution$sample123[((index$sample$30 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$32]);
																											{
																												int traceTempVariable$currentState$35_1 = distributionTempVariable$var121$34;
																												if((index$sample$30 == sample)) {
																													if((timeStep$var113 == timeStep$var136)) {
																														{
																															for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																																if((var50 == traceTempVariable$currentState$35_1)) {
																																	{
																																		{
																																			{
																																				double var148 = state.metric_mean[traceTempVariable$currentState$35_1];
																																				if(((Math.log(cv$probabilitySample123Value33) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value33) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value33) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value33) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value33) + ((0.0 < traceTempVariable$var149$10_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$10_1))) - (0.5 * Math.log(traceTempVariable$var149$10_1))):Double.NEGATIVE_INFINITY)));
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
												for(int index$sample$5 = 0; index$sample$5 < state.noSamples; index$sample$5 += 1) {
													if(true) {
														for(int index$sample104$6 = 0; index$sample104$6 < state.noStates; index$sample104$6 += 1) {
															int distributionTempVariable$var102$8 = index$sample104$6;
															double cv$probabilitySample104Value7 = (1.0 * state.distribution$sample104[((index$sample$5 - 0) / 1)][index$sample104$6]);
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
																							state.constrainedFlag$sample68[((var66 - 0) / 1)] = true;
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								{
																									int traceTempVariable$currentState$38_1 = distributionTempVariable$var102$8;
																									if((index$sample$5 == sample)) {
																										if((0 == timeStep$var136)) {
																											{
																												for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																													if((var50 == traceTempVariable$currentState$38_1)) {
																														{
																															{
																																{
																																	double var148 = state.metric_mean[traceTempVariable$currentState$38_1];
																																	if(((Math.log(cv$probabilitySample104Value7) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value7) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value7) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value7) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)));
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
																								for(int index$sample$39 = 0; index$sample$39 < state.noSamples; index$sample$39 += 1) {
																									if(!(index$sample$39 == index$sample$5)) {
																										for(int index$sample104$40 = 0; index$sample104$40 < state.noStates; index$sample104$40 += 1) {
																											int distributionTempVariable$var102$42 = index$sample104$40;
																											double cv$probabilitySample104Value41 = (cv$probabilitySample104Value7 * state.distribution$sample104[((index$sample$39 - 0) / 1)][index$sample104$40]);
																											{
																												int traceTempVariable$currentState$43_1 = distributionTempVariable$var102$42;
																												if((index$sample$39 == sample)) {
																													if((0 == timeStep$var136)) {
																														{
																															for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																																if((var50 == traceTempVariable$currentState$43_1)) {
																																	{
																																		{
																																			{
																																				double var148 = state.metric_mean[traceTempVariable$currentState$43_1];
																																				if(((Math.log(cv$probabilitySample104Value41) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value41) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value41) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value41) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value41) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)));
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
																								if(state.fixedFlag$sample123) {
																									{
																										for(int index$sample$46_1 = 0; index$sample$46_1 < state.noSamples; index$sample$46_1 += 1) {
																											for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$46_1]; timeStep$var113 += 1) {
																												if((index$sample$46_1 == sample)) {
																													if((timeStep$var113 == timeStep$var136)) {
																														{
																															for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																																if((var50 == traceTempVariable$currentState$9_1)) {
																																	{
																																		{
																																			{
																																				double var148 = state.metric_mean[traceTempVariable$currentState$9_1];
																																				if(((Math.log(cv$probabilitySample104Value7) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value7) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value7) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value7) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)));
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
																									for(int index$sample$47 = 0; index$sample$47 < state.noSamples; index$sample$47 += 1) {
																										for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$47]; timeStep$var113 += 1) {
																											if(true) {
																												for(int index$sample123$49 = 0; index$sample123$49 < state.noStates; index$sample123$49 += 1) {
																													int distributionTempVariable$var121$51 = index$sample123$49;
																													double cv$probabilitySample123Value50 = (cv$probabilitySample104Value7 * state.distribution$sample123[((index$sample$47 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$49]);
																													{
																														int traceTempVariable$currentState$52_1 = distributionTempVariable$var121$51;
																														if((index$sample$47 == sample)) {
																															if((timeStep$var113 == timeStep$var136)) {
																																{
																																	for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																																		if((var50 == traceTempVariable$currentState$52_1)) {
																																			{
																																				{
																																					{
																																						double var148 = state.metric_mean[traceTempVariable$currentState$52_1];
																																						if(((Math.log(cv$probabilitySample123Value50) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value50) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value50) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value50) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value50) + ((0.0 < traceTempVariable$var149$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$11_1))) - (0.5 * Math.log(traceTempVariable$var149$11_1))):Double.NEGATIVE_INFINITY)));
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
								for(int sample = 0; sample < state.noSamples; sample += 1) {
									for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
										if(state.metric_valid_g[sample][timeStep$var136]) {
											if(state.fixedFlag$sample123) {
												{
													for(int index$sample$14_1 = 0; index$sample$14_1 < state.noSamples; index$sample$14_1 += 1) {
														for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$14_1]; timeStep$var113 += 1) {
															if((index$sample$14_1 == sample)) {
																if((timeStep$var113 == timeStep$var136)) {
																	{
																		double traceTempVariable$var149$21_1 = cv$currentValue;
																		if((var66 == state.st[sample][timeStep$var136])) {
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						state.constrainedFlag$sample68[((var66 - 0) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							if(state.fixedFlag$sample104) {
																								{
																									for(int index$sample$55_1 = 0; index$sample$55_1 < state.noSamples; index$sample$55_1 += 1) {
																										if((index$sample$55_1 == sample)) {
																											if((0 == timeStep$var136)) {
																												{
																													for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																														if((var50 == state.st[sample][timeStep$var136])) {
																															{
																																{
																																	{
																																		double var148 = state.metric_mean[state.st[sample][timeStep$var136]];
																																		if(((Math.log(1.0) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)));
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
																								for(int index$sample$56 = 0; index$sample$56 < state.noSamples; index$sample$56 += 1) {
																									if(true) {
																										for(int index$sample104$57 = 0; index$sample104$57 < state.noStates; index$sample104$57 += 1) {
																											int distributionTempVariable$var102$59 = index$sample104$57;
																											double cv$probabilitySample104Value58 = (1.0 * state.distribution$sample104[((index$sample$56 - 0) / 1)][index$sample104$57]);
																											{
																												int traceTempVariable$currentState$60_1 = distributionTempVariable$var102$59;
																												if((index$sample$56 == sample)) {
																													if((0 == timeStep$var136)) {
																														{
																															for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																																if((var50 == traceTempVariable$currentState$60_1)) {
																																	{
																																		{
																																			{
																																				double var148 = state.metric_mean[traceTempVariable$currentState$60_1];
																																				if(((Math.log(cv$probabilitySample104Value58) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value58) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value58) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value58) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value58) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)));
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
																								for(int index$sample$63_1 = 0; index$sample$63_1 < state.noSamples; index$sample$63_1 += 1) {
																									for(int index$timeStep$63_2 = 1; index$timeStep$63_2 < state.length$metric[index$sample$63_1]; index$timeStep$63_2 += 1) {
																										if((index$sample$63_1 == sample)) {
																											if((index$timeStep$63_2 == timeStep$var136)) {
																												{
																													for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																														if((var50 == state.st[sample][timeStep$var136])) {
																															{
																																{
																																	{
																																		double var148 = state.metric_mean[state.st[sample][timeStep$var136]];
																																		if(((Math.log(1.0) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var149$21_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$21_1))) - (0.5 * Math.log(traceTempVariable$var149$21_1))):Double.NEGATIVE_INFINITY)));
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
												for(int index$sample$15 = 0; index$sample$15 < state.noSamples; index$sample$15 += 1) {
													for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$15]; timeStep$var113 += 1) {
														if(true) {
															for(int index$sample123$17 = 0; index$sample123$17 < state.noStates; index$sample123$17 += 1) {
																int distributionTempVariable$var121$19 = index$sample123$17;
																double cv$probabilitySample123Value18 = (1.0 * state.distribution$sample123[((index$sample$15 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$17]);
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
																								state.constrainedFlag$sample68[((var66 - 0) / 1)] = true;
																								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																								double cv$consumerDistributionProbabilityAccumulator = 1.0;
																								{
																									if(state.fixedFlag$sample104) {
																										{
																											for(int index$sample$65_1 = 0; index$sample$65_1 < state.noSamples; index$sample$65_1 += 1) {
																												if((index$sample$65_1 == sample)) {
																													if((0 == timeStep$var136)) {
																														{
																															for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																																if((var50 == traceTempVariable$currentState$20_1)) {
																																	{
																																		{
																																			{
																																				double var148 = state.metric_mean[traceTempVariable$currentState$20_1];
																																				if(((Math.log(cv$probabilitySample123Value18) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value18) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value18) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value18) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)));
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
																										for(int index$sample$66 = 0; index$sample$66 < state.noSamples; index$sample$66 += 1) {
																											if(true) {
																												for(int index$sample104$67 = 0; index$sample104$67 < state.noStates; index$sample104$67 += 1) {
																													int distributionTempVariable$var102$69 = index$sample104$67;
																													double cv$probabilitySample104Value68 = (cv$probabilitySample123Value18 * state.distribution$sample104[((index$sample$66 - 0) / 1)][index$sample104$67]);
																													{
																														int traceTempVariable$currentState$70_1 = distributionTempVariable$var102$69;
																														if((index$sample$66 == sample)) {
																															if((0 == timeStep$var136)) {
																																{
																																	for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																																		if((var50 == traceTempVariable$currentState$70_1)) {
																																			{
																																				{
																																					{
																																						double var148 = state.metric_mean[traceTempVariable$currentState$70_1];
																																						if(((Math.log(cv$probabilitySample104Value68) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value68) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value68) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value68) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample104Value68) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)));
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
																													for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																														if((var50 == traceTempVariable$currentState$73_1)) {
																															{
																																{
																																	{
																																		double var148 = state.metric_mean[traceTempVariable$currentState$73_1];
																																		if(((Math.log(cv$probabilitySample123Value18) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value18) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value18) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value18) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)));
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
																									for(int index$sample$74 = 0; index$sample$74 < state.noSamples; index$sample$74 += 1) {
																										for(int index$timeStep$75 = 1; index$timeStep$75 < state.length$metric[index$sample$74]; index$timeStep$75 += 1) {
																											if(!((index$timeStep$75 == timeStep$var113) && (index$sample$74 == index$sample$15))) {
																												for(int index$sample123$76 = 0; index$sample123$76 < state.noStates; index$sample123$76 += 1) {
																													int distributionTempVariable$var121$78 = index$sample123$76;
																													double cv$probabilitySample123Value77 = (cv$probabilitySample123Value18 * state.distribution$sample123[((index$sample$74 - 0) / 1)][((index$timeStep$75 - 1) / 1)][index$sample123$76]);
																													{
																														int traceTempVariable$currentState$79_1 = distributionTempVariable$var121$78;
																														if((index$sample$74 == sample)) {
																															if((index$timeStep$75 == timeStep$var136)) {
																																{
																																	for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																																		if((var50 == traceTempVariable$currentState$79_1)) {
																																			{
																																				{
																																					{
																																						double var148 = state.metric_mean[traceTempVariable$currentState$79_1];
																																						if(((Math.log(cv$probabilitySample123Value77) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value77) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value77) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value77) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample123Value77) + ((0.0 < traceTempVariable$var149$22_1)?(DistributionSampling.logProbabilityGaussian(((state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] - var148) / Math.sqrt(traceTempVariable$var149$22_1))) - (0.5 * Math.log(traceTempVariable$var149$22_1))):Double.NEGATIVE_INFINITY)));
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
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$))))) || Double.isNaN(cv$ratio))) {
							double var67 = cv$originalValue;
							{
								{
									{
										state.metric_var[var66] = var67;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample84(int var82, int threadID$cv$var82, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample84[((var82 - 0) / 1)] = false;
			double cv$sum = 0.0;
			double cv$count = 0.0;
			{
				{
					{
						for(int sample = 0; sample < state.noSamples; sample += 1) {
							for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
								if(state.fixedFlag$sample104) {
									{
										for(int index$sample$3_1 = 0; index$sample$3_1 < state.noSamples; index$sample$3_1 += 1) {
											if((index$sample$3_1 == sample)) {
												if((0 == timeStep$var136)) {
													{
														if((var82 == state.st[sample][timeStep$var136])) {
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample84[((var82 - 0) / 1)] = true;
																		{
																			{
																				{
																					{
																						{
																							cv$count = (cv$count + 1.0);
																							if(state.metric_valid_g[sample][timeStep$var136])
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
									for(int index$sample$4 = 0; index$sample$4 < state.noSamples; index$sample$4 += 1) {
										if(true) {
											for(int index$sample104$5 = 0; index$sample104$5 < state.noStates; index$sample104$5 += 1) {
												int distributionTempVariable$var102$7 = index$sample104$5;
												double cv$probabilitySample104Value6 = (1.0 * state.distribution$sample104[((index$sample$4 - 0) / 1)][index$sample104$5]);
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
																				state.constrainedFlag$sample84[((var82 - 0) / 1)] = true;
																				{
																					{
																						{
																							{
																								{
																									cv$count = (cv$count + cv$probabilitySample104Value6);
																									if(state.metric_valid_g[sample][timeStep$var136])
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
						for(int sample = 0; sample < state.noSamples; sample += 1) {
							for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
								if(state.fixedFlag$sample123) {
									{
										for(int index$sample$13_1 = 0; index$sample$13_1 < state.noSamples; index$sample$13_1 += 1) {
											for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$13_1]; timeStep$var113 += 1) {
												if((index$sample$13_1 == sample)) {
													if((timeStep$var113 == timeStep$var136)) {
														{
															if((var82 == state.st[sample][timeStep$var136])) {
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			state.constrainedFlag$sample84[((var82 - 0) / 1)] = true;
																			{
																				{
																					{
																						{
																							{
																								cv$count = (cv$count + 1.0);
																								if(state.metric_valid_g[sample][timeStep$var136])
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
									for(int index$sample$14 = 0; index$sample$14 < state.noSamples; index$sample$14 += 1) {
										for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$14]; timeStep$var113 += 1) {
											if(true) {
												for(int index$sample123$16 = 0; index$sample123$16 < state.noStates; index$sample123$16 += 1) {
													int distributionTempVariable$var121$18 = index$sample123$16;
													double cv$probabilitySample123Value17 = (1.0 * state.distribution$sample123[((index$sample$14 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$16]);
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
																					state.constrainedFlag$sample84[((var82 - 0) / 1)] = true;
																					{
																						{
																							{
																								{
																									{
																										cv$count = (cv$count + cv$probabilitySample123Value17);
																										if(state.metric_valid_g[sample][timeStep$var136])
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
			if(state.constrainedFlag$sample84[((var82 - 0) / 1)]) {
				double var83 = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
				{
					{
						{
							state.metric_valid_bias[var82] = var83;
						}
					}
				}
			}
		}
	}

	private final void logProbabilityDistribution$sample104() {
		if(!state.fixedProbFlag$sample104) {
			if(state.fixedFlag$sample104) {
				double cv$accumulator = 0.0;
				boolean cv$sampleReached = false;
				for(int sample = 0; sample < state.noSamples; sample += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$sample$1 = sample;
					{
						{
							int cv$sampleValue = state.st[sample][0];
							{
								{
									double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[cv$sampleValue])) && (state.initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(state.initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					state.logProbability$sample104[((sample - 0) / 1)] = cv$sampleProbability;
				}
				if(state.fixedFlag$sample104)
					state.logProbability$st = (state.logProbability$st + cv$accumulator);
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				if(state.fixedFlag$sample104)
					state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				state.fixedProbFlag$sample104 = (state.fixedFlag$sample104 && state.fixedFlag$sample19);
			}
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample104[((sample - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			if(state.fixedFlag$sample104)
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample104)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample123() {
		if(!state.fixedProbFlag$sample123) {
			if(state.fixedFlag$sample123) {
				double cv$accumulator = 0.0;
				boolean cv$sampleReached = false;
				for(int sample = 0; sample < state.noSamples; sample += 1) {
					for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int index$timeStep$1 = timeStep$var113;
						int index$sample$2 = sample;
						{
							{
								int cv$sampleValue = state.st[sample][timeStep$var113];
								if(state.fixedFlag$sample104) {
									{
										for(int index$sample$4_1 = 0; index$sample$4_1 < state.noSamples; index$sample$4_1 += 1) {
											if((index$sample$4_1 == sample)) {
												if((0 == (timeStep$var113 - 1))) {
													{
														for(int var31 = 0; var31 < state.noStates; var31 += 1) {
															if((var31 == state.st[sample][(timeStep$var113 - 1)])) {
																{
																	double[] var119 = state.m[state.st[sample][(timeStep$var113 - 1)]];
																	double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
									for(int index$sample$5 = 0; index$sample$5 < state.noSamples; index$sample$5 += 1) {
										if(true) {
											for(int index$sample104$6 = 0; index$sample104$6 < state.noStates; index$sample104$6 += 1) {
												int distributionTempVariable$var102$8 = index$sample104$6;
												double cv$probabilitySample104Value7 = (1.0 * state.distribution$sample104[((index$sample$5 - 0) / 1)][index$sample104$6]);
												{
													int traceTempVariable$var118$9_1 = distributionTempVariable$var102$8;
													if((index$sample$5 == sample)) {
														if((0 == (timeStep$var113 - 1))) {
															{
																for(int var31 = 0; var31 < state.noStates; var31 += 1) {
																	if((var31 == traceTempVariable$var118$9_1)) {
																		{
																			double[] var119 = state.m[traceTempVariable$var118$9_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample104Value7) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
												for(int var31 = 0; var31 < state.noStates; var31 += 1) {
													if((var31 == state.st[sample][(timeStep$var113 - 1)])) {
														{
															double[] var119 = state.m[state.st[sample][(timeStep$var113 - 1)]];
															double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
								if(state.fixedFlag$sample123) {
									{
										for(int index$sample$13_1 = 0; index$sample$13_1 < state.noSamples; index$sample$13_1 += 1) {
											for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < state.length$metric[index$sample$13_1]; index$timeStep$13_2 += 1) {
												if((index$sample$13_1 == sample)) {
													if((index$timeStep$13_2 == (timeStep$var113 - 1))) {
														{
															for(int var31 = 0; var31 < state.noStates; var31 += 1) {
																if((var31 == state.st[sample][(timeStep$var113 - 1)])) {
																	{
																		double[] var119 = state.m[state.st[sample][(timeStep$var113 - 1)]];
																		double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
									for(int index$sample$14 = 0; index$sample$14 < state.noSamples; index$sample$14 += 1) {
										for(int index$timeStep$15 = 1; index$timeStep$15 < state.length$metric[index$sample$14]; index$timeStep$15 += 1) {
											if(!((index$timeStep$15 == index$timeStep$1) && (index$sample$14 == index$sample$2))) {
												for(int index$sample123$16 = 0; index$sample123$16 < state.noStates; index$sample123$16 += 1) {
													int distributionTempVariable$var121$18 = index$sample123$16;
													double cv$probabilitySample123Value17 = (1.0 * state.distribution$sample123[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample123$16]);
													{
														int traceTempVariable$var118$19_1 = distributionTempVariable$var121$18;
														if((index$sample$14 == sample)) {
															if((index$timeStep$15 == (timeStep$var113 - 1))) {
																{
																	for(int var31 = 0; var31 < state.noStates; var31 += 1) {
																		if((var31 == traceTempVariable$var118$19_1)) {
																			{
																				double[] var119 = state.m[traceTempVariable$var118$19_1];
																				double cv$weightedProbability = (Math.log(cv$probabilitySample123Value17) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						state.logProbability$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = cv$sampleProbability;
					}
				}
				if(state.fixedFlag$sample123)
					state.logProbability$st = (state.logProbability$st + cv$accumulator);
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				if(state.fixedFlag$sample123)
					state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				state.fixedProbFlag$sample123 = ((state.fixedFlag$sample123 && state.fixedFlag$sample32) && state.fixedFlag$sample104);
			}
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = state.logProbability$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			if(state.fixedFlag$sample123)
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample123)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample145() {
		if(!state.fixedProbFlag$sample145) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							boolean cv$sampleValue = state.metric_valid_g[sample][timeStep$var136];
							if(state.fixedFlag$sample104) {
								{
									for(int index$sample$2_1 = 0; index$sample$2_1 < state.noSamples; index$sample$2_1 += 1) {
										if((index$sample$2_1 == sample)) {
											if((0 == timeStep$var136)) {
												{
													for(int var82 = 0; var82 < state.noStates; var82 += 1) {
														if((var82 == state.st[sample][timeStep$var136])) {
															{
																double var139 = state.metric_valid_bias[state.st[sample][timeStep$var136]];
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
								for(int index$sample$3 = 0; index$sample$3 < state.noSamples; index$sample$3 += 1) {
									if(true) {
										for(int index$sample104$4 = 0; index$sample104$4 < state.noStates; index$sample104$4 += 1) {
											int distributionTempVariable$var102$6 = index$sample104$4;
											double cv$probabilitySample104Value5 = (1.0 * state.distribution$sample104[((index$sample$3 - 0) / 1)][index$sample104$4]);
											{
												int traceTempVariable$currentState$7_1 = distributionTempVariable$var102$6;
												if((index$sample$3 == sample)) {
													if((0 == timeStep$var136)) {
														{
															for(int var82 = 0; var82 < state.noStates; var82 += 1) {
																if((var82 == traceTempVariable$currentState$7_1)) {
																	{
																		double var139 = state.metric_valid_bias[traceTempVariable$currentState$7_1];
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
							if(state.fixedFlag$sample123) {
								{
									for(int index$sample$10_1 = 0; index$sample$10_1 < state.noSamples; index$sample$10_1 += 1) {
										for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$10_1]; timeStep$var113 += 1) {
											if((index$sample$10_1 == sample)) {
												if((timeStep$var113 == timeStep$var136)) {
													{
														for(int var82 = 0; var82 < state.noStates; var82 += 1) {
															if((var82 == state.st[sample][timeStep$var136])) {
																{
																	double var139 = state.metric_valid_bias[state.st[sample][timeStep$var136]];
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
								for(int index$sample$11 = 0; index$sample$11 < state.noSamples; index$sample$11 += 1) {
									for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$11]; timeStep$var113 += 1) {
										if(true) {
											for(int index$sample123$13 = 0; index$sample123$13 < state.noStates; index$sample123$13 += 1) {
												int distributionTempVariable$var121$15 = index$sample123$13;
												double cv$probabilitySample123Value14 = (1.0 * state.distribution$sample123[((index$sample$11 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$13]);
												{
													int traceTempVariable$currentState$16_1 = distributionTempVariable$var121$15;
													if((index$sample$11 == sample)) {
														if((timeStep$var113 == timeStep$var136)) {
															{
																for(int var82 = 0; var82 < state.noStates; var82 += 1) {
																	if((var82 == traceTempVariable$currentState$16_1)) {
																		{
																			double var139 = state.metric_valid_bias[traceTempVariable$currentState$16_1];
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
					state.logProbability$sample145[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = cv$sampleProbability;
				}
			}
			boolean cv$guard$metric_valid_g = false;
			state.logProbability$metric_valid_1d = (state.logProbability$metric_valid_1d + cv$accumulator);
			{
				{
					if(!cv$guard$metric_valid_g) {
						cv$guard$metric_valid_g = true;
						state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample145 = ((state.fixedFlag$sample84 && state.fixedFlag$sample104) && state.fixedFlag$sample123);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = state.logProbability$sample145[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			boolean cv$guard$metric_valid_g = false;
			state.logProbability$metric_valid_1d = (state.logProbability$metric_valid_1d + cv$accumulator);
			{
				{
					if(!cv$guard$metric_valid_g) {
						cv$guard$metric_valid_g = true;
						state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample157() {
		if(!state.fixedProbFlag$sample157) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					if(state.metric_valid_g[sample][timeStep$var136]) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							{
								double cv$sampleValue = state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)];
								if(state.fixedFlag$sample104) {
									{
										for(int index$sample$2_1 = 0; index$sample$2_1 < state.noSamples; index$sample$2_1 += 1) {
											if((index$sample$2_1 == sample)) {
												if((0 == timeStep$var136)) {
													{
														for(int var50 = 0; var50 < state.noStates; var50 += 1) {
															if((var50 == state.st[sample][timeStep$var136])) {
																{
																	for(int index$sample$10_1 = 0; index$sample$10_1 < state.noSamples; index$sample$10_1 += 1) {
																		if((index$sample$10_1 == sample)) {
																			if((0 == timeStep$var136)) {
																				{
																					for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																						if((var66 == state.st[sample][timeStep$var136])) {
																							{
																								double var148 = state.metric_mean[state.st[sample][timeStep$var136]];
																								double var149 = state.metric_var[state.st[sample][timeStep$var136]];
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
									for(int index$sample$3 = 0; index$sample$3 < state.noSamples; index$sample$3 += 1) {
										if(true) {
											for(int index$sample104$4 = 0; index$sample104$4 < state.noStates; index$sample104$4 += 1) {
												int distributionTempVariable$var102$6 = index$sample104$4;
												double cv$probabilitySample104Value5 = (1.0 * state.distribution$sample104[((index$sample$3 - 0) / 1)][index$sample104$4]);
												{
													int traceTempVariable$currentState$7_1 = distributionTempVariable$var102$6;
													if((index$sample$3 == sample)) {
														if((0 == timeStep$var136)) {
															{
																for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																	if((var50 == traceTempVariable$currentState$7_1)) {
																		{
																			int traceTempVariable$currentState$11_1 = distributionTempVariable$var102$6;
																			if((index$sample$3 == sample)) {
																				if((0 == timeStep$var136)) {
																					{
																						for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																							if((var66 == traceTempVariable$currentState$11_1)) {
																								{
																									double var148 = state.metric_mean[traceTempVariable$currentState$11_1];
																									double var149 = state.metric_var[traceTempVariable$currentState$11_1];
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
																		for(int index$sample$12 = 0; index$sample$12 < state.noSamples; index$sample$12 += 1) {
																			if(!(index$sample$12 == index$sample$3)) {
																				for(int index$sample104$13 = 0; index$sample104$13 < state.noStates; index$sample104$13 += 1) {
																					int distributionTempVariable$var102$15 = index$sample104$13;
																					double cv$probabilitySample104Value14 = (cv$probabilitySample104Value5 * state.distribution$sample104[((index$sample$12 - 0) / 1)][index$sample104$13]);
																					{
																						int traceTempVariable$currentState$16_1 = distributionTempVariable$var102$15;
																						if((index$sample$12 == sample)) {
																							if((0 == timeStep$var136)) {
																								{
																									for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																										if((var66 == traceTempVariable$currentState$16_1)) {
																											{
																												double var148 = state.metric_mean[traceTempVariable$currentState$16_1];
																												double var149 = state.metric_var[traceTempVariable$currentState$16_1];
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
								if(state.fixedFlag$sample104) {
									{
										for(int index$sample$20_1 = 0; index$sample$20_1 < state.noSamples; index$sample$20_1 += 1) {
											if((index$sample$20_1 == sample)) {
												if((0 == timeStep$var136)) {
													{
														for(int var50 = 0; var50 < state.noStates; var50 += 1) {
															if((var50 == state.st[sample][timeStep$var136])) {
																if(state.fixedFlag$sample123) {
																	{
																		for(int index$sample$28_1 = 0; index$sample$28_1 < state.noSamples; index$sample$28_1 += 1) {
																			for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$28_1]; timeStep$var113 += 1) {
																				if((index$sample$28_1 == sample)) {
																					if((timeStep$var113 == timeStep$var136)) {
																						{
																							for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																								if((var66 == state.st[sample][timeStep$var136])) {
																									{
																										double var148 = state.metric_mean[state.st[sample][timeStep$var136]];
																										double var149 = state.metric_var[state.st[sample][timeStep$var136]];
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
																	for(int index$sample$29 = 0; index$sample$29 < state.noSamples; index$sample$29 += 1) {
																		for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$29]; timeStep$var113 += 1) {
																			if(true) {
																				for(int index$sample123$31 = 0; index$sample123$31 < state.noStates; index$sample123$31 += 1) {
																					int distributionTempVariable$var121$33 = index$sample123$31;
																					double cv$probabilitySample123Value32 = (1.0 * state.distribution$sample123[((index$sample$29 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$31]);
																					{
																						int traceTempVariable$currentState$34_1 = distributionTempVariable$var121$33;
																						if((index$sample$29 == sample)) {
																							if((timeStep$var113 == timeStep$var136)) {
																								{
																									for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																										if((var66 == traceTempVariable$currentState$34_1)) {
																											{
																												double var148 = state.metric_mean[traceTempVariable$currentState$34_1];
																												double var149 = state.metric_var[traceTempVariable$currentState$34_1];
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
									for(int index$sample$21 = 0; index$sample$21 < state.noSamples; index$sample$21 += 1) {
										if(true) {
											for(int index$sample104$22 = 0; index$sample104$22 < state.noStates; index$sample104$22 += 1) {
												int distributionTempVariable$var102$24 = index$sample104$22;
												double cv$probabilitySample104Value23 = (1.0 * state.distribution$sample104[((index$sample$21 - 0) / 1)][index$sample104$22]);
												{
													int traceTempVariable$currentState$25_1 = distributionTempVariable$var102$24;
													if((index$sample$21 == sample)) {
														if((0 == timeStep$var136)) {
															{
																for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																	if((var50 == traceTempVariable$currentState$25_1)) {
																		if(state.fixedFlag$sample123) {
																			{
																				for(int index$sample$35_1 = 0; index$sample$35_1 < state.noSamples; index$sample$35_1 += 1) {
																					for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$35_1]; timeStep$var113 += 1) {
																						if((index$sample$35_1 == sample)) {
																							if((timeStep$var113 == timeStep$var136)) {
																								{
																									for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																										if((var66 == traceTempVariable$currentState$25_1)) {
																											{
																												double var148 = state.metric_mean[traceTempVariable$currentState$25_1];
																												double var149 = state.metric_var[traceTempVariable$currentState$25_1];
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
																			for(int index$sample$36 = 0; index$sample$36 < state.noSamples; index$sample$36 += 1) {
																				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$36]; timeStep$var113 += 1) {
																					if(true) {
																						for(int index$sample123$38 = 0; index$sample123$38 < state.noStates; index$sample123$38 += 1) {
																							int distributionTempVariable$var121$40 = index$sample123$38;
																							double cv$probabilitySample123Value39 = (cv$probabilitySample104Value23 * state.distribution$sample123[((index$sample$36 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$38]);
																							{
																								int traceTempVariable$currentState$41_1 = distributionTempVariable$var121$40;
																								if((index$sample$36 == sample)) {
																									if((timeStep$var113 == timeStep$var136)) {
																										{
																											for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																												if((var66 == traceTempVariable$currentState$41_1)) {
																													{
																														double var148 = state.metric_mean[traceTempVariable$currentState$41_1];
																														double var149 = state.metric_var[traceTempVariable$currentState$41_1];
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
								if(state.fixedFlag$sample123) {
									{
										for(int index$sample$46_1 = 0; index$sample$46_1 < state.noSamples; index$sample$46_1 += 1) {
											for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$46_1]; timeStep$var113 += 1) {
												if((index$sample$46_1 == sample)) {
													if((timeStep$var113 == timeStep$var136)) {
														{
															for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																if((var50 == state.st[sample][timeStep$var136])) {
																	{
																		for(int index$sample$55_1 = 0; index$sample$55_1 < state.noSamples; index$sample$55_1 += 1) {
																			for(int index$timeStep$55_2 = 1; index$timeStep$55_2 < state.length$metric[index$sample$55_1]; index$timeStep$55_2 += 1) {
																				if((index$sample$55_1 == sample)) {
																					if((index$timeStep$55_2 == timeStep$var136)) {
																						{
																							for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																								if((var66 == state.st[sample][timeStep$var136])) {
																									{
																										double var148 = state.metric_mean[state.st[sample][timeStep$var136]];
																										double var149 = state.metric_var[state.st[sample][timeStep$var136]];
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
									for(int index$sample$47 = 0; index$sample$47 < state.noSamples; index$sample$47 += 1) {
										for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$47]; timeStep$var113 += 1) {
											if(true) {
												for(int index$sample123$49 = 0; index$sample123$49 < state.noStates; index$sample123$49 += 1) {
													int distributionTempVariable$var121$51 = index$sample123$49;
													double cv$probabilitySample123Value50 = (1.0 * state.distribution$sample123[((index$sample$47 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$49]);
													{
														int traceTempVariable$currentState$52_1 = distributionTempVariable$var121$51;
														if((index$sample$47 == sample)) {
															if((timeStep$var113 == timeStep$var136)) {
																{
																	for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																		if((var50 == traceTempVariable$currentState$52_1)) {
																			{
																				int traceTempVariable$currentState$56_1 = distributionTempVariable$var121$51;
																				if((index$sample$47 == sample)) {
																					if((timeStep$var113 == timeStep$var136)) {
																						{
																							for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																								if((var66 == traceTempVariable$currentState$56_1)) {
																									{
																										double var148 = state.metric_mean[traceTempVariable$currentState$56_1];
																										double var149 = state.metric_var[traceTempVariable$currentState$56_1];
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
																			for(int index$sample$57 = 0; index$sample$57 < state.noSamples; index$sample$57 += 1) {
																				for(int index$timeStep$58 = 1; index$timeStep$58 < state.length$metric[index$sample$57]; index$timeStep$58 += 1) {
																					if(!((index$timeStep$58 == timeStep$var113) && (index$sample$57 == index$sample$47))) {
																						for(int index$sample123$59 = 0; index$sample123$59 < state.noStates; index$sample123$59 += 1) {
																							int distributionTempVariable$var121$61 = index$sample123$59;
																							double cv$probabilitySample123Value60 = (cv$probabilitySample123Value50 * state.distribution$sample123[((index$sample$57 - 0) / 1)][((index$timeStep$58 - 1) / 1)][index$sample123$59]);
																							{
																								int traceTempVariable$currentState$62_1 = distributionTempVariable$var121$61;
																								if((index$sample$57 == sample)) {
																									if((index$timeStep$58 == timeStep$var136)) {
																										{
																											for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																												if((var66 == traceTempVariable$currentState$62_1)) {
																													{
																														double var148 = state.metric_mean[traceTempVariable$currentState$62_1];
																														double var149 = state.metric_var[traceTempVariable$currentState$62_1];
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
								if(state.fixedFlag$sample123) {
									{
										for(int index$sample$66_1 = 0; index$sample$66_1 < state.noSamples; index$sample$66_1 += 1) {
											for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$66_1]; timeStep$var113 += 1) {
												if((index$sample$66_1 == sample)) {
													if((timeStep$var113 == timeStep$var136)) {
														{
															for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																if((var50 == state.st[sample][timeStep$var136])) {
																	if(state.fixedFlag$sample104) {
																		{
																			for(int index$sample$75_1 = 0; index$sample$75_1 < state.noSamples; index$sample$75_1 += 1) {
																				if((index$sample$75_1 == sample)) {
																					if((0 == timeStep$var136)) {
																						{
																							for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																								if((var66 == state.st[sample][timeStep$var136])) {
																									{
																										double var148 = state.metric_mean[state.st[sample][timeStep$var136]];
																										double var149 = state.metric_var[state.st[sample][timeStep$var136]];
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
																		for(int index$sample$76 = 0; index$sample$76 < state.noSamples; index$sample$76 += 1) {
																			if(true) {
																				for(int index$sample104$77 = 0; index$sample104$77 < state.noStates; index$sample104$77 += 1) {
																					int distributionTempVariable$var102$79 = index$sample104$77;
																					double cv$probabilitySample104Value78 = (1.0 * state.distribution$sample104[((index$sample$76 - 0) / 1)][index$sample104$77]);
																					{
																						int traceTempVariable$currentState$80_1 = distributionTempVariable$var102$79;
																						if((index$sample$76 == sample)) {
																							if((0 == timeStep$var136)) {
																								{
																									for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																										if((var66 == traceTempVariable$currentState$80_1)) {
																											{
																												double var148 = state.metric_mean[traceTempVariable$currentState$80_1];
																												double var149 = state.metric_var[traceTempVariable$currentState$80_1];
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
									for(int index$sample$67 = 0; index$sample$67 < state.noSamples; index$sample$67 += 1) {
										for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[index$sample$67]; timeStep$var113 += 1) {
											if(true) {
												for(int index$sample123$69 = 0; index$sample123$69 < state.noStates; index$sample123$69 += 1) {
													int distributionTempVariable$var121$71 = index$sample123$69;
													double cv$probabilitySample123Value70 = (1.0 * state.distribution$sample123[((index$sample$67 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$69]);
													{
														int traceTempVariable$currentState$72_1 = distributionTempVariable$var121$71;
														if((index$sample$67 == sample)) {
															if((timeStep$var113 == timeStep$var136)) {
																{
																	for(int var50 = 0; var50 < state.noStates; var50 += 1) {
																		if((var50 == traceTempVariable$currentState$72_1)) {
																			if(state.fixedFlag$sample104) {
																				{
																					for(int index$sample$81_1 = 0; index$sample$81_1 < state.noSamples; index$sample$81_1 += 1) {
																						if((index$sample$81_1 == sample)) {
																							if((0 == timeStep$var136)) {
																								{
																									for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																										if((var66 == traceTempVariable$currentState$72_1)) {
																											{
																												double var148 = state.metric_mean[traceTempVariable$currentState$72_1];
																												double var149 = state.metric_var[traceTempVariable$currentState$72_1];
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
																				for(int index$sample$82 = 0; index$sample$82 < state.noSamples; index$sample$82 += 1) {
																					if(true) {
																						for(int index$sample104$83 = 0; index$sample104$83 < state.noStates; index$sample104$83 += 1) {
																							int distributionTempVariable$var102$85 = index$sample104$83;
																							double cv$probabilitySample104Value84 = (cv$probabilitySample123Value70 * state.distribution$sample104[((index$sample$82 - 0) / 1)][index$sample104$83]);
																							{
																								int traceTempVariable$currentState$86_1 = distributionTempVariable$var102$85;
																								if((index$sample$82 == sample)) {
																									if((0 == timeStep$var136)) {
																										{
																											for(int var66 = 0; var66 < state.noStates; var66 += 1) {
																												if((var66 == traceTempVariable$currentState$86_1)) {
																													{
																														double var148 = state.metric_mean[traceTempVariable$currentState$86_1];
																														double var149 = state.metric_var[traceTempVariable$currentState$86_1];
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
						state.logProbability$sample157[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = cv$sampleProbability;
					}
				}
			}
			boolean cv$guard$metric_g = false;
			state.logProbability$var151 = (state.logProbability$var151 + cv$accumulator);
			{
				{
					if(!cv$guard$metric_g) {
						cv$guard$metric_g = true;
						state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample157 = (((state.fixedFlag$sample52 && state.fixedFlag$sample68) && state.fixedFlag$sample104) && state.fixedFlag$sample123);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					if(state.metric_valid_g[sample][timeStep$var136]) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = state.logProbability$sample157[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$sampleReached = true;
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					}
				}
			}
			boolean cv$guard$metric_g = false;
			state.logProbability$var151 = (state.logProbability$var151 + cv$accumulator);
			{
				{
					if(!cv$guard$metric_g) {
						cv$guard$metric_g = true;
						state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample104() {
		if(!state.fixedProbFlag$sample104) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$sample$1 = sample;
				{
					{
						int cv$sampleValue = state.st[sample][0];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[cv$sampleValue])) && (state.initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(state.initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample104[((sample - 0) / 1)] = cv$sampleProbability;
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample104)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample104 = (state.fixedFlag$sample104 && state.fixedFlag$sample19);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample104[((sample - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample104)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample123() {
		if(!state.fixedProbFlag$sample123) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$timeStep$1 = timeStep$var113;
					int index$sample$2 = sample;
					{
						{
							int cv$sampleValue = state.st[sample][timeStep$var113];
							{
								{
									double[] var119 = state.m[state.st[sample][(timeStep$var113 - 1)]];
									double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					state.logProbability$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = cv$sampleProbability;
				}
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample123)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample123 = ((state.fixedFlag$sample123 && state.fixedFlag$sample32) && state.fixedFlag$sample104);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = state.logProbability$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample123)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample145() {
		if(!state.fixedProbFlag$sample145) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							boolean cv$sampleValue = state.metric_valid_g[sample][timeStep$var136];
							{
								{
									double var139 = state.metric_valid_bias[state.st[sample][timeStep$var136]];
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
					state.logProbability$sample145[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = cv$sampleProbability;
				}
			}
			boolean cv$guard$metric_valid_g = false;
			state.logProbability$metric_valid_1d = (state.logProbability$metric_valid_1d + cv$accumulator);
			{
				{
					if(!cv$guard$metric_valid_g) {
						cv$guard$metric_valid_g = true;
						state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample145 = ((state.fixedFlag$sample84 && state.fixedFlag$sample104) && state.fixedFlag$sample123);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = state.logProbability$sample145[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			boolean cv$guard$metric_valid_g = false;
			state.logProbability$metric_valid_1d = (state.logProbability$metric_valid_1d + cv$accumulator);
			{
				{
					if(!cv$guard$metric_valid_g) {
						cv$guard$metric_valid_g = true;
						state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample157() {
		if(!state.fixedProbFlag$sample157) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					if(state.metric_valid_g[sample][timeStep$var136]) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							{
								double cv$sampleValue = state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)];
								{
									{
										double var148 = state.metric_mean[state.st[sample][timeStep$var136]];
										double var149 = state.metric_var[state.st[sample][timeStep$var136]];
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
						state.logProbability$sample157[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = cv$sampleProbability;
					}
				}
			}
			boolean cv$guard$metric_g = false;
			state.logProbability$var151 = (state.logProbability$var151 + cv$accumulator);
			{
				{
					if(!cv$guard$metric_g) {
						cv$guard$metric_g = true;
						state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample157 = (((state.fixedFlag$sample52 && state.fixedFlag$sample68) && state.fixedFlag$sample104) && state.fixedFlag$sample123);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					if(state.metric_valid_g[sample][timeStep$var136]) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = state.logProbability$sample157[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$sampleReached = true;
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					}
				}
			}
			boolean cv$guard$metric_g = false;
			state.logProbability$var151 = (state.logProbability$var151 + cv$accumulator);
			{
				{
					if(!cv$guard$metric_g) {
						cv$guard$metric_g = true;
						state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample19() {
		if(!state.fixedProbFlag$sample19) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double[] cv$sampleValue = state.initialStateDistribution;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, state.v, state.noStates));
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
			state.logProbability$initialStateDistribution = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample19)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample19 = state.fixedFlag$sample19;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$initialStateDistribution;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample19)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample32() {
		if(!state.fixedProbFlag$sample32) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var31 = 0; var31 < state.noStates; var31 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double[] cv$sampleValue = state.m[var31];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, state.v, state.noStates));
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
			state.logProbability$var32 = cv$sampleAccumulator;
			state.logProbability$m = (state.logProbability$m + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample32)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample32 = state.fixedFlag$sample32;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var31 = 0; var31 < state.noStates; var31 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var32;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$m = (state.logProbability$m + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample32)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample52() {
		if(!state.fixedProbFlag$sample52) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var50 = 0; var50 < state.noStates; var50 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.metric_mean[var50];
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
			state.logProbability$var51 = cv$sampleAccumulator;
			state.logProbability$metric_mean = (state.logProbability$metric_mean + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample52)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample52 = state.fixedFlag$sample52;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var50 = 0; var50 < state.noStates; var50 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var51;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$metric_mean = (state.logProbability$metric_mean + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample52)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample68() {
		if(!state.fixedProbFlag$sample68) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var66 = 0; var66 < state.noStates; var66 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.metric_var[var66];
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
			state.logProbability$var67 = cv$sampleAccumulator;
			state.logProbability$metric_var = (state.logProbability$metric_var + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample68)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample68 = state.fixedFlag$sample68;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var66 = 0; var66 < state.noStates; var66 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var67;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$metric_var = (state.logProbability$metric_var + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample68)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample84() {
		if(!state.fixedProbFlag$sample84) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var82 = 0; var82 < state.noStates; var82 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.metric_valid_bias[var82];
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
			state.logProbability$var83 = cv$sampleAccumulator;
			state.logProbability$metric_valid_bias = (state.logProbability$metric_valid_bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample84)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample84 = state.fixedFlag$sample84;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var82 = 0; var82 < state.noStates; var82 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var83;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$metric_valid_bias = (state.logProbability$metric_valid_bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample84)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1) {
						double[] var32 = state.m[var31];
						if(!state.fixedFlag$sample32)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var32);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1) {
						if(!state.fixedFlag$sample52)
							state.metric_mean[var50] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$1)));
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1) {
						if(!state.fixedFlag$sample68)
							state.metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1) {
						if(!state.fixedFlag$sample84)
							state.metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noSamples, 1,
			(int forStart$index$sample, int forEnd$index$sample, int threadID$index$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$sample = forStart$index$sample; index$sample < forEnd$index$sample; index$sample += 1) {
						int sample = index$sample;
						int threadID$sample = threadID$index$sample;
						int[] var99 = state.st[sample];
						if(!state.fixedFlag$sample104)
							var99[0] = DistributionSampling.sampleCategorical(RNG$1, state.initialStateDistribution, state.noStates);
						int[] var114 = state.st[sample];
						for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
							if(!state.fixedFlag$sample123)
								var114[timeStep$var113] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.st[sample][(timeStep$var113 - 1)]], state.noStates);
						}
						boolean[] metric_valid_1d = state.metric_valid_g[sample];
						double[] metric_1d = state.metric_g[sample];
						parallelFor(RNG$1, 0, state.length$metric[sample], 1,
							(int forStart$timeStep$var136, int forEnd$timeStep$var136, int threadID$timeStep$var136, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int timeStep$var136 = forStart$timeStep$var136; timeStep$var136 < forEnd$timeStep$var136; timeStep$var136 += 1) {
										metric_valid_1d[timeStep$var136] = DistributionSampling.sampleBernoulli(RNG$2, state.metric_valid_bias[state.st[sample][timeStep$var136]]);
										if(metric_valid_1d[timeStep$var136]) {
											if(!state.fixedFlag$sample157)
												state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = ((Math.sqrt(state.metric_var[state.st[sample][timeStep$var136]]) * DistributionSampling.sampleGaussian(RNG$2)) + state.metric_mean[state.st[sample][timeStep$var136]]);
											metric_1d[timeStep$var136] = state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)];
										}
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1) {
						double[] var32 = state.m[var31];
						if(!state.fixedFlag$sample32)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var32);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1) {
						if(!state.fixedFlag$sample52)
							state.metric_mean[var50] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$1)));
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1) {
						if(!state.fixedFlag$sample68)
							state.metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1) {
						if(!state.fixedFlag$sample84)
							state.metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noSamples, 1,
			(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
						double[] cv$distribution$sample104 = state.distribution$sample104[((sample - 0) / 1)];
						for(int index$var101 = 0; index$var101 < state.noStates; index$var101 += 1) {
							double cv$value = ((((((0.0 <= index$var101) && (index$var101 < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[index$var101])) && (state.initialStateDistribution[index$var101] <= 1.0))?state.initialStateDistribution[index$var101]:0.0);
							if(!state.fixedFlag$sample104)
								cv$distribution$sample104[index$var101] = cv$value;
						}
						for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
							double[] cv$distribution$sample123 = state.distribution$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)];
							for(int index$var120 = 0; index$var120 < state.noStates; index$var120 += 1) {
								if(!state.fixedFlag$sample123)
									cv$distribution$sample123[index$var120] = 0.0;
							}
							if(state.fixedFlag$sample104) {
								{
									for(int index$sample$1_1 = 0; index$sample$1_1 < state.noSamples; index$sample$1_1 += 1) {
										if((index$sample$1_1 == sample)) {
											if((0 == (timeStep$var113 - 1))) {
												{
													for(int var31 = 0; var31 < state.noStates; var31 += 1) {
														if((var31 == state.st[sample][(timeStep$var113 - 1)])) {
															{
																double[] var119 = state.m[state.st[sample][(timeStep$var113 - 1)]];
																for(int index$var120 = 0; index$var120 < state.noStates; index$var120 += 1) {
																	if(!state.fixedFlag$sample123)
																		cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (1.0 * ((((((0.0 <= index$var120) && (index$var120 < state.noStates)) && (0 < state.noStates)) && (0.0 <= var119[index$var120])) && (var119[index$var120] <= 1.0))?var119[index$var120]:0.0)));
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
								for(int index$sample$2 = 0; index$sample$2 < state.noSamples; index$sample$2 += 1) {
									if(true) {
										for(int index$sample104$3 = 0; index$sample104$3 < state.noStates; index$sample104$3 += 1) {
											int distributionTempVariable$var102$5 = index$sample104$3;
											double cv$probabilitySample104Value4 = (1.0 * state.distribution$sample104[((index$sample$2 - 0) / 1)][index$sample104$3]);
											{
												int traceTempVariable$var118$6_1 = distributionTempVariable$var102$5;
												if((index$sample$2 == sample)) {
													if((0 == (timeStep$var113 - 1))) {
														{
															for(int var31 = 0; var31 < state.noStates; var31 += 1) {
																if((var31 == traceTempVariable$var118$6_1)) {
																	{
																		double[] var119 = state.m[traceTempVariable$var118$6_1];
																		for(int index$var120 = 0; index$var120 < state.noStates; index$var120 += 1) {
																			if(!state.fixedFlag$sample123)
																				cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (cv$probabilitySample104Value4 * ((((((0.0 <= index$var120) && (index$var120 < state.noStates)) && (0 < state.noStates)) && (0.0 <= var119[index$var120])) && (var119[index$var120] <= 1.0))?var119[index$var120]:0.0)));
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							if(state.fixedFlag$sample123) {
								{
									for(int index$sample$9_1 = 0; index$sample$9_1 < state.noSamples; index$sample$9_1 += 1) {
										for(int index$timeStep$9_2 = 1; index$timeStep$9_2 < state.length$metric[index$sample$9_1]; index$timeStep$9_2 += 1) {
											if((index$sample$9_1 == sample)) {
												if((index$timeStep$9_2 == (timeStep$var113 - 1))) {
													{
														for(int var31 = 0; var31 < state.noStates; var31 += 1) {
															if((var31 == state.st[sample][(timeStep$var113 - 1)])) {
																{
																	double[] var119 = state.m[state.st[sample][(timeStep$var113 - 1)]];
																	for(int index$var120 = 0; index$var120 < state.noStates; index$var120 += 1) {
																		if(!state.fixedFlag$sample123)
																			cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (1.0 * ((((((0.0 <= index$var120) && (index$var120 < state.noStates)) && (0 < state.noStates)) && (0.0 <= var119[index$var120])) && (var119[index$var120] <= 1.0))?var119[index$var120]:0.0)));
																	}
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
								for(int index$sample$10 = 0; index$sample$10 < state.noSamples; index$sample$10 += 1) {
									for(int index$timeStep$11 = 1; index$timeStep$11 < state.length$metric[index$sample$10]; index$timeStep$11 += 1) {
										if(true) {
											for(int index$sample123$12 = 0; index$sample123$12 < state.noStates; index$sample123$12 += 1) {
												int distributionTempVariable$var121$14 = index$sample123$12;
												double cv$probabilitySample123Value13 = (1.0 * state.distribution$sample123[((index$sample$10 - 0) / 1)][((index$timeStep$11 - 1) / 1)][index$sample123$12]);
												{
													int traceTempVariable$var118$15_1 = distributionTempVariable$var121$14;
													if((index$sample$10 == sample)) {
														if((index$timeStep$11 == (timeStep$var113 - 1))) {
															{
																for(int var31 = 0; var31 < state.noStates; var31 += 1) {
																	if((var31 == traceTempVariable$var118$15_1)) {
																		{
																			double[] var119 = state.m[traceTempVariable$var118$15_1];
																			for(int index$var120 = 0; index$var120 < state.noStates; index$var120 += 1) {
																				if(!state.fixedFlag$sample123)
																					cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (cv$probabilitySample123Value13 * ((((((0.0 <= index$var120) && (index$var120 < state.noStates)) && (0 < state.noStates)) && (0.0 <= var119[index$var120])) && (var119[index$var120] <= 1.0))?var119[index$var120]:0.0)));
																			}
																		}
																	}
																}
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
							for(int index$var120 = 0; index$var120 < state.noStates; index$var120 += 1) {
								if(!state.fixedFlag$sample123)
									cv$var120$sum = (cv$var120$sum + cv$distribution$sample123[index$var120]);
							}
							for(int index$var120 = 0; index$var120 < state.noStates; index$var120 += 1) {
								if(!state.fixedFlag$sample123)
									cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] / cv$var120$sum);
							}
						}
					}
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1) {
						double[] var32 = state.m[var31];
						if(!state.fixedFlag$sample32)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var32);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1) {
						if(!state.fixedFlag$sample52)
							state.metric_mean[var50] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$1)));
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1) {
						if(!state.fixedFlag$sample68)
							state.metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1) {
						if(!state.fixedFlag$sample84)
							state.metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noSamples, 1,
			(int forStart$index$sample, int forEnd$index$sample, int threadID$index$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$sample = forStart$index$sample; index$sample < forEnd$index$sample; index$sample += 1) {
						int sample = index$sample;
						int threadID$sample = threadID$index$sample;
						int[] var99 = state.st[sample];
						if(!state.fixedFlag$sample104)
							var99[0] = DistributionSampling.sampleCategorical(RNG$1, state.initialStateDistribution, state.noStates);
						int[] var114 = state.st[sample];
						for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
							if(!state.fixedFlag$sample123)
								var114[timeStep$var113] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.st[sample][(timeStep$var113 - 1)]], state.noStates);
						}
						boolean[] metric_valid_1d = state.metric_valid_g[sample];
						double[] metric_1d = state.metric_g[sample];
						parallelFor(RNG$1, 0, state.length$metric[sample], 1,
							(int forStart$timeStep$var136, int forEnd$timeStep$var136, int threadID$timeStep$var136, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int timeStep$var136 = forStart$timeStep$var136; timeStep$var136 < forEnd$timeStep$var136; timeStep$var136 += 1) {
										metric_valid_1d[timeStep$var136] = DistributionSampling.sampleBernoulli(RNG$2, state.metric_valid_bias[state.st[sample][timeStep$var136]]);
										if(metric_valid_1d[timeStep$var136]) {
											if(!state.fixedFlag$sample157)
												state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = ((Math.sqrt(state.metric_var[state.st[sample][timeStep$var136]]) * DistributionSampling.sampleGaussian(RNG$2)) + state.metric_mean[state.st[sample][timeStep$var136]]);
											metric_1d[timeStep$var136] = state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)];
										}
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1) {
						double[] var32 = state.m[var31];
						if(!state.fixedFlag$sample32)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var32);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1) {
						if(!state.fixedFlag$sample52)
							state.metric_mean[var50] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$1)));
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1) {
						if(!state.fixedFlag$sample68)
							state.metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1) {
						if(!state.fixedFlag$sample84)
							state.metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noSamples, 1,
			(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
						int[] var99 = state.st[sample];
						if(!state.fixedFlag$sample104)
							var99[0] = DistributionSampling.sampleCategorical(RNG$1, state.initialStateDistribution, state.noStates);
						int[] var114 = state.st[sample];
						for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
							if(!state.fixedFlag$sample123)
								var114[timeStep$var113] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.st[sample][(timeStep$var113 - 1)]], state.noStates);
						}
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1) {
						double[] var32 = state.m[var31];
						if(!state.fixedFlag$sample32)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var32);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1) {
						if(!state.fixedFlag$sample52)
							state.metric_mean[var50] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$1)));
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1) {
						if(!state.fixedFlag$sample68)
							state.metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1) {
						if(!state.fixedFlag$sample84)
							state.metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noSamples, 1,
			(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
						int[] var99 = state.st[sample];
						if(!state.fixedFlag$sample104)
							var99[0] = DistributionSampling.sampleCategorical(RNG$1, state.initialStateDistribution, state.noStates);
						int[] var114 = state.st[sample];
						for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
							if(!state.fixedFlag$sample123)
								var114[timeStep$var113] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.st[sample][(timeStep$var113 - 1)]], state.noStates);
						}
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample19)
				inferSample19();
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1) {
							if(!state.fixedFlag$sample32)
								inferSample32(var31, threadID$var31, RNG$1);
						}
				}
			);
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1) {
							if(!state.fixedFlag$sample52)
								inferSample52(var50, threadID$var50, RNG$1);
						}
				}
			);
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1) {
							if(!state.fixedFlag$sample68)
								inferSample68(var66, threadID$var66, RNG$1);
						}
				}
			);
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1) {
							if(!state.fixedFlag$sample84)
								inferSample84(var82, threadID$var82, RNG$1);
						}
				}
			);
			parallelFor(state.RNG$, 0, state.noSamples, 1,
				(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
							if(!state.fixedFlag$sample104)
								inferSample104(sample, threadID$sample, RNG$1);
							for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
								if(!state.fixedFlag$sample123)
									inferSample123(sample, timeStep$var113, threadID$sample, RNG$1);
							}
						}
				}
			);
		} else {
			parallelFor(state.RNG$, 0, state.noSamples, 1,
				(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
							for(int timeStep$var113 = (state.length$metric[sample] - ((((state.length$metric[sample] - 1) - 1) % 1) + 1)); timeStep$var113 >= ((1 - 1) + 1); timeStep$var113 -= 1) {
								if(!state.fixedFlag$sample123)
									inferSample123(sample, timeStep$var113, threadID$sample, RNG$1);
							}
							if(!state.fixedFlag$sample104)
								inferSample104(sample, threadID$sample, RNG$1);
						}
				}
			);
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1) {
							if(!state.fixedFlag$sample84)
								inferSample84(var82, threadID$var82, RNG$1);
						}
				}
			);
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1) {
							if(!state.fixedFlag$sample68)
								inferSample68(var66, threadID$var66, RNG$1);
						}
				}
			);
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1) {
							if(!state.fixedFlag$sample52)
								inferSample52(var50, threadID$var50, RNG$1);
						}
				}
			);
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1) {
							if(!state.fixedFlag$sample32)
								inferSample32(var31, threadID$var31, RNG$1);
						}
				}
			);
			if(!state.fixedFlag$sample19)
				inferSample19();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample19)
			drawValueSample19();
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1) {
						if(!state.constrainedFlag$sample32[((var31 - 0) / 1)])
							drawValueSample32(var31, threadID$var31, RNG$1);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1) {
						if(!state.constrainedFlag$sample52[((var50 - 0) / 1)])
							drawValueSample52(var50, threadID$var50, RNG$1);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1) {
						if(!state.constrainedFlag$sample68[((var66 - 0) / 1)])
							drawValueSample68(var66, threadID$var66, RNG$1);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1) {
						if(!state.constrainedFlag$sample84[((var82 - 0) / 1)])
							drawValueSample84(var82, threadID$var82, RNG$1);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noSamples, 1,
			(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
						if(!state.constrainedFlag$sample104[((sample - 0) / 1)])
							drawValueSample104(sample, threadID$sample, RNG$1);
						for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
							if(!state.constrainedFlag$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)])
								drawValueSample123(sample, timeStep$var113, threadID$sample, RNG$1);
						}
					}
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample19)
			state.logProbability$initialStateDistribution = Double.NaN;
		state.logProbability$m = 0.0;
		if(!state.fixedProbFlag$sample32)
			state.logProbability$var32 = Double.NaN;
		state.logProbability$metric_mean = 0.0;
		if(!state.fixedProbFlag$sample52)
			state.logProbability$var51 = Double.NaN;
		state.logProbability$metric_var = 0.0;
		if(!state.fixedProbFlag$sample68)
			state.logProbability$var67 = Double.NaN;
		state.logProbability$metric_valid_bias = 0.0;
		if(!state.fixedProbFlag$sample84)
			state.logProbability$var83 = Double.NaN;
		state.logProbability$st = 0.0;
		if(!state.fixedProbFlag$sample104) {
			for(int sample = 0; sample < state.noSamples; sample += 1)
				state.logProbability$sample104[((sample - 0) / 1)] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample123) {
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1)
					state.logProbability$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = Double.NaN;
			}
		}
		state.logProbability$metric_valid_1d = 0.0;
		state.logProbability$metric_valid_g = 0.0;
		if(!state.fixedProbFlag$sample145) {
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1)
					state.logProbability$sample145[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = Double.NaN;
			}
		}
		state.logProbability$var151 = 0.0;
		state.logProbability$metric_g = 0.0;
		if(!state.fixedProbFlag$sample157) {
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1)
					state.logProbability$sample157[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = Double.NaN;
			}
		}
	}

	@Override
	public final void initializeModel() {
		state.noSamples = state.length$metric.length;
		for(int var15 = 0; var15 < state.noStates; var15 += 1)
			state.v[var15] = 0.1;
		for(int index$constrainedFlag$sample32$1 = 0; index$constrainedFlag$sample32$1 < state.constrainedFlag$sample32.length; index$constrainedFlag$sample32$1 += 1)
			state.constrainedFlag$sample32[index$constrainedFlag$sample32$1] = true;
		for(int index$constrainedFlag$sample123$1 = 0; index$constrainedFlag$sample123$1 < state.constrainedFlag$sample123.length; index$constrainedFlag$sample123$1 += 1) {
			boolean[] cv$constrainedFlag$sample123$1 = state.constrainedFlag$sample123[index$constrainedFlag$sample123$1];
			for(int index$constrainedFlag$sample123$2 = 0; index$constrainedFlag$sample123$2 < cv$constrainedFlag$sample123$1.length; index$constrainedFlag$sample123$2 += 1)
				cv$constrainedFlag$sample123$1[index$constrainedFlag$sample123$2] = true;
		}
		for(int index$constrainedFlag$sample104$1 = 0; index$constrainedFlag$sample104$1 < state.constrainedFlag$sample104.length; index$constrainedFlag$sample104$1 += 1)
			state.constrainedFlag$sample104[index$constrainedFlag$sample104$1] = true;
		for(int index$constrainedFlag$sample84$1 = 0; index$constrainedFlag$sample84$1 < state.constrainedFlag$sample84.length; index$constrainedFlag$sample84$1 += 1)
			state.constrainedFlag$sample84[index$constrainedFlag$sample84$1] = true;
		for(int index$constrainedFlag$sample68$1 = 0; index$constrainedFlag$sample68$1 < state.constrainedFlag$sample68.length; index$constrainedFlag$sample68$1 += 1)
			state.constrainedFlag$sample68[index$constrainedFlag$sample68$1] = true;
		for(int index$constrainedFlag$sample52$1 = 0; index$constrainedFlag$sample52$1 < state.constrainedFlag$sample52.length; index$constrainedFlag$sample52$1 += 1)
			state.constrainedFlag$sample52[index$constrainedFlag$sample52$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample19)
			logProbabilityValue$sample19();
		if(state.fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(state.fixedFlag$sample52)
			logProbabilityValue$sample52();
		if(state.fixedFlag$sample68)
			logProbabilityValue$sample68();
		if(state.fixedFlag$sample84)
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
		state.fixedFlag$sample157 = false;
		{
			{
				boolean[][] cv$source1 = state.metric_valid;
				boolean[][] cv$target1 = state.metric_valid_g;
				int cv$length1 = cv$target1.length;
				for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
					boolean[] cv$source2 = cv$source1[cv$index1];
					boolean[] cv$target2 = cv$target1[cv$index1];
					int cv$length2 = cv$target2.length;
					for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
						cv$target2[cv$index2] = cv$source2[cv$index2];
				}
			}
			for(int sample = (state.noSamples - ((((state.noSamples - 1) - 0) % 1) + 1)); sample >= ((0 - 1) + 1); sample -= 1) {
				for(int timeStep$var136 = (state.length$metric[sample] - ((((state.length$metric[sample] - 1) - 0) % 1) + 1)); timeStep$var136 >= ((0 - 1) + 1); timeStep$var136 -= 1) {
					state.metric_g[sample][timeStep$var136] = state.metric[sample][timeStep$var136];
					if(state.metric_valid_g[sample][timeStep$var136]) {
						{
							{
								{
									{
										for(int index$timeStep$2_1 = 0; index$timeStep$2_1 < state.length$metric[sample]; index$timeStep$2_1 += 1) {
											if((timeStep$var136 == index$timeStep$2_1))
												state.var151[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = state.metric_g[sample][timeStep$var136];
										}
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