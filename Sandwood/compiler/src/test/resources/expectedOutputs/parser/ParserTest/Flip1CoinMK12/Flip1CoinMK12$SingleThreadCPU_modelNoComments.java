package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK12$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK12.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK12$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK12$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample16() {
		state.var14 = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		{
			{
				if(state.guard1) {
					{
						state.bias = state.var14;
					}
				}
			}
		}
	}

	private final void drawValueSample28() {
		state.var26 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 2);
		{
			{
				if((state.guard2 <= 2)) {
					if(!state.guard1) {
						{
							double var34 = state.var26;
							state.bias = var34;
						}
					}
				}
			}
		}
	}

	private final void drawValueSample35() {
		state.var33 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 3);
		{
			{
				if(!(state.guard2 <= 2)) {
					if(!state.guard1) {
						{
							double var34 = state.var33;
							state.bias = var34;
						}
					}
				}
			}
		}
	}

	private final void inferSample16() {
		if(true) {
			state.constrainedFlag$sample16 = false;
			int cv$sum = 0;
			int cv$count = 0;
			{
				{
					{
						{
							if(state.guard1) {
								{
									{
										for(int var47 = 0; var47 < state.samples; var47 += 1) {
											boolean cv$sampleConstrained = true;
											if(cv$sampleConstrained) {
												state.constrainedFlag$sample16 = true;
												{
													{
														{
															{
																{
																	cv$count = (cv$count + 1);
																	if(state.flips[var47])
																		cv$sum = (cv$sum + 1);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(state.constrainedFlag$sample16) {
				state.var14 = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
				{
					{
						if(state.guard1) {
							{
								state.bias = state.var14;
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample28() {
		if(true) {
			state.constrainedFlag$sample28 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = (state.var26 * 2);
			double cv$originalProbability = 0.0;
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			if((cv$var < 0.01))
				cv$var = 0.01;
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample28 || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						double var24 = cv$proposedValue;
						state.var26 = (cv$currentValue / 2);
						{
							{
								if((state.guard2 <= 2)) {
									if(!state.guard1) {
										{
											double var34 = state.var26;
											state.bias = var34;
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
									if((state.guard2 <= 2)) {
										double traceTempVariable$var34$2_1 = state.var26;
										if(!state.guard1) {
											double traceTempVariable$bias$2_2 = traceTempVariable$var34$2_1;
											{
												{
													for(int var47 = 0; var47 < state.samples; var47 += 1) {
														boolean cv$sampleConstrained = true;
														if(cv$sampleConstrained) {
															state.constrainedFlag$sample28 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				if(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$2_2) && (traceTempVariable$bias$2_2 <= 1.0))?Math.log((state.flips[var47]?traceTempVariable$bias$2_2:(1.0 - traceTempVariable$bias$2_2))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$2_2) && (traceTempVariable$bias$2_2 <= 1.0))?Math.log((state.flips[var47]?traceTempVariable$bias$2_2:(1.0 - traceTempVariable$bias$2_2))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$2_2) && (traceTempVariable$bias$2_2 <= 1.0))?Math.log((state.flips[var47]?traceTempVariable$bias$2_2:(1.0 - traceTempVariable$bias$2_2))):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$2_2) && (traceTempVariable$bias$2_2 <= 1.0))?Math.log((state.flips[var47]?traceTempVariable$bias$2_2:(1.0 - traceTempVariable$bias$2_2))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$2_2) && (traceTempVariable$bias$2_2 <= 1.0))?Math.log((state.flips[var47]?traceTempVariable$bias$2_2:(1.0 - traceTempVariable$bias$2_2))):Double.NEGATIVE_INFINITY)));
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
							double var24 = cv$originalValue;
							state.var26 = (var24 / 2);
							{
								{
									if((state.guard2 <= 2)) {
										if(!state.guard1) {
											{
												double var34 = state.var26;
												state.bias = var34;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample35() {
		if(true) {
			state.constrainedFlag$sample35 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = (state.var33 * 3);
			double cv$originalProbability = 0.0;
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			if((cv$var < 0.01))
				cv$var = 0.01;
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample35 || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						double var31 = cv$proposedValue;
						state.var33 = (cv$currentValue / 3);
						{
							{
								if(!(state.guard2 <= 2)) {
									if(!state.guard1) {
										{
											double var34 = state.var33;
											state.bias = var34;
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
									if(!(state.guard2 <= 2)) {
										double traceTempVariable$var34$2_1 = state.var33;
										if(!state.guard1) {
											double traceTempVariable$bias$2_2 = traceTempVariable$var34$2_1;
											{
												{
													for(int var47 = 0; var47 < state.samples; var47 += 1) {
														boolean cv$sampleConstrained = true;
														if(cv$sampleConstrained) {
															state.constrainedFlag$sample35 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				if(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$2_2) && (traceTempVariable$bias$2_2 <= 1.0))?Math.log((state.flips[var47]?traceTempVariable$bias$2_2:(1.0 - traceTempVariable$bias$2_2))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$2_2) && (traceTempVariable$bias$2_2 <= 1.0))?Math.log((state.flips[var47]?traceTempVariable$bias$2_2:(1.0 - traceTempVariable$bias$2_2))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$2_2) && (traceTempVariable$bias$2_2 <= 1.0))?Math.log((state.flips[var47]?traceTempVariable$bias$2_2:(1.0 - traceTempVariable$bias$2_2))):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$2_2) && (traceTempVariable$bias$2_2 <= 1.0))?Math.log((state.flips[var47]?traceTempVariable$bias$2_2:(1.0 - traceTempVariable$bias$2_2))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$2_2) && (traceTempVariable$bias$2_2 <= 1.0))?Math.log((state.flips[var47]?traceTempVariable$bias$2_2:(1.0 - traceTempVariable$bias$2_2))):Double.NEGATIVE_INFINITY)));
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
							double var31 = cv$originalValue;
							state.var33 = (var31 / 3);
							{
								{
									if(!(state.guard2 <= 2)) {
										if(!state.guard1) {
											{
												double var34 = state.var33;
												state.bias = var34;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample16() {
		if(!state.fixedProbFlag$sample16) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			if(state.guard1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.var14;
						{
							{
								double var10 = 1.0;
								double var12 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var10, var12));
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
				state.logProbability$sample16 = cv$sampleProbability;
			}
			boolean cv$guard$bias = false;
			state.logProbability$var14 = (state.logProbability$var14 + cv$accumulator);
			{
				{
					if(state.guard1) {
						if(!cv$guard$bias) {
							cv$guard$bias = true;
							state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
						}
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample16)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample16 = state.fixedFlag$sample16;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			if(state.guard1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample16;
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			boolean cv$guard$bias = false;
			state.logProbability$var14 = (state.logProbability$var14 + cv$accumulator);
			{
				{
					if(state.guard1) {
						if(!cv$guard$bias) {
							cv$guard$bias = true;
							state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
						}
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample16)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!state.fixedProbFlag$sample28) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			if(!state.guard1) {
				if((state.guard2 <= 2)) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							double cv$sampleValue = (state.var26 * 2);
							{
								{
									double var20 = 1.0;
									double var22 = 1.0;
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var20, var22));
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
					state.logProbability$sample28 = cv$sampleProbability;
				}
			}
			boolean cv$guard$bias = false;
			state.logProbability$var26 = (state.logProbability$var26 + cv$accumulator);
			{
				{
					if((state.guard2 <= 2)) {
						if(!state.guard1) {
							if(!cv$guard$bias) {
								cv$guard$bias = true;
								state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
							}
						}
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample28 = state.fixedFlag$sample28;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			if(!state.guard1) {
				if((state.guard2 <= 2)) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = state.logProbability$sample28;
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			boolean cv$guard$bias = false;
			state.logProbability$var26 = (state.logProbability$var26 + cv$accumulator);
			{
				{
					if((state.guard2 <= 2)) {
						if(!state.guard1) {
							if(!cv$guard$bias) {
								cv$guard$bias = true;
								state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
							}
						}
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!state.fixedProbFlag$sample35) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			if(!state.guard1) {
				if(!(state.guard2 <= 2)) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							double cv$sampleValue = (state.var33 * 3);
							{
								{
									double var27 = 1.0;
									double var29 = 1.0;
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var27, var29));
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
					state.logProbability$sample35 = cv$sampleProbability;
				}
			}
			boolean cv$guard$bias = false;
			state.logProbability$var33 = (state.logProbability$var33 + cv$accumulator);
			{
				{
					if(!(state.guard2 <= 2)) {
						if(!state.guard1) {
							if(!cv$guard$bias) {
								cv$guard$bias = true;
								state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
							}
						}
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample35)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample35 = state.fixedFlag$sample35;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			if(!state.guard1) {
				if(!(state.guard2 <= 2)) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = state.logProbability$sample35;
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			boolean cv$guard$bias = false;
			state.logProbability$var33 = (state.logProbability$var33 + cv$accumulator);
			{
				{
					if(!(state.guard2 <= 2)) {
						if(!state.guard1) {
							if(!cv$guard$bias) {
								cv$guard$bias = true;
								state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
							}
						}
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample35)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample52() {
		if(!state.fixedProbFlag$sample52) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var47 = 0; var47 < state.samples; var47 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.flips[var47];
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
			state.logProbability$var48 = cv$sampleAccumulator;
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample52 = ((state.fixedFlag$sample16 && state.fixedFlag$sample28) && state.fixedFlag$sample35);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var47 = 0; var47 < state.samples; var47 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var48;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$bernoulli = cv$rvAccumulator;
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(state.guard1) {
			if(!state.fixedFlag$sample16)
				state.var14 = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			if(!state.fixedFlag$sample16)
				state.bias = state.var14;
		} else {
			if((state.guard2 <= 2)) {
				if(!state.fixedFlag$sample28)
					state.var26 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 2);
				if(!state.fixedFlag$sample28)
					state.bias = state.var26;
			} else {
				if(!state.fixedFlag$sample35)
					state.var33 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 3);
				if(!state.fixedFlag$sample35)
					state.bias = state.var33;
			}
		}
		for(int var47 = 0; var47 < state.samples; var47 += 1)
			state.flips[var47] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(state.guard1) {
			if(!state.fixedFlag$sample16)
				state.var14 = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			if(!state.fixedFlag$sample16)
				state.bias = state.var14;
		} else {
			if((state.guard2 <= 2)) {
				if(!state.fixedFlag$sample28)
					state.var26 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 2);
				if(!state.fixedFlag$sample28)
					state.bias = state.var26;
			} else {
				if(!state.fixedFlag$sample35)
					state.var33 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 3);
				if(!state.fixedFlag$sample35)
					state.bias = state.var33;
			}
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(state.guard1) {
			if(!state.fixedFlag$sample16)
				state.var14 = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			if(!state.fixedFlag$sample16)
				state.bias = state.var14;
		} else {
			if((state.guard2 <= 2)) {
				if(!state.fixedFlag$sample28)
					state.var26 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 2);
				if(!state.fixedFlag$sample28)
					state.bias = state.var26;
			} else {
				if(!state.fixedFlag$sample35)
					state.var33 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 3);
				if(!state.fixedFlag$sample35)
					state.bias = state.var33;
			}
		}
		for(int var47 = 0; var47 < state.samples; var47 += 1)
			state.flips[var47] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(state.guard1) {
			if(!state.fixedFlag$sample16)
				state.var14 = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			if(!state.fixedFlag$sample16)
				state.bias = state.var14;
		} else {
			if((state.guard2 <= 2)) {
				if(!state.fixedFlag$sample28)
					state.var26 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 2);
				if(!state.fixedFlag$sample28)
					state.bias = state.var26;
			} else {
				if(!state.fixedFlag$sample35)
					state.var33 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 3);
				if(!state.fixedFlag$sample35)
					state.bias = state.var33;
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(state.guard1) {
			if(!state.fixedFlag$sample16)
				state.var14 = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			if(!state.fixedFlag$sample16)
				state.bias = state.var14;
		} else {
			if((state.guard2 <= 2)) {
				if(!state.fixedFlag$sample28)
					state.var26 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 2);
				if(!state.fixedFlag$sample28)
					state.bias = state.var26;
			} else {
				if(!state.fixedFlag$sample35)
					state.var33 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 3);
				if(!state.fixedFlag$sample35)
					state.bias = state.var33;
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(state.guard1) {
				if(!state.fixedFlag$sample16)
					inferSample16();
			} else {
				if((state.guard2 <= 2)) {
					if(!state.fixedFlag$sample28)
						inferSample28();
				} else {
					if(!state.fixedFlag$sample35)
						inferSample35();
				}
			}
		} else {
			if(state.guard1) {
				if(!state.fixedFlag$sample16)
					inferSample16();
			} else {
				if((state.guard2 <= 2)) {
					if(!state.fixedFlag$sample28)
						inferSample28();
				} else {
					if(!state.fixedFlag$sample35)
						inferSample35();
				}
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(state.guard1) {
			if(!state.constrainedFlag$sample16)
				drawValueSample16();
		} else {
			if((state.guard2 <= 2)) {
				if(!state.constrainedFlag$sample28)
					drawValueSample28();
			} else {
				if(!state.constrainedFlag$sample35)
					drawValueSample35();
			}
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$var14 = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample16)
			state.logProbability$sample16 = Double.NaN;
		state.logProbability$var26 = 0.0;
		if(!state.fixedProbFlag$sample28)
			state.logProbability$sample28 = Double.NaN;
		state.logProbability$var33 = 0.0;
		if(!state.fixedProbFlag$sample35)
			state.logProbability$sample35 = Double.NaN;
		state.logProbability$bernoulli = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample52)
			state.logProbability$var48 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.samples = state.length$flipsMeasured;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample16)
			logProbabilityValue$sample16();
		if(state.fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(state.fixedFlag$sample35)
			logProbabilityValue$sample35();
		logProbabilityValue$sample52();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample16();
		logProbabilityValue$sample28();
		logProbabilityValue$sample35();
		logProbabilityValue$sample52();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample16();
		logProbabilityValue$sample28();
		logProbabilityValue$sample35();
		logProbabilityValue$sample52();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i = (state.samples - ((((state.samples - 1) - 0) % 1) + 1)); i >= ((0 - 1) + 1); i -= 1)
			state.flips[i] = state.flipsMeasured[i];
	}

	@Override
	public final void setIntermediates() {
		if(state.guard1) {
			if(state.fixedFlag$sample16)
				state.bias = state.var14;
		} else {
			if((state.guard2 <= 2)) {
				if(state.fixedFlag$sample28)
					state.bias = state.var26;
			} else {
				if(state.fixedFlag$sample35)
					state.bias = state.var33;
			}
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
		     + "public model Flip1CoinMK12(boolean[] flipsMeasured, boolean guard1, int guard2) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    double bias;\n"
		     + "    if(guard1)\n"
		     + "      bias = beta(1.0, 1).sample();\n"
		     + "    else { \n"
		     + "        if(guard2 <= 2) {\n"
		     + "            bias = beta(1.0, 1).sample()/2;\n"
		     + "        } else\n"
		     + "            bias = beta(1.0, 1).sample()/3;\n"
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