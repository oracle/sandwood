package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ParallelMK1$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements ParallelMK1$CoreInterface {
	private boolean fixedFlag$sample20 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample24 = false;
	private double[] generated;
	private boolean[] guard$sample20gaussian23$global;
	private double[] indirection;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$generated;
	private double logProbability$indirection;
	private double logProbability$sample;
	private double[] logProbability$sample20;
	private double[] logProbability$sample24;
	private double[] logProbability$var19;
	private double[] logProbability$var23;
	private double[] observed;
	private double[] sample;
	private boolean system$gibbsForward = true;

	public ParallelMK1$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	@Override
	public final void set$fixedFlag$sample20(boolean cv$value) {
		fixedFlag$sample20 = cv$value;
		fixedProbFlag$sample20 = (fixedFlag$sample20 && fixedProbFlag$sample20);
		fixedProbFlag$sample24 = (fixedFlag$sample20 && fixedProbFlag$sample24);
	}

	@Override
	public final double[] get$generated() {
		return generated;
	}

	@Override
	public final double[] get$indirection() {
		return indirection;
	}

	@Override
	public final void set$indirection(double[] cv$value) {
		indirection = cv$value;
	}

	@Override
	public final int get$length$observed() {
		return length$observed;
	}

	@Override
	public final void set$length$observed(int cv$value) {
		length$observed = cv$value;
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
	public final double get$logProbability$generated() {
		return logProbability$generated;
	}

	@Override
	public final double get$logProbability$indirection() {
		return logProbability$indirection;
	}

	@Override
	public final double get$logProbability$sample() {
		return logProbability$sample;
	}

	@Override
	public final double[] get$observed() {
		return observed;
	}

	@Override
	public final void set$observed(double[] cv$value) {
		observed = cv$value;
	}

	@Override
	public final double[] get$sample() {
		return sample;
	}

	@Override
	public final void set$sample(double[] cv$value) {
		sample = cv$value;
	}

	private final void logProbabilityValue$sample20() {
		if(!fixedProbFlag$sample20) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = sample[((i - 0) / 1)];
					{
						{
							double var17 = 0.0;
							double var18 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + (((var17 <= cv$sampleValue) && (cv$sampleValue <= var18))?(-Math.log((var18 - var17))):Double.NEGATIVE_INFINITY));
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
				logProbability$var19[((i - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample20[((i - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$indirection = false;
				{
					if(!cv$guard$indirection) {
						cv$guard$indirection = true;
						logProbability$indirection = (logProbability$indirection + cv$sampleProbability);
					}
				}
			}
			logProbability$sample = (logProbability$sample + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample20 = fixedFlag$sample20;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample20[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var19[((i - 0) / 1)] = cv$rvAccumulator;
				boolean cv$guard$indirection = false;
				{
					if(!cv$guard$indirection) {
						cv$guard$indirection = true;
						logProbability$indirection = (logProbability$indirection + cv$sampleValue);
					}
				}
			}
			logProbability$sample = (logProbability$sample + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample24() {
		if(!fixedProbFlag$sample24) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = generated[i];
					{
						{
							double var22 = indirection[i];
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - sample[((i - 0) / 1)]) / Math.sqrt(var22))) - (0.5 * Math.log(var22))));
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
				logProbability$var23[((i - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample24[((i - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$generated = (logProbability$generated + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample24 = fixedFlag$sample20;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample24[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var23[((i - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$generated = (logProbability$generated + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample20(int i) {
		if(true) {
			int cv$numNumStates = 0;
			{
				cv$numNumStates = Math.max(cv$numNumStates, 2);
			}
			double cv$originalValue = sample[((i - 0) / 1)];
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
						sample[((i - 0) / 1)] = cv$proposedValue;
						{
							{
								indirection[i] = cv$currentValue;
							}
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$temp$0$var17;
					{
						cv$temp$0$var17 = 0.0;
					}
					double cv$temp$1$var18;
					{
						cv$temp$1$var18 = 1.0;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((cv$temp$0$var17 <= cv$currentValue) && (cv$currentValue <= cv$temp$1$var18))?(-Math.log((cv$temp$1$var18 - cv$temp$0$var17))):Double.NEGATIVE_INFINITY));
					{
						{
							boolean[] guard$sample20gaussian23 = guard$sample20gaussian23$global;
							for(int index$i$3_1 = 0; index$i$3_1 < length$observed; index$i$3_1 += 1) {
								if((i == index$i$3_1))
									guard$sample20gaussian23[((i - 0) / 1)] = false;
							}
							guard$sample20gaussian23[((i - 0) / 1)] = false;
							if(!guard$sample20gaussian23[((i - 0) / 1)]) {
								guard$sample20gaussian23[((i - 0) / 1)] = true;
								{
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double cv$temp$2$sample;
													{
														cv$temp$2$sample = cv$currentValue;
													}
													double cv$temp$3$var22;
													{
														double var22 = indirection[i];
														cv$temp$3$var22 = var22;
													}
													if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[i] - cv$temp$2$sample) / Math.sqrt(cv$temp$3$var22))) - (0.5 * Math.log(cv$temp$3$var22)))) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[i] - cv$temp$2$sample) / Math.sqrt(cv$temp$3$var22))) - (0.5 * Math.log(cv$temp$3$var22)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[i] - cv$temp$2$sample) / Math.sqrt(cv$temp$3$var22))) - (0.5 * Math.log(cv$temp$3$var22))));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[i] - cv$temp$2$sample) / Math.sqrt(cv$temp$3$var22))) - (0.5 * Math.log(cv$temp$3$var22)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[i] - cv$temp$2$sample) / Math.sqrt(cv$temp$3$var22))) - (0.5 * Math.log(cv$temp$3$var22)))));
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
							double traceTempVariable$var22$5_1 = cv$currentValue;
							for(int index$i$5_2 = 0; index$i$5_2 < length$observed; index$i$5_2 += 1) {
								if((i == index$i$5_2)) {
									if(!guard$sample20gaussian23[((i - 0) / 1)]) {
										guard$sample20gaussian23[((i - 0) / 1)] = true;
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$4$sample;
															{
																cv$temp$4$sample = cv$currentValue;
															}
															double cv$temp$5$var22;
															{
																double var22 = traceTempVariable$var22$5_1;
																cv$temp$5$var22 = var22;
															}
															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[index$i$5_2] - cv$temp$4$sample) / Math.sqrt(cv$temp$5$var22))) - (0.5 * Math.log(cv$temp$5$var22)))) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[index$i$5_2] - cv$temp$4$sample) / Math.sqrt(cv$temp$5$var22))) - (0.5 * Math.log(cv$temp$5$var22)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[index$i$5_2] - cv$temp$4$sample) / Math.sqrt(cv$temp$5$var22))) - (0.5 * Math.log(cv$temp$5$var22))));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[index$i$5_2] - cv$temp$4$sample) / Math.sqrt(cv$temp$5$var22))) - (0.5 * Math.log(cv$temp$5$var22)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[index$i$5_2] - cv$temp$4$sample) / Math.sqrt(cv$temp$5$var22))) - (0.5 * Math.log(cv$temp$5$var22)))));
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
				sample[((i - 0) / 1)] = cv$originalValue;
				{
					{
						indirection[i] = sample[((i - 0) / 1)];
					}
				}
			}
		}
	}

	@Override
	public final void allocateScratch() {
		int cv$max_i = 0;
		cv$max_i = Math.max(cv$max_i, ((length$observed - 0) / 1));
		guard$sample20gaussian23$global = new boolean[cv$max_i];
	}

	@Override
	public final void allocator() {
		{
			generated = new double[length$observed];
		}
		{
			indirection = new double[length$observed];
		}
		if(!fixedFlag$sample20) {
			{
				sample = new double[((((length$observed - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$var19 = new double[((((length$observed - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample20 = new double[((((length$observed - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var23 = new double[((((length$observed - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample24 = new double[((((length$observed - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int i = 0; i < length$observed; i += 1) {
			if(!fixedFlag$sample20)
				sample[((i - 0) / 1)] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			if(!fixedFlag$sample20)
				indirection[i] = sample[((i - 0) / 1)];
			generated[i] = ((Math.sqrt(indirection[i]) * DistributionSampling.sampleGaussian(RNG$)) + sample[((i - 0) / 1)]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int i = 0; i < length$observed; i += 1) {
			if(!fixedFlag$sample20)
				sample[((i - 0) / 1)] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			if(!fixedFlag$sample20)
				indirection[i] = sample[((i - 0) / 1)];
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int i = 0; i < length$observed; i += 1) {
			if(!fixedFlag$sample20)
				sample[((i - 0) / 1)] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			if(!fixedFlag$sample20)
				indirection[i] = sample[((i - 0) / 1)];
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int i = 0; i < length$observed; i += 1) {
				if(!fixedFlag$sample20)
					sample20(i);
			}
		} else {
			for(int i = (length$observed - ((((length$observed - 1) - 0) % 1) + 1)); i >= ((0 - 1) + 1); i -= 1) {
				if(!fixedFlag$sample20)
					sample20(i);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		for(int i = 0; i < length$observed; i += 1)
			logProbability$var19[((i - 0) / 1)] = 0.0;
		logProbability$sample = 0.0;
		logProbability$indirection = 0.0;
		if(!fixedProbFlag$sample20) {
			for(int i = 0; i < length$observed; i += 1)
				logProbability$sample20[((i - 0) / 1)] = 0.0;
		}
		for(int i = 0; i < length$observed; i += 1)
			logProbability$var23[((i - 0) / 1)] = 0.0;
		logProbability$generated = 0.0;
		if(!fixedProbFlag$sample24) {
			for(int i = 0; i < length$observed; i += 1)
				logProbability$sample24[((i - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		logProbabilityValue$sample24();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample24();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample24();
	}

	@Override
	public final void logProbabilityGeneration() {
		for(int i = 0; i < length$observed; i += 1) {
			if(!fixedFlag$sample20)
				sample[((i - 0) / 1)] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			if(!fixedFlag$sample20)
				indirection[i] = sample[((i - 0) / 1)];
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		double[] cv$source1 = observed;
		double[] cv$target1 = generated;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		for(int i = 0; i < length$observed; i += 1) {
			if(fixedFlag$sample20)
				indirection[i] = sample[((i - 0) / 1)];
		}
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
		     + "public model ParallelMK1(double[] observed) {\n"
		     + "    double[] generated = new double[observed.length];\n"
		     + "    double[] indirection = new double[observed.length];\n"
		     + "\n"
		     + "    for(int i=0; i<observed.length; i++) {\n"
		     + "        double sample = uniform(0.0, 1.0).sample();\n"
		     + "        indirection[i] = sample;\n"
		     + "        generated[i] = gaussian(sample, indirection[i]).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    generated.observe(observed);\n"
		     + "}";
	}
}