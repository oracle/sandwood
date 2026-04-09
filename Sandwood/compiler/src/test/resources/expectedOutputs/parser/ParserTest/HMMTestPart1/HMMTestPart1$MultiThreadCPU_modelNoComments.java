package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart1$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart1$CoreInterface {
	private double[] bias;
	private double[][] cv$var28$countGlobal;
	private double[] cv$var49$stateProbabilityGlobal;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample50 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample50 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean flip;
	private boolean flipMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flip;
	private double logProbability$m;
	private double logProbability$st;
	private double logProbability$var16;
	private double logProbability$var28;
	private double logProbability$var32;
	private double logProbability$var44;
	private double logProbability$var48;
	private double logProbability$var51;
	private double[][] m;
	private int st;
	private int states;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart1$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
		fixedProbFlag$sample45 = false;
		fixedProbFlag$sample53 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		fixedFlag$sample28 = cv$value;
		fixedProbFlag$sample28 = (fixedFlag$sample28 && fixedProbFlag$sample28);
		fixedProbFlag$sample50 = (fixedFlag$sample28 && fixedProbFlag$sample50);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		fixedFlag$sample45 = cv$value;
		fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedProbFlag$sample45);
		fixedProbFlag$sample53 = (fixedFlag$sample45 && fixedProbFlag$sample53);
	}

	@Override
	public final boolean get$fixedFlag$sample50() {
		return fixedFlag$sample50;
	}

	@Override
	public final void set$fixedFlag$sample50(boolean cv$value) {
		fixedFlag$sample50 = cv$value;
		fixedProbFlag$sample50 = (fixedFlag$sample50 && fixedProbFlag$sample50);
		fixedProbFlag$sample53 = (fixedFlag$sample50 && fixedProbFlag$sample53);
	}

	@Override
	public final boolean get$flip() {
		return flip;
	}

	@Override
	public final boolean get$flipMeasured() {
		return flipMeasured;
	}

	@Override
	public final void set$flipMeasured(boolean cv$value) {
		flipMeasured = cv$value;
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
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$flip() {
		return logProbability$flip;
	}

	@Override
	public final double get$logProbability$m() {
		return logProbability$m;
	}

	@Override
	public final double get$logProbability$st() {
		return logProbability$st;
	}

	@Override
	public final double[][] get$m() {
		return m;
	}

	@Override
	public final void set$m(double[][] cv$value) {
		m = cv$value;
		fixedProbFlag$sample28 = false;
		fixedProbFlag$sample50 = false;
	}

	@Override
	public final int get$st() {
		return st;
	}

	@Override
	public final void set$st(int cv$value) {
		st = cv$value;
		fixedProbFlag$sample50 = false;
		fixedProbFlag$sample53 = false;
	}

	@Override
	public final int get$states() {
		return states;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var27 = 0; var27 < states; var27 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var27];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, states));
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
			logProbability$var16 = cv$sampleAccumulator;
			logProbability$var28 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var28;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var16 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!fixedProbFlag$sample45) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var43 = 0; var43 < states; var43 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = bias[var43];
					{
						{
							double var30 = 1.0;
							double var31 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var30, var31));
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
			logProbability$var32 = cv$sampleAccumulator;
			logProbability$var44 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample45 = fixedFlag$sample45;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var44;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var32 = cv$rvAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample50() {
		if(!fixedProbFlag$sample50) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = st;
				{
					{
						double[] var47 = m[0];
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var47[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var48 = cv$sampleAccumulator;
			logProbability$st = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample50)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample50 = (fixedFlag$sample50 && fixedFlag$sample28);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$st;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var48 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample50)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				boolean cv$sampleValue = flip;
				{
					{
						double var50 = bias[st];
						double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var50:(1.0 - var50))));
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
			logProbability$var51 = cv$sampleAccumulator;
			logProbability$flip = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample53 = (fixedFlag$sample45 && fixedFlag$sample50);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$flip;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var51 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample28(int var27, int threadID$cv$var27, Rng RNG$) {
		if(true) {
			double[] cv$targetLocal = m[var27];
			double[] cv$countLocal = cv$var28$countGlobal[threadID$cv$var27];
			int cv$arrayLength = states;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						if((var27 == 0)) {
							{
								{
									{
										{
											{
												cv$countLocal[st] = (cv$countLocal[st] + 1.0);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, states);
		}
	}

	private final void sample45(int var43, int threadID$cv$var43, Rng RNG$) {
		if(true) {
			int cv$sum = 0;
			int cv$count = 0;
			{
				{
					{
						if((var43 == st)) {
							{
								{
									{
										{
											{
												cv$count = (cv$count + 1);
												if(flip)
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
			double var44 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
			{
				{
					bias[var43] = var44;
				}
			}
		}
	}

	private final void sample50() {
		if(true) {
			int cv$numNumStates = 0;
			{
				cv$numNumStates = Math.max(cv$numNumStates, states);
			}
			double[] cv$stateProbabilityLocal = cv$var49$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				st = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] cv$temp$0$var47;
					{
						double[] var47 = m[0];
						cv$temp$0$var47 = var47;
					}
					int cv$temp$1$$var150;
					{
						int $var150 = states;
						cv$temp$1$$var150 = $var150;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var150))?Math.log(cv$temp$0$var47[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							int traceTempVariable$st$1_1 = cv$currentValue;
							{
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								{
									{
										{
											{
												double cv$temp$2$var50;
												{
													double var50 = bias[traceTempVariable$st$1_1];
													cv$temp$2$var50 = var50;
												}
												if(((Math.log(1.0) + Math.log((flip?cv$temp$2$var50:(1.0 - cv$temp$2$var50)))) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((flip?cv$temp$2$var50:(1.0 - cv$temp$2$var50)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((flip?cv$temp$2$var50:(1.0 - cv$temp$2$var50))));
													else
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((flip?cv$temp$2$var50:(1.0 - cv$temp$2$var50)))))) + 1)) + (Math.log(1.0) + Math.log((flip?cv$temp$2$var50:(1.0 - cv$temp$2$var50)))));
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
			double cv$logSum = 0.0;
			{
				double cv$lseMax = cv$stateProbabilityLocal[0];
				for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
					double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
					if((cv$lseMax < cv$lseElementValue))
						cv$lseMax = cv$lseElementValue;
				}
				if((cv$lseMax == Double.NEGATIVE_INFINITY))
					cv$logSum = Double.NEGATIVE_INFINITY;
				else {
					double cv$lseSum = 0.0;
					for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
						cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
					cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
				}
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numNumStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
			st = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates);
		}
	}

	@Override
	public final void allocateScratch() {
		{
			{
				int cv$threadCount = threadCount();
				cv$var28$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var28$countGlobal[cv$index] = new double[2];
			}
		}
		{
			int cv$var29$max = 2;
			cv$var49$stateProbabilityGlobal = new double[cv$var29$max];
		}
	}

	@Override
	public final void allocator() {
		{
			v = new double[2];
		}
		if(!fixedFlag$sample28) {
			{
				m = new double[2][];
				for(int var27 = 0; var27 < 2; var27 += 1)
					m[var27] = new double[2];
			}
		}
		if(!fixedFlag$sample45) {
			{
				bias = new double[2];
			}
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = m[var27];
						if(!fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, v, states, var28);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!fixedFlag$sample45)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample50)
			st = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		flip = DistributionSampling.sampleBernoulli(RNG$, bias[st]);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = m[var27];
						if(!fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, v, states, var28);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!fixedFlag$sample45)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample50)
			st = DistributionSampling.sampleCategorical(RNG$, m[0], states);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = m[var27];
						if(!fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, v, states, var28);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!fixedFlag$sample45)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample50)
			st = DistributionSampling.sampleCategorical(RNG$, m[0], states);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
							if(!fixedFlag$sample28)
								sample28(var27, threadID$var27, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
							if(!fixedFlag$sample45)
								sample45(var43, threadID$var43, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample50)
				sample50();
		} else {
			if(!fixedFlag$sample50)
				sample50();
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
							if(!fixedFlag$sample45)
								sample45(var43, threadID$var43, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
							if(!fixedFlag$sample28)
								sample28(var27, threadID$var27, RNG$1);
						}
				}
			);
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		states = 2;
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1)
						v[i] = 0.1;
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var16 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$var28 = 0.0;
		logProbability$var32 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample45)
			logProbability$var44 = 0.0;
		logProbability$var48 = 0.0;
		if(!fixedProbFlag$sample50)
			logProbability$st = 0.0;
		logProbability$var51 = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$flip = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		if(fixedFlag$sample50)
			logProbabilityValue$sample50();
		logProbabilityValue$sample53();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample50();
		logProbabilityValue$sample53();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample50();
		logProbabilityValue$sample53();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = m[var27];
						if(!fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, v, states, var28);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!fixedFlag$sample45)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample50)
			st = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		flip = flipMeasured;
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
		     + "model HMMTestPart1(boolean flipMeasured) {\n"
		     + "        int states = 2;\n"
		     + "\n"
		     + "        double[] v = new double[states];\n"
		     + "        for(int i:[0..states))\n"
		     + "            v[i] = 0.1;\n"
		     + "        \n"
		     + "        double[][] m = dirichlet(v).sample(states);\n"
		     + "        double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "        int st = categorical(m[0]).sample();\n"
		     + "        boolean flip = bernoulli(bias[st]).sample();\n"
		     + "\n"
		     + "        flip.observe(flipMeasured);\n"
		     + "}\n"
		     + "";
	}
}