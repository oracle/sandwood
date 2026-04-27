package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.DiscreteChoiceAlt$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.DiscreteChoiceAlt.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DiscreteChoiceAlt$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
boolean[] guard$sample24put65$global;

		@Override
		public final void allocateScratch() {
			int cv$max_i$var61 = 0;
			cv$max_i$var61 = Math.max(cv$max_i$var61, ((state.noProducts - 0) / 1));
			guard$sample24put65$global = new boolean[cv$max_i$var61];
		}
	}


	public DiscreteChoiceAlt$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample24(int i$var18) {
		state.ut[i$var18] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		{
			{
				for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						{
							state.exped[i$var36] = Math.exp(state.ut[i$var36]);
						}
					}
				}
			}
		}
		{
			{
				for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
							{
								double reduceVar$sum$13 = 0.0;
								for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1) {
									double i$var47 = reduceVar$sum$13;
									double j = state.exped[cv$reduction44Index];
									reduceVar$sum$13 = (i$var47 + j);
								}
								state.sum = reduceVar$sum$13;
							}
						}
					}
				}
			}
		}
		{
			boolean[] guard$sample24put65 = scratch.guard$sample24put65$global;
			{
				for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
							for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
								guard$sample24put65[((i$var61 - 0) / 1)] = false;
						}
					}
				}
			}
			{
				for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
							if((i$var36 == i$var61))
								guard$sample24put65[((i$var61 - 0) / 1)] = false;
						}
					}
				}
			}
			{
				for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
							for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
								if(!guard$sample24put65[((i$var61 - 0) / 1)]) {
									guard$sample24put65[((i$var61 - 0) / 1)] = true;
									{
										state.prob[i$var61] = (state.exped[i$var61] / state.sum);
									}
								}
							}
						}
					}
				}
			}
			{
				for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
							if((i$var36 == i$var61)) {
								if(!guard$sample24put65[((i$var61 - 0) / 1)]) {
									guard$sample24put65[((i$var61 - 0) / 1)] = true;
									{
										state.prob[i$var61] = (state.exped[i$var61] / state.sum);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample24(int i$var18) {
		if(true) {
			state.constrainedFlag$sample24[((i$var18 - 1) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = state.ut[i$var18];
			double cv$originalProbability = 0.0;
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			if((cv$var < 0.01))
				cv$var = 0.01;
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample24[((i$var18 - 1) / 1)] || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						double var24 = cv$proposedValue;
						{
							{
								{
									state.ut[i$var18] = cv$currentValue;
								}
							}
						}
						{
							{
								for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
									if((i$var18 == i$var36)) {
										{
											state.exped[i$var36] = Math.exp(state.ut[i$var36]);
										}
									}
								}
							}
						}
						{
							{
								for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
									if((i$var18 == i$var36)) {
										if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
											{
												double reduceVar$sum$10 = 0.0;
												for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1) {
													double i$var47 = reduceVar$sum$10;
													double j = state.exped[cv$reduction44Index];
													reduceVar$sum$10 = (i$var47 + j);
												}
												state.sum = reduceVar$sum$10;
											}
										}
									}
								}
							}
						}
						{
							boolean[] guard$sample24put65 = scratch.guard$sample24put65$global;
							{
								for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
									if((i$var18 == i$var36)) {
										if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
											for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
												guard$sample24put65[((i$var61 - 0) / 1)] = false;
										}
									}
								}
							}
							{
								for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
									if((i$var18 == i$var36)) {
										for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
											if((i$var36 == i$var61))
												guard$sample24put65[((i$var61 - 0) / 1)] = false;
										}
									}
								}
							}
							{
								for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
									if((i$var18 == i$var36)) {
										if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
											for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
												if(!guard$sample24put65[((i$var61 - 0) / 1)]) {
													guard$sample24put65[((i$var61 - 0) / 1)] = true;
													{
														state.prob[i$var61] = (state.exped[i$var61] / state.sum);
													}
												}
											}
										}
									}
								}
							}
							{
								for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
									if((i$var18 == i$var36)) {
										for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
											if((i$var36 == i$var61)) {
												if(!guard$sample24put65[((i$var61 - 0) / 1)]) {
													guard$sample24put65[((i$var61 - 0) / 1)] = true;
													{
														state.prob[i$var61] = (state.exped[i$var61] / state.sum);
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
								boolean guard$sample24categorical66 = false;
								{
									double traceTempVariable$var37$8_1 = cv$currentValue;
									for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
										if((i$var18 == i$var36)) {
											double traceTempVariable$i$8_3 = Math.exp(traceTempVariable$var37$8_1);
											if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
												if((0 < state.noProducts)) {
													double reduceVar$sum$11 = 0.0;
													for(int cv$reduction760Index = 0; cv$reduction760Index < i$var36; cv$reduction760Index += 1) {
														double i$var47 = reduceVar$sum$11;
														double j = state.exped[cv$reduction760Index];
														reduceVar$sum$11 = (i$var47 + j);
													}
													for(int cv$reduction760Index = (i$var36 + 1); cv$reduction760Index < state.noProducts; cv$reduction760Index += 1) {
														double i$var47 = reduceVar$sum$11;
														double j = state.exped[cv$reduction760Index];
														reduceVar$sum$11 = (i$var47 + j);
													}
													double cv$reduced44 = reduceVar$sum$11;
													reduceVar$sum$11 = (traceTempVariable$i$8_3 + cv$reduced44);
													double traceTempVariable$sum$8_4 = reduceVar$sum$11;
													double traceTempVariable$sum$8_5 = traceTempVariable$sum$8_4;
													if(!guard$sample24categorical66) {
														guard$sample24categorical66 = true;
														{
															{
																for(int var76 = 0; var76 < state.noObs; var76 += 1) {
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample24[((i$var18 - 1) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							if(((Math.log(1.0) + ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY)));
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
									double traceTempVariable$var37$9_1 = cv$currentValue;
									for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
										if((i$var18 == i$var36)) {
											double traceTempVariable$var62$9_3 = Math.exp(traceTempVariable$var37$9_1);
											for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
												if((i$var36 == i$var61)) {
													if(!guard$sample24categorical66) {
														guard$sample24categorical66 = true;
														{
															{
																for(int var76 = 0; var76 < state.noObs; var76 += 1) {
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample24[((i$var18 - 1) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							if(((Math.log(1.0) + ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY)));
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
							double var24 = cv$originalValue;
							{
								{
									{
										state.ut[i$var18] = var24;
									}
								}
							}
							{
								{
									for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
										if((i$var18 == i$var36)) {
											{
												state.exped[i$var36] = Math.exp(state.ut[i$var36]);
											}
										}
									}
								}
							}
							{
								{
									for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
										if((i$var18 == i$var36)) {
											if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
												{
													double reduceVar$sum$12 = 0.0;
													for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1) {
														double i$var47 = reduceVar$sum$12;
														double j = state.exped[cv$reduction44Index];
														reduceVar$sum$12 = (i$var47 + j);
													}
													state.sum = reduceVar$sum$12;
												}
											}
										}
									}
								}
							}
							{
								boolean[] guard$sample24put65 = scratch.guard$sample24put65$global;
								{
									for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
										if((i$var18 == i$var36)) {
											if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
												for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
													guard$sample24put65[((i$var61 - 0) / 1)] = false;
											}
										}
									}
								}
								{
									for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
										if((i$var18 == i$var36)) {
											for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
												if((i$var36 == i$var61))
													guard$sample24put65[((i$var61 - 0) / 1)] = false;
											}
										}
									}
								}
								{
									for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
										if((i$var18 == i$var36)) {
											if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
												for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
													if(!guard$sample24put65[((i$var61 - 0) / 1)]) {
														guard$sample24put65[((i$var61 - 0) / 1)] = true;
														{
															state.prob[i$var61] = (state.exped[i$var61] / state.sum);
														}
													}
												}
											}
										}
									}
								}
								{
									for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
										if((i$var18 == i$var36)) {
											for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
												if((i$var36 == i$var61)) {
													if(!guard$sample24put65[((i$var61 - 0) / 1)]) {
														guard$sample24put65[((i$var61 - 0) / 1)] = true;
														{
															state.prob[i$var61] = (state.exped[i$var61] / state.sum);
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample24() {
		if(!state.fixedProbFlag$sample24) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.ut[i$var18];
						{
							{
								double var21 = 0.0;
								double var22 = 10.0;
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var22)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var21) / Math.sqrt(var22))) - (0.5 * Math.log(var22))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample24[((i$var18 - 1) / 1)] = cv$sampleProbability;
				boolean cv$guard$exped = false;
				boolean cv$guard$sum = false;
				boolean cv$guard$prob = false;
				{
					{
						for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								if(!cv$guard$exped) {
									cv$guard$exped = true;
									state.logProbability$exped = (state.logProbability$exped + cv$sampleProbability);
								}
							}
						}
					}
				}
				{
					{
						for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
									if(!cv$guard$sum) {
										cv$guard$sum = true;
										state.logProbability$sum = (state.logProbability$sum + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
				{
					{
						for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
									for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
										if(!cv$guard$prob) {
											cv$guard$prob = true;
											state.logProbability$prob = (state.logProbability$prob + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
					{
						for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
									if((i$var36 == i$var61)) {
										if(!cv$guard$prob) {
											cv$guard$prob = true;
											state.logProbability$prob = (state.logProbability$prob + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
				}
			}
			state.logProbability$ut = (state.logProbability$ut + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample24)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample24 = state.fixedFlag$sample24;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample24[((i$var18 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				boolean cv$guard$exped = false;
				boolean cv$guard$sum = false;
				boolean cv$guard$prob = false;
				{
					{
						for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								if(!cv$guard$exped) {
									cv$guard$exped = true;
									state.logProbability$exped = (state.logProbability$exped + cv$sampleValue);
								}
							}
						}
					}
				}
				{
					{
						for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
									if(!cv$guard$sum) {
										cv$guard$sum = true;
										state.logProbability$sum = (state.logProbability$sum + cv$sampleValue);
									}
								}
							}
						}
					}
				}
				{
					{
						for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
									for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
										if(!cv$guard$prob) {
											cv$guard$prob = true;
											state.logProbability$prob = (state.logProbability$prob + cv$sampleValue);
										}
									}
								}
							}
						}
					}
					{
						for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
									if((i$var36 == i$var61)) {
										if(!cv$guard$prob) {
											cv$guard$prob = true;
											state.logProbability$prob = (state.logProbability$prob + cv$sampleValue);
										}
									}
								}
							}
						}
					}
				}
			}
			state.logProbability$ut = (state.logProbability$ut + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample24)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample78() {
		if(!state.fixedProbFlag$sample78) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var76 = 0; var76 < state.noObs; var76 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = state.choices[var76];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[cv$sampleValue])) && (state.prob[cv$sampleValue] <= 1.0))?Math.log(state.prob[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$var77 = cv$sampleAccumulator;
			state.logProbability$choices = (state.logProbability$choices + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample78 = state.fixedFlag$sample24;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var76 = 0; var76 < state.noObs; var76 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var77;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$choices = (state.logProbability$choices + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(state.RNG$, 1, state.noProducts, 1,
			(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1) {
						if(!state.fixedFlag$sample24)
							state.ut[i$var18] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1) {
						if(!state.fixedFlag$sample24)
							state.exped[i$var36] = Math.exp(state.ut[i$var36]);
					}
			}
		);
		double reduceVar$sum$14 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1) {
			double i$var47 = reduceVar$sum$14;
			double j = state.exped[cv$reduction44Index];
			if(!state.fixedFlag$sample24)
				reduceVar$sum$14 = (i$var47 + j);
		}
		if(!state.fixedFlag$sample24)
			state.sum = reduceVar$sum$14;
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1) {
						if(!state.fixedFlag$sample24)
							state.prob[i$var61] = (state.exped[i$var61] / state.sum);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$var76, int forEnd$var76, int threadID$var76, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var76 = forStart$var76; var76 < forEnd$var76; var76 += 1)
						state.choices[var76] = DistributionSampling.sampleCategorical(RNG$1, state.prob, state.noProducts);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		parallelFor(state.RNG$, 1, state.noProducts, 1,
			(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1) {
						if(!state.fixedFlag$sample24)
							state.ut[i$var18] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1)
						state.exped[i$var36] = Math.exp(state.ut[i$var36]);
			}
		);
		double reduceVar$sum$18 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1) {
			double i$var47 = reduceVar$sum$18;
			double j = state.exped[cv$reduction44Index];
			reduceVar$sum$18 = (i$var47 + j);
		}
		state.sum = reduceVar$sum$18;
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1)
						state.prob[i$var61] = (state.exped[i$var61] / state.sum);
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		parallelFor(state.RNG$, 1, state.noProducts, 1,
			(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1) {
						if(!state.fixedFlag$sample24)
							state.ut[i$var18] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1)
						state.exped[i$var36] = Math.exp(state.ut[i$var36]);
			}
		);
		double reduceVar$sum$15 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1) {
			double i$var47 = reduceVar$sum$15;
			double j = state.exped[cv$reduction44Index];
			reduceVar$sum$15 = (i$var47 + j);
		}
		state.sum = reduceVar$sum$15;
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1)
						state.prob[i$var61] = (state.exped[i$var61] / state.sum);
			}
		);
		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$var76, int forEnd$var76, int threadID$var76, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var76 = forStart$var76; var76 < forEnd$var76; var76 += 1)
						state.choices[var76] = DistributionSampling.sampleCategorical(RNG$1, state.prob, state.noProducts);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(state.RNG$, 1, state.noProducts, 1,
			(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1) {
						if(!state.fixedFlag$sample24)
							state.ut[i$var18] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1) {
						if(!state.fixedFlag$sample24)
							state.exped[i$var36] = Math.exp(state.ut[i$var36]);
					}
			}
		);
		double reduceVar$sum$16 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1) {
			double i$var47 = reduceVar$sum$16;
			double j = state.exped[cv$reduction44Index];
			if(!state.fixedFlag$sample24)
				reduceVar$sum$16 = (i$var47 + j);
		}
		if(!state.fixedFlag$sample24)
			state.sum = reduceVar$sum$16;
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1) {
						if(!state.fixedFlag$sample24)
							state.prob[i$var61] = (state.exped[i$var61] / state.sum);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		parallelFor(state.RNG$, 1, state.noProducts, 1,
			(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1) {
						if(!state.fixedFlag$sample24)
							state.ut[i$var18] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1)
						state.exped[i$var36] = Math.exp(state.ut[i$var36]);
			}
		);
		double reduceVar$sum$17 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1) {
			double i$var47 = reduceVar$sum$17;
			double j = state.exped[cv$reduction44Index];
			reduceVar$sum$17 = (i$var47 + j);
		}
		state.sum = reduceVar$sum$17;
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1)
						state.prob[i$var61] = (state.exped[i$var61] / state.sum);
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1) {
				if(!state.fixedFlag$sample24)
					inferSample24(i$var18);
			}
		} else {
			for(int i$var18 = (state.noProducts - ((((state.noProducts - 1) - 1) % 1) + 1)); i$var18 >= ((1 - 1) + 1); i$var18 -= 1) {
				if(!state.fixedFlag$sample24)
					inferSample24(i$var18);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1) {
			if(!state.constrainedFlag$sample24[((i$var18 - 1) / 1)])
				drawValueSample24(i$var18);
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$ut = 0.0;
		state.logProbability$exped = 0.0;
		state.logProbability$sum = 0.0;
		state.logProbability$prob = 0.0;
		if(!state.fixedProbFlag$sample24) {
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1)
				state.logProbability$sample24[((i$var18 - 1) / 1)] = Double.NaN;
		}
		state.logProbability$choices = 0.0;
		if(!state.fixedProbFlag$sample78)
			state.logProbability$var77 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.ut[0] = 0.0;
		for(int index$constrainedFlag$sample24$1 = 0; index$constrainedFlag$sample24$1 < state.constrainedFlag$sample24.length; index$constrainedFlag$sample24$1 += 1)
			state.constrainedFlag$sample24[index$constrainedFlag$sample24$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample24)
			logProbabilityValue$sample24();
		logProbabilityValue$sample78();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample24();
		logProbabilityValue$sample78();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample24();
		logProbabilityValue$sample78();
	}

	@Override
	public final void propagateObservedValues() {
		int[] cv$source1 = state.ObsChoices;
		int[] cv$target1 = state.choices;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1)
						state.exped[i$var36] = Math.exp(state.ut[i$var36]);
			}
		);
		double reduceVar$sum$19 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1) {
			double i$var47 = reduceVar$sum$19;
			double j = state.exped[cv$reduction44Index];
			reduceVar$sum$19 = (i$var47 + j);
		}
		state.sum = reduceVar$sum$19;
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1)
						state.prob[i$var61] = (state.exped[i$var61] / state.sum);
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model DiscreteChoiceAlt(int noProducts, int noObs, int[] ObsChoices) {\n"
		     + "    // we just need an uninformative prior for utility intercepts\n"
		     + "\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = new double[noProducts];\n"
		     + "    ut[0] = 0.0;\n"
		     + "    for(int i=1; i<noProducts; i++) \n"
		     + "        ut[i]= gaussian(0, 10).sample();\n"
		     + "\n"
		     + "    // calculate choice probabilities\n"
		     + "    double[] exped = new double[noProducts];\n"
		     + "    for(int i : [0..noProducts)) {\n"
		     + "        exped[i] = exp(ut[i]);\n"
		     + "    }\n"
		     + "    double sum = reduce(exped, 0, (i, j) -> { return i + j; });\n"
		     + "    double[] prob = new double[noProducts];\n"
		     + "    for (int i : [0..noProducts)) {\n"
		     + "        prob[i] = exped[i] / sum;\n"
		     + "    }\n"
		     + "    // draw consumer choices according to the calculated probabilities\n"
		     + "    int[] choices = categorical(prob).sample(noObs);\n"
		     + "\n"
		     + "    // assert generated choices match observed choices\n"
		     + "    choices.observe(ObsChoices);\n"
		     + "}";
	}
}