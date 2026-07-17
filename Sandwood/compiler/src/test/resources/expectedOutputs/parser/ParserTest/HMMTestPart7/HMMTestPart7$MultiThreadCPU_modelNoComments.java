package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMMTestPart7$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMMTestPart7.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMTestPart7$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$distributionAccumulator$var69;
		double[][] cv$var28$countGlobal;
		double[] cv$var52$stateProbabilityGlobal;
		double[] cv$var70$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			{
				{
					int cv$threadCount = threadCount();
					cv$var28$countGlobal = new double[cv$threadCount][];
					for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
						cv$var28$countGlobal[cv$index] = new double[5];
				}
			}
			{
				int cv$var29$max = 5;
				cv$distributionAccumulator$var69 = new double[cv$var29$max];
			}
			{
				int cv$var29$max = 5;
				cv$var52$stateProbabilityGlobal = new double[cv$var29$max];
			}
			{
				int cv$var29$max = 5;
				cv$var70$stateProbabilityGlobal = new double[cv$var29$max];
			}
		}
	}


	public HMMTestPart7$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample28(int var27, int threadID$cv$var27, Rng RNG$) {
		double[] var28 = state.m[var27];
		DistributionSampling.sampleDirichlet(RNG$, state.v, state.states, var28);
	}

	private final void drawValueSample45(int var43, int threadID$cv$var43, Rng RNG$) {
		state.bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	private final void drawValueSample53() {
		state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
	}

	private final void drawValueSample71(int i$var64) {
		int index$i$1 = i$var64;
		state.st[i$var64] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var64 - 1)]], state.states);
	}

	private final void inferSample28(int var27, int threadID$cv$var27, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample28[((var27 - 0) / 1)] = false;
			double[] cv$targetLocal = state.m[var27];
			double[] cv$countLocal = scratch.cv$var28$countGlobal[threadID$cv$var27];
			int cv$arrayLength = state.states;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							if((var27 == 0)) {
								if(state.fixedFlag$sample53) {
									{
										{
											boolean cv$sampleConstrained = (state.fixedFlag$sample53 || state.constrainedFlag$sample53);
											if(cv$sampleConstrained) {
												state.constrainedFlag$sample28[((var27 - 0) / 1)] = true;
												{
													{
														{
															{
																{
																	cv$countLocal[state.st[0]] = (cv$countLocal[state.st[0]] + 1.0);
																}
															}
														}
													}
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
						for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
							if(state.fixedFlag$sample53) {
								{
									if((0 == (i$var64 - 1))) {
										{
											if((var27 == state.st[(i$var64 - 1)])) {
												if(state.fixedFlag$sample71) {
													{
														{
															int index$i$22 = i$var64;
															boolean cv$sampleConstrained = (state.fixedFlag$sample71 || state.constrainedFlag$sample71[((i$var64 - 1) / 1)]);
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample28[((var27 - 0) / 1)] = true;
																{
																	{
																		{
																			{
																				{
																					cv$countLocal[state.st[i$var64]] = (cv$countLocal[state.st[i$var64]] + 1.0);
																				}
																			}
																		}
																	}
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
								if(true) {
									for(int index$sample53$6 = 0; index$sample53$6 < state.states; index$sample53$6 += 1) {
										int distributionTempVariable$var52$8 = index$sample53$6;
										double cv$probabilitySample53Value7 = (1.0 * state.distribution$sample53[index$sample53$6]);
										{
											int traceTempVariable$var67$9_1 = distributionTempVariable$var52$8;
											if((0 == (i$var64 - 1))) {
												{
													if((var27 == traceTempVariable$var67$9_1)) {
														if(state.fixedFlag$sample71) {
															{
																{
																	int index$i$24 = i$var64;
																	boolean cv$sampleConstrained = (state.fixedFlag$sample71 || state.constrainedFlag$sample71[((i$var64 - 1) / 1)]);
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample28[((var27 - 0) / 1)] = true;
																		{
																			{
																				{
																					{
																						{
																							cv$countLocal[state.st[i$var64]] = (cv$countLocal[state.st[i$var64]] + cv$probabilitySample53Value7);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
							if(state.fixedFlag$sample71) {
								{
									for(int index$i$13_1 = 1; index$i$13_1 < state.samples; index$i$13_1 += 1) {
										if((index$i$13_1 == (i$var64 - 1))) {
											{
												if((var27 == state.st[(i$var64 - 1)])) {
													if(state.fixedFlag$sample71) {
														{
															{
																int index$i$26 = i$var64;
																boolean cv$sampleConstrained = (state.fixedFlag$sample71 || state.constrainedFlag$sample71[((i$var64 - 1) / 1)]);
																if(cv$sampleConstrained) {
																	state.constrainedFlag$sample28[((var27 - 0) / 1)] = true;
																	{
																		{
																			{
																				{
																					{
																						cv$countLocal[state.st[i$var64]] = (cv$countLocal[state.st[i$var64]] + 1.0);
																					}
																				}
																			}
																		}
																	}
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
								for(int index$i$14 = 1; index$i$14 < state.samples; index$i$14 += 1) {
									if(true) {
										for(int index$sample71$15 = 0; index$sample71$15 < state.states; index$sample71$15 += 1) {
											int distributionTempVariable$var70$17 = index$sample71$15;
											double cv$probabilitySample71Value16 = (1.0 * state.distribution$sample71[((index$i$14 - 1) / 1)][index$sample71$15]);
											{
												int traceTempVariable$var67$18_1 = distributionTempVariable$var70$17;
												if((index$i$14 == (i$var64 - 1))) {
													{
														if((var27 == traceTempVariable$var67$18_1)) {
															if(state.fixedFlag$sample71) {
																{
																	{
																		int index$i$28 = i$var64;
																		boolean cv$sampleConstrained = (state.fixedFlag$sample71 || state.constrainedFlag$sample71[((i$var64 - 1) / 1)]);
																		if(cv$sampleConstrained) {
																			state.constrainedFlag$sample28[((var27 - 0) / 1)] = true;
																			{
																				{
																					{
																						{
																							{
																								cv$countLocal[state.st[i$var64]] = (cv$countLocal[state.st[i$var64]] + cv$probabilitySample71Value16);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
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
						if((var27 == 0)) {
							if(!state.fixedFlag$sample53) {
								{
									{
										{
											{
												double scopeVariable$reachedSourceProbability = 0.0;
												{
													scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
												double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
												for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
													cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample53[cv$loopIndex] * cv$distributionProbability));
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
					for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
						if(state.fixedFlag$sample53) {
							{
								if((0 == (i$var64 - 1))) {
									{
										if((var27 == state.st[(i$var64 - 1)])) {
											if(!state.fixedFlag$sample71) {
												{
													{
														int index$i$53 = i$var64;
														{
															{
																double scopeVariable$reachedSourceProbability = 0.0;
																{
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
																double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																	cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample71[((i$var64 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
							if(true) {
								for(int index$sample53$37 = 0; index$sample53$37 < state.states; index$sample53$37 += 1) {
									int distributionTempVariable$var52$39 = index$sample53$37;
									double cv$probabilitySample53Value38 = (1.0 * state.distribution$sample53[index$sample53$37]);
									{
										int traceTempVariable$var67$40_1 = distributionTempVariable$var52$39;
										if((0 == (i$var64 - 1))) {
											{
												if((var27 == traceTempVariable$var67$40_1)) {
													if(!state.fixedFlag$sample71) {
														{
															{
																int index$i$55 = i$var64;
																{
																	{
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample53Value38);
																		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																			cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample71[((i$var64 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
						if(state.fixedFlag$sample71) {
							{
								for(int index$i$44_1 = 1; index$i$44_1 < state.samples; index$i$44_1 += 1) {
									if((index$i$44_1 == (i$var64 - 1))) {
										{
											if((var27 == state.st[(i$var64 - 1)])) {
												if(!state.fixedFlag$sample71) {
													{
														{
															int index$i$57 = i$var64;
															{
																{
																	double scopeVariable$reachedSourceProbability = 0.0;
																	{
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																	for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																		cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample71[((i$var64 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																}
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
							for(int index$i$45 = 1; index$i$45 < state.samples; index$i$45 += 1) {
								if(true) {
									for(int index$sample71$46 = 0; index$sample71$46 < state.states; index$sample71$46 += 1) {
										int distributionTempVariable$var70$48 = index$sample71$46;
										double cv$probabilitySample71Value47 = (1.0 * state.distribution$sample71[((index$i$45 - 1) / 1)][index$sample71$46]);
										{
											int traceTempVariable$var67$49_1 = distributionTempVariable$var70$48;
											if((index$i$45 == (i$var64 - 1))) {
												{
													if((var27 == traceTempVariable$var67$49_1)) {
														if(!state.fixedFlag$sample71) {
															{
																{
																	int index$i$59 = i$var64;
																	{
																		{
																			double scopeVariable$reachedSourceProbability = 0.0;
																			{
																				scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																			}
																			double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample71Value47);
																			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																				cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample71[((i$var64 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(state.constrainedFlag$sample28[((var27 - 0) / 1)])
				Conjugates.sampleConjugateDirichletCategorical(RNG$, state.v, cv$countLocal, cv$targetLocal, state.states);
		}
	}

	private final void inferSample45(int var43, int threadID$cv$var43, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample45[((var43 - 0) / 1)] = false;
			double cv$sum = 0.0;
			double cv$count = 0.0;
			{
				{
					{
						for(int j = 0; j < state.samples; j += 1) {
							if(state.fixedFlag$sample53) {
								{
									if((0 == j)) {
										{
											if((var43 == state.st[j])) {
												{
													{
														boolean cv$sampleConstrained = true;
														if(cv$sampleConstrained) {
															state.constrainedFlag$sample45[((var43 - 0) / 1)] = true;
															{
																{
																	{
																		{
																			{
																				cv$count = (cv$count + 1.0);
																				if(state.flips[j])
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
							} else {
								if(true) {
									for(int index$sample53$3 = 0; index$sample53$3 < state.states; index$sample53$3 += 1) {
										int distributionTempVariable$var52$5 = index$sample53$3;
										double cv$probabilitySample53Value4 = (1.0 * state.distribution$sample53[index$sample53$3]);
										{
											int traceTempVariable$var83$6_1 = distributionTempVariable$var52$5;
											if((0 == j)) {
												{
													if((var43 == traceTempVariable$var83$6_1)) {
														{
															{
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	state.constrainedFlag$sample45[((var43 - 0) / 1)] = true;
																	{
																		{
																			{
																				{
																					{
																						cv$count = (cv$count + cv$probabilitySample53Value4);
																						if(state.flips[j])
																							cv$sum = (cv$sum + cv$probabilitySample53Value4);
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						for(int j = 0; j < state.samples; j += 1) {
							if(state.fixedFlag$sample71) {
								{
									for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
										if((i$var64 == j)) {
											{
												if((var43 == state.st[j])) {
													{
														{
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample45[((var43 - 0) / 1)] = true;
																{
																	{
																		{
																			{
																				{
																					cv$count = (cv$count + 1.0);
																					if(state.flips[j])
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
							} else {
								for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
									if(true) {
										for(int index$sample71$12 = 0; index$sample71$12 < state.states; index$sample71$12 += 1) {
											int distributionTempVariable$var70$14 = index$sample71$12;
											double cv$probabilitySample71Value13 = (1.0 * state.distribution$sample71[((i$var64 - 1) / 1)][index$sample71$12]);
											{
												int traceTempVariable$var83$15_1 = distributionTempVariable$var70$14;
												if((i$var64 == j)) {
													{
														if((var43 == traceTempVariable$var83$15_1)) {
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample45[((var43 - 0) / 1)] = true;
																		{
																			{
																				{
																					{
																						{
																							cv$count = (cv$count + cv$probabilitySample71Value13);
																							if(state.flips[j])
																								cv$sum = (cv$sum + cv$probabilitySample71Value13);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(state.constrainedFlag$sample45[((var43 - 0) / 1)]) {
				double var44 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
				{
					{
						{
							state.bias[var43] = var44;
						}
					}
				}
			}
		}
	}

	private final void inferSample53() {
		if(true) {
			state.constrainedFlag$sample53 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, state.states);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var52$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] var50 = state.m[0];
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.states)) && (0 < state.states)) && (0.0 <= var50[cv$currentValue])) && (var50[cv$currentValue] <= 1.0))?Math.log(var50[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$var67$1_1 = cv$currentValue;
								for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
									if((0 == (i$var64 - 1))) {
										if(state.fixedFlag$sample71) {
											{
												{
													int index$i$3 = i$var64;
													boolean cv$sampleConstrained = (state.fixedFlag$sample71 || state.constrainedFlag$sample71[((i$var64 - 1) / 1)]);
													if(cv$sampleConstrained) {
														state.constrainedFlag$sample53 = true;
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															{
																for(int var27 = 0; var27 < state.states; var27 += 1) {
																	if((var27 == traceTempVariable$var67$1_1)) {
																		{
																			{
																				{
																					double[] var68 = state.m[traceTempVariable$var67$1_1];
																					if(((Math.log(1.0) + ((((((0.0 <= state.st[i$var64]) && (state.st[i$var64] < state.states)) && (0 < state.states)) && (0.0 <= var68[state.st[i$var64]])) && (var68[state.st[i$var64]] <= 1.0))?Math.log(var68[state.st[i$var64]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.st[i$var64]) && (state.st[i$var64] < state.states)) && (0 < state.states)) && (0.0 <= var68[state.st[i$var64]])) && (var68[state.st[i$var64]] <= 1.0))?Math.log(var68[state.st[i$var64]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.st[i$var64]) && (state.st[i$var64] < state.states)) && (0 < state.states)) && (0.0 <= var68[state.st[i$var64]])) && (var68[state.st[i$var64]] <= 1.0))?Math.log(var68[state.st[i$var64]]):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.st[i$var64]) && (state.st[i$var64] < state.states)) && (0 < state.states)) && (0.0 <= var68[state.st[i$var64]])) && (var68[state.st[i$var64]] <= 1.0))?Math.log(var68[state.st[i$var64]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.st[i$var64]) && (state.st[i$var64] < state.states)) && (0 < state.states)) && (0.0 <= var68[state.st[i$var64]])) && (var68[state.st[i$var64]] <= 1.0))?Math.log(var68[state.st[i$var64]]):Double.NEGATIVE_INFINITY)));
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
					{
						{
							{
								int traceTempVariable$var83$6_1 = cv$currentValue;
								for(int j = 0; j < state.samples; j += 1) {
									if((0 == j)) {
										{
											{
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													state.constrainedFlag$sample53 = true;
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															for(int var43 = 0; var43 < state.states; var43 += 1) {
																if((var43 == traceTempVariable$var83$6_1)) {
																	{
																		{
																			{
																				double var84 = state.bias[traceTempVariable$var83$6_1];
																				if(((Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)));
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
							int traceTempVariable$var67$10_1 = cv$currentValue;
							for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
								if((0 == (i$var64 - 1))) {
									if(!state.fixedFlag$sample71) {
										{
											{
												int index$i$12 = i$var64;
												double[] cv$accumulatedConsumerDistributions = scratch.cv$distributionAccumulator$var69;
												for(int cv$i = 0; cv$i < state.states; cv$i += 1)
													cv$accumulatedConsumerDistributions[cv$i] = 0.0;
												double cv$reachedDistributionProbability = 0.0;
												{
													for(int var27 = 0; var27 < state.states; var27 += 1) {
														if((var27 == traceTempVariable$var67$10_1)) {
															{
																double scopeVariable$reachedSourceProbability = 0.0;
																{
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
																double[] var68 = state.m[traceTempVariable$var67$10_1];
																double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
																DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, var68, state.states);
															}
														}
													}
												}
												double[] cv$sampleDistribution = state.distribution$sample71[((i$var64 - 1) / 1)];
												double cv$overlap = 0.0;
												for(int cv$i = 0; cv$i < state.states; cv$i += 1) {
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
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample53) {
				double[] cv$localProbability = state.distribution$sample53;
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

	private final void inferSample71(int i$var64) {
		int index$i$1 = i$var64;
		if(true) {
			state.constrainedFlag$sample71[((i$var64 - 1) / 1)] = false;
			int cv$numStates = 0;
			if(state.fixedFlag$sample53) {
				{
					if((0 == (i$var64 - 1))) {
						{
							for(int var27 = 0; var27 < state.states; var27 += 1) {
								if((var27 == state.st[(i$var64 - 1)]))
									cv$numStates = Math.max(cv$numStates, state.states);
							}
						}
					}
				}
			} else {
				if(true) {
					for(int index$sample53$3 = 0; index$sample53$3 < state.states; index$sample53$3 += 1) {
						int distributionTempVariable$var52$5 = index$sample53$3;
						double cv$probabilitySample53Value4 = (1.0 * state.distribution$sample53[index$sample53$3]);
						{
							int traceTempVariable$var67$6_1 = distributionTempVariable$var52$5;
							if((0 == (i$var64 - 1))) {
								{
									for(int var27 = 0; var27 < state.states; var27 += 1) {
										if((var27 == traceTempVariable$var67$6_1))
											cv$numStates = Math.max(cv$numStates, state.states);
									}
								}
							}
						}
					}
				}
			}
			{
				if((index$i$1 == (i$var64 - 1))) {
					{
						for(int var27 = 0; var27 < state.states; var27 += 1) {
							if((var27 == state.st[(i$var64 - 1)]))
								cv$numStates = Math.max(cv$numStates, state.states);
						}
					}
				}
			}
			for(int index$i$10 = 1; index$i$10 < state.samples; index$i$10 += 1) {
				if(!(index$i$10 == index$i$1)) {
					for(int index$sample71$11 = 0; index$sample71$11 < state.states; index$sample71$11 += 1) {
						int distributionTempVariable$var70$13 = index$sample71$11;
						double cv$probabilitySample71Value12 = (1.0 * state.distribution$sample71[((index$i$10 - 1) / 1)][index$sample71$11]);
						{
							int traceTempVariable$var67$14_1 = distributionTempVariable$var70$13;
							if((index$i$10 == (i$var64 - 1))) {
								{
									for(int var27 = 0; var27 < state.states; var27 += 1) {
										if((var27 == traceTempVariable$var67$14_1))
											cv$numStates = Math.max(cv$numStates, state.states);
									}
								}
							}
						}
					}
				}
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var70$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				if(state.fixedFlag$sample53) {
					{
						if((0 == (i$var64 - 1))) {
							{
								for(int var27 = 0; var27 < state.states; var27 += 1) {
									if((var27 == state.st[(i$var64 - 1)])) {
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
										double[] var68 = state.m[state.st[(i$var64 - 1)]];
										double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.states)) && (0 < state.states)) && (0.0 <= var68[cv$currentValue])) && (var68[cv$currentValue] <= 1.0))?Math.log(var68[cv$currentValue]):Double.NEGATIVE_INFINITY));
										{
											{
												{
													int traceTempVariable$var67$32_1 = cv$currentValue;
												}
											}
										}
										{
											{
												{
													int traceTempVariable$var83$36_1 = cv$currentValue;
													for(int j = 0; j < state.samples; j += 1) {
														if((i$var64 == j)) {
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample71[((i$var64 - 1) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				for(int var43 = 0; var43 < state.states; var43 += 1) {
																					if((var43 == traceTempVariable$var83$36_1)) {
																						{
																							{
																								{
																									double var84 = state.bias[traceTempVariable$var83$36_1];
																									if(((Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)));
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
				} else {
					if(true) {
						for(int index$sample53$18 = 0; index$sample53$18 < state.states; index$sample53$18 += 1) {
							int distributionTempVariable$var52$20 = index$sample53$18;
							double cv$probabilitySample53Value19 = (1.0 * state.distribution$sample53[index$sample53$18]);
							{
								int traceTempVariable$var67$21_1 = distributionTempVariable$var52$20;
								if((0 == (i$var64 - 1))) {
									{
										for(int var27 = 0; var27 < state.states; var27 += 1) {
											if((var27 == traceTempVariable$var67$21_1)) {
												cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample53Value19);
												double[] var68 = state.m[traceTempVariable$var67$21_1];
												double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample53Value19) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.states)) && (0 < state.states)) && (0.0 <= var68[cv$currentValue])) && (var68[cv$currentValue] <= 1.0))?Math.log(var68[cv$currentValue]):Double.NEGATIVE_INFINITY));
												{
													{
														{
															int traceTempVariable$var67$33_1 = cv$currentValue;
														}
													}
												}
												{
													{
														{
															int traceTempVariable$var83$37_1 = cv$currentValue;
															for(int j = 0; j < state.samples; j += 1) {
																if((i$var64 == j)) {
																	{
																		{
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				state.constrainedFlag$sample71[((i$var64 - 1) / 1)] = true;
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						for(int var43 = 0; var43 < state.states; var43 += 1) {
																							if((var43 == traceTempVariable$var83$37_1)) {
																								{
																									{
																										{
																											double var84 = state.bias[traceTempVariable$var83$37_1];
																											if(((Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)));
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
				{
					int traceTempVariable$var67$24_1 = cv$currentValue;
					if((index$i$1 == (i$var64 - 1))) {
						{
							for(int var27 = 0; var27 < state.states; var27 += 1) {
								if((var27 == traceTempVariable$var67$24_1)) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
									double[] var68 = state.m[traceTempVariable$var67$24_1];
									double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.states)) && (0 < state.states)) && (0.0 <= var68[cv$currentValue])) && (var68[cv$currentValue] <= 1.0))?Math.log(var68[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											{
												int traceTempVariable$var67$34_1 = cv$currentValue;
											}
										}
									}
									{
										{
											{
												int traceTempVariable$var83$38_1 = cv$currentValue;
												for(int j = 0; j < state.samples; j += 1) {
													if((i$var64 == j)) {
														{
															{
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	state.constrainedFlag$sample71[((i$var64 - 1) / 1)] = true;
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			for(int var43 = 0; var43 < state.states; var43 += 1) {
																				if((var43 == traceTempVariable$var83$38_1)) {
																					{
																						{
																							{
																								double var84 = state.bias[traceTempVariable$var83$38_1];
																								if(((Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)));
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
				for(int index$i$25 = 1; index$i$25 < state.samples; index$i$25 += 1) {
					if(!(index$i$25 == index$i$1)) {
						for(int index$sample71$26 = 0; index$sample71$26 < state.states; index$sample71$26 += 1) {
							int distributionTempVariable$var70$28 = index$sample71$26;
							double cv$probabilitySample71Value27 = (1.0 * state.distribution$sample71[((index$i$25 - 1) / 1)][index$sample71$26]);
							{
								int traceTempVariable$var67$29_1 = distributionTempVariable$var70$28;
								if((index$i$25 == (i$var64 - 1))) {
									{
										for(int var27 = 0; var27 < state.states; var27 += 1) {
											if((var27 == traceTempVariable$var67$29_1)) {
												cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample71Value27);
												double[] var68 = state.m[traceTempVariable$var67$29_1];
												double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample71Value27) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.states)) && (0 < state.states)) && (0.0 <= var68[cv$currentValue])) && (var68[cv$currentValue] <= 1.0))?Math.log(var68[cv$currentValue]):Double.NEGATIVE_INFINITY));
												{
													{
														{
															int traceTempVariable$var67$35_1 = distributionTempVariable$var70$28;
														}
													}
												}
												{
													{
														{
															int traceTempVariable$var83$39_1 = distributionTempVariable$var70$28;
															for(int j = 0; j < state.samples; j += 1) {
																if((i$var64 == j)) {
																	{
																		{
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				state.constrainedFlag$sample71[((i$var64 - 1) / 1)] = true;
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						for(int var43 = 0; var43 < state.states; var43 += 1) {
																							if((var43 == traceTempVariable$var83$39_1)) {
																								{
																									{
																										{
																											double var84 = state.bias[traceTempVariable$var83$39_1];
																											if(((Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)));
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
				{
					{
						{
							int traceTempVariable$var67$52_1 = cv$currentValue;
							for(int index$i$52_2 = 1; index$i$52_2 < state.samples; index$i$52_2 += 1) {
								if((i$var64 == (index$i$52_2 - 1))) {
									{
										{
											int index$i$54 = index$i$52_2;
											double[] cv$accumulatedConsumerDistributions = scratch.cv$distributionAccumulator$var69;
											for(int cv$i = 0; cv$i < state.states; cv$i += 1)
												cv$accumulatedConsumerDistributions[cv$i] = 0.0;
											double cv$reachedDistributionProbability = 0.0;
											{
												for(int var27 = 0; var27 < state.states; var27 += 1) {
													if((var27 == traceTempVariable$var67$52_1)) {
														{
															double scopeVariable$reachedSourceProbability = 0.0;
															if(state.fixedFlag$sample53) {
																{
																	if((0 == (i$var64 - 1))) {
																		{
																			for(int index$var27$61_1 = 0; index$var27$61_1 < state.states; index$var27$61_1 += 1) {
																				if((index$var27$61_1 == state.st[(i$var64 - 1)]))
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample53$57 = 0; index$sample53$57 < state.states; index$sample53$57 += 1) {
																		int distributionTempVariable$var52$59 = index$sample53$57;
																		double cv$probabilitySample53Value58 = (1.0 * state.distribution$sample53[index$sample53$57]);
																		{
																			int traceTempVariable$var67$60_1 = distributionTempVariable$var52$59;
																			if((0 == (i$var64 - 1))) {
																				{
																					for(int index$var27$62_1 = 0; index$var27$62_1 < state.states; index$var27$62_1 += 1) {
																						if((index$var27$62_1 == traceTempVariable$var67$60_1))
																							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample53Value58);
																					}
																				}
																			}
																		}
																	}
																}
															}
															{
																int traceTempVariable$var67$63_1 = cv$currentValue;
																if((index$i$1 == (i$var64 - 1))) {
																	{
																		for(int index$var27$69_1 = 0; index$var27$69_1 < state.states; index$var27$69_1 += 1) {
																			if((index$var27$69_1 == traceTempVariable$var67$63_1))
																				scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																	}
																}
															}
															for(int index$i$64 = 1; index$i$64 < state.samples; index$i$64 += 1) {
																if((!(index$i$64 == index$i$1) && !(index$i$64 == index$i$54))) {
																	for(int index$sample71$65 = 0; index$sample71$65 < state.states; index$sample71$65 += 1) {
																		int distributionTempVariable$var70$67 = index$sample71$65;
																		double cv$probabilitySample71Value66 = (1.0 * state.distribution$sample71[((index$i$64 - 1) / 1)][index$sample71$65]);
																		{
																			int traceTempVariable$var67$68_1 = distributionTempVariable$var70$67;
																			if((index$i$64 == (i$var64 - 1))) {
																				{
																					for(int index$var27$70_1 = 0; index$var27$70_1 < state.states; index$var27$70_1 += 1) {
																						if((index$var27$70_1 == traceTempVariable$var67$68_1))
																							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample71Value66);
																					}
																				}
																			}
																		}
																	}
																}
															}
															double[] var68 = state.m[traceTempVariable$var67$52_1];
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
															cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
															DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, var68, state.states);
														}
													}
												}
											}
											double[] cv$sampleDistribution = state.distribution$sample71[((index$i$52_2 - 1) / 1)];
											double cv$overlap = 0.0;
											for(int cv$i = 0; cv$i < state.states; cv$i += 1) {
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
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample71[((i$var64 - 1) / 1)]) {
				double[] cv$localProbability = state.distribution$sample71[((i$var64 - 1) / 1)];
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

	private final void logProbabilityDistribution$sample53() {
		if(!state.fixedProbFlag$sample53) {
			if(state.fixedFlag$sample53) {
				double cv$accumulator = 0.0;
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = state.st[0];
						{
							{
								double[] var50 = state.m[0];
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var50[cv$sampleValue])) && (var50[cv$sampleValue] <= 1.0))?Math.log(var50[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				state.logProbability$var52 = cv$sampleProbability;
				if(state.fixedFlag$sample53)
					state.logProbability$st = (state.logProbability$st + cv$accumulator);
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				if(state.fixedFlag$sample53)
					state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				state.fixedProbFlag$sample53 = (state.fixedFlag$sample53 && state.fixedFlag$sample28);
			}
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$var52;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(state.fixedFlag$sample53)
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample53)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample71() {
		if(!state.fixedProbFlag$sample71) {
			if(state.fixedFlag$sample71) {
				double cv$accumulator = 0.0;
				boolean cv$sampleReached = false;
				for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$i$1 = i$var64;
					{
						{
							int cv$sampleValue = state.st[i$var64];
							if(state.fixedFlag$sample53) {
								{
									if((0 == (i$var64 - 1))) {
										{
											for(int var27 = 0; var27 < state.states; var27 += 1) {
												if((var27 == state.st[(i$var64 - 1)])) {
													{
														double[] var68 = state.m[state.st[(i$var64 - 1)]];
														double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
							} else {
								if(true) {
									for(int index$sample53$4 = 0; index$sample53$4 < state.states; index$sample53$4 += 1) {
										int distributionTempVariable$var52$6 = index$sample53$4;
										double cv$probabilitySample53Value5 = (1.0 * state.distribution$sample53[index$sample53$4]);
										{
											int traceTempVariable$var67$7_1 = distributionTempVariable$var52$6;
											if((0 == (i$var64 - 1))) {
												{
													for(int var27 = 0; var27 < state.states; var27 += 1) {
														if((var27 == traceTempVariable$var67$7_1)) {
															{
																double[] var68 = state.m[traceTempVariable$var67$7_1];
																double cv$weightedProbability = (Math.log(cv$probabilitySample53Value5) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample53Value5);
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
								if((index$i$1 == (i$var64 - 1))) {
									{
										for(int var27 = 0; var27 < state.states; var27 += 1) {
											if((var27 == state.st[(i$var64 - 1)])) {
												{
													double[] var68 = state.m[state.st[(i$var64 - 1)]];
													double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
							if(state.fixedFlag$sample71) {
								{
									for(int index$i$11_1 = 1; index$i$11_1 < state.samples; index$i$11_1 += 1) {
										if((index$i$11_1 == (i$var64 - 1))) {
											{
												for(int var27 = 0; var27 < state.states; var27 += 1) {
													if((var27 == state.st[(i$var64 - 1)])) {
														{
															double[] var68 = state.m[state.st[(i$var64 - 1)]];
															double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
							} else {
								for(int index$i$12 = 1; index$i$12 < state.samples; index$i$12 += 1) {
									if(!(index$i$12 == index$i$1)) {
										for(int index$sample71$13 = 0; index$sample71$13 < state.states; index$sample71$13 += 1) {
											int distributionTempVariable$var70$15 = index$sample71$13;
											double cv$probabilitySample71Value14 = (1.0 * state.distribution$sample71[((index$i$12 - 1) / 1)][index$sample71$13]);
											{
												int traceTempVariable$var67$16_1 = distributionTempVariable$var70$15;
												if((index$i$12 == (i$var64 - 1))) {
													{
														for(int var27 = 0; var27 < state.states; var27 += 1) {
															if((var27 == traceTempVariable$var67$16_1)) {
																{
																	double[] var68 = state.m[traceTempVariable$var67$16_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample71Value14) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value14);
																}
															}
														}
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
					state.logProbability$sample71[((i$var64 - 1) / 1)] = cv$sampleProbability;
				}
				if(state.fixedFlag$sample71)
					state.logProbability$st = (state.logProbability$st + cv$accumulator);
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				if(state.fixedFlag$sample71)
					state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				state.fixedProbFlag$sample71 = ((state.fixedFlag$sample71 && state.fixedFlag$sample28) && state.fixedFlag$sample53);
			}
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample71[((i$var64 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			if(state.fixedFlag$sample71)
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample71)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample87() {
		if(!state.fixedProbFlag$sample87) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.samples; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.flips[j];
						if(state.fixedFlag$sample53) {
							{
								if((0 == j)) {
									{
										for(int var43 = 0; var43 < state.states; var43 += 1) {
											if((var43 == state.st[j])) {
												{
													double var84 = state.bias[state.st[j]];
													double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((cv$sampleValue?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
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
						} else {
							if(true) {
								for(int index$sample53$3 = 0; index$sample53$3 < state.states; index$sample53$3 += 1) {
									int distributionTempVariable$var52$5 = index$sample53$3;
									double cv$probabilitySample53Value4 = (1.0 * state.distribution$sample53[index$sample53$3]);
									{
										int traceTempVariable$var83$6_1 = distributionTempVariable$var52$5;
										if((0 == j)) {
											{
												for(int var43 = 0; var43 < state.states; var43 += 1) {
													if((var43 == traceTempVariable$var83$6_1)) {
														{
															double var84 = state.bias[traceTempVariable$var83$6_1];
															double cv$weightedProbability = (Math.log(cv$probabilitySample53Value4) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((cv$sampleValue?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample53Value4);
														}
													}
												}
											}
										}
									}
								}
							}
						}
						if(state.fixedFlag$sample71) {
							{
								for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
									if((i$var64 == j)) {
										{
											for(int var43 = 0; var43 < state.states; var43 += 1) {
												if((var43 == state.st[j])) {
													{
														double var84 = state.bias[state.st[j]];
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((cv$sampleValue?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
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
						} else {
							for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
								if(true) {
									for(int index$sample71$11 = 0; index$sample71$11 < state.states; index$sample71$11 += 1) {
										int distributionTempVariable$var70$13 = index$sample71$11;
										double cv$probabilitySample71Value12 = (1.0 * state.distribution$sample71[((i$var64 - 1) / 1)][index$sample71$11]);
										{
											int traceTempVariable$var83$14_1 = distributionTempVariable$var70$13;
											if((i$var64 == j)) {
												{
													for(int var43 = 0; var43 < state.states; var43 += 1) {
														if((var43 == traceTempVariable$var83$14_1)) {
															{
																double var84 = state.bias[traceTempVariable$var83$14_1];
																double cv$weightedProbability = (Math.log(cv$probabilitySample71Value12) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((cv$sampleValue?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value12);
															}
														}
													}
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
				state.logProbability$sample87[((j - 0) / 1)] = cv$sampleProbability;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample87 = ((state.fixedFlag$sample45 && state.fixedFlag$sample53) && state.fixedFlag$sample71);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.samples; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample87[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!state.fixedProbFlag$sample28) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var27 = 0; var27 < state.states; var27 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double[] cv$sampleValue = state.m[var27];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, state.v, state.states));
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
			state.logProbability$var28 = cv$sampleAccumulator;
			state.logProbability$m = (state.logProbability$m + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample28 = state.fixedFlag$sample28;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var27 = 0; var27 < state.states; var27 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var28;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$m = (state.logProbability$m + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!state.fixedProbFlag$sample45) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var43 = 0; var43 < state.states; var43 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.bias[var43];
						{
							{
								double var30 = 1.0;
								double var31 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var30, var31));
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
			state.logProbability$var44 = cv$sampleAccumulator;
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample45)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample45 = state.fixedFlag$sample45;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var43 = 0; var43 < state.states; var43 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var44;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample45)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!state.fixedProbFlag$sample53) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = state.st[0];
					{
						{
							double[] var50 = state.m[0];
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var50[cv$sampleValue])) && (var50[cv$sampleValue] <= 1.0))?Math.log(var50[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$var52 = cv$sampleProbability;
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample53)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample53 = (state.fixedFlag$sample53 && state.fixedFlag$sample28);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$var52;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample53)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample71() {
		if(!state.fixedProbFlag$sample71) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$i$1 = i$var64;
				{
					{
						int cv$sampleValue = state.st[i$var64];
						{
							{
								double[] var68 = state.m[state.st[(i$var64 - 1)]];
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample71[((i$var64 - 1) / 1)] = cv$sampleProbability;
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample71)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample71 = ((state.fixedFlag$sample71 && state.fixedFlag$sample28) && state.fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample71[((i$var64 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample71)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample87() {
		if(!state.fixedProbFlag$sample87) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.samples; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.flips[j];
						{
							{
								double var84 = state.bias[state.st[j]];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((cv$sampleValue?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample87[((j - 0) / 1)] = cv$sampleProbability;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample87 = ((state.fixedFlag$sample45 && state.fixedFlag$sample53) && state.fixedFlag$sample71);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.samples; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample87[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(state.RNG$, 0, state.states, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = state.m[var27];
						if(!state.fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.states, var28);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.states, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!state.fixedFlag$sample45)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
		for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
			if(!state.fixedFlag$sample71)
				state.st[i$var64] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var64 - 1)]], state.states);
		}
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						state.flips[j] = DistributionSampling.sampleBernoulli(RNG$1, state.bias[state.st[j]]);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		parallelFor(state.RNG$, 0, state.states, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = state.m[var27];
						if(!state.fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.states, var28);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.states, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!state.fixedFlag$sample45)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		double[] cv$distribution$sample53 = state.distribution$sample53;
		double[] var50 = state.m[0];
		for(int index$var51 = 0; index$var51 < state.states; index$var51 += 1) {
			double cv$value = ((((((0.0 <= index$var51) && (index$var51 < state.states)) && (0 < state.states)) && (0.0 <= var50[index$var51])) && (var50[index$var51] <= 1.0))?var50[index$var51]:0.0);
			if(!state.fixedFlag$sample53)
				cv$distribution$sample53[index$var51] = cv$value;
		}
		for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
			double[] cv$distribution$sample71 = state.distribution$sample71[((i$var64 - 1) / 1)];
			for(int index$var69 = 0; index$var69 < state.states; index$var69 += 1) {
				if(!state.fixedFlag$sample71)
					cv$distribution$sample71[index$var69] = 0.0;
			}
			if(state.fixedFlag$sample53) {
				{
					if((0 == (i$var64 - 1))) {
						{
							for(int var27 = 0; var27 < state.states; var27 += 1) {
								if((var27 == state.st[(i$var64 - 1)])) {
									{
										double[] var68 = state.m[state.st[(i$var64 - 1)]];
										for(int index$var69 = 0; index$var69 < state.states; index$var69 += 1) {
											if(!state.fixedFlag$sample71)
												cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (1.0 * ((((((0.0 <= index$var69) && (index$var69 < state.states)) && (0 < state.states)) && (0.0 <= var68[index$var69])) && (var68[index$var69] <= 1.0))?var68[index$var69]:0.0)));
										}
									}
								}
							}
						}
					}
				}
			} else {
				if(true) {
					for(int index$sample53$2 = 0; index$sample53$2 < state.states; index$sample53$2 += 1) {
						int distributionTempVariable$var52$4 = index$sample53$2;
						double cv$probabilitySample53Value3 = (1.0 * state.distribution$sample53[index$sample53$2]);
						{
							int traceTempVariable$var67$5_1 = distributionTempVariable$var52$4;
							if((0 == (i$var64 - 1))) {
								{
									for(int var27 = 0; var27 < state.states; var27 += 1) {
										if((var27 == traceTempVariable$var67$5_1)) {
											{
												double[] var68 = state.m[traceTempVariable$var67$5_1];
												for(int index$var69 = 0; index$var69 < state.states; index$var69 += 1) {
													if(!state.fixedFlag$sample71)
														cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (cv$probabilitySample53Value3 * ((((((0.0 <= index$var69) && (index$var69 < state.states)) && (0 < state.states)) && (0.0 <= var68[index$var69])) && (var68[index$var69] <= 1.0))?var68[index$var69]:0.0)));
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(state.fixedFlag$sample71) {
				{
					for(int index$i$8_1 = 1; index$i$8_1 < state.samples; index$i$8_1 += 1) {
						if((index$i$8_1 == (i$var64 - 1))) {
							{
								for(int var27 = 0; var27 < state.states; var27 += 1) {
									if((var27 == state.st[(i$var64 - 1)])) {
										{
											double[] var68 = state.m[state.st[(i$var64 - 1)]];
											for(int index$var69 = 0; index$var69 < state.states; index$var69 += 1) {
												if(!state.fixedFlag$sample71)
													cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (1.0 * ((((((0.0 <= index$var69) && (index$var69 < state.states)) && (0 < state.states)) && (0.0 <= var68[index$var69])) && (var68[index$var69] <= 1.0))?var68[index$var69]:0.0)));
											}
										}
									}
								}
							}
						}
					}
				}
			} else {
				for(int index$i$9 = 1; index$i$9 < state.samples; index$i$9 += 1) {
					if(true) {
						for(int index$sample71$10 = 0; index$sample71$10 < state.states; index$sample71$10 += 1) {
							int distributionTempVariable$var70$12 = index$sample71$10;
							double cv$probabilitySample71Value11 = (1.0 * state.distribution$sample71[((index$i$9 - 1) / 1)][index$sample71$10]);
							{
								int traceTempVariable$var67$13_1 = distributionTempVariable$var70$12;
								if((index$i$9 == (i$var64 - 1))) {
									{
										for(int var27 = 0; var27 < state.states; var27 += 1) {
											if((var27 == traceTempVariable$var67$13_1)) {
												{
													double[] var68 = state.m[traceTempVariable$var67$13_1];
													for(int index$var69 = 0; index$var69 < state.states; index$var69 += 1) {
														if(!state.fixedFlag$sample71)
															cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (cv$probabilitySample71Value11 * ((((((0.0 <= index$var69) && (index$var69 < state.states)) && (0 < state.states)) && (0.0 <= var68[index$var69])) && (var68[index$var69] <= 1.0))?var68[index$var69]:0.0)));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			double cv$var69$sum = 0.0;
			for(int index$var69 = 0; index$var69 < state.states; index$var69 += 1) {
				if(!state.fixedFlag$sample71)
					cv$var69$sum = (cv$var69$sum + cv$distribution$sample71[index$var69]);
			}
			for(int index$var69 = 0; index$var69 < state.states; index$var69 += 1) {
				if(!state.fixedFlag$sample71)
					cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] / cv$var69$sum);
			}
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		parallelFor(state.RNG$, 0, state.states, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = state.m[var27];
						if(!state.fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.states, var28);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.states, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!state.fixedFlag$sample45)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
		for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
			if(!state.fixedFlag$sample71)
				state.st[i$var64] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var64 - 1)]], state.states);
		}
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						state.flips[j] = DistributionSampling.sampleBernoulli(RNG$1, state.bias[state.st[j]]);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(state.RNG$, 0, state.states, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = state.m[var27];
						if(!state.fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.states, var28);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.states, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!state.fixedFlag$sample45)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
		for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
			if(!state.fixedFlag$sample71)
				state.st[i$var64] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var64 - 1)]], state.states);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		parallelFor(state.RNG$, 0, state.states, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = state.m[var27];
						if(!state.fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.states, var28);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.states, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!state.fixedFlag$sample45)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
		for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
			if(!state.fixedFlag$sample71)
				state.st[i$var64] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var64 - 1)]], state.states);
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			parallelFor(state.RNG$, 0, state.states, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
							if(!state.fixedFlag$sample28)
								inferSample28(var27, threadID$var27, RNG$1);
						}
				}
			);
			parallelFor(state.RNG$, 0, state.states, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
							if(!state.fixedFlag$sample45)
								inferSample45(var43, threadID$var43, RNG$1);
						}
				}
			);
			if(!state.fixedFlag$sample53)
				inferSample53();
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
				if(!state.fixedFlag$sample71)
					inferSample71(i$var64);
			}
		} else {
			for(int i$var64 = (state.samples - ((((state.samples - 1) - 1) % 1) + 1)); i$var64 >= ((1 - 1) + 1); i$var64 -= 1) {
				if(!state.fixedFlag$sample71)
					inferSample71(i$var64);
			}
			if(!state.fixedFlag$sample53)
				inferSample53();
			parallelFor(state.RNG$, 0, state.states, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
							if(!state.fixedFlag$sample45)
								inferSample45(var43, threadID$var43, RNG$1);
						}
				}
			);
			parallelFor(state.RNG$, 0, state.states, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
							if(!state.fixedFlag$sample28)
								inferSample28(var27, threadID$var27, RNG$1);
						}
				}
			);
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		parallelFor(state.RNG$, 0, state.states, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						if(!state.constrainedFlag$sample28[((var27 - 0) / 1)])
							drawValueSample28(var27, threadID$var27, RNG$1);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.states, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!state.constrainedFlag$sample45[((var43 - 0) / 1)])
							drawValueSample45(var43, threadID$var43, RNG$1);
					}
			}
		);
		if(!state.constrainedFlag$sample53)
			drawValueSample53();
		for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
			if(!state.constrainedFlag$sample71[((i$var64 - 1) / 1)])
				drawValueSample71(i$var64);
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$m = 0.0;
		if(!state.fixedProbFlag$sample28)
			state.logProbability$var28 = Double.NaN;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample45)
			state.logProbability$var44 = Double.NaN;
		state.logProbability$st = 0.0;
		if(!state.fixedProbFlag$sample53)
			state.logProbability$var52 = Double.NaN;
		if(!state.fixedProbFlag$sample71) {
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1)
				state.logProbability$sample71[((i$var64 - 1) / 1)] = Double.NaN;
		}
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample87) {
			for(int j = 0; j < state.samples; j += 1)
				state.logProbability$sample87[((j - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.states = 5;
		for(int i$var13 = 0; i$var13 < 5; i$var13 += 1)
			state.v[i$var13] = 0.1;
		state.samples = state.length$flipsMeasured;
		for(int index$constrainedFlag$sample45$1 = 0; index$constrainedFlag$sample45$1 < state.constrainedFlag$sample45.length; index$constrainedFlag$sample45$1 += 1)
			state.constrainedFlag$sample45[index$constrainedFlag$sample45$1] = true;
		for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < state.constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
			state.constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
		for(int index$constrainedFlag$sample71$1 = 0; index$constrainedFlag$sample71$1 < state.constrainedFlag$sample71.length; index$constrainedFlag$sample71$1 += 1)
			state.constrainedFlag$sample71[index$constrainedFlag$sample71$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(state.fixedFlag$sample45)
			logProbabilityValue$sample45();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityDistribution$sample53();
		logProbabilityDistribution$sample71();
		logProbabilityDistribution$sample87();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample71();
		logProbabilityValue$sample87();
	}

	@Override
	public final void propagateObservedValues() {
		boolean[] cv$source1 = state.flipsMeasured;
		boolean[] cv$target1 = state.flips;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

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
		     + "model HMMTestPart7(boolean[] flipsMeasured) {\n"
		     + "        int states = 5;\n"
		     + "\n"
		     + "        double[] v = new double[states];\n"
		     + "        for(int i:[0..states))\n"
		     + "            v[i] = 0.1;\n"
		     + "        \n"
		     + "        double[][] m = dirichlet(v).sample(states);\n"
		     + "        double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "        int samples = flipsMeasured.length;\n"
		     + "        int[] st = new int[samples];\n"
		     + "\n"
		     + "        st[0] = categorical(m[0]).sampleDistribution();\n"
		     + "\n"
		     + "        for(int i:[1..samples))\n"
		     + "            st[i] = categorical(m[st[i-1]]).sampleDistribution();\n"
		     + "            \n"
		     + "        boolean[] flips = new boolean[samples];\n"
		     + "            \n"
		     + "        for(int j:[0..samples))\n"
		     + "            flips[j] = bernoulli(bias[st[j]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}