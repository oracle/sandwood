package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMM_Mk2$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMM_Mk2.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMM_Mk2$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var123$stateProbabilityGlobal;
		double[] cv$var42$countGlobal;
		double[] cv$var56$countGlobal;
		double[] cv$var75$countGlobal;
		double[] cv$var77$stateProbabilityGlobal;
		double[] cv$var92$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			{
				cv$var42$countGlobal = new double[state.noStates];
			}
			{
				cv$var56$countGlobal = new double[state.noEvents];
			}
			{
				cv$var75$countGlobal = new double[state.noStates];
			}
			{
				cv$var77$stateProbabilityGlobal = new double[state.noStates];
			}
			{
				int cv$var43$max = state.noStates;
				cv$var92$stateProbabilityGlobal = new double[cv$var43$max];
			}
			{
				int cv$var43$max = state.noStates;
				cv$var123$stateProbabilityGlobal = new double[cv$var43$max];
			}
		}
	}


	public HMM_Mk2$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample126(int i$var104, int j$var115) {
		int[] var116 = state.st[i$var104];
		var116[j$var115] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[i$var104][(j$var115 - 1)]], state.noStates);
	}

	private final void drawValueSample42(int var41) {
		double[] var42 = state.m[var41];
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, var42);
	}

	private final void drawValueSample57(int var55) {
		double[] var56 = state.bias[var55];
		DistributionSampling.sampleDirichlet(state.RNG$, state.v2, state.noEvents, var56);
	}

	private final void drawValueSample78() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.weights);
	}

	private final void drawValueSample80() {
		state.initialState = DistributionSampling.sampleCategorical(state.RNG$, state.weights, state.noStates);
	}

	private final void drawValueSample95(int i$var87) {
		int[] var88 = state.st[i$var87];
		var88[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.initialState], state.noStates);
	}

	private final void inferSample126(int i$var104, int j$var115) {
		if(true) {
			state.constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, state.noStates);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var123$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				int var123 = cv$currentValue;
				{
					{
						{
							int[] var116 = state.st[i$var104];
							var116[j$var115] = cv$currentValue;
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] var121 = state.m[state.st[i$var104][(j$var115 - 1)]];
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var121[cv$currentValue])) && (var121[cv$currentValue] <= 1.0))?Math.log(var121[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$var120$2_1 = cv$currentValue;
								for(int index$i$2_2 = 0; index$i$2_2 < state.samples; index$i$2_2 += 1) {
									if((i$var104 == index$i$2_2)) {
										for(int index$j$2_3 = 1; index$j$2_3 < state.length$eventsMeasured[index$i$2_2]; index$j$2_3 += 1) {
											if((j$var115 == (index$j$2_3 - 1))) {
												{
													{
														boolean cv$sampleConstrained = (state.fixedFlag$sample126 || state.constrainedFlag$sample126[((index$i$2_2 - 0) / 1)][((index$j$2_3 - 1) / 1)]);
														if(cv$sampleConstrained) {
															state.constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				double[] sc$var121$1 = state.m[traceTempVariable$var120$2_1];
																				if(((Math.log(1.0) + ((((((0.0 <= state.st[index$i$2_2][index$j$2_3]) && (state.st[index$i$2_2][index$j$2_3] < state.noStates)) && (0 < state.noStates)) && (0.0 <= sc$var121$1[state.st[index$i$2_2][index$j$2_3]])) && (sc$var121$1[state.st[index$i$2_2][index$j$2_3]] <= 1.0))?Math.log(sc$var121$1[state.st[index$i$2_2][index$j$2_3]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.st[index$i$2_2][index$j$2_3]) && (state.st[index$i$2_2][index$j$2_3] < state.noStates)) && (0 < state.noStates)) && (0.0 <= sc$var121$1[state.st[index$i$2_2][index$j$2_3]])) && (sc$var121$1[state.st[index$i$2_2][index$j$2_3]] <= 1.0))?Math.log(sc$var121$1[state.st[index$i$2_2][index$j$2_3]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.st[index$i$2_2][index$j$2_3]) && (state.st[index$i$2_2][index$j$2_3] < state.noStates)) && (0 < state.noStates)) && (0.0 <= sc$var121$1[state.st[index$i$2_2][index$j$2_3]])) && (sc$var121$1[state.st[index$i$2_2][index$j$2_3]] <= 1.0))?Math.log(sc$var121$1[state.st[index$i$2_2][index$j$2_3]]):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.st[index$i$2_2][index$j$2_3]) && (state.st[index$i$2_2][index$j$2_3] < state.noStates)) && (0 < state.noStates)) && (0.0 <= sc$var121$1[state.st[index$i$2_2][index$j$2_3]])) && (sc$var121$1[state.st[index$i$2_2][index$j$2_3]] <= 1.0))?Math.log(sc$var121$1[state.st[index$i$2_2][index$j$2_3]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.st[index$i$2_2][index$j$2_3]) && (state.st[index$i$2_2][index$j$2_3] < state.noStates)) && (0 < state.noStates)) && (0.0 <= sc$var121$1[state.st[index$i$2_2][index$j$2_3]])) && (sc$var121$1[state.st[index$i$2_2][index$j$2_3]] <= 1.0))?Math.log(sc$var121$1[state.st[index$i$2_2][index$j$2_3]]):Double.NEGATIVE_INFINITY)));
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
					{
						{
							{
								int traceTempVariable$var152$5_1 = cv$currentValue;
								for(int i$var136 = 0; i$var136 < state.samples; i$var136 += 1) {
									if((i$var104 == i$var136)) {
										for(int j$var149 = 1; j$var149 < state.length$eventsMeasured[i$var136]; j$var149 += 1) {
											if((j$var115 == j$var149)) {
												{
													{
														boolean cv$sampleConstrained = true;
														if(cv$sampleConstrained) {
															state.constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				double[] var153 = state.bias[traceTempVariable$var152$5_1];
																				if(((Math.log(1.0) + ((((((0.0 <= (state.events[i$var136][j$var149] - 1)) && ((state.events[i$var136][j$var149] - 1) < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var136][j$var149] - 1)])) && (var153[(state.events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= (state.events[i$var136][j$var149] - 1)) && ((state.events[i$var136][j$var149] - 1) < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var136][j$var149] - 1)])) && (var153[(state.events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= (state.events[i$var136][j$var149] - 1)) && ((state.events[i$var136][j$var149] - 1) < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var136][j$var149] - 1)])) && (var153[(state.events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= (state.events[i$var136][j$var149] - 1)) && ((state.events[i$var136][j$var149] - 1) < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var136][j$var149] - 1)])) && (var153[(state.events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= (state.events[i$var136][j$var149] - 1)) && ((state.events[i$var136][j$var149] - 1) < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var136][j$var149] - 1)])) && (var153[(state.events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)));
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
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)]) {
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
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				int var123 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
				{
					{
						{
							int[] var116 = state.st[i$var104];
							var116[j$var115] = var123;
						}
					}
				}
			}
		}
	}

	private final void inferSample42(int var41) {
		if(true) {
			state.constrainedFlag$sample42[((var41 - 0) / 1)] = false;
			double[] cv$targetLocal = state.m[var41];
			double[] cv$countLocal = scratch.cv$var42$countGlobal;
			int cv$arrayLength = state.noStates;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							if((var41 == state.initialState)) {
								for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1) {
									boolean cv$sampleConstrained = (state.fixedFlag$sample95 || state.constrainedFlag$sample95[((i$var87 - 0) / 1)]);
									if(cv$sampleConstrained) {
										state.constrainedFlag$sample42[((var41 - 0) / 1)] = true;
										{
											{
												{
													{
														{
															cv$countLocal[state.st[i$var87][0]] = (cv$countLocal[state.st[i$var87][0]] + 1.0);
														}
													}
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
							for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
								for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
									if((var41 == state.st[i$var104][(j$var115 - 1)])) {
										{
											{
												boolean cv$sampleConstrained = (state.fixedFlag$sample126 || state.constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)]);
												if(cv$sampleConstrained) {
													state.constrainedFlag$sample42[((var41 - 0) / 1)] = true;
													{
														{
															{
																{
																	{
																		cv$countLocal[state.st[i$var104][j$var115]] = (cv$countLocal[state.st[i$var104][j$var115]] + 1.0);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(state.constrainedFlag$sample42[((var41 - 0) / 1)])
				Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, cv$countLocal, cv$targetLocal, state.noStates);
		}
	}

	private final void inferSample57(int var55) {
		if(true) {
			state.constrainedFlag$sample57[((var55 - 0) / 1)] = false;
			double[] cv$targetLocal = state.bias[var55];
			double[] cv$countLocal = scratch.cv$var56$countGlobal;
			int cv$arrayLength = state.noEvents;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							for(int i$var136 = 0; i$var136 < state.samples; i$var136 += 1) {
								for(int j$var149 = 1; j$var149 < state.length$eventsMeasured[i$var136]; j$var149 += 1) {
									if((var55 == state.st[i$var136][j$var149])) {
										{
											{
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													state.constrainedFlag$sample57[((var55 - 0) / 1)] = true;
													{
														{
															{
																{
																	{
																		cv$countLocal[(state.events[i$var136][j$var149] - 1)] = (cv$countLocal[(state.events[i$var136][j$var149] - 1)] + 1.0);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(state.constrainedFlag$sample57[((var55 - 0) / 1)])
				Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v2, cv$countLocal, cv$targetLocal, state.noEvents);
		}
	}

	private final void inferSample78() {
		if(true) {
			state.constrainedFlag$sample78 = false;
			double[] cv$targetLocal = state.weights;
			double[] cv$countLocal = scratch.cv$var75$countGlobal;
			int cv$arrayLength = state.noStates;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							{
								{
									boolean cv$sampleConstrained = (state.fixedFlag$sample80 || state.constrainedFlag$sample80);
									if(cv$sampleConstrained) {
										state.constrainedFlag$sample78 = true;
										{
											{
												{
													{
														{
															cv$countLocal[state.initialState] = (cv$countLocal[state.initialState] + 1.0);
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(state.constrainedFlag$sample78)
				Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, cv$countLocal, cv$targetLocal, state.noStates);
		}
	}

	private final void inferSample80() {
		if(true) {
			state.constrainedFlag$sample80 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, state.noStates);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var77$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				state.initialState = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.weights[cv$currentValue])) && (state.weights[cv$currentValue] <= 1.0))?Math.log(state.weights[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1) {
									int traceTempVariable$initialState$1_2 = cv$currentValue;
									{
										{
											boolean cv$sampleConstrained = (state.fixedFlag$sample95 || state.constrainedFlag$sample95[((i$var87 - 0) / 1)]);
											if(cv$sampleConstrained) {
												state.constrainedFlag$sample80 = true;
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													{
														{
															{
																{
																	double[] var90 = state.m[traceTempVariable$initialState$1_2];
																	if(((Math.log(1.0) + ((((((0.0 <= state.st[i$var87][0]) && (state.st[i$var87][0] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var90[state.st[i$var87][0]])) && (var90[state.st[i$var87][0]] <= 1.0))?Math.log(var90[state.st[i$var87][0]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.st[i$var87][0]) && (state.st[i$var87][0] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var90[state.st[i$var87][0]])) && (var90[state.st[i$var87][0]] <= 1.0))?Math.log(var90[state.st[i$var87][0]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.st[i$var87][0]) && (state.st[i$var87][0] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var90[state.st[i$var87][0]])) && (var90[state.st[i$var87][0]] <= 1.0))?Math.log(var90[state.st[i$var87][0]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.st[i$var87][0]) && (state.st[i$var87][0] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var90[state.st[i$var87][0]])) && (var90[state.st[i$var87][0]] <= 1.0))?Math.log(var90[state.st[i$var87][0]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.st[i$var87][0]) && (state.st[i$var87][0] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var90[state.st[i$var87][0]])) && (var90[state.st[i$var87][0]] <= 1.0))?Math.log(var90[state.st[i$var87][0]]):Double.NEGATIVE_INFINITY)));
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
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample80) {
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
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				state.initialState = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
			}
		}
	}

	private final void inferSample95(int i$var87) {
		if(true) {
			state.constrainedFlag$sample95[((i$var87 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, state.noStates);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var92$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				int var92 = cv$currentValue;
				{
					{
						{
							int[] var88 = state.st[i$var87];
							var88[0] = cv$currentValue;
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] var90 = state.m[state.initialState];
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var90[cv$currentValue])) && (var90[cv$currentValue] <= 1.0))?Math.log(var90[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$var120$2_1 = cv$currentValue;
								for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
									if((i$var87 == i$var104)) {
										for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
											if((0 == (j$var115 - 1))) {
												{
													{
														boolean cv$sampleConstrained = (state.fixedFlag$sample126 || state.constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)]);
														if(cv$sampleConstrained) {
															state.constrainedFlag$sample95[((i$var87 - 0) / 1)] = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				double[] var121 = state.m[traceTempVariable$var120$2_1];
																				if(((Math.log(1.0) + ((((((0.0 <= state.st[i$var104][j$var115]) && (state.st[i$var104][j$var115] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var121[state.st[i$var104][j$var115]])) && (var121[state.st[i$var104][j$var115]] <= 1.0))?Math.log(var121[state.st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.st[i$var104][j$var115]) && (state.st[i$var104][j$var115] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var121[state.st[i$var104][j$var115]])) && (var121[state.st[i$var104][j$var115]] <= 1.0))?Math.log(var121[state.st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.st[i$var104][j$var115]) && (state.st[i$var104][j$var115] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var121[state.st[i$var104][j$var115]])) && (var121[state.st[i$var104][j$var115]] <= 1.0))?Math.log(var121[state.st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.st[i$var104][j$var115]) && (state.st[i$var104][j$var115] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var121[state.st[i$var104][j$var115]])) && (var121[state.st[i$var104][j$var115]] <= 1.0))?Math.log(var121[state.st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.st[i$var104][j$var115]) && (state.st[i$var104][j$var115] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var121[state.st[i$var104][j$var115]])) && (var121[state.st[i$var104][j$var115]] <= 1.0))?Math.log(var121[state.st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)));
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
					{
						{
							{
								int traceTempVariable$var152$5_1 = cv$currentValue;
								for(int i$var136 = 0; i$var136 < state.samples; i$var136 += 1) {
									if((i$var87 == i$var136)) {
										for(int j$var149 = 1; j$var149 < state.length$eventsMeasured[i$var136]; j$var149 += 1) {
											if((0 == j$var149)) {
												{
													{
														boolean cv$sampleConstrained = true;
														if(cv$sampleConstrained) {
															state.constrainedFlag$sample95[((i$var87 - 0) / 1)] = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				double[] var153 = state.bias[traceTempVariable$var152$5_1];
																				if(((Math.log(1.0) + ((((((0.0 <= (state.events[i$var136][j$var149] - 1)) && ((state.events[i$var136][j$var149] - 1) < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var136][j$var149] - 1)])) && (var153[(state.events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= (state.events[i$var136][j$var149] - 1)) && ((state.events[i$var136][j$var149] - 1) < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var136][j$var149] - 1)])) && (var153[(state.events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= (state.events[i$var136][j$var149] - 1)) && ((state.events[i$var136][j$var149] - 1) < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var136][j$var149] - 1)])) && (var153[(state.events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= (state.events[i$var136][j$var149] - 1)) && ((state.events[i$var136][j$var149] - 1) < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var136][j$var149] - 1)])) && (var153[(state.events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= (state.events[i$var136][j$var149] - 1)) && ((state.events[i$var136][j$var149] - 1) < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var136][j$var149] - 1)])) && (var153[(state.events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)));
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
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample95[((i$var87 - 0) / 1)]) {
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
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				int var92 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
				{
					{
						{
							int[] var88 = state.st[i$var87];
							var88[0] = var92;
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample126() {
		if(!state.fixedProbFlag$sample126) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							int cv$sampleValue = state.st[i$var104][j$var115];
							{
								{
									double[] var121 = state.m[state.st[i$var104][(j$var115 - 1)]];
									double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var121[cv$sampleValue])) && (var121[cv$sampleValue] <= 1.0))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					state.logProbability$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = cv$sampleProbability;
				}
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample126)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample126 = ((state.fixedFlag$sample126 && state.fixedFlag$sample42) && state.fixedFlag$sample95);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = state.logProbability$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample126)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample159() {
		if(!state.fixedProbFlag$sample159) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var136 = 0; i$var136 < state.samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < state.length$eventsMeasured[i$var136]; j$var149 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							int cv$sampleValue = (state.events[i$var136][j$var149] - 1);
							{
								{
									double[] var153 = state.bias[state.st[i$var136][j$var149]];
									double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[cv$sampleValue])) && (var153[cv$sampleValue] <= 1.0))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					state.logProbability$sample159[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)] = cv$sampleProbability;
				}
			}
			state.logProbability$events = (state.logProbability$events + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample159 = ((state.fixedFlag$sample57 && state.fixedFlag$sample95) && state.fixedFlag$sample126);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var136 = 0; i$var136 < state.samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < state.length$eventsMeasured[i$var136]; j$var149 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = state.logProbability$sample159[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			state.logProbability$events = (state.logProbability$events + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample42() {
		if(!state.fixedProbFlag$sample42) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var41 = 0; var41 < state.noStates; var41 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double[] cv$sampleValue = state.m[var41];
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
			state.logProbability$var42 = cv$sampleAccumulator;
			state.logProbability$m = (state.logProbability$m + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample42)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample42 = state.fixedFlag$sample42;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var41 = 0; var41 < state.noStates; var41 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var42;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$m = (state.logProbability$m + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample42)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample57() {
		if(!state.fixedProbFlag$sample57) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var55 = 0; var55 < state.noStates; var55 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double[] cv$sampleValue = state.bias[var55];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, state.v2, state.noEvents));
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
			state.logProbability$var56 = cv$sampleAccumulator;
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample57 = state.fixedFlag$sample57;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var55 = 0; var55 < state.noStates; var55 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var56;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample78() {
		if(!state.fixedProbFlag$sample78) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double[] cv$sampleValue = state.weights;
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
			state.logProbability$weights = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample78)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample78 = state.fixedFlag$sample78;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$weights;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample78)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample80() {
		if(!state.fixedProbFlag$sample80) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = state.initialState;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.weights[cv$sampleValue])) && (state.weights[cv$sampleValue] <= 1.0))?Math.log(state.weights[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$initialState = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample80)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample80 = (state.fixedFlag$sample80 && state.fixedFlag$sample78);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$initialState;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample80)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample95() {
		if(!state.fixedProbFlag$sample95) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = state.st[i$var87][0];
						{
							{
								double[] var90 = state.m[state.initialState];
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var90[cv$sampleValue])) && (var90[cv$sampleValue] <= 1.0))?Math.log(var90[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample95[((i$var87 - 0) / 1)] = cv$sampleProbability;
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample95)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample95 = ((state.fixedFlag$sample95 && state.fixedFlag$sample42) && state.fixedFlag$sample80);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample95[((i$var87 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample95)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		for(int var41 = 0; var41 < state.noStates; var41 += 1) {
			double[] var42 = state.m[var41];
			if(!state.fixedFlag$sample42)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, var42);
		}
		for(int var55 = 0; var55 < state.noStates; var55 += 1) {
			double[] var56 = state.bias[var55];
			if(!state.fixedFlag$sample57)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v2, state.noEvents, var56);
		}
		if(!state.fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.weights);
		if(!state.fixedFlag$sample80)
			state.initialState = DistributionSampling.sampleCategorical(state.RNG$, state.weights, state.noStates);
		for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1) {
			int[] var88 = state.st[i$var87];
			if(!state.fixedFlag$sample95)
				var88[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.initialState], state.noStates);
		}
		for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
			int[] var116 = state.st[i$var104];
			for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
				if(!state.fixedFlag$sample126)
					var116[j$var115] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[i$var104][(j$var115 - 1)]], state.noStates);
			}
		}
		for(int i$var136 = 0; i$var136 < state.samples; i$var136 += 1) {
			int[] var150 = state.events[i$var136];
			for(int j$var149 = 1; j$var149 < state.length$eventsMeasured[i$var136]; j$var149 += 1)
				var150[j$var149] = (DistributionSampling.sampleCategorical(state.RNG$, state.bias[state.st[i$var136][j$var149]], state.noEvents) + 1);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		for(int var41 = 0; var41 < state.noStates; var41 += 1) {
			double[] var42 = state.m[var41];
			if(!state.fixedFlag$sample42)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, var42);
		}
		for(int var55 = 0; var55 < state.noStates; var55 += 1) {
			double[] var56 = state.bias[var55];
			if(!state.fixedFlag$sample57)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v2, state.noEvents, var56);
		}
		if(!state.fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.weights);
		if(!state.fixedFlag$sample80)
			state.initialState = DistributionSampling.sampleCategorical(state.RNG$, state.weights, state.noStates);
		for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1) {
			int[] var88 = state.st[i$var87];
			if(!state.fixedFlag$sample95)
				var88[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.initialState], state.noStates);
		}
		for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
			int[] var116 = state.st[i$var104];
			for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
				if(!state.fixedFlag$sample126)
					var116[j$var115] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[i$var104][(j$var115 - 1)]], state.noStates);
			}
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		for(int var41 = 0; var41 < state.noStates; var41 += 1) {
			double[] var42 = state.m[var41];
			if(!state.fixedFlag$sample42)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, var42);
		}
		for(int var55 = 0; var55 < state.noStates; var55 += 1) {
			double[] var56 = state.bias[var55];
			if(!state.fixedFlag$sample57)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v2, state.noEvents, var56);
		}
		if(!state.fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.weights);
		if(!state.fixedFlag$sample80)
			state.initialState = DistributionSampling.sampleCategorical(state.RNG$, state.weights, state.noStates);
		for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1) {
			int[] var88 = state.st[i$var87];
			if(!state.fixedFlag$sample95)
				var88[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.initialState], state.noStates);
		}
		for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
			int[] var116 = state.st[i$var104];
			for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
				if(!state.fixedFlag$sample126)
					var116[j$var115] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[i$var104][(j$var115 - 1)]], state.noStates);
			}
		}
		for(int i$var136 = 0; i$var136 < state.samples; i$var136 += 1) {
			int[] var150 = state.events[i$var136];
			for(int j$var149 = 1; j$var149 < state.length$eventsMeasured[i$var136]; j$var149 += 1)
				var150[j$var149] = (DistributionSampling.sampleCategorical(state.RNG$, state.bias[state.st[i$var136][j$var149]], state.noEvents) + 1);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var41 = 0; var41 < state.noStates; var41 += 1) {
			double[] var42 = state.m[var41];
			if(!state.fixedFlag$sample42)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, var42);
		}
		for(int var55 = 0; var55 < state.noStates; var55 += 1) {
			double[] var56 = state.bias[var55];
			if(!state.fixedFlag$sample57)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v2, state.noEvents, var56);
		}
		if(!state.fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.weights);
		if(!state.fixedFlag$sample80)
			state.initialState = DistributionSampling.sampleCategorical(state.RNG$, state.weights, state.noStates);
		for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1) {
			int[] var88 = state.st[i$var87];
			if(!state.fixedFlag$sample95)
				var88[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.initialState], state.noStates);
		}
		for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
			int[] var116 = state.st[i$var104];
			for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
				if(!state.fixedFlag$sample126)
					var116[j$var115] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[i$var104][(j$var115 - 1)]], state.noStates);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		for(int var41 = 0; var41 < state.noStates; var41 += 1) {
			double[] var42 = state.m[var41];
			if(!state.fixedFlag$sample42)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, var42);
		}
		for(int var55 = 0; var55 < state.noStates; var55 += 1) {
			double[] var56 = state.bias[var55];
			if(!state.fixedFlag$sample57)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v2, state.noEvents, var56);
		}
		if(!state.fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.weights);
		if(!state.fixedFlag$sample80)
			state.initialState = DistributionSampling.sampleCategorical(state.RNG$, state.weights, state.noStates);
		for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1) {
			int[] var88 = state.st[i$var87];
			if(!state.fixedFlag$sample95)
				var88[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.initialState], state.noStates);
		}
		for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
			int[] var116 = state.st[i$var104];
			for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
				if(!state.fixedFlag$sample126)
					var116[j$var115] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[i$var104][(j$var115 - 1)]], state.noStates);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			for(int var41 = 0; var41 < state.noStates; var41 += 1) {
				if(!state.fixedFlag$sample42)
					inferSample42(var41);
			}
			for(int var55 = 0; var55 < state.noStates; var55 += 1) {
				if(!state.fixedFlag$sample57)
					inferSample57(var55);
			}
			if(!state.fixedFlag$sample78)
				inferSample78();
			if(!state.fixedFlag$sample80)
				inferSample80();
			for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1) {
				if(!state.fixedFlag$sample95)
					inferSample95(i$var87);
			}
			for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
					if(!state.fixedFlag$sample126)
						inferSample126(i$var104, j$var115);
				}
			}
		} else {
			for(int i$var104 = (state.samples - ((((state.samples - 1) - 0) % 1) + 1)); i$var104 >= ((0 - 1) + 1); i$var104 -= 1) {
				for(int j$var115 = (state.length$eventsMeasured[i$var104] - ((((state.length$eventsMeasured[i$var104] - 1) - 1) % 1) + 1)); j$var115 >= ((1 - 1) + 1); j$var115 -= 1) {
					if(!state.fixedFlag$sample126)
						inferSample126(i$var104, j$var115);
				}
			}
			for(int i$var87 = (state.samples - ((((state.samples - 1) - 0) % 1) + 1)); i$var87 >= ((0 - 1) + 1); i$var87 -= 1) {
				if(!state.fixedFlag$sample95)
					inferSample95(i$var87);
			}
			if(!state.fixedFlag$sample80)
				inferSample80();
			if(!state.fixedFlag$sample78)
				inferSample78();
			for(int var55 = (state.noStates - ((((state.noStates - 1) - 0) % 1) + 1)); var55 >= ((0 - 1) + 1); var55 -= 1) {
				if(!state.fixedFlag$sample57)
					inferSample57(var55);
			}
			for(int var41 = (state.noStates - ((((state.noStates - 1) - 0) % 1) + 1)); var41 >= ((0 - 1) + 1); var41 -= 1) {
				if(!state.fixedFlag$sample42)
					inferSample42(var41);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int var41 = 0; var41 < state.noStates; var41 += 1) {
			if(!state.constrainedFlag$sample42[((var41 - 0) / 1)])
				drawValueSample42(var41);
		}
		for(int var55 = 0; var55 < state.noStates; var55 += 1) {
			if(!state.constrainedFlag$sample57[((var55 - 0) / 1)])
				drawValueSample57(var55);
		}
		if(!state.constrainedFlag$sample78)
			drawValueSample78();
		if(!state.constrainedFlag$sample80)
			drawValueSample80();
		for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1) {
			if(!state.constrainedFlag$sample95[((i$var87 - 0) / 1)])
				drawValueSample95(i$var87);
		}
		for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
			for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
				if(!state.constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)])
					drawValueSample126(i$var104, j$var115);
			}
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$m = 0.0;
		if(!state.fixedProbFlag$sample42)
			state.logProbability$var42 = Double.NaN;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample57)
			state.logProbability$var56 = Double.NaN;
		if(!state.fixedProbFlag$sample78)
			state.logProbability$weights = Double.NaN;
		if(!state.fixedProbFlag$sample80)
			state.logProbability$initialState = Double.NaN;
		state.logProbability$st = 0.0;
		if(!state.fixedProbFlag$sample95) {
			for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1)
				state.logProbability$sample95[((i$var87 - 0) / 1)] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample126) {
			for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1)
					state.logProbability$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = Double.NaN;
			}
		}
		state.logProbability$events = 0.0;
		if(!state.fixedProbFlag$sample159) {
			for(int i$var136 = 0; i$var136 < state.samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < state.length$eventsMeasured[i$var136]; j$var149 += 1)
					state.logProbability$sample159[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)] = Double.NaN;
			}
		}
	}

	@Override
	public final void initializeModel() {
		for(int var14 = 0; var14 < state.noStates; var14 += 1)
			state.v[var14] = 0.1;
		for(int var27 = 0; var27 < state.noEvents; var27 += 1)
			state.v2[var27] = 0.1;
		state.samples = state.length$eventsMeasured.length;
		for(int index$constrainedFlag$sample95$1 = 0; index$constrainedFlag$sample95$1 < state.constrainedFlag$sample95.length; index$constrainedFlag$sample95$1 += 1)
			state.constrainedFlag$sample95[index$constrainedFlag$sample95$1] = true;
		for(int index$constrainedFlag$sample126$1 = 0; index$constrainedFlag$sample126$1 < state.constrainedFlag$sample126.length; index$constrainedFlag$sample126$1 += 1) {
			boolean[] cv$constrainedFlag$sample126$1 = state.constrainedFlag$sample126[index$constrainedFlag$sample126$1];
			for(int index$constrainedFlag$sample126$2 = 0; index$constrainedFlag$sample126$2 < cv$constrainedFlag$sample126$1.length; index$constrainedFlag$sample126$2 += 1)
				cv$constrainedFlag$sample126$1[index$constrainedFlag$sample126$2] = true;
		}
		for(int index$constrainedFlag$sample42$1 = 0; index$constrainedFlag$sample42$1 < state.constrainedFlag$sample42.length; index$constrainedFlag$sample42$1 += 1)
			state.constrainedFlag$sample42[index$constrainedFlag$sample42$1] = true;
		for(int index$constrainedFlag$sample57$1 = 0; index$constrainedFlag$sample57$1 < state.constrainedFlag$sample57.length; index$constrainedFlag$sample57$1 += 1)
			state.constrainedFlag$sample57[index$constrainedFlag$sample57$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample42)
			logProbabilityValue$sample42();
		if(state.fixedFlag$sample57)
			logProbabilityValue$sample57();
		if(state.fixedFlag$sample78)
			logProbabilityValue$sample78();
		if(state.fixedFlag$sample80)
			logProbabilityValue$sample80();
		if(state.fixedFlag$sample95)
			logProbabilityValue$sample95();
		if(state.fixedFlag$sample126)
			logProbabilityValue$sample126();
		logProbabilityValue$sample159();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample42();
		logProbabilityValue$sample57();
		logProbabilityValue$sample78();
		logProbabilityValue$sample80();
		logProbabilityValue$sample95();
		logProbabilityValue$sample126();
		logProbabilityValue$sample159();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample42();
		logProbabilityValue$sample57();
		logProbabilityValue$sample78();
		logProbabilityValue$sample80();
		logProbabilityValue$sample95();
		logProbabilityValue$sample126();
		logProbabilityValue$sample159();
	}

	@Override
	public final void propagateObservedValues() {
		int[][] cv$source1 = state.eventsMeasured;
		int[][] cv$target1 = state.events;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = cv$source1[cv$index1];
			int[] cv$target2 = cv$target1[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
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
		     + "model HMM_Mk2(int[][] eventsMeasured, int noStates, int noEvents) {\n"
		     + "        \n"
		     + "        // Construct arrays describing the probability of a move from 1 state to another.\n"
		     + "        double[] v = new double[noStates] <~ 0.1;\n"
		     + "        double[] v2 = new double[noEvents] <~ 0.1;\n"
		     + "        double[][] m = dirichlet(v).sample(noStates);\n"
		     + "        \n"
		     + "        // Construct the bias for each webpage.\n"
		     + "        double[][] bias = dirichlet(v2).sample(noStates);\n"
		     + "\n"
		     + "        // Determine how many samples the model will need to produce.\n"
		     + "        int samples = eventsMeasured.length;\n"
		     + "        \n"
		     + "        // Allocate space for the state, i.e. which webpage we are going to trigger an event on.\n"
		     + "        int[][] st = new int[samples][];\n"
		     + "        for(int i:[0 .. samples)){\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            st[i] = new int[streamLength];\n"
		     + "        }\n"
		     + "\n"
		     + "        // Set the initial state by sampling from a categorical with learnt weightings.\n"
		     + "        double[] weights = dirichlet(v).sample();\n"
		     + "        int initialState = categorical(weights).sample();\n"
		     + "        for(int i:[0..samples)) {\n"
		     + "            st[i][0] = categorical(m[initialState]).sample();\n"
		     + "        }\n"
		     + "\n"
		     + "        //Determine the remaining states based on the previous state.\n"
		     + "        for(int i:[0 .. samples)){\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            for(int j:[1..streamLength)){\n"
		     + "                st[i][j] = categorical(m[st[i][j-1]]).sample();\n"
		     + "            }\n"
		     + "        }\n"
		     + "            \n"
		     + "        //Generate each event.\n"
		     + "        int[][] events = new int[samples][];\n"
		     + "        for(int i:[0 .. samples)) {\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            events[i] = new int[streamLength];\n"
		     + "            for(int j:[1..streamLength)){\n"
		     + "                events[i][j] = categorical(bias[st[i][j]]).sample() + 1;\n"
		     + "            }\n"
		     + "        }\n"
		     + "\n"
		     + "        //Tie the values of the flips to the values we have measured.\n"
		     + "        events.observe(eventsMeasured);\n"
		     + "}\n"
		     + "";
	}
}