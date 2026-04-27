package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.ReductionTest1$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.ReductionTest1.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class ReductionTest1$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public ReductionTest1$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample101(int i$var80, int var95) {
		double[] var86 = state.time_coeff[i$var80];
		var86[var95] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		{
			{
				for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
					if((i$var80 == i$var119)) {
						for(int j = 0; j < state.time_dim; j += 1) {
							if((var95 == j)) {
								for(int t = (0 + 1); t < state.T; t += 1) {
									double[][] var129 = state.time_impact[t];
									double[] var130 = var129[i$var119];
									var130[j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
								}
							}
						}
					}
				}
			}
		}
		{
			{
				for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
					if((i$var80 == i$var119)) {
						for(int j = 0; j < state.time_dim; j += 1) {
							if((var95 == j)) {
								for(int t = (0 + 1); t < state.T; t += 1) {
									for(int index$t$2_4 = (0 + 1); index$t$2_4 < state.T; index$t$2_4 += 1) {
										if((t == index$t$2_4)) {
											for(int index$i$2_5 = 0; index$i$2_5 < state.n_ac; index$i$2_5 += 1) {
												if((i$var119 == index$i$2_5)) {
													if(((0 <= j) && (j < state.time_dim))) {
														{
															double[] var139 = state.sum_t[index$t$2_4];
															double reduceVar$var151$3 = 0.0;
															for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1) {
																double x = reduceVar$var151$3;
																double y = state.time_impact[index$t$2_4][index$i$2_5][cv$reduction152Index];
																reduceVar$var151$3 = (x + y);
															}
															var139[index$i$2_5] = reduceVar$var151$3;
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample101(int i$var80, int var95) {
		if(true) {
			state.constrainedFlag$sample101[((i$var80 - 0) / 1)][((var95 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = state.time_coeff[i$var80][var95];
			double cv$originalProbability = 0.0;
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample101[((i$var80 - 0) / 1)][((var95 - 0) / 1)] || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						double var96 = cv$proposedValue;
						{
							{
								{
									double[] var86 = state.time_coeff[i$var80];
									var86[var95] = cv$currentValue;
								}
							}
						}
						{
							{
								for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
									if((i$var80 == i$var119)) {
										for(int j = 0; j < state.time_dim; j += 1) {
											if((var95 == j)) {
												for(int t = (0 + 1); t < state.T; t += 1) {
													double[][] var129 = state.time_impact[t];
													double[] var130 = var129[i$var119];
													var130[j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
												}
											}
										}
									}
								}
							}
						}
						{
							{
								for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
									if((i$var80 == i$var119)) {
										for(int j = 0; j < state.time_dim; j += 1) {
											if((var95 == j)) {
												for(int t = (0 + 1); t < state.T; t += 1) {
													for(int index$t$3_4 = (0 + 1); index$t$3_4 < state.T; index$t$3_4 += 1) {
														if((t == index$t$3_4)) {
															for(int index$i$3_5 = 0; index$i$3_5 < state.n_ac; index$i$3_5 += 1) {
																if((i$var119 == index$i$3_5)) {
																	if(((0 <= j) && (j < state.time_dim))) {
																		{
																			double[] var139 = state.sum_t[index$t$3_4];
																			double reduceVar$var151$0 = 0.0;
																			for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1) {
																				double x = reduceVar$var151$0;
																				double y = state.time_impact[index$t$3_4][index$i$3_5][cv$reduction152Index];
																				reduceVar$var151$0 = (x + y);
																			}
																			var139[index$i$3_5] = reduceVar$var151$0;
																		}
																	}
																}
															}
														}
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
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + ((0.0 < 1.0)?(DistributionSampling.logProbabilityGaussian(((cv$currentValue - 0.0) / Math.sqrt(1.0))) - (0.5 * Math.log(1.0))):Double.NEGATIVE_INFINITY));
						{
							{
								{
									double traceTempVariable$var134$4_1 = cv$currentValue;
									for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
										if((i$var80 == i$var119)) {
											for(int j = 0; j < state.time_dim; j += 1) {
												if((var95 == j)) {
													for(int t = (0 + 1); t < state.T; t += 1) {
														double traceTempVariable$x$4_5 = (state.TimeFeat[t][j] * traceTempVariable$var134$4_1);
														for(int index$t$4_6 = (0 + 1); index$t$4_6 < state.T; index$t$4_6 += 1) {
															if((t == index$t$4_6)) {
																for(int index$i$4_7 = 0; index$i$4_7 < state.n_ac; index$i$4_7 += 1) {
																	if((i$var119 == index$i$4_7)) {
																		if(((0 <= j) && (j < state.time_dim))) {
																			if((0 < state.time_dim)) {
																				double reduceVar$var151$1 = 0.0;
																				for(int cv$reduction332Index = 0; cv$reduction332Index < j; cv$reduction332Index += 1) {
																					double x = reduceVar$var151$1;
																					double y = state.time_impact[index$t$4_6][index$i$4_7][cv$reduction332Index];
																					reduceVar$var151$1 = (x + y);
																				}
																				for(int cv$reduction332Index = (j + 1); cv$reduction332Index < state.time_dim; cv$reduction332Index += 1) {
																					double x = reduceVar$var151$1;
																					double y = state.time_impact[index$t$4_6][index$i$4_7][cv$reduction332Index];
																					reduceVar$var151$1 = (x + y);
																				}
																				double cv$reduced152 = reduceVar$var151$1;
																				reduceVar$var151$1 = (traceTempVariable$x$4_5 + cv$reduced152);
																				double traceTempVariable$var151$4_8 = reduceVar$var151$1;
																				double traceTempVariable$var156$4_9 = traceTempVariable$var151$4_8;
																				for(int index$t$4_10 = (0 + 1); index$t$4_10 < state.T; index$t$4_10 += 1) {
																					if((index$t$4_6 == index$t$4_10)) {
																						for(int index$i$4_11 = 0; index$i$4_11 < state.n_ac; index$i$4_11 += 1) {
																							if((index$i$4_7 == index$i$4_11)) {
																								{
																									{
																										boolean cv$sampleConstrained = true;
																										if(cv$sampleConstrained) {
																											state.constrainedFlag$sample101[((i$var80 - 0) / 1)][((var95 - 0) / 1)] = true;
																											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																											double cv$consumerDistributionProbabilityAccumulator = 1.0;
																											{
																												{
																													{
																														{
																															{
																																if(((Math.log(1.0) + DistributionSampling.logProbabilityPoisson(state.arr[index$t$4_10][index$i$4_11], traceTempVariable$var156$4_9)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityPoisson(state.arr[index$t$4_10][index$i$4_11], traceTempVariable$var156$4_9)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(state.arr[index$t$4_10][index$i$4_11], traceTempVariable$var156$4_9));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(state.arr[index$t$4_10][index$i$4_11], traceTempVariable$var156$4_9)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(state.arr[index$t$4_10][index$i$4_11], traceTempVariable$var156$4_9)));
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
																}
															}
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
							double var96 = cv$originalValue;
							{
								{
									{
										double[] var86 = state.time_coeff[i$var80];
										var86[var95] = var96;
									}
								}
							}
							{
								{
									for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
										if((i$var80 == i$var119)) {
											for(int j = 0; j < state.time_dim; j += 1) {
												if((var95 == j)) {
													for(int t = (0 + 1); t < state.T; t += 1) {
														double[][] var129 = state.time_impact[t];
														double[] var130 = var129[i$var119];
														var130[j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
													}
												}
											}
										}
									}
								}
							}
							{
								{
									for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
										if((i$var80 == i$var119)) {
											for(int j = 0; j < state.time_dim; j += 1) {
												if((var95 == j)) {
													for(int t = (0 + 1); t < state.T; t += 1) {
														for(int index$t$9_4 = (0 + 1); index$t$9_4 < state.T; index$t$9_4 += 1) {
															if((t == index$t$9_4)) {
																for(int index$i$9_5 = 0; index$i$9_5 < state.n_ac; index$i$9_5 += 1) {
																	if((i$var119 == index$i$9_5)) {
																		if(((0 <= j) && (j < state.time_dim))) {
																			{
																				double[] var139 = state.sum_t[index$t$9_4];
																				double reduceVar$var151$2 = 0.0;
																				for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1) {
																					double x = reduceVar$var151$2;
																					double y = state.time_impact[index$t$9_4][index$i$9_5][cv$reduction152Index];
																					reduceVar$var151$2 = (x + y);
																				}
																				var139[index$i$9_5] = reduceVar$var151$2;
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample101() {
		if(!state.fixedProbFlag$sample101) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var95 = 0; var95 < state.time_dim; var95 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							double cv$sampleValue = state.time_coeff[i$var80][var95];
							{
								{
									double var83 = 0.0;
									double var84 = 1.0;
									double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var84)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var83) / Math.sqrt(var84))) - (0.5 * Math.log(var84))):Double.NEGATIVE_INFINITY));
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
					state.logProbability$sample101[((i$var80 - 0) / 1)][((var95 - 0) / 1)] = cv$sampleProbability;
					boolean cv$guard$time_impact = false;
					boolean cv$guard$sum_t = false;
					{
						{
							for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
								if((i$var80 == i$var119)) {
									for(int j = 0; j < state.time_dim; j += 1) {
										if((var95 == j)) {
											for(int t = (0 + 1); t < state.T; t += 1) {
												if(!cv$guard$time_impact) {
													cv$guard$time_impact = true;
													state.logProbability$time_impact = (state.logProbability$time_impact + cv$sampleProbability);
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
							for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
								if((i$var80 == i$var119)) {
									for(int j = 0; j < state.time_dim; j += 1) {
										if((var95 == j)) {
											for(int t = (0 + 1); t < state.T; t += 1) {
												for(int index$t$3_4 = (0 + 1); index$t$3_4 < state.T; index$t$3_4 += 1) {
													if((t == index$t$3_4)) {
														for(int index$i$3_5 = 0; index$i$3_5 < state.n_ac; index$i$3_5 += 1) {
															if((i$var119 == index$i$3_5)) {
																if(((0 <= j) && (j < state.time_dim))) {
																	if(!cv$guard$sum_t) {
																		cv$guard$sum_t = true;
																		state.logProbability$sum_t = (state.logProbability$sum_t + cv$sampleProbability);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			}
			state.logProbability$time_coeff = (state.logProbability$time_coeff + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample101)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample101 = state.fixedFlag$sample101;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
				double cv$rvAccumulator = 0.0;
				for(int var95 = 0; var95 < state.time_dim; var95 += 1) {
					double cv$sampleValue = state.logProbability$sample101[((i$var80 - 0) / 1)][((var95 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					boolean cv$guard$time_impact = false;
					boolean cv$guard$sum_t = false;
					{
						{
							for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
								if((i$var80 == i$var119)) {
									for(int j = 0; j < state.time_dim; j += 1) {
										if((var95 == j)) {
											for(int t = (0 + 1); t < state.T; t += 1) {
												if(!cv$guard$time_impact) {
													cv$guard$time_impact = true;
													state.logProbability$time_impact = (state.logProbability$time_impact + cv$sampleValue);
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
							for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
								if((i$var80 == i$var119)) {
									for(int j = 0; j < state.time_dim; j += 1) {
										if((var95 == j)) {
											for(int t = (0 + 1); t < state.T; t += 1) {
												for(int index$t$5_4 = (0 + 1); index$t$5_4 < state.T; index$t$5_4 += 1) {
													if((t == index$t$5_4)) {
														for(int index$i$5_5 = 0; index$i$5_5 < state.n_ac; index$i$5_5 += 1) {
															if((i$var119 == index$i$5_5)) {
																if(((0 <= j) && (j < state.time_dim))) {
																	if(!cv$guard$sum_t) {
																		cv$guard$sum_t = true;
																		state.logProbability$sum_t = (state.logProbability$sum_t + cv$sampleValue);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			state.logProbability$time_coeff = (state.logProbability$time_coeff + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample101)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample165() {
		if(!state.fixedProbFlag$sample165) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int t = (0 + 1); t < state.T; t += 1) {
				for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							int cv$sampleValue = state.arr[t][i$var119];
							{
								{
									double var156 = state.sum_t[t][i$var119];
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$sampleValue, var156));
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
					state.logProbability$sample165[((t - (0 + 1)) / 1)][((i$var119 - 0) / 1)] = cv$sampleProbability;
				}
			}
			state.logProbability$arr = (state.logProbability$arr + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample165 = state.fixedFlag$sample101;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int t = (0 + 1); t < state.T; t += 1) {
				for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = state.logProbability$sample165[((t - (0 + 1)) / 1)][((i$var119 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			state.logProbability$arr = (state.logProbability$arr + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
			double[] var86 = state.time_coeff[i$var80];
			for(int var95 = 0; var95 < state.time_dim; var95 += 1) {
				if(!state.fixedFlag$sample101)
					var86[var95] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
			}
		}
		for(int t = (0 + 1); t < state.T; t += 1) {
			double[][] var129 = state.time_impact[t];
			double[] var139 = state.sum_t[t];
			int[] var154 = state.arr[t];
			for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
				for(int j = 0; j < state.time_dim; j += 1) {
					double[] var130 = var129[i$var119];
					if(!state.fixedFlag$sample101)
						var130[j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
				}
				double reduceVar$var151$4 = 0.0;
				for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1) {
					double x = reduceVar$var151$4;
					double y = state.time_impact[t][i$var119][cv$reduction152Index];
					if(!state.fixedFlag$sample101)
						reduceVar$var151$4 = (x + y);
				}
				if(!state.fixedFlag$sample101)
					var139[i$var119] = reduceVar$var151$4;
				var154[i$var119] = DistributionSampling.samplePoisson(state.RNG$, state.sum_t[t][i$var119]);
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
			double[] var86 = state.time_coeff[i$var80];
			for(int var95 = 0; var95 < state.time_dim; var95 += 1) {
				if(!state.fixedFlag$sample101)
					var86[var95] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
			}
		}
		for(int t = (0 + 1); t < state.T; t += 1) {
			double[][] var129 = state.time_impact[t];
			double[] var139 = state.sum_t[t];
			for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
				for(int j = 0; j < state.time_dim; j += 1) {
					double[] var130 = var129[i$var119];
					var130[j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
				}
				double reduceVar$var151$8 = 0.0;
				for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1) {
					double x = reduceVar$var151$8;
					double y = state.time_impact[t][i$var119][cv$reduction152Index];
					reduceVar$var151$8 = (x + y);
				}
				var139[i$var119] = reduceVar$var151$8;
			}
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
			double[] var86 = state.time_coeff[i$var80];
			for(int var95 = 0; var95 < state.time_dim; var95 += 1) {
				if(!state.fixedFlag$sample101)
					var86[var95] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
			}
		}
		for(int t = (0 + 1); t < state.T; t += 1) {
			double[][] var129 = state.time_impact[t];
			double[] var139 = state.sum_t[t];
			int[] var154 = state.arr[t];
			for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
				for(int j = 0; j < state.time_dim; j += 1) {
					double[] var130 = var129[i$var119];
					var130[j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
				}
				double reduceVar$var151$5 = 0.0;
				for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1) {
					double x = reduceVar$var151$5;
					double y = state.time_impact[t][i$var119][cv$reduction152Index];
					reduceVar$var151$5 = (x + y);
				}
				var139[i$var119] = reduceVar$var151$5;
				var154[i$var119] = DistributionSampling.samplePoisson(state.RNG$, state.sum_t[t][i$var119]);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
			double[] var86 = state.time_coeff[i$var80];
			for(int var95 = 0; var95 < state.time_dim; var95 += 1) {
				if(!state.fixedFlag$sample101)
					var86[var95] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
			}
		}
		for(int t = (0 + 1); t < state.T; t += 1) {
			double[][] var129 = state.time_impact[t];
			double[] var139 = state.sum_t[t];
			for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
				for(int j = 0; j < state.time_dim; j += 1) {
					double[] var130 = var129[i$var119];
					if(!state.fixedFlag$sample101)
						var130[j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
				}
				double reduceVar$var151$6 = 0.0;
				for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1) {
					double x = reduceVar$var151$6;
					double y = state.time_impact[t][i$var119][cv$reduction152Index];
					if(!state.fixedFlag$sample101)
						reduceVar$var151$6 = (x + y);
				}
				if(!state.fixedFlag$sample101)
					var139[i$var119] = reduceVar$var151$6;
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
			double[] var86 = state.time_coeff[i$var80];
			for(int var95 = 0; var95 < state.time_dim; var95 += 1) {
				if(!state.fixedFlag$sample101)
					var86[var95] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
			}
		}
		for(int t = (0 + 1); t < state.T; t += 1) {
			double[][] var129 = state.time_impact[t];
			double[] var139 = state.sum_t[t];
			for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
				for(int j = 0; j < state.time_dim; j += 1) {
					double[] var130 = var129[i$var119];
					var130[j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
				}
				double reduceVar$var151$7 = 0.0;
				for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1) {
					double x = reduceVar$var151$7;
					double y = state.time_impact[t][i$var119][cv$reduction152Index];
					reduceVar$var151$7 = (x + y);
				}
				var139[i$var119] = reduceVar$var151$7;
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
				for(int var95 = 0; var95 < state.time_dim; var95 += 1) {
					if(!state.fixedFlag$sample101)
						inferSample101(i$var80, var95);
				}
			}
		} else {
			for(int i$var80 = (state.n_ac - ((((state.n_ac - 1) - 0) % 1) + 1)); i$var80 >= ((0 - 1) + 1); i$var80 -= 1) {
				for(int var95 = (state.time_dim - ((((state.time_dim - 1) - 0) % 1) + 1)); var95 >= ((0 - 1) + 1); var95 -= 1) {
					if(!state.fixedFlag$sample101)
						inferSample101(i$var80, var95);
				}
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
			for(int var95 = 0; var95 < state.time_dim; var95 += 1) {
				if(!state.constrainedFlag$sample101[((i$var80 - 0) / 1)][((var95 - 0) / 1)])
					drawValueSample101(i$var80, var95);
			}
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$time_coeff = 0.0;
		state.logProbability$time_impact = 0.0;
		state.logProbability$sum_t = 0.0;
		if(!state.fixedProbFlag$sample101) {
			for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
				for(int var95 = 0; var95 < state.time_dim; var95 += 1)
					state.logProbability$sample101[((i$var80 - 0) / 1)][((var95 - 0) / 1)] = Double.NaN;
			}
		}
		state.logProbability$arr = 0.0;
		if(!state.fixedProbFlag$sample165) {
			for(int t = (0 + 1); t < state.T; t += 1) {
				for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1)
					state.logProbability$sample165[((t - (0 + 1)) / 1)][((i$var119 - 0) / 1)] = Double.NaN;
			}
		}
	}

	@Override
	public final void initializeModel() {
		state.time_dim = state.TimeFeat[0].length;
		for(int index$constrainedFlag$sample101$1 = 0; index$constrainedFlag$sample101$1 < state.constrainedFlag$sample101.length; index$constrainedFlag$sample101$1 += 1) {
			boolean[] cv$constrainedFlag$sample101$1 = state.constrainedFlag$sample101[index$constrainedFlag$sample101$1];
			for(int index$constrainedFlag$sample101$2 = 0; index$constrainedFlag$sample101$2 < cv$constrainedFlag$sample101$1.length; index$constrainedFlag$sample101$2 += 1)
				cv$constrainedFlag$sample101$1[index$constrainedFlag$sample101$2] = true;
		}
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample101)
			logProbabilityValue$sample101();
		logProbabilityValue$sample165();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample101();
		logProbabilityValue$sample165();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample101();
		logProbabilityValue$sample165();
	}

	@Override
	public final void propagateObservedValues() {
		int[][] cv$source1 = state.ObsArr;
		int[][] cv$target1 = state.arr;
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
	public final void setIntermediates() {
		for(int t = (0 + 1); t < state.T; t += 1) {
			double[][] var129 = state.time_impact[t];
			double[] var139 = state.sum_t[t];
			for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
				for(int j = 0; j < state.time_dim; j += 1) {
					double[] var130 = var129[i$var119];
					var130[j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
				}
				double reduceVar$var151$9 = 0.0;
				for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1) {
					double x = reduceVar$var151$9;
					double y = state.time_impact[t][i$var119][cv$reduction152Index];
					reduceVar$var151$9 = (x + y);
				}
				var139[i$var119] = reduceVar$var151$9;
			}
		}
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
		     + " \n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model ReductionTest1(int T, int n_ac, int[][] ObsArr, double[][] TimeFeat) {\n"
		     + "    int time_dim = TimeFeat[0].length; //length of the feature vector\n"
		     + "\n"
		     + "\n"
		     + "    double[][] time_coeff = new double[n_ac][time_dim];\n"
		     + "    double[][] sum_t = new double[T][n_ac];\n"
		     + "    double[][][] time_impact = new double[T][n_ac][time_dim];\n"
		     + "    int[][] arr = new int[T][n_ac];\n"
		     + "    \n"
		     + "    for (int i : [0..n_ac))\n"
		     + "        time_coeff[i] = gaussian(0,1).sample(time_dim);\n"
		     + "\n"
		     + "    for (int t : (0..T)) {\n"
		     + "        for (int i : [0..n_ac)){\n"
		     + "            for (int j : [0..time_dim))\n"
		     + "                time_impact[t][i][j] = TimeFeat[t][j]*time_coeff[i][j];\n"
		     + "            //calculate sum\n"
		     + "            sum_t[t][i] = reduce(time_impact[t][i], 0, (x, y) -> { return x + y; });\n"
		     + "            arr[t][i] = poisson(sum_t[t][i]).sample();\n"
		     + "        }\n"
		     + "    }\n"
		     + "    arr.observe(ObsArr);\n"
		     + "}";
	}
}