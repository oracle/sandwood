package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Conditional4$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Conditional4.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Conditional4$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var4$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			cv$var4$stateProbabilityGlobal = new double[2];
		}
	}


	public Conditional4$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample21() {
		state.var19 = (0.0 + ((0.5 - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
		{
			{
				{
					state.bias[0] = state.var19;
				}
			}
		}
	}

	private final void drawValueSample4() {
		state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		{
			{
				if(state.guard) {
					{
						state.bias[0] = 0.5;
					}
				}
			}
		}
		{
			{
				if(!state.guard) {
					{
						state.bias[0] = state.var19;
					}
				}
			}
		}
	}

	private final void inferSample21() {
		if(true) {
			state.constrainedFlag$sample21 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = state.var19;
			double cv$originalProbability = 0.0;
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample21 || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						state.var19 = cv$proposedValue;
						{
							{
								{
									state.bias[0] = cv$currentValue;
								}
							}
						}
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < 0.5))?(-Math.log((0.5 - 0.0))):Double.NEGATIVE_INFINITY));
						{
							{
								{
									double traceTempVariable$var22$2_1 = cv$currentValue;
									if((0 == 0)) {
										{
											{
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													state.constrainedFlag$sample21 = true;
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	{
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBeta(state.value, traceTempVariable$var22$2_1, 1.0)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBeta(state.value, traceTempVariable$var22$2_1, 1.0)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(state.value, traceTempVariable$var22$2_1, 1.0));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBeta(state.value, traceTempVariable$var22$2_1, 1.0)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBeta(state.value, traceTempVariable$var22$2_1, 1.0)));
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
							state.var19 = cv$originalValue;
							{
								{
									{
										state.bias[0] = state.var19;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample4() {
		if(true) {
			state.constrainedFlag$sample4 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var4$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				state.guard = cv$currentValue;
				{
					{
						if(cv$currentValue) {
							{
								state.bias[0] = 0.5;
							}
						}
					}
				}
				{
					{
						if(!cv$currentValue) {
							{
								state.bias[0] = state.var19;
							}
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= 0.5) && (0.5 <= 1.0))?Math.log((cv$currentValue?0.5:(1.0 - 0.5))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								if(cv$currentValue) {
									{
										{
											{
												double traceTempVariable$var22$4_1 = 0.5;
												if((0 == 0)) {
													{
														{
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample4 = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				{
																					{
																						if(((Math.log(1.0) + DistributionSampling.logProbabilityBeta(state.value, traceTempVariable$var22$4_1, 1.0)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBeta(state.value, traceTempVariable$var22$4_1, 1.0)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(state.value, traceTempVariable$var22$4_1, 1.0));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBeta(state.value, traceTempVariable$var22$4_1, 1.0)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBeta(state.value, traceTempVariable$var22$4_1, 1.0)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																					}
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
					{
						{
							{
								if(!cv$currentValue) {
									{
										{
											{
												if((0 == 0)) {
													{
														{
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample4 = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				{
																					{
																						double var22 = state.bias[0];
																						if(((Math.log(1.0) + DistributionSampling.logProbabilityBeta(state.value, var22, 1.0)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBeta(state.value, var22, 1.0)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(state.value, var22, 1.0));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBeta(state.value, var22, 1.0)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBeta(state.value, var22, 1.0)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																					}
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
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															{
																{
																	{
																		if(((Math.log(1.0) + (((0.0 <= state.var19) && (state.var19 < 0.5))?(-Math.log((0.5 - 0.0))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= state.var19) && (state.var19 < 0.5))?(-Math.log((0.5 - 0.0))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= state.var19) && (state.var19 < 0.5))?(-Math.log((0.5 - 0.0))):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= state.var19) && (state.var19 < 0.5))?(-Math.log((0.5 - 0.0))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= state.var19) && (state.var19 < 0.5))?(-Math.log((0.5 - 0.0))):Double.NEGATIVE_INFINITY)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample4) {
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
				state.guard = (DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
				{
					{
						if(state.guard) {
							{
								state.bias[0] = 0.5;
							}
						}
					}
				}
				{
					{
						if(!state.guard) {
							{
								state.bias[0] = state.var19;
							}
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample21() {
		if(!state.fixedProbFlag$sample21) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			if(!state.guard) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.var19;
						{
							{
								double var16 = 0.0;
								double var17 = 0.5;
								double cv$weightedProbability = (Math.log(1.0) + (((var16 <= cv$sampleValue) && (cv$sampleValue < var17))?(-Math.log((var17 - var16))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample21 = cv$sampleProbability;
			}
			boolean cv$guard$bias = false;
			state.logProbability$var19 = (state.logProbability$var19 + cv$accumulator);
			{
				{
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample21)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample21 = (state.fixedFlag$sample21 && state.fixedFlag$sample4);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			if(!state.guard) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample21;
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			boolean cv$guard$bias = false;
			state.logProbability$var19 = (state.logProbability$var19 + cv$accumulator);
			{
				{
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample21)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample27() {
		if(!state.fixedProbFlag$sample27) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = state.value;
					{
						{
							double var22 = state.bias[0];
							double var23 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var22, var23));
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
			state.logProbability$value = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample27 = (state.fixedFlag$sample4 && state.fixedFlag$sample21);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$value;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample4() {
		if(!state.fixedProbFlag$sample4) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					boolean cv$sampleValue = state.guard;
					{
						{
							double var2 = 0.5;
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var2) && (var2 <= 1.0))?Math.log((cv$sampleValue?var2:(1.0 - var2))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$bernoulli = cv$sampleAccumulator;
			state.logProbability$guard = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample4)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample4 = state.fixedFlag$sample4;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$guard;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$bernoulli = cv$rvAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample4)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		if(state.guard)
			state.bias[0] = 0.5;
		else {
			if(!state.fixedFlag$sample21)
				state.var19 = (0.0 + ((0.5 - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			if(!(state.fixedFlag$sample4 && state.fixedFlag$sample21))
				state.bias[0] = state.var19;
		}
		state.value = DistributionSampling.sampleBeta(state.RNG$, state.bias[0], 1.0);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		if(state.guard)
			state.bias[0] = 0.5;
		else {
			if(!state.fixedFlag$sample21)
				state.var19 = (0.0 + ((0.5 - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			state.bias[0] = state.var19;
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		if(state.guard)
			state.bias[0] = 0.5;
		else {
			if(!state.fixedFlag$sample21)
				state.var19 = (0.0 + ((0.5 - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			state.bias[0] = state.var19;
		}
		state.value = DistributionSampling.sampleBeta(state.RNG$, state.bias[0], 1.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		if(state.guard)
			state.bias[0] = 0.5;
		else {
			if(!state.fixedFlag$sample21)
				state.var19 = (0.0 + ((0.5 - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			if(!(state.fixedFlag$sample4 && state.fixedFlag$sample21))
				state.bias[0] = state.var19;
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		if(state.guard)
			state.bias[0] = 0.5;
		else {
			if(!state.fixedFlag$sample21)
				state.var19 = (0.0 + ((0.5 - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			state.bias[0] = state.var19;
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample4)
				inferSample4();
			if(!state.guard) {
				if(!state.fixedFlag$sample21)
					inferSample21();
			}
		} else {
			if(!state.guard) {
				if(!state.fixedFlag$sample21)
					inferSample21();
			}
			if(!state.fixedFlag$sample4)
				inferSample4();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample4)
			drawValueSample4();
		if(!state.guard) {
			if(!state.constrainedFlag$sample21)
				drawValueSample21();
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$bernoulli = 0.0;
		if(!state.fixedProbFlag$sample4)
			state.logProbability$guard = Double.NaN;
		state.logProbability$var19 = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample21)
			state.logProbability$sample21 = Double.NaN;
		if(!state.fixedProbFlag$sample27)
			state.logProbability$value = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample4)
			logProbabilityValue$sample4();
		if(state.fixedFlag$sample21)
			logProbabilityValue$sample21();
		logProbabilityValue$sample27();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample4();
		logProbabilityValue$sample21();
		logProbabilityValue$sample27();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample4();
		logProbabilityValue$sample21();
		logProbabilityValue$sample27();
	}

	@Override
	public final void propagateObservedValues() {
		state.value = state.observedValue;
	}

	@Override
	public final void setIntermediates() {
		if(state.guard)
			state.bias[0] = 0.5;
		else
			state.bias[0] = state.var19;
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
		     + "public model Conditional4(double observedValue)  {\n"
		     + "        \n"
		     + "    //Construct a bernoulli\n"
		     + "    Bernoulli bernoulli = bernoulli(0.5);\n"
		     + "                \n"
		     + "    //Sample from it\n"
		     + "    boolean guard = bernoulli.sample();\n"
		     + "    \n"
		     + "    double[] bias = new double[1];\n"
		     + "        \n"
		     + "    if(guard)\n"
		     + "        bias[0] = 0.5;\n"
		     + "    else\n"
		     + "        bias[0] = uniform(0.0, 0.5).sample();\n"
		     + "    \n"
		     + "    double value = beta(bias[0],1.0).sample();\n"
		     + "    \n"
		     + "    //Link the sampled value to the observed value\n"
		     + "    value.observe(observedValue);\n"
		     + "}";
	}
}