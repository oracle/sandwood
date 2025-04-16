package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK14$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip1CoinMK14$CoreInterface {
	private double b;
	private double bias;
	private double[] c;
	private boolean fixedFlag$sample8 = false;
	private boolean fixedProbFlag$sample37 = false;
	private boolean fixedProbFlag$sample8 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private boolean guard1;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$c;
	private double logProbability$flips;
	private double logProbability$var35;
	private double logProbability$var7;
	private int samples;
	private boolean system$gibbsForward = true;

	public Flip1CoinMK14$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$b() {
		return b;
	}

	@Override
	public final void set$b(double cv$value) {
		b = cv$value;
		fixedProbFlag$sample8 = false;
		fixedProbFlag$sample37 = false;
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final boolean get$fixedFlag$sample8() {
		return fixedFlag$sample8;
	}

	@Override
	public final void set$fixedFlag$sample8(boolean cv$value) {
		fixedFlag$sample8 = cv$value;
		fixedProbFlag$sample8 = (fixedFlag$sample8 && fixedProbFlag$sample8);
		fixedProbFlag$sample37 = (fixedFlag$sample8 && fixedProbFlag$sample37);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final boolean[] get$flipsMeasured() {
		return flipsMeasured;
	}

	@Override
	public final void set$flipsMeasured(boolean[] cv$value) {
		flipsMeasured = cv$value;
	}

	@Override
	public final boolean get$guard1() {
		return guard1;
	}

	@Override
	public final void set$guard1(boolean cv$value) {
		guard1 = cv$value;
	}

	@Override
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	@Override
	public final void set$length$flipsMeasured(int cv$value) {
		length$flipsMeasured = cv$value;
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
	public final double get$logProbability$b() {
		return logProbability$b;
	}

	@Override
	public final double get$logProbability$bernoulli() {
		return logProbability$bernoulli;
	}

	@Override
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$flips() {
		return logProbability$flips;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	private final void logProbabilityValue$sample37() {
		if(!fixedProbFlag$sample37) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var34 = 0; var34 < samples; var34 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[var34];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?bias:(1.0 - bias))));
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var35 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample37 = fixedFlag$sample8;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var35;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bernoulli = cv$rvAccumulator;
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample8() {
		if(!fixedProbFlag$sample8) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = b;
				{
					{
						double var4 = 1.0;
						double var6 = 1.0;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var4, var6));
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
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var7 = cv$sampleAccumulator;
			logProbability$b = cv$sampleProbability;
			boolean cv$guard$c = false;
			boolean cv$guard$bias = false;
			{
				if(!guard1) {
					if(!cv$guard$c) {
						cv$guard$c = true;
						logProbability$c = (logProbability$c + cv$accumulator);
					}
				}
			}
			{
				if(guard1) {
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						logProbability$bias = (logProbability$bias + cv$accumulator);
					}
				}
				if(!guard1) {
					if((0 == 0)) {
						if(!guard1) {
							if(!cv$guard$bias) {
								cv$guard$bias = true;
								logProbability$bias = (logProbability$bias + cv$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample8)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample8 = fixedFlag$sample8;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$b;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var7 = cv$rvAccumulator;
			boolean cv$guard$c = false;
			boolean cv$guard$bias = false;
			{
				if(!guard1) {
					if(!cv$guard$c) {
						cv$guard$c = true;
						logProbability$c = (logProbability$c + cv$accumulator);
					}
				}
			}
			{
				if(guard1) {
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						logProbability$bias = (logProbability$bias + cv$accumulator);
					}
				}
				if(!guard1) {
					if((0 == 0)) {
						if(!guard1) {
							if(!cv$guard$bias) {
								cv$guard$bias = true;
								logProbability$bias = (logProbability$bias + cv$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample8)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample8() {
		if(true) {
			int cv$numNumStates = 0;
			{
				cv$numNumStates = Math.max(cv$numNumStates, 2);
			}
			double cv$originalValue = b;
			double cv$originalProbability = 0.0;
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				double cv$currentValue;
				if((cv$valuePos == 0))
					cv$currentValue = cv$originalValue;
				else {
					cv$currentValue = cv$proposedValue;
					{
						b = cv$proposedValue;
						{
							if(!guard1) {
								{
									c[0] = (cv$currentValue / 2);
								}
							}
						}
						{
							if(guard1) {
								{
									if(guard1)
										bias = cv$currentValue;
									else
										bias = c[0];
								}
							}
							if(!guard1) {
								if((0 == 0)) {
									if(!guard1) {
										{
											if(guard1)
												bias = cv$currentValue;
											else
												bias = c[0];
										}
									}
								}
							}
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$temp$0$var4;
					{
						cv$temp$0$var4 = 1.0;
					}
					double cv$temp$1$var6;
					{
						cv$temp$1$var6 = 1.0;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$currentValue, cv$temp$0$var4, cv$temp$1$var6));
					{
						{
							if(guard1) {
								double traceTempVariable$bias$4_1 = cv$currentValue;
								double traceTempVariable$b$4_2 = cv$currentValue;
								{
									for(int var34 = 0; var34 < samples; var34 += 1) {
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											{
												{
													{
														double cv$temp$2$bias;
														{
															cv$temp$2$bias = traceTempVariable$bias$4_1;
														}
														if(((Math.log(1.0) + Math.log((flips[var34]?cv$temp$2$bias:(1.0 - cv$temp$2$bias)))) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((flips[var34]?cv$temp$2$bias:(1.0 - cv$temp$2$bias)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((flips[var34]?cv$temp$2$bias:(1.0 - cv$temp$2$bias))));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((flips[var34]?cv$temp$2$bias:(1.0 - cv$temp$2$bias)))))) + 1)) + (Math.log(1.0) + Math.log((flips[var34]?cv$temp$2$bias:(1.0 - cv$temp$2$bias)))));
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
							if(!guard1) {
								double traceTempVariable$b$5_1 = cv$currentValue;
								double traceTempVariable$var21$5_2 = (traceTempVariable$b$5_1 / 2);
								if((0 == 0)) {
									if(!guard1) {
										double traceTempVariable$bias$5_3 = traceTempVariable$var21$5_2;
										{
											for(int var34 = 0; var34 < samples; var34 += 1) {
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													{
														{
															{
																double cv$temp$3$bias;
																{
																	cv$temp$3$bias = traceTempVariable$bias$5_3;
																}
																if(((Math.log(1.0) + Math.log((flips[var34]?cv$temp$3$bias:(1.0 - cv$temp$3$bias)))) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((flips[var34]?cv$temp$3$bias:(1.0 - cv$temp$3$bias)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((flips[var34]?cv$temp$3$bias:(1.0 - cv$temp$3$bias))));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((flips[var34]?cv$temp$3$bias:(1.0 - cv$temp$3$bias)))))) + 1)) + (Math.log(1.0) + Math.log((flips[var34]?cv$temp$3$bias:(1.0 - cv$temp$3$bias)))));
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
			}
			double cv$ratio = (cv$proposedProbability - cv$originalProbability);
			if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
				b = cv$originalValue;
				{
					if(!guard1) {
						{
							c[0] = (b / 2);
						}
					}
				}
				{
					if(guard1) {
						{
							if(guard1)
								bias = b;
							else
								bias = c[0];
						}
					}
					if(!guard1) {
						if((0 == 0)) {
							if(!guard1) {
								{
									if(guard1)
										bias = b;
									else
										bias = c[0];
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		{
			if(!guard1)
				c = new double[1];
		}
		{
			flips = new boolean[length$flipsMeasured];
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample8)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(guard1) {
			if(!fixedFlag$sample8)
				bias = b;
		} else {
			if(!fixedFlag$sample8)
				c[0] = (b / 2);
			if(!fixedFlag$sample8)
				bias = c[0];
		}
		for(int var34 = 0; var34 < samples; var34 += 1)
			flips[var34] = DistributionSampling.sampleBernoulli(RNG$, bias);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample8)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(guard1) {
			if(!fixedFlag$sample8)
				bias = b;
		} else {
			if(!fixedFlag$sample8)
				c[0] = (b / 2);
			if(!fixedFlag$sample8)
				bias = c[0];
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample8)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(guard1) {
			if(!fixedFlag$sample8)
				bias = b;
		} else {
			if(!fixedFlag$sample8)
				c[0] = (b / 2);
			if(!fixedFlag$sample8)
				bias = c[0];
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample8)
				sample8();
		} else {
			if(!fixedFlag$sample8)
				sample8();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		samples = length$flipsMeasured;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var7 = 0.0;
		logProbability$c = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample8)
			logProbability$b = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample37)
			logProbability$var35 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample8)
			logProbabilityValue$sample8();
		logProbabilityValue$sample37();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample8();
		logProbabilityValue$sample37();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample8();
		logProbabilityValue$sample37();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample8)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(guard1) {
			if(!fixedFlag$sample8)
				bias = b;
		} else {
			if(!fixedFlag$sample8)
				c[0] = (b / 2);
			if(!fixedFlag$sample8)
				bias = c[0];
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i = (samples - ((((samples - 1) - 0) % 1) + 1)); i >= ((0 - 1) + 1); i -= 1)
			flips[i] = flipsMeasured[i];
	}

	@Override
	public final void setIntermediates() {
		if(guard1) {
			if(fixedFlag$sample8)
				bias = b;
		} else {
			if(fixedFlag$sample8)
				c[0] = (b / 2);
			if(fixedFlag$sample8)
				bias = c[0];
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
		     + "public model Flip1CoinMK14(boolean[] flipsMeasured, boolean guard1) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    double b = beta(1.0, 1).sample();\n"
		     + "    double bias;\n"
		     + "    if(guard1)\n"
		     + "      bias = b;\n"
		     + "    else {\n"
		     + "      double[] c = new double[1];\n"
		     + "      c[0] = b/2;\n"
		     + "      bias = c[0];\n"
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