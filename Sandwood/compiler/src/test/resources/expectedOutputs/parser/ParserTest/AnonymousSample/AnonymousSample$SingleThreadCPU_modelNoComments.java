package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.AnonymousSample$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.AnonymousSample.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class AnonymousSample$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public AnonymousSample$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample15() {
		state.mean1 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 2000.0);
	}

	private final void drawValueSample21() {
		state.mean2 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 2000.0);
	}

	private final void drawValueSample9() {
		state.priorSigma2 = ((Math.sqrt(900.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 10000.0);
	}

	private final void inferSample15() {
		if(true) {
			state.constrainedFlag$sample15 = false;
			double cv$sum = 0.0;
			double cv$denominatorSquareSum = 0.0;
			boolean cv$sigmaNotFound = true;
			double cv$sigmaValue = 1.0;
			{
				{
					{
						{
							for(int i = 0; i < state.n; i += 1) {
								boolean cv$sampleConstrained = true;
								if(cv$sampleConstrained) {
									state.constrainedFlag$sample15 = true;
									{
										{
											{
												{
													{
														double cv$denominator = 1.0;
														double cv$numerator = 0.0;
														cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
														cv$sum = (cv$sum + (cv$denominator * (state.amounts1[i] - cv$numerator)));
														if(cv$sigmaNotFound) {
															cv$sigmaValue = state.priorSigma2;
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
			if(state.constrainedFlag$sample15)
				state.mean1 = Conjugates.sampleConjugateGaussianGaussian(state.RNG$, 2000.0, 10000.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
		}
	}

	private final void inferSample21() {
		if(true) {
			state.constrainedFlag$sample21 = false;
			double cv$sum = 0.0;
			double cv$denominatorSquareSum = 0.0;
			boolean cv$sigmaNotFound = true;
			double cv$sigmaValue = 1.0;
			{
				{
					{
						{
							for(int i = 0; i < state.n; i += 1) {
								boolean cv$sampleConstrained = true;
								if(cv$sampleConstrained) {
									state.constrainedFlag$sample21 = true;
									{
										{
											{
												{
													{
														double cv$denominator = 1.0;
														double cv$numerator = 0.0;
														cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
														cv$sum = (cv$sum + (cv$denominator * (state.var39[((i - 0) / 1)] - cv$numerator)));
														if(cv$sigmaNotFound) {
															cv$sigmaValue = state.priorSigma2;
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
			if(state.constrainedFlag$sample21)
				state.mean2 = Conjugates.sampleConjugateGaussianGaussian(state.RNG$, 2000.0, 10000.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
		}
	}

	private final void inferSample9() {
		if(true) {
			state.constrainedFlag$sample9 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = state.priorSigma2;
			double cv$originalProbability = 0.0;
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
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
						state.priorSigma2 = cv$proposedValue;
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + ((0.0 < 900.0)?(DistributionSampling.logProbabilityGaussian(((cv$currentValue - 10000.0) / Math.sqrt(900.0))) - (0.5 * Math.log(900.0))):Double.NEGATIVE_INFINITY));
						{
							{
								{
									for(int i = 0; i < state.n; i += 1) {
										double traceTempVariable$priorSigma2$1_2 = cv$currentValue;
										{
											{
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
																		if(((Math.log(1.0) + ((0.0 < traceTempVariable$priorSigma2$1_2)?(DistributionSampling.logProbabilityGaussian(((state.amounts1[i] - state.mean1) / Math.sqrt(traceTempVariable$priorSigma2$1_2))) - (0.5 * Math.log(traceTempVariable$priorSigma2$1_2))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$priorSigma2$1_2)?(DistributionSampling.logProbabilityGaussian(((state.amounts1[i] - state.mean1) / Math.sqrt(traceTempVariable$priorSigma2$1_2))) - (0.5 * Math.log(traceTempVariable$priorSigma2$1_2))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$priorSigma2$1_2)?(DistributionSampling.logProbabilityGaussian(((state.amounts1[i] - state.mean1) / Math.sqrt(traceTempVariable$priorSigma2$1_2))) - (0.5 * Math.log(traceTempVariable$priorSigma2$1_2))):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$priorSigma2$1_2)?(DistributionSampling.logProbabilityGaussian(((state.amounts1[i] - state.mean1) / Math.sqrt(traceTempVariable$priorSigma2$1_2))) - (0.5 * Math.log(traceTempVariable$priorSigma2$1_2))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$priorSigma2$1_2)?(DistributionSampling.logProbabilityGaussian(((state.amounts1[i] - state.mean1) / Math.sqrt(traceTempVariable$priorSigma2$1_2))) - (0.5 * Math.log(traceTempVariable$priorSigma2$1_2))):Double.NEGATIVE_INFINITY)));
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
									for(int i = 0; i < state.n; i += 1) {
										double traceTempVariable$priorSigma2$4_2 = cv$currentValue;
										{
											{
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
																		if(((Math.log(1.0) + ((0.0 < traceTempVariable$priorSigma2$4_2)?(DistributionSampling.logProbabilityGaussian(((state.var39[((i - 0) / 1)] - state.mean2) / Math.sqrt(traceTempVariable$priorSigma2$4_2))) - (0.5 * Math.log(traceTempVariable$priorSigma2$4_2))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$priorSigma2$4_2)?(DistributionSampling.logProbabilityGaussian(((state.var39[((i - 0) / 1)] - state.mean2) / Math.sqrt(traceTempVariable$priorSigma2$4_2))) - (0.5 * Math.log(traceTempVariable$priorSigma2$4_2))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$priorSigma2$4_2)?(DistributionSampling.logProbabilityGaussian(((state.var39[((i - 0) / 1)] - state.mean2) / Math.sqrt(traceTempVariable$priorSigma2$4_2))) - (0.5 * Math.log(traceTempVariable$priorSigma2$4_2))):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$priorSigma2$4_2)?(DistributionSampling.logProbabilityGaussian(((state.var39[((i - 0) / 1)] - state.mean2) / Math.sqrt(traceTempVariable$priorSigma2$4_2))) - (0.5 * Math.log(traceTempVariable$priorSigma2$4_2))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$priorSigma2$4_2)?(DistributionSampling.logProbabilityGaussian(((state.var39[((i - 0) / 1)] - state.mean2) / Math.sqrt(traceTempVariable$priorSigma2$4_2))) - (0.5 * Math.log(traceTempVariable$priorSigma2$4_2))):Double.NEGATIVE_INFINITY)));
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
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$))))) || Double.isNaN(cv$ratio)))
							state.priorSigma2 = cv$originalValue;
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample15() {
		if(!state.fixedProbFlag$sample15) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = state.mean1;
					{
						{
							double var12 = 2000.0;
							double var13 = 10000.0;
							double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var13)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var12) / Math.sqrt(var13))) - (0.5 * Math.log(var13))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$mean1 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample15)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample15 = state.fixedFlag$sample15;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$mean1;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample15)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample21() {
		if(!state.fixedProbFlag$sample21) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = state.mean2;
					{
						{
							double var18 = 2000.0;
							double var19 = 10000.0;
							double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var19)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var18) / Math.sqrt(var19))) - (0.5 * Math.log(var19))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$mean2 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample21)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample21 = state.fixedFlag$sample21;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$mean2;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample21)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!state.fixedProbFlag$sample35) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < state.n; i += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.amounts1[i];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < state.priorSigma2)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.mean1) / Math.sqrt(state.priorSigma2))) - (0.5 * Math.log(state.priorSigma2))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample35[((i - 0) / 1)] = cv$sampleProbability;
			}
			state.logProbability$amounts1 = (state.logProbability$amounts1 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample35 = (state.fixedFlag$sample9 && state.fixedFlag$sample15);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < state.n; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample35[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			state.logProbability$amounts1 = (state.logProbability$amounts1 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample39() {
		if(!state.fixedProbFlag$sample39) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < state.n; i += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.var39[((i - 0) / 1)];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < state.priorSigma2)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.mean2) / Math.sqrt(state.priorSigma2))) - (0.5 * Math.log(state.priorSigma2))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample39[((i - 0) / 1)] = cv$sampleProbability;
			}
			boolean cv$guard$amounts2 = false;
			state.logProbability$var39 = (state.logProbability$var39 + cv$accumulator);
			{
				{
					if(!cv$guard$amounts2) {
						cv$guard$amounts2 = true;
						state.logProbability$amounts2 = (state.logProbability$amounts2 + cv$accumulator);
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample39 = (state.fixedFlag$sample9 && state.fixedFlag$sample21);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < state.n; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample39[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			boolean cv$guard$amounts2 = false;
			state.logProbability$var39 = (state.logProbability$var39 + cv$accumulator);
			{
				{
					if(!cv$guard$amounts2) {
						cv$guard$amounts2 = true;
						state.logProbability$amounts2 = (state.logProbability$amounts2 + cv$accumulator);
					}
				}
			}
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
					double cv$sampleValue = state.priorSigma2;
					{
						{
							double var6 = 10000.0;
							double var7 = 900.0;
							double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var7)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var6) / Math.sqrt(var7))) - (0.5 * Math.log(var7))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$priorSigma2 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample9)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample9 = state.fixedFlag$sample9;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$priorSigma2;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample9)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample9)
			state.priorSigma2 = ((Math.sqrt(900.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 10000.0);
		if(!state.fixedFlag$sample15)
			state.mean1 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 2000.0);
		if(!state.fixedFlag$sample21)
			state.mean2 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 2000.0);
		for(int i = 0; i < state.n; i += 1) {
			state.amounts1[i] = ((Math.sqrt(state.priorSigma2) * DistributionSampling.sampleGaussian(state.RNG$)) + state.mean1);
			state.var39[((i - 0) / 1)] = ((Math.sqrt(state.priorSigma2) * DistributionSampling.sampleGaussian(state.RNG$)) + state.mean2);
			state.amounts2[i] = (state.amounts1[i] + state.var39[((i - 0) / 1)]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample9)
			state.priorSigma2 = ((Math.sqrt(900.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 10000.0);
		if(!state.fixedFlag$sample15)
			state.mean1 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 2000.0);
		if(!state.fixedFlag$sample21)
			state.mean2 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 2000.0);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample9)
			state.priorSigma2 = ((Math.sqrt(900.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 10000.0);
		if(!state.fixedFlag$sample15)
			state.mean1 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 2000.0);
		if(!state.fixedFlag$sample21)
			state.mean2 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 2000.0);
		for(int i = 0; i < state.n; i += 1) {
			state.amounts1[i] = ((Math.sqrt(state.priorSigma2) * DistributionSampling.sampleGaussian(state.RNG$)) + state.mean1);
			state.var39[((i - 0) / 1)] = ((Math.sqrt(state.priorSigma2) * DistributionSampling.sampleGaussian(state.RNG$)) + state.mean2);
			state.amounts2[i] = (state.amounts1[i] + state.var39[((i - 0) / 1)]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample9)
			state.priorSigma2 = ((Math.sqrt(900.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 10000.0);
		if(!state.fixedFlag$sample15)
			state.mean1 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 2000.0);
		if(!state.fixedFlag$sample21)
			state.mean2 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 2000.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample9)
			state.priorSigma2 = ((Math.sqrt(900.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 10000.0);
		if(!state.fixedFlag$sample15)
			state.mean1 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 2000.0);
		if(!state.fixedFlag$sample21)
			state.mean2 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 2000.0);
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample9)
				inferSample9();
			if(!state.fixedFlag$sample15)
				inferSample15();
			if(!state.fixedFlag$sample21)
				inferSample21();
		} else {
			if(!state.fixedFlag$sample21)
				inferSample21();
			if(!state.fixedFlag$sample15)
				inferSample15();
			if(!state.fixedFlag$sample9)
				inferSample9();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample9)
			drawValueSample9();
		if(!state.constrainedFlag$sample15)
			drawValueSample15();
		if(!state.constrainedFlag$sample21)
			drawValueSample21();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample9)
			state.logProbability$priorSigma2 = Double.NaN;
		if(!state.fixedProbFlag$sample15)
			state.logProbability$mean1 = Double.NaN;
		if(!state.fixedProbFlag$sample21)
			state.logProbability$mean2 = Double.NaN;
		state.logProbability$amounts1 = 0.0;
		if(!state.fixedProbFlag$sample35) {
			for(int i = 0; i < state.n; i += 1)
				state.logProbability$sample35[((i - 0) / 1)] = Double.NaN;
		}
		state.logProbability$var39 = 0.0;
		state.logProbability$amounts2 = 0.0;
		if(!state.fixedProbFlag$sample39) {
			for(int i = 0; i < state.n; i += 1)
				state.logProbability$sample39[((i - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.n = state.length$obsAmounts1;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample9)
			logProbabilityValue$sample9();
		if(state.fixedFlag$sample15)
			logProbabilityValue$sample15();
		if(state.fixedFlag$sample21)
			logProbabilityValue$sample21();
		logProbabilityValue$sample35();
		logProbabilityValue$sample39();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample15();
		logProbabilityValue$sample21();
		logProbabilityValue$sample35();
		logProbabilityValue$sample39();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample15();
		logProbabilityValue$sample21();
		logProbabilityValue$sample35();
		logProbabilityValue$sample39();
	}

	@Override
	public final void propagateObservedValues() {
		{
			double[] cv$source1 = state.obsAmounts1;
			double[] cv$target1 = state.amounts1;
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				cv$target1[cv$index1] = cv$source1[cv$index1];
		}
		{
			double[] cv$source1 = state.obsAmounts2;
			double[] cv$target1 = state.amounts2;
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				cv$target1[cv$index1] = cv$source1[cv$index1];
		}
		for(int i = (state.n - ((((state.n - 1) - 0) % 1) + 1)); i >= ((0 - 1) + 1); i -= 1)
			state.var39[((i - 0) / 1)] = (state.amounts2[i] - state.amounts1[i]);
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model AnonymousSample(double[] obsAmounts1, double[] obsAmounts2) {\n"
		     + "    int n = obsAmounts1.length;\n"
		     + "\n"
		     + "    double priorSigma2 = gaussian(10000, 900).sample();   // can always use inverseGamma(1.5, 100)\n"
		     + "\n"
		     + "    double mean1 = gaussian(2000, 10000).sample();\n"
		     + "    double mean2 = gaussian(2000, 10000).sample();\n"
		     + "\n"
		     + "\n"
		     + "    double[] amounts1 = new double[n];\n"
		     + "    double[] amounts2 = new double[n];\n"
		     + "    for(int i : [0..n)) {\n"
		     + "        amounts1[i] = gaussian(mean1, priorSigma2).sample();\n"
		     + "        amounts2[i] = amounts1[i] + gaussian(mean2, priorSigma2).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    amounts1.observe(obsAmounts1);\n"
		     + "    amounts2.observe(obsAmounts2);\n"
		     + "}";
	}
}