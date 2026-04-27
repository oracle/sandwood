package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK15$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK15.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK15$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK15$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample8() {
		state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		{
			{
				if(!state.guard1) {
					{
						state.c[0] = (state.b / 2);
					}
				}
			}
		}
		{
			{
				if(!state.guard1) {
					{
						state.c[1] = (state.b / 2);
					}
				}
			}
		}
		{
			boolean guard$sample8if37 = false;
			{
				if(state.guard1) {
					if(!guard$sample8if37) {
						guard$sample8if37 = true;
						{
							state.bias = state.b;
						}
					}
				}
			}
			{
				if(!state.guard1) {
					if(((0 <= 0) && (0 < 2))) {
						if(!state.guard1) {
							if(!guard$sample8if37) {
								guard$sample8if37 = true;
								{
									double reduceVar$var33$20 = 0.0;
									for(int cv$reduction30Index = 0; cv$reduction30Index < 2; cv$reduction30Index += 1) {
										double i$var30 = reduceVar$var33$20;
										double j = state.c[cv$reduction30Index];
										reduceVar$var33$20 = (i$var30 + j);
									}
									state.bias = reduceVar$var33$20;
								}
							}
						}
					}
				}
			}
			{
				if(!state.guard1) {
					if(((0 <= 1) && (1 < 2))) {
						if(!state.guard1) {
							if(!guard$sample8if37) {
								guard$sample8if37 = true;
								{
									double reduceVar$var33$21 = 0.0;
									for(int cv$reduction30Index = 0; cv$reduction30Index < 2; cv$reduction30Index += 1) {
										double i$var30 = reduceVar$var33$21;
										double j = state.c[cv$reduction30Index];
										reduceVar$var33$21 = (i$var30 + j);
									}
									state.bias = reduceVar$var33$21;
								}
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample8() {
		if(true) {
			state.constrainedFlag$sample8 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = state.b;
			double cv$originalProbability = 0.0;
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			if((cv$var < 0.01))
				cv$var = 0.01;
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample8 || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						state.b = cv$proposedValue;
						{
							{
								if(!state.guard1) {
									{
										state.c[0] = (cv$currentValue / 2);
									}
								}
							}
						}
						{
							{
								if(!state.guard1) {
									{
										state.c[1] = (cv$currentValue / 2);
									}
								}
							}
						}
						{
							boolean guard$sample8if37 = false;
							{
								if(state.guard1) {
									if(!guard$sample8if37) {
										guard$sample8if37 = true;
										{
											state.bias = cv$currentValue;
										}
									}
								}
							}
							{
								if(!state.guard1) {
									if(((0 <= 0) && (0 < 2))) {
										if(!state.guard1) {
											if(!guard$sample8if37) {
												guard$sample8if37 = true;
												{
													double reduceVar$var33$14 = 0.0;
													for(int cv$reduction30Index = 0; cv$reduction30Index < 2; cv$reduction30Index += 1) {
														double i$var30 = reduceVar$var33$14;
														double j = state.c[cv$reduction30Index];
														reduceVar$var33$14 = (i$var30 + j);
													}
													state.bias = reduceVar$var33$14;
												}
											}
										}
									}
								}
							}
							{
								if(!state.guard1) {
									if(((0 <= 1) && (1 < 2))) {
										if(!state.guard1) {
											if(!guard$sample8if37) {
												guard$sample8if37 = true;
												{
													double reduceVar$var33$15 = 0.0;
													for(int cv$reduction30Index = 0; cv$reduction30Index < 2; cv$reduction30Index += 1) {
														double i$var30 = reduceVar$var33$15;
														double j = state.c[cv$reduction30Index];
														reduceVar$var33$15 = (i$var30 + j);
													}
													state.bias = reduceVar$var33$15;
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
						double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$currentValue, 1.0, 1.0));
						{
							{
								boolean guard$sample8bernoulli38 = false;
								{
									if(state.guard1) {
										double traceTempVariable$bias$6_1 = cv$currentValue;
										double traceTempVariable$b$6_2 = cv$currentValue;
										if(!guard$sample8bernoulli38) {
											guard$sample8bernoulli38 = true;
											{
												{
													for(int var46 = 0; var46 < state.samples; var46 += 1) {
														boolean cv$sampleConstrained = true;
														if(cv$sampleConstrained) {
															state.constrainedFlag$sample8 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				if(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$6_1) && (traceTempVariable$bias$6_1 <= 1.0))?Math.log((state.flips[var46]?traceTempVariable$bias$6_1:(1.0 - traceTempVariable$bias$6_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$6_1) && (traceTempVariable$bias$6_1 <= 1.0))?Math.log((state.flips[var46]?traceTempVariable$bias$6_1:(1.0 - traceTempVariable$bias$6_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$6_1) && (traceTempVariable$bias$6_1 <= 1.0))?Math.log((state.flips[var46]?traceTempVariable$bias$6_1:(1.0 - traceTempVariable$bias$6_1))):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$6_1) && (traceTempVariable$bias$6_1 <= 1.0))?Math.log((state.flips[var46]?traceTempVariable$bias$6_1:(1.0 - traceTempVariable$bias$6_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$6_1) && (traceTempVariable$bias$6_1 <= 1.0))?Math.log((state.flips[var46]?traceTempVariable$bias$6_1:(1.0 - traceTempVariable$bias$6_1))):Double.NEGATIVE_INFINITY)));
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
								{
									if(!state.guard1) {
										double traceTempVariable$b$7_1 = cv$currentValue;
										double traceTempVariable$i$7_2 = (traceTempVariable$b$7_1 / 2);
										if(((0 <= 0) && (0 < 2))) {
											if((0 < 2)) {
												double reduceVar$var33$16 = 0.0;
												for(int cv$reduction511Index = 0; cv$reduction511Index < 0; cv$reduction511Index += 1) {
													double i$var30 = reduceVar$var33$16;
													double j = state.c[cv$reduction511Index];
													reduceVar$var33$16 = (i$var30 + j);
												}
												for(int cv$reduction511Index = (0 + 1); cv$reduction511Index < 2; cv$reduction511Index += 1) {
													double i$var30 = reduceVar$var33$16;
													double j = state.c[cv$reduction511Index];
													reduceVar$var33$16 = (i$var30 + j);
												}
												double cv$reduced30 = reduceVar$var33$16;
												reduceVar$var33$16 = (traceTempVariable$i$7_2 + cv$reduced30);
												double traceTempVariable$var33$7_3 = reduceVar$var33$16;
												if(!state.guard1) {
													double traceTempVariable$bias$7_4 = traceTempVariable$var33$7_3;
													if(!guard$sample8bernoulli38) {
														guard$sample8bernoulli38 = true;
														{
															{
																for(int var46 = 0; var46 < state.samples; var46 += 1) {
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample8 = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							if(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$7_4) && (traceTempVariable$bias$7_4 <= 1.0))?Math.log((state.flips[var46]?traceTempVariable$bias$7_4:(1.0 - traceTempVariable$bias$7_4))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$7_4) && (traceTempVariable$bias$7_4 <= 1.0))?Math.log((state.flips[var46]?traceTempVariable$bias$7_4:(1.0 - traceTempVariable$bias$7_4))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$7_4) && (traceTempVariable$bias$7_4 <= 1.0))?Math.log((state.flips[var46]?traceTempVariable$bias$7_4:(1.0 - traceTempVariable$bias$7_4))):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$7_4) && (traceTempVariable$bias$7_4 <= 1.0))?Math.log((state.flips[var46]?traceTempVariable$bias$7_4:(1.0 - traceTempVariable$bias$7_4))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$7_4) && (traceTempVariable$bias$7_4 <= 1.0))?Math.log((state.flips[var46]?traceTempVariable$bias$7_4:(1.0 - traceTempVariable$bias$7_4))):Double.NEGATIVE_INFINITY)));
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
									if(!state.guard1) {
										double traceTempVariable$b$8_1 = cv$currentValue;
										double traceTempVariable$i$8_2 = (traceTempVariable$b$8_1 / 2);
										if(((0 <= 1) && (1 < 2))) {
											if((0 < 2)) {
												double reduceVar$var33$17 = 0.0;
												for(int cv$reduction535Index = 0; cv$reduction535Index < 1; cv$reduction535Index += 1) {
													double i$var30 = reduceVar$var33$17;
													double j = state.c[cv$reduction535Index];
													reduceVar$var33$17 = (i$var30 + j);
												}
												for(int cv$reduction535Index = (1 + 1); cv$reduction535Index < 2; cv$reduction535Index += 1) {
													double i$var30 = reduceVar$var33$17;
													double j = state.c[cv$reduction535Index];
													reduceVar$var33$17 = (i$var30 + j);
												}
												double cv$reduced30 = reduceVar$var33$17;
												reduceVar$var33$17 = (traceTempVariable$i$8_2 + cv$reduced30);
												double traceTempVariable$var33$8_3 = reduceVar$var33$17;
												if(!state.guard1) {
													double traceTempVariable$bias$8_4 = traceTempVariable$var33$8_3;
													if(!guard$sample8bernoulli38) {
														guard$sample8bernoulli38 = true;
														{
															{
																for(int var46 = 0; var46 < state.samples; var46 += 1) {
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample8 = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							if(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$8_4) && (traceTempVariable$bias$8_4 <= 1.0))?Math.log((state.flips[var46]?traceTempVariable$bias$8_4:(1.0 - traceTempVariable$bias$8_4))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$8_4) && (traceTempVariable$bias$8_4 <= 1.0))?Math.log((state.flips[var46]?traceTempVariable$bias$8_4:(1.0 - traceTempVariable$bias$8_4))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$8_4) && (traceTempVariable$bias$8_4 <= 1.0))?Math.log((state.flips[var46]?traceTempVariable$bias$8_4:(1.0 - traceTempVariable$bias$8_4))):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$8_4) && (traceTempVariable$bias$8_4 <= 1.0))?Math.log((state.flips[var46]?traceTempVariable$bias$8_4:(1.0 - traceTempVariable$bias$8_4))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$8_4) && (traceTempVariable$bias$8_4 <= 1.0))?Math.log((state.flips[var46]?traceTempVariable$bias$8_4:(1.0 - traceTempVariable$bias$8_4))):Double.NEGATIVE_INFINITY)));
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
							state.b = cv$originalValue;
							{
								{
									if(!state.guard1) {
										{
											state.c[0] = (state.b / 2);
										}
									}
								}
							}
							{
								{
									if(!state.guard1) {
										{
											state.c[1] = (state.b / 2);
										}
									}
								}
							}
							{
								boolean guard$sample8if37 = false;
								{
									if(state.guard1) {
										if(!guard$sample8if37) {
											guard$sample8if37 = true;
											{
												state.bias = state.b;
											}
										}
									}
								}
								{
									if(!state.guard1) {
										if(((0 <= 0) && (0 < 2))) {
											if(!state.guard1) {
												if(!guard$sample8if37) {
													guard$sample8if37 = true;
													{
														double reduceVar$var33$18 = 0.0;
														for(int cv$reduction30Index = 0; cv$reduction30Index < 2; cv$reduction30Index += 1) {
															double i$var30 = reduceVar$var33$18;
															double j = state.c[cv$reduction30Index];
															reduceVar$var33$18 = (i$var30 + j);
														}
														state.bias = reduceVar$var33$18;
													}
												}
											}
										}
									}
								}
								{
									if(!state.guard1) {
										if(((0 <= 1) && (1 < 2))) {
											if(!state.guard1) {
												if(!guard$sample8if37) {
													guard$sample8if37 = true;
													{
														double reduceVar$var33$19 = 0.0;
														for(int cv$reduction30Index = 0; cv$reduction30Index < 2; cv$reduction30Index += 1) {
															double i$var30 = reduceVar$var33$19;
															double j = state.c[cv$reduction30Index];
															reduceVar$var33$19 = (i$var30 + j);
														}
														state.bias = reduceVar$var33$19;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample50() {
		if(!state.fixedProbFlag$sample50) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var46 = 0; var46 < state.samples; var46 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.flips[var46];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= state.bias) && (state.bias <= 1.0))?Math.log((cv$sampleValue?state.bias:(1.0 - state.bias))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$bernoulli = cv$sampleAccumulator;
			state.logProbability$var47 = cv$sampleAccumulator;
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample50 = state.fixedFlag$sample8;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var46 = 0; var46 < state.samples; var46 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var47;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$bernoulli = cv$rvAccumulator;
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample8() {
		if(!state.fixedProbFlag$sample8) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = state.b;
					{
						{
							double var4 = 1.0;
							double var6 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var4, var6));
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
			state.logProbability$b = cv$sampleProbability;
			boolean cv$guard$bias = false;
			{
				{
					if(state.guard1) {
						if(!cv$guard$bias) {
							cv$guard$bias = true;
							state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
						}
					}
				}
				{
					if(!state.guard1) {
						if(((0 <= 0) && (0 < 2))) {
							if(!state.guard1) {
								if(!cv$guard$bias) {
									cv$guard$bias = true;
									state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
								}
							}
						}
					}
				}
				{
					if(!state.guard1) {
						if(((0 <= 1) && (1 < 2))) {
							if(!state.guard1) {
								if(!cv$guard$bias) {
									cv$guard$bias = true;
									state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
								}
							}
						}
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample8)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample8 = state.fixedFlag$sample8;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$b;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			boolean cv$guard$bias = false;
			{
				{
					if(state.guard1) {
						if(!cv$guard$bias) {
							cv$guard$bias = true;
							state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
						}
					}
				}
				{
					if(!state.guard1) {
						if(((0 <= 0) && (0 < 2))) {
							if(!state.guard1) {
								if(!cv$guard$bias) {
									cv$guard$bias = true;
									state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
								}
							}
						}
					}
				}
				{
					if(!state.guard1) {
						if(((0 <= 1) && (1 < 2))) {
							if(!state.guard1) {
								if(!cv$guard$bias) {
									cv$guard$bias = true;
									state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
								}
							}
						}
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample8)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample8)
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(state.guard1) {
			if(!state.fixedFlag$sample8)
				state.bias = state.b;
		} else {
			if(!state.fixedFlag$sample8)
				state.c[0] = (state.b / 2);
			if(!state.fixedFlag$sample8)
				state.c[1] = (state.b / 2);
			double reduceVar$var33$22 = 0.0;
			for(int cv$reduction30Index = 0; cv$reduction30Index < 2; cv$reduction30Index += 1) {
				double i$var30 = reduceVar$var33$22;
				double j = state.c[cv$reduction30Index];
				if(!state.fixedFlag$sample8)
					reduceVar$var33$22 = (i$var30 + j);
			}
			if(!state.fixedFlag$sample8)
				state.bias = reduceVar$var33$22;
		}
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
						state.flips[var46] = DistributionSampling.sampleBernoulli(RNG$1, state.bias);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample8)
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(state.guard1) {
			if(!state.fixedFlag$sample8)
				state.bias = state.b;
		} else {
			state.c[0] = (state.b / 2);
			state.c[1] = (state.b / 2);
			double reduceVar$var33$26 = 0.0;
			for(int cv$reduction30Index = 0; cv$reduction30Index < 2; cv$reduction30Index += 1) {
				double i$var30 = reduceVar$var33$26;
				double j = state.c[cv$reduction30Index];
				reduceVar$var33$26 = (i$var30 + j);
			}
			state.bias = reduceVar$var33$26;
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample8)
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(state.guard1) {
			if(!state.fixedFlag$sample8)
				state.bias = state.b;
		} else {
			state.c[0] = (state.b / 2);
			state.c[1] = (state.b / 2);
			double reduceVar$var33$23 = 0.0;
			for(int cv$reduction30Index = 0; cv$reduction30Index < 2; cv$reduction30Index += 1) {
				double i$var30 = reduceVar$var33$23;
				double j = state.c[cv$reduction30Index];
				reduceVar$var33$23 = (i$var30 + j);
			}
			state.bias = reduceVar$var33$23;
		}
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
						state.flips[var46] = DistributionSampling.sampleBernoulli(RNG$1, state.bias);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample8)
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(state.guard1) {
			if(!state.fixedFlag$sample8)
				state.bias = state.b;
		} else {
			if(!state.fixedFlag$sample8)
				state.c[0] = (state.b / 2);
			if(!state.fixedFlag$sample8)
				state.c[1] = (state.b / 2);
			double reduceVar$var33$24 = 0.0;
			for(int cv$reduction30Index = 0; cv$reduction30Index < 2; cv$reduction30Index += 1) {
				double i$var30 = reduceVar$var33$24;
				double j = state.c[cv$reduction30Index];
				if(!state.fixedFlag$sample8)
					reduceVar$var33$24 = (i$var30 + j);
			}
			if(!state.fixedFlag$sample8)
				state.bias = reduceVar$var33$24;
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample8)
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(state.guard1) {
			if(!state.fixedFlag$sample8)
				state.bias = state.b;
		} else {
			state.c[0] = (state.b / 2);
			state.c[1] = (state.b / 2);
			double reduceVar$var33$25 = 0.0;
			for(int cv$reduction30Index = 0; cv$reduction30Index < 2; cv$reduction30Index += 1) {
				double i$var30 = reduceVar$var33$25;
				double j = state.c[cv$reduction30Index];
				reduceVar$var33$25 = (i$var30 + j);
			}
			state.bias = reduceVar$var33$25;
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample8)
				inferSample8();
		} else {
			if(!state.fixedFlag$sample8)
				inferSample8();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample8)
			drawValueSample8();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample8)
			state.logProbability$b = Double.NaN;
		state.logProbability$bernoulli = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample50)
			state.logProbability$var47 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.samples = state.length$flipsMeasured;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample8)
			logProbabilityValue$sample8();
		logProbabilityValue$sample50();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample8();
		logProbabilityValue$sample50();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample8();
		logProbabilityValue$sample50();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i$var58 = (state.samples - ((((state.samples - 1) - 0) % 1) + 1)); i$var58 >= ((0 - 1) + 1); i$var58 -= 1)
			state.flips[i$var58] = state.flipsMeasured[i$var58];
	}

	@Override
	public final void setIntermediates() {
		if(state.guard1) {
			if(state.fixedFlag$sample8)
				state.bias = state.b;
		} else {
			state.c[0] = (state.b / 2);
			state.c[1] = (state.b / 2);
			double reduceVar$var33$27 = 0.0;
			for(int cv$reduction30Index = 0; cv$reduction30Index < 2; cv$reduction30Index += 1) {
				double i$var30 = reduceVar$var33$27;
				double j = state.c[cv$reduction30Index];
				reduceVar$var33$27 = (i$var30 + j);
			}
			state.bias = reduceVar$var33$27;
		}
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
		     + "public model Flip1CoinMK15(boolean[] flipsMeasured, boolean guard1) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    double b = beta(1.0, 1).sample();\n"
		     + "    double bias;\n"
		     + "    if(guard1)\n"
		     + "      bias = b;\n"
		     + "    else {\n"
		     + "      double[] c = new double[2];\n"
		     + "      c[0] = b/2;\n"
		     + "      c[1] = b/2;\n"
		     + "      bias = reduce(c, 0, (i,j) -> {\n"
		     + "            return i + j;\n"
		     + "        });\n"
		     + "    }\n"
		     + "        \n"
		     + "    Bernoulli bernoulli = bernoulli(bias);\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "\n"
		     + "    for(int i:[0..samples))\n"
		     + "        flips[i].observe(flipsMeasured[i]);\n"
		     + "}\n"
		     + "";
	}
}