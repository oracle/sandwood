package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.GaussianMixtureTest$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.GaussianMixtureTest.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class GaussianMixtureTest$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var17$countGlobal;
		double[][] cv$var68$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			{
				cv$var17$countGlobal = new double[5];
			}
			{
				{
					int cv$threadCount = threadCount();
					cv$var68$stateProbabilityGlobal = new double[cv$threadCount][];
					for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
						cv$var68$stateProbabilityGlobal[cv$index] = new double[5];
				}
			}
		}
	}


	public GaussianMixtureTest$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample17() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.k, state.phi);
	}

	private final void drawValueSample34(int var33, int threadID$cv$var33, Rng RNG$) {
		state.mu[var33] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
	}

	private final void drawValueSample52(int var51, int threadID$cv$var51, Rng RNG$) {
		state.sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
	}

	private final void drawValueSample68(int i$var66, int threadID$cv$i$var66, Rng RNG$) {
		state.z[((i$var66 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, state.phi, state.k);
	}

	private final void inferSample17() {
		if(true) {
			state.constrainedFlag$sample17 = false;
			double[] cv$targetLocal = state.phi;
			double[] cv$countLocal = scratch.cv$var17$countGlobal;
			int cv$arrayLength = state.k;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1) {
								boolean cv$sampleConstrained = state.constrainedFlag$sample68[((i$var66 - 0) / 1)];
								if(cv$sampleConstrained) {
									state.constrainedFlag$sample17 = true;
									{
										{
											{
												{
													{
														cv$countLocal[state.z[((i$var66 - 0) / 1)]] = (cv$countLocal[state.z[((i$var66 - 0) / 1)]] + 1.0);
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
				Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.alpha, cv$countLocal, cv$targetLocal, state.k);
		}
	}

	private final void inferSample34(int var33, int threadID$cv$var33, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample34[((var33 - 0) / 1)] = false;
			double cv$sum = 0.0;
			double cv$denominatorSquareSum = 0.0;
			boolean cv$sigmaNotFound = true;
			double cv$sigmaValue = 1.0;
			{
				{
					{
						{
							for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1) {
								if((var33 == state.z[((i$var66 - 0) / 1)])) {
									{
										{
											boolean cv$sampleConstrained = true;
											if(cv$sampleConstrained) {
												state.constrainedFlag$sample34[((var33 - 0) / 1)] = true;
												{
													{
														{
															{
																{
																	double cv$denominator = 1.0;
																	double cv$numerator = 0.0;
																	cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
																	cv$sum = (cv$sum + (cv$denominator * (state.x[i$var66] - cv$numerator)));
																	if(cv$sigmaNotFound) {
																		cv$sigmaValue = state.sigma[state.z[((i$var66 - 0) / 1)]];
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
			}
			if(state.constrainedFlag$sample34[((var33 - 0) / 1)]) {
				double var34 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 20.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
				{
					{
						{
							state.mu[var33] = var34;
						}
					}
				}
			}
		}
	}

	private final void inferSample52(int var51, int threadID$cv$var51, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample52[((var51 - 0) / 1)] = false;
			double cv$sum = 0.0;
			int cv$count = 0;
			{
				{
					{
						{
							for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1) {
								if((var51 == state.z[((i$var66 - 0) / 1)])) {
									{
										{
											boolean cv$sampleConstrained = true;
											if(cv$sampleConstrained) {
												state.constrainedFlag$sample52[((var51 - 0) / 1)] = true;
												{
													{
														{
															{
																{
																	double cv$var71$mu = state.mu[state.z[((i$var66 - 0) / 1)]];
																	double cv$var71$diff = (cv$var71$mu - state.x[i$var66]);
																	cv$sum = (cv$sum + (cv$var71$diff * cv$var71$diff));
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
			}
			if(state.constrainedFlag$sample52[((var51 - 0) / 1)]) {
				double var52 = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 1.0, 1.0, cv$sum, cv$count);
				{
					{
						{
							state.sigma[var51] = var52;
						}
					}
				}
			}
		}
	}

	private final void inferSample68(int i$var66, int threadID$cv$i$var66, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample68[((i$var66 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, state.k);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var68$stateProbabilityGlobal[threadID$cv$i$var66];
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				state.z[((i$var66 - 0) / 1)] = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.k)) && (0 < state.k)) && (0.0 <= state.phi[cv$currentValue])) && (state.phi[cv$currentValue] <= 1.0))?Math.log(state.phi[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							boolean guard$sample68gaussian71 = false;
							{
								if(!guard$sample68gaussian71) {
									guard$sample68gaussian71 = true;
									{
										{
											boolean cv$sampleConstrained = true;
											if(cv$sampleConstrained) {
												state.constrainedFlag$sample68[((i$var66 - 0) / 1)] = true;
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													{
														{
															{
																{
																	double var69 = state.mu[cv$currentValue];
																	double var70 = state.sigma[cv$currentValue];
																	if(((Math.log(1.0) + ((0.0 < var70)?(DistributionSampling.logProbabilityGaussian(((state.x[i$var66] - var69) / Math.sqrt(var70))) - (0.5 * Math.log(var70))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var70)?(DistributionSampling.logProbabilityGaussian(((state.x[i$var66] - var69) / Math.sqrt(var70))) - (0.5 * Math.log(var70))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var70)?(DistributionSampling.logProbabilityGaussian(((state.x[i$var66] - var69) / Math.sqrt(var70))) - (0.5 * Math.log(var70))):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var70)?(DistributionSampling.logProbabilityGaussian(((state.x[i$var66] - var69) / Math.sqrt(var70))) - (0.5 * Math.log(var70))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var70)?(DistributionSampling.logProbabilityGaussian(((state.x[i$var66] - var69) / Math.sqrt(var70))) - (0.5 * Math.log(var70))):Double.NEGATIVE_INFINITY)));
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
							{
								if(!guard$sample68gaussian71) {
									guard$sample68gaussian71 = true;
									{
										{
											boolean cv$sampleConstrained = true;
											if(cv$sampleConstrained) {
												state.constrainedFlag$sample68[((i$var66 - 0) / 1)] = true;
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													{
														{
															{
																{
																	double var69 = state.mu[cv$currentValue];
																	double var70 = state.sigma[cv$currentValue];
																	if(((Math.log(1.0) + ((0.0 < var70)?(DistributionSampling.logProbabilityGaussian(((state.x[i$var66] - var69) / Math.sqrt(var70))) - (0.5 * Math.log(var70))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var70)?(DistributionSampling.logProbabilityGaussian(((state.x[i$var66] - var69) / Math.sqrt(var70))) - (0.5 * Math.log(var70))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var70)?(DistributionSampling.logProbabilityGaussian(((state.x[i$var66] - var69) / Math.sqrt(var70))) - (0.5 * Math.log(var70))):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var70)?(DistributionSampling.logProbabilityGaussian(((state.x[i$var66] - var69) / Math.sqrt(var70))) - (0.5 * Math.log(var70))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var70)?(DistributionSampling.logProbabilityGaussian(((state.x[i$var66] - var69) / Math.sqrt(var70))) - (0.5 * Math.log(var70))):Double.NEGATIVE_INFINITY)));
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
			if(state.constrainedFlag$sample68[((i$var66 - 0) / 1)]) {
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
				state.z[((i$var66 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates);
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
					double[] cv$sampleValue = state.phi;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, state.alpha, state.k));
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
			state.logProbability$phi = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample17)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample17 = state.fixedFlag$sample17;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$phi;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample17)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample34() {
		if(!state.fixedProbFlag$sample34) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var33 = 0; var33 < state.k; var33 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.mu[var33];
						{
							{
								double var20 = 0.0;
								double var21 = 20.0;
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
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			state.logProbability$var34 = cv$sampleAccumulator;
			state.logProbability$mu = (state.logProbability$mu + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample34)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample34 = state.fixedFlag$sample34;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var33 = 0; var33 < state.k; var33 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var34;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$mu = (state.logProbability$mu + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample34)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample52() {
		if(!state.fixedProbFlag$sample52) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var51 = 0; var51 < state.k; var51 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.sigma[var51];
						{
							{
								double var38 = 1.0;
								double var39 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var38, var39));
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
			state.logProbability$var52 = cv$sampleAccumulator;
			state.logProbability$sigma = (state.logProbability$sigma + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample52)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample52 = state.fixedFlag$sample52;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var51 = 0; var51 < state.k; var51 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var52;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$sigma = (state.logProbability$sigma + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample52)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample68() {
		double cv$accumulator = 0.0;
		boolean cv$sampleReached = false;
		for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1) {
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = state.z[((i$var66 - 0) / 1)];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.k)) && (0 < state.k)) && (0.0 <= state.phi[cv$sampleValue])) && (state.phi[cv$sampleValue] <= 1.0))?Math.log(state.phi[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$sample68[((i$var66 - 0) / 1)] = cv$sampleProbability;
		}
		state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
	}

	private final void logProbabilityValue$sample72() {
		double cv$accumulator = 0.0;
		boolean cv$sampleReached = false;
		for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1) {
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = state.x[i$var66];
					{
						{
							double var69 = state.mu[state.z[((i$var66 - 0) / 1)]];
							double var70 = state.sigma[state.z[((i$var66 - 0) / 1)]];
							double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var70)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var69) / Math.sqrt(var70))) - (0.5 * Math.log(var70))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$sample72[((i$var66 - 0) / 1)] = cv$sampleProbability;
		}
		state.logProbability$x = (state.logProbability$x + cv$accumulator);
		state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
		state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.k, state.phi);
		parallelFor(state.RNG$, 0, state.k, 1,
			(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
						if(!state.fixedFlag$sample34)
							state.mu[var33] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.k, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1) {
						if(!state.fixedFlag$sample52)
							state.sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.length$xMeasured, 1,
			(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1) {
						state.z[((i$var66 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$1, state.phi, state.k);
						state.x[i$var66] = ((Math.sqrt(state.sigma[state.z[((i$var66 - 0) / 1)]]) * DistributionSampling.sampleGaussian(RNG$1)) + state.mu[state.z[((i$var66 - 0) / 1)]]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.k, state.phi);
		parallelFor(state.RNG$, 0, state.k, 1,
			(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
						if(!state.fixedFlag$sample34)
							state.mu[var33] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.k, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1) {
						if(!state.fixedFlag$sample52)
							state.sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.length$xMeasured, 1,
			(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1)
						state.z[((i$var66 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$1, state.phi, state.k);
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.k, state.phi);
		parallelFor(state.RNG$, 0, state.k, 1,
			(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
						if(!state.fixedFlag$sample34)
							state.mu[var33] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.k, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1) {
						if(!state.fixedFlag$sample52)
							state.sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.length$xMeasured, 1,
			(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1) {
						state.z[((i$var66 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$1, state.phi, state.k);
						state.x[i$var66] = ((Math.sqrt(state.sigma[state.z[((i$var66 - 0) / 1)]]) * DistributionSampling.sampleGaussian(RNG$1)) + state.mu[state.z[((i$var66 - 0) / 1)]]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.k, state.phi);
		parallelFor(state.RNG$, 0, state.k, 1,
			(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
						if(!state.fixedFlag$sample34)
							state.mu[var33] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.k, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1) {
						if(!state.fixedFlag$sample52)
							state.sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.length$xMeasured, 1,
			(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1)
						state.z[((i$var66 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$1, state.phi, state.k);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.k, state.phi);
		parallelFor(state.RNG$, 0, state.k, 1,
			(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
						if(!state.fixedFlag$sample34)
							state.mu[var33] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.k, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1) {
						if(!state.fixedFlag$sample52)
							state.sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.length$xMeasured, 1,
			(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1)
						state.z[((i$var66 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$1, state.phi, state.k);
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample17)
				inferSample17();
			parallelFor(state.RNG$, 0, state.k, 1,
				(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
							if(!state.fixedFlag$sample34)
								inferSample34(var33, threadID$var33, RNG$1);
						}
				}
			);
			parallelFor(state.RNG$, 0, state.k, 1,
				(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1) {
							if(!state.fixedFlag$sample52)
								inferSample52(var51, threadID$var51, RNG$1);
						}
				}
			);
			parallelFor(state.RNG$, 0, state.length$xMeasured, 1,
				(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1)
							inferSample68(i$var66, threadID$i$var66, RNG$1);
				}
			);
		} else {
			parallelFor(state.RNG$, 0, state.length$xMeasured, 1,
				(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1)
							inferSample68(i$var66, threadID$i$var66, RNG$1);
				}
			);
			parallelFor(state.RNG$, 0, state.k, 1,
				(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1) {
							if(!state.fixedFlag$sample52)
								inferSample52(var51, threadID$var51, RNG$1);
						}
				}
			);
			parallelFor(state.RNG$, 0, state.k, 1,
				(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
							if(!state.fixedFlag$sample34)
								inferSample34(var33, threadID$var33, RNG$1);
						}
				}
			);
			if(!state.fixedFlag$sample17)
				inferSample17();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample17)
			drawValueSample17();
		parallelFor(state.RNG$, 0, state.k, 1,
			(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
						if(!state.constrainedFlag$sample34[((var33 - 0) / 1)])
							drawValueSample34(var33, threadID$var33, RNG$1);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.k, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1) {
						if(!state.constrainedFlag$sample52[((var51 - 0) / 1)])
							drawValueSample52(var51, threadID$var51, RNG$1);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.length$xMeasured, 1,
			(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1) {
						if(!state.constrainedFlag$sample68[((i$var66 - 0) / 1)])
							drawValueSample68(i$var66, threadID$i$var66, RNG$1);
					}
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample17)
			state.logProbability$phi = Double.NaN;
		state.logProbability$mu = 0.0;
		if(!state.fixedProbFlag$sample34)
			state.logProbability$var34 = Double.NaN;
		state.logProbability$sigma = 0.0;
		if(!state.fixedProbFlag$sample52)
			state.logProbability$var52 = Double.NaN;
		for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1)
			state.logProbability$sample68[((i$var66 - 0) / 1)] = Double.NaN;
		state.logProbability$x = 0.0;
		for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1)
			state.logProbability$sample72[((i$var66 - 0) / 1)] = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.k = 5;
		for(int i$var13 = 0; i$var13 < 5; i$var13 += 1)
			state.alpha[i$var13] = 1.0;
		for(int index$constrainedFlag$sample52$1 = 0; index$constrainedFlag$sample52$1 < state.constrainedFlag$sample52.length; index$constrainedFlag$sample52$1 += 1)
			state.constrainedFlag$sample52[index$constrainedFlag$sample52$1] = true;
		for(int index$constrainedFlag$sample68$1 = 0; index$constrainedFlag$sample68$1 < state.constrainedFlag$sample68.length; index$constrainedFlag$sample68$1 += 1)
			state.constrainedFlag$sample68[index$constrainedFlag$sample68$1] = true;
		for(int index$constrainedFlag$sample34$1 = 0; index$constrainedFlag$sample34$1 < state.constrainedFlag$sample34.length; index$constrainedFlag$sample34$1 += 1)
			state.constrainedFlag$sample34[index$constrainedFlag$sample34$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample17)
			logProbabilityValue$sample17();
		if(state.fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(state.fixedFlag$sample52)
			logProbabilityValue$sample52();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample34();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample34();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample72();
	}

	@Override
	public final void propagateObservedValues() {
		double[] cv$source1 = state.xMeasured;
		double[] cv$target1 = state.x;
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
		     + " * Copyright (c) 2019-2023, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model GaussianMixtureTest(double[] xMeasured) {\n"
		     + "\n"
		     + "        int k = 5;\n"
		     + "\n"
		     + "        double[] alpha = new double[k];\n"
		     + "        for(int i:[0..k)) \n"
		     + "            alpha[i] = 1.0;\n"
		     + "        \n"
		     + "        double[] phi = dirichlet(alpha).sample();\n"
		     + "        double[] mu = gaussian(0, 20).sample(k);\n"
		     + "        double[] sigma = inverseGamma(1, 1).sample(k);\n"
		     + "        \n"
		     + "        double[] x = new double[xMeasured.length];\n"
		     + "        for(int i:[0..xMeasured.length)) {\n"
		     + "            int z = categorical(phi).sample();\n"
		     + "            x[i] = gaussian(mu[z], sigma[z]).sample();\n"
		     + "        }\n"
		     + "        \n"
		     + "        x.observe(xMeasured);\n"
		     + "}\n"
		     + "";
	}
}