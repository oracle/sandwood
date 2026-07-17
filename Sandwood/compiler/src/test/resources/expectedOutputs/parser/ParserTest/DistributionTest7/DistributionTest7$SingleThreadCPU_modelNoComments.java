package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.DistributionTest7$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.DistributionTest7.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DistributionTest7$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var31$stateProbabilityGlobal;
		double[] cv$var43$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			{
				cv$var31$stateProbabilityGlobal = new double[3];
			}
			{
				cv$var43$stateProbabilityGlobal = new double[(10 + 1)];
			}
		}
	}


	public DistributionTest7$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample31() {
		state.cat = DistributionSampling.sampleCategorical(state.RNG$, state.prob, 3);
		{
			{
				{
					if(!(state.cat == 1))
						state.result = state.var43;
					else
						state.result = 5;
				}
			}
		}
	}

	private final void drawValueSample45() {
		state.var43 = DistributionSampling.sampleBinomial(state.RNG$, state.bias[state.cat], 10);
		{
			{
				if(!(state.cat == 1)) {
					{
						state.result = state.var43;
					}
				}
			}
		}
	}

	private final void inferSample31() {
		if(true) {
			state.constrainedFlag$sample31 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 3);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var31$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < 3)) && (0 < 3)) && (0.0 <= state.prob[cv$currentValue])) && (state.prob[cv$currentValue] <= 1.0))?Math.log(state.prob[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								if(!(cv$currentValue == 1)) {
									int traceTempVariable$cat$1_1 = cv$currentValue;
									{
										{
											boolean cv$sampleConstrained = (state.fixedFlag$sample45 || state.constrainedFlag$sample45);
											if(cv$sampleConstrained) {
												state.constrainedFlag$sample31 = true;
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													{
														double traceTempVariable$var40$3_1 = 0.2;
														if((0 == traceTempVariable$cat$1_1)) {
															{
																{
																	{
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, traceTempVariable$var40$3_1, 10)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, traceTempVariable$var40$3_1, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, traceTempVariable$var40$3_1, 10));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, traceTempVariable$var40$3_1, 10)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, traceTempVariable$var40$3_1, 10)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													}
													{
														double traceTempVariable$var40$4_1 = 0.3;
														if((1 == traceTempVariable$cat$1_1)) {
															{
																{
																	{
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, traceTempVariable$var40$4_1, 10)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, traceTempVariable$var40$4_1, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, traceTempVariable$var40$4_1, 10));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, traceTempVariable$var40$4_1, 10)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, traceTempVariable$var40$4_1, 10)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													}
													{
														double traceTempVariable$var40$5_1 = 0.5;
														if((2 == traceTempVariable$cat$1_1)) {
															{
																{
																	{
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, traceTempVariable$var40$5_1, 10)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, traceTempVariable$var40$5_1, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, traceTempVariable$var40$5_1, 10));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, traceTempVariable$var40$5_1, 10)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, traceTempVariable$var40$5_1, 10)));
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
					{
						{
							{
								int traceTempVariable$cat$9_1 = cv$currentValue;
								{
									{
										boolean cv$sampleConstrained = true;
										if(cv$sampleConstrained) {
											state.constrainedFlag$sample31 = true;
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													int traceTempVariable$cat$11_1 = cv$currentValue;
													{
														if(!(traceTempVariable$cat$11_1 == 1)) {
															int traceTempVariable$result$16_1 = state.var43;
															{
																{
																	{
																		double var47 = (double)traceTempVariable$result$16_1;
																		double var46 = (double)traceTempVariable$cat$11_1;
																		if(((Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													}
												}
												if(!true) {
													for(int index$sample31$12 = 0; index$sample31$12 < 3; index$sample31$12 += 1) {
														int distributionTempVariable$cat$14 = index$sample31$12;
														double cv$probabilitySample31Value13 = (1.0 * state.distribution$sample31[index$sample31$12]);
														{
															int traceTempVariable$cat$15_1 = distributionTempVariable$cat$14;
															{
																if(!(traceTempVariable$cat$15_1 == 1)) {
																	int traceTempVariable$result$17_1 = state.var43;
																	{
																		{
																			{
																				double var47 = (double)traceTempVariable$result$17_1;
																				double var46 = (double)traceTempVariable$cat$15_1;
																				if(((Math.log(cv$probabilitySample31Value13) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value13) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value13) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value13) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample31Value13) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value13);
																			}
																		}
																	}
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
									{
										{
											int traceTempVariable$cat$21_1 = cv$currentValue;
											{
												if((traceTempVariable$cat$21_1 == 1)) {
													int traceTempVariable$result$26_1 = 5;
													{
														{
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample31 = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			if(!(traceTempVariable$cat$21_1 == 1)) {
																				int traceTempVariable$result$30_1 = state.var43;
																				{
																					int traceTempVariable$cat$31_1 = cv$currentValue;
																					{
																						{
																							{
																								double var47 = (double)traceTempVariable$result$30_1;
																								double var46 = (double)traceTempVariable$cat$31_1;
																								if(((Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample31$32 = 0; index$sample31$32 < 3; index$sample31$32 += 1) {
																						int distributionTempVariable$cat$34 = index$sample31$32;
																						double cv$probabilitySample31Value33 = (1.0 * state.distribution$sample31[index$sample31$32]);
																						{
																							int traceTempVariable$cat$35_1 = distributionTempVariable$cat$34;
																							{
																								{
																									{
																										double var47 = (double)traceTempVariable$result$30_1;
																										double var46 = (double)traceTempVariable$cat$35_1;
																										if(((Math.log(cv$probabilitySample31Value33) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value33) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value33) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value33) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample31Value33) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value33);
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
										if(!true) {
											for(int index$sample31$22 = 0; index$sample31$22 < 3; index$sample31$22 += 1) {
												int distributionTempVariable$cat$24 = index$sample31$22;
												double cv$probabilitySample31Value23 = (1.0 * state.distribution$sample31[index$sample31$22]);
												{
													int traceTempVariable$cat$25_1 = distributionTempVariable$cat$24;
													{
														if((traceTempVariable$cat$25_1 == 1)) {
															int traceTempVariable$result$27_1 = 5;
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample31 = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					if(!(traceTempVariable$cat$25_1 == 1)) {
																						int traceTempVariable$result$36_1 = state.var43;
																						{
																							int traceTempVariable$cat$37_1 = cv$currentValue;
																							{
																								{
																									{
																										double var47 = (double)traceTempVariable$result$36_1;
																										double var46 = (double)traceTempVariable$cat$37_1;
																										if(((Math.log(cv$probabilitySample31Value23) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value23) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value23) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value23) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample31Value23) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value23);
																									}
																								}
																							}
																						}
																						{
																							int traceTempVariable$cat$38_1 = distributionTempVariable$cat$24;
																							{
																								{
																									{
																										double var47 = (double)traceTempVariable$result$36_1;
																										double var46 = (double)traceTempVariable$cat$38_1;
																										if(((Math.log(cv$probabilitySample31Value23) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value23) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value23) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value23) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample31Value23) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value23);
																									}
																								}
																							}
																						}
																						if(!true) {
																							for(int index$sample31$39 = 0; index$sample31$39 < 3; index$sample31$39 += 1) {
																								int distributionTempVariable$cat$41 = index$sample31$39;
																								double cv$probabilitySample31Value40 = (cv$probabilitySample31Value23 * state.distribution$sample31[index$sample31$39]);
																								{
																									int traceTempVariable$cat$42_1 = distributionTempVariable$cat$41;
																									{
																										{
																											{
																												double var47 = (double)traceTempVariable$result$36_1;
																												double var46 = (double)traceTempVariable$cat$42_1;
																												if(((Math.log(cv$probabilitySample31Value40) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value40) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value40) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value40) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample31Value40) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value40);
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
									{
										{
											int traceTempVariable$cat$48_1 = cv$currentValue;
											{
												if(!(traceTempVariable$cat$48_1 == 1)) {
													int traceTempVariable$result$53_1 = state.var43;
													{
														{
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample31 = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			if(!(traceTempVariable$cat$48_1 == 1)) {
																				int traceTempVariable$result$57_1 = state.var43;
																				{
																					int traceTempVariable$cat$58_1 = cv$currentValue;
																					{
																						{
																							{
																								double var47 = (double)traceTempVariable$result$57_1;
																								double var46 = (double)traceTempVariable$cat$58_1;
																								if(((Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample31$59 = 0; index$sample31$59 < 3; index$sample31$59 += 1) {
																						int distributionTempVariable$cat$61 = index$sample31$59;
																						double cv$probabilitySample31Value60 = (1.0 * state.distribution$sample31[index$sample31$59]);
																						{
																							int traceTempVariable$cat$62_1 = distributionTempVariable$cat$61;
																							{
																								{
																									{
																										double var47 = (double)traceTempVariable$result$57_1;
																										double var46 = (double)traceTempVariable$cat$62_1;
																										if(((Math.log(cv$probabilitySample31Value60) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value60) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value60) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value60) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample31Value60) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value60);
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
										if(!true) {
											for(int index$sample31$49 = 0; index$sample31$49 < 3; index$sample31$49 += 1) {
												int distributionTempVariable$cat$51 = index$sample31$49;
												double cv$probabilitySample31Value50 = (1.0 * state.distribution$sample31[index$sample31$49]);
												{
													int traceTempVariable$cat$52_1 = distributionTempVariable$cat$51;
													{
														if(!(traceTempVariable$cat$52_1 == 1)) {
															int traceTempVariable$result$54_1 = state.var43;
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample31 = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					if(!(traceTempVariable$cat$52_1 == 1)) {
																						int traceTempVariable$result$63_1 = state.var43;
																						{
																							int traceTempVariable$cat$64_1 = cv$currentValue;
																							{
																								{
																									{
																										double var47 = (double)traceTempVariable$result$63_1;
																										double var46 = (double)traceTempVariable$cat$64_1;
																										if(((Math.log(cv$probabilitySample31Value50) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value50) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value50) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value50) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample31Value50) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value50);
																									}
																								}
																							}
																						}
																						{
																							int traceTempVariable$cat$65_1 = distributionTempVariable$cat$51;
																							{
																								{
																									{
																										double var47 = (double)traceTempVariable$result$63_1;
																										double var46 = (double)traceTempVariable$cat$65_1;
																										if(((Math.log(cv$probabilitySample31Value50) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value50) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value50) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value50) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample31Value50) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value50);
																									}
																								}
																							}
																						}
																						if(!true) {
																							for(int index$sample31$66 = 0; index$sample31$66 < 3; index$sample31$66 += 1) {
																								int distributionTempVariable$cat$68 = index$sample31$66;
																								double cv$probabilitySample31Value67 = (cv$probabilitySample31Value50 * state.distribution$sample31[index$sample31$66]);
																								{
																									int traceTempVariable$cat$69_1 = distributionTempVariable$cat$68;
																									{
																										{
																											{
																												double var47 = (double)traceTempVariable$result$63_1;
																												double var46 = (double)traceTempVariable$cat$69_1;
																												if(((Math.log(cv$probabilitySample31Value67) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value67) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value67) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value67) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample31Value67) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value67);
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
									{
										{
											if(!(cv$currentValue == 1)) {
												{
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															{
																{
																	int traceTempVariable$cat$77_1 = cv$currentValue;
																	{
																		double traceTempVariable$var40$82_1 = 0.2;
																		if((0 == traceTempVariable$cat$77_1)) {
																			{
																				double var40 = state.bias[cv$currentValue];
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
																if(!true) {
																	for(int index$sample31$78 = 0; index$sample31$78 < 3; index$sample31$78 += 1) {
																		int distributionTempVariable$cat$80 = index$sample31$78;
																		double cv$probabilitySample31Value79 = (1.0 * state.distribution$sample31[index$sample31$78]);
																		{
																			int traceTempVariable$cat$81_1 = distributionTempVariable$cat$80;
																			{
																				double traceTempVariable$var40$83_1 = 0.2;
																				if((0 == traceTempVariable$cat$81_1)) {
																					{
																						double var40 = state.bias[cv$currentValue];
																						if(((Math.log(cv$probabilitySample31Value79) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value79) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value79) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value79) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)))) + 1)) + (Math.log(cv$probabilitySample31Value79) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value79);
																					}
																				}
																			}
																		}
																	}
																}
																{
																	int traceTempVariable$cat$84_1 = cv$currentValue;
																	{
																		double traceTempVariable$var40$89_1 = 0.3;
																		if((1 == traceTempVariable$cat$84_1)) {
																			{
																				double var40 = state.bias[cv$currentValue];
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
																if(!true) {
																	for(int index$sample31$85 = 0; index$sample31$85 < 3; index$sample31$85 += 1) {
																		int distributionTempVariable$cat$87 = index$sample31$85;
																		double cv$probabilitySample31Value86 = (1.0 * state.distribution$sample31[index$sample31$85]);
																		{
																			int traceTempVariable$cat$88_1 = distributionTempVariable$cat$87;
																			{
																				double traceTempVariable$var40$90_1 = 0.3;
																				if((1 == traceTempVariable$cat$88_1)) {
																					{
																						double var40 = state.bias[cv$currentValue];
																						if(((Math.log(cv$probabilitySample31Value86) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value86) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value86) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value86) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)))) + 1)) + (Math.log(cv$probabilitySample31Value86) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value86);
																					}
																				}
																			}
																		}
																	}
																}
																{
																	int traceTempVariable$cat$91_1 = cv$currentValue;
																	{
																		double traceTempVariable$var40$96_1 = 0.5;
																		if((2 == traceTempVariable$cat$91_1)) {
																			{
																				double var40 = state.bias[cv$currentValue];
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
																if(!true) {
																	for(int index$sample31$92 = 0; index$sample31$92 < 3; index$sample31$92 += 1) {
																		int distributionTempVariable$cat$94 = index$sample31$92;
																		double cv$probabilitySample31Value93 = (1.0 * state.distribution$sample31[index$sample31$92]);
																		{
																			int traceTempVariable$cat$95_1 = distributionTempVariable$cat$94;
																			{
																				double traceTempVariable$var40$97_1 = 0.5;
																				if((2 == traceTempVariable$cat$95_1)) {
																					{
																						double var40 = state.bias[cv$currentValue];
																						if(((Math.log(cv$probabilitySample31Value93) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value93) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value93) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value93) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)))) + 1)) + (Math.log(cv$probabilitySample31Value93) + DistributionSampling.logProbabilityBinomial(state.var43, var40, 10)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value93);
																					}
																				}
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
			if(state.constrainedFlag$sample31) {
				double[] cv$localProbability = state.distribution$sample31;
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
						cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
			}
		}
	}

	private final void inferSample45() {
		if(true) {
			state.constrainedFlag$sample45 = false;
			int cv$numStates = 0;
			if(state.fixedFlag$sample31) {
				{
					{
						double traceTempVariable$var40$6_1 = 0.2;
						if((0 == state.cat))
							cv$numStates = Math.max(cv$numStates, (10 + 1));
					}
				}
			} else {
				if(true) {
					for(int index$sample31$2 = 0; index$sample31$2 < 3; index$sample31$2 += 1) {
						int distributionTempVariable$cat$4 = index$sample31$2;
						double cv$probabilitySample31Value3 = (1.0 * state.distribution$sample31[index$sample31$2]);
						{
							{
								double traceTempVariable$var40$7_1 = 0.2;
								if((0 == distributionTempVariable$cat$4))
									cv$numStates = Math.max(cv$numStates, (10 + 1));
							}
						}
					}
				}
			}
			if(state.fixedFlag$sample31) {
				{
					{
						double traceTempVariable$var40$13_1 = 0.3;
						if((1 == state.cat))
							cv$numStates = Math.max(cv$numStates, (10 + 1));
					}
				}
			} else {
				if(true) {
					for(int index$sample31$9 = 0; index$sample31$9 < 3; index$sample31$9 += 1) {
						int distributionTempVariable$cat$11 = index$sample31$9;
						double cv$probabilitySample31Value10 = (1.0 * state.distribution$sample31[index$sample31$9]);
						{
							{
								double traceTempVariable$var40$14_1 = 0.3;
								if((1 == distributionTempVariable$cat$11))
									cv$numStates = Math.max(cv$numStates, (10 + 1));
							}
						}
					}
				}
			}
			if(state.fixedFlag$sample31) {
				{
					{
						double traceTempVariable$var40$20_1 = 0.5;
						if((2 == state.cat))
							cv$numStates = Math.max(cv$numStates, (10 + 1));
					}
				}
			} else {
				if(true) {
					for(int index$sample31$16 = 0; index$sample31$16 < 3; index$sample31$16 += 1) {
						int distributionTempVariable$cat$18 = index$sample31$16;
						double cv$probabilitySample31Value17 = (1.0 * state.distribution$sample31[index$sample31$16]);
						{
							{
								double traceTempVariable$var40$21_1 = 0.5;
								if((2 == distributionTempVariable$cat$18))
									cv$numStates = Math.max(cv$numStates, (10 + 1));
							}
						}
					}
				}
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var43$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				state.var43 = cv$currentValue;
				{
					{
						if(!(state.cat == 1)) {
							{
								state.result = cv$currentValue;
							}
						}
					}
				}
				if(state.fixedFlag$sample31) {
					{
						{
							double traceTempVariable$var40$28_1 = 0.2;
							if((0 == state.cat)) {
								cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
								double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$currentValue, traceTempVariable$var40$28_1, 10));
								{
									{
										{
											{
												if(!(state.cat == 1)) {
													int traceTempVariable$result$45_1 = cv$currentValue;
													{
														{
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample45 = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				{
																					double var47 = (double)traceTempVariable$result$45_1;
																					double var46 = (double)state.cat;
																					if(((Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
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
								if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
									cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
								else {
									if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
										cv$stateProbabilityValue = cv$accumulatedProbabilities;
									else
										cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
								}
							}
						}
					}
				} else {
					if(true) {
						for(int index$sample31$24 = 0; index$sample31$24 < 3; index$sample31$24 += 1) {
							int distributionTempVariable$cat$26 = index$sample31$24;
							double cv$probabilitySample31Value25 = (1.0 * state.distribution$sample31[index$sample31$24]);
							{
								{
									double traceTempVariable$var40$29_1 = 0.2;
									if((0 == distributionTempVariable$cat$26)) {
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample31Value25);
										double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample31Value25) + DistributionSampling.logProbabilityBinomial(cv$currentValue, traceTempVariable$var40$29_1, 10));
										{
											{
												{
													{
														if(!(distributionTempVariable$cat$26 == 1)) {
															int traceTempVariable$result$51_1 = cv$currentValue;
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample45 = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				int traceTempVariable$cat$81_1 = distributionTempVariable$cat$26;
																				{
																					{
																						{
																							double var47 = (double)traceTempVariable$result$51_1;
																							double var46 = (double)traceTempVariable$cat$81_1;
																							if(((Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample31$82 = 0; index$sample31$82 < 3; index$sample31$82 += 1) {
																					int distributionTempVariable$cat$84 = index$sample31$82;
																					double cv$probabilitySample31Value83 = (1.0 * state.distribution$sample31[index$sample31$82]);
																					{
																						int traceTempVariable$cat$85_1 = distributionTempVariable$cat$84;
																						{
																							{
																								{
																									double var47 = (double)traceTempVariable$result$51_1;
																									double var46 = (double)traceTempVariable$cat$85_1;
																									if(((Math.log(cv$probabilitySample31Value83) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value83) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value83) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value83) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample31Value83) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value83);
																								}
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
												if(!true) {
													for(int index$sample31$47 = 0; index$sample31$47 < 3; index$sample31$47 += 1) {
														int distributionTempVariable$cat$49 = index$sample31$47;
														double cv$probabilitySample31Value48 = (1.0 * state.distribution$sample31[index$sample31$47]);
														{
															{
																if(!(distributionTempVariable$cat$49 == 1)) {
																	int traceTempVariable$result$52_1 = cv$currentValue;
																	{
																		{
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				state.constrainedFlag$sample45 = true;
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						int traceTempVariable$cat$86_1 = distributionTempVariable$cat$26;
																						{
																							{
																								{
																									double var47 = (double)traceTempVariable$result$52_1;
																									double var46 = (double)traceTempVariable$cat$86_1;
																									if(((Math.log(cv$probabilitySample31Value48) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value48) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value48) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value48) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample31Value48) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value48);
																								}
																							}
																						}
																					}
																					{
																						int traceTempVariable$cat$87_1 = distributionTempVariable$cat$49;
																						{
																							{
																								{
																									double var47 = (double)traceTempVariable$result$52_1;
																									double var46 = (double)traceTempVariable$cat$87_1;
																									if(((Math.log(cv$probabilitySample31Value48) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value48) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value48) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value48) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample31Value48) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value48);
																								}
																							}
																						}
																					}
																					if(!true) {
																						for(int index$sample31$88 = 0; index$sample31$88 < 3; index$sample31$88 += 1) {
																							int distributionTempVariable$cat$90 = index$sample31$88;
																							double cv$probabilitySample31Value89 = (cv$probabilitySample31Value48 * state.distribution$sample31[index$sample31$88]);
																							{
																								int traceTempVariable$cat$91_1 = distributionTempVariable$cat$90;
																								{
																									{
																										{
																											double var47 = (double)traceTempVariable$result$52_1;
																											double var46 = (double)traceTempVariable$cat$91_1;
																											if(((Math.log(cv$probabilitySample31Value89) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value89) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value89) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value89) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample31Value89) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value89);
																										}
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
										if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
											cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
										else {
											if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
												cv$stateProbabilityValue = cv$accumulatedProbabilities;
											else
												cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
										}
									}
								}
							}
						}
					}
				}
				if(state.fixedFlag$sample31) {
					{
						{
							double traceTempVariable$var40$35_1 = 0.3;
							if((1 == state.cat)) {
								cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
								double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$currentValue, traceTempVariable$var40$35_1, 10));
								{
									{
										{
											{
												if(!(state.cat == 1)) {
													int traceTempVariable$result$54_1 = cv$currentValue;
													{
														{
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample45 = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				{
																					double var47 = (double)traceTempVariable$result$54_1;
																					double var46 = (double)state.cat;
																					if(((Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
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
								if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
									cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
								else {
									if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
										cv$stateProbabilityValue = cv$accumulatedProbabilities;
									else
										cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
								}
							}
						}
					}
				} else {
					if(true) {
						for(int index$sample31$31 = 0; index$sample31$31 < 3; index$sample31$31 += 1) {
							int distributionTempVariable$cat$33 = index$sample31$31;
							double cv$probabilitySample31Value32 = (1.0 * state.distribution$sample31[index$sample31$31]);
							{
								{
									double traceTempVariable$var40$36_1 = 0.3;
									if((1 == distributionTempVariable$cat$33)) {
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample31Value32);
										double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample31Value32) + DistributionSampling.logProbabilityBinomial(cv$currentValue, traceTempVariable$var40$36_1, 10));
										{
											{
												{
													{
														if(!(distributionTempVariable$cat$33 == 1)) {
															int traceTempVariable$result$60_1 = cv$currentValue;
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample45 = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				int traceTempVariable$cat$93_1 = distributionTempVariable$cat$33;
																				{
																					{
																						{
																							double var47 = (double)traceTempVariable$result$60_1;
																							double var46 = (double)traceTempVariable$cat$93_1;
																							if(((Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample31$94 = 0; index$sample31$94 < 3; index$sample31$94 += 1) {
																					int distributionTempVariable$cat$96 = index$sample31$94;
																					double cv$probabilitySample31Value95 = (1.0 * state.distribution$sample31[index$sample31$94]);
																					{
																						int traceTempVariable$cat$97_1 = distributionTempVariable$cat$96;
																						{
																							{
																								{
																									double var47 = (double)traceTempVariable$result$60_1;
																									double var46 = (double)traceTempVariable$cat$97_1;
																									if(((Math.log(cv$probabilitySample31Value95) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value95) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value95) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value95) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample31Value95) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value95);
																								}
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
												if(!true) {
													for(int index$sample31$56 = 0; index$sample31$56 < 3; index$sample31$56 += 1) {
														int distributionTempVariable$cat$58 = index$sample31$56;
														double cv$probabilitySample31Value57 = (1.0 * state.distribution$sample31[index$sample31$56]);
														{
															{
																if(!(distributionTempVariable$cat$58 == 1)) {
																	int traceTempVariable$result$61_1 = cv$currentValue;
																	{
																		{
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				state.constrainedFlag$sample45 = true;
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						int traceTempVariable$cat$98_1 = distributionTempVariable$cat$33;
																						{
																							{
																								{
																									double var47 = (double)traceTempVariable$result$61_1;
																									double var46 = (double)traceTempVariable$cat$98_1;
																									if(((Math.log(cv$probabilitySample31Value57) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value57) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value57) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value57) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample31Value57) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value57);
																								}
																							}
																						}
																					}
																					{
																						int traceTempVariable$cat$99_1 = distributionTempVariable$cat$58;
																						{
																							{
																								{
																									double var47 = (double)traceTempVariable$result$61_1;
																									double var46 = (double)traceTempVariable$cat$99_1;
																									if(((Math.log(cv$probabilitySample31Value57) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value57) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value57) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value57) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample31Value57) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value57);
																								}
																							}
																						}
																					}
																					if(!true) {
																						for(int index$sample31$100 = 0; index$sample31$100 < 3; index$sample31$100 += 1) {
																							int distributionTempVariable$cat$102 = index$sample31$100;
																							double cv$probabilitySample31Value101 = (cv$probabilitySample31Value57 * state.distribution$sample31[index$sample31$100]);
																							{
																								int traceTempVariable$cat$103_1 = distributionTempVariable$cat$102;
																								{
																									{
																										{
																											double var47 = (double)traceTempVariable$result$61_1;
																											double var46 = (double)traceTempVariable$cat$103_1;
																											if(((Math.log(cv$probabilitySample31Value101) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value101) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value101) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value101) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample31Value101) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value101);
																										}
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
										if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
											cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
										else {
											if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
												cv$stateProbabilityValue = cv$accumulatedProbabilities;
											else
												cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
										}
									}
								}
							}
						}
					}
				}
				if(state.fixedFlag$sample31) {
					{
						{
							double traceTempVariable$var40$42_1 = 0.5;
							if((2 == state.cat)) {
								cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
								double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$currentValue, traceTempVariable$var40$42_1, 10));
								{
									{
										{
											{
												if(!(state.cat == 1)) {
													int traceTempVariable$result$63_1 = cv$currentValue;
													{
														{
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample45 = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				{
																					double var47 = (double)traceTempVariable$result$63_1;
																					double var46 = (double)state.cat;
																					if(((Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
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
								if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
									cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
								else {
									if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
										cv$stateProbabilityValue = cv$accumulatedProbabilities;
									else
										cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
								}
							}
						}
					}
				} else {
					if(true) {
						for(int index$sample31$38 = 0; index$sample31$38 < 3; index$sample31$38 += 1) {
							int distributionTempVariable$cat$40 = index$sample31$38;
							double cv$probabilitySample31Value39 = (1.0 * state.distribution$sample31[index$sample31$38]);
							{
								{
									double traceTempVariable$var40$43_1 = 0.5;
									if((2 == distributionTempVariable$cat$40)) {
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample31Value39);
										double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample31Value39) + DistributionSampling.logProbabilityBinomial(cv$currentValue, traceTempVariable$var40$43_1, 10));
										{
											{
												{
													{
														if(!(distributionTempVariable$cat$40 == 1)) {
															int traceTempVariable$result$69_1 = cv$currentValue;
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample45 = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				int traceTempVariable$cat$105_1 = distributionTempVariable$cat$40;
																				{
																					{
																						{
																							double var47 = (double)traceTempVariable$result$69_1;
																							double var46 = (double)traceTempVariable$cat$105_1;
																							if(((Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample31$106 = 0; index$sample31$106 < 3; index$sample31$106 += 1) {
																					int distributionTempVariable$cat$108 = index$sample31$106;
																					double cv$probabilitySample31Value107 = (1.0 * state.distribution$sample31[index$sample31$106]);
																					{
																						int traceTempVariable$cat$109_1 = distributionTempVariable$cat$108;
																						{
																							{
																								{
																									double var47 = (double)traceTempVariable$result$69_1;
																									double var46 = (double)traceTempVariable$cat$109_1;
																									if(((Math.log(cv$probabilitySample31Value107) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value107) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value107) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value107) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample31Value107) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value107);
																								}
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
												if(!true) {
													for(int index$sample31$65 = 0; index$sample31$65 < 3; index$sample31$65 += 1) {
														int distributionTempVariable$cat$67 = index$sample31$65;
														double cv$probabilitySample31Value66 = (1.0 * state.distribution$sample31[index$sample31$65]);
														{
															{
																if(!(distributionTempVariable$cat$67 == 1)) {
																	int traceTempVariable$result$70_1 = cv$currentValue;
																	{
																		{
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				state.constrainedFlag$sample45 = true;
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						int traceTempVariable$cat$110_1 = distributionTempVariable$cat$40;
																						{
																							{
																								{
																									double var47 = (double)traceTempVariable$result$70_1;
																									double var46 = (double)traceTempVariable$cat$110_1;
																									if(((Math.log(cv$probabilitySample31Value66) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value66) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value66) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value66) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample31Value66) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value66);
																								}
																							}
																						}
																					}
																					{
																						int traceTempVariable$cat$111_1 = distributionTempVariable$cat$67;
																						{
																							{
																								{
																									double var47 = (double)traceTempVariable$result$70_1;
																									double var46 = (double)traceTempVariable$cat$111_1;
																									if(((Math.log(cv$probabilitySample31Value66) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value66) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value66) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value66) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample31Value66) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value66);
																								}
																							}
																						}
																					}
																					if(!true) {
																						for(int index$sample31$112 = 0; index$sample31$112 < 3; index$sample31$112 += 1) {
																							int distributionTempVariable$cat$114 = index$sample31$112;
																							double cv$probabilitySample31Value113 = (cv$probabilitySample31Value66 * state.distribution$sample31[index$sample31$112]);
																							{
																								int traceTempVariable$cat$115_1 = distributionTempVariable$cat$114;
																								{
																									{
																										{
																											double var47 = (double)traceTempVariable$result$70_1;
																											double var46 = (double)traceTempVariable$cat$115_1;
																											if(((Math.log(cv$probabilitySample31Value113) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value113) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value113) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value113) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample31Value113) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((state.data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY)));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value113);
																										}
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
										if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
											cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
										else {
											if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
												cv$stateProbabilityValue = cv$accumulatedProbabilities;
											else
												cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
										}
									}
								}
							}
						}
					}
				}
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample45) {
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
				state.var43 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
				{
					{
						if(!(state.cat == 1)) {
							{
								state.result = state.var43;
							}
						}
					}
				}
			}
		}
	}

	private final void logProbabilityDistribution$sample31() {
		if(!state.fixedProbFlag$sample31) {
			if(state.fixedFlag$sample31) {
				double cv$accumulator = 0.0;
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = state.cat;
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < 3)) && (0 < 3)) && (0.0 <= state.prob[cv$sampleValue])) && (state.prob[cv$sampleValue] <= 1.0))?Math.log(state.prob[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				state.logProbability$cat = cv$sampleProbability;
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				if(state.fixedFlag$sample31)
					state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				state.fixedProbFlag$sample31 = state.fixedFlag$sample31;
			}
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$cat;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample31)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample45() {
		if(!state.fixedProbFlag$sample45) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			if(!(state.cat == 1)) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = state.var43;
						if(state.fixedFlag$sample31) {
							{
								{
									double traceTempVariable$var40$7_1 = 0.2;
									if((0 == state.cat)) {
										{
											double var40 = traceTempVariable$var40$7_1;
											int var41 = 10;
											double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, var40, var41));
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
						} else {
							if(true) {
								for(int index$sample31$3 = 0; index$sample31$3 < 3; index$sample31$3 += 1) {
									int distributionTempVariable$cat$5 = index$sample31$3;
									double cv$probabilitySample31Value4 = (1.0 * state.distribution$sample31[index$sample31$3]);
									{
										{
											double traceTempVariable$var40$8_1 = 0.2;
											if((0 == distributionTempVariable$cat$5)) {
												{
													double var40 = traceTempVariable$var40$8_1;
													int var41 = 10;
													double cv$weightedProbability = (Math.log(cv$probabilitySample31Value4) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, var40, var41));
													if((cv$weightedProbability < cv$distributionAccumulator))
														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
													else {
														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
															cv$distributionAccumulator = cv$weightedProbability;
														else
															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
													}
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample31Value4);
												}
											}
										}
									}
								}
							}
						}
						if(state.fixedFlag$sample31) {
							{
								{
									double traceTempVariable$var40$14_1 = 0.3;
									if((1 == state.cat)) {
										{
											double var40 = traceTempVariable$var40$14_1;
											int var41 = 10;
											double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, var40, var41));
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
						} else {
							if(true) {
								for(int index$sample31$10 = 0; index$sample31$10 < 3; index$sample31$10 += 1) {
									int distributionTempVariable$cat$12 = index$sample31$10;
									double cv$probabilitySample31Value11 = (1.0 * state.distribution$sample31[index$sample31$10]);
									{
										{
											double traceTempVariable$var40$15_1 = 0.3;
											if((1 == distributionTempVariable$cat$12)) {
												{
													double var40 = traceTempVariable$var40$15_1;
													int var41 = 10;
													double cv$weightedProbability = (Math.log(cv$probabilitySample31Value11) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, var40, var41));
													if((cv$weightedProbability < cv$distributionAccumulator))
														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
													else {
														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
															cv$distributionAccumulator = cv$weightedProbability;
														else
															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
													}
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample31Value11);
												}
											}
										}
									}
								}
							}
						}
						if(state.fixedFlag$sample31) {
							{
								{
									double traceTempVariable$var40$21_1 = 0.5;
									if((2 == state.cat)) {
										{
											double var40 = traceTempVariable$var40$21_1;
											int var41 = 10;
											double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, var40, var41));
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
						} else {
							if(true) {
								for(int index$sample31$17 = 0; index$sample31$17 < 3; index$sample31$17 += 1) {
									int distributionTempVariable$cat$19 = index$sample31$17;
									double cv$probabilitySample31Value18 = (1.0 * state.distribution$sample31[index$sample31$17]);
									{
										{
											double traceTempVariable$var40$22_1 = 0.5;
											if((2 == distributionTempVariable$cat$19)) {
												{
													double var40 = traceTempVariable$var40$22_1;
													int var41 = 10;
													double cv$weightedProbability = (Math.log(cv$probabilitySample31Value18) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, var40, var41));
													if((cv$weightedProbability < cv$distributionAccumulator))
														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
													else {
														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
															cv$distributionAccumulator = cv$weightedProbability;
														else
															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
													}
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample31Value18);
												}
											}
										}
									}
								}
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
				state.logProbability$sample45 = cv$sampleProbability;
			}
			boolean cv$guard$result = false;
			state.logProbability$var43 = (state.logProbability$var43 + cv$accumulator);
			{
				{
					if(!(state.cat == 1)) {
						if((state.fixedFlag$sample31 && state.fixedFlag$sample45)) {
							if(!cv$guard$result) {
								cv$guard$result = true;
								state.logProbability$result = (state.logProbability$result + cv$accumulator);
							}
						}
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample45)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample45 = (state.fixedFlag$sample45 && state.fixedFlag$sample31);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			if(!(state.cat == 1)) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample45;
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			boolean cv$guard$result = false;
			state.logProbability$var43 = (state.logProbability$var43 + cv$accumulator);
			{
				{
					if(!(state.cat == 1)) {
						if((state.fixedFlag$sample31 && state.fixedFlag$sample45)) {
							if(!cv$guard$result) {
								cv$guard$result = true;
								state.logProbability$result = (state.logProbability$result + cv$accumulator);
							}
						}
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample45)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample51() {
		if(!state.fixedProbFlag$sample51) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = state.data;
					if(state.fixedFlag$sample31) {
						{
							{
								if(!(state.cat == 1)) {
									int traceTempVariable$result$7_1 = state.var43;
									{
										{
											double var47 = (double)traceTempVariable$result$7_1;
											double var46 = (double)state.cat;
											double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
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
						}
					} else {
						if(true) {
							for(int index$sample31$3 = 0; index$sample31$3 < 3; index$sample31$3 += 1) {
								int distributionTempVariable$cat$5 = index$sample31$3;
								double cv$probabilitySample31Value4 = (1.0 * state.distribution$sample31[index$sample31$3]);
								{
									{
										if(!(distributionTempVariable$cat$5 == 1)) {
											int traceTempVariable$result$8_1 = state.var43;
											{
												{
													double var47 = (double)traceTempVariable$result$8_1;
													double var46 = (double)distributionTempVariable$cat$5;
													double cv$weightedProbability = (Math.log(cv$probabilitySample31Value4) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
													if((cv$weightedProbability < cv$distributionAccumulator))
														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
													else {
														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
															cv$distributionAccumulator = cv$weightedProbability;
														else
															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
													}
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample31Value4);
												}
											}
											if(!true) {
												for(int index$sample31$11 = 0; index$sample31$11 < 3; index$sample31$11 += 1) {
													int distributionTempVariable$cat$13 = index$sample31$11;
													double cv$probabilitySample31Value12 = (cv$probabilitySample31Value4 * state.distribution$sample31[index$sample31$11]);
													{
														{
															double var47 = (double)traceTempVariable$result$8_1;
															double var46 = (double)distributionTempVariable$cat$13;
															double cv$weightedProbability = (Math.log(cv$probabilitySample31Value12) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample31Value12);
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
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			state.logProbability$data = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample51 = (state.fixedFlag$sample31 && state.fixedFlag$sample45);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$data;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!state.fixedProbFlag$sample31) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = state.cat;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < 3)) && (0 < 3)) && (0.0 <= state.prob[cv$sampleValue])) && (state.prob[cv$sampleValue] <= 1.0))?Math.log(state.prob[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$cat = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample31)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample31 = state.fixedFlag$sample31;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$cat;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample31)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!state.fixedProbFlag$sample45) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			if(!(state.cat == 1)) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = state.var43;
						{
							{
								double var40 = state.bias[state.cat];
								int var41 = 10;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, var40, var41));
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
				state.logProbability$sample45 = cv$sampleProbability;
			}
			boolean cv$guard$result = false;
			state.logProbability$var43 = (state.logProbability$var43 + cv$accumulator);
			{
				{
					if(!(state.cat == 1)) {
						if(!cv$guard$result) {
							cv$guard$result = true;
							state.logProbability$result = (state.logProbability$result + cv$accumulator);
						}
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample45)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample45 = (state.fixedFlag$sample45 && state.fixedFlag$sample31);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			if(!(state.cat == 1)) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample45;
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			boolean cv$guard$result = false;
			state.logProbability$var43 = (state.logProbability$var43 + cv$accumulator);
			{
				{
					if(!(state.cat == 1)) {
						if(!cv$guard$result) {
							cv$guard$result = true;
							state.logProbability$result = (state.logProbability$result + cv$accumulator);
						}
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample45)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample51() {
		if(!state.fixedProbFlag$sample51) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = state.data;
					{
						{
							double var47 = (double)state.result;
							double var46 = (double)state.cat;
							double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var46)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$data = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample51 = (state.fixedFlag$sample31 && state.fixedFlag$sample45);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$data;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample31)
			state.cat = DistributionSampling.sampleCategorical(state.RNG$, state.prob, 3);
		if(!(state.cat == 1)) {
			if(!state.fixedFlag$sample45)
				state.var43 = DistributionSampling.sampleBinomial(state.RNG$, state.bias[state.cat], 10);
			if(!(state.fixedFlag$sample31 && state.fixedFlag$sample45))
				state.result = state.var43;
		} else {
			if(!state.fixedFlag$sample31)
				state.result = 5;
		}
		state.data = ((Math.sqrt(state.cat) * DistributionSampling.sampleGaussian(state.RNG$)) + (double)state.result);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		double[] cv$distribution$sample31 = state.distribution$sample31;
		for(int index$var30 = 0; index$var30 < 3; index$var30 += 1) {
			double cv$value = ((((((0.0 <= index$var30) && (index$var30 < 3)) && (0 < 3)) && (0.0 <= state.prob[index$var30])) && (state.prob[index$var30] <= 1.0))?state.prob[index$var30]:0.0);
			if(!state.fixedFlag$sample31)
				cv$distribution$sample31[index$var30] = cv$value;
		}
		if(!(state.cat == 1)) {
			if(!state.fixedFlag$sample45)
				state.var43 = DistributionSampling.sampleBinomial(state.RNG$, state.bias[state.cat], 10);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample31)
			state.cat = DistributionSampling.sampleCategorical(state.RNG$, state.prob, 3);
		if(!(state.cat == 1)) {
			if(!state.fixedFlag$sample45)
				state.var43 = DistributionSampling.sampleBinomial(state.RNG$, state.bias[state.cat], 10);
			if(!(state.fixedFlag$sample31 && state.fixedFlag$sample45))
				state.result = state.var43;
		} else
			state.result = 5;
		state.data = ((Math.sqrt(state.cat) * DistributionSampling.sampleGaussian(state.RNG$)) + (double)state.result);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample31)
			state.cat = DistributionSampling.sampleCategorical(state.RNG$, state.prob, 3);
		if(!(state.cat == 1)) {
			if(!state.fixedFlag$sample45)
				state.var43 = DistributionSampling.sampleBinomial(state.RNG$, state.bias[state.cat], 10);
			if(!(state.fixedFlag$sample31 && state.fixedFlag$sample45))
				state.result = state.var43;
		} else {
			if(!state.fixedFlag$sample31)
				state.result = 5;
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample31)
			state.cat = DistributionSampling.sampleCategorical(state.RNG$, state.prob, 3);
		if(!(state.cat == 1)) {
			if(!state.fixedFlag$sample45)
				state.var43 = DistributionSampling.sampleBinomial(state.RNG$, state.bias[state.cat], 10);
			if(!(state.fixedFlag$sample31 && state.fixedFlag$sample45))
				state.result = state.var43;
		} else
			state.result = 5;
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample31)
				inferSample31();
			if(!(state.cat == 1)) {
				if(!state.fixedFlag$sample45)
					inferSample45();
			}
		} else {
			if(!(state.cat == 1)) {
				if(!state.fixedFlag$sample45)
					inferSample45();
			}
			if(!state.fixedFlag$sample31)
				inferSample31();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample31)
			drawValueSample31();
		if(!(state.cat == 1)) {
			if(!state.constrainedFlag$sample45)
				drawValueSample45();
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample31)
			state.logProbability$cat = Double.NaN;
		state.logProbability$var43 = 0.0;
		state.logProbability$result = 0.0;
		if(!state.fixedProbFlag$sample45)
			state.logProbability$sample45 = Double.NaN;
		if(!state.fixedProbFlag$sample51)
			state.logProbability$data = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.bias[0] = 0.2;
		state.bias[1] = 0.3;
		state.bias[2] = 0.5;
		state.prob[0] = 0.2;
		state.prob[1] = 0.4;
		state.prob[2] = 0.4;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample45)
			logProbabilityValue$sample45();
		logProbabilityValue$sample51();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample31();
		logProbabilityDistribution$sample45();
		logProbabilityDistribution$sample51();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample31();
		logProbabilityValue$sample45();
		logProbabilityValue$sample51();
	}

	@Override
	public final void propagateObservedValues() {
		state.data = state.observedData;
	}

	@Override
	public final void setIntermediates() {
		if(!(state.cat == 1)) {
			if((state.fixedFlag$sample31 && state.fixedFlag$sample45))
				state.result = state.var43;
		} else
			state.result = 5;
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
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model DistributionTest7(double observedData ) {\n"
		     + "\n"
		     + "    double[] bias = {0.2, 0.3, 0.5};\n"
		     + "    double[] prob = {0.2, 0.4, 0.4};\n"
		     + "\n"
		     + "    int cat = categorical(prob).sampleDistribution();\n"
		     + "    int result;\n"
		     + "    if(cat != 1) {\n"
		     + "        result = binomial(bias[cat], 10).sample();\n"
		     + "    } else {\n"
		     + "        result = 5;\n"
		     + "    }\n"
		     + "    \n"
		     + "\n"
		     + "    double data = gaussian(result, (double) cat).sample();\n"
		     + "\n"
		     + "    data.observe(observedData);\n"
		     + "\n"
		     + "    }";
	}
}