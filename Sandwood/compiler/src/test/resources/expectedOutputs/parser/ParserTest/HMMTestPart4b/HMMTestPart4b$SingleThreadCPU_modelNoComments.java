package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart4b$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMTestPart4b$CoreInterface {
	private double[] bias;
	private double[] cv$var123$stateProbabilityGlobal;
	private double[] cv$var32$countGlobal;
	private double[] cv$var83$stateProbabilityGlobal;
	private boolean fixedFlag$sample128 = false;
	private boolean fixedFlag$sample195 = false;
	private boolean fixedFlag$sample33 = false;
	private boolean fixedFlag$sample50 = false;
	private boolean fixedFlag$sample88 = false;
	private boolean fixedProbFlag$sample128 = false;
	private boolean fixedProbFlag$sample195 = false;
	private boolean fixedProbFlag$sample33 = false;
	private boolean fixedProbFlag$sample50 = false;
	private boolean fixedProbFlag$sample88 = false;
	private boolean[][][] flips;
	private boolean[][][] flipsMeasured;
	private int[][] length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[][][] logProbability$sample128;
	private double[][][] logProbability$sample195;
	private double logProbability$st;
	private double[][][] logProbability$var122;
	private double[][][] logProbability$var187;
	private double logProbability$var20;
	private double logProbability$var32;
	private double logProbability$var36;
	private double logProbability$var48;
	private double logProbability$var82;
	private double logProbability$var83;
	private double[][] m;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[][][] st;
	private int states;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart4b$SingleThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample50 = false;
		fixedProbFlag$sample195 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample128() {
		return fixedFlag$sample128;
	}

	@Override
	public final void set$fixedFlag$sample128(boolean cv$value) {
		fixedFlag$sample128 = cv$value;
		fixedProbFlag$sample128 = (fixedFlag$sample128 && fixedProbFlag$sample128);
		fixedProbFlag$sample195 = (fixedFlag$sample128 && fixedProbFlag$sample195);
	}

	@Override
	public final boolean get$fixedFlag$sample195() {
		return fixedFlag$sample195;
	}

	@Override
	public final void set$fixedFlag$sample195(boolean cv$value) {
		fixedFlag$sample195 = cv$value;
		fixedProbFlag$sample195 = (fixedFlag$sample195 && fixedProbFlag$sample195);
	}

	@Override
	public final boolean get$fixedFlag$sample33() {
		return fixedFlag$sample33;
	}

	@Override
	public final void set$fixedFlag$sample33(boolean cv$value) {
		fixedFlag$sample33 = cv$value;
		fixedProbFlag$sample33 = (fixedFlag$sample33 && fixedProbFlag$sample33);
		fixedProbFlag$sample88 = (fixedFlag$sample33 && fixedProbFlag$sample88);
		fixedProbFlag$sample128 = (fixedFlag$sample33 && fixedProbFlag$sample128);
	}

	@Override
	public final boolean get$fixedFlag$sample50() {
		return fixedFlag$sample50;
	}

	@Override
	public final void set$fixedFlag$sample50(boolean cv$value) {
		fixedFlag$sample50 = cv$value;
		fixedProbFlag$sample50 = (fixedFlag$sample50 && fixedProbFlag$sample50);
		fixedProbFlag$sample195 = (fixedFlag$sample50 && fixedProbFlag$sample195);
	}

	@Override
	public final boolean get$fixedFlag$sample88() {
		return fixedFlag$sample88;
	}

	@Override
	public final void set$fixedFlag$sample88(boolean cv$value) {
		fixedFlag$sample88 = cv$value;
		fixedProbFlag$sample88 = (fixedFlag$sample88 && fixedProbFlag$sample88);
		fixedProbFlag$sample195 = (fixedFlag$sample88 && fixedProbFlag$sample195);
	}

	@Override
	public final boolean[][][] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[][][] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample195 = false;
	}

	@Override
	public final boolean[][][] get$flipsMeasured() {
		return flipsMeasured;
	}

	@Override
	public final void set$flipsMeasured(boolean[][][] cv$value) {
		flipsMeasured = cv$value;
	}

	@Override
	public final int[][] get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	@Override
	public final void set$length$flipsMeasured(int[][] cv$value) {
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
		fixedProbFlag$sample33 = false;
		fixedProbFlag$sample88 = false;
		fixedProbFlag$sample128 = false;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	@Override
	public final int[][][] get$st() {
		return st;
	}

	@Override
	public final void set$st(int[][][] cv$value) {
		st = cv$value;
		setFlag$st = true;
		fixedProbFlag$sample88 = false;
		fixedProbFlag$sample128 = false;
		fixedProbFlag$sample195 = false;
	}

	@Override
	public final int get$states() {
		return states;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample128() {
		if(!fixedProbFlag$sample128) {
			double cv$accumulator = 0.0;
			for(int i$var99 = 1; i$var99 < samples; i$var99 += 1) {
				for(int j$var108 = 0; j$var108 < samples; j$var108 += 1) {
					for(int k = 0; k < samples; k += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							int cv$sampleValue = st[i$var99][j$var108][k];
							{
								{
									double[] var121 = m[0];
									double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var121.length))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						logProbability$var122[((i$var99 - 1) / 1)][((j$var108 - 0) / 1)][((k - 0) / 1)] = cv$sampleAccumulator;
						logProbability$sample128[((i$var99 - 1) / 1)][((j$var108 - 0) / 1)][((k - 0) / 1)] = cv$sampleProbability;
					}
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample128)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample128 = (fixedFlag$sample128 && fixedFlag$sample33);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var99 = 1; i$var99 < samples; i$var99 += 1) {
				for(int j$var108 = 0; j$var108 < samples; j$var108 += 1) {
					for(int k = 0; k < samples; k += 1) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample128[((i$var99 - 1) / 1)][((j$var108 - 0) / 1)][((k - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var122[((i$var99 - 1) / 1)][((j$var108 - 0) / 1)][((k - 0) / 1)] = cv$rvAccumulator;
					}
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample128)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample195() {
		if(!fixedProbFlag$sample195) {
			double cv$accumulator = 0.0;
			for(int l = 0; l < samples; l += 1) {
				for(int p = 0; p < samples; p += 1) {
					for(int n = 0; n < samples; n += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							boolean cv$sampleValue = flips[l][n][p];
							{
								{
									double var186 = bias[st[p][l][n]];
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var186));
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
						logProbability$var187[((l - 0) / 1)][((p - 0) / 1)][((n - 0) / 1)] = cv$sampleAccumulator;
						logProbability$sample195[((l - 0) / 1)][((p - 0) / 1)][((n - 0) / 1)] = cv$sampleProbability;
					}
				}
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample195 = (((fixedFlag$sample195 && fixedFlag$sample50) && fixedFlag$sample88) && fixedFlag$sample128);
		} else {
			double cv$accumulator = 0.0;
			for(int l = 0; l < samples; l += 1) {
				for(int p = 0; p < samples; p += 1) {
					for(int n = 0; n < samples; n += 1) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample195[((l - 0) / 1)][((p - 0) / 1)][((n - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var187[((l - 0) / 1)][((p - 0) / 1)][((n - 0) / 1)] = cv$rvAccumulator;
					}
				}
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample33() {
		if(!fixedProbFlag$sample33) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var31 = 0; var31 < states; var31 += 1) {
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
			if(fixedFlag$sample33)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample33 = fixedFlag$sample33;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var32;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var20 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample33)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample50() {
		if(!fixedProbFlag$sample50) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var47 = 0; var47 < states; var47 += 1) {
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
			if(fixedFlag$sample50)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample50 = fixedFlag$sample50;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var48;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var36 = cv$rvAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample50)
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
				int cv$sampleValue = st[0][0][0];
				{
					{
						double[] var81 = m[0];
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var81.length))?Math.log(var81[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var82 = cv$sampleAccumulator;
			logProbability$var83 = cv$sampleProbability;
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample88 = (fixedFlag$sample88 && fixedFlag$sample33);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var83;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var82 = cv$rvAccumulator;
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample128(int i$var99, int j$var108, int k) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, states);
		}
		double[] cv$stateProbabilityLocal = cv$var123$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			int var123 = cv$currentValue;
			int[][] var118 = st[i$var99];
			int[] var119 = var118[j$var108];
			var119[k] = cv$currentValue;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var121;
				{
					double[] var121 = m[0];
					cv$temp$0$var121 = var121;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var121.length))?Math.log(cv$temp$0$var121[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var185$1_1 = cv$currentValue;
						for(int p = 0; p < samples; p += 1) {
							if((i$var99 == p)) {
								for(int l = 0; l < samples; l += 1) {
									if((j$var108 == l)) {
										for(int n = 0; n < samples; n += 1) {
											if((k == n)) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	double cv$temp$1$var186;
																	{
																		double var186 = bias[traceTempVariable$var185$1_1];
																		cv$temp$1$var186 = var186;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$1$var186)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$1$var186)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$1$var186));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$1$var186)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$1$var186)));
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
		int var123 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
		int[][] var118 = st[i$var99];
		int[] var119 = var118[j$var108];
		var119[k] = var123;
	}

	private final void sample33(int var31) {
		double[] cv$targetLocal = m[var31];
		double[] cv$countLocal = cv$var32$countGlobal;
		int cv$arrayLength = states;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					if((var31 == 0)) {
						{
							{
								{
									{
										{
											cv$countLocal[st[0][0][0]] = (cv$countLocal[st[0][0][0]] + 1.0);
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
					if((var31 == 0)) {
						for(int i$var99 = 1; i$var99 < samples; i$var99 += 1) {
							for(int j$var108 = 0; j$var108 < samples; j$var108 += 1) {
								for(int k = 0; k < samples; k += 1)
									cv$countLocal[st[i$var99][j$var108][k]] = (cv$countLocal[st[i$var99][j$var108][k]] + 1.0);
							}
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	private final void sample50(int var47) {
		int cv$sum = 0;
		int cv$count = 0;
		{
			{
				{
					for(int l = 0; l < samples; l += 1) {
						for(int p = 0; p < samples; p += 1) {
							for(int n = 0; n < samples; n += 1) {
								if((var47 == st[p][l][n])) {
									{
										{
											{
												{
													{
														cv$count = (cv$count + 1);
														if(flips[l][n][p])
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
		double var48 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[var47] = var48;
	}

	private final void sample88() {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, states);
		}
		double[] cv$stateProbabilityLocal = cv$var83$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			int var83 = cv$currentValue;
			int[][] var76 = st[0];
			int[] var78 = var76[0];
			var78[0] = cv$currentValue;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var81;
				{
					double[] var81 = m[0];
					cv$temp$0$var81 = var81;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var81.length))?Math.log(cv$temp$0$var81[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var185$1_1 = cv$currentValue;
						for(int p = 0; p < samples; p += 1) {
							if((0 == p)) {
								for(int l = 0; l < samples; l += 1) {
									if((0 == l)) {
										for(int n = 0; n < samples; n += 1) {
											if((0 == n)) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	double cv$temp$1$var186;
																	{
																		double var186 = bias[traceTempVariable$var185$1_1];
																		cv$temp$1$var186 = var186;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$1$var186)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$1$var186)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$1$var186));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$1$var186)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$1$var186)));
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
		int var83 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
		int[][] var76 = st[0];
		int[] var78 = var76[0];
		var78[0] = var83;
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			for(int var31 = 0; var31 < 2; var31 += 1)
				cv$max = Math.max(cv$max, 2);
			cv$var32$countGlobal = new double[cv$max];
		}
		{
			int cv$var33$max = 2;
			cv$var83$stateProbabilityGlobal = new double[cv$var33$max];
		}
		{
			int cv$var33$max = 2;
			cv$var123$stateProbabilityGlobal = new double[cv$var33$max];
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
				for(int var31 = 0; var31 < 2; var31 += 1)
					m[var31] = new double[2];
			}
		}
		if(!setFlag$bias) {
			{
				bias = new double[2];
			}
		}
		if(!setFlag$st) {
			{
				st = new int[length$flipsMeasured.length][][];
				for(int var61 = 0; var61 < length$flipsMeasured.length; var61 += 1) {
					int[][] subarray$0 = new int[length$flipsMeasured.length][];
					st[var61] = subarray$0;
					for(int var71 = 0; var71 < length$flipsMeasured.length; var71 += 1)
						subarray$0[var71] = new int[length$flipsMeasured.length];
				}
			}
		}
		if(!setFlag$flips) {
			{
				flips = new boolean[length$flipsMeasured.length][][];
				for(int i$var137 = 0; i$var137 < length$flipsMeasured.length; i$var137 += 1) {
					boolean[][] subarray$0 = new boolean[length$flipsMeasured.length][];
					flips[i$var137] = subarray$0;
					for(int j$var148 = 0; j$var148 < length$flipsMeasured.length; j$var148 += 1)
						subarray$0[j$var148] = new boolean[length$flipsMeasured.length];
				}
			}
		}
		{
			logProbability$var122 = new double[((((length$flipsMeasured.length - 1) - 1) / 1) + 1)][][];
			for(int i$var99 = 1; i$var99 < length$flipsMeasured.length; i$var99 += 1) {
				double[][] subarray$0 = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)][];
				logProbability$var122[((i$var99 - 1) / 1)] = subarray$0;
				for(int j$var108 = 0; j$var108 < length$flipsMeasured.length; j$var108 += 1)
					subarray$0[((j$var108 - 0) / 1)] = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$sample128 = new double[((((length$flipsMeasured.length - 1) - 1) / 1) + 1)][][];
			for(int i$var99 = 1; i$var99 < length$flipsMeasured.length; i$var99 += 1) {
				double[][] subarray$0 = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)][];
				logProbability$sample128[((i$var99 - 1) / 1)] = subarray$0;
				for(int j$var108 = 0; j$var108 < length$flipsMeasured.length; j$var108 += 1)
					subarray$0[((j$var108 - 0) / 1)] = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$var187 = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)][][];
			for(int l = 0; l < length$flipsMeasured.length; l += 1) {
				double[][] subarray$0 = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)][];
				logProbability$var187[((l - 0) / 1)] = subarray$0;
				for(int p = 0; p < length$flipsMeasured.length; p += 1)
					subarray$0[((p - 0) / 1)] = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$sample195 = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)][][];
			for(int l = 0; l < length$flipsMeasured.length; l += 1) {
				double[][] subarray$0 = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)][];
				logProbability$sample195[((l - 0) / 1)] = subarray$0;
				for(int p = 0; p < length$flipsMeasured.length; p += 1)
					subarray$0[((p - 0) / 1)] = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)];
			}
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int var31 = 0; var31 < states; var31 += 1) {
			double[] var32 = m[var31];
			if(!fixedFlag$sample33)
				DistributionSampling.sampleDirichlet(RNG$, v, var32);
		}
		for(int var47 = 0; var47 < states; var47 += 1) {
			if(!fixedFlag$sample50)
				bias[var47] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		int[][] var76 = st[0];
		int[] var78 = var76[0];
		if(!fixedFlag$sample88)
			var78[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		for(int i$var99 = 1; i$var99 < samples; i$var99 += 1) {
			int[][] var118 = st[i$var99];
			for(int j$var108 = 0; j$var108 < samples; j$var108 += 1) {
				for(int k = 0; k < samples; k += 1) {
					int[] var119 = var118[j$var108];
					if(!fixedFlag$sample128)
						var119[k] = DistributionSampling.sampleCategorical(RNG$, m[0]);
				}
			}
		}
		for(int l = 0; l < samples; l += 1) {
			boolean[][] var181 = flips[l];
			for(int p = 0; p < samples; p += 1) {
				for(int n = 0; n < samples; n += 1) {
					boolean[] var182 = var181[n];
					if(!fixedFlag$sample195)
						var182[p] = DistributionSampling.sampleBernoulli(RNG$, bias[st[p][l][n]]);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var31 = 0; var31 < states; var31 += 1) {
			double[] var32 = m[var31];
			if(!fixedFlag$sample33)
				DistributionSampling.sampleDirichlet(RNG$, v, var32);
		}
		for(int var47 = 0; var47 < states; var47 += 1) {
			if(!fixedFlag$sample50)
				bias[var47] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		int[][] var76 = st[0];
		int[] var78 = var76[0];
		if(!fixedFlag$sample88)
			var78[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		for(int i$var99 = 1; i$var99 < samples; i$var99 += 1) {
			int[][] var118 = st[i$var99];
			for(int j$var108 = 0; j$var108 < samples; j$var108 += 1) {
				for(int k = 0; k < samples; k += 1) {
					int[] var119 = var118[j$var108];
					if(!fixedFlag$sample128)
						var119[k] = DistributionSampling.sampleCategorical(RNG$, m[0]);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var31 = 0; var31 < states; var31 += 1) {
			double[] var32 = m[var31];
			if(!fixedFlag$sample33)
				DistributionSampling.sampleDirichlet(RNG$, v, var32);
		}
		for(int var47 = 0; var47 < states; var47 += 1) {
			if(!fixedFlag$sample50)
				bias[var47] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		int[][] var76 = st[0];
		int[] var78 = var76[0];
		if(!fixedFlag$sample88)
			var78[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		for(int i$var99 = 1; i$var99 < samples; i$var99 += 1) {
			int[][] var118 = st[i$var99];
			for(int j$var108 = 0; j$var108 < samples; j$var108 += 1) {
				for(int k = 0; k < samples; k += 1) {
					int[] var119 = var118[j$var108];
					if(!fixedFlag$sample128)
						var119[k] = DistributionSampling.sampleCategorical(RNG$, m[0]);
				}
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var31 = 0; var31 < states; var31 += 1) {
				if(!fixedFlag$sample33)
					sample33(var31);
			}
			for(int var47 = 0; var47 < states; var47 += 1) {
				if(!fixedFlag$sample50)
					sample50(var47);
			}
			if(!fixedFlag$sample88)
				sample88();
			for(int i$var99 = 1; i$var99 < samples; i$var99 += 1) {
				for(int j$var108 = 0; j$var108 < samples; j$var108 += 1) {
					for(int k = 0; k < samples; k += 1) {
						if(!fixedFlag$sample128)
							sample128(i$var99, j$var108, k);
					}
				}
			}
		} else {
			for(int i$var99 = (samples - ((((samples - 1) - 1) % 1) + 1)); i$var99 >= ((1 - 1) + 1); i$var99 -= 1) {
				for(int j$var108 = (samples - ((((samples - 1) - 0) % 1) + 1)); j$var108 >= ((0 - 1) + 1); j$var108 -= 1) {
					for(int k = (samples - ((((samples - 1) - 0) % 1) + 1)); k >= ((0 - 1) + 1); k -= 1) {
						if(!fixedFlag$sample128)
							sample128(i$var99, j$var108, k);
					}
				}
			}
			if(!fixedFlag$sample88)
				sample88();
			for(int var47 = (states - ((((states - 1) - 0) % 1) + 1)); var47 >= ((0 - 1) + 1); var47 -= 1) {
				if(!fixedFlag$sample50)
					sample50(var47);
			}
			for(int var31 = (states - ((((states - 1) - 0) % 1) + 1)); var31 >= ((0 - 1) + 1); var31 -= 1) {
				if(!fixedFlag$sample33)
					sample33(var31);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		states = 2;
		for(int i$var17 = 0; i$var17 < 2; i$var17 += 1)
			v[i$var17] = 0.1;
		samples = length$flipsMeasured.length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var20 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample33)
			logProbability$var32 = 0.0;
		logProbability$var36 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample50)
			logProbability$var48 = 0.0;
		logProbability$var82 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample88)
			logProbability$var83 = 0.0;
		for(int i$var99 = 1; i$var99 < samples; i$var99 += 1) {
			for(int j$var108 = 0; j$var108 < samples; j$var108 += 1) {
				for(int k = 0; k < samples; k += 1)
					logProbability$var122[((i$var99 - 1) / 1)][((j$var108 - 0) / 1)][((k - 0) / 1)] = 0.0;
			}
		}
		if(!fixedProbFlag$sample128) {
			for(int i$var99 = 1; i$var99 < samples; i$var99 += 1) {
				for(int j$var108 = 0; j$var108 < samples; j$var108 += 1) {
					for(int k = 0; k < samples; k += 1)
						logProbability$sample128[((i$var99 - 1) / 1)][((j$var108 - 0) / 1)][((k - 0) / 1)] = 0.0;
				}
			}
		}
		for(int l = 0; l < samples; l += 1) {
			for(int p = 0; p < samples; p += 1) {
				for(int n = 0; n < samples; n += 1)
					logProbability$var187[((l - 0) / 1)][((p - 0) / 1)][((n - 0) / 1)] = 0.0;
			}
		}
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample195) {
			for(int l = 0; l < samples; l += 1) {
				for(int p = 0; p < samples; p += 1) {
					for(int n = 0; n < samples; n += 1)
						logProbability$sample195[((l - 0) / 1)][((p - 0) / 1)][((n - 0) / 1)] = 0.0;
				}
			}
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample33)
			logProbabilityValue$sample33();
		if(fixedFlag$sample50)
			logProbabilityValue$sample50();
		if(fixedFlag$sample88)
			logProbabilityValue$sample88();
		if(fixedFlag$sample128)
			logProbabilityValue$sample128();
		logProbabilityValue$sample195();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample33();
		logProbabilityValue$sample50();
		logProbabilityValue$sample88();
		logProbabilityValue$sample128();
		logProbabilityValue$sample195();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample33();
		logProbabilityValue$sample50();
		logProbabilityValue$sample88();
		logProbabilityValue$sample128();
		logProbabilityValue$sample195();
	}

	@Override
	public final void logProbabilityGeneration() {
		for(int var31 = 0; var31 < states; var31 += 1) {
			double[] var32 = m[var31];
			if(!fixedFlag$sample33)
				DistributionSampling.sampleDirichlet(RNG$, v, var32);
		}
		for(int var47 = 0; var47 < states; var47 += 1) {
			if(!fixedFlag$sample50)
				bias[var47] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		int[][] var76 = st[0];
		int[] var78 = var76[0];
		if(!fixedFlag$sample88)
			var78[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		for(int i$var99 = 1; i$var99 < samples; i$var99 += 1) {
			int[][] var118 = st[i$var99];
			for(int j$var108 = 0; j$var108 < samples; j$var108 += 1) {
				for(int k = 0; k < samples; k += 1) {
					int[] var119 = var118[j$var108];
					if(!fixedFlag$sample128)
						var119[k] = DistributionSampling.sampleCategorical(RNG$, m[0]);
				}
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		boolean[][][] cv$source1 = flipsMeasured;
		boolean[][][] cv$target1 = flips;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[][] cv$source2 = cv$source1[cv$index1];
			boolean[][] cv$target2 = cv$target1[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1) {
				boolean[] cv$source3 = cv$source2[cv$index2];
				boolean[] cv$target3 = cv$target2[cv$index2];
				int cv$length3 = cv$target3.length;
				for(int cv$index3 = 0; cv$index3 < cv$length3; cv$index3 += 1)
					cv$target3[cv$index3] = cv$source3[cv$index3];
			}
		}
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart4b(boolean[][][] flipsMeasured) {\n        int states = 2;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n        \n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        \n        int[][][] st = new int[samples][samples][samples];\n\n        st[0][0][0] = categorical(m[0]).sample();\n\n        for(int i:[1..samples))\n            for(int j:[0..samples))\n                for(int k:[0..samples))\n                    st[i][j][k] = categorical(m[0]).sample();\n            \n        boolean[][][] flips = new boolean[samples][][];\n        for(int i:[0..samples)) {\n            flips[i] = new boolean[samples][];\n            for(int j:[0..samples))\n                flips[i][j] = new boolean[samples];\n        }\n            \n        for(int l:[0..samples))\n            for(int p:[0..samples))\n                for(int n:[0..samples))\n                    flips[l][n][p] = bernoulli(bias[st[p][l][n]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
	}
}