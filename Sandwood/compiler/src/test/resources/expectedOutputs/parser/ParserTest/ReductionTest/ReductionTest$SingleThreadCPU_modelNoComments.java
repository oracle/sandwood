package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ReductionTest$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements ReductionTest$CoreInterface {
	private double[] bias;
	private double[] cv$var18$countGlobal;
	private double[] cv$var35$stateProbabilityGlobal;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample30 = false;
	private boolean fixedFlag$sample38 = false;
	private boolean fixedFlag$sample54 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample30 = false;
	private boolean fixedProbFlag$sample38 = false;
	private boolean fixedProbFlag$sample54 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample38;
	private double[] logProbability$sample54;
	private double logProbability$st;
	private double logProbability$var13;
	private double logProbability$var18;
	private double logProbability$var22;
	private double logProbability$var27;
	private double[] logProbability$var34;
	private double[] logProbability$var49;
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
	}

	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	@Override
	public final void set$fixedFlag$sample20(boolean cv$value) {
		fixedFlag$sample20 = cv$value;
		fixedProbFlag$sample20 = (fixedFlag$sample20 && fixedProbFlag$sample20);
		fixedProbFlag$sample38 = (fixedFlag$sample20 && fixedProbFlag$sample38);
	}

	@Override
	public final boolean get$fixedFlag$sample30() {
		return fixedFlag$sample30;
	}

	@Override
	public final void set$fixedFlag$sample30(boolean cv$value) {
		fixedFlag$sample30 = cv$value;
		fixedProbFlag$sample30 = (fixedFlag$sample30 && fixedProbFlag$sample30);
		fixedProbFlag$sample54 = (fixedFlag$sample30 && fixedProbFlag$sample54);
	}

	@Override
	public final boolean get$fixedFlag$sample38() {
		return fixedFlag$sample38;
	}

	@Override
	public final void set$fixedFlag$sample38(boolean cv$value) {
		fixedFlag$sample38 = cv$value;
		fixedProbFlag$sample38 = (fixedFlag$sample38 && fixedProbFlag$sample38);
		fixedProbFlag$sample54 = (fixedFlag$sample38 && fixedProbFlag$sample54);
	}

	@Override
	public final boolean get$fixedFlag$sample54() {
		return fixedFlag$sample54;
	}

	@Override
	public final void set$fixedFlag$sample54(boolean cv$value) {
		fixedFlag$sample54 = cv$value;
		fixedProbFlag$sample54 = (fixedFlag$sample54 && fixedProbFlag$sample54);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
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
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample20() {
		if(!fixedProbFlag$sample20) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var17 = 0; var17 < noCats; var17 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var17];
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
			logProbability$var13 = cv$sampleAccumulator;
			logProbability$var18 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample20 = fixedFlag$sample20;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var18;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var13 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample30() {
		if(!fixedProbFlag$sample30) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var26 = 0; var26 < noFlips; var26 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = bias[var26];
					{
						{
							double var20 = 1.0;
							double var21 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var20, var21));
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
			logProbability$var22 = cv$sampleAccumulator;
			logProbability$var27 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample30 = fixedFlag$sample30;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var27;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var22 = cv$rvAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample38() {
		if(!fixedProbFlag$sample38) {
			double cv$accumulator = 0.0;
			for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = st[i$var32];
					{
						{
							double[] var33 = m[i$var32];
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var33.length))?Math.log(var33[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var34[((i$var32 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample38[((i$var32 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample38)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample38 = (fixedFlag$sample38 && fixedFlag$sample20);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample38[((i$var32 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var34[((i$var32 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample38)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample54() {
		if(!fixedProbFlag$sample54) {
			double cv$accumulator = 0.0;
			for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[j$var40];
					{
						{
							int reduceVar$var47$2 = 0;
							for(int cv$reduction47Index = 0; cv$reduction47Index < noCats; cv$reduction47Index += 1) {
								int i$var44 = reduceVar$var47$2;
								int j$var45 = st[cv$reduction47Index];
								reduceVar$var47$2 = (i$var44 + j$var45);
							}
							double var48 = bias[reduceVar$var47$2];
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var48));
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
				logProbability$var49[((j$var40 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample54[((j$var40 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample54 = ((fixedFlag$sample54 && fixedFlag$sample30) && fixedFlag$sample38);
		} else {
			double cv$accumulator = 0.0;
			for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample54[((j$var40 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var49[((j$var40 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample20(int var17) {
		double[] cv$targetLocal = m[var17];
		double[] cv$countLocal = cv$var18$countGlobal;
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1) {
						if((var17 == i$var32)) {
							{
								{
									{
										{
											{
												cv$countLocal[st[i$var32]] = (cv$countLocal[st[i$var32]] + 1.0);
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

	private final void sample30(int var26) {
		int cv$sum = 0;
		int cv$count = 0;
		{
			{
				{
					int reduceVar$var47$0 = 0;
					for(int cv$reduction47Index = 0; cv$reduction47Index < noCats; cv$reduction47Index += 1) {
						int i$var44 = reduceVar$var47$0;
						int j$var45 = st[cv$reduction47Index];
						reduceVar$var47$0 = (i$var44 + j$var45);
					}
					if((var26 == reduceVar$var47$0)) {
						for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1) {
							cv$count = (cv$count + 1);
							if(flips[j$var40])
								cv$sum = (cv$sum + 1);
						}
					}
				}
			}
		}
		double var27 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[var26] = var27;
	}

	private final void sample38(int i$var32) {
		double[] cv$stateProbabilityLocal = cv$var35$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			int var35 = cv$currentValue;
			st[i$var32] = cv$currentValue;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var33;
				{
					double[] var33 = m[i$var32];
					cv$temp$0$var33 = var33;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var33.length))?Math.log(cv$temp$0$var33[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$i$1_1 = cv$currentValue;
						if(((0 <= i$var32) && (i$var32 < noCats))) {
							{
								if((0 < noCats)) {
									int reduceVar$var47$1 = 0;
									for(int cv$reduction125Index = 0; cv$reduction125Index < i$var32; cv$reduction125Index += 1) {
										int i$var44 = reduceVar$var47$1;
										int j$var45 = st[cv$reduction125Index];
										reduceVar$var47$1 = (i$var44 + j$var45);
									}
									for(int cv$reduction125Index = (i$var32 + 1); cv$reduction125Index < noCats; cv$reduction125Index += 1) {
										int i$var44 = reduceVar$var47$1;
										int j$var45 = st[cv$reduction125Index];
										reduceVar$var47$1 = (i$var44 + j$var45);
									}
									int cv$reduced47 = reduceVar$var47$1;
									reduceVar$var47$1 = (traceTempVariable$i$1_1 + cv$reduced47);
									int traceTempVariable$var47$1_2 = reduceVar$var47$1;
									for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1) {
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											{
												{
													{
														double cv$temp$1$var48;
														{
															double var48 = bias[traceTempVariable$var47$1_2];
															cv$temp$1$var48 = var48;
														}
														if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j$var40], cv$temp$1$var48)) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j$var40], cv$temp$1$var48)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j$var40], cv$temp$1$var48));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j$var40], cv$temp$1$var48)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j$var40], cv$temp$1$var48)));
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
			for(int cv$lseIndex = 1; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$stateProbabilityLocal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		int var35 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
		st[i$var32] = var35;
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			for(int var17 = 0; var17 < noCats; var17 += 1)
				cv$max = Math.max(cv$max, (length$flipsMeasured / noCats));
			cv$var18$countGlobal = new double[cv$max];
		}
		{
			int cv$var19$max = ((0.0 <= length$flipsMeasured)?((0.0 <= noCats)?(length$flipsMeasured / noCats):((noCats < 0.0)?(length$flipsMeasured / noCats):length$flipsMeasured)):((length$flipsMeasured < 0.0)?((0.0 <= noCats)?(length$flipsMeasured / noCats):((noCats < 0.0)?(length$flipsMeasured / noCats):(-length$flipsMeasured))):((0.0 <= noCats)?(length$flipsMeasured / noCats):((noCats < 0.0)?(length$flipsMeasured / noCats):Math.max(length$flipsMeasured, (-length$flipsMeasured))))));
			cv$var35$stateProbabilityGlobal = new double[cv$var19$max];
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
				for(int var17 = 0; var17 < noCats; var17 += 1)
					m[var17] = new double[(length$flipsMeasured / noCats)];
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
			logProbability$var34 = new double[((((noCats - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample38 = new double[((((noCats - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var49 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample54 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int var17 = 0; var17 < noCats; var17 += 1) {
			double[] var18 = m[var17];
			if(!fixedFlag$sample20)
				DistributionSampling.sampleDirichlet(RNG$, v, var18);
		}
		for(int var26 = 0; var26 < noFlips; var26 += 1) {
			if(!fixedFlag$sample30)
				bias[var26] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1) {
			if(!fixedFlag$sample38)
				st[i$var32] = DistributionSampling.sampleCategorical(RNG$, m[i$var32]);
		}
		for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1) {
			int reduceVar$var47$3 = 0;
			for(int cv$reduction47Index = 0; cv$reduction47Index < noCats; cv$reduction47Index += 1) {
				int i$var44 = reduceVar$var47$3;
				int j$var45 = st[cv$reduction47Index];
				if(!fixedFlag$sample54)
					reduceVar$var47$3 = (i$var44 + j$var45);
			}
			if(!fixedFlag$sample54)
				flips[j$var40] = DistributionSampling.sampleBernoulli(RNG$, bias[reduceVar$var47$3]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var17 = 0; var17 < noCats; var17 += 1) {
			double[] var18 = m[var17];
			if(!fixedFlag$sample20)
				DistributionSampling.sampleDirichlet(RNG$, v, var18);
		}
		for(int var26 = 0; var26 < noFlips; var26 += 1) {
			if(!fixedFlag$sample30)
				bias[var26] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1) {
			if(!fixedFlag$sample38)
				st[i$var32] = DistributionSampling.sampleCategorical(RNG$, m[i$var32]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var17 = 0; var17 < noCats; var17 += 1) {
			double[] var18 = m[var17];
			if(!fixedFlag$sample20)
				DistributionSampling.sampleDirichlet(RNG$, v, var18);
		}
		for(int var26 = 0; var26 < noFlips; var26 += 1) {
			if(!fixedFlag$sample30)
				bias[var26] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1) {
			if(!fixedFlag$sample38)
				st[i$var32] = DistributionSampling.sampleCategorical(RNG$, m[i$var32]);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var17 = 0; var17 < noCats; var17 += 1) {
				if(!fixedFlag$sample20)
					sample20(var17);
			}
			for(int var26 = 0; var26 < noFlips; var26 += 1) {
				if(!fixedFlag$sample30)
					sample30(var26);
			}
			for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1) {
				if(!fixedFlag$sample38)
					sample38(i$var32);
			}
		} else {
			for(int i$var32 = (noCats - ((((noCats - 1) - 0) % 1) + 1)); i$var32 >= ((0 - 1) + 1); i$var32 -= 1) {
				if(!fixedFlag$sample38)
					sample38(i$var32);
			}
			for(int var26 = (noFlips - ((((noFlips - 1) - 0) % 1) + 1)); var26 >= ((0 - 1) + 1); var26 -= 1) {
				if(!fixedFlag$sample30)
					sample30(var26);
			}
			for(int var17 = (noCats - ((((noCats - 1) - 0) % 1) + 1)); var17 >= ((0 - 1) + 1); var17 -= 1) {
				if(!fixedFlag$sample20)
					sample20(var17);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		noFlips = length$flipsMeasured;
		noStates = (length$flipsMeasured / noCats);
		for(int i$var10 = 0; i$var10 < (length$flipsMeasured / noCats); i$var10 += 1)
			v[i$var10] = 0.1;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var13 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$var18 = 0.0;
		logProbability$var22 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample30)
			logProbability$var27 = 0.0;
		for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1)
			logProbability$var34[((i$var32 - 0) / 1)] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample38) {
			for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1)
				logProbability$sample38[((i$var32 - 0) / 1)] = 0.0;
		}
		for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1)
			logProbability$var49[((j$var40 - 0) / 1)] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample54) {
			for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1)
				logProbability$sample54[((j$var40 - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample30)
			logProbabilityValue$sample30();
		if(fixedFlag$sample38)
			logProbabilityValue$sample38();
		logProbabilityValue$sample54();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample30();
		logProbabilityValue$sample38();
		logProbabilityValue$sample54();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample30();
		logProbabilityValue$sample38();
		logProbabilityValue$sample54();
	}

	@Override
	public final void logProbabilityGeneration() {
		for(int var17 = 0; var17 < noCats; var17 += 1) {
			double[] var18 = m[var17];
			if(!fixedFlag$sample20)
				DistributionSampling.sampleDirichlet(RNG$, v, var18);
		}
		for(int var26 = 0; var26 < noFlips; var26 += 1) {
			if(!fixedFlag$sample30)
				bias[var26] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1) {
			if(!fixedFlag$sample38)
				st[i$var32] = DistributionSampling.sampleCategorical(RNG$, m[i$var32]);
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