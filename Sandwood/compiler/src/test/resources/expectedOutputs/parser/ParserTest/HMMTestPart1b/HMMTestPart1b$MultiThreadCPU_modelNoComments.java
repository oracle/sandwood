package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart1b$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart1b$CoreInterface {
	private double[] bias;
	private double[][] cv$var14$countGlobal;
	private double[] cv$var28$stateProbabilityGlobal;
	private boolean fixedFlag$sample14 = false;
	private boolean fixedFlag$sample24 = false;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedProbFlag$sample14 = false;
	private boolean fixedProbFlag$sample24 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean flip;
	private boolean flipMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flip;
	private double logProbability$m;
	private double logProbability$st;
	private double logProbability$var14;
	private double logProbability$var18;
	private double logProbability$var23;
	private double logProbability$var27;
	private double logProbability$var30;
	private double logProbability$var9;
	private double[][] m;
	private boolean setFlag$bias = false;
	private boolean setFlag$m = false;
	private int st;
	private int states;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart1b$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
		setFlag$bias = true;
		fixedProbFlag$sample24 = false;
		fixedProbFlag$sample32 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample14() {
		return fixedFlag$sample14;
	}

	@Override
	public final void set$fixedFlag$sample14(boolean cv$value) {
		fixedFlag$sample14 = cv$value;
		fixedProbFlag$sample14 = (fixedFlag$sample14 && fixedProbFlag$sample14);
		fixedProbFlag$sample29 = (fixedFlag$sample14 && fixedProbFlag$sample29);
	}

	@Override
	public final boolean get$fixedFlag$sample24() {
		return fixedFlag$sample24;
	}

	@Override
	public final void set$fixedFlag$sample24(boolean cv$value) {
		fixedFlag$sample24 = cv$value;
		fixedProbFlag$sample24 = (fixedFlag$sample24 && fixedProbFlag$sample24);
		fixedProbFlag$sample32 = (fixedFlag$sample24 && fixedProbFlag$sample32);
	}

	@Override
	public final boolean get$fixedFlag$sample29() {
		return fixedFlag$sample29;
	}

	@Override
	public final void set$fixedFlag$sample29(boolean cv$value) {
		fixedFlag$sample29 = cv$value;
		fixedProbFlag$sample29 = (fixedFlag$sample29 && fixedProbFlag$sample29);
		fixedProbFlag$sample32 = (fixedFlag$sample29 && fixedProbFlag$sample32);
	}

	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	@Override
	public final void set$fixedFlag$sample32(boolean cv$value) {
		fixedFlag$sample32 = cv$value;
		fixedProbFlag$sample32 = (fixedFlag$sample32 && fixedProbFlag$sample32);
	}

	@Override
	public final boolean get$flip() {
		return flip;
	}

	@Override
	public final void set$flip(boolean cv$value) {
		flip = cv$value;
		fixedProbFlag$sample32 = false;
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
		setFlag$m = true;
		fixedProbFlag$sample14 = false;
		fixedProbFlag$sample29 = false;
	}

	@Override
	public final int get$st() {
		return st;
	}

	@Override
	public final void set$st(int cv$value) {
		st = cv$value;
		fixedProbFlag$sample29 = false;
		fixedProbFlag$sample32 = false;
	}

	@Override
	public final int get$states() {
		return states;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample14() {
		if(!fixedProbFlag$sample14) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var13 = 0; var13 < states; var13 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var13];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v));
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
			logProbability$var9 = cv$sampleAccumulator;
			logProbability$var14 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample14)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample14 = fixedFlag$sample14;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var14;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var9 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample14)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample24() {
		if(!fixedProbFlag$sample24) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var22 = 0; var22 < states; var22 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = bias[var22];
					{
						{
							double var16 = 1.0;
							double var17 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var16, var17));
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
			logProbability$var18 = cv$sampleAccumulator;
			logProbability$var23 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample24 = fixedFlag$sample24;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var23;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var18 = cv$rvAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample29() {
		if(!fixedProbFlag$sample29) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = st;
				{
					{
						double[] var26 = m[0];
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var26.length))?Math.log(var26[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var27 = cv$sampleAccumulator;
			logProbability$st = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample29 = (fixedFlag$sample29 && fixedFlag$sample14);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$st;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var27 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample32() {
		if(!fixedProbFlag$sample32) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				boolean cv$sampleValue = flip;
				{
					{
						double var29 = bias[st];
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var29));
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
			logProbability$var30 = cv$sampleAccumulator;
			logProbability$flip = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample32 = ((fixedFlag$sample32 && fixedFlag$sample24) && fixedFlag$sample29);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$flip;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var30 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample14(int var13, int threadID$cv$var13, Rng RNG$) {
		double[] cv$targetLocal = m[var13];
		double[] cv$countLocal = cv$var14$countGlobal[threadID$cv$var13];
		int cv$arrayLength = states;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					if((var13 == 0)) {
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
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	private final void sample24(int var22, int threadID$cv$var22, Rng RNG$) {
		int cv$sum = 0;
		int cv$count = 0;
		{
			{
				{
					if((var22 == st)) {
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
		double var23 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[var22] = var23;
	}

	private final void sample29() {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, states);
		}
		double[] cv$stateProbabilityLocal = cv$var28$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			st = cv$currentValue;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var26;
				{
					double[] var26 = m[0];
					cv$temp$0$var26 = var26;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var26.length))?Math.log(cv$temp$0$var26[cv$currentValue]):Double.NEGATIVE_INFINITY));
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
											double cv$temp$1$var29;
											{
												double var29 = bias[traceTempVariable$st$1_1];
												cv$temp$1$var29 = var29;
											}
											if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flip, cv$temp$1$var29)) < cv$accumulatedConsumerProbabilities))
												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flip, cv$temp$1$var29)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
											else {
												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flip, cv$temp$1$var29));
												else
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flip, cv$temp$1$var29)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flip, cv$temp$1$var29)));
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
			for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			for(int var13 = 0; var13 < 2; var13 += 1)
				cv$max = Math.max(cv$max, 2);
			{
				int cv$threadCount = threadCount();
				cv$var14$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var14$countGlobal[cv$index] = new double[cv$max];
			}
		}
		{
			int cv$var15$max = 2;
			cv$var28$stateProbabilityGlobal = new double[cv$var15$max];
		}
	}

	@Override
	public final void allocator() {
		{
			v = new double[2];
		}
		if(!setFlag$m) {
			{
				m = new double[2][];
				for(int var13 = 0; var13 < 2; var13 += 1)
					m[var13] = new double[2];
			}
		}
		if(!setFlag$bias) {
			{
				bias = new double[2];
			}
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var13, int forEnd$var13, int threadID$var13, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var13 = forStart$var13; var13 < forEnd$var13; var13 += 1) {
						double[] var14 = m[var13];
						if(!fixedFlag$sample14)
							DistributionSampling.sampleDirichlet(RNG$1, v, var14);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var22, int forEnd$var22, int threadID$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var22 = forStart$var22; var22 < forEnd$var22; var22 += 1) {
						if(!fixedFlag$sample24)
							bias[var22] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample29)
			st = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample32)
			flip = DistributionSampling.sampleBernoulli(RNG$, bias[st]);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var13, int forEnd$var13, int threadID$var13, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var13 = forStart$var13; var13 < forEnd$var13; var13 += 1) {
						double[] var14 = m[var13];
						if(!fixedFlag$sample14)
							DistributionSampling.sampleDirichlet(RNG$1, v, var14);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var22, int forEnd$var22, int threadID$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var22 = forStart$var22; var22 < forEnd$var22; var22 += 1) {
						if(!fixedFlag$sample24)
							bias[var22] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample29)
			st = DistributionSampling.sampleCategorical(RNG$, m[0]);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var13, int forEnd$var13, int threadID$var13, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var13 = forStart$var13; var13 < forEnd$var13; var13 += 1) {
						double[] var14 = m[var13];
						if(!fixedFlag$sample14)
							DistributionSampling.sampleDirichlet(RNG$1, v, var14);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var22, int forEnd$var22, int threadID$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var22 = forStart$var22; var22 < forEnd$var22; var22 += 1) {
						if(!fixedFlag$sample24)
							bias[var22] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample29)
			st = DistributionSampling.sampleCategorical(RNG$, m[0]);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var13, int forEnd$var13, int threadID$var13, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var13 = forStart$var13; var13 < forEnd$var13; var13 += 1) {
							if(!fixedFlag$sample14)
								sample14(var13, threadID$var13, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var22, int forEnd$var22, int threadID$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var22 = forStart$var22; var22 < forEnd$var22; var22 += 1) {
							if(!fixedFlag$sample24)
								sample24(var22, threadID$var22, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample29)
				sample29();
		} else {
			if(!fixedFlag$sample29)
				sample29();
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var22, int forEnd$var22, int threadID$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var22 = forStart$var22; var22 < forEnd$var22; var22 += 1) {
							if(!fixedFlag$sample24)
								sample24(var22, threadID$var22, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var13, int forEnd$var13, int threadID$var13, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var13 = forStart$var13; var13 < forEnd$var13; var13 += 1) {
							if(!fixedFlag$sample14)
								sample14(var13, threadID$var13, RNG$1);
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
			(int forStart$var6, int forEnd$var6, int threadID$var6, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var6 = forStart$var6; var6 < forEnd$var6; var6 += 1)
						v[var6] = 0.1;
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var9 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample14)
			logProbability$var14 = 0.0;
		logProbability$var18 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample24)
			logProbability$var23 = 0.0;
		logProbability$var27 = 0.0;
		if(!fixedProbFlag$sample29)
			logProbability$st = 0.0;
		logProbability$var30 = 0.0;
		if(!fixedProbFlag$sample32)
			logProbability$flip = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample14)
			logProbabilityValue$sample14();
		if(fixedFlag$sample24)
			logProbabilityValue$sample24();
		if(fixedFlag$sample29)
			logProbabilityValue$sample29();
		logProbabilityValue$sample32();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample14();
		logProbabilityValue$sample24();
		logProbabilityValue$sample29();
		logProbabilityValue$sample32();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample14();
		logProbabilityValue$sample24();
		logProbabilityValue$sample29();
		logProbabilityValue$sample32();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var13, int forEnd$var13, int threadID$var13, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var13 = forStart$var13; var13 < forEnd$var13; var13 += 1) {
						double[] var14 = m[var13];
						if(!fixedFlag$sample14)
							DistributionSampling.sampleDirichlet(RNG$1, v, var14);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var22, int forEnd$var22, int threadID$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var22 = forStart$var22; var22 < forEnd$var22; var22 += 1) {
						if(!fixedFlag$sample24)
							bias[var22] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample29)
			st = DistributionSampling.sampleCategorical(RNG$, m[0]);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		flip = flipMeasured;
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart1b(boolean flipMeasured) {\n        int states = 2;\n\n        double[] v = new double[states] <~ 0.1;\n        \n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int st = categorical(m[0]).sample();\n        boolean flip = bernoulli(bias[st]).sample();\n\n        flip.observe(flipMeasured);\n}\n";
	}
}