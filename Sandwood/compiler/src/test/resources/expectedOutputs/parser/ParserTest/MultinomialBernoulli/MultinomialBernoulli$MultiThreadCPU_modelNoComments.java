package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.MultinomialBernoulli$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.MultinomialBernoulli.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class MultinomialBernoulli$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var17$countGlobal;

		@Override
		public final void allocateScratch() {
			cv$var17$countGlobal = new double[3];
		}
	}


	public MultinomialBernoulli$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample17() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.beta, 3, state.p);
	}

	private final void drawValueSample20() {
		DistributionSampling.sampleMultinomial(state.RNG$, state.p, 3, state.n, state.prior);
	}

	private final void inferSample17() {
		if(true) {
			state.constrainedFlag$sample17 = false;
			double[] cv$targetLocal = state.p;
			double[] cv$countLocal = scratch.cv$var17$countGlobal;
			int cv$arrayLength = 3;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							{
								{
									boolean cv$sampleConstrained = (state.fixedFlag$sample20 || state.constrainedFlag$sample20);
									if(cv$sampleConstrained) {
										state.constrainedFlag$sample17 = true;
										{
											{
												{
													{
														{
															int[] cv$sampleValue = state.prior;
															for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (1.0 * cv$sampleValue[cv$loopIndex]));
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(state.constrainedFlag$sample17)
				Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.beta, cv$countLocal, cv$targetLocal, 3);
		}
	}

	private final void inferSample20() {
		if(!(state.n == 0)) {
			state.constrainedFlag$sample20 = false;
			int[] cv$targetLocal = state.prior;
			double cv$originalProbability = 0.0;
			double cv$proposedProbability = 0.0;
			int cv$arrayLength = 3;
			int cv$nonZeroCount = 0;
			for(int cv$loopIndex = 0; cv$loopIndex < 3; cv$loopIndex += 1) {
				if(!(cv$targetLocal[cv$loopIndex] == 0))
					cv$nonZeroCount = (cv$nonZeroCount + 1);
			}
			int cv$sourceIndex = (int)(0.0 + ((cv$nonZeroCount - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			for(int cv$loopIndex = 0; cv$loopIndex < (cv$sourceIndex + 1); cv$loopIndex += 1) {
				if((cv$targetLocal[cv$loopIndex] == 0))
					cv$sourceIndex = (cv$sourceIndex + 1);
			}
			int cv$changeValue = (int)(1.0 + (((cv$targetLocal[cv$sourceIndex] + 1.0) - 1.0) * DistributionSampling.sampleUniform(state.RNG$)));
			int cv$destinationIndex = (int)(0.0 + (((cv$arrayLength - 1) - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			if((cv$sourceIndex <= cv$destinationIndex))
				cv$destinationIndex = (cv$destinationIndex + 1);
			for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
				if((state.constrainedFlag$sample20 || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					if((cv$valuePos == 1)) {
						{
							cv$targetLocal[cv$sourceIndex] = (cv$targetLocal[cv$sourceIndex] - cv$changeValue);
							cv$targetLocal[cv$destinationIndex] = (cv$targetLocal[cv$destinationIndex] + cv$changeValue);
						}
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(cv$targetLocal, state.p, 3, state.n));
						{
							{
								{
									{
										{
											for(int i$var47 = 0; i$var47 < state.length; i$var47 += 3) {
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													state.constrainedFlag$sample20 = true;
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	{
																		double var24 = (double)(state.prior[0] / state.n);
																		if(((Math.log(1.0) + (((0.0 <= var24) && (var24 <= 1.0))?Math.log((state.output[i$var47]?var24:(1.0 - var24))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var24) && (var24 <= 1.0))?Math.log((state.output[i$var47]?var24:(1.0 - var24))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var24) && (var24 <= 1.0))?Math.log((state.output[i$var47]?var24:(1.0 - var24))):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var24) && (var24 <= 1.0))?Math.log((state.output[i$var47]?var24:(1.0 - var24))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var24) && (var24 <= 1.0))?Math.log((state.output[i$var47]?var24:(1.0 - var24))):Double.NEGATIVE_INFINITY)));
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
							{
								{
									{
										{
											for(int i$var59 = 1; i$var59 < state.length; i$var59 += 3) {
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													state.constrainedFlag$sample20 = true;
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	{
																		double var29 = (double)(state.prior[1] / state.n);
																		if(((Math.log(1.0) + (((0.0 <= var29) && (var29 <= 1.0))?Math.log((state.output[i$var59]?var29:(1.0 - var29))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var29) && (var29 <= 1.0))?Math.log((state.output[i$var59]?var29:(1.0 - var29))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var29) && (var29 <= 1.0))?Math.log((state.output[i$var59]?var29:(1.0 - var29))):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var29) && (var29 <= 1.0))?Math.log((state.output[i$var59]?var29:(1.0 - var29))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var29) && (var29 <= 1.0))?Math.log((state.output[i$var59]?var29:(1.0 - var29))):Double.NEGATIVE_INFINITY)));
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
							{
								{
									{
										{
											for(int i$var71 = 2; i$var71 < state.length; i$var71 += 3) {
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													state.constrainedFlag$sample20 = true;
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	{
																		double var34 = (double)(state.prior[2] / state.n);
																		if(((Math.log(1.0) + (((0.0 <= var34) && (var34 <= 1.0))?Math.log((state.output[i$var71]?var34:(1.0 - var34))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var34) && (var34 <= 1.0))?Math.log((state.output[i$var71]?var34:(1.0 - var34))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var34) && (var34 <= 1.0))?Math.log((state.output[i$var71]?var34:(1.0 - var34))):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var34) && (var34 <= 1.0))?Math.log((state.output[i$var71]?var34:(1.0 - var34))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var34) && (var34 <= 1.0))?Math.log((state.output[i$var71]?var34:(1.0 - var34))):Double.NEGATIVE_INFINITY)));
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
					if((cv$valuePos == 0))
						cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
					else
						cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
					double cv$ratio = (cv$proposedProbability - cv$originalProbability);
					if((cv$valuePos == 1)) {
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$))))) || Double.isNaN(cv$ratio))) {
							cv$targetLocal[cv$sourceIndex] = (cv$targetLocal[cv$sourceIndex] + cv$changeValue);
							cv$targetLocal[cv$destinationIndex] = (cv$targetLocal[cv$destinationIndex] - cv$changeValue);
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample17() {
		if(!state.fixedProbFlag$sample17) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double[] cv$sampleValue = state.p;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, state.beta, 3));
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
			state.logProbability$p = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample17)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample17 = state.fixedFlag$sample17;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$p;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample17)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample20() {
		if(!state.fixedProbFlag$sample20) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int[] cv$sampleValue = state.prior;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(cv$sampleValue, state.p, 3, state.n));
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
			state.logProbability$prior = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample20)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample20 = (state.fixedFlag$sample20 && state.fixedFlag$sample17);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$prior;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample20)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample48() {
		if(!state.fixedProbFlag$sample48) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var47 = 0; i$var47 < state.length; i$var47 += 3) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.output[i$var47];
						{
							{
								double var24 = (double)(state.prior[0] / state.n);
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var24) && (var24 <= 1.0))?Math.log((cv$sampleValue?var24:(1.0 - var24))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$b1 = cv$sampleAccumulator;
			state.logProbability$var48 = cv$sampleAccumulator;
			state.logProbability$output = (state.logProbability$output + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample48 = state.fixedFlag$sample20;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var47 = 0; i$var47 < state.length; i$var47 += 3)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var48;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$b1 = cv$rvAccumulator;
			state.logProbability$output = (state.logProbability$output + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample60() {
		if(!state.fixedProbFlag$sample60) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var59 = 1; i$var59 < state.length; i$var59 += 3) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.output[i$var59];
						{
							{
								double var29 = (double)(state.prior[1] / state.n);
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var29) && (var29 <= 1.0))?Math.log((cv$sampleValue?var29:(1.0 - var29))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$b2 = cv$sampleAccumulator;
			state.logProbability$var60 = cv$sampleAccumulator;
			state.logProbability$output = (state.logProbability$output + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample60 = state.fixedFlag$sample20;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var59 = 1; i$var59 < state.length; i$var59 += 3)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var60;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$b2 = cv$rvAccumulator;
			state.logProbability$output = (state.logProbability$output + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample72() {
		if(!state.fixedProbFlag$sample72) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var71 = 2; i$var71 < state.length; i$var71 += 3) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.output[i$var71];
						{
							{
								double var34 = (double)(state.prior[2] / state.n);
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var34) && (var34 <= 1.0))?Math.log((cv$sampleValue?var34:(1.0 - var34))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$b3 = cv$sampleAccumulator;
			state.logProbability$var72 = cv$sampleAccumulator;
			state.logProbability$output = (state.logProbability$output + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample72 = state.fixedFlag$sample20;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var71 = 2; i$var71 < state.length; i$var71 += 3)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var72;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$b3 = cv$rvAccumulator;
			state.logProbability$output = (state.logProbability$output + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.beta, 3, state.p);
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(state.RNG$, state.p, 3, state.n, state.prior);
		parallelFor(state.RNG$, 0, state.length, 3,
			(int forStart$i$var47, int forEnd$i$var47, int threadID$i$var47, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var47 = forStart$i$var47; i$var47 < forEnd$i$var47; i$var47 += 3)
						state.output[i$var47] = DistributionSampling.sampleBernoulli(RNG$1, (state.prior[0] / state.n));
			}
		);
		parallelFor(state.RNG$, 1, state.length, 3,
			(int forStart$i$var59, int forEnd$i$var59, int threadID$i$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var59 = forStart$i$var59; i$var59 < forEnd$i$var59; i$var59 += 3)
						state.output[i$var59] = DistributionSampling.sampleBernoulli(RNG$1, (state.prior[1] / state.n));
			}
		);
		parallelFor(state.RNG$, 2, state.length, 3,
			(int forStart$i$var71, int forEnd$i$var71, int threadID$i$var71, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var71 = forStart$i$var71; i$var71 < forEnd$i$var71; i$var71 += 3)
						state.output[i$var71] = DistributionSampling.sampleBernoulli(RNG$1, (state.prior[2] / state.n));
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.beta, 3, state.p);
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(state.RNG$, state.p, 3, state.n, state.prior);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.beta, 3, state.p);
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(state.RNG$, state.p, 3, state.n, state.prior);
		parallelFor(state.RNG$, 0, state.length, 3,
			(int forStart$i$var47, int forEnd$i$var47, int threadID$i$var47, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var47 = forStart$i$var47; i$var47 < forEnd$i$var47; i$var47 += 3)
						state.output[i$var47] = DistributionSampling.sampleBernoulli(RNG$1, (state.prior[0] / state.n));
			}
		);
		parallelFor(state.RNG$, 1, state.length, 3,
			(int forStart$i$var59, int forEnd$i$var59, int threadID$i$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var59 = forStart$i$var59; i$var59 < forEnd$i$var59; i$var59 += 3)
						state.output[i$var59] = DistributionSampling.sampleBernoulli(RNG$1, (state.prior[1] / state.n));
			}
		);
		parallelFor(state.RNG$, 2, state.length, 3,
			(int forStart$i$var71, int forEnd$i$var71, int threadID$i$var71, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var71 = forStart$i$var71; i$var71 < forEnd$i$var71; i$var71 += 3)
						state.output[i$var71] = DistributionSampling.sampleBernoulli(RNG$1, (state.prior[2] / state.n));
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.beta, 3, state.p);
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(state.RNG$, state.p, 3, state.n, state.prior);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.beta, 3, state.p);
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(state.RNG$, state.p, 3, state.n, state.prior);
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample17)
				inferSample17();
			if(!state.fixedFlag$sample20)
				inferSample20();
		} else {
			if(!state.fixedFlag$sample20)
				inferSample20();
			if(!state.fixedFlag$sample17)
				inferSample17();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample17)
			drawValueSample17();
		if(!state.constrainedFlag$sample20)
			drawValueSample20();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample17)
			state.logProbability$p = Double.NaN;
		if(!state.fixedProbFlag$sample20)
			state.logProbability$prior = Double.NaN;
		state.logProbability$b1 = Double.NaN;
		state.logProbability$output = 0.0;
		if(!state.fixedProbFlag$sample48)
			state.logProbability$var48 = Double.NaN;
		state.logProbability$b2 = Double.NaN;
		if(!state.fixedProbFlag$sample60)
			state.logProbability$var60 = Double.NaN;
		state.logProbability$b3 = Double.NaN;
		if(!state.fixedProbFlag$sample72)
			state.logProbability$var72 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.beta[0] = 0.1;
		state.beta[1] = 0.1;
		state.beta[2] = 0.1;
		state.n = 10;
		state.length = state.length$observed;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample17)
			logProbabilityValue$sample17();
		if(state.fixedFlag$sample20)
			logProbabilityValue$sample20();
		logProbabilityValue$sample48();
		logProbabilityValue$sample60();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample20();
		logProbabilityValue$sample48();
		logProbabilityValue$sample60();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample20();
		logProbabilityValue$sample48();
		logProbabilityValue$sample60();
		logProbabilityValue$sample72();
	}

	@Override
	public final void propagateObservedValues() {
		boolean[] cv$source1 = state.observed;
		boolean[] cv$target1 = state.output;
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
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model MultinomialBernoulli(boolean[] observed) {\n"
		     + "    double[] beta = {0.1, 0.1, 0.1};\n"
		     + "    double[] p = dirichlet(beta).sample();\n"
		     + "    int n = 10;\n"
		     + "    int[] prior = multinomial(p, n).sample();\n"
		     + "    Bernoulli b1 = new Bernoulli(prior[0]/n);\n"
		     + "    Bernoulli b2 = new Bernoulli(prior[1]/n);\n"
		     + "    Bernoulli b3 = new Bernoulli(prior[2]/n);\n"
		     + "    int length = observed.length;\n"
		     + "    boolean[] output = new boolean[length];\n"
		     + "    for(int i=0; i<length; i+=3)\n"
		     + "        output[i] = b1.sample();\n"
		     + "    for(int i=1; i<length; i+=3)\n"
		     + "        output[i] = b2.sample();\n"
		     + "    for(int i=2; i<length; i+=3)\n"
		     + "        output[i] = b3.sample();\n"
		     + "    output.observe(observed);\n"
		     + "}\n"
		     + "";
	}
}