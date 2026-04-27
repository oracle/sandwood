package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMMMetrics4$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMMMetrics4.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMMetrics4$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$distributionAccumulator$var73;
		double[] cv$var20$countGlobal;
		double[] cv$var33$countGlobal;
		double[] cv$var55$stateProbabilityGlobal;
		double[] cv$var74$stateProbabilityGlobal;
		boolean[][][] guard$sample57gaussian255$global;
		boolean[][][] guard$sample76gaussian255$global;

		@Override
		public final void allocateScratch() {
			{
				cv$var20$countGlobal = new double[state.noStates];
			}
			{
				cv$var33$countGlobal = new double[state.noStates];
			}
			{
				int cv$var34$max = state.noStates;
				cv$distributionAccumulator$var73 = new double[cv$var34$max];
			}
			{
				cv$var55$stateProbabilityGlobal = new double[state.noStates];
			}
			{
				int cv$max_sample$var196 = 0;
				int cv$max_server = 0;
				int cv$max_timeStep$var226 = 0;
				for(int sample$var196 = 0; sample$var196 < state.length$metric.length; sample$var196 += 1) {
					for(int server = 0; server < state.length$metric[0].length; server += 1)
						cv$max_timeStep$var226 = Math.max(cv$max_timeStep$var226, ((state.length$metric[sample$var196][0] - 0) / 1));
					cv$max_server = Math.max(cv$max_server, ((state.length$metric[0].length - 0) / 1));
				}
				cv$max_sample$var196 = Math.max(cv$max_sample$var196, ((state.length$metric.length - 0) / 1));
				guard$sample57gaussian255$global = new boolean[cv$max_sample$var196][cv$max_server][cv$max_timeStep$var226];
			}
			{
				int cv$var34$max = state.noStates;
				cv$var74$stateProbabilityGlobal = new double[cv$var34$max];
			}
			{
				int cv$max_sample$var196 = 0;
				int cv$max_server = 0;
				int cv$max_timeStep$var226 = 0;
				for(int sample$var196 = 0; sample$var196 < state.length$metric.length; sample$var196 += 1) {
					for(int server = 0; server < state.length$metric[0].length; server += 1)
						cv$max_timeStep$var226 = Math.max(cv$max_timeStep$var226, ((state.length$metric[sample$var196][0] - 0) / 1));
					cv$max_server = Math.max(cv$max_server, ((state.length$metric[0].length - 0) / 1));
				}
				cv$max_sample$var196 = Math.max(cv$max_sample$var196, ((state.length$metric.length - 0) / 1));
				guard$sample76gaussian255$global = new boolean[cv$max_sample$var196][cv$max_server][cv$max_timeStep$var226];
			}
		}
	}


	public HMMMetrics4$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample134(int var119, int var129) {
		double[] var120 = state.current_metric_mean[var119];
		var120[var129] = (0.0 + (((double)state.max_metric - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
	}

	private final void drawValueSample162(int var146, int var156) {
		double[] var147 = state.current_metric_var[var146];
		var147[var156] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
	}

	private final void drawValueSample190(int var173, int var183) {
		double[] var174 = state.current_metric_valid_bias[var173];
		var174[var183] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void drawValueSample20() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
	}

	private final void drawValueSample33(int var32) {
		double[] var33 = state.m[var32];
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, var33);
	}

	private final void drawValueSample57(int sample$var45) {
		int index$sample$1 = sample$var45;
		int[] var52 = state.st[sample$var45];
		var52[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
	}

	private final void drawValueSample76(int sample$var45, int timeStep$var66) {
		int index$timeStep$1 = timeStep$var66;
		int index$sample$2 = sample$var45;
		int[] var67 = state.st[sample$var45];
		var67[timeStep$var66] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample$var45][(timeStep$var66 - 1)]], state.noStates);
	}

	private final void inferSample134(int var119, int var129) {
		if(true) {
			state.constrainedFlag$sample134[((var119 - 0) / 1)][((var129 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = state.current_metric_mean[var119][var129];
			double cv$originalProbability = 0.0;
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			if((cv$var < 0.01))
				cv$var = 0.01;
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample134[((var119 - 0) / 1)][((var129 - 0) / 1)] || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						double var130 = cv$proposedValue;
						{
							{
								{
									double[] var120 = state.current_metric_mean[var119];
									var120[var129] = cv$currentValue;
								}
							}
						}
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double var107 = (double)state.max_metric;
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < var107))?(-Math.log((var107 - 0.0))):Double.NEGATIVE_INFINITY));
						{
							{
								for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
									for(int server = 0; server < state.noServers; server += 1) {
										for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
											if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
												if(state.fixedFlag$sample57) {
													{
														for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
															if((sample$var45 == sample$var196)) {
																if((0 == timeStep$var226)) {
																	{
																		double traceTempVariable$var241$11_1 = cv$currentValue;
																		if((var119 == server)) {
																			if((var129 == state.st[sample$var196][timeStep$var226])) {
																				{
																					{
																						boolean cv$sampleConstrained = true;
																						if(cv$sampleConstrained) {
																							state.constrainedFlag$sample134[((var119 - 0) / 1)][((var129 - 0) / 1)] = true;
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								{
																									for(int index$sample$29_1 = 0; index$sample$29_1 < state.noSamples; index$sample$29_1 += 1) {
																										if((index$sample$29_1 == sample$var196)) {
																											if((0 == timeStep$var226)) {
																												{
																													for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																														for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																															if((var146 == server)) {
																																if((var156 == state.st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			{
																																				double var243 = state.current_metric_var[server][state.st[sample$var196][timeStep$var226]];
																																				if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																								if(state.fixedFlag$sample76) {
																									{
																										for(int index$sample$31_1 = 0; index$sample$31_1 < state.noSamples; index$sample$31_1 += 1) {
																											for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$31_1][0]; timeStep$var66 += 1) {
																												if((index$sample$31_1 == sample$var196)) {
																													if((timeStep$var66 == timeStep$var226)) {
																														{
																															for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																	if((var146 == server)) {
																																		if((var156 == state.st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					{
																																						double var243 = state.current_metric_var[server][state.st[sample$var196][timeStep$var226]];
																																						if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																									for(int index$sample$32 = 0; index$sample$32 < state.noSamples; index$sample$32 += 1) {
																										for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$32][0]; timeStep$var66 += 1) {
																											if(true) {
																												for(int index$sample76$34 = 0; index$sample76$34 < state.noStates; index$sample76$34 += 1) {
																													int distributionTempVariable$var74$36 = index$sample76$34;
																													double cv$probabilitySample76Value35 = (1.0 * state.distribution$sample76[((index$sample$32 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$34]);
																													{
																														int traceTempVariable$currentState$37_1 = distributionTempVariable$var74$36;
																														if((index$sample$32 == sample$var196)) {
																															if((timeStep$var66 == timeStep$var226)) {
																																{
																																	for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == traceTempVariable$currentState$37_1)) {
																																					{
																																						{
																																							{
																																								double var243 = state.current_metric_var[server][traceTempVariable$currentState$37_1];
																																								if(((Math.log(cv$probabilitySample76Value35) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value35) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value35) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value35) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value35) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$11_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value35);
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
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
													for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
														if(true) {
															for(int index$sample57$7 = 0; index$sample57$7 < state.noStates; index$sample57$7 += 1) {
																int distributionTempVariable$var55$9 = index$sample57$7;
																double cv$probabilitySample57Value8 = (1.0 * state.distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$7]);
																{
																	int traceTempVariable$currentState$10_1 = distributionTempVariable$var55$9;
																	if((sample$var45 == sample$var196)) {
																		if((0 == timeStep$var226)) {
																			{
																				double traceTempVariable$var241$12_1 = cv$currentValue;
																				if((var119 == server)) {
																					if((var129 == traceTempVariable$currentState$10_1)) {
																						{
																							{
																								boolean cv$sampleConstrained = true;
																								if(cv$sampleConstrained) {
																									state.constrainedFlag$sample134[((var119 - 0) / 1)][((var129 - 0) / 1)] = true;
																									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																									double cv$consumerDistributionProbabilityAccumulator = 1.0;
																									{
																										{
																											int traceTempVariable$currentState$40_1 = distributionTempVariable$var55$9;
																											if((sample$var45 == sample$var196)) {
																												if((0 == timeStep$var226)) {
																													{
																														for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																															for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																if((var146 == server)) {
																																	if((var156 == traceTempVariable$currentState$40_1)) {
																																		{
																																			{
																																				{
																																					double var243 = state.current_metric_var[server][traceTempVariable$currentState$40_1];
																																					if(((Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value8);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																										for(int index$sample$41 = 0; index$sample$41 < state.noSamples; index$sample$41 += 1) {
																											if(!(index$sample$41 == sample$var45)) {
																												for(int index$sample57$42 = 0; index$sample57$42 < state.noStates; index$sample57$42 += 1) {
																													int distributionTempVariable$var55$44 = index$sample57$42;
																													double cv$probabilitySample57Value43 = (cv$probabilitySample57Value8 * state.distribution$sample57[((index$sample$41 - 0) / 1)][index$sample57$42]);
																													{
																														int traceTempVariable$currentState$45_1 = distributionTempVariable$var55$44;
																														if((index$sample$41 == sample$var196)) {
																															if((0 == timeStep$var226)) {
																																{
																																	for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == traceTempVariable$currentState$45_1)) {
																																					{
																																						{
																																							{
																																								double var243 = state.current_metric_var[server][traceTempVariable$currentState$45_1];
																																								if(((Math.log(cv$probabilitySample57Value43) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value43) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value43) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value43) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value43) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value43);
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																										if(state.fixedFlag$sample76) {
																											{
																												for(int index$sample$48_1 = 0; index$sample$48_1 < state.noSamples; index$sample$48_1 += 1) {
																													for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$48_1][0]; timeStep$var66 += 1) {
																														if((index$sample$48_1 == sample$var196)) {
																															if((timeStep$var66 == timeStep$var226)) {
																																{
																																	for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == traceTempVariable$currentState$10_1)) {
																																					{
																																						{
																																							{
																																								double var243 = state.current_metric_var[server][traceTempVariable$currentState$10_1];
																																								if(((Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value8);
																																							}
																																						}
																																					}
																																				}
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
																											for(int index$sample$49 = 0; index$sample$49 < state.noSamples; index$sample$49 += 1) {
																												for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$49][0]; timeStep$var66 += 1) {
																													if(true) {
																														for(int index$sample76$51 = 0; index$sample76$51 < state.noStates; index$sample76$51 += 1) {
																															int distributionTempVariable$var74$53 = index$sample76$51;
																															double cv$probabilitySample76Value52 = (cv$probabilitySample57Value8 * state.distribution$sample76[((index$sample$49 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$51]);
																															{
																																int traceTempVariable$currentState$54_1 = distributionTempVariable$var74$53;
																																if((index$sample$49 == sample$var196)) {
																																	if((timeStep$var66 == timeStep$var226)) {
																																		{
																																			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																				for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																					if((var146 == server)) {
																																						if((var156 == traceTempVariable$currentState$54_1)) {
																																							{
																																								{
																																									{
																																										double var243 = state.current_metric_var[server][traceTempVariable$currentState$54_1];
																																										if(((Math.log(cv$probabilitySample76Value52) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value52) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value52) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value52) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value52) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$12_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																										}
																																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value52);
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
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
								for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
									for(int server = 0; server < state.noServers; server += 1) {
										for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
											if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
												if(state.fixedFlag$sample76) {
													{
														for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
															for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
																if((sample$var45 == sample$var196)) {
																	if((timeStep$var66 == timeStep$var226)) {
																		{
																			double traceTempVariable$var241$23_1 = cv$currentValue;
																			if((var119 == server)) {
																				if((var129 == state.st[sample$var196][timeStep$var226])) {
																					{
																						{
																							boolean cv$sampleConstrained = true;
																							if(cv$sampleConstrained) {
																								state.constrainedFlag$sample134[((var119 - 0) / 1)][((var129 - 0) / 1)] = true;
																								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																								double cv$consumerDistributionProbabilityAccumulator = 1.0;
																								{
																									if(state.fixedFlag$sample57) {
																										{
																											for(int index$sample$57_1 = 0; index$sample$57_1 < state.noSamples; index$sample$57_1 += 1) {
																												if((index$sample$57_1 == sample$var196)) {
																													if((0 == timeStep$var226)) {
																														{
																															for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																	if((var146 == server)) {
																																		if((var156 == state.st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					{
																																						double var243 = state.current_metric_var[server][state.st[sample$var196][timeStep$var226]];
																																						if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																									} else {
																										for(int index$sample$58 = 0; index$sample$58 < state.noSamples; index$sample$58 += 1) {
																											if(true) {
																												for(int index$sample57$59 = 0; index$sample57$59 < state.noStates; index$sample57$59 += 1) {
																													int distributionTempVariable$var55$61 = index$sample57$59;
																													double cv$probabilitySample57Value60 = (1.0 * state.distribution$sample57[((index$sample$58 - 0) / 1)][index$sample57$59]);
																													{
																														int traceTempVariable$currentState$62_1 = distributionTempVariable$var55$61;
																														if((index$sample$58 == sample$var196)) {
																															if((0 == timeStep$var226)) {
																																{
																																	for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == traceTempVariable$currentState$62_1)) {
																																					{
																																						{
																																							{
																																								double var243 = state.current_metric_var[server][traceTempVariable$currentState$62_1];
																																								if(((Math.log(cv$probabilitySample57Value60) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value60) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value60) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value60) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value60) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value60);
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
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
																										for(int index$sample$65_1 = 0; index$sample$65_1 < state.noSamples; index$sample$65_1 += 1) {
																											for(int index$timeStep$65_2 = 1; index$timeStep$65_2 < state.length$metric[index$sample$65_1][0]; index$timeStep$65_2 += 1) {
																												if((index$sample$65_1 == sample$var196)) {
																													if((index$timeStep$65_2 == timeStep$var226)) {
																														{
																															for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																	if((var146 == server)) {
																																		if((var156 == state.st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					{
																																						double var243 = state.current_metric_var[server][state.st[sample$var196][timeStep$var226]];
																																						if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$23_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
												} else {
													for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
														for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
															if(true) {
																for(int index$sample76$19 = 0; index$sample76$19 < state.noStates; index$sample76$19 += 1) {
																	int distributionTempVariable$var74$21 = index$sample76$19;
																	double cv$probabilitySample76Value20 = (1.0 * state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$19]);
																	{
																		int traceTempVariable$currentState$22_1 = distributionTempVariable$var74$21;
																		if((sample$var45 == sample$var196)) {
																			if((timeStep$var66 == timeStep$var226)) {
																				{
																					double traceTempVariable$var241$24_1 = cv$currentValue;
																					if((var119 == server)) {
																						if((var129 == traceTempVariable$currentState$22_1)) {
																							{
																								{
																									boolean cv$sampleConstrained = true;
																									if(cv$sampleConstrained) {
																										state.constrainedFlag$sample134[((var119 - 0) / 1)][((var129 - 0) / 1)] = true;
																										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																										double cv$consumerDistributionProbabilityAccumulator = 1.0;
																										{
																											if(state.fixedFlag$sample57) {
																												{
																													for(int index$sample$67_1 = 0; index$sample$67_1 < state.noSamples; index$sample$67_1 += 1) {
																														if((index$sample$67_1 == sample$var196)) {
																															if((0 == timeStep$var226)) {
																																{
																																	for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == traceTempVariable$currentState$22_1)) {
																																					{
																																						{
																																							{
																																								double var243 = state.current_metric_var[server][traceTempVariable$currentState$22_1];
																																								if(((Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value20);
																																							}
																																						}
																																					}
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
																												for(int index$sample$68 = 0; index$sample$68 < state.noSamples; index$sample$68 += 1) {
																													if(true) {
																														for(int index$sample57$69 = 0; index$sample57$69 < state.noStates; index$sample57$69 += 1) {
																															int distributionTempVariable$var55$71 = index$sample57$69;
																															double cv$probabilitySample57Value70 = (cv$probabilitySample76Value20 * state.distribution$sample57[((index$sample$68 - 0) / 1)][index$sample57$69]);
																															{
																																int traceTempVariable$currentState$72_1 = distributionTempVariable$var55$71;
																																if((index$sample$68 == sample$var196)) {
																																	if((0 == timeStep$var226)) {
																																		{
																																			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																				for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																					if((var146 == server)) {
																																						if((var156 == traceTempVariable$currentState$72_1)) {
																																							{
																																								{
																																									{
																																										double var243 = state.current_metric_var[server][traceTempVariable$currentState$72_1];
																																										if(((Math.log(cv$probabilitySample57Value70) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value70) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value70) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value70) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value70) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																										}
																																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value70);
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
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
																												int traceTempVariable$currentState$75_1 = distributionTempVariable$var74$21;
																												if((sample$var45 == sample$var196)) {
																													if((timeStep$var66 == timeStep$var226)) {
																														{
																															for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																	if((var146 == server)) {
																																		if((var156 == traceTempVariable$currentState$75_1)) {
																																			{
																																				{
																																					{
																																						double var243 = state.current_metric_var[server][traceTempVariable$currentState$75_1];
																																						if(((Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value20);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																											for(int index$sample$76 = 0; index$sample$76 < state.noSamples; index$sample$76 += 1) {
																												for(int index$timeStep$77 = 1; index$timeStep$77 < state.length$metric[index$sample$76][0]; index$timeStep$77 += 1) {
																													if(!((index$timeStep$77 == timeStep$var66) && (index$sample$76 == sample$var45))) {
																														for(int index$sample76$78 = 0; index$sample76$78 < state.noStates; index$sample76$78 += 1) {
																															int distributionTempVariable$var74$80 = index$sample76$78;
																															double cv$probabilitySample76Value79 = (cv$probabilitySample76Value20 * state.distribution$sample76[((index$sample$76 - 0) / 1)][((index$timeStep$77 - 1) / 1)][index$sample76$78]);
																															{
																																int traceTempVariable$currentState$81_1 = distributionTempVariable$var74$80;
																																if((index$sample$76 == sample$var196)) {
																																	if((index$timeStep$77 == timeStep$var226)) {
																																		{
																																			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																				for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																					if((var146 == server)) {
																																						if((var156 == traceTempVariable$currentState$81_1)) {
																																							{
																																								{
																																									{
																																										double var243 = state.current_metric_var[server][traceTempVariable$currentState$81_1];
																																										if(((Math.log(cv$probabilitySample76Value79) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value79) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value79) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value79) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value79) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - traceTempVariable$var241$24_1) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																										}
																																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value79);
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
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
							double var130 = cv$originalValue;
							{
								{
									{
										double[] var120 = state.current_metric_mean[var119];
										var120[var129] = var130;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample162(int var146, int var156) {
		if(true) {
			state.constrainedFlag$sample162[((var146 - 0) / 1)][((var156 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = state.current_metric_var[var146][var156];
			double cv$originalProbability = 0.0;
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			if((cv$var < 0.01))
				cv$var = 0.01;
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample162[((var146 - 0) / 1)][((var156 - 0) / 1)] || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						double var157 = cv$proposedValue;
						{
							{
								{
									double[] var147 = state.current_metric_var[var146];
									var147[var156] = cv$currentValue;
								}
							}
						}
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, 1.0, 1.0));
						{
							{
								for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
									for(int server = 0; server < state.noServers; server += 1) {
										for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
											if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
												if(state.fixedFlag$sample57) {
													{
														for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
															if((sample$var45 == sample$var196)) {
																if((0 == timeStep$var226)) {
																	{
																		double traceTempVariable$var243$11_1 = cv$currentValue;
																		if((var146 == server)) {
																			if((var156 == state.st[sample$var196][timeStep$var226])) {
																				{
																					{
																						boolean cv$sampleConstrained = true;
																						if(cv$sampleConstrained) {
																							state.constrainedFlag$sample162[((var146 - 0) / 1)][((var156 - 0) / 1)] = true;
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								{
																									for(int index$sample$29_1 = 0; index$sample$29_1 < state.noSamples; index$sample$29_1 += 1) {
																										if((index$sample$29_1 == sample$var196)) {
																											if((0 == timeStep$var226)) {
																												{
																													for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																														for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																															if((var119 == server)) {
																																if((var129 == state.st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			{
																																				double var241 = state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]];
																																				if(((Math.log(1.0) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)));
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
																								if(state.fixedFlag$sample76) {
																									{
																										for(int index$sample$31_1 = 0; index$sample$31_1 < state.noSamples; index$sample$31_1 += 1) {
																											for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$31_1][0]; timeStep$var66 += 1) {
																												if((index$sample$31_1 == sample$var196)) {
																													if((timeStep$var66 == timeStep$var226)) {
																														{
																															for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																	if((var119 == server)) {
																																		if((var129 == state.st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					{
																																						double var241 = state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]];
																																						if(((Math.log(1.0) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)));
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
																									for(int index$sample$32 = 0; index$sample$32 < state.noSamples; index$sample$32 += 1) {
																										for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$32][0]; timeStep$var66 += 1) {
																											if(true) {
																												for(int index$sample76$34 = 0; index$sample76$34 < state.noStates; index$sample76$34 += 1) {
																													int distributionTempVariable$var74$36 = index$sample76$34;
																													double cv$probabilitySample76Value35 = (1.0 * state.distribution$sample76[((index$sample$32 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$34]);
																													{
																														int traceTempVariable$currentState$37_1 = distributionTempVariable$var74$36;
																														if((index$sample$32 == sample$var196)) {
																															if((timeStep$var66 == timeStep$var226)) {
																																{
																																	for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																		for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																			if((var119 == server)) {
																																				if((var129 == traceTempVariable$currentState$37_1)) {
																																					{
																																						{
																																							{
																																								double var241 = state.current_metric_mean[server][traceTempVariable$currentState$37_1];
																																								if(((Math.log(cv$probabilitySample76Value35) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value35) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value35) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value35) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value35) + ((0.0 < traceTempVariable$var243$11_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$11_1))) - (0.5 * Math.log(traceTempVariable$var243$11_1))):Double.NEGATIVE_INFINITY)));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value35);
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
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
													for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
														if(true) {
															for(int index$sample57$7 = 0; index$sample57$7 < state.noStates; index$sample57$7 += 1) {
																int distributionTempVariable$var55$9 = index$sample57$7;
																double cv$probabilitySample57Value8 = (1.0 * state.distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$7]);
																{
																	int traceTempVariable$currentState$10_1 = distributionTempVariable$var55$9;
																	if((sample$var45 == sample$var196)) {
																		if((0 == timeStep$var226)) {
																			{
																				double traceTempVariable$var243$12_1 = cv$currentValue;
																				if((var146 == server)) {
																					if((var156 == traceTempVariable$currentState$10_1)) {
																						{
																							{
																								boolean cv$sampleConstrained = true;
																								if(cv$sampleConstrained) {
																									state.constrainedFlag$sample162[((var146 - 0) / 1)][((var156 - 0) / 1)] = true;
																									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																									double cv$consumerDistributionProbabilityAccumulator = 1.0;
																									{
																										{
																											int traceTempVariable$currentState$40_1 = distributionTempVariable$var55$9;
																											if((sample$var45 == sample$var196)) {
																												if((0 == timeStep$var226)) {
																													{
																														for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																															for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																if((var119 == server)) {
																																	if((var129 == traceTempVariable$currentState$40_1)) {
																																		{
																																			{
																																				{
																																					double var241 = state.current_metric_mean[server][traceTempVariable$currentState$40_1];
																																					if(((Math.log(cv$probabilitySample57Value8) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value8) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value8) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value8) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value8);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																										for(int index$sample$41 = 0; index$sample$41 < state.noSamples; index$sample$41 += 1) {
																											if(!(index$sample$41 == sample$var45)) {
																												for(int index$sample57$42 = 0; index$sample57$42 < state.noStates; index$sample57$42 += 1) {
																													int distributionTempVariable$var55$44 = index$sample57$42;
																													double cv$probabilitySample57Value43 = (cv$probabilitySample57Value8 * state.distribution$sample57[((index$sample$41 - 0) / 1)][index$sample57$42]);
																													{
																														int traceTempVariable$currentState$45_1 = distributionTempVariable$var55$44;
																														if((index$sample$41 == sample$var196)) {
																															if((0 == timeStep$var226)) {
																																{
																																	for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																		for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																			if((var119 == server)) {
																																				if((var129 == traceTempVariable$currentState$45_1)) {
																																					{
																																						{
																																							{
																																								double var241 = state.current_metric_mean[server][traceTempVariable$currentState$45_1];
																																								if(((Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value43) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value43);
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																										if(state.fixedFlag$sample76) {
																											{
																												for(int index$sample$48_1 = 0; index$sample$48_1 < state.noSamples; index$sample$48_1 += 1) {
																													for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$48_1][0]; timeStep$var66 += 1) {
																														if((index$sample$48_1 == sample$var196)) {
																															if((timeStep$var66 == timeStep$var226)) {
																																{
																																	for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																		for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																			if((var119 == server)) {
																																				if((var129 == traceTempVariable$currentState$10_1)) {
																																					{
																																						{
																																							{
																																								double var241 = state.current_metric_mean[server][traceTempVariable$currentState$10_1];
																																								if(((Math.log(cv$probabilitySample57Value8) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value8) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value8) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value8) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value8);
																																							}
																																						}
																																					}
																																				}
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
																											for(int index$sample$49 = 0; index$sample$49 < state.noSamples; index$sample$49 += 1) {
																												for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$49][0]; timeStep$var66 += 1) {
																													if(true) {
																														for(int index$sample76$51 = 0; index$sample76$51 < state.noStates; index$sample76$51 += 1) {
																															int distributionTempVariable$var74$53 = index$sample76$51;
																															double cv$probabilitySample76Value52 = (cv$probabilitySample57Value8 * state.distribution$sample76[((index$sample$49 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$51]);
																															{
																																int traceTempVariable$currentState$54_1 = distributionTempVariable$var74$53;
																																if((index$sample$49 == sample$var196)) {
																																	if((timeStep$var66 == timeStep$var226)) {
																																		{
																																			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																				for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																					if((var119 == server)) {
																																						if((var129 == traceTempVariable$currentState$54_1)) {
																																							{
																																								{
																																									{
																																										double var241 = state.current_metric_mean[server][traceTempVariable$currentState$54_1];
																																										if(((Math.log(cv$probabilitySample76Value52) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value52) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value52) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value52) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value52) + ((0.0 < traceTempVariable$var243$12_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$12_1))) - (0.5 * Math.log(traceTempVariable$var243$12_1))):Double.NEGATIVE_INFINITY)));
																																										}
																																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value52);
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
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
								for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
									for(int server = 0; server < state.noServers; server += 1) {
										for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
											if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
												if(state.fixedFlag$sample76) {
													{
														for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
															for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
																if((sample$var45 == sample$var196)) {
																	if((timeStep$var66 == timeStep$var226)) {
																		{
																			double traceTempVariable$var243$23_1 = cv$currentValue;
																			if((var146 == server)) {
																				if((var156 == state.st[sample$var196][timeStep$var226])) {
																					{
																						{
																							boolean cv$sampleConstrained = true;
																							if(cv$sampleConstrained) {
																								state.constrainedFlag$sample162[((var146 - 0) / 1)][((var156 - 0) / 1)] = true;
																								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																								double cv$consumerDistributionProbabilityAccumulator = 1.0;
																								{
																									if(state.fixedFlag$sample57) {
																										{
																											for(int index$sample$57_1 = 0; index$sample$57_1 < state.noSamples; index$sample$57_1 += 1) {
																												if((index$sample$57_1 == sample$var196)) {
																													if((0 == timeStep$var226)) {
																														{
																															for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																	if((var119 == server)) {
																																		if((var129 == state.st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					{
																																						double var241 = state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]];
																																						if(((Math.log(1.0) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)));
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
																									} else {
																										for(int index$sample$58 = 0; index$sample$58 < state.noSamples; index$sample$58 += 1) {
																											if(true) {
																												for(int index$sample57$59 = 0; index$sample57$59 < state.noStates; index$sample57$59 += 1) {
																													int distributionTempVariable$var55$61 = index$sample57$59;
																													double cv$probabilitySample57Value60 = (1.0 * state.distribution$sample57[((index$sample$58 - 0) / 1)][index$sample57$59]);
																													{
																														int traceTempVariable$currentState$62_1 = distributionTempVariable$var55$61;
																														if((index$sample$58 == sample$var196)) {
																															if((0 == timeStep$var226)) {
																																{
																																	for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																		for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																			if((var119 == server)) {
																																				if((var129 == traceTempVariable$currentState$62_1)) {
																																					{
																																						{
																																							{
																																								double var241 = state.current_metric_mean[server][traceTempVariable$currentState$62_1];
																																								if(((Math.log(cv$probabilitySample57Value60) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value60) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value60) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value60) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value60) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value60);
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
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
																										for(int index$sample$65_1 = 0; index$sample$65_1 < state.noSamples; index$sample$65_1 += 1) {
																											for(int index$timeStep$65_2 = 1; index$timeStep$65_2 < state.length$metric[index$sample$65_1][0]; index$timeStep$65_2 += 1) {
																												if((index$sample$65_1 == sample$var196)) {
																													if((index$timeStep$65_2 == timeStep$var226)) {
																														{
																															for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																	if((var119 == server)) {
																																		if((var129 == state.st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					{
																																						double var241 = state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]];
																																						if(((Math.log(1.0) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var243$23_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$23_1))) - (0.5 * Math.log(traceTempVariable$var243$23_1))):Double.NEGATIVE_INFINITY)));
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
												} else {
													for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
														for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
															if(true) {
																for(int index$sample76$19 = 0; index$sample76$19 < state.noStates; index$sample76$19 += 1) {
																	int distributionTempVariable$var74$21 = index$sample76$19;
																	double cv$probabilitySample76Value20 = (1.0 * state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$19]);
																	{
																		int traceTempVariable$currentState$22_1 = distributionTempVariable$var74$21;
																		if((sample$var45 == sample$var196)) {
																			if((timeStep$var66 == timeStep$var226)) {
																				{
																					double traceTempVariable$var243$24_1 = cv$currentValue;
																					if((var146 == server)) {
																						if((var156 == traceTempVariable$currentState$22_1)) {
																							{
																								{
																									boolean cv$sampleConstrained = true;
																									if(cv$sampleConstrained) {
																										state.constrainedFlag$sample162[((var146 - 0) / 1)][((var156 - 0) / 1)] = true;
																										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																										double cv$consumerDistributionProbabilityAccumulator = 1.0;
																										{
																											if(state.fixedFlag$sample57) {
																												{
																													for(int index$sample$67_1 = 0; index$sample$67_1 < state.noSamples; index$sample$67_1 += 1) {
																														if((index$sample$67_1 == sample$var196)) {
																															if((0 == timeStep$var226)) {
																																{
																																	for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																		for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																			if((var119 == server)) {
																																				if((var129 == traceTempVariable$currentState$22_1)) {
																																					{
																																						{
																																							{
																																								double var241 = state.current_metric_mean[server][traceTempVariable$currentState$22_1];
																																								if(((Math.log(cv$probabilitySample76Value20) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value20) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value20) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value20) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value20);
																																							}
																																						}
																																					}
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
																												for(int index$sample$68 = 0; index$sample$68 < state.noSamples; index$sample$68 += 1) {
																													if(true) {
																														for(int index$sample57$69 = 0; index$sample57$69 < state.noStates; index$sample57$69 += 1) {
																															int distributionTempVariable$var55$71 = index$sample57$69;
																															double cv$probabilitySample57Value70 = (cv$probabilitySample76Value20 * state.distribution$sample57[((index$sample$68 - 0) / 1)][index$sample57$69]);
																															{
																																int traceTempVariable$currentState$72_1 = distributionTempVariable$var55$71;
																																if((index$sample$68 == sample$var196)) {
																																	if((0 == timeStep$var226)) {
																																		{
																																			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																				for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																					if((var119 == server)) {
																																						if((var129 == traceTempVariable$currentState$72_1)) {
																																							{
																																								{
																																									{
																																										double var241 = state.current_metric_mean[server][traceTempVariable$currentState$72_1];
																																										if(((Math.log(cv$probabilitySample57Value70) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value70) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value70) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value70) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value70) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)));
																																										}
																																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value70);
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
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
																												int traceTempVariable$currentState$75_1 = distributionTempVariable$var74$21;
																												if((sample$var45 == sample$var196)) {
																													if((timeStep$var66 == timeStep$var226)) {
																														{
																															for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																	if((var119 == server)) {
																																		if((var129 == traceTempVariable$currentState$75_1)) {
																																			{
																																				{
																																					{
																																						double var241 = state.current_metric_mean[server][traceTempVariable$currentState$75_1];
																																						if(((Math.log(cv$probabilitySample76Value20) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value20) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value20) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value20) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value20);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																											for(int index$sample$76 = 0; index$sample$76 < state.noSamples; index$sample$76 += 1) {
																												for(int index$timeStep$77 = 1; index$timeStep$77 < state.length$metric[index$sample$76][0]; index$timeStep$77 += 1) {
																													if(!((index$timeStep$77 == timeStep$var66) && (index$sample$76 == sample$var45))) {
																														for(int index$sample76$78 = 0; index$sample76$78 < state.noStates; index$sample76$78 += 1) {
																															int distributionTempVariable$var74$80 = index$sample76$78;
																															double cv$probabilitySample76Value79 = (cv$probabilitySample76Value20 * state.distribution$sample76[((index$sample$76 - 0) / 1)][((index$timeStep$77 - 1) / 1)][index$sample76$78]);
																															{
																																int traceTempVariable$currentState$81_1 = distributionTempVariable$var74$80;
																																if((index$sample$76 == sample$var196)) {
																																	if((index$timeStep$77 == timeStep$var226)) {
																																		{
																																			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																				for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																					if((var119 == server)) {
																																						if((var129 == traceTempVariable$currentState$81_1)) {
																																							{
																																								{
																																									{
																																										double var241 = state.current_metric_mean[server][traceTempVariable$currentState$81_1];
																																										if(((Math.log(cv$probabilitySample76Value79) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value79) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value79) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value79) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value79) + ((0.0 < traceTempVariable$var243$24_1)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(traceTempVariable$var243$24_1))) - (0.5 * Math.log(traceTempVariable$var243$24_1))):Double.NEGATIVE_INFINITY)));
																																										}
																																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value79);
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
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
							double var157 = cv$originalValue;
							{
								{
									{
										double[] var147 = state.current_metric_var[var146];
										var147[var156] = var157;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample190(int var173, int var183) {
		if(true) {
			state.constrainedFlag$sample190[((var173 - 0) / 1)][((var183 - 0) / 1)] = false;
			double cv$sum = 0.0;
			double cv$count = 0.0;
			{
				{
					{
						for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
							for(int server = 0; server < state.noServers; server += 1) {
								for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
									if(state.fixedFlag$sample57) {
										{
											for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
												if((sample$var45 == sample$var196)) {
													if((0 == timeStep$var226)) {
														{
															if((var173 == server)) {
																if((var183 == state.st[sample$var196][timeStep$var226])) {
																	{
																		{
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				state.constrainedFlag$sample190[((var173 - 0) / 1)][((var183 - 0) / 1)] = true;
																				{
																					{
																						{
																							{
																								{
																									cv$count = (cv$count + 1.0);
																									if(state.metric_valid_g[sample$var196][server][timeStep$var226])
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
										for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
											if(true) {
												for(int index$sample57$6 = 0; index$sample57$6 < state.noStates; index$sample57$6 += 1) {
													int distributionTempVariable$var55$8 = index$sample57$6;
													double cv$probabilitySample57Value7 = (1.0 * state.distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$6]);
													{
														int traceTempVariable$currentState$9_1 = distributionTempVariable$var55$8;
														if((sample$var45 == sample$var196)) {
															if((0 == timeStep$var226)) {
																{
																	if((var173 == server)) {
																		if((var183 == traceTempVariable$currentState$9_1)) {
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						state.constrainedFlag$sample190[((var173 - 0) / 1)][((var183 - 0) / 1)] = true;
																						{
																							{
																								{
																									{
																										{
																											cv$count = (cv$count + cv$probabilitySample57Value7);
																											if(state.metric_valid_g[sample$var196][server][timeStep$var226])
																												cv$sum = (cv$sum + cv$probabilitySample57Value7);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
							for(int server = 0; server < state.noServers; server += 1) {
								for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
									if(state.fixedFlag$sample76) {
										{
											for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
												for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
													if((sample$var45 == sample$var196)) {
														if((timeStep$var66 == timeStep$var226)) {
															{
																if((var173 == server)) {
																	if((var183 == state.st[sample$var196][timeStep$var226])) {
																		{
																			{
																				boolean cv$sampleConstrained = true;
																				if(cv$sampleConstrained) {
																					state.constrainedFlag$sample190[((var173 - 0) / 1)][((var183 - 0) / 1)] = true;
																					{
																						{
																							{
																								{
																									{
																										cv$count = (cv$count + 1.0);
																										if(state.metric_valid_g[sample$var196][server][timeStep$var226])
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
										}
									} else {
										for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
											for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
												if(true) {
													for(int index$sample76$18 = 0; index$sample76$18 < state.noStates; index$sample76$18 += 1) {
														int distributionTempVariable$var74$20 = index$sample76$18;
														double cv$probabilitySample76Value19 = (1.0 * state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$18]);
														{
															int traceTempVariable$currentState$21_1 = distributionTempVariable$var74$20;
															if((sample$var45 == sample$var196)) {
																if((timeStep$var66 == timeStep$var226)) {
																	{
																		if((var173 == server)) {
																			if((var183 == traceTempVariable$currentState$21_1)) {
																				{
																					{
																						boolean cv$sampleConstrained = true;
																						if(cv$sampleConstrained) {
																							state.constrainedFlag$sample190[((var173 - 0) / 1)][((var183 - 0) / 1)] = true;
																							{
																								{
																									{
																										{
																											{
																												cv$count = (cv$count + cv$probabilitySample76Value19);
																												if(state.metric_valid_g[sample$var196][server][timeStep$var226])
																													cv$sum = (cv$sum + cv$probabilitySample76Value19);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(state.constrainedFlag$sample190[((var173 - 0) / 1)][((var183 - 0) / 1)]) {
				double var184 = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
				{
					{
						{
							double[] var174 = state.current_metric_valid_bias[var173];
							var174[var183] = var184;
						}
					}
				}
			}
		}
	}

	private final void inferSample20() {
		if(true) {
			state.constrainedFlag$sample20 = false;
			double[] cv$targetLocal = state.initialStateDistribution;
			double[] cv$countLocal = scratch.cv$var20$countGlobal;
			int cv$arrayLength = state.noStates;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
								if(state.fixedFlag$sample57) {
									{
										{
											int index$sample$3 = sample$var45;
											boolean cv$sampleConstrained = (state.fixedFlag$sample57 || state.constrainedFlag$sample57[((sample$var45 - 0) / 1)]);
											if(cv$sampleConstrained) {
												state.constrainedFlag$sample20 = true;
												{
													{
														{
															{
																{
																	cv$countLocal[state.st[sample$var45][0]] = (cv$countLocal[state.st[sample$var45][0]] + 1.0);
																}
															}
														}
													}
												}
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
						for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
							if(!state.fixedFlag$sample57) {
								{
									{
										int index$sample$7 = sample$var45;
										{
											{
												double scopeVariable$reachedSourceProbability = 0.0;
												{
													scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
												double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
												for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
													cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample57[((sample$var45 - 0) / 1)][cv$loopIndex] * cv$distributionProbability));
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(state.constrainedFlag$sample20)
				Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, cv$countLocal, cv$targetLocal, state.noStates);
		}
	}

	private final void inferSample33(int var32) {
		if(true) {
			state.constrainedFlag$sample33[((var32 - 0) / 1)] = false;
			double[] cv$targetLocal = state.m[var32];
			double[] cv$countLocal = scratch.cv$var33$countGlobal;
			int cv$arrayLength = state.noStates;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
							for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
								if(state.fixedFlag$sample57) {
									{
										for(int index$sample$3_1 = 0; index$sample$3_1 < state.noSamples; index$sample$3_1 += 1) {
											if((index$sample$3_1 == sample$var45)) {
												if((0 == (timeStep$var66 - 1))) {
													{
														if((var32 == state.st[sample$var45][(timeStep$var66 - 1)])) {
															if(state.fixedFlag$sample76) {
																{
																	{
																		int index$timeStep$23 = timeStep$var66;
																		int index$sample$24 = sample$var45;
																		boolean cv$sampleConstrained = (state.fixedFlag$sample76 || state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)]);
																		if(cv$sampleConstrained) {
																			state.constrainedFlag$sample33[((var32 - 0) / 1)] = true;
																			{
																				{
																					{
																						{
																							{
																								cv$countLocal[state.st[sample$var45][timeStep$var66]] = (cv$countLocal[state.st[sample$var45][timeStep$var66]] + 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
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
											for(int index$sample57$5 = 0; index$sample57$5 < state.noStates; index$sample57$5 += 1) {
												int distributionTempVariable$var55$7 = index$sample57$5;
												double cv$probabilitySample57Value6 = (1.0 * state.distribution$sample57[((index$sample$4 - 0) / 1)][index$sample57$5]);
												{
													int traceTempVariable$var71$8_1 = distributionTempVariable$var55$7;
													if((index$sample$4 == sample$var45)) {
														if((0 == (timeStep$var66 - 1))) {
															{
																if((var32 == traceTempVariable$var71$8_1)) {
																	if(state.fixedFlag$sample76) {
																		{
																			{
																				int index$timeStep$26 = timeStep$var66;
																				int index$sample$27 = sample$var45;
																				boolean cv$sampleConstrained = (state.fixedFlag$sample76 || state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)]);
																				if(cv$sampleConstrained) {
																					state.constrainedFlag$sample33[((var32 - 0) / 1)] = true;
																					{
																						{
																							{
																								{
																									{
																										cv$countLocal[state.st[sample$var45][timeStep$var66]] = (cv$countLocal[state.st[sample$var45][timeStep$var66]] + cv$probabilitySample57Value6);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
							for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
								if(state.fixedFlag$sample76) {
									{
										for(int index$sample$13_1 = 0; index$sample$13_1 < state.noSamples; index$sample$13_1 += 1) {
											for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < state.length$metric[index$sample$13_1][0]; index$timeStep$13_2 += 1) {
												if((index$sample$13_1 == sample$var45)) {
													if((index$timeStep$13_2 == (timeStep$var66 - 1))) {
														{
															if((var32 == state.st[sample$var45][(timeStep$var66 - 1)])) {
																if(state.fixedFlag$sample76) {
																	{
																		{
																			int index$timeStep$29 = timeStep$var66;
																			int index$sample$30 = sample$var45;
																			boolean cv$sampleConstrained = (state.fixedFlag$sample76 || state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)]);
																			if(cv$sampleConstrained) {
																				state.constrainedFlag$sample33[((var32 - 0) / 1)] = true;
																				{
																					{
																						{
																							{
																								{
																									cv$countLocal[state.st[sample$var45][timeStep$var66]] = (cv$countLocal[state.st[sample$var45][timeStep$var66]] + 1.0);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
										for(int index$timeStep$15 = 1; index$timeStep$15 < state.length$metric[index$sample$14][0]; index$timeStep$15 += 1) {
											if(true) {
												for(int index$sample76$16 = 0; index$sample76$16 < state.noStates; index$sample76$16 += 1) {
													int distributionTempVariable$var74$18 = index$sample76$16;
													double cv$probabilitySample76Value17 = (1.0 * state.distribution$sample76[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample76$16]);
													{
														int traceTempVariable$var71$19_1 = distributionTempVariable$var74$18;
														if((index$sample$14 == sample$var45)) {
															if((index$timeStep$15 == (timeStep$var66 - 1))) {
																{
																	if((var32 == traceTempVariable$var71$19_1)) {
																		if(state.fixedFlag$sample76) {
																			{
																				{
																					int index$timeStep$32 = timeStep$var66;
																					int index$sample$33 = sample$var45;
																					boolean cv$sampleConstrained = (state.fixedFlag$sample76 || state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)]);
																					if(cv$sampleConstrained) {
																						state.constrainedFlag$sample33[((var32 - 0) / 1)] = true;
																						{
																							{
																								{
																									{
																										{
																											cv$countLocal[state.st[sample$var45][timeStep$var66]] = (cv$countLocal[state.st[sample$var45][timeStep$var66]] + cv$probabilitySample76Value17);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
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
					for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
						for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
							if(state.fixedFlag$sample57) {
								{
									for(int index$sample$40_1 = 0; index$sample$40_1 < state.noSamples; index$sample$40_1 += 1) {
										if((index$sample$40_1 == sample$var45)) {
											if((0 == (timeStep$var66 - 1))) {
												{
													if((var32 == state.st[sample$var45][(timeStep$var66 - 1)])) {
														if(!state.fixedFlag$sample76) {
															{
																{
																	int index$timeStep$60 = timeStep$var66;
																	int index$sample$61 = sample$var45;
																	{
																		{
																			double scopeVariable$reachedSourceProbability = 0.0;
																			{
																				scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																			}
																			double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																				cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																		}
																	}
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
										for(int index$sample57$42 = 0; index$sample57$42 < state.noStates; index$sample57$42 += 1) {
											int distributionTempVariable$var55$44 = index$sample57$42;
											double cv$probabilitySample57Value43 = (1.0 * state.distribution$sample57[((index$sample$41 - 0) / 1)][index$sample57$42]);
											{
												int traceTempVariable$var71$45_1 = distributionTempVariable$var55$44;
												if((index$sample$41 == sample$var45)) {
													if((0 == (timeStep$var66 - 1))) {
														{
															if((var32 == traceTempVariable$var71$45_1)) {
																if(!state.fixedFlag$sample76) {
																	{
																		{
																			int index$timeStep$63 = timeStep$var66;
																			int index$sample$64 = sample$var45;
																			{
																				{
																					double scopeVariable$reachedSourceProbability = 0.0;
																					{
																						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																					}
																					double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample57Value43);
																					for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																						cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
						for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
							if(state.fixedFlag$sample76) {
								{
									for(int index$sample$50_1 = 0; index$sample$50_1 < state.noSamples; index$sample$50_1 += 1) {
										for(int index$timeStep$50_2 = 1; index$timeStep$50_2 < state.length$metric[index$sample$50_1][0]; index$timeStep$50_2 += 1) {
											if((index$sample$50_1 == sample$var45)) {
												if((index$timeStep$50_2 == (timeStep$var66 - 1))) {
													{
														if((var32 == state.st[sample$var45][(timeStep$var66 - 1)])) {
															if(!state.fixedFlag$sample76) {
																{
																	{
																		int index$timeStep$66 = timeStep$var66;
																		int index$sample$67 = sample$var45;
																		{
																			{
																				double scopeVariable$reachedSourceProbability = 0.0;
																				{
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																				}
																				double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																				for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																					cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																			}
																		}
																	}
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
									for(int index$timeStep$52 = 1; index$timeStep$52 < state.length$metric[index$sample$51][0]; index$timeStep$52 += 1) {
										if(true) {
											for(int index$sample76$53 = 0; index$sample76$53 < state.noStates; index$sample76$53 += 1) {
												int distributionTempVariable$var74$55 = index$sample76$53;
												double cv$probabilitySample76Value54 = (1.0 * state.distribution$sample76[((index$sample$51 - 0) / 1)][((index$timeStep$52 - 1) / 1)][index$sample76$53]);
												{
													int traceTempVariable$var71$56_1 = distributionTempVariable$var74$55;
													if((index$sample$51 == sample$var45)) {
														if((index$timeStep$52 == (timeStep$var66 - 1))) {
															{
																if((var32 == traceTempVariable$var71$56_1)) {
																	if(!state.fixedFlag$sample76) {
																		{
																			{
																				int index$timeStep$69 = timeStep$var66;
																				int index$sample$70 = sample$var45;
																				{
																					{
																						double scopeVariable$reachedSourceProbability = 0.0;
																						{
																							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																						}
																						double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample76Value54);
																						for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(state.constrainedFlag$sample33[((var32 - 0) / 1)])
				Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, cv$countLocal, cv$targetLocal, state.noStates);
		}
	}

	private final void inferSample57(int sample$var45) {
		int index$sample$1 = sample$var45;
		if(true) {
			state.constrainedFlag$sample57[((sample$var45 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, state.noStates);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var55$stateProbabilityGlobal;
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
								int traceTempVariable$var71$2_1 = cv$currentValue;
								for(int index$sample$2_2 = 0; index$sample$2_2 < state.noSamples; index$sample$2_2 += 1) {
									if((sample$var45 == index$sample$2_2)) {
										for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$2_2][0]; timeStep$var66 += 1) {
											if((0 == (timeStep$var66 - 1))) {
												if(state.fixedFlag$sample76) {
													{
														{
															int index$timeStep$4 = timeStep$var66;
															int index$sample$5 = index$sample$2_2;
															boolean cv$sampleConstrained = (state.fixedFlag$sample76 || state.constrainedFlag$sample76[((index$sample$2_2 - 0) / 1)][((timeStep$var66 - 1) / 1)]);
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample57[((sample$var45 - 0) / 1)] = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		for(int var32 = 0; var32 < state.noStates; var32 += 1) {
																			if((var32 == traceTempVariable$var71$2_1)) {
																				{
																					{
																						{
																							double[] var72 = state.m[traceTempVariable$var71$2_1];
																							if(((Math.log(1.0) + ((((((0.0 <= state.st[index$sample$2_2][timeStep$var66]) && (state.st[index$sample$2_2][timeStep$var66] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[state.st[index$sample$2_2][timeStep$var66]])) && (var72[state.st[index$sample$2_2][timeStep$var66]] <= 1.0))?Math.log(var72[state.st[index$sample$2_2][timeStep$var66]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.st[index$sample$2_2][timeStep$var66]) && (state.st[index$sample$2_2][timeStep$var66] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[state.st[index$sample$2_2][timeStep$var66]])) && (var72[state.st[index$sample$2_2][timeStep$var66]] <= 1.0))?Math.log(var72[state.st[index$sample$2_2][timeStep$var66]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.st[index$sample$2_2][timeStep$var66]) && (state.st[index$sample$2_2][timeStep$var66] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[state.st[index$sample$2_2][timeStep$var66]])) && (var72[state.st[index$sample$2_2][timeStep$var66]] <= 1.0))?Math.log(var72[state.st[index$sample$2_2][timeStep$var66]]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.st[index$sample$2_2][timeStep$var66]) && (state.st[index$sample$2_2][timeStep$var66] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[state.st[index$sample$2_2][timeStep$var66]])) && (var72[state.st[index$sample$2_2][timeStep$var66]] <= 1.0))?Math.log(var72[state.st[index$sample$2_2][timeStep$var66]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.st[index$sample$2_2][timeStep$var66]) && (state.st[index$sample$2_2][timeStep$var66] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[state.st[index$sample$2_2][timeStep$var66]])) && (var72[state.st[index$sample$2_2][timeStep$var66]] <= 1.0))?Math.log(var72[state.st[index$sample$2_2][timeStep$var66]]):Double.NEGATIVE_INFINITY)));
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
								for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
									if((sample$var45 == sample$var196)) {
										for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
											if((0 == timeStep$var226)) {
												for(int server = 0; server < state.noServers; server += 1) {
													boolean cv$sampleConstrained = true;
													if(cv$sampleConstrained) {
														state.constrainedFlag$sample57[((sample$var45 - 0) / 1)] = true;
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															{
																for(int var173 = 0; var173 < state.noServers; var173 += 1) {
																	for(int var183 = 0; var183 < state.noStates; var183 += 1) {
																		if((var173 == server)) {
																			if((var183 == traceTempVariable$currentState$8_1)) {
																				{
																					{
																						{
																							double var230 = state.current_metric_valid_bias[server][traceTempVariable$currentState$8_1];
																							if(((Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)));
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
					{
						{
							boolean[][][] guard$sample57gaussian255 = scratch.guard$sample57gaussian255$global;
							{
								for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
									if((sample$var45 == sample$var196)) {
										for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
											if((0 == timeStep$var226)) {
												for(int server = 0; server < state.noServers; server += 1) {
													if(state.metric_valid_g[sample$var196][server][timeStep$var226])
														guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
												}
											}
										}
									}
								}
							}
							{
								for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
									if((sample$var45 == sample$var196)) {
										for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
											if((0 == timeStep$var226)) {
												for(int server = 0; server < state.noServers; server += 1) {
													if(state.metric_valid_g[sample$var196][server][timeStep$var226])
														guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
												}
											}
										}
									}
								}
							}
							{
								int traceTempVariable$currentState$14_1 = cv$currentValue;
								for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
									if((sample$var45 == sample$var196)) {
										for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
											if((0 == timeStep$var226)) {
												for(int server = 0; server < state.noServers; server += 1) {
													if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
														if(!guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
															guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample57[((sample$var45 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																					for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																						if((var119 == server)) {
																							if((var129 == traceTempVariable$currentState$14_1)) {
																								{
																									int traceTempVariable$currentState$19_1 = cv$currentValue;
																									if((index$sample$1 == sample$var196)) {
																										if((0 == timeStep$var226)) {
																											{
																												for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																													for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																														if((var146 == server)) {
																															if((var156 == traceTempVariable$currentState$19_1)) {
																																{
																																	{
																																		{
																																			double var241 = state.current_metric_mean[server][traceTempVariable$currentState$19_1];
																																			double var243 = state.current_metric_var[server][traceTempVariable$currentState$19_1];
																																			if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																								for(int index$sample$20 = 0; index$sample$20 < state.noSamples; index$sample$20 += 1) {
																									if(!(index$sample$20 == index$sample$1)) {
																										for(int index$sample57$21 = 0; index$sample57$21 < state.noStates; index$sample57$21 += 1) {
																											int distributionTempVariable$var55$23 = index$sample57$21;
																											double cv$probabilitySample57Value22 = (1.0 * state.distribution$sample57[((index$sample$20 - 0) / 1)][index$sample57$21]);
																											{
																												int traceTempVariable$currentState$24_1 = distributionTempVariable$var55$23;
																												if((index$sample$20 == sample$var196)) {
																													if((0 == timeStep$var226)) {
																														{
																															for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																	if((var146 == server)) {
																																		if((var156 == traceTempVariable$currentState$24_1)) {
																																			{
																																				{
																																					{
																																						double var241 = state.current_metric_mean[server][traceTempVariable$currentState$24_1];
																																						double var243 = state.current_metric_var[server][traceTempVariable$currentState$24_1];
																																						if(((Math.log(cv$probabilitySample57Value22) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value22) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value22) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value22) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value22) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value22);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
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
																				for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																					for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																						if((var119 == server)) {
																							if((var129 == traceTempVariable$currentState$14_1)) {
																								if(state.fixedFlag$sample76) {
																									{
																										for(int index$sample$28_1 = 0; index$sample$28_1 < state.noSamples; index$sample$28_1 += 1) {
																											for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$28_1][0]; timeStep$var66 += 1) {
																												if((index$sample$28_1 == sample$var196)) {
																													if((timeStep$var66 == timeStep$var226)) {
																														{
																															for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																	if((var146 == server)) {
																																		if((var156 == traceTempVariable$currentState$14_1)) {
																																			{
																																				{
																																					{
																																						double var241 = state.current_metric_mean[server][traceTempVariable$currentState$14_1];
																																						double var243 = state.current_metric_var[server][traceTempVariable$currentState$14_1];
																																						if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																									for(int index$sample$29 = 0; index$sample$29 < state.noSamples; index$sample$29 += 1) {
																										for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$29][0]; timeStep$var66 += 1) {
																											if(true) {
																												for(int index$sample76$31 = 0; index$sample76$31 < state.noStates; index$sample76$31 += 1) {
																													int distributionTempVariable$var74$33 = index$sample76$31;
																													double cv$probabilitySample76Value32 = (1.0 * state.distribution$sample76[((index$sample$29 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$31]);
																													{
																														int traceTempVariable$currentState$34_1 = distributionTempVariable$var74$33;
																														if((index$sample$29 == sample$var196)) {
																															if((timeStep$var66 == timeStep$var226)) {
																																{
																																	for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == traceTempVariable$currentState$34_1)) {
																																					{
																																						{
																																							{
																																								double var241 = state.current_metric_mean[server][traceTempVariable$currentState$34_1];
																																								double var243 = state.current_metric_var[server][traceTempVariable$currentState$34_1];
																																								if(((Math.log(cv$probabilitySample76Value32) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value32) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value32) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value32) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value32) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value32);
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
							{
								int traceTempVariable$currentState$15_1 = cv$currentValue;
								for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
									if((sample$var45 == sample$var196)) {
										for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
											if((0 == timeStep$var226)) {
												for(int server = 0; server < state.noServers; server += 1) {
													if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
														if(!guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
															guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample57[((sample$var45 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				int traceTempVariable$currentState$37_1 = cv$currentValue;
																				if((index$sample$1 == sample$var196)) {
																					if((0 == timeStep$var226)) {
																						{
																							for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																								for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																									if((var119 == server)) {
																										if((var129 == traceTempVariable$currentState$37_1)) {
																											{
																												for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																													for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																														if((var146 == server)) {
																															if((var156 == traceTempVariable$currentState$37_1)) {
																																{
																																	{
																																		{
																																			double var241 = state.current_metric_mean[server][traceTempVariable$currentState$37_1];
																																			double var243 = state.current_metric_var[server][traceTempVariable$currentState$37_1];
																																			if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																				}
																			}
																			for(int index$sample$38 = 0; index$sample$38 < state.noSamples; index$sample$38 += 1) {
																				if(!(index$sample$38 == index$sample$1)) {
																					for(int index$sample57$39 = 0; index$sample57$39 < state.noStates; index$sample57$39 += 1) {
																						int distributionTempVariable$var55$41 = index$sample57$39;
																						double cv$probabilitySample57Value40 = (1.0 * state.distribution$sample57[((index$sample$38 - 0) / 1)][index$sample57$39]);
																						{
																							int traceTempVariable$currentState$42_1 = distributionTempVariable$var55$41;
																							if((index$sample$38 == sample$var196)) {
																								if((0 == timeStep$var226)) {
																									{
																										for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																											for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																												if((var119 == server)) {
																													if((var129 == traceTempVariable$currentState$42_1)) {
																														{
																															for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																	if((var146 == server)) {
																																		if((var156 == traceTempVariable$currentState$42_1)) {
																																			{
																																				{
																																					{
																																						double var241 = state.current_metric_mean[server][traceTempVariable$currentState$42_1];
																																						double var243 = state.current_metric_var[server][traceTempVariable$currentState$42_1];
																																						if(((Math.log(cv$probabilitySample57Value40) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value40) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value40) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value40) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value40) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value40);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			if(state.fixedFlag$sample76) {
																				{
																					for(int index$sample$47_1 = 0; index$sample$47_1 < state.noSamples; index$sample$47_1 += 1) {
																						for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$47_1][0]; timeStep$var66 += 1) {
																							if((index$sample$47_1 == sample$var196)) {
																								if((timeStep$var66 == timeStep$var226)) {
																									{
																										for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																											for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																												if((var119 == server)) {
																													if((var129 == traceTempVariable$currentState$15_1)) {
																														{
																															for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																	if((var146 == server)) {
																																		if((var156 == traceTempVariable$currentState$15_1)) {
																																			{
																																				{
																																					{
																																						double var241 = state.current_metric_mean[server][traceTempVariable$currentState$15_1];
																																						double var243 = state.current_metric_var[server][traceTempVariable$currentState$15_1];
																																						if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																							}
																						}
																					}
																				}
																			} else {
																				for(int index$sample$48 = 0; index$sample$48 < state.noSamples; index$sample$48 += 1) {
																					for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$48][0]; timeStep$var66 += 1) {
																						if(true) {
																							for(int index$sample76$50 = 0; index$sample76$50 < state.noStates; index$sample76$50 += 1) {
																								int distributionTempVariable$var74$52 = index$sample76$50;
																								double cv$probabilitySample76Value51 = (1.0 * state.distribution$sample76[((index$sample$48 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$50]);
																								{
																									int traceTempVariable$currentState$53_1 = distributionTempVariable$var74$52;
																									if((index$sample$48 == sample$var196)) {
																										if((timeStep$var66 == timeStep$var226)) {
																											{
																												for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																													for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																														if((var119 == server)) {
																															if((var129 == traceTempVariable$currentState$53_1)) {
																																{
																																	for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == traceTempVariable$currentState$53_1)) {
																																					{
																																						{
																																							{
																																								double var241 = state.current_metric_mean[server][traceTempVariable$currentState$53_1];
																																								double var243 = state.current_metric_var[server][traceTempVariable$currentState$53_1];
																																								if(((Math.log(cv$probabilitySample76Value51) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value51) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value51) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value51) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value51) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value51);
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
							int traceTempVariable$var71$66_1 = cv$currentValue;
							for(int index$sample$66_2 = 0; index$sample$66_2 < state.noSamples; index$sample$66_2 += 1) {
								if((sample$var45 == index$sample$66_2)) {
									for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$66_2][0]; timeStep$var66 += 1) {
										if((0 == (timeStep$var66 - 1))) {
											if(!state.fixedFlag$sample76) {
												{
													{
														int index$timeStep$68 = timeStep$var66;
														int index$sample$69 = index$sample$66_2;
														double[] cv$accumulatedConsumerDistributions = scratch.cv$distributionAccumulator$var73;
														for(int cv$i = 0; cv$i < state.noStates; cv$i += 1)
															cv$accumulatedConsumerDistributions[cv$i] = 0.0;
														double cv$reachedDistributionProbability = 0.0;
														{
															for(int var32 = 0; var32 < state.noStates; var32 += 1) {
																if((var32 == traceTempVariable$var71$66_1)) {
																	{
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		double[] var72 = state.m[traceTempVariable$var71$66_1];
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																		cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
																		DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, var72, state.noStates);
																	}
																}
															}
														}
														double[] cv$sampleDistribution = state.distribution$sample76[((index$sample$66_2 - 0) / 1)][((timeStep$var66 - 1) / 1)];
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
			if(state.constrainedFlag$sample57[((sample$var45 - 0) / 1)]) {
				double[] cv$localProbability = state.distribution$sample57[((sample$var45 - 0) / 1)];
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

	private final void inferSample76(int sample$var45, int timeStep$var66) {
		int index$timeStep$1 = timeStep$var66;
		int index$sample$2 = sample$var45;
		if(true) {
			state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = false;
			int cv$numStates = 0;
			if(state.fixedFlag$sample57) {
				{
					for(int index$sample$3_1 = 0; index$sample$3_1 < state.noSamples; index$sample$3_1 += 1) {
						if((index$sample$3_1 == sample$var45)) {
							if((0 == (timeStep$var66 - 1))) {
								{
									for(int var32 = 0; var32 < state.noStates; var32 += 1) {
										if((var32 == state.st[sample$var45][(timeStep$var66 - 1)]))
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
						for(int index$sample57$5 = 0; index$sample57$5 < state.noStates; index$sample57$5 += 1) {
							int distributionTempVariable$var55$7 = index$sample57$5;
							double cv$probabilitySample57Value6 = (1.0 * state.distribution$sample57[((index$sample$4 - 0) / 1)][index$sample57$5]);
							{
								int traceTempVariable$var71$8_1 = distributionTempVariable$var55$7;
								if((index$sample$4 == sample$var45)) {
									if((0 == (timeStep$var66 - 1))) {
										{
											for(int var32 = 0; var32 < state.noStates; var32 += 1) {
												if((var32 == traceTempVariable$var71$8_1))
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
				if((index$sample$2 == sample$var45)) {
					if((index$timeStep$1 == (timeStep$var66 - 1))) {
						{
							for(int var32 = 0; var32 < state.noStates; var32 += 1) {
								if((var32 == state.st[sample$var45][(timeStep$var66 - 1)]))
									cv$numStates = Math.max(cv$numStates, state.noStates);
							}
						}
					}
				}
			}
			for(int index$sample$12 = 0; index$sample$12 < state.noSamples; index$sample$12 += 1) {
				for(int index$timeStep$13 = 1; index$timeStep$13 < state.length$metric[index$sample$12][0]; index$timeStep$13 += 1) {
					if(!((index$timeStep$13 == index$timeStep$1) && (index$sample$12 == index$sample$2))) {
						for(int index$sample76$14 = 0; index$sample76$14 < state.noStates; index$sample76$14 += 1) {
							int distributionTempVariable$var74$16 = index$sample76$14;
							double cv$probabilitySample76Value15 = (1.0 * state.distribution$sample76[((index$sample$12 - 0) / 1)][((index$timeStep$13 - 1) / 1)][index$sample76$14]);
							{
								int traceTempVariable$var71$17_1 = distributionTempVariable$var74$16;
								if((index$sample$12 == sample$var45)) {
									if((index$timeStep$13 == (timeStep$var66 - 1))) {
										{
											for(int var32 = 0; var32 < state.noStates; var32 += 1) {
												if((var32 == traceTempVariable$var71$17_1))
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
			double[] cv$stateProbabilityLocal = scratch.cv$var74$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				if(state.fixedFlag$sample57) {
					{
						for(int index$sample$20_1 = 0; index$sample$20_1 < state.noSamples; index$sample$20_1 += 1) {
							if((index$sample$20_1 == sample$var45)) {
								if((0 == (timeStep$var66 - 1))) {
									{
										for(int var32 = 0; var32 < state.noStates; var32 += 1) {
											if((var32 == state.st[sample$var45][(timeStep$var66 - 1)])) {
												cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
												double[] var72 = state.m[state.st[sample$var45][(timeStep$var66 - 1)]];
												double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[cv$currentValue])) && (var72[cv$currentValue] <= 1.0))?Math.log(var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
												{
													{
														{
															int traceTempVariable$var71$37_1 = cv$currentValue;
														}
													}
												}
												{
													{
														{
															int traceTempVariable$currentState$41_1 = cv$currentValue;
															for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																if((sample$var45 == sample$var196)) {
																	for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																		if((timeStep$var66 == timeStep$var226)) {
																			for(int server = 0; server < state.noServers; server += 1) {
																				boolean cv$sampleConstrained = true;
																				if(cv$sampleConstrained) {
																					state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							for(int var173 = 0; var173 < state.noServers; var173 += 1) {
																								for(int var183 = 0; var183 < state.noStates; var183 += 1) {
																									if((var173 == server)) {
																										if((var183 == traceTempVariable$currentState$41_1)) {
																											{
																												{
																													{
																														double var230 = state.current_metric_valid_bias[server][traceTempVariable$currentState$41_1];
																														if(((Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)));
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
												{
													{
														boolean[][][] guard$sample76gaussian255 = scratch.guard$sample76gaussian255$global;
														{
															for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																if((sample$var45 == sample$var196)) {
																	for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																		if((timeStep$var66 == timeStep$var226)) {
																			for(int server = 0; server < state.noServers; server += 1) {
																				if(state.metric_valid_g[sample$var196][server][timeStep$var226])
																					guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																			}
																		}
																	}
																}
															}
														}
														{
															for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																if((sample$var45 == sample$var196)) {
																	for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																		if((timeStep$var66 == timeStep$var226)) {
																			for(int server = 0; server < state.noServers; server += 1) {
																				if(state.metric_valid_g[sample$var196][server][timeStep$var226])
																					guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																			}
																		}
																	}
																}
															}
														}
														{
															int traceTempVariable$currentState$65_1 = cv$currentValue;
															for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																if((sample$var45 == sample$var196)) {
																	for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																		if((timeStep$var66 == timeStep$var226)) {
																			for(int server = 0; server < state.noServers; server += 1) {
																				if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
																					if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																						guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																						{
																							{
																								boolean cv$sampleConstrained = true;
																								if(cv$sampleConstrained) {
																									state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																									double cv$consumerDistributionProbabilityAccumulator = 1.0;
																									{
																										{
																											for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																												for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																													if((var119 == server)) {
																														if((var129 == traceTempVariable$currentState$65_1)) {
																															{
																																for(int index$sample$82_1 = 0; index$sample$82_1 < state.noSamples; index$sample$82_1 += 1) {
																																	if((index$sample$82_1 == sample$var196)) {
																																		if((0 == timeStep$var226)) {
																																			{
																																				for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																					for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																						if((var146 == server)) {
																																							if((var156 == traceTempVariable$currentState$65_1)) {
																																								{
																																									{
																																										{
																																											double var241 = state.current_metric_mean[server][traceTempVariable$currentState$65_1];
																																											double var243 = state.current_metric_var[server][traceTempVariable$currentState$65_1];
																																											if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																											else {
																																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																												else
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																												}
																											}
																										}
																										{
																											for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																												for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																													if((var119 == server)) {
																														if((var129 == traceTempVariable$currentState$65_1)) {
																															{
																																int traceTempVariable$currentState$85_1 = cv$currentValue;
																																if((index$sample$2 == sample$var196)) {
																																	if((index$timeStep$1 == timeStep$var226)) {
																																		{
																																			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																				for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																					if((var146 == server)) {
																																						if((var156 == traceTempVariable$currentState$85_1)) {
																																							{
																																								{
																																									{
																																										double var241 = state.current_metric_mean[server][traceTempVariable$currentState$85_1];
																																										double var243 = state.current_metric_var[server][traceTempVariable$currentState$85_1];
																																										if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																															for(int index$sample$86 = 0; index$sample$86 < state.noSamples; index$sample$86 += 1) {
																																for(int index$timeStep$87 = 1; index$timeStep$87 < state.length$metric[index$sample$86][0]; index$timeStep$87 += 1) {
																																	if(!((index$timeStep$87 == index$timeStep$1) && (index$sample$86 == index$sample$2))) {
																																		for(int index$sample76$88 = 0; index$sample76$88 < state.noStates; index$sample76$88 += 1) {
																																			int distributionTempVariable$var74$90 = index$sample76$88;
																																			double cv$probabilitySample76Value89 = (1.0 * state.distribution$sample76[((index$sample$86 - 0) / 1)][((index$timeStep$87 - 1) / 1)][index$sample76$88]);
																																			{
																																				int traceTempVariable$currentState$91_1 = distributionTempVariable$var74$90;
																																				if((index$sample$86 == sample$var196)) {
																																					if((index$timeStep$87 == timeStep$var226)) {
																																						{
																																							for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																								for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																									if((var146 == server)) {
																																										if((var156 == traceTempVariable$currentState$91_1)) {
																																											{
																																												{
																																													{
																																														double var241 = state.current_metric_mean[server][traceTempVariable$currentState$91_1];
																																														double var243 = state.current_metric_var[server][traceTempVariable$currentState$91_1];
																																														if(((Math.log(cv$probabilitySample76Value89) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value89) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																														else {
																																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value89) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																															else
																																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value89) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value89) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																														}
																																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value89);
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
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
														{
															int traceTempVariable$currentState$69_1 = cv$currentValue;
															for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																if((sample$var45 == sample$var196)) {
																	for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																		if((timeStep$var66 == timeStep$var226)) {
																			for(int server = 0; server < state.noServers; server += 1) {
																				if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
																					if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																						guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																						{
																							{
																								boolean cv$sampleConstrained = true;
																								if(cv$sampleConstrained) {
																									state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																									double cv$consumerDistributionProbabilityAccumulator = 1.0;
																									{
																										{
																											for(int index$sample$153_1 = 0; index$sample$153_1 < state.noSamples; index$sample$153_1 += 1) {
																												if((index$sample$153_1 == sample$var196)) {
																													if((0 == timeStep$var226)) {
																														{
																															for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																	if((var119 == server)) {
																																		if((var129 == traceTempVariable$currentState$69_1)) {
																																			{
																																				for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																					for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																						if((var146 == server)) {
																																							if((var156 == traceTempVariable$currentState$69_1)) {
																																								{
																																									{
																																										{
																																											double var241 = state.current_metric_mean[server][traceTempVariable$currentState$69_1];
																																											double var243 = state.current_metric_var[server][traceTempVariable$currentState$69_1];
																																											if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																											else {
																																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																												else
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																												}
																											}
																										}
																										{
																											int traceTempVariable$currentState$156_1 = cv$currentValue;
																											if((index$sample$2 == sample$var196)) {
																												if((index$timeStep$1 == timeStep$var226)) {
																													{
																														for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																															for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																if((var119 == server)) {
																																	if((var129 == traceTempVariable$currentState$156_1)) {
																																		{
																																			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																				for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																					if((var146 == server)) {
																																						if((var156 == traceTempVariable$currentState$156_1)) {
																																							{
																																								{
																																									{
																																										double var241 = state.current_metric_mean[server][traceTempVariable$currentState$156_1];
																																										double var243 = state.current_metric_var[server][traceTempVariable$currentState$156_1];
																																										if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																											}
																										}
																										for(int index$sample$157 = 0; index$sample$157 < state.noSamples; index$sample$157 += 1) {
																											for(int index$timeStep$158 = 1; index$timeStep$158 < state.length$metric[index$sample$157][0]; index$timeStep$158 += 1) {
																												if(!((index$timeStep$158 == index$timeStep$1) && (index$sample$157 == index$sample$2))) {
																													for(int index$sample76$159 = 0; index$sample76$159 < state.noStates; index$sample76$159 += 1) {
																														int distributionTempVariable$var74$161 = index$sample76$159;
																														double cv$probabilitySample76Value160 = (1.0 * state.distribution$sample76[((index$sample$157 - 0) / 1)][((index$timeStep$158 - 1) / 1)][index$sample76$159]);
																														{
																															int traceTempVariable$currentState$162_1 = distributionTempVariable$var74$161;
																															if((index$sample$157 == sample$var196)) {
																																if((index$timeStep$158 == timeStep$var226)) {
																																	{
																																		for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																			for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																				if((var119 == server)) {
																																					if((var129 == traceTempVariable$currentState$162_1)) {
																																						{
																																							for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																								for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																									if((var146 == server)) {
																																										if((var156 == traceTempVariable$currentState$162_1)) {
																																											{
																																												{
																																													{
																																														double var241 = state.current_metric_mean[server][traceTempVariable$currentState$162_1];
																																														double var243 = state.current_metric_var[server][traceTempVariable$currentState$162_1];
																																														if(((Math.log(cv$probabilitySample76Value160) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value160) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																														else {
																																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value160) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																															else
																																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value160) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value160) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																														}
																																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value160);
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
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
							for(int index$sample57$22 = 0; index$sample57$22 < state.noStates; index$sample57$22 += 1) {
								int distributionTempVariable$var55$24 = index$sample57$22;
								double cv$probabilitySample57Value23 = (1.0 * state.distribution$sample57[((index$sample$21 - 0) / 1)][index$sample57$22]);
								{
									int traceTempVariable$var71$25_1 = distributionTempVariable$var55$24;
									if((index$sample$21 == sample$var45)) {
										if((0 == (timeStep$var66 - 1))) {
											{
												for(int var32 = 0; var32 < state.noStates; var32 += 1) {
													if((var32 == traceTempVariable$var71$25_1)) {
														cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample57Value23);
														double[] var72 = state.m[traceTempVariable$var71$25_1];
														double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample57Value23) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[cv$currentValue])) && (var72[cv$currentValue] <= 1.0))?Math.log(var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
														{
															{
																{
																	int traceTempVariable$var71$38_1 = cv$currentValue;
																}
															}
														}
														{
															{
																{
																	int traceTempVariable$currentState$42_1 = cv$currentValue;
																	for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																		if((sample$var45 == sample$var196)) {
																			for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int server = 0; server < state.noServers; server += 1) {
																						boolean cv$sampleConstrained = true;
																						if(cv$sampleConstrained) {
																							state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								{
																									for(int var173 = 0; var173 < state.noServers; var173 += 1) {
																										for(int var183 = 0; var183 < state.noStates; var183 += 1) {
																											if((var173 == server)) {
																												if((var183 == traceTempVariable$currentState$42_1)) {
																													{
																														{
																															{
																																double var230 = state.current_metric_valid_bias[server][traceTempVariable$currentState$42_1];
																																if(((Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)));
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
														{
															{
																boolean[][][] guard$sample76gaussian255 = scratch.guard$sample76gaussian255$global;
																{
																	for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																		if((sample$var45 == sample$var196)) {
																			for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int server = 0; server < state.noServers; server += 1) {
																						if(state.metric_valid_g[sample$var196][server][timeStep$var226])
																							guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																					}
																				}
																			}
																		}
																	}
																}
																{
																	for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																		if((sample$var45 == sample$var196)) {
																			for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int server = 0; server < state.noServers; server += 1) {
																						if(state.metric_valid_g[sample$var196][server][timeStep$var226])
																							guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																					}
																				}
																			}
																		}
																	}
																}
																{
																	int traceTempVariable$currentState$66_1 = cv$currentValue;
																	for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																		if((sample$var45 == sample$var196)) {
																			for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int server = 0; server < state.noServers; server += 1) {
																						if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
																							if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																								guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																								{
																									{
																										boolean cv$sampleConstrained = true;
																										if(cv$sampleConstrained) {
																											state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																											double cv$consumerDistributionProbabilityAccumulator = 1.0;
																											{
																												{
																													for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																														for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																															if((var119 == server)) {
																																if((var129 == traceTempVariable$currentState$66_1)) {
																																	{
																																		int traceTempVariable$currentState$95_1 = distributionTempVariable$var55$24;
																																		if((index$sample$21 == sample$var196)) {
																																			if((0 == timeStep$var226)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$95_1)) {
																																									{
																																										{
																																											{
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$95_1];
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$95_1];
																																												if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																																	for(int index$sample$96 = 0; index$sample$96 < state.noSamples; index$sample$96 += 1) {
																																		if(!(index$sample$96 == index$sample$21)) {
																																			for(int index$sample57$97 = 0; index$sample57$97 < state.noStates; index$sample57$97 += 1) {
																																				int distributionTempVariable$var55$99 = index$sample57$97;
																																				double cv$probabilitySample57Value98 = (1.0 * state.distribution$sample57[((index$sample$96 - 0) / 1)][index$sample57$97]);
																																				{
																																					int traceTempVariable$currentState$100_1 = distributionTempVariable$var55$99;
																																					if((index$sample$96 == sample$var196)) {
																																						if((0 == timeStep$var226)) {
																																							{
																																								for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																									for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																										if((var146 == server)) {
																																											if((var156 == traceTempVariable$currentState$100_1)) {
																																												{
																																													{
																																														{
																																															double var241 = state.current_metric_mean[server][traceTempVariable$currentState$100_1];
																																															double var243 = state.current_metric_var[server][traceTempVariable$currentState$100_1];
																																															if(((Math.log(cv$probabilitySample57Value98) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value98) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																															else {
																																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value98) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																																else
																																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value98) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value98) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																															}
																																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value98);
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
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
																													for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																														for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																															if((var119 == server)) {
																																if((var129 == traceTempVariable$currentState$66_1)) {
																																	{
																																		int traceTempVariable$currentState$104_1 = cv$currentValue;
																																		if((index$sample$2 == sample$var196)) {
																																			if((index$timeStep$1 == timeStep$var226)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$104_1)) {
																																									{
																																										{
																																											{
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$104_1];
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$104_1];
																																												if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																																	for(int index$sample$105 = 0; index$sample$105 < state.noSamples; index$sample$105 += 1) {
																																		for(int index$timeStep$106 = 1; index$timeStep$106 < state.length$metric[index$sample$105][0]; index$timeStep$106 += 1) {
																																			if(!((index$timeStep$106 == index$timeStep$1) && (index$sample$105 == index$sample$2))) {
																																				for(int index$sample76$107 = 0; index$sample76$107 < state.noStates; index$sample76$107 += 1) {
																																					int distributionTempVariable$var74$109 = index$sample76$107;
																																					double cv$probabilitySample76Value108 = (1.0 * state.distribution$sample76[((index$sample$105 - 0) / 1)][((index$timeStep$106 - 1) / 1)][index$sample76$107]);
																																					{
																																						int traceTempVariable$currentState$110_1 = distributionTempVariable$var74$109;
																																						if((index$sample$105 == sample$var196)) {
																																							if((index$timeStep$106 == timeStep$var226)) {
																																								{
																																									for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																										for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																											if((var146 == server)) {
																																												if((var156 == traceTempVariable$currentState$110_1)) {
																																													{
																																														{
																																															{
																																																double var241 = state.current_metric_mean[server][traceTempVariable$currentState$110_1];
																																																double var243 = state.current_metric_var[server][traceTempVariable$currentState$110_1];
																																																if(((Math.log(cv$probabilitySample76Value108) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value108) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																																else {
																																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value108) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																																	else
																																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value108) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value108) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																																}
																																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value108);
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
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
																{
																	int traceTempVariable$currentState$70_1 = cv$currentValue;
																	for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																		if((sample$var45 == sample$var196)) {
																			for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int server = 0; server < state.noServers; server += 1) {
																						if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
																							if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																								guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																								{
																									{
																										boolean cv$sampleConstrained = true;
																										if(cv$sampleConstrained) {
																											state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																											double cv$consumerDistributionProbabilityAccumulator = 1.0;
																											{
																												{
																													int traceTempVariable$currentState$167_1 = distributionTempVariable$var55$24;
																													if((index$sample$21 == sample$var196)) {
																														if((0 == timeStep$var226)) {
																															{
																																for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																	for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																		if((var119 == server)) {
																																			if((var129 == traceTempVariable$currentState$167_1)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$167_1)) {
																																									{
																																										{
																																											{
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$167_1];
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$167_1];
																																												if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																													}
																												}
																												for(int index$sample$168 = 0; index$sample$168 < state.noSamples; index$sample$168 += 1) {
																													if(!(index$sample$168 == index$sample$21)) {
																														for(int index$sample57$169 = 0; index$sample57$169 < state.noStates; index$sample57$169 += 1) {
																															int distributionTempVariable$var55$171 = index$sample57$169;
																															double cv$probabilitySample57Value170 = (1.0 * state.distribution$sample57[((index$sample$168 - 0) / 1)][index$sample57$169]);
																															{
																																int traceTempVariable$currentState$172_1 = distributionTempVariable$var55$171;
																																if((index$sample$168 == sample$var196)) {
																																	if((0 == timeStep$var226)) {
																																		{
																																			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																				for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																					if((var119 == server)) {
																																						if((var129 == traceTempVariable$currentState$172_1)) {
																																							{
																																								for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																									for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																										if((var146 == server)) {
																																											if((var156 == traceTempVariable$currentState$172_1)) {
																																												{
																																													{
																																														{
																																															double var241 = state.current_metric_mean[server][traceTempVariable$currentState$172_1];
																																															double var243 = state.current_metric_var[server][traceTempVariable$currentState$172_1];
																																															if(((Math.log(cv$probabilitySample57Value170) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value170) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																															else {
																																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value170) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																																else
																																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value170) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value170) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																															}
																																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value170);
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
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
																													if((index$sample$2 == sample$var196)) {
																														if((index$timeStep$1 == timeStep$var226)) {
																															{
																																for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																	for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																		if((var119 == server)) {
																																			if((var129 == traceTempVariable$currentState$177_1)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$177_1)) {
																																									{
																																										{
																																											{
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$177_1];
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$177_1];
																																												if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																													}
																												}
																												for(int index$sample$178 = 0; index$sample$178 < state.noSamples; index$sample$178 += 1) {
																													for(int index$timeStep$179 = 1; index$timeStep$179 < state.length$metric[index$sample$178][0]; index$timeStep$179 += 1) {
																														if(!((index$timeStep$179 == index$timeStep$1) && (index$sample$178 == index$sample$2))) {
																															for(int index$sample76$180 = 0; index$sample76$180 < state.noStates; index$sample76$180 += 1) {
																																int distributionTempVariable$var74$182 = index$sample76$180;
																																double cv$probabilitySample76Value181 = (1.0 * state.distribution$sample76[((index$sample$178 - 0) / 1)][((index$timeStep$179 - 1) / 1)][index$sample76$180]);
																																{
																																	int traceTempVariable$currentState$183_1 = distributionTempVariable$var74$182;
																																	if((index$sample$178 == sample$var196)) {
																																		if((index$timeStep$179 == timeStep$var226)) {
																																			{
																																				for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																					for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																						if((var119 == server)) {
																																							if((var129 == traceTempVariable$currentState$183_1)) {
																																								{
																																									for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																										for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																											if((var146 == server)) {
																																												if((var156 == traceTempVariable$currentState$183_1)) {
																																													{
																																														{
																																															{
																																																double var241 = state.current_metric_mean[server][traceTempVariable$currentState$183_1];
																																																double var243 = state.current_metric_var[server][traceTempVariable$currentState$183_1];
																																																if(((Math.log(cv$probabilitySample76Value181) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value181) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																																else {
																																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value181) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																																	else
																																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value181) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value181) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																																}
																																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value181);
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
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
					int traceTempVariable$var71$28_1 = cv$currentValue;
					if((index$sample$2 == sample$var45)) {
						if((index$timeStep$1 == (timeStep$var66 - 1))) {
							{
								for(int var32 = 0; var32 < state.noStates; var32 += 1) {
									if((var32 == traceTempVariable$var71$28_1)) {
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
										double[] var72 = state.m[traceTempVariable$var71$28_1];
										double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[cv$currentValue])) && (var72[cv$currentValue] <= 1.0))?Math.log(var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
										{
											{
												{
													int traceTempVariable$var71$39_1 = cv$currentValue;
												}
											}
										}
										{
											{
												{
													int traceTempVariable$currentState$43_1 = cv$currentValue;
													for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
														if((sample$var45 == sample$var196)) {
															for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																if((timeStep$var66 == timeStep$var226)) {
																	for(int server = 0; server < state.noServers; server += 1) {
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					for(int var173 = 0; var173 < state.noServers; var173 += 1) {
																						for(int var183 = 0; var183 < state.noStates; var183 += 1) {
																							if((var173 == server)) {
																								if((var183 == traceTempVariable$currentState$43_1)) {
																									{
																										{
																											{
																												double var230 = state.current_metric_valid_bias[server][traceTempVariable$currentState$43_1];
																												if(((Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)));
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
										{
											{
												boolean[][][] guard$sample76gaussian255 = scratch.guard$sample76gaussian255$global;
												{
													for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
														if((sample$var45 == sample$var196)) {
															for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																if((timeStep$var66 == timeStep$var226)) {
																	for(int server = 0; server < state.noServers; server += 1) {
																		if(state.metric_valid_g[sample$var196][server][timeStep$var226])
																			guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																	}
																}
															}
														}
													}
												}
												{
													for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
														if((sample$var45 == sample$var196)) {
															for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																if((timeStep$var66 == timeStep$var226)) {
																	for(int server = 0; server < state.noServers; server += 1) {
																		if(state.metric_valid_g[sample$var196][server][timeStep$var226])
																			guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																	}
																}
															}
														}
													}
												}
												{
													int traceTempVariable$currentState$67_1 = cv$currentValue;
													for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
														if((sample$var45 == sample$var196)) {
															for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																if((timeStep$var66 == timeStep$var226)) {
																	for(int server = 0; server < state.noServers; server += 1) {
																		if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
																			if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																				guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																				{
																					{
																						boolean cv$sampleConstrained = true;
																						if(cv$sampleConstrained) {
																							state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								{
																									for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																										for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																											if((var119 == server)) {
																												if((var129 == traceTempVariable$currentState$67_1)) {
																													if(state.fixedFlag$sample57) {
																														{
																															for(int index$sample$114_1 = 0; index$sample$114_1 < state.noSamples; index$sample$114_1 += 1) {
																																if((index$sample$114_1 == sample$var196)) {
																																	if((0 == timeStep$var226)) {
																																		{
																																			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																				for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																					if((var146 == server)) {
																																						if((var156 == traceTempVariable$currentState$67_1)) {
																																							{
																																								{
																																									{
																																										double var241 = state.current_metric_mean[server][traceTempVariable$currentState$67_1];
																																										double var243 = state.current_metric_var[server][traceTempVariable$currentState$67_1];
																																										if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																													} else {
																														for(int index$sample$115 = 0; index$sample$115 < state.noSamples; index$sample$115 += 1) {
																															if(true) {
																																for(int index$sample57$116 = 0; index$sample57$116 < state.noStates; index$sample57$116 += 1) {
																																	int distributionTempVariable$var55$118 = index$sample57$116;
																																	double cv$probabilitySample57Value117 = (1.0 * state.distribution$sample57[((index$sample$115 - 0) / 1)][index$sample57$116]);
																																	{
																																		int traceTempVariable$currentState$119_1 = distributionTempVariable$var55$118;
																																		if((index$sample$115 == sample$var196)) {
																																			if((0 == timeStep$var226)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$119_1)) {
																																									{
																																										{
																																											{
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$119_1];
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$119_1];
																																												if(((Math.log(cv$probabilitySample57Value117) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value117) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value117) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value117) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value117) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																												}
																																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value117);
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
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
																									for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																										for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																											if((var119 == server)) {
																												if((var129 == traceTempVariable$currentState$67_1)) {
																													{
																														int traceTempVariable$currentState$123_1 = cv$currentValue;
																														if((index$sample$2 == sample$var196)) {
																															if((index$timeStep$1 == timeStep$var226)) {
																																{
																																	for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == traceTempVariable$currentState$123_1)) {
																																					{
																																						{
																																							{
																																								double var241 = state.current_metric_mean[server][traceTempVariable$currentState$123_1];
																																								double var243 = state.current_metric_var[server][traceTempVariable$currentState$123_1];
																																								if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																													for(int index$sample$124 = 0; index$sample$124 < state.noSamples; index$sample$124 += 1) {
																														for(int index$timeStep$125 = 1; index$timeStep$125 < state.length$metric[index$sample$124][0]; index$timeStep$125 += 1) {
																															if(!((index$timeStep$125 == index$timeStep$1) && (index$sample$124 == index$sample$2))) {
																																for(int index$sample76$126 = 0; index$sample76$126 < state.noStates; index$sample76$126 += 1) {
																																	int distributionTempVariable$var74$128 = index$sample76$126;
																																	double cv$probabilitySample76Value127 = (1.0 * state.distribution$sample76[((index$sample$124 - 0) / 1)][((index$timeStep$125 - 1) / 1)][index$sample76$126]);
																																	{
																																		int traceTempVariable$currentState$129_1 = distributionTempVariable$var74$128;
																																		if((index$sample$124 == sample$var196)) {
																																			if((index$timeStep$125 == timeStep$var226)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$129_1)) {
																																									{
																																										{
																																											{
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$129_1];
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$129_1];
																																												if(((Math.log(cv$probabilitySample76Value127) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value127) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value127) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value127) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value127) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																												}
																																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value127);
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
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
												{
													int traceTempVariable$currentState$71_1 = cv$currentValue;
													for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
														if((sample$var45 == sample$var196)) {
															for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																if((timeStep$var66 == timeStep$var226)) {
																	for(int server = 0; server < state.noServers; server += 1) {
																		if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
																			if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																				guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																				{
																					{
																						boolean cv$sampleConstrained = true;
																						if(cv$sampleConstrained) {
																							state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								if(state.fixedFlag$sample57) {
																									{
																										for(int index$sample$188_1 = 0; index$sample$188_1 < state.noSamples; index$sample$188_1 += 1) {
																											if((index$sample$188_1 == sample$var196)) {
																												if((0 == timeStep$var226)) {
																													{
																														for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																															for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																if((var119 == server)) {
																																	if((var129 == traceTempVariable$currentState$71_1)) {
																																		{
																																			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																				for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																					if((var146 == server)) {
																																						if((var156 == traceTempVariable$currentState$71_1)) {
																																							{
																																								{
																																									{
																																										double var241 = state.current_metric_mean[server][traceTempVariable$currentState$71_1];
																																										double var243 = state.current_metric_var[server][traceTempVariable$currentState$71_1];
																																										if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																											}
																										}
																									}
																								} else {
																									for(int index$sample$189 = 0; index$sample$189 < state.noSamples; index$sample$189 += 1) {
																										if(true) {
																											for(int index$sample57$190 = 0; index$sample57$190 < state.noStates; index$sample57$190 += 1) {
																												int distributionTempVariable$var55$192 = index$sample57$190;
																												double cv$probabilitySample57Value191 = (1.0 * state.distribution$sample57[((index$sample$189 - 0) / 1)][index$sample57$190]);
																												{
																													int traceTempVariable$currentState$193_1 = distributionTempVariable$var55$192;
																													if((index$sample$189 == sample$var196)) {
																														if((0 == timeStep$var226)) {
																															{
																																for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																	for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																		if((var119 == server)) {
																																			if((var129 == traceTempVariable$currentState$193_1)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$193_1)) {
																																									{
																																										{
																																											{
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$193_1];
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$193_1];
																																												if(((Math.log(cv$probabilitySample57Value191) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value191) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value191) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value191) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value191) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																												}
																																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value191);
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
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
																									if((index$sample$2 == sample$var196)) {
																										if((index$timeStep$1 == timeStep$var226)) {
																											{
																												for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																													for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																														if((var119 == server)) {
																															if((var129 == traceTempVariable$currentState$198_1)) {
																																{
																																	for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																			if((var146 == server)) {
																																				if((var156 == traceTempVariable$currentState$198_1)) {
																																					{
																																						{
																																							{
																																								double var241 = state.current_metric_mean[server][traceTempVariable$currentState$198_1];
																																								double var243 = state.current_metric_var[server][traceTempVariable$currentState$198_1];
																																								if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																									}
																								}
																								for(int index$sample$199 = 0; index$sample$199 < state.noSamples; index$sample$199 += 1) {
																									for(int index$timeStep$200 = 1; index$timeStep$200 < state.length$metric[index$sample$199][0]; index$timeStep$200 += 1) {
																										if(!((index$timeStep$200 == index$timeStep$1) && (index$sample$199 == index$sample$2))) {
																											for(int index$sample76$201 = 0; index$sample76$201 < state.noStates; index$sample76$201 += 1) {
																												int distributionTempVariable$var74$203 = index$sample76$201;
																												double cv$probabilitySample76Value202 = (1.0 * state.distribution$sample76[((index$sample$199 - 0) / 1)][((index$timeStep$200 - 1) / 1)][index$sample76$201]);
																												{
																													int traceTempVariable$currentState$204_1 = distributionTempVariable$var74$203;
																													if((index$sample$199 == sample$var196)) {
																														if((index$timeStep$200 == timeStep$var226)) {
																															{
																																for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																	for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																		if((var119 == server)) {
																																			if((var129 == traceTempVariable$currentState$204_1)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$204_1)) {
																																									{
																																										{
																																											{
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$204_1];
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$204_1];
																																												if(((Math.log(cv$probabilitySample76Value202) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value202) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value202) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value202) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value202) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																												}
																																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value202);
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
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
					for(int index$timeStep$30 = 1; index$timeStep$30 < state.length$metric[index$sample$29][0]; index$timeStep$30 += 1) {
						if(!((index$timeStep$30 == index$timeStep$1) && (index$sample$29 == index$sample$2))) {
							for(int index$sample76$31 = 0; index$sample76$31 < state.noStates; index$sample76$31 += 1) {
								int distributionTempVariable$var74$33 = index$sample76$31;
								double cv$probabilitySample76Value32 = (1.0 * state.distribution$sample76[((index$sample$29 - 0) / 1)][((index$timeStep$30 - 1) / 1)][index$sample76$31]);
								{
									int traceTempVariable$var71$34_1 = distributionTempVariable$var74$33;
									if((index$sample$29 == sample$var45)) {
										if((index$timeStep$30 == (timeStep$var66 - 1))) {
											{
												for(int var32 = 0; var32 < state.noStates; var32 += 1) {
													if((var32 == traceTempVariable$var71$34_1)) {
														cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample76Value32);
														double[] var72 = state.m[traceTempVariable$var71$34_1];
														double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample76Value32) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[cv$currentValue])) && (var72[cv$currentValue] <= 1.0))?Math.log(var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
														{
															{
																{
																	int traceTempVariable$var71$40_1 = distributionTempVariable$var74$33;
																}
															}
														}
														{
															{
																{
																	int traceTempVariable$currentState$44_1 = distributionTempVariable$var74$33;
																	for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																		if((sample$var45 == sample$var196)) {
																			for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int server = 0; server < state.noServers; server += 1) {
																						boolean cv$sampleConstrained = true;
																						if(cv$sampleConstrained) {
																							state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								{
																									for(int var173 = 0; var173 < state.noServers; var173 += 1) {
																										for(int var183 = 0; var183 < state.noStates; var183 += 1) {
																											if((var173 == server)) {
																												if((var183 == traceTempVariable$currentState$44_1)) {
																													{
																														{
																															{
																																double var230 = state.current_metric_valid_bias[server][traceTempVariable$currentState$44_1];
																																if(((Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY)));
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
														{
															{
																boolean[][][] guard$sample76gaussian255 = scratch.guard$sample76gaussian255$global;
																{
																	for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																		if((sample$var45 == sample$var196)) {
																			for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int server = 0; server < state.noServers; server += 1) {
																						if(state.metric_valid_g[sample$var196][server][timeStep$var226])
																							guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																					}
																				}
																			}
																		}
																	}
																}
																{
																	for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																		if((sample$var45 == sample$var196)) {
																			for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int server = 0; server < state.noServers; server += 1) {
																						if(state.metric_valid_g[sample$var196][server][timeStep$var226])
																							guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																					}
																				}
																			}
																		}
																	}
																}
																{
																	int traceTempVariable$currentState$68_1 = distributionTempVariable$var74$33;
																	for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																		if((sample$var45 == sample$var196)) {
																			for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int server = 0; server < state.noServers; server += 1) {
																						if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
																							if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																								guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																								{
																									{
																										boolean cv$sampleConstrained = true;
																										if(cv$sampleConstrained) {
																											state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																											double cv$consumerDistributionProbabilityAccumulator = 1.0;
																											{
																												{
																													for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																														for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																															if((var119 == server)) {
																																if((var129 == traceTempVariable$currentState$68_1)) {
																																	if(state.fixedFlag$sample57) {
																																		{
																																			for(int index$sample$133_1 = 0; index$sample$133_1 < state.noSamples; index$sample$133_1 += 1) {
																																				if((index$sample$133_1 == sample$var196)) {
																																					if((0 == timeStep$var226)) {
																																						{
																																							for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																								for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																									if((var146 == server)) {
																																										if((var156 == traceTempVariable$currentState$68_1)) {
																																											{
																																												{
																																													{
																																														double var241 = state.current_metric_mean[server][traceTempVariable$currentState$68_1];
																																														double var243 = state.current_metric_var[server][traceTempVariable$currentState$68_1];
																																														if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																														else {
																																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																															else
																																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																																	} else {
																																		for(int index$sample$134 = 0; index$sample$134 < state.noSamples; index$sample$134 += 1) {
																																			if(true) {
																																				for(int index$sample57$135 = 0; index$sample57$135 < state.noStates; index$sample57$135 += 1) {
																																					int distributionTempVariable$var55$137 = index$sample57$135;
																																					double cv$probabilitySample57Value136 = (1.0 * state.distribution$sample57[((index$sample$134 - 0) / 1)][index$sample57$135]);
																																					{
																																						int traceTempVariable$currentState$138_1 = distributionTempVariable$var55$137;
																																						if((index$sample$134 == sample$var196)) {
																																							if((0 == timeStep$var226)) {
																																								{
																																									for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																										for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																											if((var146 == server)) {
																																												if((var156 == traceTempVariable$currentState$138_1)) {
																																													{
																																														{
																																															{
																																																double var241 = state.current_metric_mean[server][traceTempVariable$currentState$138_1];
																																																double var243 = state.current_metric_var[server][traceTempVariable$currentState$138_1];
																																																if(((Math.log(cv$probabilitySample57Value136) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value136) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																																else {
																																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value136) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																																	else
																																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value136) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value136) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																																}
																																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value136);
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
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
																													for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																														for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																															if((var119 == server)) {
																																if((var129 == traceTempVariable$currentState$68_1)) {
																																	{
																																		int traceTempVariable$currentState$142_1 = cv$currentValue;
																																		if((index$sample$2 == sample$var196)) {
																																			if((index$timeStep$1 == timeStep$var226)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$142_1)) {
																																									{
																																										{
																																											{
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$142_1];
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$142_1];
																																												if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																																	{
																																		int traceTempVariable$currentState$143_1 = distributionTempVariable$var74$33;
																																		if((index$sample$29 == sample$var196)) {
																																			if((index$timeStep$30 == timeStep$var226)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$143_1)) {
																																									{
																																										{
																																											{
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$143_1];
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$143_1];
																																												if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																																	for(int index$sample$144 = 0; index$sample$144 < state.noSamples; index$sample$144 += 1) {
																																		for(int index$timeStep$145 = 1; index$timeStep$145 < state.length$metric[index$sample$144][0]; index$timeStep$145 += 1) {
																																			if((!((index$timeStep$145 == index$timeStep$1) && (index$sample$144 == index$sample$2)) && !((index$timeStep$145 == index$timeStep$30) && (index$sample$144 == index$sample$29)))) {
																																				for(int index$sample76$146 = 0; index$sample76$146 < state.noStates; index$sample76$146 += 1) {
																																					int distributionTempVariable$var74$148 = index$sample76$146;
																																					double cv$probabilitySample76Value147 = (1.0 * state.distribution$sample76[((index$sample$144 - 0) / 1)][((index$timeStep$145 - 1) / 1)][index$sample76$146]);
																																					{
																																						int traceTempVariable$currentState$149_1 = distributionTempVariable$var74$148;
																																						if((index$sample$144 == sample$var196)) {
																																							if((index$timeStep$145 == timeStep$var226)) {
																																								{
																																									for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																										for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																											if((var146 == server)) {
																																												if((var156 == traceTempVariable$currentState$149_1)) {
																																													{
																																														{
																																															{
																																																double var241 = state.current_metric_mean[server][traceTempVariable$currentState$149_1];
																																																double var243 = state.current_metric_var[server][traceTempVariable$currentState$149_1];
																																																if(((Math.log(cv$probabilitySample76Value147) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value147) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																																else {
																																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value147) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																																	else
																																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value147) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value147) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																																}
																																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value147);
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
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
																{
																	int traceTempVariable$currentState$72_1 = distributionTempVariable$var74$33;
																	for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
																		if((sample$var45 == sample$var196)) {
																			for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
																				if((timeStep$var66 == timeStep$var226)) {
																					for(int server = 0; server < state.noServers; server += 1) {
																						if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
																							if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																								guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																								{
																									{
																										boolean cv$sampleConstrained = true;
																										if(cv$sampleConstrained) {
																											state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = true;
																											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																											double cv$consumerDistributionProbabilityAccumulator = 1.0;
																											{
																												if(state.fixedFlag$sample57) {
																													{
																														for(int index$sample$209_1 = 0; index$sample$209_1 < state.noSamples; index$sample$209_1 += 1) {
																															if((index$sample$209_1 == sample$var196)) {
																																if((0 == timeStep$var226)) {
																																	{
																																		for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																			for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																				if((var119 == server)) {
																																					if((var129 == traceTempVariable$currentState$72_1)) {
																																						{
																																							for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																								for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																									if((var146 == server)) {
																																										if((var156 == traceTempVariable$currentState$72_1)) {
																																											{
																																												{
																																													{
																																														double var241 = state.current_metric_mean[server][traceTempVariable$currentState$72_1];
																																														double var243 = state.current_metric_var[server][traceTempVariable$currentState$72_1];
																																														if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																														else {
																																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																															else
																																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																															}
																														}
																													}
																												} else {
																													for(int index$sample$210 = 0; index$sample$210 < state.noSamples; index$sample$210 += 1) {
																														if(true) {
																															for(int index$sample57$211 = 0; index$sample57$211 < state.noStates; index$sample57$211 += 1) {
																																int distributionTempVariable$var55$213 = index$sample57$211;
																																double cv$probabilitySample57Value212 = (1.0 * state.distribution$sample57[((index$sample$210 - 0) / 1)][index$sample57$211]);
																																{
																																	int traceTempVariable$currentState$214_1 = distributionTempVariable$var55$213;
																																	if((index$sample$210 == sample$var196)) {
																																		if((0 == timeStep$var226)) {
																																			{
																																				for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																					for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																						if((var119 == server)) {
																																							if((var129 == traceTempVariable$currentState$214_1)) {
																																								{
																																									for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																										for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																											if((var146 == server)) {
																																												if((var156 == traceTempVariable$currentState$214_1)) {
																																													{
																																														{
																																															{
																																																double var241 = state.current_metric_mean[server][traceTempVariable$currentState$214_1];
																																																double var243 = state.current_metric_var[server][traceTempVariable$currentState$214_1];
																																																if(((Math.log(cv$probabilitySample57Value212) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value212) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																																else {
																																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value212) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																																	else
																																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value212) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample57Value212) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																																}
																																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value212);
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
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
																													if((index$sample$2 == sample$var196)) {
																														if((index$timeStep$1 == timeStep$var226)) {
																															{
																																for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																	for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																		if((var119 == server)) {
																																			if((var129 == traceTempVariable$currentState$219_1)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$219_1)) {
																																									{
																																										{
																																											{
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$219_1];
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$219_1];
																																												if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																													}
																												}
																												{
																													int traceTempVariable$currentState$220_1 = distributionTempVariable$var74$33;
																													if((index$sample$29 == sample$var196)) {
																														if((index$timeStep$30 == timeStep$var226)) {
																															{
																																for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																	for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																		if((var119 == server)) {
																																			if((var129 == traceTempVariable$currentState$220_1)) {
																																				{
																																					for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																						for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																							if((var146 == server)) {
																																								if((var156 == traceTempVariable$currentState$220_1)) {
																																									{
																																										{
																																											{
																																												double var241 = state.current_metric_mean[server][traceTempVariable$currentState$220_1];
																																												double var243 = state.current_metric_var[server][traceTempVariable$currentState$220_1];
																																												if(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																												else {
																																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																													else
																																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
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
																													}
																												}
																												for(int index$sample$221 = 0; index$sample$221 < state.noSamples; index$sample$221 += 1) {
																													for(int index$timeStep$222 = 1; index$timeStep$222 < state.length$metric[index$sample$221][0]; index$timeStep$222 += 1) {
																														if((!((index$timeStep$222 == index$timeStep$1) && (index$sample$221 == index$sample$2)) && !((index$timeStep$222 == index$timeStep$30) && (index$sample$221 == index$sample$29)))) {
																															for(int index$sample76$223 = 0; index$sample76$223 < state.noStates; index$sample76$223 += 1) {
																																int distributionTempVariable$var74$225 = index$sample76$223;
																																double cv$probabilitySample76Value224 = (1.0 * state.distribution$sample76[((index$sample$221 - 0) / 1)][((index$timeStep$222 - 1) / 1)][index$sample76$223]);
																																{
																																	int traceTempVariable$currentState$226_1 = distributionTempVariable$var74$225;
																																	if((index$sample$221 == sample$var196)) {
																																		if((index$timeStep$222 == timeStep$var226)) {
																																			{
																																				for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																																					for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																																						if((var119 == server)) {
																																							if((var129 == traceTempVariable$currentState$226_1)) {
																																								{
																																									for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																																										for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																											if((var146 == server)) {
																																												if((var156 == traceTempVariable$currentState$226_1)) {
																																													{
																																														{
																																															{
																																																double var241 = state.current_metric_mean[server][traceTempVariable$currentState$226_1];
																																																double var243 = state.current_metric_var[server][traceTempVariable$currentState$226_1];
																																																if(((Math.log(cv$probabilitySample76Value224) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value224) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																																else {
																																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value224) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																																	else
																																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value224) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample76Value224) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY)));
																																																}
																																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value224);
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
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
							int traceTempVariable$var71$265_1 = cv$currentValue;
							for(int index$sample$265_2 = 0; index$sample$265_2 < state.noSamples; index$sample$265_2 += 1) {
								if((sample$var45 == index$sample$265_2)) {
									for(int index$timeStep$265_3 = 1; index$timeStep$265_3 < state.length$metric[index$sample$265_2][0]; index$timeStep$265_3 += 1) {
										if((timeStep$var66 == (index$timeStep$265_3 - 1))) {
											{
												{
													int index$timeStep$267 = index$timeStep$265_3;
													int index$sample$268 = index$sample$265_2;
													double[] cv$accumulatedConsumerDistributions = scratch.cv$distributionAccumulator$var73;
													for(int cv$i = 0; cv$i < state.noStates; cv$i += 1)
														cv$accumulatedConsumerDistributions[cv$i] = 0.0;
													double cv$reachedDistributionProbability = 0.0;
													{
														for(int var32 = 0; var32 < state.noStates; var32 += 1) {
															if((var32 == traceTempVariable$var71$265_1)) {
																{
																	double scopeVariable$reachedSourceProbability = 0.0;
																	if(state.fixedFlag$sample57) {
																		{
																			for(int index$sample$270_1 = 0; index$sample$270_1 < state.noSamples; index$sample$270_1 += 1) {
																				if((index$sample$270_1 == sample$var45)) {
																					if((0 == (timeStep$var66 - 1))) {
																						{
																							for(int index$var32$276_1 = 0; index$var32$276_1 < state.noStates; index$var32$276_1 += 1) {
																								if((index$var32$276_1 == state.st[sample$var45][(timeStep$var66 - 1)]))
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
																				for(int index$sample57$272 = 0; index$sample57$272 < state.noStates; index$sample57$272 += 1) {
																					int distributionTempVariable$var55$274 = index$sample57$272;
																					double cv$probabilitySample57Value273 = (1.0 * state.distribution$sample57[((index$sample$271 - 0) / 1)][index$sample57$272]);
																					{
																						int traceTempVariable$var71$275_1 = distributionTempVariable$var55$274;
																						if((index$sample$271 == sample$var45)) {
																							if((0 == (timeStep$var66 - 1))) {
																								{
																									for(int index$var32$277_1 = 0; index$var32$277_1 < state.noStates; index$var32$277_1 += 1) {
																										if((index$var32$277_1 == traceTempVariable$var71$275_1))
																											scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample57Value273);
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
																		int traceTempVariable$var71$278_1 = cv$currentValue;
																		if((index$sample$2 == sample$var45)) {
																			if((index$timeStep$1 == (timeStep$var66 - 1))) {
																				{
																					for(int index$var32$285_1 = 0; index$var32$285_1 < state.noStates; index$var32$285_1 += 1) {
																						if((index$var32$285_1 == traceTempVariable$var71$278_1))
																							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																					}
																				}
																			}
																		}
																	}
																	for(int index$sample$279 = 0; index$sample$279 < state.noSamples; index$sample$279 += 1) {
																		for(int index$timeStep$280 = 1; index$timeStep$280 < state.length$metric[index$sample$279][0]; index$timeStep$280 += 1) {
																			if((!((index$timeStep$280 == index$timeStep$1) && (index$sample$279 == index$sample$2)) && !((index$timeStep$280 == index$timeStep$267) && (index$sample$279 == index$sample$268)))) {
																				for(int index$sample76$281 = 0; index$sample76$281 < state.noStates; index$sample76$281 += 1) {
																					int distributionTempVariable$var74$283 = index$sample76$281;
																					double cv$probabilitySample76Value282 = (1.0 * state.distribution$sample76[((index$sample$279 - 0) / 1)][((index$timeStep$280 - 1) / 1)][index$sample76$281]);
																					{
																						int traceTempVariable$var71$284_1 = distributionTempVariable$var74$283;
																						if((index$sample$279 == sample$var45)) {
																							if((index$timeStep$280 == (timeStep$var66 - 1))) {
																								{
																									for(int index$var32$286_1 = 0; index$var32$286_1 < state.noStates; index$var32$286_1 += 1) {
																										if((index$var32$286_1 == traceTempVariable$var71$284_1))
																											scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample76Value282);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																	double[] var72 = state.m[traceTempVariable$var71$265_1];
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																	cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
																	DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, var72, state.noStates);
																}
															}
														}
													}
													double[] cv$sampleDistribution = state.distribution$sample76[((index$sample$265_2 - 0) / 1)][((index$timeStep$265_3 - 1) / 1)];
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
			if(state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)]) {
				double[] cv$localProbability = state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)];
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

	private final void logProbabilityDistribution$sample241() {
		if(!state.fixedProbFlag$sample241) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							{
								boolean cv$sampleValue = state.metric_valid_g[sample$var196][server][timeStep$var226];
								if(state.fixedFlag$sample57) {
									{
										for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
											if((sample$var45 == sample$var196)) {
												if((0 == timeStep$var226)) {
													{
														for(int var173 = 0; var173 < state.noServers; var173 += 1) {
															for(int var183 = 0; var183 < state.noStates; var183 += 1) {
																if((var173 == server)) {
																	if((var183 == state.st[sample$var196][timeStep$var226])) {
																		{
																			double var230 = state.current_metric_valid_bias[server][state.st[sample$var196][timeStep$var226]];
																			double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((cv$sampleValue?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
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
								} else {
									for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
										if(true) {
											for(int index$sample57$4 = 0; index$sample57$4 < state.noStates; index$sample57$4 += 1) {
												int distributionTempVariable$var55$6 = index$sample57$4;
												double cv$probabilitySample57Value5 = (1.0 * state.distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$4]);
												{
													int traceTempVariable$currentState$7_1 = distributionTempVariable$var55$6;
													if((sample$var45 == sample$var196)) {
														if((0 == timeStep$var226)) {
															{
																for(int var173 = 0; var173 < state.noServers; var173 += 1) {
																	for(int var183 = 0; var183 < state.noStates; var183 += 1) {
																		if((var173 == server)) {
																			if((var183 == traceTempVariable$currentState$7_1)) {
																				{
																					double var230 = state.current_metric_valid_bias[server][traceTempVariable$currentState$7_1];
																					double cv$weightedProbability = (Math.log(cv$probabilitySample57Value5) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((cv$sampleValue?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value5);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								if(state.fixedFlag$sample76) {
									{
										for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
											for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
												if((sample$var45 == sample$var196)) {
													if((timeStep$var66 == timeStep$var226)) {
														{
															for(int var173 = 0; var173 < state.noServers; var173 += 1) {
																for(int var183 = 0; var183 < state.noStates; var183 += 1) {
																	if((var173 == server)) {
																		if((var183 == state.st[sample$var196][timeStep$var226])) {
																			{
																				double var230 = state.current_metric_valid_bias[server][state.st[sample$var196][timeStep$var226]];
																				double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((cv$sampleValue?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
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
								} else {
									for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
										for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
											if(true) {
												for(int index$sample76$13 = 0; index$sample76$13 < state.noStates; index$sample76$13 += 1) {
													int distributionTempVariable$var74$15 = index$sample76$13;
													double cv$probabilitySample76Value14 = (1.0 * state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$13]);
													{
														int traceTempVariable$currentState$16_1 = distributionTempVariable$var74$15;
														if((sample$var45 == sample$var196)) {
															if((timeStep$var66 == timeStep$var226)) {
																{
																	for(int var173 = 0; var173 < state.noServers; var173 += 1) {
																		for(int var183 = 0; var183 < state.noStates; var183 += 1) {
																			if((var173 == server)) {
																				if((var183 == traceTempVariable$currentState$16_1)) {
																					{
																						double var230 = state.current_metric_valid_bias[server][traceTempVariable$currentState$16_1];
																						double cv$weightedProbability = (Math.log(cv$probabilitySample76Value14) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((cv$sampleValue?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value14);
																					}
																				}
																			}
																		}
																	}
																}
															}
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
						state.logProbability$sample241[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = cv$sampleProbability;
					}
				}
			}
			boolean cv$guard$metric_valid_g = false;
			state.logProbability$metric_valid_inner = (state.logProbability$metric_valid_inner + cv$accumulator);
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
			state.fixedProbFlag$sample241 = ((state.fixedFlag$sample57 && state.fixedFlag$sample76) && state.fixedFlag$sample190);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = state.logProbability$sample241[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$sampleReached = true;
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					}
				}
			}
			boolean cv$guard$metric_valid_g = false;
			state.logProbability$metric_valid_inner = (state.logProbability$metric_valid_inner + cv$accumulator);
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

	private final void logProbabilityDistribution$sample256() {
		if(!state.fixedProbFlag$sample256) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
							double cv$sampleAccumulator = 0.0;
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							double cv$probabilityReached = 0.0;
							{
								{
									double cv$sampleValue = state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
									if(state.fixedFlag$sample57) {
										{
											for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
												if((sample$var45 == sample$var196)) {
													if((0 == timeStep$var226)) {
														{
															for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																	if((var119 == server)) {
																		if((var129 == state.st[sample$var196][timeStep$var226])) {
																			{
																				for(int index$sample$10_1 = 0; index$sample$10_1 < state.noSamples; index$sample$10_1 += 1) {
																					if((index$sample$10_1 == sample$var196)) {
																						if((0 == timeStep$var226)) {
																							{
																								for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																									for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																										if((var146 == server)) {
																											if((var156 == state.st[sample$var196][timeStep$var226])) {
																												{
																													double var241 = state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]];
																													double var243 = state.current_metric_var[server][state.st[sample$var196][timeStep$var226]];
																													double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
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
											}
										}
									} else {
										for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
											if(true) {
												for(int index$sample57$4 = 0; index$sample57$4 < state.noStates; index$sample57$4 += 1) {
													int distributionTempVariable$var55$6 = index$sample57$4;
													double cv$probabilitySample57Value5 = (1.0 * state.distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$4]);
													{
														int traceTempVariable$currentState$7_1 = distributionTempVariable$var55$6;
														if((sample$var45 == sample$var196)) {
															if((0 == timeStep$var226)) {
																{
																	for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																		for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																			if((var119 == server)) {
																				if((var129 == traceTempVariable$currentState$7_1)) {
																					{
																						int traceTempVariable$currentState$11_1 = distributionTempVariable$var55$6;
																						if((sample$var45 == sample$var196)) {
																							if((0 == timeStep$var226)) {
																								{
																									for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																										for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																											if((var146 == server)) {
																												if((var156 == traceTempVariable$currentState$11_1)) {
																													{
																														double var241 = state.current_metric_mean[server][traceTempVariable$currentState$11_1];
																														double var243 = state.current_metric_var[server][traceTempVariable$currentState$11_1];
																														double cv$weightedProbability = (Math.log(cv$probabilitySample57Value5) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																														if((cv$weightedProbability < cv$distributionAccumulator))
																															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																														else {
																															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																cv$distributionAccumulator = cv$weightedProbability;
																															else
																																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																														}
																														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value5);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$12 = 0; index$sample$12 < state.noSamples; index$sample$12 += 1) {
																						if(!(index$sample$12 == sample$var45)) {
																							for(int index$sample57$13 = 0; index$sample57$13 < state.noStates; index$sample57$13 += 1) {
																								int distributionTempVariable$var55$15 = index$sample57$13;
																								double cv$probabilitySample57Value14 = (cv$probabilitySample57Value5 * state.distribution$sample57[((index$sample$12 - 0) / 1)][index$sample57$13]);
																								{
																									int traceTempVariable$currentState$16_1 = distributionTempVariable$var55$15;
																									if((index$sample$12 == sample$var196)) {
																										if((0 == timeStep$var226)) {
																											{
																												for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																													for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																														if((var146 == server)) {
																															if((var156 == traceTempVariable$currentState$16_1)) {
																																{
																																	double var241 = state.current_metric_mean[server][traceTempVariable$currentState$16_1];
																																	double var243 = state.current_metric_var[server][traceTempVariable$currentState$16_1];
																																	double cv$weightedProbability = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																	if((cv$weightedProbability < cv$distributionAccumulator))
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																	else {
																																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																			cv$distributionAccumulator = cv$weightedProbability;
																																		else
																																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																	}
																																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value14);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
									if(state.fixedFlag$sample57) {
										{
											for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
												if((sample$var45 == sample$var196)) {
													if((0 == timeStep$var226)) {
														{
															for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																	if((var119 == server)) {
																		if((var129 == state.st[sample$var196][timeStep$var226])) {
																			if(state.fixedFlag$sample76) {
																				{
																					for(int index$sample$28_1 = 0; index$sample$28_1 < state.noSamples; index$sample$28_1 += 1) {
																						for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$28_1][0]; timeStep$var66 += 1) {
																							if((index$sample$28_1 == sample$var196)) {
																								if((timeStep$var66 == timeStep$var226)) {
																									{
																										for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																											for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																												if((var146 == server)) {
																													if((var156 == state.st[sample$var196][timeStep$var226])) {
																														{
																															double var241 = state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]];
																															double var243 = state.current_metric_var[server][state.st[sample$var196][timeStep$var226]];
																															double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
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
																			} else {
																				for(int index$sample$29 = 0; index$sample$29 < state.noSamples; index$sample$29 += 1) {
																					for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$29][0]; timeStep$var66 += 1) {
																						if(true) {
																							for(int index$sample76$31 = 0; index$sample76$31 < state.noStates; index$sample76$31 += 1) {
																								int distributionTempVariable$var74$33 = index$sample76$31;
																								double cv$probabilitySample76Value32 = (1.0 * state.distribution$sample76[((index$sample$29 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$31]);
																								{
																									int traceTempVariable$currentState$34_1 = distributionTempVariable$var74$33;
																									if((index$sample$29 == sample$var196)) {
																										if((timeStep$var66 == timeStep$var226)) {
																											{
																												for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																													for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																														if((var146 == server)) {
																															if((var156 == traceTempVariable$currentState$34_1)) {
																																{
																																	double var241 = state.current_metric_mean[server][traceTempVariable$currentState$34_1];
																																	double var243 = state.current_metric_var[server][traceTempVariable$currentState$34_1];
																																	double cv$weightedProbability = (Math.log(cv$probabilitySample76Value32) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																	if((cv$weightedProbability < cv$distributionAccumulator))
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																	else {
																																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																			cv$distributionAccumulator = cv$weightedProbability;
																																		else
																																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																	}
																																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value32);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
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
										for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
											if(true) {
												for(int index$sample57$22 = 0; index$sample57$22 < state.noStates; index$sample57$22 += 1) {
													int distributionTempVariable$var55$24 = index$sample57$22;
													double cv$probabilitySample57Value23 = (1.0 * state.distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$22]);
													{
														int traceTempVariable$currentState$25_1 = distributionTempVariable$var55$24;
														if((sample$var45 == sample$var196)) {
															if((0 == timeStep$var226)) {
																{
																	for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																		for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																			if((var119 == server)) {
																				if((var129 == traceTempVariable$currentState$25_1)) {
																					if(state.fixedFlag$sample76) {
																						{
																							for(int index$sample$35_1 = 0; index$sample$35_1 < state.noSamples; index$sample$35_1 += 1) {
																								for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$35_1][0]; timeStep$var66 += 1) {
																									if((index$sample$35_1 == sample$var196)) {
																										if((timeStep$var66 == timeStep$var226)) {
																											{
																												for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																													for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																														if((var146 == server)) {
																															if((var156 == traceTempVariable$currentState$25_1)) {
																																{
																																	double var241 = state.current_metric_mean[server][traceTempVariable$currentState$25_1];
																																	double var243 = state.current_metric_var[server][traceTempVariable$currentState$25_1];
																																	double cv$weightedProbability = (Math.log(cv$probabilitySample57Value23) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																	if((cv$weightedProbability < cv$distributionAccumulator))
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																	else {
																																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																			cv$distributionAccumulator = cv$weightedProbability;
																																		else
																																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																	}
																																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value23);
																																}
																															}
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
																							for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[index$sample$36][0]; timeStep$var66 += 1) {
																								if(true) {
																									for(int index$sample76$38 = 0; index$sample76$38 < state.noStates; index$sample76$38 += 1) {
																										int distributionTempVariable$var74$40 = index$sample76$38;
																										double cv$probabilitySample76Value39 = (cv$probabilitySample57Value23 * state.distribution$sample76[((index$sample$36 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$38]);
																										{
																											int traceTempVariable$currentState$41_1 = distributionTempVariable$var74$40;
																											if((index$sample$36 == sample$var196)) {
																												if((timeStep$var66 == timeStep$var226)) {
																													{
																														for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																															for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																if((var146 == server)) {
																																	if((var156 == traceTempVariable$currentState$41_1)) {
																																		{
																																			double var241 = state.current_metric_mean[server][traceTempVariable$currentState$41_1];
																																			double var243 = state.current_metric_var[server][traceTempVariable$currentState$41_1];
																																			double cv$weightedProbability = (Math.log(cv$probabilitySample76Value39) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																			if((cv$weightedProbability < cv$distributionAccumulator))
																																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																			else {
																																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																					cv$distributionAccumulator = cv$weightedProbability;
																																				else
																																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																			}
																																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value39);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
									if(state.fixedFlag$sample76) {
										{
											for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
												for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
													if((sample$var45 == sample$var196)) {
														if((timeStep$var66 == timeStep$var226)) {
															{
																for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																	for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																		if((var119 == server)) {
																			if((var129 == state.st[sample$var196][timeStep$var226])) {
																				{
																					for(int index$sample$55_1 = 0; index$sample$55_1 < state.noSamples; index$sample$55_1 += 1) {
																						for(int index$timeStep$55_2 = 1; index$timeStep$55_2 < state.length$metric[index$sample$55_1][0]; index$timeStep$55_2 += 1) {
																							if((index$sample$55_1 == sample$var196)) {
																								if((index$timeStep$55_2 == timeStep$var226)) {
																									{
																										for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																											for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																												if((var146 == server)) {
																													if((var156 == state.st[sample$var196][timeStep$var226])) {
																														{
																															double var241 = state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]];
																															double var243 = state.current_metric_var[server][state.st[sample$var196][timeStep$var226]];
																															double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
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
													}
												}
											}
										}
									} else {
										for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
											for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
												if(true) {
													for(int index$sample76$49 = 0; index$sample76$49 < state.noStates; index$sample76$49 += 1) {
														int distributionTempVariable$var74$51 = index$sample76$49;
														double cv$probabilitySample76Value50 = (1.0 * state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$49]);
														{
															int traceTempVariable$currentState$52_1 = distributionTempVariable$var74$51;
															if((sample$var45 == sample$var196)) {
																if((timeStep$var66 == timeStep$var226)) {
																	{
																		for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																			for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																				if((var119 == server)) {
																					if((var129 == traceTempVariable$currentState$52_1)) {
																						{
																							int traceTempVariable$currentState$56_1 = distributionTempVariable$var74$51;
																							if((sample$var45 == sample$var196)) {
																								if((timeStep$var66 == timeStep$var226)) {
																									{
																										for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																											for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																												if((var146 == server)) {
																													if((var156 == traceTempVariable$currentState$56_1)) {
																														{
																															double var241 = state.current_metric_mean[server][traceTempVariable$currentState$56_1];
																															double var243 = state.current_metric_var[server][traceTempVariable$currentState$56_1];
																															double cv$weightedProbability = (Math.log(cv$probabilitySample76Value50) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																															if((cv$weightedProbability < cv$distributionAccumulator))
																																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																															else {
																																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																	cv$distributionAccumulator = cv$weightedProbability;
																																else
																																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																															}
																															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value50);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						for(int index$sample$57 = 0; index$sample$57 < state.noSamples; index$sample$57 += 1) {
																							for(int index$timeStep$58 = 1; index$timeStep$58 < state.length$metric[index$sample$57][0]; index$timeStep$58 += 1) {
																								if(!((index$timeStep$58 == timeStep$var66) && (index$sample$57 == sample$var45))) {
																									for(int index$sample76$59 = 0; index$sample76$59 < state.noStates; index$sample76$59 += 1) {
																										int distributionTempVariable$var74$61 = index$sample76$59;
																										double cv$probabilitySample76Value60 = (cv$probabilitySample76Value50 * state.distribution$sample76[((index$sample$57 - 0) / 1)][((index$timeStep$58 - 1) / 1)][index$sample76$59]);
																										{
																											int traceTempVariable$currentState$62_1 = distributionTempVariable$var74$61;
																											if((index$sample$57 == sample$var196)) {
																												if((index$timeStep$58 == timeStep$var226)) {
																													{
																														for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																															for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																if((var146 == server)) {
																																	if((var156 == traceTempVariable$currentState$62_1)) {
																																		{
																																			double var241 = state.current_metric_mean[server][traceTempVariable$currentState$62_1];
																																			double var243 = state.current_metric_var[server][traceTempVariable$currentState$62_1];
																																			double cv$weightedProbability = (Math.log(cv$probabilitySample76Value60) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																			if((cv$weightedProbability < cv$distributionAccumulator))
																																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																			else {
																																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																					cv$distributionAccumulator = cv$weightedProbability;
																																				else
																																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																			}
																																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value60);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
									if(state.fixedFlag$sample76) {
										{
											for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
												for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
													if((sample$var45 == sample$var196)) {
														if((timeStep$var66 == timeStep$var226)) {
															{
																for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																	for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																		if((var119 == server)) {
																			if((var129 == state.st[sample$var196][timeStep$var226])) {
																				if(state.fixedFlag$sample57) {
																					{
																						for(int index$sample$75_1 = 0; index$sample$75_1 < state.noSamples; index$sample$75_1 += 1) {
																							if((index$sample$75_1 == sample$var196)) {
																								if((0 == timeStep$var226)) {
																									{
																										for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																											for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																												if((var146 == server)) {
																													if((var156 == state.st[sample$var196][timeStep$var226])) {
																														{
																															double var241 = state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]];
																															double var243 = state.current_metric_var[server][state.st[sample$var196][timeStep$var226]];
																															double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
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
																				} else {
																					for(int index$sample$76 = 0; index$sample$76 < state.noSamples; index$sample$76 += 1) {
																						if(true) {
																							for(int index$sample57$77 = 0; index$sample57$77 < state.noStates; index$sample57$77 += 1) {
																								int distributionTempVariable$var55$79 = index$sample57$77;
																								double cv$probabilitySample57Value78 = (1.0 * state.distribution$sample57[((index$sample$76 - 0) / 1)][index$sample57$77]);
																								{
																									int traceTempVariable$currentState$80_1 = distributionTempVariable$var55$79;
																									if((index$sample$76 == sample$var196)) {
																										if((0 == timeStep$var226)) {
																											{
																												for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																													for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																														if((var146 == server)) {
																															if((var156 == traceTempVariable$currentState$80_1)) {
																																{
																																	double var241 = state.current_metric_mean[server][traceTempVariable$currentState$80_1];
																																	double var243 = state.current_metric_var[server][traceTempVariable$currentState$80_1];
																																	double cv$weightedProbability = (Math.log(cv$probabilitySample57Value78) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																	if((cv$weightedProbability < cv$distributionAccumulator))
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																	else {
																																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																			cv$distributionAccumulator = cv$weightedProbability;
																																		else
																																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																	}
																																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value78);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
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
										for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
											for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
												if(true) {
													for(int index$sample76$69 = 0; index$sample76$69 < state.noStates; index$sample76$69 += 1) {
														int distributionTempVariable$var74$71 = index$sample76$69;
														double cv$probabilitySample76Value70 = (1.0 * state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$69]);
														{
															int traceTempVariable$currentState$72_1 = distributionTempVariable$var74$71;
															if((sample$var45 == sample$var196)) {
																if((timeStep$var66 == timeStep$var226)) {
																	{
																		for(int var119 = 0; var119 < state.noServers; var119 += 1) {
																			for(int var129 = 0; var129 < state.noStates; var129 += 1) {
																				if((var119 == server)) {
																					if((var129 == traceTempVariable$currentState$72_1)) {
																						if(state.fixedFlag$sample57) {
																							{
																								for(int index$sample$81_1 = 0; index$sample$81_1 < state.noSamples; index$sample$81_1 += 1) {
																									if((index$sample$81_1 == sample$var196)) {
																										if((0 == timeStep$var226)) {
																											{
																												for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																													for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																														if((var146 == server)) {
																															if((var156 == traceTempVariable$currentState$72_1)) {
																																{
																																	double var241 = state.current_metric_mean[server][traceTempVariable$currentState$72_1];
																																	double var243 = state.current_metric_var[server][traceTempVariable$currentState$72_1];
																																	double cv$weightedProbability = (Math.log(cv$probabilitySample76Value70) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																	if((cv$weightedProbability < cv$distributionAccumulator))
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																	else {
																																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																			cv$distributionAccumulator = cv$weightedProbability;
																																		else
																																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																	}
																																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value70);
																																}
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
																							for(int index$sample$82 = 0; index$sample$82 < state.noSamples; index$sample$82 += 1) {
																								if(true) {
																									for(int index$sample57$83 = 0; index$sample57$83 < state.noStates; index$sample57$83 += 1) {
																										int distributionTempVariable$var55$85 = index$sample57$83;
																										double cv$probabilitySample57Value84 = (cv$probabilitySample76Value70 * state.distribution$sample57[((index$sample$82 - 0) / 1)][index$sample57$83]);
																										{
																											int traceTempVariable$currentState$86_1 = distributionTempVariable$var55$85;
																											if((index$sample$82 == sample$var196)) {
																												if((0 == timeStep$var226)) {
																													{
																														for(int var146 = 0; var146 < state.noServers; var146 += 1) {
																															for(int var156 = 0; var156 < state.noStates; var156 += 1) {
																																if((var146 == server)) {
																																	if((var156 == traceTempVariable$currentState$86_1)) {
																																		{
																																			double var241 = state.current_metric_mean[server][traceTempVariable$currentState$86_1];
																																			double var243 = state.current_metric_var[server][traceTempVariable$currentState$86_1];
																																			double cv$weightedProbability = (Math.log(cv$probabilitySample57Value84) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
																																			if((cv$weightedProbability < cv$distributionAccumulator))
																																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																			else {
																																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																					cv$distributionAccumulator = cv$weightedProbability;
																																				else
																																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																			}
																																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value84);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
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
							state.logProbability$sample256[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = cv$sampleProbability;
						}
					}
				}
			}
			boolean cv$guard$metric_g = false;
			state.logProbability$var245 = (state.logProbability$var245 + cv$accumulator);
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
			state.fixedProbFlag$sample256 = (((state.fixedFlag$sample57 && state.fixedFlag$sample76) && state.fixedFlag$sample134) && state.fixedFlag$sample162);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
							double cv$rvAccumulator = 0.0;
							double cv$sampleValue = state.logProbability$sample256[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
							cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
							cv$sampleReached = true;
							cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						}
					}
				}
			}
			boolean cv$guard$metric_g = false;
			state.logProbability$var245 = (state.logProbability$var245 + cv$accumulator);
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

	private final void logProbabilityDistribution$sample57() {
		if(!state.fixedProbFlag$sample57) {
			if(state.fixedFlag$sample57) {
				double cv$accumulator = 0.0;
				boolean cv$sampleReached = false;
				for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$sample$1 = sample$var45;
					{
						{
							int cv$sampleValue = state.st[sample$var45][0];
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
					state.logProbability$sample57[((sample$var45 - 0) / 1)] = cv$sampleProbability;
				}
				if(state.fixedFlag$sample57)
					state.logProbability$st = (state.logProbability$st + cv$accumulator);
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				if(state.fixedFlag$sample57)
					state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				state.fixedProbFlag$sample57 = (state.fixedFlag$sample57 && state.fixedFlag$sample20);
			}
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample57[((sample$var45 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			if(state.fixedFlag$sample57)
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample76() {
		if(!state.fixedProbFlag$sample76) {
			if(state.fixedFlag$sample76) {
				double cv$accumulator = 0.0;
				boolean cv$sampleReached = false;
				for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
					for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int index$timeStep$1 = timeStep$var66;
						int index$sample$2 = sample$var45;
						{
							{
								int cv$sampleValue = state.st[sample$var45][timeStep$var66];
								if(state.fixedFlag$sample57) {
									{
										for(int index$sample$4_1 = 0; index$sample$4_1 < state.noSamples; index$sample$4_1 += 1) {
											if((index$sample$4_1 == sample$var45)) {
												if((0 == (timeStep$var66 - 1))) {
													{
														for(int var32 = 0; var32 < state.noStates; var32 += 1) {
															if((var32 == state.st[sample$var45][(timeStep$var66 - 1)])) {
																{
																	double[] var72 = state.m[state.st[sample$var45][(timeStep$var66 - 1)]];
																	double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
											for(int index$sample57$6 = 0; index$sample57$6 < state.noStates; index$sample57$6 += 1) {
												int distributionTempVariable$var55$8 = index$sample57$6;
												double cv$probabilitySample57Value7 = (1.0 * state.distribution$sample57[((index$sample$5 - 0) / 1)][index$sample57$6]);
												{
													int traceTempVariable$var71$9_1 = distributionTempVariable$var55$8;
													if((index$sample$5 == sample$var45)) {
														if((0 == (timeStep$var66 - 1))) {
															{
																for(int var32 = 0; var32 < state.noStates; var32 += 1) {
																	if((var32 == traceTempVariable$var71$9_1)) {
																		{
																			double[] var72 = state.m[traceTempVariable$var71$9_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample57Value7) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value7);
																		}
																	}
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
									if((index$sample$2 == sample$var45)) {
										if((index$timeStep$1 == (timeStep$var66 - 1))) {
											{
												for(int var32 = 0; var32 < state.noStates; var32 += 1) {
													if((var32 == state.st[sample$var45][(timeStep$var66 - 1)])) {
														{
															double[] var72 = state.m[state.st[sample$var45][(timeStep$var66 - 1)]];
															double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
								if(state.fixedFlag$sample76) {
									{
										for(int index$sample$13_1 = 0; index$sample$13_1 < state.noSamples; index$sample$13_1 += 1) {
											for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < state.length$metric[index$sample$13_1][0]; index$timeStep$13_2 += 1) {
												if((index$sample$13_1 == sample$var45)) {
													if((index$timeStep$13_2 == (timeStep$var66 - 1))) {
														{
															for(int var32 = 0; var32 < state.noStates; var32 += 1) {
																if((var32 == state.st[sample$var45][(timeStep$var66 - 1)])) {
																	{
																		double[] var72 = state.m[state.st[sample$var45][(timeStep$var66 - 1)]];
																		double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
										for(int index$timeStep$15 = 1; index$timeStep$15 < state.length$metric[index$sample$14][0]; index$timeStep$15 += 1) {
											if(!((index$timeStep$15 == index$timeStep$1) && (index$sample$14 == index$sample$2))) {
												for(int index$sample76$16 = 0; index$sample76$16 < state.noStates; index$sample76$16 += 1) {
													int distributionTempVariable$var74$18 = index$sample76$16;
													double cv$probabilitySample76Value17 = (1.0 * state.distribution$sample76[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample76$16]);
													{
														int traceTempVariable$var71$19_1 = distributionTempVariable$var74$18;
														if((index$sample$14 == sample$var45)) {
															if((index$timeStep$15 == (timeStep$var66 - 1))) {
																{
																	for(int var32 = 0; var32 < state.noStates; var32 += 1) {
																		if((var32 == traceTempVariable$var71$19_1)) {
																			{
																				double[] var72 = state.m[traceTempVariable$var71$19_1];
																				double cv$weightedProbability = (Math.log(cv$probabilitySample76Value17) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value17);
																			}
																		}
																	}
																}
															}
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
						state.logProbability$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = cv$sampleProbability;
					}
				}
				if(state.fixedFlag$sample76)
					state.logProbability$st = (state.logProbability$st + cv$accumulator);
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				if(state.fixedFlag$sample76)
					state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				state.fixedProbFlag$sample76 = ((state.fixedFlag$sample76 && state.fixedFlag$sample33) && state.fixedFlag$sample57);
			}
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = state.logProbability$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			if(state.fixedFlag$sample76)
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample76)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample134() {
		if(!state.fixedProbFlag$sample134) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
				for(int var129 = 0; var129 < state.noStates; var129 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							double cv$sampleValue = state.current_metric_mean[var119][var129];
							{
								{
									double var106 = 0.0;
									double var107 = (double)state.max_metric;
									double cv$weightedProbability = (Math.log(1.0) + (((var106 <= cv$sampleValue) && (cv$sampleValue < var107))?(-Math.log((var107 - var106))):Double.NEGATIVE_INFINITY));
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
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			state.logProbability$var130 = cv$sampleAccumulator;
			state.logProbability$current_metric_mean = (state.logProbability$current_metric_mean + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample134)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample134 = state.fixedFlag$sample134;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
				for(int var129 = 0; var129 < state.noStates; var129 += 1)
					cv$sampleReached = true;
			}
			double cv$sampleValue = state.logProbability$var130;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$current_metric_mean = (state.logProbability$current_metric_mean + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample134)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample162() {
		if(!state.fixedProbFlag$sample162) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
				for(int var156 = 0; var156 < state.noStates; var156 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							double cv$sampleValue = state.current_metric_var[var146][var156];
							{
								{
									double var133 = 1.0;
									double var134 = 1.0;
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var133, var134));
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
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			state.logProbability$var157 = cv$sampleAccumulator;
			state.logProbability$current_metric_var = (state.logProbability$current_metric_var + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample162)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample162 = state.fixedFlag$sample162;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
				for(int var156 = 0; var156 < state.noStates; var156 += 1)
					cv$sampleReached = true;
			}
			double cv$sampleValue = state.logProbability$var157;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$current_metric_var = (state.logProbability$current_metric_var + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample162)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample190() {
		if(!state.fixedProbFlag$sample190) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var173 = 0; var173 < state.noServers; var173 += 1) {
				for(int var183 = 0; var183 < state.noStates; var183 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							double cv$sampleValue = state.current_metric_valid_bias[var173][var183];
							{
								{
									double var160 = 1.0;
									double var161 = 1.0;
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var160, var161));
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
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			state.logProbability$var184 = cv$sampleAccumulator;
			state.logProbability$current_metric_valid_bias = (state.logProbability$current_metric_valid_bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample190)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample190 = state.fixedFlag$sample190;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var173 = 0; var173 < state.noServers; var173 += 1) {
				for(int var183 = 0; var183 < state.noStates; var183 += 1)
					cv$sampleReached = true;
			}
			double cv$sampleValue = state.logProbability$var184;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$current_metric_valid_bias = (state.logProbability$current_metric_valid_bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample190)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample20() {
		if(!state.fixedProbFlag$sample20) {
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
			if(state.fixedFlag$sample20)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample20 = state.fixedFlag$sample20;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$initialStateDistribution;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample20)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample241() {
		if(!state.fixedProbFlag$sample241) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							{
								boolean cv$sampleValue = state.metric_valid_g[sample$var196][server][timeStep$var226];
								{
									{
										double var230 = state.current_metric_valid_bias[server][state.st[sample$var196][timeStep$var226]];
										double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((cv$sampleValue?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
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
						state.logProbability$sample241[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = cv$sampleProbability;
					}
				}
			}
			boolean cv$guard$metric_valid_g = false;
			state.logProbability$metric_valid_inner = (state.logProbability$metric_valid_inner + cv$accumulator);
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
			state.fixedProbFlag$sample241 = ((state.fixedFlag$sample57 && state.fixedFlag$sample76) && state.fixedFlag$sample190);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = state.logProbability$sample241[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$sampleReached = true;
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					}
				}
			}
			boolean cv$guard$metric_valid_g = false;
			state.logProbability$metric_valid_inner = (state.logProbability$metric_valid_inner + cv$accumulator);
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

	private final void logProbabilityValue$sample256() {
		if(!state.fixedProbFlag$sample256) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
							double cv$sampleAccumulator = 0.0;
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							double cv$probabilityReached = 0.0;
							{
								{
									double cv$sampleValue = state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
									{
										{
											double var241 = state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]];
											double var243 = state.current_metric_var[server][state.st[sample$var196][timeStep$var226]];
											double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))):Double.NEGATIVE_INFINITY));
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
							state.logProbability$sample256[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = cv$sampleProbability;
						}
					}
				}
			}
			boolean cv$guard$metric_g = false;
			state.logProbability$var245 = (state.logProbability$var245 + cv$accumulator);
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
			state.fixedProbFlag$sample256 = (((state.fixedFlag$sample57 && state.fixedFlag$sample76) && state.fixedFlag$sample134) && state.fixedFlag$sample162);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
							double cv$rvAccumulator = 0.0;
							double cv$sampleValue = state.logProbability$sample256[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
							cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
							cv$sampleReached = true;
							cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						}
					}
				}
			}
			boolean cv$guard$metric_g = false;
			state.logProbability$var245 = (state.logProbability$var245 + cv$accumulator);
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

	private final void logProbabilityValue$sample33() {
		if(!state.fixedProbFlag$sample33) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var32 = 0; var32 < state.noStates; var32 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double[] cv$sampleValue = state.m[var32];
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
			state.logProbability$var33 = cv$sampleAccumulator;
			state.logProbability$m = (state.logProbability$m + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample33)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample33 = state.fixedFlag$sample33;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var32 = 0; var32 < state.noStates; var32 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var33;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$m = (state.logProbability$m + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample33)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample57() {
		if(!state.fixedProbFlag$sample57) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$sample$1 = sample$var45;
				{
					{
						int cv$sampleValue = state.st[sample$var45][0];
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
				state.logProbability$sample57[((sample$var45 - 0) / 1)] = cv$sampleProbability;
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample57 = (state.fixedFlag$sample57 && state.fixedFlag$sample20);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample57[((sample$var45 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample76() {
		if(!state.fixedProbFlag$sample76) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$timeStep$1 = timeStep$var66;
					int index$sample$2 = sample$var45;
					{
						{
							int cv$sampleValue = state.st[sample$var45][timeStep$var66];
							{
								{
									double[] var72 = state.m[state.st[sample$var45][(timeStep$var66 - 1)]];
									double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					state.logProbability$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = cv$sampleProbability;
				}
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample76)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample76 = ((state.fixedFlag$sample76 && state.fixedFlag$sample33) && state.fixedFlag$sample57);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = state.logProbability$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample76)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		for(int var32 = 0; var32 < state.noStates; var32 += 1) {
			double[] var33 = state.m[var32];
			if(!state.fixedFlag$sample33)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, var33);
		}
		for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
			int[] var52 = state.st[sample$var45];
			if(!state.fixedFlag$sample57)
				var52[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
			int[] var67 = state.st[sample$var45];
			for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
				if(!state.fixedFlag$sample76)
					var67[timeStep$var66] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample$var45][(timeStep$var66 - 1)]], state.noStates);
			}
		}
		for(int var119 = 0; var119 < state.noServers; var119 += 1) {
			double[] var120 = state.current_metric_mean[var119];
			for(int var129 = 0; var129 < state.noStates; var129 += 1) {
				if(!state.fixedFlag$sample134)
					var120[var129] = (0.0 + (((double)state.max_metric - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			}
		}
		for(int var146 = 0; var146 < state.noServers; var146 += 1) {
			double[] var147 = state.current_metric_var[var146];
			for(int var156 = 0; var156 < state.noStates; var156 += 1) {
				if(!state.fixedFlag$sample162)
					var147[var156] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
			}
		}
		for(int var173 = 0; var173 < state.noServers; var173 += 1) {
			double[] var174 = state.current_metric_valid_bias[var173];
			for(int var183 = 0; var183 < state.noStates; var183 += 1) {
				if(!state.fixedFlag$sample190)
					var174[var183] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			}
		}
		for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
			boolean[][] var215 = state.metric_valid_g[sample$var196];
			double[][] var211 = state.metric_g[sample$var196];
			for(int server = 0; server < state.noServers; server += 1) {
				boolean[] metric_valid_inner = state.metric_valid_g[sample$var196][server];
				double[] metric_inner = var211[server];
				for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
					metric_valid_inner[timeStep$var226] = DistributionSampling.sampleBernoulli(state.RNG$, state.current_metric_valid_bias[server][state.st[sample$var196][timeStep$var226]]);
					if(metric_valid_inner[timeStep$var226]) {
						state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = ((Math.sqrt(state.current_metric_var[server][state.st[sample$var196][timeStep$var226]]) * DistributionSampling.sampleGaussian(state.RNG$)) + state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]]);
						metric_inner[timeStep$var226] = state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
					}
				}
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		for(int var32 = 0; var32 < state.noStates; var32 += 1) {
			double[] var33 = state.m[var32];
			if(!state.fixedFlag$sample33)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, var33);
		}
		for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
			double[] cv$distribution$sample57 = state.distribution$sample57[((sample$var45 - 0) / 1)];
			for(int index$var54 = 0; index$var54 < state.noStates; index$var54 += 1) {
				double cv$value = ((((((0.0 <= index$var54) && (index$var54 < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[index$var54])) && (state.initialStateDistribution[index$var54] <= 1.0))?state.initialStateDistribution[index$var54]:0.0);
				if(!state.fixedFlag$sample57)
					cv$distribution$sample57[index$var54] = cv$value;
			}
			for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
				double[] cv$distribution$sample76 = state.distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)];
				for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1) {
					if(!state.fixedFlag$sample76)
						cv$distribution$sample76[index$var73] = 0.0;
				}
				if(state.fixedFlag$sample57) {
					{
						for(int index$sample$1_1 = 0; index$sample$1_1 < state.noSamples; index$sample$1_1 += 1) {
							if((index$sample$1_1 == sample$var45)) {
								if((0 == (timeStep$var66 - 1))) {
									{
										for(int var32 = 0; var32 < state.noStates; var32 += 1) {
											if((var32 == state.st[sample$var45][(timeStep$var66 - 1)])) {
												{
													double[] var72 = state.m[state.st[sample$var45][(timeStep$var66 - 1)]];
													for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1) {
														if(!state.fixedFlag$sample76)
															cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (1.0 * ((((((0.0 <= index$var73) && (index$var73 < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[index$var73])) && (var72[index$var73] <= 1.0))?var72[index$var73]:0.0)));
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
							for(int index$sample57$3 = 0; index$sample57$3 < state.noStates; index$sample57$3 += 1) {
								int distributionTempVariable$var55$5 = index$sample57$3;
								double cv$probabilitySample57Value4 = (1.0 * state.distribution$sample57[((index$sample$2 - 0) / 1)][index$sample57$3]);
								{
									int traceTempVariable$var71$6_1 = distributionTempVariable$var55$5;
									if((index$sample$2 == sample$var45)) {
										if((0 == (timeStep$var66 - 1))) {
											{
												for(int var32 = 0; var32 < state.noStates; var32 += 1) {
													if((var32 == traceTempVariable$var71$6_1)) {
														{
															double[] var72 = state.m[traceTempVariable$var71$6_1];
															for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1) {
																if(!state.fixedFlag$sample76)
																	cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (cv$probabilitySample57Value4 * ((((((0.0 <= index$var73) && (index$var73 < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[index$var73])) && (var72[index$var73] <= 1.0))?var72[index$var73]:0.0)));
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if(state.fixedFlag$sample76) {
					{
						for(int index$sample$9_1 = 0; index$sample$9_1 < state.noSamples; index$sample$9_1 += 1) {
							for(int index$timeStep$9_2 = 1; index$timeStep$9_2 < state.length$metric[index$sample$9_1][0]; index$timeStep$9_2 += 1) {
								if((index$sample$9_1 == sample$var45)) {
									if((index$timeStep$9_2 == (timeStep$var66 - 1))) {
										{
											for(int var32 = 0; var32 < state.noStates; var32 += 1) {
												if((var32 == state.st[sample$var45][(timeStep$var66 - 1)])) {
													{
														double[] var72 = state.m[state.st[sample$var45][(timeStep$var66 - 1)]];
														for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1) {
															if(!state.fixedFlag$sample76)
																cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (1.0 * ((((((0.0 <= index$var73) && (index$var73 < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[index$var73])) && (var72[index$var73] <= 1.0))?var72[index$var73]:0.0)));
														}
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
						for(int index$timeStep$11 = 1; index$timeStep$11 < state.length$metric[index$sample$10][0]; index$timeStep$11 += 1) {
							if(true) {
								for(int index$sample76$12 = 0; index$sample76$12 < state.noStates; index$sample76$12 += 1) {
									int distributionTempVariable$var74$14 = index$sample76$12;
									double cv$probabilitySample76Value13 = (1.0 * state.distribution$sample76[((index$sample$10 - 0) / 1)][((index$timeStep$11 - 1) / 1)][index$sample76$12]);
									{
										int traceTempVariable$var71$15_1 = distributionTempVariable$var74$14;
										if((index$sample$10 == sample$var45)) {
											if((index$timeStep$11 == (timeStep$var66 - 1))) {
												{
													for(int var32 = 0; var32 < state.noStates; var32 += 1) {
														if((var32 == traceTempVariable$var71$15_1)) {
															{
																double[] var72 = state.m[traceTempVariable$var71$15_1];
																for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1) {
																	if(!state.fixedFlag$sample76)
																		cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (cv$probabilitySample76Value13 * ((((((0.0 <= index$var73) && (index$var73 < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[index$var73])) && (var72[index$var73] <= 1.0))?var72[index$var73]:0.0)));
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				double cv$var73$sum = 0.0;
				for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1) {
					if(!state.fixedFlag$sample76)
						cv$var73$sum = (cv$var73$sum + cv$distribution$sample76[index$var73]);
				}
				for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1) {
					if(!state.fixedFlag$sample76)
						cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] / cv$var73$sum);
				}
			}
		}
		for(int var119 = 0; var119 < state.noServers; var119 += 1) {
			double[] var120 = state.current_metric_mean[var119];
			for(int var129 = 0; var129 < state.noStates; var129 += 1) {
				if(!state.fixedFlag$sample134)
					var120[var129] = (0.0 + (((double)state.max_metric - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			}
		}
		for(int var146 = 0; var146 < state.noServers; var146 += 1) {
			double[] var147 = state.current_metric_var[var146];
			for(int var156 = 0; var156 < state.noStates; var156 += 1) {
				if(!state.fixedFlag$sample162)
					var147[var156] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
			}
		}
		for(int var173 = 0; var173 < state.noServers; var173 += 1) {
			double[] var174 = state.current_metric_valid_bias[var173];
			for(int var183 = 0; var183 < state.noStates; var183 += 1) {
				if(!state.fixedFlag$sample190)
					var174[var183] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			}
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		for(int var32 = 0; var32 < state.noStates; var32 += 1) {
			double[] var33 = state.m[var32];
			if(!state.fixedFlag$sample33)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, var33);
		}
		for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
			int[] var52 = state.st[sample$var45];
			if(!state.fixedFlag$sample57)
				var52[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
			int[] var67 = state.st[sample$var45];
			for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
				if(!state.fixedFlag$sample76)
					var67[timeStep$var66] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample$var45][(timeStep$var66 - 1)]], state.noStates);
			}
		}
		for(int var119 = 0; var119 < state.noServers; var119 += 1) {
			double[] var120 = state.current_metric_mean[var119];
			for(int var129 = 0; var129 < state.noStates; var129 += 1) {
				if(!state.fixedFlag$sample134)
					var120[var129] = (0.0 + (((double)state.max_metric - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			}
		}
		for(int var146 = 0; var146 < state.noServers; var146 += 1) {
			double[] var147 = state.current_metric_var[var146];
			for(int var156 = 0; var156 < state.noStates; var156 += 1) {
				if(!state.fixedFlag$sample162)
					var147[var156] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
			}
		}
		for(int var173 = 0; var173 < state.noServers; var173 += 1) {
			double[] var174 = state.current_metric_valid_bias[var173];
			for(int var183 = 0; var183 < state.noStates; var183 += 1) {
				if(!state.fixedFlag$sample190)
					var174[var183] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			}
		}
		for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
			boolean[][] var215 = state.metric_valid_g[sample$var196];
			double[][] var211 = state.metric_g[sample$var196];
			for(int server = 0; server < state.noServers; server += 1) {
				boolean[] metric_valid_inner = state.metric_valid_g[sample$var196][server];
				double[] metric_inner = var211[server];
				for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
					metric_valid_inner[timeStep$var226] = DistributionSampling.sampleBernoulli(state.RNG$, state.current_metric_valid_bias[server][state.st[sample$var196][timeStep$var226]]);
					if(metric_valid_inner[timeStep$var226]) {
						state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = ((Math.sqrt(state.current_metric_var[server][state.st[sample$var196][timeStep$var226]]) * DistributionSampling.sampleGaussian(state.RNG$)) + state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]]);
						metric_inner[timeStep$var226] = state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
					}
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		for(int var32 = 0; var32 < state.noStates; var32 += 1) {
			double[] var33 = state.m[var32];
			if(!state.fixedFlag$sample33)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, var33);
		}
		for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
			int[] var52 = state.st[sample$var45];
			if(!state.fixedFlag$sample57)
				var52[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
			int[] var67 = state.st[sample$var45];
			for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
				if(!state.fixedFlag$sample76)
					var67[timeStep$var66] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample$var45][(timeStep$var66 - 1)]], state.noStates);
			}
		}
		for(int var119 = 0; var119 < state.noServers; var119 += 1) {
			double[] var120 = state.current_metric_mean[var119];
			for(int var129 = 0; var129 < state.noStates; var129 += 1) {
				if(!state.fixedFlag$sample134)
					var120[var129] = (0.0 + (((double)state.max_metric - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			}
		}
		for(int var146 = 0; var146 < state.noServers; var146 += 1) {
			double[] var147 = state.current_metric_var[var146];
			for(int var156 = 0; var156 < state.noStates; var156 += 1) {
				if(!state.fixedFlag$sample162)
					var147[var156] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
			}
		}
		for(int var173 = 0; var173 < state.noServers; var173 += 1) {
			double[] var174 = state.current_metric_valid_bias[var173];
			for(int var183 = 0; var183 < state.noStates; var183 += 1) {
				if(!state.fixedFlag$sample190)
					var174[var183] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		for(int var32 = 0; var32 < state.noStates; var32 += 1) {
			double[] var33 = state.m[var32];
			if(!state.fixedFlag$sample33)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, var33);
		}
		for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
			int[] var52 = state.st[sample$var45];
			if(!state.fixedFlag$sample57)
				var52[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
			int[] var67 = state.st[sample$var45];
			for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
				if(!state.fixedFlag$sample76)
					var67[timeStep$var66] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample$var45][(timeStep$var66 - 1)]], state.noStates);
			}
		}
		for(int var119 = 0; var119 < state.noServers; var119 += 1) {
			double[] var120 = state.current_metric_mean[var119];
			for(int var129 = 0; var129 < state.noStates; var129 += 1) {
				if(!state.fixedFlag$sample134)
					var120[var129] = (0.0 + (((double)state.max_metric - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			}
		}
		for(int var146 = 0; var146 < state.noServers; var146 += 1) {
			double[] var147 = state.current_metric_var[var146];
			for(int var156 = 0; var156 < state.noStates; var156 += 1) {
				if(!state.fixedFlag$sample162)
					var147[var156] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
			}
		}
		for(int var173 = 0; var173 < state.noServers; var173 += 1) {
			double[] var174 = state.current_metric_valid_bias[var173];
			for(int var183 = 0; var183 < state.noStates; var183 += 1) {
				if(!state.fixedFlag$sample190)
					var174[var183] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample20)
				inferSample20();
			for(int var32 = 0; var32 < state.noStates; var32 += 1) {
				if(!state.fixedFlag$sample33)
					inferSample33(var32);
			}
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				if(!state.fixedFlag$sample57)
					inferSample57(sample$var45);
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
					if(!state.fixedFlag$sample76)
						inferSample76(sample$var45, timeStep$var66);
				}
			}
			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
				for(int var129 = 0; var129 < state.noStates; var129 += 1) {
					if(!state.fixedFlag$sample134)
						inferSample134(var119, var129);
				}
			}
			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
				for(int var156 = 0; var156 < state.noStates; var156 += 1) {
					if(!state.fixedFlag$sample162)
						inferSample162(var146, var156);
				}
			}
			for(int var173 = 0; var173 < state.noServers; var173 += 1) {
				for(int var183 = 0; var183 < state.noStates; var183 += 1) {
					if(!state.fixedFlag$sample190)
						inferSample190(var173, var183);
				}
			}
		} else {
			for(int var173 = (state.noServers - ((((state.noServers - 1) - 0) % 1) + 1)); var173 >= ((0 - 1) + 1); var173 -= 1) {
				for(int var183 = (state.noStates - ((((state.noStates - 1) - 0) % 1) + 1)); var183 >= ((0 - 1) + 1); var183 -= 1) {
					if(!state.fixedFlag$sample190)
						inferSample190(var173, var183);
				}
			}
			for(int var146 = (state.noServers - ((((state.noServers - 1) - 0) % 1) + 1)); var146 >= ((0 - 1) + 1); var146 -= 1) {
				for(int var156 = (state.noStates - ((((state.noStates - 1) - 0) % 1) + 1)); var156 >= ((0 - 1) + 1); var156 -= 1) {
					if(!state.fixedFlag$sample162)
						inferSample162(var146, var156);
				}
			}
			for(int var119 = (state.noServers - ((((state.noServers - 1) - 0) % 1) + 1)); var119 >= ((0 - 1) + 1); var119 -= 1) {
				for(int var129 = (state.noStates - ((((state.noStates - 1) - 0) % 1) + 1)); var129 >= ((0 - 1) + 1); var129 -= 1) {
					if(!state.fixedFlag$sample134)
						inferSample134(var119, var129);
				}
			}
			for(int sample$var45 = (state.noSamples - ((((state.noSamples - 1) - 0) % 1) + 1)); sample$var45 >= ((0 - 1) + 1); sample$var45 -= 1) {
				for(int timeStep$var66 = (state.length$metric[sample$var45][0] - ((((state.length$metric[sample$var45][0] - 1) - 1) % 1) + 1)); timeStep$var66 >= ((1 - 1) + 1); timeStep$var66 -= 1) {
					if(!state.fixedFlag$sample76)
						inferSample76(sample$var45, timeStep$var66);
				}
				if(!state.fixedFlag$sample57)
					inferSample57(sample$var45);
			}
			for(int var32 = (state.noStates - ((((state.noStates - 1) - 0) % 1) + 1)); var32 >= ((0 - 1) + 1); var32 -= 1) {
				if(!state.fixedFlag$sample33)
					inferSample33(var32);
			}
			if(!state.fixedFlag$sample20)
				inferSample20();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample20)
			drawValueSample20();
		for(int var32 = 0; var32 < state.noStates; var32 += 1) {
			if(!state.constrainedFlag$sample33[((var32 - 0) / 1)])
				drawValueSample33(var32);
		}
		for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
			if(!state.constrainedFlag$sample57[((sample$var45 - 0) / 1)])
				drawValueSample57(sample$var45);
			for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
				if(!state.constrainedFlag$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)])
					drawValueSample76(sample$var45, timeStep$var66);
			}
		}
		for(int var119 = 0; var119 < state.noServers; var119 += 1) {
			for(int var129 = 0; var129 < state.noStates; var129 += 1) {
				if(!state.constrainedFlag$sample134[((var119 - 0) / 1)][((var129 - 0) / 1)])
					drawValueSample134(var119, var129);
			}
		}
		for(int var146 = 0; var146 < state.noServers; var146 += 1) {
			for(int var156 = 0; var156 < state.noStates; var156 += 1) {
				if(!state.constrainedFlag$sample162[((var146 - 0) / 1)][((var156 - 0) / 1)])
					drawValueSample162(var146, var156);
			}
		}
		for(int var173 = 0; var173 < state.noServers; var173 += 1) {
			for(int var183 = 0; var183 < state.noStates; var183 += 1) {
				if(!state.constrainedFlag$sample190[((var173 - 0) / 1)][((var183 - 0) / 1)])
					drawValueSample190(var173, var183);
			}
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample20)
			state.logProbability$initialStateDistribution = Double.NaN;
		state.logProbability$m = 0.0;
		if(!state.fixedProbFlag$sample33)
			state.logProbability$var33 = Double.NaN;
		state.logProbability$st = 0.0;
		if(!state.fixedProbFlag$sample57) {
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1)
				state.logProbability$sample57[((sample$var45 - 0) / 1)] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample76) {
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1)
					state.logProbability$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = Double.NaN;
			}
		}
		state.logProbability$current_metric_mean = 0.0;
		if(!state.fixedProbFlag$sample134)
			state.logProbability$var130 = Double.NaN;
		state.logProbability$current_metric_var = 0.0;
		if(!state.fixedProbFlag$sample162)
			state.logProbability$var157 = Double.NaN;
		state.logProbability$current_metric_valid_bias = 0.0;
		if(!state.fixedProbFlag$sample190)
			state.logProbability$var184 = Double.NaN;
		state.logProbability$metric_valid_inner = 0.0;
		state.logProbability$metric_valid_g = 0.0;
		if(!state.fixedProbFlag$sample241) {
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1)
						state.logProbability$sample241[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = Double.NaN;
				}
			}
		}
		state.logProbability$var245 = 0.0;
		state.logProbability$metric_g = 0.0;
		if(!state.fixedProbFlag$sample256) {
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1)
						state.logProbability$sample256[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = Double.NaN;
				}
			}
		}
	}

	@Override
	public final void initializeModel() {
		state.noSamples = state.length$metric.length;
		for(int var16 = 0; var16 < state.noStates; var16 += 1)
			state.v[var16] = 0.1;
		state.noServers = state.length$metric[0].length;
		for(int index$constrainedFlag$sample190$1 = 0; index$constrainedFlag$sample190$1 < state.constrainedFlag$sample190.length; index$constrainedFlag$sample190$1 += 1) {
			boolean[] cv$constrainedFlag$sample190$1 = state.constrainedFlag$sample190[index$constrainedFlag$sample190$1];
			for(int index$constrainedFlag$sample190$2 = 0; index$constrainedFlag$sample190$2 < cv$constrainedFlag$sample190$1.length; index$constrainedFlag$sample190$2 += 1)
				cv$constrainedFlag$sample190$1[index$constrainedFlag$sample190$2] = true;
		}
		for(int index$constrainedFlag$sample76$1 = 0; index$constrainedFlag$sample76$1 < state.constrainedFlag$sample76.length; index$constrainedFlag$sample76$1 += 1) {
			boolean[] cv$constrainedFlag$sample76$1 = state.constrainedFlag$sample76[index$constrainedFlag$sample76$1];
			for(int index$constrainedFlag$sample76$2 = 0; index$constrainedFlag$sample76$2 < cv$constrainedFlag$sample76$1.length; index$constrainedFlag$sample76$2 += 1)
				cv$constrainedFlag$sample76$1[index$constrainedFlag$sample76$2] = true;
		}
		for(int index$constrainedFlag$sample57$1 = 0; index$constrainedFlag$sample57$1 < state.constrainedFlag$sample57.length; index$constrainedFlag$sample57$1 += 1)
			state.constrainedFlag$sample57[index$constrainedFlag$sample57$1] = true;
		for(int index$constrainedFlag$sample134$1 = 0; index$constrainedFlag$sample134$1 < state.constrainedFlag$sample134.length; index$constrainedFlag$sample134$1 += 1) {
			boolean[] cv$constrainedFlag$sample134$1 = state.constrainedFlag$sample134[index$constrainedFlag$sample134$1];
			for(int index$constrainedFlag$sample134$2 = 0; index$constrainedFlag$sample134$2 < cv$constrainedFlag$sample134$1.length; index$constrainedFlag$sample134$2 += 1)
				cv$constrainedFlag$sample134$1[index$constrainedFlag$sample134$2] = true;
		}
		for(int index$constrainedFlag$sample162$1 = 0; index$constrainedFlag$sample162$1 < state.constrainedFlag$sample162.length; index$constrainedFlag$sample162$1 += 1) {
			boolean[] cv$constrainedFlag$sample162$1 = state.constrainedFlag$sample162[index$constrainedFlag$sample162$1];
			for(int index$constrainedFlag$sample162$2 = 0; index$constrainedFlag$sample162$2 < cv$constrainedFlag$sample162$1.length; index$constrainedFlag$sample162$2 += 1)
				cv$constrainedFlag$sample162$1[index$constrainedFlag$sample162$2] = true;
		}
		for(int index$constrainedFlag$sample33$1 = 0; index$constrainedFlag$sample33$1 < state.constrainedFlag$sample33.length; index$constrainedFlag$sample33$1 += 1)
			state.constrainedFlag$sample33[index$constrainedFlag$sample33$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample20)
			logProbabilityValue$sample20();
		if(state.fixedFlag$sample33)
			logProbabilityValue$sample33();
		if(state.fixedFlag$sample134)
			logProbabilityValue$sample134();
		if(state.fixedFlag$sample162)
			logProbabilityValue$sample162();
		if(state.fixedFlag$sample190)
			logProbabilityValue$sample190();
		logProbabilityValue$sample241();
		logProbabilityValue$sample256();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample33();
		logProbabilityDistribution$sample57();
		logProbabilityDistribution$sample76();
		logProbabilityValue$sample134();
		logProbabilityValue$sample162();
		logProbabilityValue$sample190();
		logProbabilityDistribution$sample241();
		logProbabilityDistribution$sample256();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample33();
		logProbabilityValue$sample57();
		logProbabilityValue$sample76();
		logProbabilityValue$sample134();
		logProbabilityValue$sample162();
		logProbabilityValue$sample190();
		logProbabilityValue$sample241();
		logProbabilityValue$sample256();
	}

	@Override
	public final void propogateObservedValues() {
		{
			boolean[][][] cv$source1 = state.metric_valid;
			boolean[][][] cv$target1 = state.metric_valid_g;
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
				boolean[][] cv$source2 = cv$source1[cv$index1];
				boolean[][] cv$target2 = cv$target1[cv$index1];
				int cv$length2 = cv$target2.length;
				for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1) {
					boolean[] cv$source3 = cv$source2[cv$index2];
					boolean[] cv$target3 = cv$target2[cv$index2];
					int cv$length3 = cv$target3.length;
					for(int cv$index3 = 0; cv$index3 < cv$length3; cv$index3 += 1)
						cv$target3[cv$index3] = cv$source3[cv$index3];
				}
			}
		}
		{
			double[][][] cv$source1 = state.metric;
			double[][][] cv$target1 = state.metric_g;
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
				double[][] cv$source2 = cv$source1[cv$index1];
				double[][] cv$target2 = cv$target1[cv$index1];
				int cv$length2 = cv$target2.length;
				for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1) {
					double[] cv$source3 = cv$source2[cv$index2];
					double[] cv$target3 = cv$target2[cv$index2];
					int cv$length3 = cv$target3.length;
					for(int cv$index3 = 0; cv$index3 < cv$length3; cv$index3 += 1)
						cv$target3[cv$index3] = cv$source3[cv$index3];
				}
			}
		}
		for(int sample$var196 = (state.noSamples - ((((state.noSamples - 1) - 0) % 1) + 1)); sample$var196 >= ((0 - 1) + 1); sample$var196 -= 1) {
			for(int server = (state.noServers - ((((state.noServers - 1) - 0) % 1) + 1)); server >= ((0 - 1) + 1); server -= 1) {
				for(int timeStep$var226 = (state.length$metric[sample$var196][0] - ((((state.length$metric[sample$var196][0] - 1) - 0) % 1) + 1)); timeStep$var226 >= ((0 - 1) + 1); timeStep$var226 -= 1) {
					if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
						{
							{
								{
									{
										double[][] var211;
										var211 = state.metric_g[sample$var196];
										double[] metric_inner;
										metric_inner = state.metric_g[sample$var196][server];
										state.var245[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = metric_inner[timeStep$var226];
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
		     + "model HMMMetrics4(\n"
		     + "               double[][][] metric,\n"
		     + "               boolean[][][] metric_valid, \n"
		     + "               int max_metric,\n"
		     + "               int noStates) {\n"
		     + "    \n"
		     + "    int noSamples = metric.length;\n"
		     + "\n"
		     + "    // Construct arrays describing the probability of a move from 1 state to another.\n"
		     + "    double[] v = new double[noStates] <~ 0.1;\n"
		     + "    double[] initialStateDistribution = dirichlet(v).sample();\n"
		     + "    double[][] m = dirichlet(v).sample(noStates);\n"
		     + "    \n"
		     + "    //Calculate all the state transitions\n"
		     + "    int[][] st = new int[noSamples][];\n"
		     + "    for(int sample = 0; sample < noSamples; sample++) {\n"
		     + "        int streamLength = metric[sample][0].length;\n"
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
		     + "    }\n"
		     + "    \n"
		     + "    // Calculate the number of servers\n"
		     + "    int noServers = metric[0].length;    \n"
		     + "    \n"
		     + "    // Allocate space for each generated metric.    \n"
		     + "    double[][][] metric_g = new double[noSamples][noServers][];\n"
		     + "    boolean[][][] metric_valid_g = new boolean[noSamples][noServers][];\n"
		     + "\n"
		     + "    // Calculate metric parameters\n"
		     + "    double[][] current_metric_mean = uniform(0.0, (double) max_metric).sample(noServers, noStates);\n"
		     + "    double[][] current_metric_var = inverseGamma(1.0, 1.0).sample(noServers, noStates);\n"
		     + "    double[][] current_metric_valid_bias = beta(1.0, 1.0).sample(noServers, noStates);\n"
		     + "    \n"
		     + "    // Compute the values of each metric\n"
		     + "    for(int sample = 0; sample < noSamples; sample++) {\n"
		     + "        int streamLength = metric[sample][0].length;\n"
		     + "        for(int server = 0; server < noServers; server++) {\n"
		     + "            //Allocate space for the time series\n"
		     + "            double[] metric_inner = new double[streamLength];\n"
		     + "            metric_g[sample][server] = metric_inner;\n"
		     + "            \n"
		     + "            boolean[] metric_valid_inner = new boolean[streamLength];\n"
		     + "            metric_valid_g[sample][server] = metric_valid_inner;\n"
		     + "            \n"
		     + "            //Generate values.\n"
		     + "            for(int timeStep = 0; timeStep < streamLength; timeStep++){\n"
		     + "                int currentState = st[sample][timeStep];\n"
		     + "                \n"
		     + "                metric_valid_inner[timeStep] = bernoulli(current_metric_valid_bias[server][currentState]).sample();\n"
		     + "                if(metric_valid_inner[timeStep])\n"
		     + "                    metric_inner[timeStep] = gaussian(current_metric_mean[server][currentState], current_metric_var[server][currentState]).sample();\n"
		     + "            }\n"
		     + "        }\n"
		     + "    }\n"
		     + "\n"
		     + "    //Tie the values to the measured values.\n"
		     + "    metric_valid_g.observe(metric_valid);\n"
		     + "    metric_g.observe(metric);\n"
		     + "}";
	}
}