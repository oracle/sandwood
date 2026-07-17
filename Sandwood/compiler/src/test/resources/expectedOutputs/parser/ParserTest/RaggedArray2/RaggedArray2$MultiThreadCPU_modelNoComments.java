package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.RaggedArray2$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.RaggedArray2.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class RaggedArray2$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var77$stateProbabilityGlobal;
		double[] cv$var80$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			{
				cv$var77$stateProbabilityGlobal = new double[2];
			}
			{
				int cv$var33$max = 2;
				cv$var33$max = Math.max(cv$var33$max, 3);
				cv$var80$stateProbabilityGlobal = new double[cv$var33$max];
			}
		}
	}


	public RaggedArray2$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample81() {
		state.y = DistributionSampling.sampleCategorical(state.RNG$, state.c, 2);
		{
			{
				{
					state.p = state.b[state.y][state.i];
				}
			}
		}
	}

	private final void drawValueSample84() {
		int lengthCV$a$82_13 = -1;
		{
			{
				if((0 == state.y))
					lengthCV$a$82_13 = 2;
			}
		}
		{
			{
				if((1 == state.y))
					lengthCV$a$82_13 = 3;
			}
		}
		state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$82_13);
		{
			{
				{
					state.p = state.b[state.y][state.i];
				}
			}
		}
	}

	private final void inferSample81() {
		if(true) {
			state.constrainedFlag$sample81 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var77$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				state.y = cv$currentValue;
				{
					{
						{
							state.p = state.b[cv$currentValue][state.i];
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < 2)) && (0 < 2)) && (0.0 <= state.c[cv$currentValue])) && (state.c[cv$currentValue] <= 1.0))?Math.log(state.c[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$y$2_1 = cv$currentValue;
								{
									{
										boolean cv$sampleConstrained = (state.fixedFlag$sample84 || state.constrainedFlag$sample84);
										if(cv$sampleConstrained) {
											state.constrainedFlag$sample81 = true;
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															{
																double[] var78 = state.a[traceTempVariable$y$2_1];
																int lengthCV$a$82_10 = -1;
																{
																	{
																		if((0 == traceTempVariable$y$2_1))
																			lengthCV$a$82_10 = 2;
																	}
																}
																{
																	{
																		if((1 == traceTempVariable$y$2_1))
																			lengthCV$a$82_10 = 3;
																	}
																}
																if(((Math.log(1.0) + ((((((0.0 <= state.i) && (state.i < lengthCV$a$82_10)) && (0 < lengthCV$a$82_10)) && (0.0 <= var78[state.i])) && (var78[state.i] <= 1.0))?Math.log(var78[state.i]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.i) && (state.i < lengthCV$a$82_10)) && (0 < lengthCV$a$82_10)) && (0.0 <= var78[state.i])) && (var78[state.i] <= 1.0))?Math.log(var78[state.i]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.i) && (state.i < lengthCV$a$82_10)) && (0 < lengthCV$a$82_10)) && (0.0 <= var78[state.i])) && (var78[state.i] <= 1.0))?Math.log(var78[state.i]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.i) && (state.i < lengthCV$a$82_10)) && (0 < lengthCV$a$82_10)) && (0.0 <= var78[state.i])) && (var78[state.i] <= 1.0))?Math.log(var78[state.i]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.i) && (state.i < lengthCV$a$82_10)) && (0 < lengthCV$a$82_10)) && (0.0 <= var78[state.i])) && (var78[state.i] <= 1.0))?Math.log(var78[state.i]):Double.NEGATIVE_INFINITY)));
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
						{
							{
								int traceTempVariable$y$7_1 = cv$currentValue;
								{
									{
										for(int var95 = 0; var95 < state.length$obs_measured; var95 += 1) {
											boolean cv$sampleConstrained = true;
											if(cv$sampleConstrained) {
												state.constrainedFlag$sample81 = true;
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													{
														{
															{
																{
																	if(((Math.log(1.0) + (((0.0 <= state.p) && (state.p <= 1.0))?Math.log((state.obs[var95]?state.p:(1.0 - state.p))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= state.p) && (state.p <= 1.0))?Math.log((state.obs[var95]?state.p:(1.0 - state.p))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= state.p) && (state.p <= 1.0))?Math.log((state.obs[var95]?state.p:(1.0 - state.p))):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= state.p) && (state.p <= 1.0))?Math.log((state.obs[var95]?state.p:(1.0 - state.p))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= state.p) && (state.p <= 1.0))?Math.log((state.obs[var95]?state.p:(1.0 - state.p))):Double.NEGATIVE_INFINITY)));
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
			if(state.constrainedFlag$sample81) {
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
				state.y = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
				{
					{
						{
							state.p = state.b[state.y][state.i];
						}
					}
				}
			}
		}
	}

	private final void inferSample84() {
		if(true) {
			state.constrainedFlag$sample84 = false;
			int cv$numStates = 0;
			{
				int lengthCV$a$82_11 = -1;
				{
					{
						if((0 == state.y))
							lengthCV$a$82_11 = 2;
					}
				}
				{
					{
						if((1 == state.y))
							lengthCV$a$82_11 = 3;
					}
				}
				cv$numStates = Math.max(cv$numStates, lengthCV$a$82_11);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var80$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				state.i = cv$currentValue;
				{
					{
						{
							state.p = state.b[state.y][cv$currentValue];
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] var78 = state.a[state.y];
					int lengthCV$a$82_12 = -1;
					{
						{
							if((0 == state.y))
								lengthCV$a$82_12 = 2;
						}
					}
					{
						{
							if((1 == state.y))
								lengthCV$a$82_12 = 3;
						}
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < lengthCV$a$82_12)) && (0 < lengthCV$a$82_12)) && (0.0 <= var78[cv$currentValue])) && (var78[cv$currentValue] <= 1.0))?Math.log(var78[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$i$6_1 = cv$currentValue;
								{
									{
										for(int var95 = 0; var95 < state.length$obs_measured; var95 += 1) {
											boolean cv$sampleConstrained = true;
											if(cv$sampleConstrained) {
												state.constrainedFlag$sample84 = true;
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													{
														{
															{
																{
																	if(((Math.log(1.0) + (((0.0 <= state.p) && (state.p <= 1.0))?Math.log((state.obs[var95]?state.p:(1.0 - state.p))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= state.p) && (state.p <= 1.0))?Math.log((state.obs[var95]?state.p:(1.0 - state.p))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= state.p) && (state.p <= 1.0))?Math.log((state.obs[var95]?state.p:(1.0 - state.p))):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= state.p) && (state.p <= 1.0))?Math.log((state.obs[var95]?state.p:(1.0 - state.p))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= state.p) && (state.p <= 1.0))?Math.log((state.obs[var95]?state.p:(1.0 - state.p))):Double.NEGATIVE_INFINITY)));
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
			if(state.constrainedFlag$sample84) {
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
				state.i = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
				{
					{
						{
							state.p = state.b[state.y][state.i];
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample100() {
		if(!state.fixedProbFlag$sample100) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var95 = 0; var95 < state.length$obs_measured; var95 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.obs[var95];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= state.p) && (state.p <= 1.0))?Math.log((cv$sampleValue?state.p:(1.0 - state.p))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$var96 = cv$sampleAccumulator;
			state.logProbability$obs = (state.logProbability$obs + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample100 = (state.fixedFlag$sample81 && state.fixedFlag$sample84);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var95 = 0; var95 < state.length$obs_measured; var95 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var96;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$obs = (state.logProbability$obs + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample81() {
		if(!state.fixedProbFlag$sample81) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = state.y;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0 < 2)) && (0.0 <= state.c[cv$sampleValue])) && (state.c[cv$sampleValue] <= 1.0))?Math.log(state.c[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$y = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample81)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample81 = state.fixedFlag$sample81;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$y;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample81)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample84() {
		if(!state.fixedProbFlag$sample84) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = state.i;
					{
						{
							double[] var78 = state.a[state.y];
							int lengthCV$a$82_14 = -1;
							{
								{
									if((0 == state.y))
										lengthCV$a$82_14 = 2;
								}
							}
							{
								{
									if((1 == state.y))
										lengthCV$a$82_14 = 3;
								}
							}
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$a$82_14)) && (0 < lengthCV$a$82_14)) && (0.0 <= var78[cv$sampleValue])) && (var78[cv$sampleValue] <= 1.0))?Math.log(var78[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$i = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample84)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample84 = (state.fixedFlag$sample84 && state.fixedFlag$sample81);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$i;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample84)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample81)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.c, 2);
		int lengthCV$a$82_15 = -1;
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample84)
						lengthCV$a$82_15 = 2;
				}
			}
		}
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample84)
						lengthCV$a$82_15 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample84)
			state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$82_15);
		if(!(state.fixedFlag$sample81 && state.fixedFlag$sample84))
			state.p = state.b[state.y][state.i];
		parallelFor(state.RNG$, 0, state.length$obs_measured, 1,
			(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
						state.obs[var95] = DistributionSampling.sampleBernoulli(RNG$1, state.p);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample81)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.c, 2);
		int lengthCV$a$82_19 = -1;
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample84)
						lengthCV$a$82_19 = 2;
				}
			}
		}
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample84)
						lengthCV$a$82_19 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample84)
			state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$82_19);
		state.p = state.b[state.y][state.i];
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample81)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.c, 2);
		int lengthCV$a$82_16 = -1;
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample84)
						lengthCV$a$82_16 = 2;
				}
			}
		}
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample84)
						lengthCV$a$82_16 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample84)
			state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$82_16);
		state.p = state.b[state.y][state.i];
		parallelFor(state.RNG$, 0, state.length$obs_measured, 1,
			(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
						state.obs[var95] = DistributionSampling.sampleBernoulli(RNG$1, state.p);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample81)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.c, 2);
		int lengthCV$a$82_17 = -1;
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample84)
						lengthCV$a$82_17 = 2;
				}
			}
		}
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample84)
						lengthCV$a$82_17 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample84)
			state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$82_17);
		if(!(state.fixedFlag$sample81 && state.fixedFlag$sample84))
			state.p = state.b[state.y][state.i];
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample81)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.c, 2);
		int lengthCV$a$82_18 = -1;
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample84)
						lengthCV$a$82_18 = 2;
				}
			}
		}
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample84)
						lengthCV$a$82_18 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample84)
			state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$82_18);
		state.p = state.b[state.y][state.i];
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample81)
				inferSample81();
			if(!state.fixedFlag$sample84)
				inferSample84();
		} else {
			if(!state.fixedFlag$sample84)
				inferSample84();
			if(!state.fixedFlag$sample81)
				inferSample81();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample81)
			drawValueSample81();
		if(!state.constrainedFlag$sample84)
			drawValueSample84();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample81)
			state.logProbability$y = Double.NaN;
		if(!state.fixedProbFlag$sample84)
			state.logProbability$i = Double.NaN;
		state.logProbability$obs = 0.0;
		if(!state.fixedProbFlag$sample100)
			state.logProbability$var96 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		double[] var5 = state.a[0];
		var5[0] = 0.4;
		var5[1] = 0.6;
		double[] var18 = state.a[1];
		var18[0] = 0.2;
		var18[1] = 0.3;
		var18[2] = 0.5;
		double[] var37 = state.b[0];
		var37[0] = 0.2;
		var37[1] = 0.8;
		double[] var50 = state.b[1];
		var50[0] = 0.4;
		var50[1] = 0.2;
		var50[2] = 0.6;
		state.c[0] = 0.35;
		state.c[1] = 0.65;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample81)
			logProbabilityValue$sample81();
		if(state.fixedFlag$sample84)
			logProbabilityValue$sample84();
		logProbabilityValue$sample100();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample81();
		logProbabilityValue$sample84();
		logProbabilityValue$sample100();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample81();
		logProbabilityValue$sample84();
		logProbabilityValue$sample100();
	}

	@Override
	public final void propagateObservedValues() {
		boolean[] cv$source1 = state.obs_measured;
		boolean[] cv$target1 = state.obs;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		state.p = state.b[state.y][state.i];
	}

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
		     + " package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model RaggedArray2(boolean[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    double[][] b = {{0.2, 0.8}, {0.4, 0.2, 0.6}};\n"
		     + "    double[] c = { 0.35, 0.65 };\n"
		     + "    int y = categorical(c).sample();\n"
		     + "    int i = categorical(a[y]).sample();\n"
		     + "    double p = b[y][i];\n"
		     + "    boolean[] obs = bernoulli(p).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}