package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.DiscreteChoiceRandCoeff$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.DiscreteChoiceRandCoeff.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DiscreteChoiceRandCoeff$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
boolean[] guard$sample21categorical102$global;
		boolean[][] guard$sample21put101$global;
		boolean[] guard$sample47categorical102$global;
		boolean[][] guard$sample47put101$global;

		@Override
		public final void allocateScratch() {
			{
				int cv$max_i = 0;
				int cv$max_j$var97 = 0;
				for(int i = 0; i < state.noObs; i += 1)
					cv$max_j$var97 = Math.max(cv$max_j$var97, ((state.noProducts - 0) / 1));
				cv$max_i = Math.max(cv$max_i, ((state.noObs - 0) / 1));
				guard$sample21put101$global = new boolean[cv$max_i][cv$max_j$var97];
			}
			{
				int cv$max_i = 0;
				cv$max_i = Math.max(cv$max_i, ((state.noObs - 0) / 1));
				guard$sample21categorical102$global = new boolean[cv$max_i];
			}
			{
				int cv$max_i = 0;
				int cv$max_j$var97 = 0;
				for(int i = 0; i < state.noObs; i += 1)
					cv$max_j$var97 = Math.max(cv$max_j$var97, ((state.noProducts - 0) / 1));
				cv$max_i = Math.max(cv$max_i, ((state.noObs - 0) / 1));
				guard$sample47put101$global = new boolean[cv$max_i][cv$max_j$var97];
			}
			{
				int cv$max_i = 0;
				cv$max_i = Math.max(cv$max_i, ((state.noObs - 0) / 1));
				guard$sample47categorical102$global = new boolean[cv$max_i];
			}
		}
	}


	public DiscreteChoiceRandCoeff$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample21(int var20) {
		state.ut[var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		{
			{
				for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
					if((var20 == j$var69)) {
						for(int i = 0; i < state.noObs; i += 1)
							state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
					}
				}
			}
		}
		{
			boolean[][] guard$sample21put101 = scratch.guard$sample21put101$global;
			{
				for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
					if((var20 == j$var69)) {
						for(int i = 0; i < state.noObs; i += 1) {
							if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
								for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
									guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
							}
						}
					}
				}
			}
			{
				for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
					if((var20 == j$var69)) {
						for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
							if((j$var69 == j$var97)) {
								for(int i = 0; i < state.noObs; i += 1)
									guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
							}
						}
					}
				}
			}
			{
				for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
					if((var20 == j$var69)) {
						for(int i = 0; i < state.noObs; i += 1) {
							if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
								for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
									if(!guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
										guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
										{
											double reduceVar$sum$10 = 0.0;
											for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
												double k = reduceVar$sum$10;
												double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
												reduceVar$sum$10 = (k + l);
											}
											state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$10);
										}
									}
								}
							}
						}
					}
				}
			}
			{
				for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
					if((var20 == j$var69)) {
						for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
							if((j$var69 == j$var97)) {
								for(int i = 0; i < state.noObs; i += 1) {
									if(!guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
										guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
										{
											double reduceVar$sum$11 = 0.0;
											for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
												double k = reduceVar$sum$11;
												double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
												reduceVar$sum$11 = (k + l);
											}
											state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$11);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void drawValueSample28() {
		state.b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
	}

	private final void drawValueSample34() {
		state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
	}

	private final void drawValueSample47(int var46) {
		state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(state.RNG$)) + state.b);
		{
			{
				for(int i = 0; i < state.noObs; i += 1) {
					if((var46 == i)) {
						for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
							state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
					}
				}
			}
		}
		{
			boolean[][] guard$sample47put101 = scratch.guard$sample47put101$global;
			{
				for(int i = 0; i < state.noObs; i += 1) {
					if((var46 == i)) {
						for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
							if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
								for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
									guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
							}
						}
					}
				}
			}
			{
				for(int i = 0; i < state.noObs; i += 1) {
					if((var46 == i)) {
						for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
							for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
								if((j$var69 == j$var97))
									guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
							}
						}
					}
				}
			}
			{
				for(int i = 0; i < state.noObs; i += 1) {
					if((var46 == i)) {
						for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
							if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
								for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
									if(!guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
										guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
										{
											double reduceVar$sum$12 = 0.0;
											for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
												double k = reduceVar$sum$12;
												double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
												reduceVar$sum$12 = (k + l);
											}
											state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$12);
										}
									}
								}
							}
						}
					}
				}
			}
			{
				for(int i = 0; i < state.noObs; i += 1) {
					if((var46 == i)) {
						for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
							for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
								if((j$var69 == j$var97)) {
									if(!guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
										guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
										{
											double reduceVar$sum$13 = 0.0;
											for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
												double k = reduceVar$sum$13;
												double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
												reduceVar$sum$13 = (k + l);
											}
											state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$13);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample21(int var20) {
		if(true) {
			state.constrainedFlag$sample21[((var20 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = state.ut[var20];
			double cv$originalProbability = 0.0;
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample21[((var20 - 0) / 1)] || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						double var21 = cv$proposedValue;
						{
							{
								{
									state.ut[var20] = cv$currentValue;
								}
							}
						}
						{
							{
								for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
									if((var20 == j$var69)) {
										for(int i = 0; i < state.noObs; i += 1)
											state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
									}
								}
							}
						}
						{
							boolean[][] guard$sample21put101 = scratch.guard$sample21put101$global;
							{
								for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
									if((var20 == j$var69)) {
										for(int i = 0; i < state.noObs; i += 1) {
											if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
												for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
													guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
											}
										}
									}
								}
							}
							{
								for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
									if((var20 == j$var69)) {
										for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
											if((j$var69 == j$var97)) {
												for(int i = 0; i < state.noObs; i += 1)
													guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
											}
										}
									}
								}
							}
							{
								for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
									if((var20 == j$var69)) {
										for(int i = 0; i < state.noObs; i += 1) {
											if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
												for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
													if(!guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
														guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
														{
															double reduceVar$sum$0 = 0.0;
															for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
																double k = reduceVar$sum$0;
																double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
																reduceVar$sum$0 = (k + l);
															}
															state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$0);
														}
													}
												}
											}
										}
									}
								}
							}
							{
								for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
									if((var20 == j$var69)) {
										for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
											if((j$var69 == j$var97)) {
												for(int i = 0; i < state.noObs; i += 1) {
													if(!guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
														guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
														{
															double reduceVar$sum$1 = 0.0;
															for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
																double k = reduceVar$sum$1;
																double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
																reduceVar$sum$1 = (k + l);
															}
															state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$1);
														}
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
								boolean[] guard$sample21categorical102 = scratch.guard$sample21categorical102$global;
								{
									for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
										if((var20 == j$var69)) {
											for(int i = 0; i < state.noObs; i += 1) {
												if(((0 <= j$var69) && (j$var69 < state.noProducts)))
													guard$sample21categorical102[((i - 0) / 1)] = false;
											}
										}
									}
								}
								{
									for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
										if((var20 == j$var69)) {
											for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
												if((j$var69 == j$var97)) {
													for(int i = 0; i < state.noObs; i += 1)
														guard$sample21categorical102[((i - 0) / 1)] = false;
												}
											}
										}
									}
								}
								{
									double traceTempVariable$var70$9_1 = cv$currentValue;
									for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
										if((var20 == j$var69)) {
											for(int i = 0; i < state.noObs; i += 1) {
												double traceTempVariable$k$9_4 = Math.exp((traceTempVariable$var70$9_1 - (state.beta[i] * state.Prices[i][j$var69])));
												if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
													if((0 < state.noProducts)) {
														double reduceVar$sum$2 = 0.0;
														for(int cv$reduction343Index = 0; cv$reduction343Index < j$var69; cv$reduction343Index += 1) {
															double k = reduceVar$sum$2;
															double l = state.exped[((i - 0) / 1)][cv$reduction343Index];
															reduceVar$sum$2 = (k + l);
														}
														for(int cv$reduction343Index = (j$var69 + 1); cv$reduction343Index < state.noProducts; cv$reduction343Index += 1) {
															double k = reduceVar$sum$2;
															double l = state.exped[((i - 0) / 1)][cv$reduction343Index];
															reduceVar$sum$2 = (k + l);
														}
														double cv$reduced82 = reduceVar$sum$2;
														reduceVar$sum$2 = (traceTempVariable$k$9_4 + cv$reduced82);
														double traceTempVariable$sum$9_5 = reduceVar$sum$2;
														if(!guard$sample21categorical102[((i - 0) / 1)]) {
															guard$sample21categorical102[((i - 0) / 1)] = true;
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample21[((var20 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							if(((Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)));
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
									double traceTempVariable$var70$10_1 = cv$currentValue;
									for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
										if((var20 == j$var69)) {
											for(int i = 0; i < state.noObs; i += 1) {
												double traceTempVariable$var98$10_4 = Math.exp((traceTempVariable$var70$10_1 - (state.beta[i] * state.Prices[i][j$var69])));
												for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
													if((j$var69 == j$var97)) {
														if(!guard$sample21categorical102[((i - 0) / 1)]) {
															guard$sample21categorical102[((i - 0) / 1)] = true;
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample21[((var20 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							if(((Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)));
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
							double var21 = cv$originalValue;
							{
								{
									{
										state.ut[var20] = var21;
									}
								}
							}
							{
								{
									for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
										if((var20 == j$var69)) {
											for(int i = 0; i < state.noObs; i += 1)
												state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
										}
									}
								}
							}
							{
								boolean[][] guard$sample21put101 = scratch.guard$sample21put101$global;
								{
									for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
										if((var20 == j$var69)) {
											for(int i = 0; i < state.noObs; i += 1) {
												if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
													for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
														guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
												}
											}
										}
									}
								}
								{
									for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
										if((var20 == j$var69)) {
											for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
												if((j$var69 == j$var97)) {
													for(int i = 0; i < state.noObs; i += 1)
														guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
												}
											}
										}
									}
								}
								{
									for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
										if((var20 == j$var69)) {
											for(int i = 0; i < state.noObs; i += 1) {
												if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
													for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
														if(!guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
															guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
															{
																double reduceVar$sum$3 = 0.0;
																for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
																	double k = reduceVar$sum$3;
																	double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
																	reduceVar$sum$3 = (k + l);
																}
																state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$3);
															}
														}
													}
												}
											}
										}
									}
								}
								{
									for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
										if((var20 == j$var69)) {
											for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
												if((j$var69 == j$var97)) {
													for(int i = 0; i < state.noObs; i += 1) {
														if(!guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
															guard$sample21put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
															{
																double reduceVar$sum$4 = 0.0;
																for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
																	double k = reduceVar$sum$4;
																	double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
																	reduceVar$sum$4 = (k + l);
																}
																state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$4);
															}
														}
													}
												}
											}
										}
									}
								}
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
			double cv$sum = 0.0;
			double cv$denominatorSquareSum = 0.0;
			boolean cv$sigmaNotFound = true;
			double cv$sigmaValue = 1.0;
			{
				{
					{
						{
							{
								{
									for(int var46 = 0; var46 < state.noObs; var46 += 1) {
										boolean cv$sampleConstrained = (state.fixedFlag$sample47 || state.constrainedFlag$sample47[((var46 - 0) / 1)]);
										if(cv$sampleConstrained) {
											state.constrainedFlag$sample28 = true;
											{
												{
													{
														{
															{
																double cv$denominator = 1.0;
																double cv$numerator = 0.0;
																cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
																cv$sum = (cv$sum + (cv$denominator * (state.beta[var46] - cv$numerator)));
																if(cv$sigmaNotFound) {
																	cv$sigmaValue = state.sigma;
																	cv$sigmaNotFound = false;
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(state.constrainedFlag$sample28)
				state.b = Conjugates.sampleConjugateGaussianGaussian(state.RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
		}
	}

	private final void inferSample34() {
		if(true) {
			state.constrainedFlag$sample34 = false;
			double cv$sum = 0.0;
			int cv$count = 0;
			{
				{
					{
						{
							{
								{
									for(int var46 = 0; var46 < state.noObs; var46 += 1) {
										boolean cv$sampleConstrained = (state.fixedFlag$sample47 || state.constrainedFlag$sample47[((var46 - 0) / 1)]);
										if(cv$sampleConstrained) {
											state.constrainedFlag$sample34 = true;
											{
												{
													{
														{
															{
																double cv$var35$mu = state.b;
																double cv$var35$diff = (cv$var35$mu - state.beta[var46]);
																cv$sum = (cv$sum + (cv$var35$diff * cv$var35$diff));
																cv$count = (cv$count + 1);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(state.constrainedFlag$sample34)
				state.sigma = Conjugates.sampleConjugateInverseGammaGaussian(state.RNG$, 2.0, 2.0, cv$sum, cv$count);
		}
	}

	private final void inferSample47(int var46) {
		if(true) {
			state.constrainedFlag$sample47[((var46 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = state.beta[var46];
			double cv$originalProbability = 0.0;
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample47[((var46 - 0) / 1)] || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						double var47 = cv$proposedValue;
						{
							{
								{
									state.beta[var46] = cv$currentValue;
								}
							}
						}
						{
							{
								for(int i = 0; i < state.noObs; i += 1) {
									if((var46 == i)) {
										for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
											state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
									}
								}
							}
						}
						{
							boolean[][] guard$sample47put101 = scratch.guard$sample47put101$global;
							{
								for(int i = 0; i < state.noObs; i += 1) {
									if((var46 == i)) {
										for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
											if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
												for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
													guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
											}
										}
									}
								}
							}
							{
								for(int i = 0; i < state.noObs; i += 1) {
									if((var46 == i)) {
										for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
											for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
												if((j$var69 == j$var97))
													guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
											}
										}
									}
								}
							}
							{
								for(int i = 0; i < state.noObs; i += 1) {
									if((var46 == i)) {
										for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
											if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
												for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
													if(!guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
														guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
														{
															double reduceVar$sum$5 = 0.0;
															for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
																double k = reduceVar$sum$5;
																double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
																reduceVar$sum$5 = (k + l);
															}
															state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$5);
														}
													}
												}
											}
										}
									}
								}
							}
							{
								for(int i = 0; i < state.noObs; i += 1) {
									if((var46 == i)) {
										for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
											for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
												if((j$var69 == j$var97)) {
													if(!guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
														guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
														{
															double reduceVar$sum$6 = 0.0;
															for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
																double k = reduceVar$sum$6;
																double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
																reduceVar$sum$6 = (k + l);
															}
															state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$6);
														}
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
						double cv$accumulatedProbabilities = (Math.log(1.0) + ((0.0 < state.sigma)?(DistributionSampling.logProbabilityGaussian(((cv$currentValue - state.b) / Math.sqrt(state.sigma))) - (0.5 * Math.log(state.sigma))):Double.NEGATIVE_INFINITY));
						{
							{
								boolean[] guard$sample47categorical102 = scratch.guard$sample47categorical102$global;
								{
									for(int i = 0; i < state.noObs; i += 1) {
										if((var46 == i)) {
											for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
												if(((0 <= j$var69) && (j$var69 < state.noProducts)))
													guard$sample47categorical102[((i - 0) / 1)] = false;
											}
										}
									}
								}
								{
									for(int i = 0; i < state.noObs; i += 1) {
										if((var46 == i)) {
											for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
												for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
													if((j$var69 == j$var97))
														guard$sample47categorical102[((i - 0) / 1)] = false;
												}
											}
										}
									}
								}
								{
									double traceTempVariable$var71$9_1 = cv$currentValue;
									for(int i = 0; i < state.noObs; i += 1) {
										if((var46 == i)) {
											for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
												double traceTempVariable$k$9_4 = Math.exp((state.ut[j$var69] - (traceTempVariable$var71$9_1 * state.Prices[i][j$var69])));
												if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
													if((0 < state.noProducts)) {
														double reduceVar$sum$7 = 0.0;
														for(int cv$reduction696Index = 0; cv$reduction696Index < j$var69; cv$reduction696Index += 1) {
															double k = reduceVar$sum$7;
															double l = state.exped[((i - 0) / 1)][cv$reduction696Index];
															reduceVar$sum$7 = (k + l);
														}
														for(int cv$reduction696Index = (j$var69 + 1); cv$reduction696Index < state.noProducts; cv$reduction696Index += 1) {
															double k = reduceVar$sum$7;
															double l = state.exped[((i - 0) / 1)][cv$reduction696Index];
															reduceVar$sum$7 = (k + l);
														}
														double cv$reduced82 = reduceVar$sum$7;
														reduceVar$sum$7 = (traceTempVariable$k$9_4 + cv$reduced82);
														double traceTempVariable$sum$9_5 = reduceVar$sum$7;
														if(!guard$sample47categorical102[((i - 0) / 1)]) {
															guard$sample47categorical102[((i - 0) / 1)] = true;
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample47[((var46 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							if(((Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)));
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
									double traceTempVariable$var71$10_1 = cv$currentValue;
									for(int i = 0; i < state.noObs; i += 1) {
										if((var46 == i)) {
											for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
												double traceTempVariable$var98$10_4 = Math.exp((state.ut[j$var69] - (traceTempVariable$var71$10_1 * state.Prices[i][j$var69])));
												for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
													if((j$var69 == j$var97)) {
														if(!guard$sample47categorical102[((i - 0) / 1)]) {
															guard$sample47categorical102[((i - 0) / 1)] = true;
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample47[((var46 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							if(((Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][state.choices[i]])) && (state.prob[((i - 0) / 1)][state.choices[i]] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][state.choices[i]]):Double.NEGATIVE_INFINITY)));
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
							double var47 = cv$originalValue;
							{
								{
									{
										state.beta[var46] = var47;
									}
								}
							}
							{
								{
									for(int i = 0; i < state.noObs; i += 1) {
										if((var46 == i)) {
											for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
												state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
										}
									}
								}
							}
							{
								boolean[][] guard$sample47put101 = scratch.guard$sample47put101$global;
								{
									for(int i = 0; i < state.noObs; i += 1) {
										if((var46 == i)) {
											for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
												if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
													for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
														guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
												}
											}
										}
									}
								}
								{
									for(int i = 0; i < state.noObs; i += 1) {
										if((var46 == i)) {
											for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
												for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
													if((j$var69 == j$var97))
														guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = false;
												}
											}
										}
									}
								}
								{
									for(int i = 0; i < state.noObs; i += 1) {
										if((var46 == i)) {
											for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
												if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
													for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
														if(!guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
															guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
															{
																double reduceVar$sum$8 = 0.0;
																for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
																	double k = reduceVar$sum$8;
																	double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
																	reduceVar$sum$8 = (k + l);
																}
																state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$8);
															}
														}
													}
												}
											}
										}
									}
								}
								{
									for(int i = 0; i < state.noObs; i += 1) {
										if((var46 == i)) {
											for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
												for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
													if((j$var69 == j$var97)) {
														if(!guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)]) {
															guard$sample47put101[((i - 0) / 1)][((j$var97 - 0) / 1)] = true;
															{
																double reduceVar$sum$9 = 0.0;
																for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
																	double k = reduceVar$sum$9;
																	double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
																	reduceVar$sum$9 = (k + l);
																}
																state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$9);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample103() {
		if(!state.fixedProbFlag$sample103) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < state.noObs; i += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = state.choices[i];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[((i - 0) / 1)][cv$sampleValue])) && (state.prob[((i - 0) / 1)][cv$sampleValue] <= 1.0))?Math.log(state.prob[((i - 0) / 1)][cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample103[((i - 0) / 1)] = cv$sampleProbability;
			}
			state.logProbability$choices = (state.logProbability$choices + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample103 = (state.fixedFlag$sample21 && state.fixedFlag$sample47);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < state.noObs; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample103[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			state.logProbability$choices = (state.logProbability$choices + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample21() {
		if(!state.fixedProbFlag$sample21) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var20 = 0; var20 < state.noProducts; var20 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.ut[var20];
						{
							{
								double var7 = 0.0;
								double var8 = 10.0;
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var8)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var7) / Math.sqrt(var8))) - (0.5 * Math.log(var8))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample21[((var20 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$prob = false;
				{
					{
						for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
							if((var20 == j$var69)) {
								for(int i = 0; i < state.noObs; i += 1) {
									if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
										for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
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
					{
						for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
							if((var20 == j$var69)) {
								for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
									if((j$var69 == j$var97)) {
										for(int i = 0; i < state.noObs; i += 1) {
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
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			state.logProbability$ut = (state.logProbability$ut + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample21)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample21 = state.fixedFlag$sample21;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var20 = 0; var20 < state.noProducts; var20 += 1) {
				double cv$sampleValue = state.logProbability$sample21[((var20 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				boolean cv$guard$prob = false;
				{
					{
						for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
							if((var20 == j$var69)) {
								for(int i = 0; i < state.noObs; i += 1) {
									if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
										for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
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
					{
						for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
							if((var20 == j$var69)) {
								for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
									if((j$var69 == j$var97)) {
										for(int i = 0; i < state.noObs; i += 1) {
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
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$ut = (state.logProbability$ut + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample21)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!state.fixedProbFlag$sample28) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = state.b;
					{
						{
							double var25 = 0.0;
							double var26 = 10.0;
							double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var26)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var25) / Math.sqrt(var26))) - (0.5 * Math.log(var26))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample28 = state.fixedFlag$sample28;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$b;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample34() {
		if(!state.fixedProbFlag$sample34) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = state.sigma;
					{
						{
							double var31 = 2.0;
							double var32 = 2.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var31, var32));
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
			state.logProbability$sigma = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample34)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample34 = state.fixedFlag$sample34;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$sigma;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample34)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample47() {
		if(!state.fixedProbFlag$sample47) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var46 = 0; var46 < state.noObs; var46 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.beta[var46];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < state.sigma)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.b) / Math.sqrt(state.sigma))) - (0.5 * Math.log(state.sigma))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample47[((var46 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$prob = false;
				{
					{
						for(int i = 0; i < state.noObs; i += 1) {
							if((var46 == i)) {
								for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
									if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
										for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
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
					{
						for(int i = 0; i < state.noObs; i += 1) {
							if((var46 == i)) {
								for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
									for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
										if((j$var69 == j$var97)) {
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
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			state.logProbability$beta = (state.logProbability$beta + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample47)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample47 = ((state.fixedFlag$sample47 && state.fixedFlag$sample28) && state.fixedFlag$sample34);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var46 = 0; var46 < state.noObs; var46 += 1) {
				double cv$sampleValue = state.logProbability$sample47[((var46 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				boolean cv$guard$prob = false;
				{
					{
						for(int i = 0; i < state.noObs; i += 1) {
							if((var46 == i)) {
								for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
									if(((0 <= j$var69) && (j$var69 < state.noProducts))) {
										for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
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
					{
						for(int i = 0; i < state.noObs; i += 1) {
							if((var46 == i)) {
								for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
									for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
										if((j$var69 == j$var97)) {
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
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$beta = (state.logProbability$beta + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample47)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		for(int var20 = 0; var20 < state.noProducts; var20 += 1) {
			if(!state.fixedFlag$sample21)
				state.ut[var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		}
		if(!state.fixedFlag$sample28)
			state.b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample34)
			state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
		for(int var46 = 0; var46 < state.noObs; var46 += 1) {
			if(!state.fixedFlag$sample47)
				state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(state.RNG$)) + state.b);
		}
		for(int i = 0; i < state.noObs; i += 1) {
			for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
				if(!(state.fixedFlag$sample21 && state.fixedFlag$sample47))
					state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
			}
			double reduceVar$sum$14 = 0.0;
			for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
				double k = reduceVar$sum$14;
				double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
				if(!(state.fixedFlag$sample21 && state.fixedFlag$sample47))
					reduceVar$sum$14 = (k + l);
			}
			for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
				if(!(state.fixedFlag$sample21 && state.fixedFlag$sample47))
					state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$14);
			}
			state.choices[i] = DistributionSampling.sampleCategorical(state.RNG$, state.prob[((i - 0) / 1)], state.noProducts);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		for(int var20 = 0; var20 < state.noProducts; var20 += 1) {
			if(!state.fixedFlag$sample21)
				state.ut[var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		}
		if(!state.fixedFlag$sample28)
			state.b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample34)
			state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
		for(int var46 = 0; var46 < state.noObs; var46 += 1) {
			if(!state.fixedFlag$sample47)
				state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(state.RNG$)) + state.b);
		}
		for(int i = 0; i < state.noObs; i += 1) {
			for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
				state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
			double reduceVar$sum$18 = 0.0;
			for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
				double k = reduceVar$sum$18;
				double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
				reduceVar$sum$18 = (k + l);
			}
			for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
				state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$18);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		for(int var20 = 0; var20 < state.noProducts; var20 += 1) {
			if(!state.fixedFlag$sample21)
				state.ut[var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		}
		if(!state.fixedFlag$sample28)
			state.b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample34)
			state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
		for(int var46 = 0; var46 < state.noObs; var46 += 1) {
			if(!state.fixedFlag$sample47)
				state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(state.RNG$)) + state.b);
		}
		for(int i = 0; i < state.noObs; i += 1) {
			for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
				state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
			double reduceVar$sum$15 = 0.0;
			for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
				double k = reduceVar$sum$15;
				double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
				reduceVar$sum$15 = (k + l);
			}
			for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
				state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$15);
			state.choices[i] = DistributionSampling.sampleCategorical(state.RNG$, state.prob[((i - 0) / 1)], state.noProducts);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var20 = 0; var20 < state.noProducts; var20 += 1) {
			if(!state.fixedFlag$sample21)
				state.ut[var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		}
		if(!state.fixedFlag$sample28)
			state.b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample34)
			state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
		for(int var46 = 0; var46 < state.noObs; var46 += 1) {
			if(!state.fixedFlag$sample47)
				state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(state.RNG$)) + state.b);
		}
		for(int i = 0; i < state.noObs; i += 1) {
			for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
				if(!(state.fixedFlag$sample21 && state.fixedFlag$sample47))
					state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
			}
			double reduceVar$sum$16 = 0.0;
			for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
				double k = reduceVar$sum$16;
				double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
				if(!(state.fixedFlag$sample21 && state.fixedFlag$sample47))
					reduceVar$sum$16 = (k + l);
			}
			for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
				if(!(state.fixedFlag$sample21 && state.fixedFlag$sample47))
					state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$16);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		for(int var20 = 0; var20 < state.noProducts; var20 += 1) {
			if(!state.fixedFlag$sample21)
				state.ut[var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		}
		if(!state.fixedFlag$sample28)
			state.b = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample34)
			state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
		for(int var46 = 0; var46 < state.noObs; var46 += 1) {
			if(!state.fixedFlag$sample47)
				state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(state.RNG$)) + state.b);
		}
		for(int i = 0; i < state.noObs; i += 1) {
			for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
				state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
			double reduceVar$sum$17 = 0.0;
			for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
				double k = reduceVar$sum$17;
				double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
				reduceVar$sum$17 = (k + l);
			}
			for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
				state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$17);
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			for(int var20 = 0; var20 < state.noProducts; var20 += 1) {
				if(!state.fixedFlag$sample21)
					inferSample21(var20);
			}
			if(!state.fixedFlag$sample28)
				inferSample28();
			if(!state.fixedFlag$sample34)
				inferSample34();
			for(int var46 = 0; var46 < state.noObs; var46 += 1) {
				if(!state.fixedFlag$sample47)
					inferSample47(var46);
			}
		} else {
			for(int var46 = (state.noObs - ((((state.noObs - 1) - 0) % 1) + 1)); var46 >= ((0 - 1) + 1); var46 -= 1) {
				if(!state.fixedFlag$sample47)
					inferSample47(var46);
			}
			if(!state.fixedFlag$sample34)
				inferSample34();
			if(!state.fixedFlag$sample28)
				inferSample28();
			for(int var20 = (state.noProducts - ((((state.noProducts - 1) - 0) % 1) + 1)); var20 >= ((0 - 1) + 1); var20 -= 1) {
				if(!state.fixedFlag$sample21)
					inferSample21(var20);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int var20 = 0; var20 < state.noProducts; var20 += 1) {
			if(!state.constrainedFlag$sample21[((var20 - 0) / 1)])
				drawValueSample21(var20);
		}
		if(!state.constrainedFlag$sample28)
			drawValueSample28();
		if(!state.constrainedFlag$sample34)
			drawValueSample34();
		for(int var46 = 0; var46 < state.noObs; var46 += 1) {
			if(!state.constrainedFlag$sample47[((var46 - 0) / 1)])
				drawValueSample47(var46);
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$ut = 0.0;
		state.logProbability$prob = 0.0;
		if(!state.fixedProbFlag$sample21) {
			for(int var20 = 0; var20 < state.noProducts; var20 += 1)
				state.logProbability$sample21[((var20 - 0) / 1)] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample28)
			state.logProbability$b = Double.NaN;
		if(!state.fixedProbFlag$sample34)
			state.logProbability$sigma = Double.NaN;
		state.logProbability$beta = 0.0;
		if(!state.fixedProbFlag$sample47) {
			for(int var46 = 0; var46 < state.noObs; var46 += 1)
				state.logProbability$sample47[((var46 - 0) / 1)] = Double.NaN;
		}
		state.logProbability$choices = 0.0;
		if(!state.fixedProbFlag$sample103) {
			for(int i = 0; i < state.noObs; i += 1)
				state.logProbability$sample103[((i - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		for(int index$constrainedFlag$sample47$1 = 0; index$constrainedFlag$sample47$1 < state.constrainedFlag$sample47.length; index$constrainedFlag$sample47$1 += 1)
			state.constrainedFlag$sample47[index$constrainedFlag$sample47$1] = true;
		for(int index$constrainedFlag$sample21$1 = 0; index$constrainedFlag$sample21$1 < state.constrainedFlag$sample21.length; index$constrainedFlag$sample21$1 += 1)
			state.constrainedFlag$sample21[index$constrainedFlag$sample21$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample21)
			logProbabilityValue$sample21();
		if(state.fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(state.fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(state.fixedFlag$sample47)
			logProbabilityValue$sample47();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample21();
		logProbabilityValue$sample28();
		logProbabilityValue$sample34();
		logProbabilityValue$sample47();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample21();
		logProbabilityValue$sample28();
		logProbabilityValue$sample34();
		logProbabilityValue$sample47();
		logProbabilityValue$sample103();
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
		for(int i = 0; i < state.noObs; i += 1) {
			for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
				state.exped[((i - 0) / 1)][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
			double reduceVar$sum$19 = 0.0;
			for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1) {
				double k = reduceVar$sum$19;
				double l = state.exped[((i - 0) / 1)][cv$reduction82Index];
				reduceVar$sum$19 = (k + l);
			}
			for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
				state.prob[((i - 0) / 1)][j$var97] = (state.exped[((i - 0) / 1)][j$var97] / reduceVar$sum$19);
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
		     + "\n"
		     + "model DiscreteChoiceRandCoeff(int noProducts, int noObs, int[] ObsChoices, int[][] Prices) {\n"
		     + "    // we just need an uninformative prior for utility intercepts\n"
		     + "\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = gaussian(0, 10).sample(noProducts);\n"
		     + "    //can we set the first element to 0? like ut[0] <~ 0\n"
		     + "\n"
		     + "    //priors for distribution of beta\n"
		     + "    double b = gaussian(0,10).sample();\n"
		     + "    double sigma =  inverseGamma(2,2).sample();\n"
		     + "\n"
		     + "    double[] beta = gaussian(b, sigma).sample(noObs);\n"
		     + "\n"
		     + "    int[] choices = new int[noObs];\n"
		     + "\n"
		     + "    for (int i:[0..noObs)){\n"
		     + "        // calculate choice probabilities for consumer i\n"
		     + "\n"
		     + "        double[] exped = new double[noProducts];\n"
		     + "        for(int j : [0..noProducts)) {\n"
		     + "            exped[j] = exp(ut[j]- beta[i]*Prices[i][j]);\n"
		     + "            }\n"
		     + "        double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n"
		     + "        public double[] prob = new double[noProducts];\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            prob[j] = exped[j] / sum;\n"
		     + "        }\n"
		     + "        // emit choices of consumer i\n"
		     + "        choices[i] = categorical(prob).sample();\n"
		     + "                                }\n"
		     + "\n"
		     + "    // assert that generated choices match observed choices\n"
		     + "    choices.observe(ObsChoices);\n"
		     + "}";
	}
}