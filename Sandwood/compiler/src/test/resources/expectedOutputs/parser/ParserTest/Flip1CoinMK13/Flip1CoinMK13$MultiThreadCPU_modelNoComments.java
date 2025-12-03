package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK13$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK13.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK13$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK13$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample9() {
		state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		{
			{
				if(state.guard1) {
					{
						state.bias = state.b;
					}
				}
			}
			{
				if(!state.guard1) {
					if(state.guard2) {
						if(!state.guard1) {
							{
								double var22 = (state.b / 2);
								state.bias = var22;
							}
						}
					}
				}
			}
			{
				if(!state.guard1) {
					if(!state.guard2) {
						if(!state.guard1) {
							{
								double var22 = (state.b / 3);
								state.bias = var22;
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample9() {
		if(true) {
			state.constrainedFlag$sample9 = false;
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
				if((state.constrainedFlag$sample9 || (cv$valuePos == 0))) {
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
								if(state.guard1) {
									{
										state.bias = cv$currentValue;
									}
								}
							}
							{
								if(!state.guard1) {
									if(state.guard2) {
										if(!state.guard1) {
											{
												double var22 = (cv$currentValue / 2);
												state.bias = var22;
											}
										}
									}
								}
							}
							{
								if(!state.guard1) {
									if(!state.guard2) {
										if(!state.guard1) {
											{
												double var22 = (cv$currentValue / 3);
												state.bias = var22;
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
								{
									if(state.guard1) {
										double traceTempVariable$bias$4_1 = cv$currentValue;
										double traceTempVariable$b$4_2 = cv$currentValue;
										{
											{
												for(int var35 = 0; var35 < state.samples; var35 += 1) {
													boolean cv$sampleConstrained = true;
													if(cv$sampleConstrained) {
														state.constrainedFlag$sample9 = true;
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															{
																{
																	{
																		{
																			if(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$4_1) && (traceTempVariable$bias$4_1 <= 1.0))?Math.log((state.flips[var35]?traceTempVariable$bias$4_1:(1.0 - traceTempVariable$bias$4_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$4_1) && (traceTempVariable$bias$4_1 <= 1.0))?Math.log((state.flips[var35]?traceTempVariable$bias$4_1:(1.0 - traceTempVariable$bias$4_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$4_1) && (traceTempVariable$bias$4_1 <= 1.0))?Math.log((state.flips[var35]?traceTempVariable$bias$4_1:(1.0 - traceTempVariable$bias$4_1))):Double.NEGATIVE_INFINITY));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$4_1) && (traceTempVariable$bias$4_1 <= 1.0))?Math.log((state.flips[var35]?traceTempVariable$bias$4_1:(1.0 - traceTempVariable$bias$4_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$4_1) && (traceTempVariable$bias$4_1 <= 1.0))?Math.log((state.flips[var35]?traceTempVariable$bias$4_1:(1.0 - traceTempVariable$bias$4_1))):Double.NEGATIVE_INFINITY)));
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
								{
									if(!state.guard1) {
										if(state.guard2) {
											double traceTempVariable$var22$5_1 = (cv$currentValue / 2);
											if(!state.guard1) {
												double traceTempVariable$bias$5_2 = traceTempVariable$var22$5_1;
												double traceTempVariable$b$5_3 = cv$currentValue;
												{
													{
														for(int var35 = 0; var35 < state.samples; var35 += 1) {
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample9 = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$5_2) && (traceTempVariable$bias$5_2 <= 1.0))?Math.log((state.flips[var35]?traceTempVariable$bias$5_2:(1.0 - traceTempVariable$bias$5_2))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$5_2) && (traceTempVariable$bias$5_2 <= 1.0))?Math.log((state.flips[var35]?traceTempVariable$bias$5_2:(1.0 - traceTempVariable$bias$5_2))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$5_2) && (traceTempVariable$bias$5_2 <= 1.0))?Math.log((state.flips[var35]?traceTempVariable$bias$5_2:(1.0 - traceTempVariable$bias$5_2))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$5_2) && (traceTempVariable$bias$5_2 <= 1.0))?Math.log((state.flips[var35]?traceTempVariable$bias$5_2:(1.0 - traceTempVariable$bias$5_2))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$5_2) && (traceTempVariable$bias$5_2 <= 1.0))?Math.log((state.flips[var35]?traceTempVariable$bias$5_2:(1.0 - traceTempVariable$bias$5_2))):Double.NEGATIVE_INFINITY)));
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
								{
									if(!state.guard1) {
										if(!state.guard2) {
											double traceTempVariable$var22$6_1 = (cv$currentValue / 3);
											if(!state.guard1) {
												double traceTempVariable$bias$6_2 = traceTempVariable$var22$6_1;
												double traceTempVariable$b$6_3 = cv$currentValue;
												{
													{
														for(int var35 = 0; var35 < state.samples; var35 += 1) {
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample9 = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$6_2) && (traceTempVariable$bias$6_2 <= 1.0))?Math.log((state.flips[var35]?traceTempVariable$bias$6_2:(1.0 - traceTempVariable$bias$6_2))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$6_2) && (traceTempVariable$bias$6_2 <= 1.0))?Math.log((state.flips[var35]?traceTempVariable$bias$6_2:(1.0 - traceTempVariable$bias$6_2))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$6_2) && (traceTempVariable$bias$6_2 <= 1.0))?Math.log((state.flips[var35]?traceTempVariable$bias$6_2:(1.0 - traceTempVariable$bias$6_2))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$6_2) && (traceTempVariable$bias$6_2 <= 1.0))?Math.log((state.flips[var35]?traceTempVariable$bias$6_2:(1.0 - traceTempVariable$bias$6_2))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$6_2) && (traceTempVariable$bias$6_2 <= 1.0))?Math.log((state.flips[var35]?traceTempVariable$bias$6_2:(1.0 - traceTempVariable$bias$6_2))):Double.NEGATIVE_INFINITY)));
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
									if(state.guard1) {
										{
											state.bias = state.b;
										}
									}
								}
								{
									if(!state.guard1) {
										if(state.guard2) {
											if(!state.guard1) {
												{
													double var22 = (state.b / 2);
													state.bias = var22;
												}
											}
										}
									}
								}
								{
									if(!state.guard1) {
										if(!state.guard2) {
											if(!state.guard1) {
												{
													double var22 = (state.b / 3);
													state.bias = var22;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample40() {
		if(!state.fixedProbFlag$sample40) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var35 = 0; var35 < state.samples; var35 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.flips[var35];
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
			state.logProbability$var36 = cv$sampleAccumulator;
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample40 = state.fixedFlag$sample9;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var35 = 0; var35 < state.samples; var35 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var36;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$bernoulli = cv$rvAccumulator;
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!state.fixedProbFlag$sample9) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = state.b;
					{
						{
							double var5 = 1.0;
							double var7 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var5, var7));
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
						if(state.guard2) {
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
						if(!state.guard2) {
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
			if(state.fixedFlag$sample9)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample9 = state.fixedFlag$sample9;
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
						if(state.guard2) {
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
						if(!state.guard2) {
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
			if(state.fixedFlag$sample9)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample9)
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(state.guard1) {
			if(!state.fixedFlag$sample9)
				state.bias = state.b;
		} else {
			if(state.guard2) {
				if(!state.fixedFlag$sample9)
					state.bias = (state.b / 2);
			} else {
				if(!state.fixedFlag$sample9)
					state.bias = (state.b / 3);
			}
		}
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1)
						state.flips[var35] = DistributionSampling.sampleBernoulli(RNG$1, state.bias);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample9)
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(state.guard1) {
			if(!state.fixedFlag$sample9)
				state.bias = state.b;
		} else {
			if(state.guard2)
				state.bias = (state.b / 2);
			else
				state.bias = (state.b / 3);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample9)
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(state.guard1) {
			if(!state.fixedFlag$sample9)
				state.bias = state.b;
		} else {
			if(state.guard2)
				state.bias = (state.b / 2);
			else
				state.bias = (state.b / 3);
		}
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1)
						state.flips[var35] = DistributionSampling.sampleBernoulli(RNG$1, state.bias);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample9)
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(state.guard1) {
			if(!state.fixedFlag$sample9)
				state.bias = state.b;
		} else {
			if(state.guard2) {
				if(!state.fixedFlag$sample9)
					state.bias = (state.b / 2);
			} else {
				if(!state.fixedFlag$sample9)
					state.bias = (state.b / 3);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample9)
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(state.guard1) {
			if(!state.fixedFlag$sample9)
				state.bias = state.b;
		} else {
			if(state.guard2)
				state.bias = (state.b / 2);
			else
				state.bias = (state.b / 3);
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample9)
				inferSample9();
		} else {
			if(!state.fixedFlag$sample9)
				inferSample9();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample9)
			drawValueSample9();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample9)
			state.logProbability$b = Double.NaN;
		state.logProbability$bernoulli = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample40)
			state.logProbability$var36 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.samples = state.length$flipsMeasured;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample9)
			logProbabilityValue$sample9();
		logProbabilityValue$sample40();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample40();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample40();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i = (state.samples - ((((state.samples - 1) - 0) % 1) + 1)); i >= ((0 - 1) + 1); i -= 1)
			state.flips[i] = state.flipsMeasured[i];
	}

	@Override
	public final void setIntermediates() {
		if(state.guard1) {
			if(state.fixedFlag$sample9)
				state.bias = state.b;
		} else {
			if(state.guard2)
				state.bias = (state.b / 2);
			else
				state.bias = (state.b / 3);
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
		     + "public model Flip1CoinMK13(boolean[] flipsMeasured, boolean guard1, boolean guard2) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    double b = beta(1.0, 1).sample();\n"
		     + "    double bias;\n"
		     + "    if(guard1)\n"
		     + "      bias = b;\n"
		     + "    else { \n"
		     + "        if(guard2) {\n"
		     + "            bias = b/2;\n"
		     + "        } else\n"
		     + "            bias = b/3;\n"
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