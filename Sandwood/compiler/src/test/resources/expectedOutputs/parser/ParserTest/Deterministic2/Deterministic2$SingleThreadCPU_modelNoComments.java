package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Deterministic2$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Deterministic2.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Deterministic2$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$distributionAccumulator$var53;
		double[] cv$var29$countGlobal;
		double[] cv$var54$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			{
				cv$var29$countGlobal = new double[5];
			}
			{
				int cv$var30$max = 5;
				cv$distributionAccumulator$var53 = new double[cv$var30$max];
			}
			{
				int cv$var30$max = 5;
				cv$var54$stateProbabilityGlobal = new double[cv$var30$max];
			}
		}
	}


	public Deterministic2$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample29(int var28) {
		double[] var29 = state.m[var28];
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.states, var29);
	}

	private final void drawValueSample55(int i$var46) {
		int index$i$1 = i$var46;
		state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], state.states);
		{
			{
				for(int index$i$2_1 = 1; index$i$2_1 < state.n; index$i$2_1 += 1) {
					if((i$var46 == (index$i$2_1 - 1))) {
						{
							state.b[index$i$2_1] = state.a[(index$i$2_1 - 1)];
						}
					}
				}
			}
		}
	}

	private final void inferSample29(int var28) {
		if(true) {
			state.constrainedFlag$sample29[((var28 - 0) / 1)] = false;
			double[] cv$targetLocal = state.m[var28];
			double[] cv$countLocal = scratch.cv$var29$countGlobal;
			int cv$arrayLength = state.states;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
							int traceTempVariable$var49$2_1 = 0;
							for(int index$i$2_2 = 1; index$i$2_2 < state.n; index$i$2_2 += 1) {
								if((0 == (index$i$2_2 - 1))) {
									int traceTempVariable$var51$2_3 = traceTempVariable$var49$2_1;
									if((index$i$2_2 == i$var46)) {
										{
											if((var28 == traceTempVariable$var51$2_3)) {
												if(state.fixedFlag$sample55) {
													{
														{
															int index$i$14 = i$var46;
															boolean cv$sampleConstrained = (state.fixedFlag$sample55 || state.constrainedFlag$sample55[((i$var46 - 1) / 1)]);
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample29[((var28 - 0) / 1)] = true;
																{
																	{
																		{
																			{
																				{
																					cv$countLocal[state.a[i$var46]] = (cv$countLocal[state.a[i$var46]] + 1.0);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
							if(state.fixedFlag$sample55) {
								{
									for(int index$i$5_1 = 1; index$i$5_1 < state.n; index$i$5_1 += 1) {
										for(int index$i$5_2 = 1; index$i$5_2 < state.n; index$i$5_2 += 1) {
											if((index$i$5_1 == (index$i$5_2 - 1))) {
												int traceTempVariable$var51$5_3 = state.a[(index$i$5_2 - 1)];
												if((index$i$5_2 == i$var46)) {
													{
														if((var28 == traceTempVariable$var51$5_3)) {
															if(state.fixedFlag$sample55) {
																{
																	{
																		int index$i$16 = i$var46;
																		boolean cv$sampleConstrained = (state.fixedFlag$sample55 || state.constrainedFlag$sample55[((i$var46 - 1) / 1)]);
																		if(cv$sampleConstrained) {
																			state.constrainedFlag$sample29[((var28 - 0) / 1)] = true;
																			{
																				{
																					{
																						{
																							{
																								cv$countLocal[state.a[i$var46]] = (cv$countLocal[state.a[i$var46]] + 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
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
								for(int index$i$6 = 1; index$i$6 < state.n; index$i$6 += 1) {
									if(true) {
										for(int index$sample55$7 = 0; index$sample55$7 < state.states; index$sample55$7 += 1) {
											int distributionTempVariable$var54$9 = index$sample55$7;
											double cv$probabilitySample55Value8 = (1.0 * state.distribution$sample55[((index$i$6 - 1) / 1)][index$sample55$7]);
											{
												int traceTempVariable$var49$10_1 = distributionTempVariable$var54$9;
												for(int index$i$10_2 = 1; index$i$10_2 < state.n; index$i$10_2 += 1) {
													if((index$i$6 == (index$i$10_2 - 1))) {
														int traceTempVariable$var51$10_3 = traceTempVariable$var49$10_1;
														if((index$i$10_2 == i$var46)) {
															{
																if((var28 == traceTempVariable$var51$10_3)) {
																	if(state.fixedFlag$sample55) {
																		{
																			{
																				int index$i$18 = i$var46;
																				boolean cv$sampleConstrained = (state.fixedFlag$sample55 || state.constrainedFlag$sample55[((i$var46 - 1) / 1)]);
																				if(cv$sampleConstrained) {
																					state.constrainedFlag$sample29[((var28 - 0) / 1)] = true;
																					{
																						{
																							{
																								{
																									{
																										cv$countLocal[state.a[i$var46]] = (cv$countLocal[state.a[i$var46]] + cv$probabilitySample55Value8);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
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
					for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
						int traceTempVariable$var49$23_1 = 0;
						for(int index$i$23_2 = 1; index$i$23_2 < state.n; index$i$23_2 += 1) {
							if((0 == (index$i$23_2 - 1))) {
								int traceTempVariable$var51$23_3 = traceTempVariable$var49$23_1;
								if((index$i$23_2 == i$var46)) {
									{
										if((var28 == traceTempVariable$var51$23_3)) {
											if(!state.fixedFlag$sample55) {
												{
													{
														int index$i$35 = i$var46;
														{
															{
																double scopeVariable$reachedSourceProbability = 0.0;
																{
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
																double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																	cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample55[((i$var46 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
						if(state.fixedFlag$sample55) {
							{
								for(int index$i$26_1 = 1; index$i$26_1 < state.n; index$i$26_1 += 1) {
									for(int index$i$26_2 = 1; index$i$26_2 < state.n; index$i$26_2 += 1) {
										if((index$i$26_1 == (index$i$26_2 - 1))) {
											int traceTempVariable$var51$26_3 = state.a[(index$i$26_2 - 1)];
											if((index$i$26_2 == i$var46)) {
												{
													if((var28 == traceTempVariable$var51$26_3)) {
														if(!state.fixedFlag$sample55) {
															{
																{
																	int index$i$37 = i$var46;
																	{
																		{
																			double scopeVariable$reachedSourceProbability = 0.0;
																			{
																				scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																			}
																			double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																				cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample55[((i$var46 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																		}
																	}
																}
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
							for(int index$i$27 = 1; index$i$27 < state.n; index$i$27 += 1) {
								if(true) {
									for(int index$sample55$28 = 0; index$sample55$28 < state.states; index$sample55$28 += 1) {
										int distributionTempVariable$var54$30 = index$sample55$28;
										double cv$probabilitySample55Value29 = (1.0 * state.distribution$sample55[((index$i$27 - 1) / 1)][index$sample55$28]);
										{
											int traceTempVariable$var49$31_1 = distributionTempVariable$var54$30;
											for(int index$i$31_2 = 1; index$i$31_2 < state.n; index$i$31_2 += 1) {
												if((index$i$27 == (index$i$31_2 - 1))) {
													int traceTempVariable$var51$31_3 = traceTempVariable$var49$31_1;
													if((index$i$31_2 == i$var46)) {
														{
															if((var28 == traceTempVariable$var51$31_3)) {
																if(!state.fixedFlag$sample55) {
																	{
																		{
																			int index$i$39 = i$var46;
																			{
																				{
																					double scopeVariable$reachedSourceProbability = 0.0;
																					{
																						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																					}
																					double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample55Value29);
																					for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																						cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample55[((i$var46 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(state.constrainedFlag$sample29[((var28 - 0) / 1)])
				Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, cv$countLocal, cv$targetLocal, state.states);
		}
	}

	private final void inferSample55(int i$var46) {
		int index$i$1 = i$var46;
		if(true) {
			state.constrainedFlag$sample55[((i$var46 - 1) / 1)] = false;
			int cv$numStates = 0;
			{
				int traceTempVariable$var49$2_1 = 0;
				for(int index$i$2_2 = 1; index$i$2_2 < state.n; index$i$2_2 += 1) {
					if((0 == (index$i$2_2 - 1))) {
						int traceTempVariable$var51$2_3 = traceTempVariable$var49$2_1;
						if((index$i$2_2 == i$var46)) {
							{
								for(int var28 = 0; var28 < state.states; var28 += 1) {
									if((var28 == traceTempVariable$var51$2_3))
										cv$numStates = Math.max(cv$numStates, state.states);
								}
							}
						}
					}
				}
			}
			{
				for(int index$i$4_1 = 1; index$i$4_1 < state.n; index$i$4_1 += 1) {
					if((index$i$1 == (index$i$4_1 - 1))) {
						int traceTempVariable$var51$4_2 = state.a[(index$i$4_1 - 1)];
						if((index$i$4_1 == i$var46)) {
							{
								for(int var28 = 0; var28 < state.states; var28 += 1) {
									if((var28 == traceTempVariable$var51$4_2))
										cv$numStates = Math.max(cv$numStates, state.states);
								}
							}
						}
					}
				}
			}
			for(int index$i$5 = 1; index$i$5 < state.n; index$i$5 += 1) {
				if(!(index$i$5 == index$i$1)) {
					for(int index$sample55$6 = 0; index$sample55$6 < state.states; index$sample55$6 += 1) {
						int distributionTempVariable$var54$8 = index$sample55$6;
						double cv$probabilitySample55Value7 = (1.0 * state.distribution$sample55[((index$i$5 - 1) / 1)][index$sample55$6]);
						{
							int traceTempVariable$var49$9_1 = distributionTempVariable$var54$8;
							for(int index$i$9_2 = 1; index$i$9_2 < state.n; index$i$9_2 += 1) {
								if((index$i$5 == (index$i$9_2 - 1))) {
									int traceTempVariable$var51$9_3 = traceTempVariable$var49$9_1;
									if((index$i$9_2 == i$var46)) {
										{
											for(int var28 = 0; var28 < state.states; var28 += 1) {
												if((var28 == traceTempVariable$var51$9_3))
													cv$numStates = Math.max(cv$numStates, state.states);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var54$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				{
					int traceTempVariable$var49$12_1 = 0;
					for(int index$i$12_2 = 1; index$i$12_2 < state.n; index$i$12_2 += 1) {
						if((0 == (index$i$12_2 - 1))) {
							int traceTempVariable$var51$12_3 = traceTempVariable$var49$12_1;
							if((index$i$12_2 == i$var46)) {
								{
									for(int var28 = 0; var28 < state.states; var28 += 1) {
										if((var28 == traceTempVariable$var51$12_3)) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
											double[] var52 = state.m[traceTempVariable$var51$12_3];
											double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.states)) && (0 < state.states)) && (0.0 <= var52[cv$currentValue])) && (var52[cv$currentValue] <= 1.0))?Math.log(var52[cv$currentValue]):Double.NEGATIVE_INFINITY));
											{
												{
													{
														int traceTempVariable$var49$22_1 = cv$currentValue;
														for(int index$i$22_2 = 1; index$i$22_2 < state.n; index$i$22_2 += 1) {
															if((i$var46 == (index$i$22_2 - 1))) {
																int traceTempVariable$var51$22_3 = traceTempVariable$var49$22_1;
															}
														}
													}
												}
											}
											{
												{
													{
														int traceTempVariable$var70$25_1 = cv$currentValue;
														for(int j = 0; j < state.n; j += 1) {
															if((i$var46 == (j + 1))) {
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			state.constrainedFlag$sample55[((i$var46 - 1) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					{
																						{
																							{
																								double var72 = (double)(1 / traceTempVariable$var70$25_1);
																								if(((Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY)));
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
				{
					int traceTempVariable$var49$14_1 = cv$currentValue;
					for(int index$i$14_2 = 1; index$i$14_2 < state.n; index$i$14_2 += 1) {
						if((index$i$1 == (index$i$14_2 - 1))) {
							int traceTempVariable$var51$14_3 = traceTempVariable$var49$14_1;
							if((index$i$14_2 == i$var46)) {
								{
									for(int var28 = 0; var28 < state.states; var28 += 1) {
										if((var28 == traceTempVariable$var51$14_3)) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
											double[] var52 = state.m[traceTempVariable$var51$14_3];
											double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.states)) && (0 < state.states)) && (0.0 <= var52[cv$currentValue])) && (var52[cv$currentValue] <= 1.0))?Math.log(var52[cv$currentValue]):Double.NEGATIVE_INFINITY));
											{
												{
													{
														int traceTempVariable$var49$23_1 = cv$currentValue;
														for(int index$i$23_2 = 1; index$i$23_2 < state.n; index$i$23_2 += 1) {
															if((i$var46 == (index$i$23_2 - 1))) {
																int traceTempVariable$var51$23_3 = traceTempVariable$var49$23_1;
															}
														}
													}
												}
											}
											{
												{
													{
														int traceTempVariable$var70$26_1 = cv$currentValue;
														for(int j = 0; j < state.n; j += 1) {
															if((i$var46 == (j + 1))) {
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			state.constrainedFlag$sample55[((i$var46 - 1) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					{
																						{
																							{
																								double var72 = (double)(1 / traceTempVariable$var70$26_1);
																								if(((Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY)));
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
				for(int index$i$15 = 1; index$i$15 < state.n; index$i$15 += 1) {
					if(!(index$i$15 == index$i$1)) {
						for(int index$sample55$16 = 0; index$sample55$16 < state.states; index$sample55$16 += 1) {
							int distributionTempVariable$var54$18 = index$sample55$16;
							double cv$probabilitySample55Value17 = (1.0 * state.distribution$sample55[((index$i$15 - 1) / 1)][index$sample55$16]);
							{
								int traceTempVariable$var49$19_1 = distributionTempVariable$var54$18;
								for(int index$i$19_2 = 1; index$i$19_2 < state.n; index$i$19_2 += 1) {
									if((index$i$15 == (index$i$19_2 - 1))) {
										int traceTempVariable$var51$19_3 = traceTempVariable$var49$19_1;
										if((index$i$19_2 == i$var46)) {
											{
												for(int var28 = 0; var28 < state.states; var28 += 1) {
													if((var28 == traceTempVariable$var51$19_3)) {
														cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample55Value17);
														double[] var52 = state.m[traceTempVariable$var51$19_3];
														double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample55Value17) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.states)) && (0 < state.states)) && (0.0 <= var52[cv$currentValue])) && (var52[cv$currentValue] <= 1.0))?Math.log(var52[cv$currentValue]):Double.NEGATIVE_INFINITY));
														{
															{
																{
																	int traceTempVariable$var49$24_1 = distributionTempVariable$var54$18;
																	for(int index$i$24_2 = 1; index$i$24_2 < state.n; index$i$24_2 += 1) {
																		if((i$var46 == (index$i$24_2 - 1))) {
																			int traceTempVariable$var51$24_3 = traceTempVariable$var49$24_1;
																		}
																	}
																}
															}
														}
														{
															{
																{
																	int traceTempVariable$var70$27_1 = distributionTempVariable$var54$18;
																	for(int j = 0; j < state.n; j += 1) {
																		if((i$var46 == (j + 1))) {
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						state.constrainedFlag$sample55[((i$var46 - 1) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										{
																											double var72 = (double)(1 / traceTempVariable$var70$27_1);
																											if(((Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY)));
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
							int traceTempVariable$var49$34_1 = cv$currentValue;
							for(int index$i$34_2 = 1; index$i$34_2 < state.n; index$i$34_2 += 1) {
								if((i$var46 == (index$i$34_2 - 1))) {
									int traceTempVariable$var51$34_3 = traceTempVariable$var49$34_1;
									for(int index$i$34_4 = 1; index$i$34_4 < state.n; index$i$34_4 += 1) {
										if((index$i$34_2 == index$i$34_4)) {
											{
												{
													int index$i$36 = index$i$34_4;
													double[] cv$accumulatedConsumerDistributions = scratch.cv$distributionAccumulator$var53;
													for(int cv$i = 0; cv$i < state.states; cv$i += 1)
														cv$accumulatedConsumerDistributions[cv$i] = 0.0;
													double cv$reachedDistributionProbability = 0.0;
													{
														for(int var28 = 0; var28 < state.states; var28 += 1) {
															if((var28 == traceTempVariable$var51$34_3)) {
																{
																	double scopeVariable$reachedSourceProbability = 0.0;
																	{
																		int traceTempVariable$var49$38_1 = 0;
																		for(int index$i$38_2 = 1; index$i$38_2 < state.n; index$i$38_2 += 1) {
																			if((0 == (index$i$38_2 - 1))) {
																				int traceTempVariable$var51$38_3 = traceTempVariable$var49$38_1;
																				if((index$i$38_2 == i$var46)) {
																					{
																						for(int index$var28$39_1 = 0; index$var28$39_1 < state.states; index$var28$39_1 += 1) {
																							if((index$var28$39_1 == traceTempVariable$var51$38_3))
																								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																	{
																		int traceTempVariable$var49$40_1 = cv$currentValue;
																		for(int index$i$40_2 = 1; index$i$40_2 < state.n; index$i$40_2 += 1) {
																			if((index$i$1 == (index$i$40_2 - 1))) {
																				int traceTempVariable$var51$40_3 = traceTempVariable$var49$40_1;
																				if((index$i$40_2 == i$var46)) {
																					{
																						for(int index$var28$46_1 = 0; index$var28$46_1 < state.states; index$var28$46_1 += 1) {
																							if((index$var28$46_1 == traceTempVariable$var51$40_3))
																								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																	for(int index$i$41 = 1; index$i$41 < state.n; index$i$41 += 1) {
																		if((!(index$i$41 == index$i$1) && !(index$i$41 == index$i$36))) {
																			for(int index$sample55$42 = 0; index$sample55$42 < state.states; index$sample55$42 += 1) {
																				int distributionTempVariable$var54$44 = index$sample55$42;
																				double cv$probabilitySample55Value43 = (1.0 * state.distribution$sample55[((index$i$41 - 1) / 1)][index$sample55$42]);
																				{
																					int traceTempVariable$var49$45_1 = distributionTempVariable$var54$44;
																					for(int index$i$45_2 = 1; index$i$45_2 < state.n; index$i$45_2 += 1) {
																						if((index$i$41 == (index$i$45_2 - 1))) {
																							int traceTempVariable$var51$45_3 = traceTempVariable$var49$45_1;
																							if((index$i$45_2 == i$var46)) {
																								{
																									for(int index$var28$47_1 = 0; index$var28$47_1 < state.states; index$var28$47_1 += 1) {
																										if((index$var28$47_1 == traceTempVariable$var51$45_3))
																											scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample55Value43);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																	double[] var52 = state.m[traceTempVariable$var51$34_3];
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																	cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
																	DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, var52, state.states);
																}
															}
														}
													}
													double[] cv$sampleDistribution = state.distribution$sample55[((index$i$34_4 - 1) / 1)];
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
				}
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample55[((i$var46 - 1) / 1)]) {
				double[] cv$localProbability = state.distribution$sample55[((i$var46 - 1) / 1)];
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

	private final void logProbabilityDistribution$sample55() {
		if(!state.fixedProbFlag$sample55) {
			if(state.fixedFlag$sample55) {
				double cv$accumulator = 0.0;
				boolean cv$sampleReached = false;
				for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$i$1 = i$var46;
					{
						{
							int cv$sampleValue = state.a[i$var46];
							{
								int traceTempVariable$var49$3_1 = 0;
								for(int index$i$3_2 = 1; index$i$3_2 < state.n; index$i$3_2 += 1) {
									if((0 == (index$i$3_2 - 1))) {
										int traceTempVariable$var51$3_3 = traceTempVariable$var49$3_1;
										if((index$i$3_2 == i$var46)) {
											{
												for(int var28 = 0; var28 < state.states; var28 += 1) {
													if((var28 == traceTempVariable$var51$3_3)) {
														{
															double[] var52 = state.m[traceTempVariable$var51$3_3];
															double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var52[cv$sampleValue])) && (var52[cv$sampleValue] <= 1.0))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
							{
								for(int index$i$5_1 = 1; index$i$5_1 < state.n; index$i$5_1 += 1) {
									if((index$i$1 == (index$i$5_1 - 1))) {
										int traceTempVariable$var51$5_2 = state.a[(index$i$5_1 - 1)];
										if((index$i$5_1 == i$var46)) {
											{
												for(int var28 = 0; var28 < state.states; var28 += 1) {
													if((var28 == traceTempVariable$var51$5_2)) {
														{
															double[] var52 = state.m[traceTempVariable$var51$5_2];
															double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var52[cv$sampleValue])) && (var52[cv$sampleValue] <= 1.0))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
							if(state.fixedFlag$sample55) {
								{
									for(int index$i$6_1 = 1; index$i$6_1 < state.n; index$i$6_1 += 1) {
										for(int index$i$6_2 = 1; index$i$6_2 < state.n; index$i$6_2 += 1) {
											if((index$i$6_1 == (index$i$6_2 - 1))) {
												int traceTempVariable$var51$6_3 = state.a[(index$i$6_2 - 1)];
												if((index$i$6_2 == i$var46)) {
													{
														for(int var28 = 0; var28 < state.states; var28 += 1) {
															if((var28 == traceTempVariable$var51$6_3)) {
																{
																	double[] var52 = state.m[traceTempVariable$var51$6_3];
																	double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var52[cv$sampleValue])) && (var52[cv$sampleValue] <= 1.0))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
								for(int index$i$7 = 1; index$i$7 < state.n; index$i$7 += 1) {
									if(!(index$i$7 == index$i$1)) {
										for(int index$sample55$8 = 0; index$sample55$8 < state.states; index$sample55$8 += 1) {
											int distributionTempVariable$var54$10 = index$sample55$8;
											double cv$probabilitySample55Value9 = (1.0 * state.distribution$sample55[((index$i$7 - 1) / 1)][index$sample55$8]);
											{
												int traceTempVariable$var49$11_1 = distributionTempVariable$var54$10;
												for(int index$i$11_2 = 1; index$i$11_2 < state.n; index$i$11_2 += 1) {
													if((index$i$7 == (index$i$11_2 - 1))) {
														int traceTempVariable$var51$11_3 = traceTempVariable$var49$11_1;
														if((index$i$11_2 == i$var46)) {
															{
																for(int var28 = 0; var28 < state.states; var28 += 1) {
																	if((var28 == traceTempVariable$var51$11_3)) {
																		{
																			double[] var52 = state.m[traceTempVariable$var51$11_3];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample55Value9) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var52[cv$sampleValue])) && (var52[cv$sampleValue] <= 1.0))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample55Value9);
																		}
																	}
																}
															}
														}
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
					state.logProbability$sample55[((i$var46 - 1) / 1)] = cv$sampleProbability;
					boolean cv$guard$b = false;
					int index$i$15 = i$var46;
					{
						{
							for(int index$i$16_1 = 1; index$i$16_1 < state.n; index$i$16_1 += 1) {
								if((i$var46 == (index$i$16_1 - 1))) {
									if(state.fixedFlag$sample55) {
										if(!cv$guard$b) {
											cv$guard$b = true;
											state.logProbability$b = (state.logProbability$b + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
				}
				if(state.fixedFlag$sample55)
					state.logProbability$a = (state.logProbability$a + cv$accumulator);
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				if(state.fixedFlag$sample55)
					state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				state.fixedProbFlag$sample55 = (state.fixedFlag$sample55 && state.fixedFlag$sample29);
			}
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample55[((i$var46 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				boolean cv$guard$b = false;
				int index$i$17 = i$var46;
				{
					{
						for(int index$i$18_1 = 1; index$i$18_1 < state.n; index$i$18_1 += 1) {
							if((i$var46 == (index$i$18_1 - 1))) {
								if(state.fixedFlag$sample55) {
									if(!cv$guard$b) {
										cv$guard$b = true;
										state.logProbability$b = (state.logProbability$b + cv$sampleValue);
									}
								}
							}
						}
					}
				}
			}
			if(state.fixedFlag$sample55)
				state.logProbability$a = (state.logProbability$a + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample55)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample75() {
		if(!state.fixedProbFlag$sample75) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.n; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.flips[j];
						{
							int traceTempVariable$var70$2_1 = 0;
							if((0 == (j + 1))) {
								{
									double var72 = (double)(1 / traceTempVariable$var70$2_1);
									double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((cv$sampleValue?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY));
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
						if(state.fixedFlag$sample55) {
							{
								for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
									if((i$var46 == (j + 1))) {
										{
											double var72 = (double)(1 / state.a[(j + 1)]);
											double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((cv$sampleValue?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY));
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
						} else {
							for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
								if(true) {
									for(int index$sample55$5 = 0; index$sample55$5 < state.states; index$sample55$5 += 1) {
										int distributionTempVariable$var54$7 = index$sample55$5;
										double cv$probabilitySample55Value6 = (1.0 * state.distribution$sample55[((i$var46 - 1) / 1)][index$sample55$5]);
										{
											int traceTempVariable$var70$8_1 = distributionTempVariable$var54$7;
											if((i$var46 == (j + 1))) {
												{
													double var72 = (double)(1 / traceTempVariable$var70$8_1);
													double cv$weightedProbability = (Math.log(cv$probabilitySample55Value6) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((cv$sampleValue?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY));
													if((cv$weightedProbability < cv$distributionAccumulator))
														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
													else {
														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
															cv$distributionAccumulator = cv$weightedProbability;
														else
															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
													}
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample55Value6);
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
				state.logProbability$sample75[((j - 0) / 1)] = cv$sampleProbability;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample75 = state.fixedFlag$sample55;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.n; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample75[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample29() {
		if(!state.fixedProbFlag$sample29) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var28 = 0; var28 < state.states; var28 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double[] cv$sampleValue = state.m[var28];
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
			state.logProbability$var29 = cv$sampleAccumulator;
			state.logProbability$m = (state.logProbability$m + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample29)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample29 = state.fixedFlag$sample29;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var28 = 0; var28 < state.states; var28 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var29;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$m = (state.logProbability$m + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample29)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample55() {
		if(!state.fixedProbFlag$sample55) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$i$1 = i$var46;
				{
					{
						int cv$sampleValue = state.a[i$var46];
						{
							{
								double[] var52 = state.m[state.b[i$var46]];
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var52[cv$sampleValue])) && (var52[cv$sampleValue] <= 1.0))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample55[((i$var46 - 1) / 1)] = cv$sampleProbability;
				boolean cv$guard$b = false;
				int index$i$3 = i$var46;
				{
					{
						for(int index$i$4_1 = 1; index$i$4_1 < state.n; index$i$4_1 += 1) {
							if((i$var46 == (index$i$4_1 - 1))) {
								if(!cv$guard$b) {
									cv$guard$b = true;
									state.logProbability$b = (state.logProbability$b + cv$sampleProbability);
								}
							}
						}
					}
				}
			}
			state.logProbability$a = (state.logProbability$a + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample55)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample55 = (state.fixedFlag$sample55 && state.fixedFlag$sample29);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample55[((i$var46 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				boolean cv$guard$b = false;
				int index$i$5 = i$var46;
				{
					{
						for(int index$i$6_1 = 1; index$i$6_1 < state.n; index$i$6_1 += 1) {
							if((i$var46 == (index$i$6_1 - 1))) {
								if(!cv$guard$b) {
									cv$guard$b = true;
									state.logProbability$b = (state.logProbability$b + cv$sampleValue);
								}
							}
						}
					}
				}
			}
			state.logProbability$a = (state.logProbability$a + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample55)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample75() {
		if(!state.fixedProbFlag$sample75) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.n; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.flips[j];
						{
							{
								double var72 = (double)(1 / state.a[(j + 1)]);
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((cv$sampleValue?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample75[((j - 0) / 1)] = cv$sampleProbability;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample75 = state.fixedFlag$sample55;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.n; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample75[((j - 0) / 1)];
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
		for(int var28 = 0; var28 < state.states; var28 += 1) {
			double[] var29 = state.m[var28];
			if(!state.fixedFlag$sample29)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.states, var29);
		}
		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
			if(!state.fixedFlag$sample55)
				state.b[i$var46] = state.a[(i$var46 - 1)];
			if(!state.fixedFlag$sample55)
				state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], state.states);
		}
		for(int j = 0; j < state.n; j += 1)
			state.flips[j] = DistributionSampling.sampleBernoulli(state.RNG$, (1 / state.a[(j + 1)]));
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		for(int var28 = 0; var28 < state.states; var28 += 1) {
			double[] var29 = state.m[var28];
			if(!state.fixedFlag$sample29)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.states, var29);
		}
		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
			double[] cv$distribution$sample55 = state.distribution$sample55[((i$var46 - 1) / 1)];
			for(int index$var53 = 0; index$var53 < state.states; index$var53 += 1) {
				if(!state.fixedFlag$sample55)
					cv$distribution$sample55[index$var53] = 0.0;
			}
			{
				int traceTempVariable$var49$1_1 = 0;
				for(int index$i$1_2 = 1; index$i$1_2 < state.n; index$i$1_2 += 1) {
					if((0 == (index$i$1_2 - 1))) {
						int traceTempVariable$var51$1_3 = traceTempVariable$var49$1_1;
						if((index$i$1_2 == i$var46)) {
							{
								for(int var28 = 0; var28 < state.states; var28 += 1) {
									if((var28 == traceTempVariable$var51$1_3)) {
										{
											double[] var52 = state.m[traceTempVariable$var51$1_3];
											for(int index$var53 = 0; index$var53 < state.states; index$var53 += 1) {
												if(!state.fixedFlag$sample55)
													cv$distribution$sample55[index$var53] = (cv$distribution$sample55[index$var53] + (1.0 * ((((((0.0 <= index$var53) && (index$var53 < state.states)) && (0 < state.states)) && (0.0 <= var52[index$var53])) && (var52[index$var53] <= 1.0))?var52[index$var53]:0.0)));
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(state.fixedFlag$sample55) {
				{
					for(int index$i$3_1 = 1; index$i$3_1 < state.n; index$i$3_1 += 1) {
						for(int index$i$3_2 = 1; index$i$3_2 < state.n; index$i$3_2 += 1) {
							if((index$i$3_1 == (index$i$3_2 - 1))) {
								int traceTempVariable$var51$3_3 = state.a[(index$i$3_2 - 1)];
								if((index$i$3_2 == i$var46)) {
									{
										for(int var28 = 0; var28 < state.states; var28 += 1) {
											if((var28 == traceTempVariable$var51$3_3)) {
												{
													double[] var52 = state.m[traceTempVariable$var51$3_3];
													for(int index$var53 = 0; index$var53 < state.states; index$var53 += 1) {
														if(!state.fixedFlag$sample55)
															cv$distribution$sample55[index$var53] = (cv$distribution$sample55[index$var53] + (1.0 * ((((((0.0 <= index$var53) && (index$var53 < state.states)) && (0 < state.states)) && (0.0 <= var52[index$var53])) && (var52[index$var53] <= 1.0))?var52[index$var53]:0.0)));
													}
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
				for(int index$i$4 = 1; index$i$4 < state.n; index$i$4 += 1) {
					if(true) {
						for(int index$sample55$5 = 0; index$sample55$5 < state.states; index$sample55$5 += 1) {
							int distributionTempVariable$var54$7 = index$sample55$5;
							double cv$probabilitySample55Value6 = (1.0 * state.distribution$sample55[((index$i$4 - 1) / 1)][index$sample55$5]);
							{
								int traceTempVariable$var49$8_1 = distributionTempVariable$var54$7;
								for(int index$i$8_2 = 1; index$i$8_2 < state.n; index$i$8_2 += 1) {
									if((index$i$4 == (index$i$8_2 - 1))) {
										int traceTempVariable$var51$8_3 = traceTempVariable$var49$8_1;
										if((index$i$8_2 == i$var46)) {
											{
												for(int var28 = 0; var28 < state.states; var28 += 1) {
													if((var28 == traceTempVariable$var51$8_3)) {
														{
															double[] var52 = state.m[traceTempVariable$var51$8_3];
															for(int index$var53 = 0; index$var53 < state.states; index$var53 += 1) {
																if(!state.fixedFlag$sample55)
																	cv$distribution$sample55[index$var53] = (cv$distribution$sample55[index$var53] + (cv$probabilitySample55Value6 * ((((((0.0 <= index$var53) && (index$var53 < state.states)) && (0 < state.states)) && (0.0 <= var52[index$var53])) && (var52[index$var53] <= 1.0))?var52[index$var53]:0.0)));
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			double cv$var53$sum = 0.0;
			for(int index$var53 = 0; index$var53 < state.states; index$var53 += 1) {
				if(!state.fixedFlag$sample55)
					cv$var53$sum = (cv$var53$sum + cv$distribution$sample55[index$var53]);
			}
			for(int index$var53 = 0; index$var53 < state.states; index$var53 += 1) {
				if(!state.fixedFlag$sample55)
					cv$distribution$sample55[index$var53] = (cv$distribution$sample55[index$var53] / cv$var53$sum);
			}
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		for(int var28 = 0; var28 < state.states; var28 += 1) {
			double[] var29 = state.m[var28];
			if(!state.fixedFlag$sample29)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.states, var29);
		}
		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
			state.b[i$var46] = state.a[(i$var46 - 1)];
			if(!state.fixedFlag$sample55)
				state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], state.states);
		}
		for(int j = 0; j < state.n; j += 1)
			state.flips[j] = DistributionSampling.sampleBernoulli(state.RNG$, (1 / state.a[(j + 1)]));
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var28 = 0; var28 < state.states; var28 += 1) {
			double[] var29 = state.m[var28];
			if(!state.fixedFlag$sample29)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.states, var29);
		}
		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
			if(!state.fixedFlag$sample55)
				state.b[i$var46] = state.a[(i$var46 - 1)];
			if(!state.fixedFlag$sample55)
				state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], state.states);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		for(int var28 = 0; var28 < state.states; var28 += 1) {
			double[] var29 = state.m[var28];
			if(!state.fixedFlag$sample29)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.states, var29);
		}
		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
			state.b[i$var46] = state.a[(i$var46 - 1)];
			if(!state.fixedFlag$sample55)
				state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], state.states);
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			for(int var28 = 0; var28 < state.states; var28 += 1) {
				if(!state.fixedFlag$sample29)
					inferSample29(var28);
			}
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				if(!state.fixedFlag$sample55)
					inferSample55(i$var46);
			}
		} else {
			for(int i$var46 = (state.n - ((((state.n - 1) - 1) % 1) + 1)); i$var46 >= ((1 - 1) + 1); i$var46 -= 1) {
				if(!state.fixedFlag$sample55)
					inferSample55(i$var46);
			}
			for(int var28 = (state.states - ((((state.states - 1) - 0) % 1) + 1)); var28 >= ((0 - 1) + 1); var28 -= 1) {
				if(!state.fixedFlag$sample29)
					inferSample29(var28);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int var28 = 0; var28 < state.states; var28 += 1) {
			if(!state.constrainedFlag$sample29[((var28 - 0) / 1)])
				drawValueSample29(var28);
		}
		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
			if(!state.constrainedFlag$sample55[((i$var46 - 1) / 1)])
				drawValueSample55(i$var46);
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$m = 0.0;
		if(!state.fixedProbFlag$sample29)
			state.logProbability$var29 = Double.NaN;
		state.logProbability$a = 0.0;
		state.logProbability$b = 0.0;
		if(!state.fixedProbFlag$sample55) {
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1)
				state.logProbability$sample55[((i$var46 - 1) / 1)] = Double.NaN;
		}
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample75) {
			for(int j = 0; j < state.n; j += 1)
				state.logProbability$sample75[((j - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.states = 5;
		for(int i$var14 = 0; i$var14 < 5; i$var14 += 1)
			state.v[i$var14] = 0.1;
		state.a[0] = 0;
		for(int index$constrainedFlag$sample29$1 = 0; index$constrainedFlag$sample29$1 < state.constrainedFlag$sample29.length; index$constrainedFlag$sample29$1 += 1)
			state.constrainedFlag$sample29[index$constrainedFlag$sample29$1] = true;
		for(int index$constrainedFlag$sample55$1 = 0; index$constrainedFlag$sample55$1 < state.constrainedFlag$sample55.length; index$constrainedFlag$sample55$1 += 1)
			state.constrainedFlag$sample55[index$constrainedFlag$sample55$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample29)
			logProbabilityValue$sample29();
		logProbabilityValue$sample75();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample29();
		logProbabilityDistribution$sample55();
		logProbabilityDistribution$sample75();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample29();
		logProbabilityValue$sample55();
		logProbabilityValue$sample75();
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
	public final void setIntermediates() {
		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1)
			state.b[i$var46] = state.a[(i$var46 - 1)];
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
		     + "/**\n"
		     + " * A model for the fairness work.\n"
		     + " */\n"
		     + "public model Deterministic2(int n, boolean[] flipsMeasured) {\n"
		     + "    int states = 5;\n"
		     + "\n"
		     + "    double[] v = new double[states];\n"
		     + "    for(int i:[0..states))\n"
		     + "        v[i] = 0.1;\n"
		     + "    \n"
		     + "    double[][] m = dirichlet(v).sample(states);\n"
		     + "\n"
		     + "    int[] a = new int[n];\n"
		     + "    int[] b = new int[n];\n"
		     + "    a[0] = 0;\n"
		     + "    for(int i=1; i<n; i++) {\n"
		     + "        b[i] = a[i-1];\n"
		     + "        a[i] = categorical(m[b[i]]).sampleDistribution();\n"
		     + "    }\n"
		     + "    \n"
		     + "    boolean[] flips = new boolean[n];\n"
		     + "            \n"
		     + "    for(int j:[0..n))\n"
		     + "        flips[j] = bernoulli(1/a[j+1]).sample();\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}";
	}
}