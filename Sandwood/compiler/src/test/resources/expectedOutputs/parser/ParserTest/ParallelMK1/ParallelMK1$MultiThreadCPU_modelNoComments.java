package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ParallelMK1$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements ParallelMK1$CoreInterface {
	private boolean fixedFlag$sample26 = false;
	private boolean fixedFlag$sample30 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean fixedProbFlag$sample30 = false;
	private double[] generated;
	private boolean[][] guard$sample26gaussian29$global;
	private double[] indirection;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$generated;
	private double logProbability$indirection;
	private double logProbability$sample;
	private double[] logProbability$sample26;
	private double[] logProbability$sample30;
	private double[] logProbability$var21;
	private double[] logProbability$var25;
	private double[] observed;
	private double[] sample;
	private boolean setFlag$generated = false;
	private boolean setFlag$indirection = false;
	private boolean setFlag$sample = false;
	private boolean system$gibbsForward = true;

	public ParallelMK1$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	@Override
	public final void set$fixedFlag$sample26(boolean cv$value) {
		fixedFlag$sample26 = cv$value;
		fixedProbFlag$sample26 = (fixedFlag$sample26 && fixedProbFlag$sample26);
		fixedProbFlag$sample30 = (fixedFlag$sample26 && fixedProbFlag$sample30);
	}

	@Override
	public final boolean get$fixedFlag$sample30() {
		return fixedFlag$sample30;
	}

	@Override
	public final void set$fixedFlag$sample30(boolean cv$value) {
		fixedFlag$sample30 = cv$value;
		fixedProbFlag$sample30 = (fixedFlag$sample30 && fixedProbFlag$sample30);
	}

	@Override
	public final double[] get$generated() {
		return generated;
	}

	@Override
	public final void set$generated(double[] cv$value) {
		generated = cv$value;
		setFlag$generated = true;
		fixedProbFlag$sample30 = false;
	}

	@Override
	public final double[] get$indirection() {
		return indirection;
	}

	@Override
	public final void set$indirection(double[] cv$value) {
		indirection = cv$value;
		setFlag$indirection = true;
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
		setFlag$sample = true;
	}

	private final void logProbabilityValue$sample26() {
		if(!fixedProbFlag$sample26) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = sample[((i - 0) / 1)];
					{
						{
							double var19 = 0.0;
							double var20 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + (((var19 <= cv$sampleValue) && (cv$sampleValue <= var20))?(-Math.log((var20 - var19))):Double.NEGATIVE_INFINITY));
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
				logProbability$var21[((i - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample26[((i - 0) / 1)] = cv$sampleProbability;
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
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample26 = fixedFlag$sample26;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample26[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var21[((i - 0) / 1)] = cv$rvAccumulator;
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
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample30() {
		if(!fixedProbFlag$sample30) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = generated[i];
					{
						{
							double var24 = indirection[i];
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - sample[((i - 0) / 1)]) / Math.sqrt(var24))) - (0.5 * Math.log(var24))));
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
				logProbability$var25[((i - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample30[((i - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$generated = (logProbability$generated + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample30 = (fixedFlag$sample30 && fixedFlag$sample26);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample30[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var25[((i - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$generated = (logProbability$generated + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample26(int i, int threadID$cv$i, Rng RNG$) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = sample[((i - 0) / 1)];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
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
				double cv$temp$0$var19;
				{
					cv$temp$0$var19 = 0.0;
				}
				double cv$temp$1$var20;
				{
					cv$temp$1$var20 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((cv$temp$0$var19 <= cv$currentValue) && (cv$currentValue <= cv$temp$1$var20))?(-Math.log((cv$temp$1$var20 - cv$temp$0$var19))):Double.NEGATIVE_INFINITY));
				{
					{
						boolean[] guard$sample26gaussian29 = guard$sample26gaussian29$global[threadID$cv$i];
						for(int index$i$3_1 = 0; index$i$3_1 < length$observed; index$i$3_1 += 1) {
							if((i == index$i$3_1))
								guard$sample26gaussian29[((i - 0) / 1)] = false;
						}
						guard$sample26gaussian29[((i - 0) / 1)] = false;
						if(!guard$sample26gaussian29[((i - 0) / 1)]) {
							guard$sample26gaussian29[((i - 0) / 1)] = true;
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
												double cv$temp$3$var24;
												{
													double var24 = indirection[i];
													cv$temp$3$var24 = var24;
												}
												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[i] - cv$temp$2$sample) / Math.sqrt(cv$temp$3$var24))) - (0.5 * Math.log(cv$temp$3$var24)))) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[i] - cv$temp$2$sample) / Math.sqrt(cv$temp$3$var24))) - (0.5 * Math.log(cv$temp$3$var24)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[i] - cv$temp$2$sample) / Math.sqrt(cv$temp$3$var24))) - (0.5 * Math.log(cv$temp$3$var24))));
													else
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[i] - cv$temp$2$sample) / Math.sqrt(cv$temp$3$var24))) - (0.5 * Math.log(cv$temp$3$var24)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[i] - cv$temp$2$sample) / Math.sqrt(cv$temp$3$var24))) - (0.5 * Math.log(cv$temp$3$var24)))));
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
						double traceTempVariable$var24$5_1 = cv$currentValue;
						for(int index$i$5_2 = 0; index$i$5_2 < length$observed; index$i$5_2 += 1) {
							if((i == index$i$5_2)) {
								if(!guard$sample26gaussian29[((i - 0) / 1)]) {
									guard$sample26gaussian29[((i - 0) / 1)] = true;
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
														double cv$temp$5$var24;
														{
															double var24 = traceTempVariable$var24$5_1;
															cv$temp$5$var24 = var24;
														}
														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[index$i$5_2] - cv$temp$4$sample) / Math.sqrt(cv$temp$5$var24))) - (0.5 * Math.log(cv$temp$5$var24)))) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[index$i$5_2] - cv$temp$4$sample) / Math.sqrt(cv$temp$5$var24))) - (0.5 * Math.log(cv$temp$5$var24)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[index$i$5_2] - cv$temp$4$sample) / Math.sqrt(cv$temp$5$var24))) - (0.5 * Math.log(cv$temp$5$var24))));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[index$i$5_2] - cv$temp$4$sample) / Math.sqrt(cv$temp$5$var24))) - (0.5 * Math.log(cv$temp$5$var24)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[index$i$5_2] - cv$temp$4$sample) / Math.sqrt(cv$temp$5$var24))) - (0.5 * Math.log(cv$temp$5$var24)))));
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

	@Override
	public final void allocateScratch() {
		int cv$max_i = 0;
		cv$max_i = Math.max(cv$max_i, ((length$observed - 0) / 1));
		{
			int cv$threadCount = threadCount();
			guard$sample26gaussian29$global = new boolean[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				guard$sample26gaussian29$global[cv$index] = new boolean[cv$max_i];
		}
	}

	@Override
	public final void allocator() {
		if(!setFlag$generated) {
			{
				generated = new double[length$observed];
			}
		}
		if(!setFlag$indirection) {
			{
				indirection = new double[length$observed];
			}
		}
		if(!setFlag$sample) {
			{
				sample = new double[((((length$observed - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$var21 = new double[((((length$observed - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample26 = new double[((((length$observed - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var25 = new double[((((length$observed - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample30 = new double[((((length$observed - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample26)
							sample[((i - 0) / 1)] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$1)));
						if(!fixedFlag$sample26)
							indirection[i] = sample[((i - 0) / 1)];
						if(!fixedFlag$sample30)
							generated[i] = ((Math.sqrt(indirection[i]) * DistributionSampling.sampleGaussian(RNG$1)) + sample[((i - 0) / 1)]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 0, length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample26)
							sample[((i - 0) / 1)] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$1)));
						if(!fixedFlag$sample26)
							indirection[i] = sample[((i - 0) / 1)];
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 0, length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample26)
							sample[((i - 0) / 1)] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$1)));
						if(!fixedFlag$sample26)
							indirection[i] = sample[((i - 0) / 1)];
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward)
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							if(!fixedFlag$sample26)
								sample26(i, threadID$i, RNG$1);
						}
				}
			);
		else
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							if(!fixedFlag$sample26)
								sample26(i, threadID$i, RNG$1);
						}
				}
			);
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		for(int i = 0; i < length$observed; i += 1)
			logProbability$var21[((i - 0) / 1)] = 0.0;
		logProbability$sample = 0.0;
		logProbability$indirection = 0.0;
		if(!fixedProbFlag$sample26) {
			for(int i = 0; i < length$observed; i += 1)
				logProbability$sample26[((i - 0) / 1)] = 0.0;
		}
		for(int i = 0; i < length$observed; i += 1)
			logProbability$var25[((i - 0) / 1)] = 0.0;
		logProbability$generated = 0.0;
		if(!fixedProbFlag$sample30) {
			for(int i = 0; i < length$observed; i += 1)
				logProbability$sample30[((i - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample26)
			logProbabilityValue$sample26();
		logProbabilityValue$sample30();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample30();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample30();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 0, length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample26)
							sample[((i - 0) / 1)] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$1)));
						if(!fixedFlag$sample26)
							indirection[i] = sample[((i - 0) / 1)];
					}
			}
		);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		double[] cv$source1 = observed;
		double[] cv$target1 = generated;
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