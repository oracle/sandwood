package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ReductionTest$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements ReductionTest$CoreInterface {
	private double[] bias;
	private double[] cv$var32$countGlobal;
	private double[] cv$var63$stateProbabilityGlobal;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample51 = false;
	private boolean fixedFlag$sample66 = false;
	private boolean fixedFlag$sample91 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample51 = false;
	private boolean fixedProbFlag$sample66 = false;
	private boolean fixedProbFlag$sample91 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample66;
	private double[] logProbability$sample91;
	private double logProbability$st;
	private double logProbability$var20;
	private double logProbability$var32;
	private double logProbability$var36;
	private double logProbability$var48;
	private double[] logProbability$var62;
	private double[] logProbability$var86;
	private double[][] m;
	private int noCats;
	private int noFlips;
	private int noStates;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public ReductionTest$SingleThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample51 = false;
		fixedProbFlag$sample91 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	@Override
	public final void set$fixedFlag$sample34(boolean cv$value) {
		fixedFlag$sample34 = cv$value;
		fixedProbFlag$sample34 = (fixedFlag$sample34 && fixedProbFlag$sample34);
		fixedProbFlag$sample66 = (fixedFlag$sample34 && fixedProbFlag$sample66);
	}

	@Override
	public final boolean get$fixedFlag$sample51() {
		return fixedFlag$sample51;
	}

	@Override
	public final void set$fixedFlag$sample51(boolean cv$value) {
		fixedFlag$sample51 = cv$value;
		fixedProbFlag$sample51 = (fixedFlag$sample51 && fixedProbFlag$sample51);
		fixedProbFlag$sample91 = (fixedFlag$sample51 && fixedProbFlag$sample91);
	}

	@Override
	public final boolean get$fixedFlag$sample66() {
		return fixedFlag$sample66;
	}

	@Override
	public final void set$fixedFlag$sample66(boolean cv$value) {
		fixedFlag$sample66 = cv$value;
		fixedProbFlag$sample66 = (fixedFlag$sample66 && fixedProbFlag$sample66);
		fixedProbFlag$sample91 = (fixedFlag$sample66 && fixedProbFlag$sample91);
	}

	@Override
	public final boolean get$fixedFlag$sample91() {
		return fixedFlag$sample91;
	}

	@Override
	public final void set$fixedFlag$sample91(boolean cv$value) {
		fixedFlag$sample91 = cv$value;
		fixedProbFlag$sample91 = (fixedFlag$sample91 && fixedProbFlag$sample91);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample91 = false;
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
	public final void set$m(double[][] cv$value) {
		m = cv$value;
		setFlag$m = true;
		fixedProbFlag$sample34 = false;
		fixedProbFlag$sample66 = false;
	}

	@Override
	public final int get$noCats() {
		return noCats;
	}

	@Override
	public final void set$noCats(int cv$value) {
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
	public final void set$st(int[] cv$value) {
		st = cv$value;
		setFlag$st = true;
		fixedProbFlag$sample66 = false;
		fixedProbFlag$sample91 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample34() {
		if(!fixedProbFlag$sample34) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var31 = 0; var31 < noCats; var31 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var31];
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
			logProbability$var20 = cv$sampleAccumulator;
			logProbability$var32 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample34 = fixedFlag$sample34;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var32;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var20 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample51() {
		if(!fixedProbFlag$sample51) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var47 = 0; var47 < noFlips; var47 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = bias[var47];
					{
						{
							double var34 = 1.0;
							double var35 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var34, var35));
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
			logProbability$var36 = cv$sampleAccumulator;
			logProbability$var48 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample51 = fixedFlag$sample51;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var48;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var36 = cv$rvAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample66() {
		if(!fixedProbFlag$sample66) {
			double cv$accumulator = 0.0;
			for(int i$var60 = 0; i$var60 < noCats; i$var60 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = st[i$var60];
					{
						{
							double[] var61 = m[i$var60];
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var61.length))?Math.log(var61[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var62[((i$var60 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample66[((i$var60 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample66)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample66 = (fixedFlag$sample66 && fixedFlag$sample34);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var60 = 0; i$var60 < noCats; i$var60 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample66[((i$var60 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var62[((i$var60 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample66)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample91() {
		if(!fixedProbFlag$sample91) {
			double cv$accumulator = 0.0;
			for(int j$var75 = 0; j$var75 < noFlips; j$var75 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[j$var75];
					{
						{
							int reduceVar$var84$2 = 0;
							for(int cv$reduction82Index = 0; cv$reduction82Index < noCats; cv$reduction82Index += 1) {
								int i$var81 = reduceVar$var84$2;
								int j$var82 = st[cv$reduction82Index];
								reduceVar$var84$2 = (i$var81 + j$var82);
							}
							double var85 = bias[reduceVar$var84$2];
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var85));
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
				logProbability$var86[((j$var75 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample91[((j$var75 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample91 = ((fixedFlag$sample91 && fixedFlag$sample51) && fixedFlag$sample66);
		} else {
			double cv$accumulator = 0.0;
			for(int j$var75 = 0; j$var75 < noFlips; j$var75 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample91[((j$var75 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var86[((j$var75 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample34(int var31) {
		double[] cv$targetLocal = m[var31];
		double[] cv$countLocal = cv$var32$countGlobal;
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int i$var60 = 0; i$var60 < noCats; i$var60 += 1) {
						if((var31 == i$var60)) {
							{
								{
									{
										{
											{
												cv$countLocal[st[i$var60]] = (cv$countLocal[st[i$var60]] + 1.0);
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
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	private final void sample51(int var47) {
		int cv$sum = 0;
		int cv$count = 0;
		{
			{
				{
					int reduceVar$var84$0 = 0;
					for(int cv$reduction82Index = 0; cv$reduction82Index < noCats; cv$reduction82Index += 1) {
						int i$var81 = reduceVar$var84$0;
						int j$var82 = st[cv$reduction82Index];
						reduceVar$var84$0 = (i$var81 + j$var82);
					}
					if((var47 == reduceVar$var84$0)) {
						for(int j$var75 = 0; j$var75 < noFlips; j$var75 += 1) {
							cv$count = (cv$count + 1);
							if(flips[j$var75])
								cv$sum = (cv$sum + 1);
						}
					}
				}
			}
		}
		double var48 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[var47] = var48;
	}

	private final void sample66(int i$var60) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, noStates);
		}
		double[] cv$stateProbabilityLocal = cv$var63$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			int var63 = cv$currentValue;
			st[i$var60] = cv$currentValue;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var61;
				{
					double[] var61 = m[i$var60];
					cv$temp$0$var61 = var61;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var61.length))?Math.log(cv$temp$0$var61[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$i$1_1 = cv$currentValue;
						if(((0 <= i$var60) && (i$var60 < noCats))) {
							{
								if((0 < noCats)) {
									int reduceVar$var84$1 = 0;
									for(int cv$reduction213Index = 0; cv$reduction213Index < i$var60; cv$reduction213Index += 1) {
										int i$var81 = reduceVar$var84$1;
										int j$var82 = st[cv$reduction213Index];
										reduceVar$var84$1 = (i$var81 + j$var82);
									}
									for(int cv$reduction213Index = (i$var60 + 1); cv$reduction213Index < noCats; cv$reduction213Index += 1) {
										int i$var81 = reduceVar$var84$1;
										int j$var82 = st[cv$reduction213Index];
										reduceVar$var84$1 = (i$var81 + j$var82);
									}
									int cv$reduced82 = reduceVar$var84$1;
									reduceVar$var84$1 = (traceTempVariable$i$1_1 + cv$reduced82);
									int traceTempVariable$var84$1_2 = reduceVar$var84$1;
									for(int j$var75 = 0; j$var75 < noFlips; j$var75 += 1) {
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											{
												{
													{
														double cv$temp$1$var85;
														{
															double var85 = bias[traceTempVariable$var84$1_2];
															cv$temp$1$var85 = var85;
														}
														if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j$var75], cv$temp$1$var85)) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j$var75], cv$temp$1$var85)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j$var75], cv$temp$1$var85));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j$var75], cv$temp$1$var85)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j$var75], cv$temp$1$var85)));
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
		int var63 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
		st[i$var60] = var63;
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			for(int var31 = 0; var31 < noCats; var31 += 1)
				cv$max = Math.max(cv$max, (length$flipsMeasured / noCats));
			cv$var32$countGlobal = new double[cv$max];
		}
		{
			int cv$var33$max = ((0.0 <= length$flipsMeasured)?((0.0 <= noCats)?(length$flipsMeasured / noCats):((noCats < 0.0)?(length$flipsMeasured / noCats):length$flipsMeasured)):((length$flipsMeasured < 0.0)?((0.0 <= noCats)?(length$flipsMeasured / noCats):((noCats < 0.0)?(length$flipsMeasured / noCats):(-length$flipsMeasured))):((0.0 <= noCats)?(length$flipsMeasured / noCats):((noCats < 0.0)?(length$flipsMeasured / noCats):Math.max(length$flipsMeasured, (-length$flipsMeasured))))));
			cv$var63$stateProbabilityGlobal = new double[cv$var33$max];
		}
	}

	@Override
	public final void allocator() {
		{
			v = new double[(length$flipsMeasured / noCats)];
		}
		if(!setFlag$m) {
			{
				m = new double[noCats][];
				for(int var31 = 0; var31 < noCats; var31 += 1)
					m[var31] = new double[(length$flipsMeasured / noCats)];
			}
		}
		if(!setFlag$bias) {
			{
				bias = new double[length$flipsMeasured];
			}
		}
		if(!setFlag$st) {
			{
				st = new int[noCats];
			}
		}
		if(!setFlag$flips) {
			{
				flips = new boolean[length$flipsMeasured];
			}
		}
		{
			logProbability$var62 = new double[((((noCats - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample66 = new double[((((noCats - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var86 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample91 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int var31 = 0; var31 < noCats; var31 += 1) {
			double[] var32 = m[var31];
			if(!fixedFlag$sample34)
				DistributionSampling.sampleDirichlet(RNG$, v, var32);
		}
		for(int var47 = 0; var47 < noFlips; var47 += 1) {
			if(!fixedFlag$sample51)
				bias[var47] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int i$var60 = 0; i$var60 < noCats; i$var60 += 1) {
			if(!fixedFlag$sample66)
				st[i$var60] = DistributionSampling.sampleCategorical(RNG$, m[i$var60]);
		}
		for(int j$var75 = 0; j$var75 < noFlips; j$var75 += 1) {
			int reduceVar$var84$3 = 0;
			for(int cv$reduction82Index = 0; cv$reduction82Index < noCats; cv$reduction82Index += 1) {
				int i$var81 = reduceVar$var84$3;
				int j$var82 = st[cv$reduction82Index];
				if(!fixedFlag$sample91)
					reduceVar$var84$3 = (i$var81 + j$var82);
			}
			if(!fixedFlag$sample91)
				flips[j$var75] = DistributionSampling.sampleBernoulli(RNG$, bias[reduceVar$var84$3]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var31 = 0; var31 < noCats; var31 += 1) {
			double[] var32 = m[var31];
			if(!fixedFlag$sample34)
				DistributionSampling.sampleDirichlet(RNG$, v, var32);
		}
		for(int var47 = 0; var47 < noFlips; var47 += 1) {
			if(!fixedFlag$sample51)
				bias[var47] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int i$var60 = 0; i$var60 < noCats; i$var60 += 1) {
			if(!fixedFlag$sample66)
				st[i$var60] = DistributionSampling.sampleCategorical(RNG$, m[i$var60]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var31 = 0; var31 < noCats; var31 += 1) {
			double[] var32 = m[var31];
			if(!fixedFlag$sample34)
				DistributionSampling.sampleDirichlet(RNG$, v, var32);
		}
		for(int var47 = 0; var47 < noFlips; var47 += 1) {
			if(!fixedFlag$sample51)
				bias[var47] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int i$var60 = 0; i$var60 < noCats; i$var60 += 1) {
			if(!fixedFlag$sample66)
				st[i$var60] = DistributionSampling.sampleCategorical(RNG$, m[i$var60]);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var31 = 0; var31 < noCats; var31 += 1) {
				if(!fixedFlag$sample34)
					sample34(var31);
			}
			for(int var47 = 0; var47 < noFlips; var47 += 1) {
				if(!fixedFlag$sample51)
					sample51(var47);
			}
			for(int i$var60 = 0; i$var60 < noCats; i$var60 += 1) {
				if(!fixedFlag$sample66)
					sample66(i$var60);
			}
		} else {
			for(int i$var60 = (noCats - ((((noCats - 1) - 0) % 1) + 1)); i$var60 >= ((0 - 1) + 1); i$var60 -= 1) {
				if(!fixedFlag$sample66)
					sample66(i$var60);
			}
			for(int var47 = (noFlips - ((((noFlips - 1) - 0) % 1) + 1)); var47 >= ((0 - 1) + 1); var47 -= 1) {
				if(!fixedFlag$sample51)
					sample51(var47);
			}
			for(int var31 = (noCats - ((((noCats - 1) - 0) % 1) + 1)); var31 >= ((0 - 1) + 1); var31 -= 1) {
				if(!fixedFlag$sample34)
					sample34(var31);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		noFlips = length$flipsMeasured;
		noStates = (length$flipsMeasured / noCats);
		for(int i$var17 = 0; i$var17 < (length$flipsMeasured / noCats); i$var17 += 1)
			v[i$var17] = 0.1;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var20 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample34)
			logProbability$var32 = 0.0;
		logProbability$var36 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample51)
			logProbability$var48 = 0.0;
		for(int i$var60 = 0; i$var60 < noCats; i$var60 += 1)
			logProbability$var62[((i$var60 - 0) / 1)] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample66) {
			for(int i$var60 = 0; i$var60 < noCats; i$var60 += 1)
				logProbability$sample66[((i$var60 - 0) / 1)] = 0.0;
		}
		for(int j$var75 = 0; j$var75 < noFlips; j$var75 += 1)
			logProbability$var86[((j$var75 - 0) / 1)] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample91) {
			for(int j$var75 = 0; j$var75 < noFlips; j$var75 += 1)
				logProbability$sample91[((j$var75 - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(fixedFlag$sample51)
			logProbabilityValue$sample51();
		if(fixedFlag$sample66)
			logProbabilityValue$sample66();
		logProbabilityValue$sample91();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample34();
		logProbabilityValue$sample51();
		logProbabilityValue$sample66();
		logProbabilityValue$sample91();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample34();
		logProbabilityValue$sample51();
		logProbabilityValue$sample66();
		logProbabilityValue$sample91();
	}

	@Override
	public final void logProbabilityGeneration() {
		for(int var31 = 0; var31 < noCats; var31 += 1) {
			double[] var32 = m[var31];
			if(!fixedFlag$sample34)
				DistributionSampling.sampleDirichlet(RNG$, v, var32);
		}
		for(int var47 = 0; var47 < noFlips; var47 += 1) {
			if(!fixedFlag$sample51)
				bias[var47] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int i$var60 = 0; i$var60 < noCats; i$var60 += 1) {
			if(!fixedFlag$sample66)
				st[i$var60] = DistributionSampling.sampleCategorical(RNG$, m[i$var60]);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel ReductionTest(boolean[] flipsMeasured, int noCats) {\n    int noFlips = flipsMeasured.length;\n    int noStates = noFlips/noCats;\n    \n    double[] v = new double[noStates];\n    for(int i:[0..noStates))\n        v[i] = 0.1;\n    \n    double[][] m = dirichlet(v).sample(noCats);\n    \n    double[] bias = beta(1.0, 1.0).sample(noFlips);\n    \n    int[] st = new int[noCats];\n\n\n    for(int i:[0..noCats))\n        st[i] = categorical(m[i]).sample();\n            \n    boolean[] flips = new boolean[noFlips];\n            \n    for(int j:[0..noFlips))\n        flips[j] = bernoulli(bias[sum(st)]).sample();\n\n    flips.observe(flipsMeasured);\n    \n    private int sum(int[] a) {\n        return reduce(a, 0, (i,j) -> {\n            return i + j;\n        });\n    }\n}";
	}
}