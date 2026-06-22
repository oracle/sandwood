package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.internal.numericTools.Gaussian;
import org.sandwood.runtime.model.ExecutionTarget;

final class LowDimMix$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements LowDimMix$CoreInterface {
	private int N;
	private boolean[] component;
	private boolean[] constrainedFlag$sample101;
	private boolean[] constrainedFlag$sample20;
	private boolean[] constrainedFlag$sample83;
	private boolean constrainedFlag$sample88 = true;
	private double[] cv$var97$stateProbabilityGlobal;
	private boolean fixedFlag$sample101 = false;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample83 = false;
	private boolean fixedFlag$sample88 = false;
	private boolean fixedProbFlag$sample101 = false;
	private boolean fixedProbFlag$sample138 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample83 = false;
	private boolean fixedProbFlag$sample88 = false;
	private boolean[] guard$sample20if124$global;
	private int length$yObserved;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$component;
	private double logProbability$componentDistribution;
	private double logProbability$mu;
	private double logProbability$rawMu;
	private double[] logProbability$sample138;
	private double[] logProbability$sample20;
	private double logProbability$sigma;
	private double logProbability$theta;
	private double logProbability$var79;
	private double logProbability$var97;
	private double logProbability$y;
	private double[] mu;
	private double[] rawMu;
	private double[] sigma;
	private boolean system$gibbsForward = true;
	private double theta;
	private double[] y;
	private double[] yObserved;

	public LowDimMix$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final int get$N() {
		return N;
	}

	@Override
	public final boolean[] get$component() {
		return component;
	}

	@Override
	public final void set$component(boolean[] cv$value, boolean allocated$) {
		component = cv$value;
		fixedProbFlag$sample101 = false;
		fixedProbFlag$sample138 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample101() {
		return fixedFlag$sample101;
	}

	@Override
	public final void set$fixedFlag$sample101(boolean cv$value, boolean allocated$) {
		fixedFlag$sample101 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample101$1 = 0; index$constrainedFlag$sample101$1 < constrainedFlag$sample101.length; index$constrainedFlag$sample101$1 += 1)
				constrainedFlag$sample101[index$constrainedFlag$sample101$1] = fixedFlag$sample101;
		}
		fixedProbFlag$sample101 = (fixedFlag$sample101 && fixedProbFlag$sample101);
		fixedProbFlag$sample138 = (fixedFlag$sample101 && fixedProbFlag$sample138);
	}

	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	@Override
	public final void set$fixedFlag$sample20(boolean cv$value, boolean allocated$) {
		fixedFlag$sample20 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample20$1 = 0; index$constrainedFlag$sample20$1 < constrainedFlag$sample20.length; index$constrainedFlag$sample20$1 += 1)
				constrainedFlag$sample20[index$constrainedFlag$sample20$1] = fixedFlag$sample20;
		}
		fixedProbFlag$sample20 = (fixedFlag$sample20 && fixedProbFlag$sample20);
		fixedProbFlag$sample138 = (fixedFlag$sample20 && fixedProbFlag$sample138);
	}

	@Override
	public final boolean get$fixedFlag$sample83() {
		return fixedFlag$sample83;
	}

	@Override
	public final void set$fixedFlag$sample83(boolean cv$value, boolean allocated$) {
		fixedFlag$sample83 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample83$1 = 0; index$constrainedFlag$sample83$1 < constrainedFlag$sample83.length; index$constrainedFlag$sample83$1 += 1)
				constrainedFlag$sample83[index$constrainedFlag$sample83$1] = fixedFlag$sample83;
		}
		fixedProbFlag$sample83 = (fixedFlag$sample83 && fixedProbFlag$sample83);
		fixedProbFlag$sample138 = (fixedFlag$sample83 && fixedProbFlag$sample138);
	}

	@Override
	public final boolean get$fixedFlag$sample88() {
		return fixedFlag$sample88;
	}

	@Override
	public final void set$fixedFlag$sample88(boolean cv$value, boolean allocated$) {
		fixedFlag$sample88 = cv$value;
		constrainedFlag$sample88 = (fixedFlag$sample88 || constrainedFlag$sample88);
		fixedProbFlag$sample88 = (fixedFlag$sample88 && fixedProbFlag$sample88);
		fixedProbFlag$sample101 = (fixedFlag$sample88 && fixedProbFlag$sample101);
	}

	@Override
	public final int get$length$yObserved() {
		return length$yObserved;
	}

	@Override
	public final void set$length$yObserved(int cv$value, boolean allocated$) {
		length$yObserved = cv$value;
	}

	@Override
	public final double get$logProbability$$evidence() {
		return logProbability$$evidence;
	}

	@Override
	public final double getCurrentLogProbability() {
		return logProbability$$model;
	}

	@Override
	public final double get$logProbability$component() {
		return logProbability$component;
	}

	@Override
	public final double get$logProbability$componentDistribution() {
		return logProbability$componentDistribution;
	}

	@Override
	public final double get$logProbability$mu() {
		return logProbability$mu;
	}

	@Override
	public final double get$logProbability$rawMu() {
		return logProbability$rawMu;
	}

	@Override
	public final double get$logProbability$sigma() {
		return logProbability$sigma;
	}

	@Override
	public final double get$logProbability$theta() {
		return logProbability$theta;
	}

	@Override
	public final double get$logProbability$y() {
		return logProbability$y;
	}

	@Override
	public final double[] get$mu() {
		return mu;
	}

	@Override
	public final double[] get$rawMu() {
		return rawMu;
	}

	@Override
	public final void set$rawMu(double[] cv$value, boolean allocated$) {
		rawMu = cv$value;
		fixedProbFlag$sample20 = false;
		fixedProbFlag$sample138 = false;
	}

	@Override
	public final double[] get$sigma() {
		return sigma;
	}

	@Override
	public final void set$sigma(double[] cv$value, boolean allocated$) {
		sigma = cv$value;
		fixedProbFlag$sample83 = false;
		fixedProbFlag$sample138 = false;
	}

	@Override
	public final double get$theta() {
		return theta;
	}

	@Override
	public final void set$theta(double cv$value, boolean allocated$) {
		theta = cv$value;
		fixedProbFlag$sample88 = false;
		fixedProbFlag$sample101 = false;
	}

	@Override
	public final double[] get$y() {
		return y;
	}

	@Override
	public final double[] get$yObserved() {
		return yObserved;
	}

	@Override
	public final void set$yObserved(double[] cv$value, boolean allocated$) {
		yObserved = cv$value;
	}

	private final void drawValueSample101(int var96) {
		component[var96] = DistributionSampling.sampleBernoulli(RNG$, theta);
	}

	private final void drawValueSample20(int var19) {
		rawMu[var19] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		{
			boolean guard$sample20put43 = false;
			{
				if((var19 == 0)) {
					if(!guard$sample20put43) {
						guard$sample20put43 = true;
						{
							double var39;
							if((rawMu[0] < rawMu[1]))
								var39 = rawMu[0];
							else
								var39 = rawMu[1];
							mu[0] = var39;
						}
					}
				}
			}
			{
				if((var19 == 1)) {
					if(!guard$sample20put43) {
						guard$sample20put43 = true;
						{
							double var39;
							if((rawMu[0] < rawMu[1]))
								var39 = rawMu[0];
							else
								var39 = rawMu[1];
							mu[0] = var39;
						}
					}
				}
			}
			{
				if((rawMu[0] < rawMu[1])) {
					if((var19 == 0)) {
						if((rawMu[0] < rawMu[1])) {
							if(!guard$sample20put43) {
								guard$sample20put43 = true;
								{
									double var39;
									if((rawMu[0] < rawMu[1]))
										var39 = rawMu[0];
									else
										var39 = rawMu[1];
									mu[0] = var39;
								}
							}
						}
					}
				}
			}
			{
				if(!(rawMu[0] < rawMu[1])) {
					if((var19 == 1)) {
						if(!(rawMu[0] < rawMu[1])) {
							if(!guard$sample20put43) {
								guard$sample20put43 = true;
								{
									double var39;
									if((rawMu[0] < rawMu[1]))
										var39 = rawMu[0];
									else
										var39 = rawMu[1];
									mu[0] = var39;
								}
							}
						}
					}
				}
			}
		}
		{
			boolean guard$sample20put63 = false;
			{
				if((var19 == 0)) {
					if(!guard$sample20put63) {
						guard$sample20put63 = true;
						{
							double var57;
							if((rawMu[0] < rawMu[1]))
								var57 = rawMu[1];
							else
								var57 = rawMu[0];
							mu[1] = var57;
						}
					}
				}
			}
			{
				if((var19 == 1)) {
					if(!guard$sample20put63) {
						guard$sample20put63 = true;
						{
							double var57;
							if((rawMu[0] < rawMu[1]))
								var57 = rawMu[1];
							else
								var57 = rawMu[0];
							mu[1] = var57;
						}
					}
				}
			}
			{
				if((rawMu[0] < rawMu[1])) {
					if((var19 == 1)) {
						if((rawMu[0] < rawMu[1])) {
							if(!guard$sample20put63) {
								guard$sample20put63 = true;
								{
									double var57;
									if((rawMu[0] < rawMu[1]))
										var57 = rawMu[1];
									else
										var57 = rawMu[0];
									mu[1] = var57;
								}
							}
						}
					}
				}
			}
			{
				if(!(rawMu[0] < rawMu[1])) {
					if((var19 == 0)) {
						if(!(rawMu[0] < rawMu[1])) {
							if(!guard$sample20put63) {
								guard$sample20put63 = true;
								{
									double var57;
									if((rawMu[0] < rawMu[1]))
										var57 = rawMu[1];
									else
										var57 = rawMu[0];
									mu[1] = var57;
								}
							}
						}
					}
				}
			}
		}
	}

	private final void drawValueSample83(int var78) {
		sigma[var78] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleTruncatedGaussian(RNG$, ((0.0 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((0.0 - 0.0) / Math.sqrt((2.0 * 2.0)))), ((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0)))))) + 0.0);
	}

	private final void drawValueSample88() {
		theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
	}

	private final void inferSample101(int var96) {
		if(true) {
			constrainedFlag$sample101[((var96 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = cv$var97$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				boolean var97 = cv$currentValue;
				{
					{
						{
							component[var96] = cv$currentValue;
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= theta) && (theta <= 1.0))?Math.log((cv$currentValue?theta:(1.0 - theta))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								for(int n = 0; n < N; n += 1) {
									if((var96 == n)) {
										{
											{
												{
													if(component[n]) {
														double traceTempVariable$componentMu$3_1 = mu[0];
														{
															{
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	constrainedFlag$sample101[((var96 - 0) / 1)] = true;
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			{
																				{
																					{
																						{
																							double componentSigma;
																							if(component[n])
																								componentSigma = sigma[0];
																							else
																								componentSigma = sigma[1];
																							double var128 = (componentSigma * componentSigma);
																							if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$3_1) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$3_1) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$3_1) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$3_1) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$3_1) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
												boolean[] guard$sample20if124 = guard$sample20if124$global;
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 0)) {
															if(component[n]) {
																if((0 == 0)) {
																	if(component[n])
																		guard$sample20if124[((var19 - 0) / 1)] = false;
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 1)) {
															if(component[n]) {
																if((0 == 0)) {
																	if(component[n])
																		guard$sample20if124[((var19 - 0) / 1)] = false;
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((rawMu[0] < rawMu[1])) {
															if((var19 == 0)) {
																if((rawMu[0] < rawMu[1])) {
																	if(component[n]) {
																		if((0 == 0)) {
																			if(component[n])
																				guard$sample20if124[((var19 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if(!(rawMu[0] < rawMu[1])) {
															if((var19 == 1)) {
																if(!(rawMu[0] < rawMu[1])) {
																	if(component[n]) {
																		if((0 == 0)) {
																			if(component[n])
																				guard$sample20if124[((var19 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 0)) {
															if(component[n]) {
																if((1 == 0)) {
																	if(component[n])
																		guard$sample20if124[((var19 - 0) / 1)] = false;
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 1)) {
															if(component[n]) {
																if((1 == 0)) {
																	if(component[n])
																		guard$sample20if124[((var19 - 0) / 1)] = false;
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((rawMu[0] < rawMu[1])) {
															if((var19 == 1)) {
																if((rawMu[0] < rawMu[1])) {
																	if(component[n]) {
																		if((1 == 0)) {
																			if(component[n])
																				guard$sample20if124[((var19 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if(!(rawMu[0] < rawMu[1])) {
															if((var19 == 0)) {
																if(!(rawMu[0] < rawMu[1])) {
																	if(component[n]) {
																		if((1 == 0)) {
																			if(component[n])
																				guard$sample20if124[((var19 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 0)) {
															if(component[n]) {
																if((0 == 0)) {
																	if(component[n]) {
																		if(!guard$sample20if124[((var19 - 0) / 1)]) {
																			guard$sample20if124[((var19 - 0) / 1)] = true;
																			{
																				{
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							{
																								{
																									double var6 = (2.0 * 2.0);
																									if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
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
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 1)) {
															if(component[n]) {
																if((0 == 0)) {
																	if(component[n]) {
																		if(!guard$sample20if124[((var19 - 0) / 1)]) {
																			guard$sample20if124[((var19 - 0) / 1)] = true;
																			{
																				{
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							{
																								{
																									double var6 = (2.0 * 2.0);
																									if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
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
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((rawMu[0] < rawMu[1])) {
															if((var19 == 0)) {
																if((rawMu[0] < rawMu[1])) {
																	if(component[n]) {
																		if((0 == 0)) {
																			if(component[n]) {
																				if(!guard$sample20if124[((var19 - 0) / 1)]) {
																					guard$sample20if124[((var19 - 0) / 1)] = true;
																					{
																						{
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								{
																									{
																										{
																											double var6 = (2.0 * 2.0);
																											if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
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
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if(!(rawMu[0] < rawMu[1])) {
															if((var19 == 1)) {
																if(!(rawMu[0] < rawMu[1])) {
																	if(component[n]) {
																		if((0 == 0)) {
																			if(component[n]) {
																				if(!guard$sample20if124[((var19 - 0) / 1)]) {
																					guard$sample20if124[((var19 - 0) / 1)] = true;
																					{
																						{
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								{
																									{
																										{
																											double var6 = (2.0 * 2.0);
																											if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
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
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 0)) {
															if(component[n]) {
																if((1 == 0)) {
																	if(component[n]) {
																		if(!guard$sample20if124[((var19 - 0) / 1)]) {
																			guard$sample20if124[((var19 - 0) / 1)] = true;
																			{
																				{
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							{
																								{
																									double var6 = (2.0 * 2.0);
																									if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
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
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 1)) {
															if(component[n]) {
																if((1 == 0)) {
																	if(component[n]) {
																		if(!guard$sample20if124[((var19 - 0) / 1)]) {
																			guard$sample20if124[((var19 - 0) / 1)] = true;
																			{
																				{
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							{
																								{
																									double var6 = (2.0 * 2.0);
																									if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
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
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((rawMu[0] < rawMu[1])) {
															if((var19 == 1)) {
																if((rawMu[0] < rawMu[1])) {
																	if(component[n]) {
																		if((1 == 0)) {
																			if(component[n]) {
																				if(!guard$sample20if124[((var19 - 0) / 1)]) {
																					guard$sample20if124[((var19 - 0) / 1)] = true;
																					{
																						{
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								{
																									{
																										{
																											double var6 = (2.0 * 2.0);
																											if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
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
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if(!(rawMu[0] < rawMu[1])) {
															if((var19 == 0)) {
																if(!(rawMu[0] < rawMu[1])) {
																	if(component[n]) {
																		if((1 == 0)) {
																			if(component[n]) {
																				if(!guard$sample20if124[((var19 - 0) / 1)]) {
																					guard$sample20if124[((var19 - 0) / 1)] = true;
																					{
																						{
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								{
																									{
																										{
																											double var6 = (2.0 * 2.0);
																											if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
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
													}
												}
											}
											{
												{
													if(!component[n]) {
														double traceTempVariable$componentMu$30_1 = mu[1];
														{
															{
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	constrainedFlag$sample101[((var96 - 0) / 1)] = true;
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			{
																				{
																					{
																						{
																							double componentSigma;
																							if(component[n])
																								componentSigma = sigma[0];
																							else
																								componentSigma = sigma[1];
																							double var128 = (componentSigma * componentSigma);
																							if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$30_1) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$30_1) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$30_1) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$30_1) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$30_1) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
												boolean[] guard$sample20if124 = guard$sample20if124$global;
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 0)) {
															if(!component[n]) {
																if((0 == 1)) {
																	if(!component[n])
																		guard$sample20if124[((var19 - 0) / 1)] = false;
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 1)) {
															if(!component[n]) {
																if((0 == 1)) {
																	if(!component[n])
																		guard$sample20if124[((var19 - 0) / 1)] = false;
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((rawMu[0] < rawMu[1])) {
															if((var19 == 0)) {
																if((rawMu[0] < rawMu[1])) {
																	if(!component[n]) {
																		if((0 == 1)) {
																			if(!component[n])
																				guard$sample20if124[((var19 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if(!(rawMu[0] < rawMu[1])) {
															if((var19 == 1)) {
																if(!(rawMu[0] < rawMu[1])) {
																	if(!component[n]) {
																		if((0 == 1)) {
																			if(!component[n])
																				guard$sample20if124[((var19 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 0)) {
															if(!component[n]) {
																if((1 == 1)) {
																	if(!component[n])
																		guard$sample20if124[((var19 - 0) / 1)] = false;
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 1)) {
															if(!component[n]) {
																if((1 == 1)) {
																	if(!component[n])
																		guard$sample20if124[((var19 - 0) / 1)] = false;
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((rawMu[0] < rawMu[1])) {
															if((var19 == 1)) {
																if((rawMu[0] < rawMu[1])) {
																	if(!component[n]) {
																		if((1 == 1)) {
																			if(!component[n])
																				guard$sample20if124[((var19 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if(!(rawMu[0] < rawMu[1])) {
															if((var19 == 0)) {
																if(!(rawMu[0] < rawMu[1])) {
																	if(!component[n]) {
																		if((1 == 1)) {
																			if(!component[n])
																				guard$sample20if124[((var19 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 0)) {
															if(!component[n]) {
																if((0 == 1)) {
																	if(!component[n]) {
																		if(!guard$sample20if124[((var19 - 0) / 1)]) {
																			guard$sample20if124[((var19 - 0) / 1)] = true;
																			{
																				{
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							{
																								{
																									double var6 = (2.0 * 2.0);
																									if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
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
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 1)) {
															if(!component[n]) {
																if((0 == 1)) {
																	if(!component[n]) {
																		if(!guard$sample20if124[((var19 - 0) / 1)]) {
																			guard$sample20if124[((var19 - 0) / 1)] = true;
																			{
																				{
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							{
																								{
																									double var6 = (2.0 * 2.0);
																									if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
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
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((rawMu[0] < rawMu[1])) {
															if((var19 == 0)) {
																if((rawMu[0] < rawMu[1])) {
																	if(!component[n]) {
																		if((0 == 1)) {
																			if(!component[n]) {
																				if(!guard$sample20if124[((var19 - 0) / 1)]) {
																					guard$sample20if124[((var19 - 0) / 1)] = true;
																					{
																						{
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								{
																									{
																										{
																											double var6 = (2.0 * 2.0);
																											if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
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
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if(!(rawMu[0] < rawMu[1])) {
															if((var19 == 1)) {
																if(!(rawMu[0] < rawMu[1])) {
																	if(!component[n]) {
																		if((0 == 1)) {
																			if(!component[n]) {
																				if(!guard$sample20if124[((var19 - 0) / 1)]) {
																					guard$sample20if124[((var19 - 0) / 1)] = true;
																					{
																						{
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								{
																									{
																										{
																											double var6 = (2.0 * 2.0);
																											if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
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
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 0)) {
															if(!component[n]) {
																if((1 == 1)) {
																	if(!component[n]) {
																		if(!guard$sample20if124[((var19 - 0) / 1)]) {
																			guard$sample20if124[((var19 - 0) / 1)] = true;
																			{
																				{
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							{
																								{
																									double var6 = (2.0 * 2.0);
																									if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
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
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 1)) {
															if(!component[n]) {
																if((1 == 1)) {
																	if(!component[n]) {
																		if(!guard$sample20if124[((var19 - 0) / 1)]) {
																			guard$sample20if124[((var19 - 0) / 1)] = true;
																			{
																				{
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							{
																								{
																									double var6 = (2.0 * 2.0);
																									if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
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
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((rawMu[0] < rawMu[1])) {
															if((var19 == 1)) {
																if((rawMu[0] < rawMu[1])) {
																	if(!component[n]) {
																		if((1 == 1)) {
																			if(!component[n]) {
																				if(!guard$sample20if124[((var19 - 0) / 1)]) {
																					guard$sample20if124[((var19 - 0) / 1)] = true;
																					{
																						{
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								{
																									{
																										{
																											double var6 = (2.0 * 2.0);
																											if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
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
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if(!(rawMu[0] < rawMu[1])) {
															if((var19 == 0)) {
																if(!(rawMu[0] < rawMu[1])) {
																	if(!component[n]) {
																		if((1 == 1)) {
																			if(!component[n]) {
																				if(!guard$sample20if124[((var19 - 0) / 1)]) {
																					guard$sample20if124[((var19 - 0) / 1)] = true;
																					{
																						{
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								{
																									{
																										{
																											double var6 = (2.0 * 2.0);
																											if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
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
								for(int n = 0; n < N; n += 1) {
									if((var96 == n)) {
										{
											{
												{
													if(component[n]) {
														double traceTempVariable$componentSigma$58_1 = sigma[0];
														{
															{
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	constrainedFlag$sample101[((var96 - 0) / 1)] = true;
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			{
																				{
																					{
																						{
																							double componentMu;
																							if(component[n])
																								componentMu = mu[0];
																							else
																								componentMu = mu[1];
																							double var128 = (traceTempVariable$componentSigma$58_1 * traceTempVariable$componentSigma$58_1);
																							if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
													for(int var78 = 0; var78 < 2; var78 += 1) {
														if(component[n]) {
															if((var78 == 0)) {
																if(component[n]) {
																	{
																		{
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					{
																						{
																							double var63 = (2.0 * 2.0);
																							if(((Math.log(1.0) + (((((0.0 <= sigma[var78]) && (sigma[var78] <= 1.0E100)) && (0.0 < 1.0E100)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((sigma[var78] - 0.0) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt(var63))) - Gaussian.cdf(((0.0 - 0.0) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((((0.0 <= sigma[var78]) && (sigma[var78] <= 1.0E100)) && (0.0 < 1.0E100)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((sigma[var78] - 0.0) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt(var63))) - Gaussian.cdf(((0.0 - 0.0) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((((0.0 <= sigma[var78]) && (sigma[var78] <= 1.0E100)) && (0.0 < 1.0E100)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((sigma[var78] - 0.0) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt(var63))) - Gaussian.cdf(((0.0 - 0.0) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((((0.0 <= sigma[var78]) && (sigma[var78] <= 1.0E100)) && (0.0 < 1.0E100)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((sigma[var78] - 0.0) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt(var63))) - Gaussian.cdf(((0.0 - 0.0) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((((0.0 <= sigma[var78]) && (sigma[var78] <= 1.0E100)) && (0.0 < 1.0E100)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((sigma[var78] - 0.0) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt(var63))) - Gaussian.cdf(((0.0 - 0.0) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY)));
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
											{
												{
													if(!component[n]) {
														double traceTempVariable$componentSigma$63_1 = sigma[1];
														{
															{
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	constrainedFlag$sample101[((var96 - 0) / 1)] = true;
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			{
																				{
																					{
																						{
																							double componentMu;
																							if(component[n])
																								componentMu = mu[0];
																							else
																								componentMu = mu[1];
																							double var128 = (traceTempVariable$componentSigma$63_1 * traceTempVariable$componentSigma$63_1);
																							if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
													for(int var78 = 0; var78 < 2; var78 += 1) {
														if(!component[n]) {
															if((var78 == 1)) {
																if(!component[n]) {
																	{
																		{
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					{
																						{
																							double var63 = (2.0 * 2.0);
																							if(((Math.log(1.0) + (((((0.0 <= sigma[var78]) && (sigma[var78] <= 1.0E100)) && (0.0 < 1.0E100)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((sigma[var78] - 0.0) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt(var63))) - Gaussian.cdf(((0.0 - 0.0) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((((0.0 <= sigma[var78]) && (sigma[var78] <= 1.0E100)) && (0.0 < 1.0E100)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((sigma[var78] - 0.0) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt(var63))) - Gaussian.cdf(((0.0 - 0.0) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((((0.0 <= sigma[var78]) && (sigma[var78] <= 1.0E100)) && (0.0 < 1.0E100)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((sigma[var78] - 0.0) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt(var63))) - Gaussian.cdf(((0.0 - 0.0) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((((0.0 <= sigma[var78]) && (sigma[var78] <= 1.0E100)) && (0.0 < 1.0E100)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((sigma[var78] - 0.0) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt(var63))) - Gaussian.cdf(((0.0 - 0.0) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((((0.0 <= sigma[var78]) && (sigma[var78] <= 1.0E100)) && (0.0 < 1.0E100)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((sigma[var78] - 0.0) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt(var63))) - Gaussian.cdf(((0.0 - 0.0) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY)));
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
			if(constrainedFlag$sample101[((var96 - 0) / 1)]) {
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
				boolean var97 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
				{
					{
						{
							component[var96] = var97;
						}
					}
				}
			}
		}
	}

	private final void inferSample20(int var19) {
		if(true) {
			constrainedFlag$sample20[((var19 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = rawMu[var19];
			double cv$originalProbability = 0.0;
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((constrainedFlag$sample20[((var19 - 0) / 1)] || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						double var20 = cv$proposedValue;
						{
							{
								{
									rawMu[var19] = cv$currentValue;
								}
							}
						}
						{
							boolean guard$sample20put43 = false;
							{
								if((var19 == 0)) {
									if(!guard$sample20put43) {
										guard$sample20put43 = true;
										{
											double var39;
											if((rawMu[0] < rawMu[1]))
												var39 = rawMu[0];
											else
												var39 = rawMu[1];
											mu[0] = var39;
										}
									}
								}
							}
							{
								if((var19 == 1)) {
									if(!guard$sample20put43) {
										guard$sample20put43 = true;
										{
											double var39;
											if((rawMu[0] < rawMu[1]))
												var39 = rawMu[0];
											else
												var39 = rawMu[1];
											mu[0] = var39;
										}
									}
								}
							}
							{
								if((rawMu[0] < rawMu[1])) {
									if((var19 == 0)) {
										if((rawMu[0] < rawMu[1])) {
											if(!guard$sample20put43) {
												guard$sample20put43 = true;
												{
													double var39;
													if((rawMu[0] < rawMu[1]))
														var39 = rawMu[0];
													else
														var39 = rawMu[1];
													mu[0] = var39;
												}
											}
										}
									}
								}
							}
							{
								if(!(rawMu[0] < rawMu[1])) {
									if((var19 == 1)) {
										if(!(rawMu[0] < rawMu[1])) {
											if(!guard$sample20put43) {
												guard$sample20put43 = true;
												{
													double var39;
													if((rawMu[0] < rawMu[1]))
														var39 = rawMu[0];
													else
														var39 = rawMu[1];
													mu[0] = var39;
												}
											}
										}
									}
								}
							}
						}
						{
							boolean guard$sample20put63 = false;
							{
								if((var19 == 0)) {
									if(!guard$sample20put63) {
										guard$sample20put63 = true;
										{
											double var57;
											if((rawMu[0] < rawMu[1]))
												var57 = rawMu[1];
											else
												var57 = rawMu[0];
											mu[1] = var57;
										}
									}
								}
							}
							{
								if((var19 == 1)) {
									if(!guard$sample20put63) {
										guard$sample20put63 = true;
										{
											double var57;
											if((rawMu[0] < rawMu[1]))
												var57 = rawMu[1];
											else
												var57 = rawMu[0];
											mu[1] = var57;
										}
									}
								}
							}
							{
								if((rawMu[0] < rawMu[1])) {
									if((var19 == 1)) {
										if((rawMu[0] < rawMu[1])) {
											if(!guard$sample20put63) {
												guard$sample20put63 = true;
												{
													double var57;
													if((rawMu[0] < rawMu[1]))
														var57 = rawMu[1];
													else
														var57 = rawMu[0];
													mu[1] = var57;
												}
											}
										}
									}
								}
							}
							{
								if(!(rawMu[0] < rawMu[1])) {
									if((var19 == 0)) {
										if(!(rawMu[0] < rawMu[1])) {
											if(!guard$sample20put63) {
												guard$sample20put63 = true;
												{
													double var57;
													if((rawMu[0] < rawMu[1]))
														var57 = rawMu[1];
													else
														var57 = rawMu[0];
													mu[1] = var57;
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
						double var6 = (2.0 * 2.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((cv$currentValue - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
						{
							{
								{
									double traceTempVariable$var36$10_1 = cv$currentValue;
									if((rawMu[0] < rawMu[1])) {
										if((var19 == 0)) {
											if((rawMu[0] < rawMu[1])) {
												double traceTempVariable$var39$10_2 = traceTempVariable$var36$10_1;
												double traceTempVariable$var115$10_3 = traceTempVariable$var39$10_2;
												for(int n = 0; n < N; n += 1) {
													if(component[n]) {
														if((0 == 0)) {
															if(component[n]) {
																double traceTempVariable$componentMu$10_5 = traceTempVariable$var115$10_3;
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					{
																						{
																							{
																								double componentSigma;
																								if(component[n])
																									componentSigma = sigma[0];
																								else
																									componentSigma = sigma[1];
																								double var128 = (componentSigma * componentSigma);
																								if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$10_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$10_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$10_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$10_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$10_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
								{
									double traceTempVariable$var36$11_1 = cv$currentValue;
									if((rawMu[0] < rawMu[1])) {
										if((var19 == 0)) {
											if((rawMu[0] < rawMu[1])) {
												double traceTempVariable$var39$11_2 = traceTempVariable$var36$11_1;
												double traceTempVariable$var117$11_3 = traceTempVariable$var39$11_2;
												for(int n = 0; n < N; n += 1) {
													if(!component[n]) {
														if((0 == 1)) {
															if(!component[n]) {
																double traceTempVariable$componentMu$11_5 = traceTempVariable$var117$11_3;
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					{
																						{
																							{
																								double componentSigma;
																								if(component[n])
																									componentSigma = sigma[0];
																								else
																									componentSigma = sigma[1];
																								double var128 = (componentSigma * componentSigma);
																								if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$11_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$11_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$11_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$11_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$11_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
								{
									double traceTempVariable$var38$12_1 = cv$currentValue;
									if(!(rawMu[0] < rawMu[1])) {
										if((var19 == 1)) {
											if(!(rawMu[0] < rawMu[1])) {
												double traceTempVariable$var39$12_2 = traceTempVariable$var38$12_1;
												double traceTempVariable$var115$12_3 = traceTempVariable$var39$12_2;
												for(int n = 0; n < N; n += 1) {
													if(component[n]) {
														if((0 == 0)) {
															if(component[n]) {
																double traceTempVariable$componentMu$12_5 = traceTempVariable$var115$12_3;
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					{
																						{
																							{
																								double componentSigma;
																								if(component[n])
																									componentSigma = sigma[0];
																								else
																									componentSigma = sigma[1];
																								double var128 = (componentSigma * componentSigma);
																								if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$12_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$12_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$12_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$12_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$12_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
								{
									double traceTempVariable$var38$13_1 = cv$currentValue;
									if(!(rawMu[0] < rawMu[1])) {
										if((var19 == 1)) {
											if(!(rawMu[0] < rawMu[1])) {
												double traceTempVariable$var39$13_2 = traceTempVariable$var38$13_1;
												double traceTempVariable$var117$13_3 = traceTempVariable$var39$13_2;
												for(int n = 0; n < N; n += 1) {
													if(!component[n]) {
														if((0 == 1)) {
															if(!component[n]) {
																double traceTempVariable$componentMu$13_5 = traceTempVariable$var117$13_3;
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					{
																						{
																							{
																								double componentSigma;
																								if(component[n])
																									componentSigma = sigma[0];
																								else
																									componentSigma = sigma[1];
																								double var128 = (componentSigma * componentSigma);
																								if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$13_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$13_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$13_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$13_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$13_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
								{
									double traceTempVariable$var54$14_1 = cv$currentValue;
									if((rawMu[0] < rawMu[1])) {
										if((var19 == 1)) {
											if((rawMu[0] < rawMu[1])) {
												double traceTempVariable$var57$14_2 = traceTempVariable$var54$14_1;
												double traceTempVariable$var115$14_3 = traceTempVariable$var57$14_2;
												for(int n = 0; n < N; n += 1) {
													if(component[n]) {
														if((1 == 0)) {
															if(component[n]) {
																double traceTempVariable$componentMu$14_5 = traceTempVariable$var115$14_3;
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					{
																						{
																							{
																								double componentSigma;
																								if(component[n])
																									componentSigma = sigma[0];
																								else
																									componentSigma = sigma[1];
																								double var128 = (componentSigma * componentSigma);
																								if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$14_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$14_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$14_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$14_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$14_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
								{
									double traceTempVariable$var54$15_1 = cv$currentValue;
									if((rawMu[0] < rawMu[1])) {
										if((var19 == 1)) {
											if((rawMu[0] < rawMu[1])) {
												double traceTempVariable$var57$15_2 = traceTempVariable$var54$15_1;
												double traceTempVariable$var117$15_3 = traceTempVariable$var57$15_2;
												for(int n = 0; n < N; n += 1) {
													if(!component[n]) {
														if((1 == 1)) {
															if(!component[n]) {
																double traceTempVariable$componentMu$15_5 = traceTempVariable$var117$15_3;
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					{
																						{
																							{
																								double componentSigma;
																								if(component[n])
																									componentSigma = sigma[0];
																								else
																									componentSigma = sigma[1];
																								double var128 = (componentSigma * componentSigma);
																								if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$15_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$15_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$15_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$15_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$15_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
								{
									double traceTempVariable$var56$16_1 = cv$currentValue;
									if(!(rawMu[0] < rawMu[1])) {
										if((var19 == 0)) {
											if(!(rawMu[0] < rawMu[1])) {
												double traceTempVariable$var57$16_2 = traceTempVariable$var56$16_1;
												double traceTempVariable$var115$16_3 = traceTempVariable$var57$16_2;
												for(int n = 0; n < N; n += 1) {
													if(component[n]) {
														if((1 == 0)) {
															if(component[n]) {
																double traceTempVariable$componentMu$16_5 = traceTempVariable$var115$16_3;
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					{
																						{
																							{
																								double componentSigma;
																								if(component[n])
																									componentSigma = sigma[0];
																								else
																									componentSigma = sigma[1];
																								double var128 = (componentSigma * componentSigma);
																								if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$16_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$16_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$16_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$16_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$16_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
								{
									double traceTempVariable$var56$17_1 = cv$currentValue;
									if(!(rawMu[0] < rawMu[1])) {
										if((var19 == 0)) {
											if(!(rawMu[0] < rawMu[1])) {
												double traceTempVariable$var57$17_2 = traceTempVariable$var56$17_1;
												double traceTempVariable$var117$17_3 = traceTempVariable$var57$17_2;
												for(int n = 0; n < N; n += 1) {
													if(!component[n]) {
														if((1 == 1)) {
															if(!component[n]) {
																double traceTempVariable$componentMu$17_5 = traceTempVariable$var117$17_3;
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					{
																						{
																							{
																								double componentSigma;
																								if(component[n])
																									componentSigma = sigma[0];
																								else
																									componentSigma = sigma[1];
																								double var128 = (componentSigma * componentSigma);
																								if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$17_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$17_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$17_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$17_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$17_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
						}
						{
							{
								boolean guard$sample20if41 = false;
								{
									if((var19 == 0)) {
										if(!guard$sample20if41) {
											guard$sample20if41 = true;
											{
												{
													{
														if((rawMu[0] < rawMu[1])) {
															double traceTempVariable$var39$36_1 = rawMu[0];
															double traceTempVariable$var115$36_2 = traceTempVariable$var39$36_1;
															for(int n = 0; n < N; n += 1) {
																if(component[n]) {
																	if((0 == 0)) {
																		if(component[n]) {
																			double traceTempVariable$componentMu$36_4 = traceTempVariable$var115$36_2;
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										{
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												double var128 = (componentSigma * componentSigma);
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$36_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$36_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$36_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$36_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$36_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
													{
														if((rawMu[0] < rawMu[1])) {
															double traceTempVariable$var39$38_1 = rawMu[0];
															double traceTempVariable$var117$38_2 = traceTempVariable$var39$38_1;
															for(int n = 0; n < N; n += 1) {
																if(!component[n]) {
																	if((0 == 1)) {
																		if(!component[n]) {
																			double traceTempVariable$componentMu$38_4 = traceTempVariable$var117$38_2;
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										{
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												double var128 = (componentSigma * componentSigma);
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$38_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$38_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$38_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$38_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$38_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
												{
													{
														for(int index$var19$48_1 = 0; index$var19$48_1 < 2; index$var19$48_1 += 1) {
															if((rawMu[0] < rawMu[1])) {
																if((index$var19$48_1 == 0)) {
																	if((rawMu[0] < rawMu[1])) {
																		{
																			{
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$48_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$48_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$48_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$48_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$48_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)));
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
												{
													{
														if(!(rawMu[0] < rawMu[1])) {
															double traceTempVariable$var39$52_1 = rawMu[1];
															double traceTempVariable$var115$52_2 = traceTempVariable$var39$52_1;
															for(int n = 0; n < N; n += 1) {
																if(component[n]) {
																	if((0 == 0)) {
																		if(component[n]) {
																			double traceTempVariable$componentMu$52_4 = traceTempVariable$var115$52_2;
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										{
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												double var128 = (componentSigma * componentSigma);
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$52_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$52_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$52_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$52_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$52_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
													{
														if(!(rawMu[0] < rawMu[1])) {
															double traceTempVariable$var39$54_1 = rawMu[1];
															double traceTempVariable$var117$54_2 = traceTempVariable$var39$54_1;
															for(int n = 0; n < N; n += 1) {
																if(!component[n]) {
																	if((0 == 1)) {
																		if(!component[n]) {
																			double traceTempVariable$componentMu$54_4 = traceTempVariable$var117$54_2;
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										{
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												double var128 = (componentSigma * componentSigma);
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$54_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$54_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$54_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$54_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$54_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
												{
													{
														for(int index$var19$64_1 = 0; index$var19$64_1 < 2; index$var19$64_1 += 1) {
															if(!(rawMu[0] < rawMu[1])) {
																if((index$var19$64_1 == 1)) {
																	if(!(rawMu[0] < rawMu[1])) {
																		{
																			{
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$64_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$64_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$64_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$64_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$64_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)));
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
										}
									}
								}
								{
									if((var19 == 1)) {
										if(!guard$sample20if41) {
											guard$sample20if41 = true;
											{
												{
													{
														if((rawMu[0] < rawMu[1])) {
															double traceTempVariable$var39$37_1 = rawMu[0];
															double traceTempVariable$var115$37_2 = traceTempVariable$var39$37_1;
															for(int n = 0; n < N; n += 1) {
																if(component[n]) {
																	if((0 == 0)) {
																		if(component[n]) {
																			double traceTempVariable$componentMu$37_4 = traceTempVariable$var115$37_2;
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										{
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												double var128 = (componentSigma * componentSigma);
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$37_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$37_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$37_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$37_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$37_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
													{
														if((rawMu[0] < rawMu[1])) {
															double traceTempVariable$var39$39_1 = rawMu[0];
															double traceTempVariable$var117$39_2 = traceTempVariable$var39$39_1;
															for(int n = 0; n < N; n += 1) {
																if(!component[n]) {
																	if((0 == 1)) {
																		if(!component[n]) {
																			double traceTempVariable$componentMu$39_4 = traceTempVariable$var117$39_2;
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										{
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												double var128 = (componentSigma * componentSigma);
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$39_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$39_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$39_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$39_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$39_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
												{
													{
														for(int index$var19$49_1 = 0; index$var19$49_1 < 2; index$var19$49_1 += 1) {
															if((rawMu[0] < rawMu[1])) {
																if((index$var19$49_1 == 0)) {
																	if((rawMu[0] < rawMu[1])) {
																		{
																			{
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$49_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$49_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$49_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$49_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$49_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)));
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
												{
													{
														if(!(rawMu[0] < rawMu[1])) {
															double traceTempVariable$var39$53_1 = rawMu[1];
															double traceTempVariable$var115$53_2 = traceTempVariable$var39$53_1;
															for(int n = 0; n < N; n += 1) {
																if(component[n]) {
																	if((0 == 0)) {
																		if(component[n]) {
																			double traceTempVariable$componentMu$53_4 = traceTempVariable$var115$53_2;
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										{
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												double var128 = (componentSigma * componentSigma);
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$53_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$53_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$53_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$53_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$53_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
													{
														if(!(rawMu[0] < rawMu[1])) {
															double traceTempVariable$var39$55_1 = rawMu[1];
															double traceTempVariable$var117$55_2 = traceTempVariable$var39$55_1;
															for(int n = 0; n < N; n += 1) {
																if(!component[n]) {
																	if((0 == 1)) {
																		if(!component[n]) {
																			double traceTempVariable$componentMu$55_4 = traceTempVariable$var117$55_2;
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										{
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												double var128 = (componentSigma * componentSigma);
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$55_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$55_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$55_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$55_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$55_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
												{
													{
														for(int index$var19$65_1 = 0; index$var19$65_1 < 2; index$var19$65_1 += 1) {
															if(!(rawMu[0] < rawMu[1])) {
																if((index$var19$65_1 == 1)) {
																	if(!(rawMu[0] < rawMu[1])) {
																		{
																			{
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$65_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$65_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$65_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$65_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$65_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)));
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
										}
									}
								}
							}
						}
						{
							{
								boolean guard$sample20if61 = false;
								{
									if((var19 == 0)) {
										if(!guard$sample20if61) {
											guard$sample20if61 = true;
											{
												{
													{
														if((rawMu[0] < rawMu[1])) {
															double traceTempVariable$var57$70_1 = rawMu[1];
															double traceTempVariable$var115$70_2 = traceTempVariable$var57$70_1;
															for(int n = 0; n < N; n += 1) {
																if(component[n]) {
																	if((1 == 0)) {
																		if(component[n]) {
																			double traceTempVariable$componentMu$70_4 = traceTempVariable$var115$70_2;
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										{
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												double var128 = (componentSigma * componentSigma);
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$70_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$70_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$70_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$70_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$70_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
													{
														if((rawMu[0] < rawMu[1])) {
															double traceTempVariable$var57$72_1 = rawMu[1];
															double traceTempVariable$var117$72_2 = traceTempVariable$var57$72_1;
															for(int n = 0; n < N; n += 1) {
																if(!component[n]) {
																	if((1 == 1)) {
																		if(!component[n]) {
																			double traceTempVariable$componentMu$72_4 = traceTempVariable$var117$72_2;
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										{
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												double var128 = (componentSigma * componentSigma);
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$72_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$72_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$72_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$72_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$72_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
												{
													{
														for(int index$var19$82_1 = 0; index$var19$82_1 < 2; index$var19$82_1 += 1) {
															if((rawMu[0] < rawMu[1])) {
																if((index$var19$82_1 == 1)) {
																	if((rawMu[0] < rawMu[1])) {
																		{
																			{
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$82_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$82_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$82_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$82_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$82_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)));
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
												{
													{
														if(!(rawMu[0] < rawMu[1])) {
															double traceTempVariable$var57$86_1 = rawMu[0];
															double traceTempVariable$var115$86_2 = traceTempVariable$var57$86_1;
															for(int n = 0; n < N; n += 1) {
																if(component[n]) {
																	if((1 == 0)) {
																		if(component[n]) {
																			double traceTempVariable$componentMu$86_4 = traceTempVariable$var115$86_2;
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										{
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												double var128 = (componentSigma * componentSigma);
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$86_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$86_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$86_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$86_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$86_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
													{
														if(!(rawMu[0] < rawMu[1])) {
															double traceTempVariable$var57$88_1 = rawMu[0];
															double traceTempVariable$var117$88_2 = traceTempVariable$var57$88_1;
															for(int n = 0; n < N; n += 1) {
																if(!component[n]) {
																	if((1 == 1)) {
																		if(!component[n]) {
																			double traceTempVariable$componentMu$88_4 = traceTempVariable$var117$88_2;
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										{
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												double var128 = (componentSigma * componentSigma);
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$88_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$88_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$88_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$88_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$88_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
												{
													{
														for(int index$var19$98_1 = 0; index$var19$98_1 < 2; index$var19$98_1 += 1) {
															if(!(rawMu[0] < rawMu[1])) {
																if((index$var19$98_1 == 0)) {
																	if(!(rawMu[0] < rawMu[1])) {
																		{
																			{
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$98_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$98_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$98_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$98_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$98_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)));
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
										}
									}
								}
								{
									if((var19 == 1)) {
										if(!guard$sample20if61) {
											guard$sample20if61 = true;
											{
												{
													{
														if((rawMu[0] < rawMu[1])) {
															double traceTempVariable$var57$71_1 = rawMu[1];
															double traceTempVariable$var115$71_2 = traceTempVariable$var57$71_1;
															for(int n = 0; n < N; n += 1) {
																if(component[n]) {
																	if((1 == 0)) {
																		if(component[n]) {
																			double traceTempVariable$componentMu$71_4 = traceTempVariable$var115$71_2;
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										{
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												double var128 = (componentSigma * componentSigma);
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$71_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$71_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$71_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$71_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$71_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
													{
														if((rawMu[0] < rawMu[1])) {
															double traceTempVariable$var57$73_1 = rawMu[1];
															double traceTempVariable$var117$73_2 = traceTempVariable$var57$73_1;
															for(int n = 0; n < N; n += 1) {
																if(!component[n]) {
																	if((1 == 1)) {
																		if(!component[n]) {
																			double traceTempVariable$componentMu$73_4 = traceTempVariable$var117$73_2;
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										{
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												double var128 = (componentSigma * componentSigma);
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$73_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$73_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$73_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$73_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$73_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
												{
													{
														for(int index$var19$83_1 = 0; index$var19$83_1 < 2; index$var19$83_1 += 1) {
															if((rawMu[0] < rawMu[1])) {
																if((index$var19$83_1 == 1)) {
																	if((rawMu[0] < rawMu[1])) {
																		{
																			{
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$83_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$83_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$83_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$83_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$83_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)));
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
												{
													{
														if(!(rawMu[0] < rawMu[1])) {
															double traceTempVariable$var57$87_1 = rawMu[0];
															double traceTempVariable$var115$87_2 = traceTempVariable$var57$87_1;
															for(int n = 0; n < N; n += 1) {
																if(component[n]) {
																	if((1 == 0)) {
																		if(component[n]) {
																			double traceTempVariable$componentMu$87_4 = traceTempVariable$var115$87_2;
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										{
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												double var128 = (componentSigma * componentSigma);
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$87_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$87_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$87_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$87_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$87_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
													{
														if(!(rawMu[0] < rawMu[1])) {
															double traceTempVariable$var57$89_1 = rawMu[0];
															double traceTempVariable$var117$89_2 = traceTempVariable$var57$89_1;
															for(int n = 0; n < N; n += 1) {
																if(!component[n]) {
																	if((1 == 1)) {
																		if(!component[n]) {
																			double traceTempVariable$componentMu$89_4 = traceTempVariable$var117$89_2;
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										{
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												double var128 = (componentSigma * componentSigma);
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$89_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$89_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$89_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$89_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$89_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
												{
													{
														for(int index$var19$99_1 = 0; index$var19$99_1 < 2; index$var19$99_1 += 1) {
															if(!(rawMu[0] < rawMu[1])) {
																if((index$var19$99_1 == 0)) {
																	if(!(rawMu[0] < rawMu[1])) {
																		{
																			{
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$99_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$99_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$99_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$99_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$99_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)));
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
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
							double var20 = cv$originalValue;
							{
								{
									{
										rawMu[var19] = var20;
									}
								}
							}
							{
								boolean guard$sample20put43 = false;
								{
									if((var19 == 0)) {
										if(!guard$sample20put43) {
											guard$sample20put43 = true;
											{
												double var39;
												if((rawMu[0] < rawMu[1]))
													var39 = rawMu[0];
												else
													var39 = rawMu[1];
												mu[0] = var39;
											}
										}
									}
								}
								{
									if((var19 == 1)) {
										if(!guard$sample20put43) {
											guard$sample20put43 = true;
											{
												double var39;
												if((rawMu[0] < rawMu[1]))
													var39 = rawMu[0];
												else
													var39 = rawMu[1];
												mu[0] = var39;
											}
										}
									}
								}
								{
									if((rawMu[0] < rawMu[1])) {
										if((var19 == 0)) {
											if((rawMu[0] < rawMu[1])) {
												if(!guard$sample20put43) {
													guard$sample20put43 = true;
													{
														double var39;
														if((rawMu[0] < rawMu[1]))
															var39 = rawMu[0];
														else
															var39 = rawMu[1];
														mu[0] = var39;
													}
												}
											}
										}
									}
								}
								{
									if(!(rawMu[0] < rawMu[1])) {
										if((var19 == 1)) {
											if(!(rawMu[0] < rawMu[1])) {
												if(!guard$sample20put43) {
													guard$sample20put43 = true;
													{
														double var39;
														if((rawMu[0] < rawMu[1]))
															var39 = rawMu[0];
														else
															var39 = rawMu[1];
														mu[0] = var39;
													}
												}
											}
										}
									}
								}
							}
							{
								boolean guard$sample20put63 = false;
								{
									if((var19 == 0)) {
										if(!guard$sample20put63) {
											guard$sample20put63 = true;
											{
												double var57;
												if((rawMu[0] < rawMu[1]))
													var57 = rawMu[1];
												else
													var57 = rawMu[0];
												mu[1] = var57;
											}
										}
									}
								}
								{
									if((var19 == 1)) {
										if(!guard$sample20put63) {
											guard$sample20put63 = true;
											{
												double var57;
												if((rawMu[0] < rawMu[1]))
													var57 = rawMu[1];
												else
													var57 = rawMu[0];
												mu[1] = var57;
											}
										}
									}
								}
								{
									if((rawMu[0] < rawMu[1])) {
										if((var19 == 1)) {
											if((rawMu[0] < rawMu[1])) {
												if(!guard$sample20put63) {
													guard$sample20put63 = true;
													{
														double var57;
														if((rawMu[0] < rawMu[1]))
															var57 = rawMu[1];
														else
															var57 = rawMu[0];
														mu[1] = var57;
													}
												}
											}
										}
									}
								}
								{
									if(!(rawMu[0] < rawMu[1])) {
										if((var19 == 0)) {
											if(!(rawMu[0] < rawMu[1])) {
												if(!guard$sample20put63) {
													guard$sample20put63 = true;
													{
														double var57;
														if((rawMu[0] < rawMu[1]))
															var57 = rawMu[1];
														else
															var57 = rawMu[0];
														mu[1] = var57;
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

	private final void inferSample83(int var78) {
		if(true) {
			constrainedFlag$sample83[((var78 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = sigma[var78];
			double cv$originalProbability = 0.0;
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((constrainedFlag$sample83[((var78 - 0) / 1)] || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						double var79 = cv$proposedValue;
						{
							{
								{
									sigma[var78] = cv$currentValue;
								}
							}
						}
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double var63 = (2.0 * 2.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((((0.0 <= cv$currentValue) && (cv$currentValue <= 1.0E100)) && (0.0 < 1.0E100)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((cv$currentValue - 0.0) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt(var63))) - Gaussian.cdf(((0.0 - 0.0) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY));
						{
							{
								{
									double traceTempVariable$var124$2_1 = cv$currentValue;
									for(int n = 0; n < N; n += 1) {
										if(component[n]) {
											if((var78 == 0)) {
												if(component[n]) {
													double traceTempVariable$componentSigma$2_3 = traceTempVariable$var124$2_1;
													{
														{
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																constrainedFlag$sample83[((var78 - 0) / 1)] = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				{
																					double componentMu;
																					if(component[n])
																						componentMu = mu[0];
																					else
																						componentMu = mu[1];
																					double var128 = (traceTempVariable$componentSigma$2_3 * traceTempVariable$componentSigma$2_3);
																					if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
									double traceTempVariable$var126$3_1 = cv$currentValue;
									for(int n = 0; n < N; n += 1) {
										if(!component[n]) {
											if((var78 == 1)) {
												if(!component[n]) {
													double traceTempVariable$componentSigma$3_3 = traceTempVariable$var126$3_1;
													{
														{
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																constrainedFlag$sample83[((var78 - 0) / 1)] = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				{
																					double componentMu;
																					if(component[n])
																						componentMu = mu[0];
																					else
																						componentMu = mu[1];
																					double var128 = (traceTempVariable$componentSigma$3_3 * traceTempVariable$componentSigma$3_3);
																					if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
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
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
							double var79 = cv$originalValue;
							{
								{
									{
										sigma[var78] = var79;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample88() {
		if(true) {
			constrainedFlag$sample88 = false;
			int cv$sum = 0;
			int cv$count = 0;
			{
				{
					{
						{
							{
								{
									for(int var96 = 0; var96 < N; var96 += 1) {
										boolean cv$sampleConstrained = (fixedFlag$sample101 || constrainedFlag$sample101[((var96 - 0) / 1)]);
										if(cv$sampleConstrained) {
											constrainedFlag$sample88 = true;
											{
												{
													{
														{
															{
																cv$count = (cv$count + 1);
																if(component[var96])
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
			if(constrainedFlag$sample88)
				theta = Conjugates.sampleConjugateBetaBinomial(RNG$, 5.0, 5.0, cv$sum, cv$count);
		}
	}

	private final void logProbabilityValue$sample101() {
		if(!fixedProbFlag$sample101) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var96 = 0; var96 < N; var96 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = component[var96];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= theta) && (theta <= 1.0))?Math.log((cv$sampleValue?theta:(1.0 - theta))):Double.NEGATIVE_INFINITY));
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
			logProbability$componentDistribution = cv$sampleAccumulator;
			logProbability$var97 = cv$sampleAccumulator;
			logProbability$component = (logProbability$component + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample101)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample101 = (fixedFlag$sample101 && fixedFlag$sample88);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var96 = 0; var96 < N; var96 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var97;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$componentDistribution = cv$rvAccumulator;
			logProbability$component = (logProbability$component + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample101)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample138() {
		if(!fixedProbFlag$sample138) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int n = 0; n < N; n += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = y[n];
						{
							{
								double componentMu;
								if(component[n])
									componentMu = mu[0];
								else
									componentMu = mu[1];
								double componentSigma;
								if(component[n])
									componentSigma = sigma[0];
								else
									componentSigma = sigma[1];
								double var128 = (componentSigma * componentSigma);
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
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
				logProbability$sample138[((n - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample138 = ((fixedFlag$sample20 && fixedFlag$sample83) && fixedFlag$sample101);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int n = 0; n < N; n += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample138[((n - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample20() {
		if(!fixedProbFlag$sample20) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var19 = 0; var19 < 2; var19 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = rawMu[var19];
						{
							{
								double var3 = 0.0;
								double var6 = (2.0 * 2.0);
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var3) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
								{
									boolean guard$put21if41 = false;
									{
										if((var19 == 0)) {
											if(!guard$put21if41)
												guard$put21if41 = true;
										}
									}
									{
										if((var19 == 1)) {
											if(!guard$put21if41)
												guard$put21if41 = true;
										}
									}
								}
								{
									boolean guard$put21if61 = false;
									{
										if((var19 == 0)) {
											if(!guard$put21if61)
												guard$put21if61 = true;
										}
									}
									{
										if((var19 == 1)) {
											if(!guard$put21if61)
												guard$put21if61 = true;
										}
									}
								}
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
				logProbability$sample20[((var19 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$mu = false;
				{
					{
						if((rawMu[0] < rawMu[1])) {
							if((var19 == 0)) {
								if((rawMu[0] < rawMu[1])) {
									if(!cv$guard$mu) {
										cv$guard$mu = true;
										logProbability$mu = (logProbability$mu + cv$sampleProbability);
									}
								}
							}
						}
					}
					{
						if(!(rawMu[0] < rawMu[1])) {
							if((var19 == 1)) {
								if(!(rawMu[0] < rawMu[1])) {
									if(!cv$guard$mu) {
										cv$guard$mu = true;
										logProbability$mu = (logProbability$mu + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
				{
					{
						if((rawMu[0] < rawMu[1])) {
							if((var19 == 1)) {
								if((rawMu[0] < rawMu[1])) {
									if(!cv$guard$mu) {
										cv$guard$mu = true;
										logProbability$mu = (logProbability$mu + cv$sampleProbability);
									}
								}
							}
						}
					}
					{
						if(!(rawMu[0] < rawMu[1])) {
							if((var19 == 0)) {
								if(!(rawMu[0] < rawMu[1])) {
									if(!cv$guard$mu) {
										cv$guard$mu = true;
										logProbability$mu = (logProbability$mu + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$rawMu = (logProbability$rawMu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample20 = fixedFlag$sample20;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var19 = 0; var19 < 2; var19 += 1) {
				double cv$sampleValue = logProbability$sample20[((var19 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				boolean cv$guard$mu = false;
				{
					{
						if((rawMu[0] < rawMu[1])) {
							if((var19 == 0)) {
								if((rawMu[0] < rawMu[1])) {
									if(!cv$guard$mu) {
										cv$guard$mu = true;
										logProbability$mu = (logProbability$mu + cv$sampleValue);
									}
								}
							}
						}
					}
					{
						if(!(rawMu[0] < rawMu[1])) {
							if((var19 == 1)) {
								if(!(rawMu[0] < rawMu[1])) {
									if(!cv$guard$mu) {
										cv$guard$mu = true;
										logProbability$mu = (logProbability$mu + cv$sampleValue);
									}
								}
							}
						}
					}
				}
				{
					{
						if((rawMu[0] < rawMu[1])) {
							if((var19 == 1)) {
								if((rawMu[0] < rawMu[1])) {
									if(!cv$guard$mu) {
										cv$guard$mu = true;
										logProbability$mu = (logProbability$mu + cv$sampleValue);
									}
								}
							}
						}
					}
					{
						if(!(rawMu[0] < rawMu[1])) {
							if((var19 == 0)) {
								if(!(rawMu[0] < rawMu[1])) {
									if(!cv$guard$mu) {
										cv$guard$mu = true;
										logProbability$mu = (logProbability$mu + cv$sampleValue);
									}
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$rawMu = (logProbability$rawMu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample83() {
		if(!fixedProbFlag$sample83) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var78 = 0; var78 < 2; var78 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = sigma[var78];
						{
							{
								double var60 = 0.0;
								double var63 = (2.0 * 2.0);
								double var64 = 0.0;
								double var65 = 1.0E100;
								double cv$weightedProbability = (Math.log(1.0) + (((((var64 <= cv$sampleValue) && (cv$sampleValue <= var65)) && (var64 < var65)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var60) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((var65 - var60) / Math.sqrt(var63))) - Gaussian.cdf(((var64 - var60) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY));
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
			logProbability$var79 = cv$sampleAccumulator;
			logProbability$sigma = (logProbability$sigma + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample83)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample83 = fixedFlag$sample83;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var78 = 0; var78 < 2; var78 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var79;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$sigma = (logProbability$sigma + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample83)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample88() {
		if(!fixedProbFlag$sample88) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = theta;
					{
						{
							double var81 = 5.0;
							double var82 = 5.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var81, var82));
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
			logProbability$theta = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample88 = fixedFlag$sample88;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$theta;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void allocateScratch() {
		{
			cv$var97$stateProbabilityGlobal = new double[2];
		}
		{
			int cv$max_var19 = 0;
			cv$max_var19 = Math.max(cv$max_var19, ((2 - 0) / 1));
			guard$sample20if124$global = new boolean[cv$max_var19];
		}
	}

	@Override
	public final void allocator() {
		if(!fixedFlag$sample20) {
			{
				rawMu = new double[2];
			}
		}
		{
			mu = new double[2];
		}
		if(!fixedFlag$sample83) {
			{
				sigma = new double[2];
			}
		}
		if(!fixedFlag$sample101) {
			{
				component = new boolean[length$yObserved];
			}
		}
		{
			y = new double[length$yObserved];
		}
		{
			constrainedFlag$sample101 = new boolean[((((length$yObserved - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample20 = new boolean[((((2 - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample83 = new boolean[((((2 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample20 = new double[((((2 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample138 = new double[((((length$yObserved - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int var19 = 0; var19 < 2; var19 += 1) {
			if(!fixedFlag$sample20)
				rawMu[var19] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		double var39 = 0.0;
		if((rawMu[0] < rawMu[1])) {
			if(!fixedFlag$sample20)
				var39 = rawMu[0];
		} else {
			if(!fixedFlag$sample20)
				var39 = rawMu[1];
		}
		if(!fixedFlag$sample20)
			mu[0] = var39;
		double var57 = 0.0;
		if((rawMu[0] < rawMu[1])) {
			if(!fixedFlag$sample20)
				var57 = rawMu[1];
		} else {
			if(!fixedFlag$sample20)
				var57 = rawMu[0];
		}
		if(!fixedFlag$sample20)
			mu[1] = var57;
		for(int var78 = 0; var78 < 2; var78 += 1) {
			if(!fixedFlag$sample83)
				sigma[var78] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleTruncatedGaussian(RNG$, ((0.0 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((0.0 - 0.0) / Math.sqrt((2.0 * 2.0)))), ((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0)))))) + 0.0);
		}
		if(!fixedFlag$sample88)
			theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
		for(int var96 = 0; var96 < N; var96 += 1) {
			if(!fixedFlag$sample101)
				component[var96] = DistributionSampling.sampleBernoulli(RNG$, theta);
		}
		for(int n = 0; n < N; n += 1) {
			double componentMu;
			if(component[n])
				componentMu = mu[0];
			else
				componentMu = mu[1];
			double componentSigma;
			if(component[n])
				componentSigma = sigma[0];
			else
				componentSigma = sigma[1];
			y[n] = ((Math.sqrt((componentSigma * componentSigma)) * DistributionSampling.sampleGaussian(RNG$)) + componentMu);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		for(int var19 = 0; var19 < 2; var19 += 1) {
			if(!fixedFlag$sample20)
				rawMu[var19] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		double var39;
		if((rawMu[0] < rawMu[1]))
			var39 = rawMu[0];
		else
			var39 = rawMu[1];
		mu[0] = var39;
		double var57;
		if((rawMu[0] < rawMu[1]))
			var57 = rawMu[1];
		else
			var57 = rawMu[0];
		mu[1] = var57;
		for(int var78 = 0; var78 < 2; var78 += 1) {
			if(!fixedFlag$sample83)
				sigma[var78] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleTruncatedGaussian(RNG$, ((0.0 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((0.0 - 0.0) / Math.sqrt((2.0 * 2.0)))), ((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0)))))) + 0.0);
		}
		if(!fixedFlag$sample88)
			theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
		for(int var96 = 0; var96 < N; var96 += 1) {
			if(!fixedFlag$sample101)
				component[var96] = DistributionSampling.sampleBernoulli(RNG$, theta);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		for(int var19 = 0; var19 < 2; var19 += 1) {
			if(!fixedFlag$sample20)
				rawMu[var19] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		double var39;
		if((rawMu[0] < rawMu[1]))
			var39 = rawMu[0];
		else
			var39 = rawMu[1];
		mu[0] = var39;
		double var57;
		if((rawMu[0] < rawMu[1]))
			var57 = rawMu[1];
		else
			var57 = rawMu[0];
		mu[1] = var57;
		for(int var78 = 0; var78 < 2; var78 += 1) {
			if(!fixedFlag$sample83)
				sigma[var78] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleTruncatedGaussian(RNG$, ((0.0 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((0.0 - 0.0) / Math.sqrt((2.0 * 2.0)))), ((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0)))))) + 0.0);
		}
		if(!fixedFlag$sample88)
			theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
		for(int var96 = 0; var96 < N; var96 += 1) {
			if(!fixedFlag$sample101)
				component[var96] = DistributionSampling.sampleBernoulli(RNG$, theta);
		}
		for(int n = 0; n < N; n += 1) {
			double componentMu;
			if(component[n])
				componentMu = mu[0];
			else
				componentMu = mu[1];
			double componentSigma;
			if(component[n])
				componentSigma = sigma[0];
			else
				componentSigma = sigma[1];
			y[n] = ((Math.sqrt((componentSigma * componentSigma)) * DistributionSampling.sampleGaussian(RNG$)) + componentMu);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var19 = 0; var19 < 2; var19 += 1) {
			if(!fixedFlag$sample20)
				rawMu[var19] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		double var39 = 0.0;
		if((rawMu[0] < rawMu[1])) {
			if(!fixedFlag$sample20)
				var39 = rawMu[0];
		} else {
			if(!fixedFlag$sample20)
				var39 = rawMu[1];
		}
		if(!fixedFlag$sample20)
			mu[0] = var39;
		double var57 = 0.0;
		if((rawMu[0] < rawMu[1])) {
			if(!fixedFlag$sample20)
				var57 = rawMu[1];
		} else {
			if(!fixedFlag$sample20)
				var57 = rawMu[0];
		}
		if(!fixedFlag$sample20)
			mu[1] = var57;
		for(int var78 = 0; var78 < 2; var78 += 1) {
			if(!fixedFlag$sample83)
				sigma[var78] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleTruncatedGaussian(RNG$, ((0.0 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((0.0 - 0.0) / Math.sqrt((2.0 * 2.0)))), ((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0)))))) + 0.0);
		}
		if(!fixedFlag$sample88)
			theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
		for(int var96 = 0; var96 < N; var96 += 1) {
			if(!fixedFlag$sample101)
				component[var96] = DistributionSampling.sampleBernoulli(RNG$, theta);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		for(int var19 = 0; var19 < 2; var19 += 1) {
			if(!fixedFlag$sample20)
				rawMu[var19] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		double var39;
		if((rawMu[0] < rawMu[1]))
			var39 = rawMu[0];
		else
			var39 = rawMu[1];
		mu[0] = var39;
		double var57;
		if((rawMu[0] < rawMu[1]))
			var57 = rawMu[1];
		else
			var57 = rawMu[0];
		mu[1] = var57;
		for(int var78 = 0; var78 < 2; var78 += 1) {
			if(!fixedFlag$sample83)
				sigma[var78] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleTruncatedGaussian(RNG$, ((0.0 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((0.0 - 0.0) / Math.sqrt((2.0 * 2.0)))), ((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0)))))) + 0.0);
		}
		if(!fixedFlag$sample88)
			theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
		for(int var96 = 0; var96 < N; var96 += 1) {
			if(!fixedFlag$sample101)
				component[var96] = DistributionSampling.sampleBernoulli(RNG$, theta);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var19 = 0; var19 < 2; var19 += 1) {
				if(!fixedFlag$sample20)
					inferSample20(var19);
			}
			for(int var78 = 0; var78 < 2; var78 += 1) {
				if(!fixedFlag$sample83)
					inferSample83(var78);
			}
			if(!fixedFlag$sample88)
				inferSample88();
			for(int var96 = 0; var96 < N; var96 += 1) {
				if(!fixedFlag$sample101)
					inferSample101(var96);
			}
		} else {
			for(int var96 = (N - ((((N - 1) - 0) % 1) + 1)); var96 >= ((0 - 1) + 1); var96 -= 1) {
				if(!fixedFlag$sample101)
					inferSample101(var96);
			}
			if(!fixedFlag$sample88)
				inferSample88();
			for(int var78 = (2 - ((((2 - 1) - 0) % 1) + 1)); var78 >= ((0 - 1) + 1); var78 -= 1) {
				if(!fixedFlag$sample83)
					inferSample83(var78);
			}
			for(int var19 = (2 - ((((2 - 1) - 0) % 1) + 1)); var19 >= ((0 - 1) + 1); var19 -= 1) {
				if(!fixedFlag$sample20)
					inferSample20(var19);
			}
		}
		system$gibbsForward = !system$gibbsForward;
		for(int var19 = 0; var19 < 2; var19 += 1) {
			if(!constrainedFlag$sample20[((var19 - 0) / 1)])
				drawValueSample20(var19);
		}
		for(int var78 = 0; var78 < 2; var78 += 1) {
			if(!constrainedFlag$sample83[((var78 - 0) / 1)])
				drawValueSample83(var78);
		}
		if(!constrainedFlag$sample88)
			drawValueSample88();
		for(int var96 = 0; var96 < N; var96 += 1) {
			if(!constrainedFlag$sample101[((var96 - 0) / 1)])
				drawValueSample101(var96);
		}
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$rawMu = 0.0;
		logProbability$mu = 0.0;
		if(!fixedProbFlag$sample20) {
			for(int var19 = 0; var19 < 2; var19 += 1)
				logProbability$sample20[((var19 - 0) / 1)] = Double.NaN;
		}
		logProbability$sigma = 0.0;
		if(!fixedProbFlag$sample83)
			logProbability$var79 = Double.NaN;
		if(!fixedProbFlag$sample88)
			logProbability$theta = Double.NaN;
		logProbability$componentDistribution = Double.NaN;
		logProbability$component = 0.0;
		if(!fixedProbFlag$sample101)
			logProbability$var97 = Double.NaN;
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample138) {
			for(int n = 0; n < N; n += 1)
				logProbability$sample138[((n - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		N = length$yObserved;
		for(int index$constrainedFlag$sample101$1 = 0; index$constrainedFlag$sample101$1 < constrainedFlag$sample101.length; index$constrainedFlag$sample101$1 += 1)
			constrainedFlag$sample101[index$constrainedFlag$sample101$1] = true;
		for(int index$constrainedFlag$sample20$1 = 0; index$constrainedFlag$sample20$1 < constrainedFlag$sample20.length; index$constrainedFlag$sample20$1 += 1)
			constrainedFlag$sample20[index$constrainedFlag$sample20$1] = true;
		for(int index$constrainedFlag$sample83$1 = 0; index$constrainedFlag$sample83$1 < constrainedFlag$sample83.length; index$constrainedFlag$sample83$1 += 1)
			constrainedFlag$sample83[index$constrainedFlag$sample83$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		if(fixedFlag$sample83)
			logProbabilityValue$sample83();
		if(fixedFlag$sample88)
			logProbabilityValue$sample88();
		if(fixedFlag$sample101)
			logProbabilityValue$sample101();
		logProbabilityValue$sample138();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample83();
		logProbabilityValue$sample88();
		logProbabilityValue$sample101();
		logProbabilityValue$sample138();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample83();
		logProbabilityValue$sample88();
		logProbabilityValue$sample101();
		logProbabilityValue$sample138();
	}

	@Override
	public final void propagateObservedValues() {
		double[] cv$source1 = yObserved;
		double[] cv$target1 = y;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		double var39;
		if((rawMu[0] < rawMu[1]))
			var39 = rawMu[0];
		else
			var39 = rawMu[1];
		mu[0] = var39;
		double var57;
		if((rawMu[0] < rawMu[1]))
			var57 = rawMu[1];
		else
			var57 = rawMu[0];
		mu[1] = var57;
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2026, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model LowDimMix(double[] yObserved) {\n"
		     + "    int N = yObserved.length;\n"
		     + "\n"
		     + "    // Stan parameter: ordered[2] mu; prior: mu ~ normal(0, 2)\n"
		     + "    // Sampling two unconstrained normal values and sorting them gives the same ordered support up to\n"
		     + "    // the constant normalisation factor for the ordered constraint.\n"
		     + "    double[] rawMu = gaussian(0.0, 2.0 * 2.0).sample(2);\n"
		     + "    double[] mu = new double[2];\n"
		     + "    mu[0] = rawMu[0] < rawMu[1] ? rawMu[0] : rawMu[1];\n"
		     + "    mu[1] = rawMu[0] < rawMu[1] ? rawMu[1] : rawMu[0];\n"
		     + "\n"
		     + "    // Stan parameter: array[2] real<lower=0> sigma; prior: sigma ~ normal(0, 2)\n"
		     + "    double[] sigma = truncatedGaussian(0.0, 2.0 * 2.0, 0.0, 1.0e100).sample(2);\n"
		     + "\n"
		     + "    // Stan parameter: real<lower=0, upper=1> theta; prior: theta ~ beta(5, 5)\n"
		     + "    double theta = beta(5.0, 5.0).sample();\n"
		     + "\n"
		     + "    // Stan likelihood:\n"
		     + "    // target += log_mix(theta, normal_lpdf(y[n] | mu[1], sigma[1]),\n"
		     + "    //                   normal_lpdf(y[n] | mu[2], sigma[2]));\n"
		     + "    // In Sandwood, represent the same two-component mixture with explicit latent component indicators.\n"
		     + "    Bernoulli componentDistribution = bernoulli(theta);\n"
		     + "    boolean[] component = componentDistribution.sample(N);\n"
		     + "    double[] y = new double[N];\n"
		     + "\n"
		     + "    for(int n = 0; n < N; n++) {\n"
		     + "        double componentMu = component[n] ? mu[0] : mu[1];\n"
		     + "        double componentSigma = component[n] ? sigma[0] : sigma[1];\n"
		     + "        y[n] = gaussian(componentMu, componentSigma * componentSigma).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    y.observe(yObserved);\n"
		     + "}\n"
		     + "";
	}
}