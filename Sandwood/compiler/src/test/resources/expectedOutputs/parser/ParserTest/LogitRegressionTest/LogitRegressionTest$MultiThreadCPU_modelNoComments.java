package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.LogitRegressionTest$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.LogitRegressionTest.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class LogitRegressionTest$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
boolean[][] guard$sample35bernoulli93$global;
		boolean[][] guard$sample35put89$global;

		@Override
		public final void allocateScratch() {
			{
				int cv$max_i = 0;
				int cv$max_j$var85 = 0;
				for(int i = 0; i < state.x.length; i += 1)
					cv$max_j$var85 = Math.max(cv$max_j$var85, ((3 - 0) / 1));
				cv$max_i = Math.max(cv$max_i, ((state.x.length - 0) / 1));
				guard$sample35put89$global = new boolean[cv$max_i][cv$max_j$var85];
			}
			{
				int cv$max_i = 0;
				int cv$max_j$var85 = 0;
				for(int i = 0; i < state.x.length; i += 1)
					cv$max_j$var85 = Math.max(cv$max_j$var85, ((3 - 0) / 1));
				cv$max_i = Math.max(cv$max_i, ((state.x.length - 0) / 1));
				guard$sample35bernoulli93$global = new boolean[cv$max_i][cv$max_j$var85];
			}
		}
	}


	public LogitRegressionTest$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample35(int var33) {
		state.weights[var33] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		{
			{
				for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
					if((var33 == j$var61)) {
						for(int i = 0; i < state.n; i += 1)
							state.indicator[((i - 0) / 1)][j$var61] = Math.exp((state.weights[j$var61] * state.x[i][j$var61]));
					}
				}
			}
		}
		{
			boolean[][] guard$sample35put89 = scratch.guard$sample35put89$global;
			{
				for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
					if((var33 == j$var61)) {
						if((j$var61 == 0)) {
							for(int i = 0; i < state.n; i += 1) {
								for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1)
									guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
							}
						}
					}
				}
			}
			{
				for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
					if((var33 == j$var61)) {
						if((j$var61 == 1)) {
							for(int i = 0; i < state.n; i += 1) {
								for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1)
									guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
							}
						}
					}
				}
			}
			{
				for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
					if((var33 == j$var61)) {
						if((j$var61 == 2)) {
							for(int i = 0; i < state.n; i += 1) {
								for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1)
									guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
							}
						}
					}
				}
			}
			{
				for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
					if((var33 == j$var61)) {
						for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
							if((j$var61 == j$var85)) {
								for(int i = 0; i < state.n; i += 1)
									guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
							}
						}
					}
				}
			}
			{
				for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
					if((var33 == j$var61)) {
						if((j$var61 == 0)) {
							for(int i = 0; i < state.n; i += 1) {
								for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
									if(!guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
										guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
										{
											state.p[((i - 0) / 1)][j$var85] = (state.indicator[((i - 0) / 1)][j$var85] / ((state.indicator[((i - 0) / 1)][0] + state.indicator[((i - 0) / 1)][1]) + state.indicator[((i - 0) / 1)][2]));
										}
									}
								}
							}
						}
					}
				}
			}
			{
				for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
					if((var33 == j$var61)) {
						if((j$var61 == 1)) {
							for(int i = 0; i < state.n; i += 1) {
								for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
									if(!guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
										guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
										{
											state.p[((i - 0) / 1)][j$var85] = (state.indicator[((i - 0) / 1)][j$var85] / ((state.indicator[((i - 0) / 1)][0] + state.indicator[((i - 0) / 1)][1]) + state.indicator[((i - 0) / 1)][2]));
										}
									}
								}
							}
						}
					}
				}
			}
			{
				for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
					if((var33 == j$var61)) {
						if((j$var61 == 2)) {
							for(int i = 0; i < state.n; i += 1) {
								for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
									if(!guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
										guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
										{
											state.p[((i - 0) / 1)][j$var85] = (state.indicator[((i - 0) / 1)][j$var85] / ((state.indicator[((i - 0) / 1)][0] + state.indicator[((i - 0) / 1)][1]) + state.indicator[((i - 0) / 1)][2]));
										}
									}
								}
							}
						}
					}
				}
			}
			{
				for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
					if((var33 == j$var61)) {
						for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
							if((j$var61 == j$var85)) {
								for(int i = 0; i < state.n; i += 1) {
									if(!guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
										guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
										{
											state.p[((i - 0) / 1)][j$var85] = (state.indicator[((i - 0) / 1)][j$var85] / ((state.indicator[((i - 0) / 1)][0] + state.indicator[((i - 0) / 1)][1]) + state.indicator[((i - 0) / 1)][2]));
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void drawValueSample42() {
		state.bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
	}

	private final void inferSample35(int var33) {
		if(true) {
			state.constrainedFlag$sample35[((var33 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = state.weights[var33];
			double cv$originalProbability = 0.0;
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			if((cv$var < 0.01))
				cv$var = 0.01;
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample35[((var33 - 0) / 1)] || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						double var34 = cv$proposedValue;
						{
							{
								{
									state.weights[var33] = cv$currentValue;
								}
							}
						}
						{
							{
								for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
									if((var33 == j$var61)) {
										for(int i = 0; i < state.n; i += 1)
											state.indicator[((i - 0) / 1)][j$var61] = Math.exp((state.weights[j$var61] * state.x[i][j$var61]));
									}
								}
							}
						}
						{
							boolean[][] guard$sample35put89 = scratch.guard$sample35put89$global;
							{
								for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
									if((var33 == j$var61)) {
										if((j$var61 == 0)) {
											for(int i = 0; i < state.n; i += 1) {
												for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1)
													guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
											}
										}
									}
								}
							}
							{
								for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
									if((var33 == j$var61)) {
										if((j$var61 == 1)) {
											for(int i = 0; i < state.n; i += 1) {
												for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1)
													guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
											}
										}
									}
								}
							}
							{
								for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
									if((var33 == j$var61)) {
										if((j$var61 == 2)) {
											for(int i = 0; i < state.n; i += 1) {
												for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1)
													guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
											}
										}
									}
								}
							}
							{
								for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
									if((var33 == j$var61)) {
										for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
											if((j$var61 == j$var85)) {
												for(int i = 0; i < state.n; i += 1)
													guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
											}
										}
									}
								}
							}
							{
								for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
									if((var33 == j$var61)) {
										if((j$var61 == 0)) {
											for(int i = 0; i < state.n; i += 1) {
												for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
													if(!guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
														guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
														{
															state.p[((i - 0) / 1)][j$var85] = (state.indicator[((i - 0) / 1)][j$var85] / ((state.indicator[((i - 0) / 1)][0] + state.indicator[((i - 0) / 1)][1]) + state.indicator[((i - 0) / 1)][2]));
														}
													}
												}
											}
										}
									}
								}
							}
							{
								for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
									if((var33 == j$var61)) {
										if((j$var61 == 1)) {
											for(int i = 0; i < state.n; i += 1) {
												for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
													if(!guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
														guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
														{
															state.p[((i - 0) / 1)][j$var85] = (state.indicator[((i - 0) / 1)][j$var85] / ((state.indicator[((i - 0) / 1)][0] + state.indicator[((i - 0) / 1)][1]) + state.indicator[((i - 0) / 1)][2]));
														}
													}
												}
											}
										}
									}
								}
							}
							{
								for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
									if((var33 == j$var61)) {
										if((j$var61 == 2)) {
											for(int i = 0; i < state.n; i += 1) {
												for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
													if(!guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
														guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
														{
															state.p[((i - 0) / 1)][j$var85] = (state.indicator[((i - 0) / 1)][j$var85] / ((state.indicator[((i - 0) / 1)][0] + state.indicator[((i - 0) / 1)][1]) + state.indicator[((i - 0) / 1)][2]));
														}
													}
												}
											}
										}
									}
								}
							}
							{
								for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
									if((var33 == j$var61)) {
										for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
											if((j$var61 == j$var85)) {
												for(int i = 0; i < state.n; i += 1) {
													if(!guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
														guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
														{
															state.p[((i - 0) / 1)][j$var85] = (state.indicator[((i - 0) / 1)][j$var85] / ((state.indicator[((i - 0) / 1)][0] + state.indicator[((i - 0) / 1)][1]) + state.indicator[((i - 0) / 1)][2]));
														}
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
						double cv$accumulatedProbabilities = (Math.log(1.0) + ((0.0 < 10.0)?(DistributionSampling.logProbabilityGaussian(((cv$currentValue - 0.0) / Math.sqrt(10.0))) - (0.5 * Math.log(10.0))):Double.NEGATIVE_INFINITY));
						{
							{
								boolean[][] guard$sample35bernoulli93 = scratch.guard$sample35bernoulli93$global;
								{
									for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
										if((var33 == j$var61)) {
											if((j$var61 == 0)) {
												for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
													for(int index$j$11_3 = 0; index$j$11_3 < state.k; index$j$11_3 += 1) {
														if((j$var85 == index$j$11_3)) {
															for(int i = 0; i < state.n; i += 1)
																guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
														}
													}
												}
											}
										}
									}
								}
								{
									for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
										if((var33 == j$var61)) {
											if((j$var61 == 1)) {
												for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
													for(int index$j$12_3 = 0; index$j$12_3 < state.k; index$j$12_3 += 1) {
														if((j$var85 == index$j$12_3)) {
															for(int i = 0; i < state.n; i += 1)
																guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
														}
													}
												}
											}
										}
									}
								}
								{
									for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
										if((var33 == j$var61)) {
											if((j$var61 == 2)) {
												for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
													for(int index$j$13_3 = 0; index$j$13_3 < state.k; index$j$13_3 += 1) {
														if((j$var85 == index$j$13_3)) {
															for(int i = 0; i < state.n; i += 1)
																guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
														}
													}
												}
											}
										}
									}
								}
								{
									for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
										if((var33 == j$var61)) {
											for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
												if((j$var61 == j$var85)) {
													for(int index$j$14_3 = 0; index$j$14_3 < state.k; index$j$14_3 += 1) {
														if((j$var85 == index$j$14_3)) {
															for(int i = 0; i < state.n; i += 1)
																guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
														}
													}
												}
											}
										}
									}
								}
								{
									double traceTempVariable$var62$15_1 = cv$currentValue;
									for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
										if((var33 == j$var61)) {
											for(int i = 0; i < state.n; i += 1) {
												double traceTempVariable$var69$15_4 = Math.exp((traceTempVariable$var62$15_1 * state.x[i][j$var61]));
												if((j$var61 == 0)) {
													for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
														double traceTempVariable$var90$15_6 = (state.indicator[((i - 0) / 1)][j$var85] / ((traceTempVariable$var69$15_4 + state.indicator[((i - 0) / 1)][1]) + state.indicator[((i - 0) / 1)][2]));
														for(int index$j$15_7 = 0; index$j$15_7 < state.k; index$j$15_7 += 1) {
															if((j$var85 == index$j$15_7)) {
																if(!guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
																	guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
																	{
																		{
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				state.constrainedFlag$sample35[((var33 - 0) / 1)] = true;
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						{
																							{
																								{
																									double var91 = (traceTempVariable$var90$15_6 + state.bias);
																									if(((Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][index$j$15_7]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][index$j$15_7]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][index$j$15_7]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][index$j$15_7]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][index$j$15_7]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY)));
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
								{
									double traceTempVariable$var62$16_1 = cv$currentValue;
									for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
										if((var33 == j$var61)) {
											for(int i = 0; i < state.n; i += 1) {
												double traceTempVariable$var71$16_4 = Math.exp((traceTempVariable$var62$16_1 * state.x[i][j$var61]));
												if((j$var61 == 1)) {
													for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
														double traceTempVariable$var90$16_6 = (state.indicator[((i - 0) / 1)][j$var85] / ((state.indicator[((i - 0) / 1)][0] + traceTempVariable$var71$16_4) + state.indicator[((i - 0) / 1)][2]));
														for(int index$j$16_7 = 0; index$j$16_7 < state.k; index$j$16_7 += 1) {
															if((j$var85 == index$j$16_7)) {
																if(!guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
																	guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
																	{
																		{
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				state.constrainedFlag$sample35[((var33 - 0) / 1)] = true;
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						{
																							{
																								{
																									double var91 = (traceTempVariable$var90$16_6 + state.bias);
																									if(((Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][index$j$16_7]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][index$j$16_7]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][index$j$16_7]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][index$j$16_7]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][index$j$16_7]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY)));
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
								{
									double traceTempVariable$var62$17_1 = cv$currentValue;
									for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
										if((var33 == j$var61)) {
											for(int i = 0; i < state.n; i += 1) {
												double traceTempVariable$var74$17_4 = Math.exp((traceTempVariable$var62$17_1 * state.x[i][j$var61]));
												if((j$var61 == 2)) {
													for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
														double traceTempVariable$var90$17_6 = (state.indicator[((i - 0) / 1)][j$var85] / ((state.indicator[((i - 0) / 1)][0] + state.indicator[((i - 0) / 1)][1]) + traceTempVariable$var74$17_4));
														for(int index$j$17_7 = 0; index$j$17_7 < state.k; index$j$17_7 += 1) {
															if((j$var85 == index$j$17_7)) {
																if(!guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
																	guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
																	{
																		{
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				state.constrainedFlag$sample35[((var33 - 0) / 1)] = true;
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						{
																							{
																								{
																									double var91 = (traceTempVariable$var90$17_6 + state.bias);
																									if(((Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][index$j$17_7]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][index$j$17_7]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][index$j$17_7]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][index$j$17_7]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][index$j$17_7]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY)));
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
								{
									double traceTempVariable$var62$18_1 = cv$currentValue;
									for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
										if((var33 == j$var61)) {
											for(int i = 0; i < state.n; i += 1) {
												double traceTempVariable$var86$18_4 = Math.exp((traceTempVariable$var62$18_1 * state.x[i][j$var61]));
												for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
													if((j$var61 == j$var85)) {
														double traceTempVariable$var90$18_6 = (traceTempVariable$var86$18_4 / ((state.indicator[((i - 0) / 1)][0] + state.indicator[((i - 0) / 1)][1]) + state.indicator[((i - 0) / 1)][2]));
														for(int index$j$18_7 = 0; index$j$18_7 < state.k; index$j$18_7 += 1) {
															if((j$var85 == index$j$18_7)) {
																if(!guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
																	guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
																	{
																		{
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				state.constrainedFlag$sample35[((var33 - 0) / 1)] = true;
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						{
																							{
																								{
																									double var91 = (traceTempVariable$var90$18_6 + state.bias);
																									if(((Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][index$j$18_7]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][index$j$18_7]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][index$j$18_7]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][index$j$18_7]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][index$j$18_7]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY)));
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
							double var34 = cv$originalValue;
							{
								{
									{
										state.weights[var33] = var34;
									}
								}
							}
							{
								{
									for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
										if((var33 == j$var61)) {
											for(int i = 0; i < state.n; i += 1)
												state.indicator[((i - 0) / 1)][j$var61] = Math.exp((state.weights[j$var61] * state.x[i][j$var61]));
										}
									}
								}
							}
							{
								boolean[][] guard$sample35put89 = scratch.guard$sample35put89$global;
								{
									for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
										if((var33 == j$var61)) {
											if((j$var61 == 0)) {
												for(int i = 0; i < state.n; i += 1) {
													for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1)
														guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
												}
											}
										}
									}
								}
								{
									for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
										if((var33 == j$var61)) {
											if((j$var61 == 1)) {
												for(int i = 0; i < state.n; i += 1) {
													for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1)
														guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
												}
											}
										}
									}
								}
								{
									for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
										if((var33 == j$var61)) {
											if((j$var61 == 2)) {
												for(int i = 0; i < state.n; i += 1) {
													for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1)
														guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
												}
											}
										}
									}
								}
								{
									for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
										if((var33 == j$var61)) {
											for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
												if((j$var61 == j$var85)) {
													for(int i = 0; i < state.n; i += 1)
														guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
												}
											}
										}
									}
								}
								{
									for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
										if((var33 == j$var61)) {
											if((j$var61 == 0)) {
												for(int i = 0; i < state.n; i += 1) {
													for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
														if(!guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
															guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
															{
																state.p[((i - 0) / 1)][j$var85] = (state.indicator[((i - 0) / 1)][j$var85] / ((state.indicator[((i - 0) / 1)][0] + state.indicator[((i - 0) / 1)][1]) + state.indicator[((i - 0) / 1)][2]));
															}
														}
													}
												}
											}
										}
									}
								}
								{
									for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
										if((var33 == j$var61)) {
											if((j$var61 == 1)) {
												for(int i = 0; i < state.n; i += 1) {
													for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
														if(!guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
															guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
															{
																state.p[((i - 0) / 1)][j$var85] = (state.indicator[((i - 0) / 1)][j$var85] / ((state.indicator[((i - 0) / 1)][0] + state.indicator[((i - 0) / 1)][1]) + state.indicator[((i - 0) / 1)][2]));
															}
														}
													}
												}
											}
										}
									}
								}
								{
									for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
										if((var33 == j$var61)) {
											if((j$var61 == 2)) {
												for(int i = 0; i < state.n; i += 1) {
													for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
														if(!guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
															guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
															{
																state.p[((i - 0) / 1)][j$var85] = (state.indicator[((i - 0) / 1)][j$var85] / ((state.indicator[((i - 0) / 1)][0] + state.indicator[((i - 0) / 1)][1]) + state.indicator[((i - 0) / 1)][2]));
															}
														}
													}
												}
											}
										}
									}
								}
								{
									for(int j$var61 = 0; j$var61 < state.k; j$var61 += 1) {
										if((var33 == j$var61)) {
											for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
												if((j$var61 == j$var85)) {
													for(int i = 0; i < state.n; i += 1) {
														if(!guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
															guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
															{
																state.p[((i - 0) / 1)][j$var85] = (state.indicator[((i - 0) / 1)][j$var85] / ((state.indicator[((i - 0) / 1)][0] + state.indicator[((i - 0) / 1)][1]) + state.indicator[((i - 0) / 1)][2]));
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample42() {
		if(true) {
			state.constrainedFlag$sample42 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = state.bias;
			double cv$originalProbability = 0.0;
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			if((cv$var < 0.01))
				cv$var = 0.01;
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample42 || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						state.bias = cv$proposedValue;
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + ((0.0 < 10.0)?(DistributionSampling.logProbabilityGaussian(((cv$currentValue - 0.0) / Math.sqrt(10.0))) - (0.5 * Math.log(10.0))):Double.NEGATIVE_INFINITY));
						{
							{
								{
									for(int i = 0; i < state.n; i += 1) {
										for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
											double traceTempVariable$bias$1_3 = cv$currentValue;
											{
												{
													boolean cv$sampleConstrained = true;
													if(cv$sampleConstrained) {
														state.constrainedFlag$sample42 = true;
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															{
																{
																	{
																		{
																			double var91 = (state.p[((i - 0) / 1)][j$var85] + traceTempVariable$bias$1_3);
																			if(((Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][j$var85]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][j$var85]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][j$var85]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][j$var85]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((state.y[i][j$var85]?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY)));
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
					if((cv$valuePos == 0))
						cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
					else
						cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
					double cv$ratio = (cv$proposedProbability - cv$originalProbability);
					if((cv$valuePos == 1)) {
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$))))) || Double.isNaN(cv$ratio)))
							state.bias = cv$originalValue;
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!state.fixedProbFlag$sample35) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var33 = 0; var33 < state.k; var33 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.weights[var33];
						{
							{
								double var20 = 0.0;
								double var21 = 10.0;
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var21)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var20) / Math.sqrt(var21))) - (0.5 * Math.log(var21))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample35[((var33 - 0) / 1)] = cv$sampleProbability;
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			state.logProbability$weights = (state.logProbability$weights + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample35)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample35 = state.fixedFlag$sample35;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var33 = 0; var33 < state.k; var33 += 1) {
				double cv$sampleValue = state.logProbability$sample35[((var33 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$weights = (state.logProbability$weights + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample35)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample42() {
		if(!state.fixedProbFlag$sample42) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = state.bias;
					{
						{
							double var38 = 0.0;
							double var39 = 10.0;
							double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var39)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var38) / Math.sqrt(var39))) - (0.5 * Math.log(var39))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$bias = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample42)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample42 = state.fixedFlag$sample42;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$bias;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample42)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample94() {
		if(!state.fixedProbFlag$sample94) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < state.n; i += 1) {
				for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							boolean cv$sampleValue = state.y[i][j$var85];
							{
								{
									double var91 = (state.p[((i - 0) / 1)][j$var85] + state.bias);
									double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var91) && (var91 <= 1.0))?Math.log((cv$sampleValue?var91:(1.0 - var91))):Double.NEGATIVE_INFINITY));
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
					state.logProbability$sample94[((i - 0) / 1)][((j$var85 - 0) / 1)] = cv$sampleProbability;
				}
			}
			state.logProbability$y = (state.logProbability$y + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample94 = (state.fixedFlag$sample35 && state.fixedFlag$sample42);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < state.n; i += 1) {
				for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = state.logProbability$sample94[((i - 0) / 1)][((j$var85 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			state.logProbability$y = (state.logProbability$y + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(state.RNG$, 0, state.k, 1,
			(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
						if(!state.fixedFlag$sample35)
							state.weights[var33] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!state.fixedFlag$sample42)
			state.bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						parallelFor(RNG$1, 0, state.k, 1,
							(int forStart$j$var61, int forEnd$j$var61, int threadID$j$var61, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var61 = forStart$j$var61; j$var61 < forEnd$j$var61; j$var61 += 1) {
										if(!state.fixedFlag$sample35)
											state.indicator[((i - 0) / 1)][j$var61] = Math.exp((state.weights[j$var61] * state.x[i][j$var61]));
									}
							}
						);
						boolean[] var89 = state.y[i];
						parallelFor(RNG$1, 0, state.k, 1,
							(int forStart$j$var85, int forEnd$j$var85, int threadID$j$var85, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var85 = forStart$j$var85; j$var85 < forEnd$j$var85; j$var85 += 1) {
										if(!state.fixedFlag$sample35)
											state.p[((i - 0) / 1)][j$var85] = (state.indicator[((i - 0) / 1)][j$var85] / ((state.indicator[((i - 0) / 1)][0] + state.indicator[((i - 0) / 1)][1]) + state.indicator[((i - 0) / 1)][2]));
										var89[j$var85] = DistributionSampling.sampleBernoulli(RNG$2, (state.p[((i - 0) / 1)][j$var85] + state.bias));
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		parallelFor(state.RNG$, 0, state.k, 1,
			(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
						if(!state.fixedFlag$sample35)
							state.weights[var33] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!state.fixedFlag$sample42)
			state.bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						parallelFor(RNG$1, 0, state.k, 1,
							(int forStart$j$var61, int forEnd$j$var61, int threadID$j$var61, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var61 = forStart$j$var61; j$var61 < forEnd$j$var61; j$var61 += 1)
										state.indicator[((i - 0) / 1)][j$var61] = Math.exp((state.weights[j$var61] * state.x[i][j$var61]));
							}
						);
						parallelFor(RNG$1, 0, state.k, 1,
							(int forStart$j$var85, int forEnd$j$var85, int threadID$j$var85, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var85 = forStart$j$var85; j$var85 < forEnd$j$var85; j$var85 += 1)
										state.p[((i - 0) / 1)][j$var85] = (state.indicator[((i - 0) / 1)][j$var85] / ((state.indicator[((i - 0) / 1)][0] + state.indicator[((i - 0) / 1)][1]) + state.indicator[((i - 0) / 1)][2]));
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		parallelFor(state.RNG$, 0, state.k, 1,
			(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
						if(!state.fixedFlag$sample35)
							state.weights[var33] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!state.fixedFlag$sample42)
			state.bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						parallelFor(RNG$1, 0, state.k, 1,
							(int forStart$j$var61, int forEnd$j$var61, int threadID$j$var61, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var61 = forStart$j$var61; j$var61 < forEnd$j$var61; j$var61 += 1)
										state.indicator[((i - 0) / 1)][j$var61] = Math.exp((state.weights[j$var61] * state.x[i][j$var61]));
							}
						);
						boolean[] var89 = state.y[i];
						parallelFor(RNG$1, 0, state.k, 1,
							(int forStart$j$var85, int forEnd$j$var85, int threadID$j$var85, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var85 = forStart$j$var85; j$var85 < forEnd$j$var85; j$var85 += 1) {
										state.p[((i - 0) / 1)][j$var85] = (state.indicator[((i - 0) / 1)][j$var85] / ((state.indicator[((i - 0) / 1)][0] + state.indicator[((i - 0) / 1)][1]) + state.indicator[((i - 0) / 1)][2]));
										var89[j$var85] = DistributionSampling.sampleBernoulli(RNG$2, (state.p[((i - 0) / 1)][j$var85] + state.bias));
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(state.RNG$, 0, state.k, 1,
			(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
						if(!state.fixedFlag$sample35)
							state.weights[var33] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!state.fixedFlag$sample42)
			state.bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						parallelFor(RNG$1, 0, state.k, 1,
							(int forStart$j$var61, int forEnd$j$var61, int threadID$j$var61, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var61 = forStart$j$var61; j$var61 < forEnd$j$var61; j$var61 += 1) {
										if(!state.fixedFlag$sample35)
											state.indicator[((i - 0) / 1)][j$var61] = Math.exp((state.weights[j$var61] * state.x[i][j$var61]));
									}
							}
						);
						parallelFor(RNG$1, 0, state.k, 1,
							(int forStart$j$var85, int forEnd$j$var85, int threadID$j$var85, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var85 = forStart$j$var85; j$var85 < forEnd$j$var85; j$var85 += 1) {
										if(!state.fixedFlag$sample35)
											state.p[((i - 0) / 1)][j$var85] = (state.indicator[((i - 0) / 1)][j$var85] / ((state.indicator[((i - 0) / 1)][0] + state.indicator[((i - 0) / 1)][1]) + state.indicator[((i - 0) / 1)][2]));
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		parallelFor(state.RNG$, 0, state.k, 1,
			(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
						if(!state.fixedFlag$sample35)
							state.weights[var33] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!state.fixedFlag$sample42)
			state.bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						parallelFor(RNG$1, 0, state.k, 1,
							(int forStart$j$var61, int forEnd$j$var61, int threadID$j$var61, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var61 = forStart$j$var61; j$var61 < forEnd$j$var61; j$var61 += 1)
										state.indicator[((i - 0) / 1)][j$var61] = Math.exp((state.weights[j$var61] * state.x[i][j$var61]));
							}
						);
						parallelFor(RNG$1, 0, state.k, 1,
							(int forStart$j$var85, int forEnd$j$var85, int threadID$j$var85, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var85 = forStart$j$var85; j$var85 < forEnd$j$var85; j$var85 += 1)
										state.p[((i - 0) / 1)][j$var85] = (state.indicator[((i - 0) / 1)][j$var85] / ((state.indicator[((i - 0) / 1)][0] + state.indicator[((i - 0) / 1)][1]) + state.indicator[((i - 0) / 1)][2]));
							}
						);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			for(int var33 = 0; var33 < state.k; var33 += 1) {
				if(!state.fixedFlag$sample35)
					inferSample35(var33);
			}
			if(!state.fixedFlag$sample42)
				inferSample42();
		} else {
			if(!state.fixedFlag$sample42)
				inferSample42();
			for(int var33 = (state.k - ((((state.k - 1) - 0) % 1) + 1)); var33 >= ((0 - 1) + 1); var33 -= 1) {
				if(!state.fixedFlag$sample35)
					inferSample35(var33);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int var33 = 0; var33 < state.k; var33 += 1) {
			if(!state.constrainedFlag$sample35[((var33 - 0) / 1)])
				drawValueSample35(var33);
		}
		if(!state.constrainedFlag$sample42)
			drawValueSample42();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$weights = 0.0;
		if(!state.fixedProbFlag$sample35) {
			for(int var33 = 0; var33 < state.k; var33 += 1)
				state.logProbability$sample35[((var33 - 0) / 1)] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample42)
			state.logProbability$bias = Double.NaN;
		state.logProbability$y = 0.0;
		if(!state.fixedProbFlag$sample94) {
			for(int i = 0; i < state.n; i += 1) {
				for(int j$var85 = 0; j$var85 < state.k; j$var85 += 1)
					state.logProbability$sample94[((i - 0) / 1)][((j$var85 - 0) / 1)] = Double.NaN;
			}
		}
	}

	@Override
	public final void initializeModel() {
		state.k = 3;
		state.n = state.x.length;
		for(int index$constrainedFlag$sample35$1 = 0; index$constrainedFlag$sample35$1 < state.constrainedFlag$sample35.length; index$constrainedFlag$sample35$1 += 1)
			state.constrainedFlag$sample35[index$constrainedFlag$sample35$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample35)
			logProbabilityValue$sample35();
		if(state.fixedFlag$sample42)
			logProbabilityValue$sample42();
		logProbabilityValue$sample94();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample35();
		logProbabilityValue$sample42();
		logProbabilityValue$sample94();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample35();
		logProbabilityValue$sample42();
		logProbabilityValue$sample94();
	}

	@Override
	public final void propagateObservedValues() {
		boolean[][] cv$source1 = state.yMeasured;
		boolean[][] cv$target1 = state.y;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[] cv$source2 = cv$source1[cv$index1];
			boolean[] cv$target2 = cv$target1[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	@Override
	public final void setIntermediates() {
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						parallelFor(RNG$1, 0, state.k, 1,
							(int forStart$j$var61, int forEnd$j$var61, int threadID$j$var61, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var61 = forStart$j$var61; j$var61 < forEnd$j$var61; j$var61 += 1)
										state.indicator[((i - 0) / 1)][j$var61] = Math.exp((state.weights[j$var61] * state.x[i][j$var61]));
							}
						);
						parallelFor(RNG$1, 0, state.k, 1,
							(int forStart$j$var85, int forEnd$j$var85, int threadID$j$var85, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var85 = forStart$j$var85; j$var85 < forEnd$j$var85; j$var85 += 1)
										state.p[((i - 0) / 1)][j$var85] = (state.indicator[((i - 0) / 1)][j$var85] / ((state.indicator[((i - 0) / 1)][0] + state.indicator[((i - 0) / 1)][1]) + state.indicator[((i - 0) / 1)][2]));
							}
						);
					}
			}
		);
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
		     + "model LogitRegressionTest(double[][] x, boolean[][] yMeasured) {\n"
		     + "    int k = 3;\n"
		     + "\n"
		     + "    int n = x.length;\n"
		     + "    boolean[][] y = new boolean[n][k];\n"
		     + "\n"
		     + "    double[] weights = gaussian(0,10).sample(k);\n"
		     + "    //TODO, change this to a beta distribution.\n"
		     + "    double bias = gaussian(0,10).sample();\n"
		     + "\n"
		     + "    for(int i:[0 .. n)) {\n"
		     + "        double[] indicator = new double[k];\n"
		     + "        for(int j:[0 .. k)) {\n"
		     + "            indicator[j] = exp(weights[j] * x[i][j]);\n"
		     + "        }\n"
		     + "        \n"
		     + "        //Single assignment semantics means a for loop cannot be used here.\n"
		     + "        double sum = indicator[0] + indicator[1] + indicator[2];\n"
		     + "        double[] p = new double[k];\n"
		     + "\n"
		     + "        for(int j:[0 .. k)) {\n"
		     + "            p[j] = indicator[j]/sum;\n"
		     + "            //This really wants to be a Categorical, but for now y will have\n"
		     + "            //to be arrays with just a single value set.\n"
		     + "            y[i][j] = bernoulli(p[j] + bias).sample();\n"
		     + "        }    \n"
		     + "    }\n"
		     + "\n"
		     + "    y.observe(yMeasured);\n"
		     + "}\n"
		     + "";
	}
}