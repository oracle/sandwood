package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class ReductionTest$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements ReductionTest$CoreInterface {
	private double[] bias;
	private boolean[] constrainedFlag$sample30;
	private boolean[] constrainedFlag$sample47;
	private boolean[] constrainedFlag$sample62;
	private double[][] cv$var30$countGlobal;
	private double[] cv$var61$stateProbabilityGlobal;
	private boolean fixedFlag$sample30 = false;
	private boolean fixedFlag$sample47 = false;
	private boolean fixedFlag$sample62 = false;
	private boolean fixedProbFlag$sample30 = false;
	private boolean fixedProbFlag$sample47 = false;
	private boolean fixedProbFlag$sample62 = false;
	private boolean fixedProbFlag$sample87 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample62;
	private double[] logProbability$sample87;
	private double logProbability$st;
	private double logProbability$var30;
	private double logProbability$var46;
	private double[][] m;
	private int noCats;
	private int noFlips;
	private int noStates;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public ReductionTest$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value, boolean allocated$) {
		bias = cv$value;
		fixedProbFlag$sample47 = false;
		fixedProbFlag$sample87 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample30() {
		return fixedFlag$sample30;
	}

	@Override
	public final void set$fixedFlag$sample30(boolean cv$value, boolean allocated$) {
		fixedFlag$sample30 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample30$1 = 0; index$constrainedFlag$sample30$1 < constrainedFlag$sample30.length; index$constrainedFlag$sample30$1 += 1)
				constrainedFlag$sample30[index$constrainedFlag$sample30$1] = true;
		}
		fixedProbFlag$sample30 = (fixedFlag$sample30 && fixedProbFlag$sample30);
		fixedProbFlag$sample62 = (fixedFlag$sample30 && fixedProbFlag$sample62);
	}

	@Override
	public final boolean get$fixedFlag$sample47() {
		return fixedFlag$sample47;
	}

	@Override
	public final void set$fixedFlag$sample47(boolean cv$value, boolean allocated$) {
		fixedFlag$sample47 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample47$1 = 0; index$constrainedFlag$sample47$1 < constrainedFlag$sample47.length; index$constrainedFlag$sample47$1 += 1)
				constrainedFlag$sample47[index$constrainedFlag$sample47$1] = true;
		}
		fixedProbFlag$sample47 = (fixedFlag$sample47 && fixedProbFlag$sample47);
		fixedProbFlag$sample87 = (fixedFlag$sample47 && fixedProbFlag$sample87);
	}

	@Override
	public final boolean get$fixedFlag$sample62() {
		return fixedFlag$sample62;
	}

	@Override
	public final void set$fixedFlag$sample62(boolean cv$value, boolean allocated$) {
		fixedFlag$sample62 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample62$1 = 0; index$constrainedFlag$sample62$1 < constrainedFlag$sample62.length; index$constrainedFlag$sample62$1 += 1)
				constrainedFlag$sample62[index$constrainedFlag$sample62$1] = true;
		}
		fixedProbFlag$sample62 = (fixedFlag$sample62 && fixedProbFlag$sample62);
		fixedProbFlag$sample87 = (fixedFlag$sample62 && fixedProbFlag$sample87);
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
	public final void set$flipsMeasured(boolean[] cv$value, boolean allocated$) {
		flipsMeasured = cv$value;
	}

	@Override
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	@Override
	public final void set$length$flipsMeasured(int cv$value, boolean allocated$) {
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
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$flips() {
		return logProbability$flips;
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
	public final void set$m(double[][] cv$value, boolean allocated$) {
		m = cv$value;
		fixedProbFlag$sample30 = false;
		fixedProbFlag$sample62 = false;
	}

	@Override
	public final int get$noCats() {
		return noCats;
	}

	@Override
	public final void set$noCats(int cv$value, boolean allocated$) {
		noCats = cv$value;
	}

	@Override
	public final int get$noFlips() {
		return noFlips;
	}

	@Override
	public final int get$noStates() {
		return noStates;
	}

	@Override
	public final int[] get$st() {
		return st;
	}

	@Override
	public final void set$st(int[] cv$value, boolean allocated$) {
		st = cv$value;
		fixedProbFlag$sample62 = false;
		fixedProbFlag$sample87 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void drawValueSample30(int var29, int threadID$cv$var29, Rng RNG$) {
		double[] var30 = m[var29];
		DistributionSampling.sampleDirichlet(RNG$, v, noStates, var30);
	}

	private final void drawValueSample47(int var45, int threadID$cv$var45, Rng RNG$) {
		bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	private final void drawValueSample62(int i$var58) {
		st[i$var58] = DistributionSampling.sampleCategorical(RNG$, m[i$var58], noStates);
	}

	private final void inferSample30(int var29, int threadID$cv$var29, Rng RNG$) {
		if(true) {
			constrainedFlag$sample30[((var29 - 0) / 1)] = false;
			double[] cv$targetLocal = m[var29];
			double[] cv$countLocal = cv$var30$countGlobal[threadID$cv$var29];
			int cv$arrayLength = noStates;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							for(int i$var58 = 0; i$var58 < noCats; i$var58 += 1) {
								if((var29 == i$var58)) {
									{
										{
											boolean cv$sampleConstrained = (fixedFlag$sample62 || constrainedFlag$sample62[((i$var58 - 0) / 1)]);
											if(cv$sampleConstrained) {
												constrainedFlag$sample30[((var29 - 0) / 1)] = true;
												{
													{
														{
															{
																{
																	cv$countLocal[st[i$var58]] = (cv$countLocal[st[i$var58]] + 1.0);
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
			if(constrainedFlag$sample30[((var29 - 0) / 1)])
				Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, noStates);
		}
	}

	private final void inferSample47(int var45, int threadID$cv$var45, Rng RNG$) {
		if(true) {
			constrainedFlag$sample47[((var45 - 0) / 1)] = false;
			int cv$sum = 0;
			int cv$count = 0;
			{
				{
					{
						{
							int reduceVar$var82$5 = 0;
							for(int cv$reduction78Index = 0; cv$reduction78Index < noCats; cv$reduction78Index += 1) {
								int i$var79 = reduceVar$var82$5;
								int j$var80 = st[cv$reduction78Index];
								reduceVar$var82$5 = (i$var79 + j$var80);
							}
							if((var45 == reduceVar$var82$5)) {
								for(int j$var73 = 0; j$var73 < noFlips; j$var73 += 1) {
									boolean cv$sampleConstrained = true;
									if(cv$sampleConstrained) {
										constrainedFlag$sample47[((var45 - 0) / 1)] = true;
										{
											{
												{
													{
														{
															cv$count = (cv$count + 1);
															if(flips[j$var73])
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
			if(constrainedFlag$sample47[((var45 - 0) / 1)]) {
				double var46 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
				{
					{
						{
							bias[var45] = var46;
						}
					}
				}
			}
		}
	}

	private final void inferSample62(int i$var58) {
		if(true) {
			constrainedFlag$sample62[((i$var58 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, noStates);
			}
			double[] cv$stateProbabilityLocal = cv$var61$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				int var61 = cv$currentValue;
				{
					{
						{
							st[i$var58] = cv$currentValue;
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] var59 = m[i$var58];
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < noStates)) && (0 < noStates)) && (0.0 <= var59[cv$currentValue])) && (var59[cv$currentValue] <= 1.0))?Math.log(var59[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$i$2_1 = cv$currentValue;
								if(((0 <= i$var58) && (i$var58 < noCats))) {
									if((0 < noCats)) {
										int reduceVar$var82$6 = 0;
										for(int cv$reduction543Index = 0; cv$reduction543Index < i$var58; cv$reduction543Index += 1) {
											int i$var79 = reduceVar$var82$6;
											int j$var80 = st[cv$reduction543Index];
											reduceVar$var82$6 = (i$var79 + j$var80);
										}
										for(int cv$reduction543Index = (i$var58 + 1); cv$reduction543Index < noCats; cv$reduction543Index += 1) {
											int i$var79 = reduceVar$var82$6;
											int j$var80 = st[cv$reduction543Index];
											reduceVar$var82$6 = (i$var79 + j$var80);
										}
										int cv$reduced78 = reduceVar$var82$6;
										reduceVar$var82$6 = (traceTempVariable$i$2_1 + cv$reduced78);
										int traceTempVariable$var82$2_2 = reduceVar$var82$6;
										for(int j$var73 = 0; j$var73 < noFlips; j$var73 += 1) {
											boolean cv$sampleConstrained = true;
											if(cv$sampleConstrained) {
												constrainedFlag$sample62[((i$var58 - 0) / 1)] = true;
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													{
														{
															{
																{
																	double var83 = bias[traceTempVariable$var82$2_2];
																	if(((Math.log(1.0) + (((0.0 <= var83) && (var83 <= 1.0))?Math.log((flips[j$var73]?var83:(1.0 - var83))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var83) && (var83 <= 1.0))?Math.log((flips[j$var73]?var83:(1.0 - var83))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var83) && (var83 <= 1.0))?Math.log((flips[j$var73]?var83:(1.0 - var83))):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var83) && (var83 <= 1.0))?Math.log((flips[j$var73]?var83:(1.0 - var83))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var83) && (var83 <= 1.0))?Math.log((flips[j$var73]?var83:(1.0 - var83))):Double.NEGATIVE_INFINITY)));
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
			if(constrainedFlag$sample62[((i$var58 - 0) / 1)]) {
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
				int var61 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates);
				{
					{
						{
							st[i$var58] = var61;
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample30() {
		if(!fixedProbFlag$sample30) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var29 = 0; var29 < noCats; var29 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double[] cv$sampleValue = m[var29];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, noStates));
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
			logProbability$var30 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample30 = fixedFlag$sample30;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var29 = 0; var29 < noCats; var29 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var30;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample47() {
		if(!fixedProbFlag$sample47) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var45 = 0; var45 < noFlips; var45 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = bias[var45];
						{
							{
								double var32 = 1.0;
								double var33 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var32, var33));
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
			logProbability$var46 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample47 = fixedFlag$sample47;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var45 = 0; var45 < noFlips; var45 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var46;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample62() {
		if(!fixedProbFlag$sample62) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var58 = 0; i$var58 < noCats; i$var58 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = st[i$var58];
						{
							{
								double[] var59 = m[i$var58];
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var59[cv$sampleValue])) && (var59[cv$sampleValue] <= 1.0))?Math.log(var59[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$sample62[((i$var58 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample62)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample62 = (fixedFlag$sample62 && fixedFlag$sample30);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var58 = 0; i$var58 < noCats; i$var58 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample62[((i$var58 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample62)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample87() {
		if(!fixedProbFlag$sample87) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j$var73 = 0; j$var73 < noFlips; j$var73 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = flips[j$var73];
						{
							{
								int reduceVar$var82$7 = 0;
								for(int cv$reduction78Index = 0; cv$reduction78Index < noCats; cv$reduction78Index += 1) {
									int i$var79 = reduceVar$var82$7;
									int j$var80 = st[cv$reduction78Index];
									reduceVar$var82$7 = (i$var79 + j$var80);
								}
								double var83 = bias[reduceVar$var82$7];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var83) && (var83 <= 1.0))?Math.log((cv$sampleValue?var83:(1.0 - var83))):Double.NEGATIVE_INFINITY));
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
				logProbability$sample87[((j$var73 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample87 = (fixedFlag$sample47 && fixedFlag$sample62);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j$var73 = 0; j$var73 < noFlips; j$var73 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample87[((j$var73 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void allocateScratch() {
		{
			{
				int cv$threadCount = threadCount();
				cv$var30$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var30$countGlobal[cv$index] = new double[((0.0 <= flipsMeasured.length)?((0.0 <= noCats)?(flipsMeasured.length / noCats):((noCats < 0.0)?(flipsMeasured.length / noCats):flipsMeasured.length)):((flipsMeasured.length < 0.0)?((0.0 <= noCats)?(flipsMeasured.length / noCats):((noCats < 0.0)?(flipsMeasured.length / noCats):(-flipsMeasured.length))):((0.0 <= noCats)?(flipsMeasured.length / noCats):((noCats < 0.0)?(flipsMeasured.length / noCats):Math.max(flipsMeasured.length, (-flipsMeasured.length))))))];
			}
		}
		{
			int cv$var31$max = ((0.0 <= flipsMeasured.length)?((0.0 <= noCats)?(flipsMeasured.length / noCats):((noCats < 0.0)?(flipsMeasured.length / noCats):flipsMeasured.length)):((flipsMeasured.length < 0.0)?((0.0 <= noCats)?(flipsMeasured.length / noCats):((noCats < 0.0)?(flipsMeasured.length / noCats):(-flipsMeasured.length))):((0.0 <= noCats)?(flipsMeasured.length / noCats):((noCats < 0.0)?(flipsMeasured.length / noCats):Math.max(flipsMeasured.length, (-flipsMeasured.length))))));
			cv$var61$stateProbabilityGlobal = new double[cv$var31$max];
		}
	}

	@Override
	public final void allocator() {
		{
			v = new double[(length$flipsMeasured / noCats)];
		}
		if(!fixedFlag$sample30) {
			{
				m = new double[noCats][];
				for(int var29 = 0; var29 < noCats; var29 += 1)
					m[var29] = new double[(length$flipsMeasured / noCats)];
			}
		}
		if(!fixedFlag$sample47) {
			{
				bias = new double[length$flipsMeasured];
			}
		}
		if(!fixedFlag$sample62) {
			{
				st = new int[noCats];
			}
		}
		{
			flips = new boolean[length$flipsMeasured];
		}
		{
			constrainedFlag$sample47 = new boolean[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample30 = new boolean[((((noCats - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample62 = new boolean[((((noCats - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample62 = new double[((((noCats - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample87 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, noCats, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = m[var29];
						if(!fixedFlag$sample30)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var30);
					}
			}
		);
		parallelFor(RNG$, 0, noFlips, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!fixedFlag$sample47)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, noCats, 1,
			(int forStart$i$var58, int forEnd$i$var58, int threadID$i$var58, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var58 = forStart$i$var58; i$var58 < forEnd$i$var58; i$var58 += 1) {
						if(!fixedFlag$sample62)
							st[i$var58] = DistributionSampling.sampleCategorical(RNG$1, m[i$var58], noStates);
					}
			}
		);
		parallelFor(RNG$, 0, noFlips, 1,
			(int forStart$j$var73, int forEnd$j$var73, int threadID$j$var73, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var73 = forStart$j$var73; j$var73 < forEnd$j$var73; j$var73 += 1) {
						int reduceVar$var82$8 = 0;
						for(int cv$reduction78Index = 0; cv$reduction78Index < noCats; cv$reduction78Index += 1) {
							int i$var79 = reduceVar$var82$8;
							int j$var80 = st[cv$reduction78Index];
							reduceVar$var82$8 = (i$var79 + j$var80);
						}
						flips[j$var73] = DistributionSampling.sampleBernoulli(RNG$1, bias[reduceVar$var82$8]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		parallelFor(RNG$, 0, noCats, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = m[var29];
						if(!fixedFlag$sample30)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var30);
					}
			}
		);
		parallelFor(RNG$, 0, noFlips, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!fixedFlag$sample47)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, noCats, 1,
			(int forStart$i$var58, int forEnd$i$var58, int threadID$i$var58, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var58 = forStart$i$var58; i$var58 < forEnd$i$var58; i$var58 += 1) {
						if(!fixedFlag$sample62)
							st[i$var58] = DistributionSampling.sampleCategorical(RNG$1, m[i$var58], noStates);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		parallelFor(RNG$, 0, noCats, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = m[var29];
						if(!fixedFlag$sample30)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var30);
					}
			}
		);
		parallelFor(RNG$, 0, noFlips, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!fixedFlag$sample47)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, noCats, 1,
			(int forStart$i$var58, int forEnd$i$var58, int threadID$i$var58, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var58 = forStart$i$var58; i$var58 < forEnd$i$var58; i$var58 += 1) {
						if(!fixedFlag$sample62)
							st[i$var58] = DistributionSampling.sampleCategorical(RNG$1, m[i$var58], noStates);
					}
			}
		);
		parallelFor(RNG$, 0, noFlips, 1,
			(int forStart$j$var73, int forEnd$j$var73, int threadID$j$var73, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var73 = forStart$j$var73; j$var73 < forEnd$j$var73; j$var73 += 1) {
						int reduceVar$var82$9 = 0;
						for(int cv$reduction78Index = 0; cv$reduction78Index < noCats; cv$reduction78Index += 1) {
							int i$var79 = reduceVar$var82$9;
							int j$var80 = st[cv$reduction78Index];
							reduceVar$var82$9 = (i$var79 + j$var80);
						}
						flips[j$var73] = DistributionSampling.sampleBernoulli(RNG$1, bias[reduceVar$var82$9]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 0, noCats, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = m[var29];
						if(!fixedFlag$sample30)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var30);
					}
			}
		);
		parallelFor(RNG$, 0, noFlips, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!fixedFlag$sample47)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, noCats, 1,
			(int forStart$i$var58, int forEnd$i$var58, int threadID$i$var58, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var58 = forStart$i$var58; i$var58 < forEnd$i$var58; i$var58 += 1) {
						if(!fixedFlag$sample62)
							st[i$var58] = DistributionSampling.sampleCategorical(RNG$1, m[i$var58], noStates);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		parallelFor(RNG$, 0, noCats, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = m[var29];
						if(!fixedFlag$sample30)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var30);
					}
			}
		);
		parallelFor(RNG$, 0, noFlips, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!fixedFlag$sample47)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, noCats, 1,
			(int forStart$i$var58, int forEnd$i$var58, int threadID$i$var58, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var58 = forStart$i$var58; i$var58 < forEnd$i$var58; i$var58 += 1) {
						if(!fixedFlag$sample62)
							st[i$var58] = DistributionSampling.sampleCategorical(RNG$1, m[i$var58], noStates);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			parallelFor(RNG$, 0, noCats, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
							if(!fixedFlag$sample30)
								inferSample30(var29, threadID$var29, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noFlips, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
							if(!fixedFlag$sample47)
								inferSample47(var45, threadID$var45, RNG$1);
						}
				}
			);
			for(int i$var58 = 0; i$var58 < noCats; i$var58 += 1) {
				if(!fixedFlag$sample62)
					inferSample62(i$var58);
			}
		} else {
			for(int i$var58 = (noCats - ((((noCats - 1) - 0) % 1) + 1)); i$var58 >= ((0 - 1) + 1); i$var58 -= 1) {
				if(!fixedFlag$sample62)
					inferSample62(i$var58);
			}
			parallelFor(RNG$, 0, noFlips, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
							if(!fixedFlag$sample47)
								inferSample47(var45, threadID$var45, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noCats, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
							if(!fixedFlag$sample30)
								inferSample30(var29, threadID$var29, RNG$1);
						}
				}
			);
		}
		system$gibbsForward = !system$gibbsForward;
		parallelFor(RNG$, 0, noCats, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						if(!constrainedFlag$sample30[((var29 - 0) / 1)])
							drawValueSample30(var29, threadID$var29, RNG$1);
					}
			}
		);
		parallelFor(RNG$, 0, noFlips, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!constrainedFlag$sample47[((var45 - 0) / 1)])
							drawValueSample47(var45, threadID$var45, RNG$1);
					}
			}
		);
		for(int i$var58 = 0; i$var58 < noCats; i$var58 += 1) {
			if(!constrainedFlag$sample62[((i$var58 - 0) / 1)])
				drawValueSample62(i$var58);
		}
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample30)
			logProbability$var30 = Double.NaN;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample47)
			logProbability$var46 = Double.NaN;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample62) {
			for(int i$var58 = 0; i$var58 < noCats; i$var58 += 1)
				logProbability$sample62[((i$var58 - 0) / 1)] = Double.NaN;
		}
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample87) {
			for(int j$var73 = 0; j$var73 < noFlips; j$var73 += 1)
				logProbability$sample87[((j$var73 - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		noFlips = length$flipsMeasured;
		noStates = (length$flipsMeasured / noCats);
		for(int i$var15 = 0; i$var15 < (length$flipsMeasured / noCats); i$var15 += 1)
			v[i$var15] = 0.1;
		for(int index$constrainedFlag$sample47$1 = 0; index$constrainedFlag$sample47$1 < constrainedFlag$sample47.length; index$constrainedFlag$sample47$1 += 1)
			constrainedFlag$sample47[index$constrainedFlag$sample47$1] = true;
		for(int index$constrainedFlag$sample30$1 = 0; index$constrainedFlag$sample30$1 < constrainedFlag$sample30.length; index$constrainedFlag$sample30$1 += 1)
			constrainedFlag$sample30[index$constrainedFlag$sample30$1] = true;
		for(int index$constrainedFlag$sample62$1 = 0; index$constrainedFlag$sample62$1 < constrainedFlag$sample62.length; index$constrainedFlag$sample62$1 += 1)
			constrainedFlag$sample62[index$constrainedFlag$sample62$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample30)
			logProbabilityValue$sample30();
		if(fixedFlag$sample47)
			logProbabilityValue$sample47();
		if(fixedFlag$sample62)
			logProbabilityValue$sample62();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample30();
		logProbabilityValue$sample47();
		logProbabilityValue$sample62();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample30();
		logProbabilityValue$sample47();
		logProbabilityValue$sample62();
		logProbabilityValue$sample87();
	}

	@Override
	public final void propagateObservedValues() {
		boolean[] cv$source1 = flipsMeasured;
		boolean[] cv$target1 = flips;
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
		     + " * Copyright (c) 2019-2023, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model ReductionTest(boolean[] flipsMeasured, int noCats) {\n"
		     + "    int noFlips = flipsMeasured.length;\n"
		     + "    int noStates = noFlips/noCats;\n"
		     + "    \n"
		     + "    double[] v = new double[noStates];\n"
		     + "    for(int i:[0..noStates))\n"
		     + "        v[i] = 0.1;\n"
		     + "    \n"
		     + "    double[][] m = dirichlet(v).sample(noCats);\n"
		     + "    \n"
		     + "    double[] bias = beta(1.0, 1.0).sample(noFlips);\n"
		     + "    \n"
		     + "    int[] st = new int[noCats];\n"
		     + "\n"
		     + "\n"
		     + "    for(int i:[0..noCats))\n"
		     + "        st[i] = categorical(m[i]).sample();\n"
		     + "            \n"
		     + "    boolean[] flips = new boolean[noFlips];\n"
		     + "            \n"
		     + "    for(int j:[0..noFlips))\n"
		     + "        flips[j] = bernoulli(bias[sum(st)]).sample();\n"
		     + "\n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "    \n"
		     + "    private int sum(int[] a) {\n"
		     + "        return reduce(a, 0, (i,j) -> {\n"
		     + "            return i + j;\n"
		     + "        });\n"
		     + "    }\n"
		     + "}";
	}
}